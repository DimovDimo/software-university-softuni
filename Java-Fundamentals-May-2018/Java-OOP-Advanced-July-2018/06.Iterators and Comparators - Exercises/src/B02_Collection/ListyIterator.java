package B02_Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ListyIterator implements Iterable {
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
        return this.counter < this.elements.size() -1;
    }

    public void Print(){
        checkIllegalStateException();

        System.out.println(this.elements.get(this.counter));
    }

    private void checkIllegalStateException() {
        if (this.elements.isEmpty()){
            throw new IllegalStateException("Invalid Operation!");
        }
    }

    public void PrintAll(){
        checkIllegalStateException();

        System.out.println(String.join(" ", this::iterator));
    }

    @Override
    public Iterator iterator() {
        return new InsideIterator();
    }

    private final class InsideIterator implements Iterator<String>{

        private int counter;

        public InsideIterator() {
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return this.counter < elements.size();
        }

        @Override
        public String next() {
            return elements.get(this.counter++);
        }
    }
}
