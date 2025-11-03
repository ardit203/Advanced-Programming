package Sandbox._04_Generics;

class Box<T> {
    private T value;

    public Box(T value) {//You don't need to specify the parameter type in the constructor
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

class IntBox {
    private int value;

    public IntBox(int value) {
        this.value = value;
    }

    public void set(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}

class StringBox {
    private String value;

    public StringBox(String value) {
        this.value = value;
    }

    public void set(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}

class ObjectBox {
    private Object value;

    public ObjectBox(Object value) {
        this.value = value;
    }

    public void set(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}

class Pair<T, U, E> {
    T element1;
    U element2;
    E element3;

    public Pair(T element1, U element2, E element3) {
        this.element1 = element1;
        this.element2 = element2;
        this.element3 = element3;
    }
}

class Person {
    String name;
    String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}

class Student extends Person{
    private int id;

    public Student(String name, String surname, int id) {
        super(name, surname);
        this.id = id;
    }
}

public class GenericExploring {
    static void main() {

        //-------------------------------1. base generic---------------------------------------------------------

        IntBox intBox1 = new IntBox(10);
        StringBox strBox = new StringBox("Hello");

        Box<Integer> integerBox1 = new Box<>(10);
        Box<String> stringBox1 = new Box<>("Hello");

        //Using generics we just created a general (generic) class and created objects of two different types


        //----------------------------------2. Problems with casting-----------------------------------------------


        ObjectBox intBox2 = new ObjectBox(10);
        //int value = intBox.get(); --this wont work without casting
        int value = (int) intBox2.get();

        ObjectBox strBox2 = new ObjectBox("Hello");

        //int str = (int) strBox.get(); -- even with casting the program compiles successfully
        // but will crash later throwing ClassCastException, causing a RunTimeError

        Box<Integer> integerBox2 = new Box<>(10);
        int integerValue = integerBox2.get(); //no casting needed

        Box<String> stringBox2 = new Box<>("Hello");
        String str = stringBox2.get();

        //String s = (String) integerBox2.get(); -- this won't even compile
        //int x = (Integer) stringBox2.get(); -- this won't even compile
        //integerBox2 = stringBox2;
        //RunTimeError's have no become CompileTimeError's

        integerBox2.equals(new Box<>(10)); //will print true

        //-------------------------------------3. raw types ----------------------------------------------------------

        Box<Integer> intBox3 = new Box<>(10);
        Box rawBox = new Box(10); // this is a raw type (works sometimes! - not recommended)
        System.out.println(rawBox.get());

        //---------------------------------------4. Restrictions-----------------------------------------------------

        //Box<int> intBox4 = new Box<>(10);
        //Box<char> charBox4 = new Box<>('a');
        //Box<long> longBox4; -- primitive types cannot be used as Type Arguments
        //Generic type arguments must be Reference Types (classes, interfaces, arrays...)

        Box<Integer> intBox4 = new Box<>(10);
        Box<Character> charBox4 = new Box<>('a');
        Box<Long> longBox4;

        class Item<T> {
            //T value = new T(); -- you cannot directly instantiate a Type Parameter directly
            //T [] objects = new T[5];
            T[] objects = (T[]) new Object[10];
        }

        //Box<Person>[] array = new Box<Person>[10]; -- you can't do this
        Box<Person>[] array = new Box[10]; //But you can do this, but who knows what will happen
        array[0] = new Box<>(new Person("Person 1", "Person 1"));
        array[1] = new Box<>(new Person("Person 2", "Person 2"));
        for (int i = 0; i < 2; i++) {
            System.out.println(array[i]);
        }

        //class CustomException<E> extends Exception{
            //You cannot create a generic class from the Exception, Error, Throwable,
            // or any class derived from Throwable
        //}

        //-------------------------------5. multiple type parameters---------------------------------------------------


        Pair<Integer, String, Person> pair = new Pair<>(10, "hello", new Person("name", "surname"));

        //-------------------------------6. Generic method---------------------------------------------------
        String genericMethod = get("hello");
        //Generic class generic method (same type)
        class Test1<T>{//generic class
            T element;

            public Test1(T element) {
                this.element = element;
            }

            public T getElement() {//generic method
                return element;
            }
        }

        //Generic class generic method (different type)
        class Test2<T>{//generic class
            T element;
            //static T staticTelement; -- T cannot be referenced from a static context

            public Test2(T element) {
                this.element = element;
            }

            public <U> T getElement(U uElement, T tElement) {
                return tElement;
            }

            public <U> U getElement2(U uElement, T tElement) {
                return uElement;
            }

            public static  <U> U add(U uElement) {
                return uElement;
            }
        }

        //Generic class non-generic method
        class Test3<T>{
            T element;
            int index;
            public Test3(T element) {
                this.element = element;
                this.index = 0;
            }

            public int getIndex(){
                return index;
            }

        }

        //Normal class generic method
        class Test4{
            Integer element;

            public Test4(Integer element) {
                this.element = element;
            }

            public <T> T get(T content){
                return content;
            }

            public static <T> T get2(T content){
                return content;
            }
        }

        //-------------------------------7. Generic Interfaces---------------------------------------------------


        //-------------------------------8. Inheritance Rules---------------------------------------------------



    }

    public static <T> T get(T element){
        return element;
    }
}
