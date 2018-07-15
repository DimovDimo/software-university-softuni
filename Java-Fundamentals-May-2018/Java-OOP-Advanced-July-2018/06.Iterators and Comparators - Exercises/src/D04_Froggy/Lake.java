package D04_Froggy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Lake<T> implements Iterable<T> {

    private List<T> lake;

    public Lake() {
        this.lake = new ArrayList<>();
    }

    public void setLake(T... lake) {
        this.lake.addAll(Arrays.asList(lake));
    }

    @Override
    public Iterator<T> iterator() {
        return new Frog();
    }

    private final class Frog implements Iterator<T>{

        private int evenIndex;
        private int oddIndex;
        private boolean isEvenIteration;
        private boolean getLastEven;

        public Frog() {
            this.evenIndex = 0;
            this.oddIndex = 1;
            this.isEvenIteration = true;
            this.getLastEven = false;
        }

        @Override
        public boolean hasNext() {
            if (this.isEvenIteration){
                if (this.evenIndex + 2 >= lake.size()){
                    this.isEvenIteration = false;
                    this.getLastEven = true;
                    return true;
                }

                return this.evenIndex + 2 < lake.size();
            }

            if (this.oddIndex + 2 == lake.size() + 1 || this.oddIndex + 2 == lake.size()){
                return true;
            }

            return this.oddIndex + 2 < lake.size();
        }

        @Override
        public T next() {
            if (this.getLastEven){
                this.getLastEven = false;
                return lake.get(this.evenIndex);
            }

            if (this.isEvenIteration){
                this.evenIndex += 2;
                return lake.get(this.evenIndex - 2);
            }

            this.oddIndex += 2;
            return lake.get(this.oddIndex - 2);
        }
    }
}
