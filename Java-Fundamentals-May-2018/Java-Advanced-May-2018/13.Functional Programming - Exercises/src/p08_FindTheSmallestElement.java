import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class p08_FindTheSmallestElement {
    public static void main(String[] args) throws IOException {
        Function<List<Integer>, Integer> getIndexOfSmallestRightmastOne = nums -> {
            Integer smalest = Integer.MAX_VALUE;
            Integer smalestIndex = 0;
            for (int i = 0; i < nums.size(); i++) {
                if (nums.get(i) <= smalest){
                    smalest = nums.get(i);
                    smalestIndex = i;
                }
            }

            return smalestIndex;
        };

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split("\\s+");
        List<Integer> numersInput = new LinkedList<>();
        for (int i = 0; i < inputs.length; i++) {
            numersInput.add(Integer.parseInt(inputs[i]));
        }

        System.out.println(getIndexOfSmallestRightmastOne.apply(numersInput));
    }
}
