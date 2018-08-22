import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class C03_Earthquake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int countLines = Integer.parseInt(reader.readLine());

        Deque<Deque<Integer>> sequences = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        while (countLines-- > 0){
            Deque<Integer> sequence = Arrays.stream(reader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

            sequences.add(sequence);
        }

        while (!sequences.isEmpty()){
            Deque<Integer> currentSequence = sequences.remove();

            int seismicity = currentSequence.remove();
            result.add(seismicity);
            while (!currentSequence.isEmpty()){
                int wave = currentSequence.peek();
                if (wave <= seismicity){
                    currentSequence.remove();
                } else {
                    break;
                }
            }

            if (!currentSequence.isEmpty()){
                sequences.add(currentSequence);
            }
        }

        System.out.println(result.size());
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
