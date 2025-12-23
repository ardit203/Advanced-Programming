package Exercises._08_Concurrent_Programming._1_Thread_and_Runnable;

import java.util.List;

class Person {
    long id;
    String name;

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s", id, name);
    }
}

class PersonCreatorThread extends Thread{
    int name;

    public PersonCreatorThread(int name) {
        this.name = name;
    }

    @Override
    public void run() {
        Person p = new Person(Thread.currentThread().threadId(), Thread.currentThread().getName());
        String str = "Creator: " + (name - 1) + " -> " + p;
        System.out.println(str);
    }
}

public class TestThread {
    static void main() throws InterruptedException {
        PersonCreatorThread p1 = new PersonCreatorThread(1);
        PersonCreatorThread p2 = new PersonCreatorThread(2);
        PersonCreatorThread p3 = new PersonCreatorThread(3);
        PersonCreatorThread p4 = new PersonCreatorThread(4);
        PersonCreatorThread p5 = new PersonCreatorThread(5);

        List<PersonCreatorThread> threads = List.of(p1,p2,p3,p4,p5);

        threads.forEach(Thread::start);

        for (PersonCreatorThread thread : threads) {
            thread.join();
        }
    }

}
