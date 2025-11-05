package Exercises._03_Generics.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box<T> {
    private List<T> items;
    private Random random;

    public Box(){
        this.items = new ArrayList<>();
        this.random = new Random();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void add(T item){
        items.add(item);
    }

    public T drawItem(){
        if (isEmpty()){
            return null;
        }
        return items.get(random.nextInt(0,items.size()));
    }

}
