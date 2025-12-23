package Exercises._08_Concurrent_Programming._1_Thread_and_Runnable;

import java.util.List;

class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class TestRunnable {
    static void main() throws InterruptedException {
        Runnable task1 = () -> {
            Animal animal = new Animal("1. Dog");
            System.out.println(animal);
        };

        Runnable task2 = () -> {
            Animal animal = new Animal("2. Cat");
            System.out.println(animal);
        };

        Runnable task3 = () -> {
            Animal animal = new Animal("3. Horse");
            System.out.println(animal);
        };

        Runnable task4 = () -> {
            Animal animal = new Animal("4. Chicken");
            System.out.println(animal);
        };

        List<Runnable> runnableList = List.of(task1,task2,task3,task4);

        //This by itself only runs the tasks sequentially, u can see that in the order of the animals in every execution
//        runnableList.forEach(Runnable::run);

        List<Thread> threads = List.of(new Thread(task1), new Thread(task2), new Thread(task3), new Thread(task4));

        //This makes them concurrent
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

    }
}
