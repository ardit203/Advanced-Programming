package Exercises._03_Generics.Box;

public class BoxTester {
    static void main() {
        Box<Integer> box1 = new Box<>();
        box1.add(1);
        box1.add(2);
        box1.add(3);
        box1.add(4);
        box1.add(5);
        box1.add(6);

        System.out.println(box1.drawItem());
        System.out.println(box1.drawItem());
        System.out.println(box1.drawItem());
        System.out.println();

        Box<MyClass> box2 = new Box<>();
        box2.add(new MyClass("name 1", "desc 1"));
        box2.add(new MyClass("name 2", "desc 2"));
        box2.add(new MyClass("name 3", "desc 3"));
        box2.add(new MyClass("name 4", "desc 4"));
        box2.add(new MyClass("name 5", "desc 5"));
        box2.add(new MyClass("name 6", "desc 6"));

        System.out.println(box2.drawItem());
        System.out.println(box2.drawItem());
        System.out.println(box2.drawItem());
    }
}
