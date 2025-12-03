# Lab Exercise 4 - Advanced Programming

## Task 1


This task is very similar to task number 5 from laboratory exercise 2 – however, now you will use a map to implement the class `Scheduler` and you do NOT need the class `Timestamp`.

Instructions for the task: Develop the class `Scheduler`. This class stores objects of a generic type **T** that are uniquely referenced by time (an object of the class `Date`). The class should implement the following methods:

* `Scheduler()` – creates a new empty scheduler
* `add(Date d, T t)` – adds a new object to the scheduler
* `remove(Date d): boolean` – deletes the corresponding element from the scheduler if it exists and returns true, otherwise returns false
* `next(): T` – returns the next object, i.e., the object associated with the date closest to the current date and still not passed
* `last(): T` – returns the next object, i.e., the object associated with the date closest to the current date but already passed
* `getAll(Date begin, Date end): ArrayList<T>` – returns a list of objects whose dates are between begin and end

**Note:** Two objects CANNOT have the same date.

Additionally, implement two additional methods:

* `getFirst(): T` – returns the object associated with the earliest date (overall)
* `getLast(): T` – returns the object associated with the latest date (overall)

### Starter code
```java
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class SchedulerTest {


    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.getFirst());
            System.out.println(scheduler.getLast());
        }
        if ( k == 3 ) { //test Scheduler with String
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.next());
            System.out.println(scheduler.last());
            ArrayList<String> res = scheduler.getAll(new Date(now.getTime()-10000000), new Date(now.getTime()+17000000));
            Collections.sort(res);
            for ( String t : res ) {
                System.out.print(t+" , ");
            }
        }
        if ( k == 4 ) {//test Scheduler with ints complex
            Scheduler<Integer> scheduler = new Scheduler<Integer>();
            int counter = 0;
            ArrayList<Date> to_remove = new ArrayList<Date>();

            while ( jin.hasNextLong() ) {
                Date d = new Date(jin.nextLong());
                int i = jin.nextInt();
                if ( (counter&7) == 0 ) {
                    to_remove.add(d);
                }
                scheduler.add(d,i);
                ++counter;
            }
            jin.next();

            while ( jin.hasNextLong() ) {
                Date l = new Date(jin.nextLong());
                Date h = new Date(jin.nextLong());
                ArrayList<Integer> res = scheduler.getAll(l,h);
                Collections.sort(res);
                System.out.println(l+" <: "+print(res)+" >: "+h);
            }
            System.out.println("test");
            ArrayList<Integer> res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
            for ( Date d : to_remove ) {
                scheduler.remove(d);
            }
            res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
        }
    }

    private static <T> String print(ArrayList<T> res) {
        if ( res == null || res.size() == 0 ) return "NONE";
        StringBuffer sb = new StringBuffer();
        for ( T t : res ) {
            sb.append(t+" , ");
        }
        return sb.substring(0, sb.length()-3);
    }


}
```

### Solution
```java
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

class Scheduler<T> {
    Map<Date, T> map;

    public Scheduler() {
        map = new TreeMap<>();
    }
    public void add(Date d, T t) {
        map.putIfAbsent(d, t);
    }

    public boolean remove(Date d) {
        return map.remove(d) != null;
    }

    public T next() {
        Date now = new Date();
        Date next = map.keySet()
                .stream()
                .filter(date -> date.after(now))
                .min(Comparator.comparing(Date::getTime)).orElse(now);
        return map.get(next);
    }

    public T last() {
        Date now = new Date();
        Date last = map.keySet()
                .stream()
                .filter(date -> date.before(now))
                .max(Comparator.comparing(Date::getTime)).orElse(now);
        return map.get(last);
    }

    public ArrayList<T> getAll(Date begin, Date end) {
        return map.entrySet().stream()
                .filter(e -> e.getKey().after(begin) && e.getKey().before(end))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public T getFirst() {
        Date date = map.keySet().stream().findFirst().orElse(new Date());
        return map.get(date);
    }

    public T getLast() {
        Date date = map.keySet().stream().max(Comparator.comparing(Date::getTime)).orElse(new Date());
        return map.get(date);
    }
}




public class SchedulerTest {


    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.getFirst());
            System.out.println(scheduler.getLast());
        }
        if ( k == 3 ) { //test Scheduler with String
            Scheduler<String> scheduler = new Scheduler<String>();
            Date now = new Date();
            scheduler.add(new Date(now.getTime()-7200000), jin.next());
            scheduler.add(new Date(now.getTime()-3600000), jin.next());
            scheduler.add(new Date(now.getTime()-14400000), jin.next());
            scheduler.add(new Date(now.getTime()+7200000), jin.next());
            scheduler.add(new Date(now.getTime()+14400000), jin.next());
            scheduler.add(new Date(now.getTime()+3600000), jin.next());
            scheduler.add(new Date(now.getTime()+18000000), jin.next());
            System.out.println(scheduler.next());
            System.out.println(scheduler.last());
            ArrayList<String> res = scheduler.getAll(new Date(now.getTime()-10000000), new Date(now.getTime()+17000000));
            Collections.sort(res);
            for ( String t : res ) {
                System.out.print(t+" , ");
            }
        }
        if ( k == 4 ) {//test Scheduler with ints complex
            Scheduler<Integer> scheduler = new Scheduler<Integer>();
            int counter = 0;
            ArrayList<Date> to_remove = new ArrayList<Date>();

            while ( jin.hasNextLong() ) {
                Date d = new Date(jin.nextLong());
                int i = jin.nextInt();
                if ( (counter&7) == 0 ) {
                    to_remove.add(d);
                }
                scheduler.add(d,i);
                ++counter;
            }
            jin.next();

            while ( jin.hasNextLong() ) {
                Date l = new Date(jin.nextLong());
                Date h = new Date(jin.nextLong());
                ArrayList<Integer> res = scheduler.getAll(l,h);
                Collections.sort(res);
                System.out.println(l+" <: "+print(res)+" >: "+h);
            }
            System.out.println("test");
            ArrayList<Integer> res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
            for ( Date d : to_remove ) {
                scheduler.remove(d);
            }
            res = scheduler.getAll(new Date(0),new Date(Long.MAX_VALUE));
            Collections.sort(res);
            System.out.println(print(res));
        }
    }

    private static <T> String print(ArrayList<T> res) {
        if ( res == null || res.size() == 0 ) return "NONE";
        StringBuffer sb = new StringBuffer();
        for ( T t : res ) {
            sb.append(t+" , ");
        }
        return sb.substring(0, sb.length()-3);
    }


}
```