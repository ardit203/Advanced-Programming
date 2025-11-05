package Exercises._03_Generics.Priority_Queue;

public class DrawingItem implements Drawable {
    private String item;

    public DrawingItem(String item) {
        this.item = item;
    }

    @Override
    public String draw() {
        return item;
    }

    @Override
    public String toString() {
        return item;
    }
}
