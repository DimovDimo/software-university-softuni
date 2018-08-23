package A01_Internships;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Internships {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<String> problems = new ArrayDeque<>();
        ArrayDeque<String> candidates = new ArrayDeque<>();

        int countProblems = Integer.parseInt(reader.readLine());
        int countCandidates = Integer.parseInt(reader.readLine());

        for (int i = 0; i < countProblems; i++) {
            problems.push(reader.readLine());
        }

        Pattern pattern = Pattern.compile("^([A-Z][a-z]+ [A-Z][a-z]+)$");
        for (int i = 0; i < countCandidates; i++) {
            Matcher matcher = pattern.matcher(reader.readLine());
            if (matcher.find()){
                candidates.add(matcher.group());
            }
        }

        while (true){
            if (candidates.size() == 1){
                System.out.printf("%s gets the job!", candidates.remove());
                break;
            } else if (problems.size() == 0){
                System.out.println(candidates.stream().collect(Collectors.joining(", ")));
                break;
            }

            String candidate = candidates.remove();
            String problem = problems.pop();
            int candidateSumLetters = Arrays.stream(candidate.split("")).mapToInt(c -> (int)c.charAt(0)).sum();
            int problemSumLetters = Arrays.stream(problem.split("")).mapToInt(c -> (int)c.charAt(0)).sum();
            if (candidateSumLetters > problemSumLetters){
                candidates.add(candidate);
                System.out.printf("%s solved %s.%n", candidate, problem);
            } else {
                problems.add(problem);
                System.out.printf("%s failed %s.%n", candidate, problem);
            }
        }
    }
}
