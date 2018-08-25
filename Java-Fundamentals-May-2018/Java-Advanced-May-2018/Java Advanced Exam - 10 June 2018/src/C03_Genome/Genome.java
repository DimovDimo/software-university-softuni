package C03_Genome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Genome {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> organisms = new LinkedHashMap<>();

        Pattern genomePattern = Pattern.compile("^([!@#$?a-z]+)=(\\d+)--(\\d+)<<([a-z]+)$");
        while (true){
            String line = reader.readLine();
            if ("Stop!".equals(line)){
                break;
            }
            
            Matcher genomeMatcher = genomePattern.matcher(line);
            if (genomeMatcher.find()){
//                String[] genome = genomeMatcher.group().split("(\\[=-<\\]+)");
                String nomeOfGene = Arrays.stream(genomeMatcher.group(1).split("[!@#$?]+")).collect(Collectors.joining(""));
                int lenghtOfName = Integer.parseInt(genomeMatcher.group(2));
                int countOfGenes = Integer.parseInt(genomeMatcher.group(3));
                String nomeOfOrganism = String.valueOf(genomeMatcher.group(4));
                if (nomeOfGene.length() == lenghtOfName){
                    if (!organisms.containsKey(nomeOfOrganism)){
                        organisms.put(nomeOfOrganism, countOfGenes);
                    } else {
                        int currentSumGenes = organisms.get(nomeOfOrganism) + countOfGenes;
                        organisms.put(nomeOfOrganism, currentSumGenes);
                    }
                }
            }
        }

        organisms.entrySet().stream()
                .sorted((organism1, organism2) -> {
                    int countGenesOfOrganism1 = organism1.getValue();
                    int countGenesOfOrganism2 = organism2.getValue();
                    return countGenesOfOrganism2 - countGenesOfOrganism1;
                })
                .forEach(organism -> {
                    System.out.printf("%s has genome size of %d%n", organism.getKey(), organism.getValue());
                });
    }
}
