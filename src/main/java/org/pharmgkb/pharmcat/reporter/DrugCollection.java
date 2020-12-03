package org.pharmgkb.pharmcat.reporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.pharmgkb.pharmcat.reporter.model.cpic.Drug;


/**
 * This class will read the drugs.json file and serve the data as {@link Drug} objects.
 */
public class DrugCollection {
  private static final String FILE_NAME = "drugs.json";
  private static final Type DRUG_LIST_TYPE = new TypeToken<ArrayList<Drug>>(){}.getType();
  private static final Gson GSON = new GsonBuilder()
      .serializeNulls()
      .excludeFieldsWithoutExposeAnnotation()
      .setPrettyPrinting().create();

  private final List<Drug> m_drugList = new ArrayList<>();

  public DrugCollection() throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME)))) {
      m_drugList.addAll(GSON.fromJson(br, DRUG_LIST_TYPE));
    }
  }

  public int size() {
    return m_drugList.size();
  }

  /**
   * Get all CPIC {@link Drug} objects
   * @return a List of CPIC {@link Drug} objects
   */
  public List<Drug> list() {
    return m_drugList;
  }

  /**
   * Find a CPIC {@link Drug} object that has the given ID or name
   * @return an optional CPIC {@link Drug} object
   */
  public Optional<Drug> find(String identifier) {
    if (StringUtils.isBlank(identifier)) return Optional.empty();
    return m_drugList.stream()
        .filter((d) -> d.getDrugId().equalsIgnoreCase(identifier) || d.getDrugName().equalsIgnoreCase(identifier))
        .findFirst();
  }
}
