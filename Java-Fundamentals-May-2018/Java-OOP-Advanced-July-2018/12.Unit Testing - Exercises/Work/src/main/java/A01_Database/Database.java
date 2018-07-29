package A01_Database;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    public static final int MAX_CAPACITY = 16;

    private Integer[] array;

    public Database(Integer[] array) throws OperationNotSupportedException {
        this.array = new Integer[MAX_CAPACITY];
        this.setArray(array);
    }

    private void setArray(Integer[] array) throws OperationNotSupportedException {
        if (array.length > MAX_CAPACITY){
            throw new OperationNotSupportedException("Lenght is more than 16");
        }

        for (int i = 0; i < MAX_CAPACITY; i++) {
            try {
                this.array[i] = array[i];
            } catch (Exception ignored){}
        }
    }

    public void add(Integer number) throws OperationNotSupportedException {
        if (number == null){
            throw new OperationNotSupportedException("Number is null");
        } else if (Arrays.asList(this.array).contains(null) == false){
            throw new OperationNotSupportedException("The array is full");
        }

        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] == null){
                this.array[i] = number;
                break;
            }
        }
    }

    public Integer remove() throws OperationNotSupportedException{
        if (isEmpty()){
            throw new OperationNotSupportedException("The array is empty");
        }

        for (int i = this.array.length - 1; i >= 0; i--) {
            if (this.array[i] != null){
                Integer result = this.array[i];
                this.array[i] = null;
                return result;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        boolean isEmpty = true;
        for (Integer element : this.array) {
            if (element != null){
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    public Integer[] fetch() throws OperationNotSupportedException{
        return this.array;
    }
}
