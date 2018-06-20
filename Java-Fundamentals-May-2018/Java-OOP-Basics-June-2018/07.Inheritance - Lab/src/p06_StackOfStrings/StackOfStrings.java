package p06_StackOfStrings;

import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {
    private List<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item){
        this.data.add(item);
    }

    public String pop(){
        int lastIndex = data.size() - 1;
        String lastItem = data.get(lastIndex);
        data.remove(lastIndex);
        return lastItem;
    }

    public String peek(){
        int lastIndex = data.size() - 1;
        String lastItem = data.get(lastIndex);
        return lastItem;
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }
}
