package p06_GenericFlatMethod;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T extends Comparable<T>> T getMax(List<T> list){
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }

        T max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (max.compareTo(list.get(i)) < 0){
                max = list.get(i);
            }
        }

        return max;
    }

    public static <T extends Comparable<T>> T getMin(List<T> list){
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }

        T min = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (min.compareTo(list.get(i)) > 0){
                min = list.get(i);
            }
        }

        return min;
    }

    public static List<Integer> getNullIndices(List<?> list){
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null){
                indices.add(i);
            }
        }

        return indices;
    }

    public static <T> void flatten(List<? super T> destination, List<List<? extends T>> source){
        for (List<? extends T> item : source) {
            destination.addAll(item);
        }
    }
}
