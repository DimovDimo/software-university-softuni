package p07_GenericAddAllMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 1, 2, 3, 4, 5);

        Integer min = ListUtils.getMin(integers);
        Integer max = ListUtils.getMax(integers);
    }
}
