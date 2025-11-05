package Book.Ch1_OOP_in_Java.Problem1_3;

public abstract class Shape implements Scalable, Stackable {
    private String id;
    private Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        char c = '/';
        if (this instanceof Circle) {
            c = 'C';
        } else if (this instanceof Rectangle) {
            c = 'R';
        }
        return String.format("%c: %-5s%-10s%10.2f\n", c, id, color, weight());
    }
}
