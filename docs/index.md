---
title: Home
permalink: index.html
---

# Pharmacogenomics Clinical Annotation Tool

An active area of genomic medicine implementation at many health care organizations and academic medical centers includes development of decision support and return of results around pharmacogenomics.  One of the challenges in implementing pharmacogenomics is the representation of the information in clinical dosing guidelines, including star-allele haplotypes, and extracting these variants and haplotypes from genetic datasets.  In a collaboration between the [Pharmacogenomics Knowledgebase (PharmGKB)](https://www.pharmgkb.org) and the former [PGRN Statistical Analysis Resource (P-STAR)](http://www.pgrn.org/p-star.html), with input from other groups, we are developing a software tool to extract guideline variants from a genetic dataset (represented as a vcf), interpret the variant alleles, and generate a report with genotype-based prescribing recommendations which can be used to inform treatment decisions.
The [Clinical Pharmacogenetics Implementation Consortium (CPIC)](https://cpicpgx.org) has established guidelines surrounding gene-drug pairs that can and should lead to treatment modifications based on genetic variants.  These guidelines are used for the initial version of PharmCAT, and other sources of PGx information and guidelines will be included in the future.

References:
- Commentary: TE Klein, MD Ritchie. [PharmCAT: A Pharmacogenomics Clinical Annotation Tool](https://dx.doi.org/10.1002/cpt.928). Clinical Pharmacology & Therapeutics (2018) 104(1):19-22.
- Methods paper: K Sangkuhl & M Whirl-Carrillo, et al. [Pharmacogenomics Clinical Annotation Tool (PharmCAT)](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6977333). Clinical Pharmacology & Therapeutics (2020) 107(1):203-210.

PharmCAT is under active development.

## Documentation

[Summary of Genes and Drugs included in PharmCAT](/summary)

[Technical documentation is on the GitHub wiki](https://github.com/PharmGKB/PharmCAT/wiki). This is where you can learn how to run PharmCAT including data requirements. 

Read [the PharmCAT wiki page on named allele matching](https://github.com/PharmGKB/PharmCAT/wiki/NamedAlleleMatcher-101) to learn how PharmCAT matches genotype data to allele definitions.

There are detailed documents on how a few particular genes are handled by PharmCAT. See the [gene definition exceptions](methods/gene-definition-exceptions) for a rundown of exceptional circumstances when analyzing particular genes. 

## Running PharmCAT

To learn how to run PharmCAT, [read the instructions on GitHub](https://github.com/PharmGKB/PharmCAT/wiki/Running-PharmCAT).  Please make sure to also read and understand PharmCAT's [VCF requirements](https://github.com/PharmGKB/PharmCAT/wiki/VCF-Requirements).

## Examples

We have an example collection of synthetic input and output files generated by PharmCAT.

[View examples of input and output files](examples).

## Contact

If you are interested in testing the tool or have questions, please contact [pharmcat@pharmgkb.org](mailto:pharmcat@pharmgkb.org).

## Team

### Co-PIs

Teri Klein (Stanford University) and Marylyn Ritchie (University of Pennsylvania)

### Current Team

| Name | Institution |
|:-----|:------------|
| Binglan Li | Stanford University |
| Katrin Sangkuhl | Stanford University |
| Mark Woon | Stanford University |  
| Michelle Whirl-Carrillo | Stanford University |
| Ryan Whaley | Stanford University |
| Yuki Bradford | University of Pennsylvania |
| Scott Dudek | University of Pennsylvania |
| Anastasia Lucas | University of Pennsylvania |
| Sony Tuteja | University of Pennsylvania |
| Anurag Verma | University of Pennsylvania |
| Shefali Setia Verma | University of Pennsylvania |


### Scientific Advisory Board

| Name |
|:-----|
|Burns Blaxall |
|Rhonda DeHoff |
|Phil Empey |
|Andrea Gaedigk |
|Houda Hachad |
|Jonathan Haines |
|James Hoffman |
|Janina Jeff |
|Stuart Scott|
|Casey Overby Taylor |
|Sara Van Driest |
|Marc Williams | 

### Past Contributors

| Name | Institution |
|:-----|:------------|
|Solomon Adams| University of Pittsburgh |
|Lester Carter | Stanford (formerly) |
|Mark Dunnenberger| Northshore University Health System |
|Philip Empey| University of Pittsburgh |
|Alex Frase| University of Pennsylvania |
|Robert Freimuth | Mayo Clinic |
|Andrea Gaedigk| Children’s Mercy Hospital |
|Adam Gordon | University of Washington |
|Cyrine Haidar | St Jude Children’s Research Hospital |
|James Hoffman| St Jude Children’s Research Hospital |
|Kevin Hicks | Moffitt Cancer Center & Research Institute |
|Ming Ta (Mike) Lee | Geisinger |
|Neil Miller| Children’s Mercy Hospital |
|Sean Mooney | University of Washington |
|Minoli Perera |  Northwestern University |
|Thomas Person | Geisinger |
|Josh Peterson | Vanderbilt University |
|Stuart Scott | Stanford University |
|Greyson Twist | Children’s Mercy Hospital|
|Marc Williams | Geisinger |
|Chunlei Wu | Scrips Research Institute |
|Wenjian Yang | St Jude Children’s Research Hospital |
