package Exercises._05_Intro_to_Maps.Meals;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HealthyMeals {
    private List<String> healthyMeals;

    public HealthyMeals(){
        this.healthyMeals = new ArrayList<>();
    }

    public void evaluate(InputStream is, OutputStream os) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        PrintWriter pw = new PrintWriter(os);
        String meals = br.readLine();
        healthyMeals = Arrays.asList(meals.split("\\s++"));

        List<Person> persons = new ArrayList<>();

        persons = br.lines().filter(Objects::nonNull).map(Person::create).collect(Collectors.toList());

        persons.stream()
                .sorted(Comparator.comparingInt((Person p) -> p.count(healthyMeals))
                        .reversed()
                        .thenComparingInt(Person::getId)).forEach(pw::println);
        pw.flush();
        pw.close();
    }



    /*
salad apple yogurt
101 salad pizza salad soup yogurt
102 apple yogurt coffee
103 burger fries
    * */

    static void main() throws IOException {
        HealthyMeals hm = new HealthyMeals();
        hm.evaluate(System.in, System.out);
    }
}
