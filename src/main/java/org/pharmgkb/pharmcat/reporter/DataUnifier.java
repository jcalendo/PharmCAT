package org.pharmgkb.pharmcat.reporter;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;
import org.pharmgkb.pharmcat.haplotype.model.json.GeneCall;
import org.pharmgkb.pharmcat.reporter.model.CPICException;
import org.pharmgkb.pharmcat.reporter.model.CPICinteraction;
import org.pharmgkb.pharmcat.reporter.model.Group;
import org.pharmgkb.pharmcat.reporter.resultsJSON.GeneReport;
import org.pharmgkb.pharmcat.reporter.resultsJSON.Interaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * This is the primary class and method for matching data the the three different sources.
 * I hate just about everything about how this was done, but for the sake of a quick hack to get
 * reports up and running it will have to do.
 *
 * @author greytwist
 *
 */
public class DataUnifier {
  private static final Logger sf_logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private List<GeneCall> m_calls;
  private List<CPICinteraction> m_guidelines;
  private Multimap<String, String> m_sampleGeneToDiplotypeMap = TreeMultimap.create();
  private Map<String, GeneReport> m_symbolToGeneReportMap = new HashMap<>();

  /**
   * public constructor
   * @param calls GeneCall objects from the sample data
   * @param guidelines a List of all the guidelines to try to apply
   */
  public DataUnifier(List<GeneCall> calls, List<CPICinteraction> guidelines, Multimap<String, CPICException> exceptionMap) {
    m_calls = calls;
    m_guidelines = guidelines;
    compileGeneData(exceptionMap);
  }


  private void compileGeneData(Multimap<String, CPICException> exceptionMap) {
    ExceptionMatcher exceptionMatcher = new ExceptionMatcher();

    for (GeneCall call : m_calls) {

      // convert GeneCalls to GeneReports
      GeneReport gene = new GeneReport(call);

      if (exceptionMap.containsKey(call.getGene()) ){
        // add any known gene exceptions
        exceptionMap.get(call.getGene()).stream()
            .filter(exception -> exceptionMatcher.test(gene, exception.getMatches()))
            .forEach(gene::addException);
      }

      m_sampleGeneToDiplotypeMap.putAll(gene.getGene(), gene.getDips());

      m_symbolToGeneReportMap.put(call.getGene(), gene);
    }
  }

  /**
   *  Call to do the actual matching, this should all be broken out into
   *  independent methods so errors are clearly and atomically identified
   *  and handled.
   *
   *  This is going to need to be rethought through and reconstructed
   */
  public List<Interaction> findMatches() throws Exception {

    // This is the loop for looking through the cpic drug gene interactions and trying to figure out which apply to the situation
    List<Interaction> resultInteractions = new ArrayList<>();

    for(CPICinteraction guideline : m_guidelines) {
      DrugRecommendationMatcher drugRecommendationMatcher = new DrugRecommendationMatcher(m_symbolToGeneReportMap.keySet(), guideline);
      if (!drugRecommendationMatcher.matches()) {
        sf_logger.warn("Can't annotate guideline {}, it's missing {}",
            guideline.getName(),
            drugRecommendationMatcher.getNeededGenes());
        continue;
      }

      sf_logger.info("Able to use {}", guideline.getName());
      Interaction guidelineResult = new Interaction(guideline);

      Set<String> calledGenotypesForGuideline = makeAllCalledGenotypes(drugRecommendationMatcher.getDefinedGeneSymbolSet());

      for (Group annotationGroup : guideline.getGroups()) {
        calledGenotypesForGuideline.stream()
            .filter(calledGenotype -> annotationGroup.getGenotypes().contains(calledGenotype))
            .forEach(calledGenotype -> {
              guidelineResult.addMatchingGroup(annotationGroup);
              guidelineResult.putMatchedDiplotype(annotationGroup.getId(), calledGenotype);
            });
      }
      resultInteractions.add(guidelineResult);
    }
    return resultInteractions;
  }

  private Set<String> makeAllCalledGenotypes(Collection<String> geneSymbols) {
    Set<String> results = new TreeSet<>();
    for (String symbol : geneSymbols) {
      results = makeCalledGenotypes(symbol, results);
    }
    return results;
  }

  private Set<String> makeCalledGenotypes(String symbol, Set<String> results) {
    if (results.size() == 0) {
      return Sets.newHashSet(m_sampleGeneToDiplotypeMap.get(symbol));
    }
    else {
      Set<String> newResults = new TreeSet<>();
      for (String geno1 : results) {
        for (String geno2 : m_sampleGeneToDiplotypeMap.get(symbol)) {
          Set<String> genos = new TreeSet<>();
          genos.add(geno1);
          genos.add(geno2);
          newResults.add(genos.stream().collect(Collectors.joining(";")));
        }
      }
      return newResults;
    }
  }

  public Map<String, GeneReport> getSymbolToGeneReportMap() {
    return m_symbolToGeneReportMap;
  }
}
