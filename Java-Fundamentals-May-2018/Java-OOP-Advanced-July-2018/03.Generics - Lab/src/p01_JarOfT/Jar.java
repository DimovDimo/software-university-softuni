package p01_JarOfT;

import java.util.ArrayDeque;
import java.util.Deque;

public class Jar<T> {

    private Deque<T> items;

    public Jar() {
        this.items = new ArrayDeque<>();
    }

    public void add(T element){
        this.items.push(element);
    }

    public T remove(){
        return this.items.poll();
    }
}
