package A01_ListyIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListyIterator {
    private int counter;
    private List<String> elements;

    public ListyIterator() {
        this.counter = 0;
        this.elements = new ArrayList<>();
    }

    public void Create(String... elements){
        this.elements = Arrays.asList(elements);
    }

    public boolean Move(){
        if (this.HasNext()){
            this.counter++;
            return true;
        }

        return false;
    }

    public boolean HasNext(){
        return this.counter < this.elements.size() - 1;
    }

    public void Print(){
        if (this.elements.isEmpty()){
            throw new IllegalStateException("Invalid Operation!");
        }

        System.out.println(this.elements.get(this.counter));
    }
}
