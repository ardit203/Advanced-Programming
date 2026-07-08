package Exercises._05_Intro_to_Maps.Meals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Person {
    int id;
    private List<String> meals;
    private int count;

    public Person(int id){
        this.id = id;
        this.meals = new ArrayList<>();
        this.count = 0;
    }

    public static Person create(String line){
        String [] tokens = line.split("\\s++");
        int id = Integer.parseInt(tokens[0]);

        Person person = new Person(id);

        IntStream.range(1, tokens.length).forEach(i -> person.meals.add(tokens[i]));
        return person;
    }

    public int getId(){
        return id;
    }

    public int count(List<String> healthyMeals){
        count = (int) meals.stream().filter(healthyMeals::contains).distinct().count();
        return count;
    }


    @Override
    public String toString() {
        return String.format("Person ID: %d (healthy meals: %d)", id, count);
    }
}
