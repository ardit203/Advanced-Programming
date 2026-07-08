package Exercises._03_Generics.Priority_Queue;


public class QueueTest {


    static void main() {
        PriorityQueue<DrawingItem> queue = new PriorityQueue<>();
        queue.add(new DrawingItem("D1"), 10);
        queue.add(new DrawingItem("D2"), 100);
        queue.add(new DrawingItem("D3"), 1);

        while (!queue.isEmpty()){
            System.out.println(queue.remove());
        }
    }

}
