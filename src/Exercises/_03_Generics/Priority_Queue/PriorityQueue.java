package Exercises._03_Generics.Priority_Queue;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Drawable> {
    private List<Item<T>> items;

    public PriorityQueue() {
        this.items = new ArrayList<>();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void add(T element, int priority){
        items.add(new Item<>(element, priority));
    }

    public Item<T> remove(){
        Item<T> max = items.get(0);

        for (int i = 1; i < items.size(); i++) {
            Item<T> current = items.get(i);
            if(current.getPriority() > max.getPriority()){
                max = current;
            }
        }

        items.remove(max);
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item<T> item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }

}
