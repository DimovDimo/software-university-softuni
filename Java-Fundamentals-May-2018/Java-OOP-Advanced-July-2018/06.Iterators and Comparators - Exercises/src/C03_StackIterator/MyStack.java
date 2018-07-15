package C03_StackIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyStack implements Iterable<String> {

    private List<String> elements;

    public MyStack() {
        this.elements = new ArrayList<>();
    }

    public void push(String... elements){
        this.elements.addAll(Arrays.asList(elements));
    }

    public void pop(){
        if (this.elements.isEmpty()){
            throw new IndexOutOfBoundsException("No elements");
        }

        int lastIndex = this.elements.size() - 1;
        this.elements.remove(lastIndex);
    }

    @Override
    public Iterator<String> iterator() {
        return new MyStackIterator();
    }

    private final class MyStackIterator implements Iterator<String>{

        private int index;
        private boolean isTwice;
        private boolean isEnd;

        public MyStackIterator() {
            this.index = elements.size() - 1;
            this.isTwice = true;
            this.isEnd = false;
        }

        @Override
        public boolean hasNext() {
            if (this.isEnd){
                return false;
            }

           if (isTwice && 0 == this.index) {
               this.isTwice = false;
               return true;
           } else if (isTwice == false && 0 == this.index) {
               this.isEnd = true;
               return true;
           }

           return 0 <= this.index;
        }

        @Override
        public String next() {
            if (this.isEnd){
                return elements.get(this.index);
            }

            if (isTwice == false &&
                    this.index == 0){
                String returnElement = elements.get(this.index);
                this.index = elements.size() - 1;
                return returnElement;
            }

            return elements.get(this.index--);
        }
    }
}
