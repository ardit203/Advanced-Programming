You need to implement a class for auditions `Audition` with the following methods:

* `void addParticipant(String city, String code, String name, int age)`
  adds a new participant with code `code`, name, and age for an audition in the given city `city`.
  In the same city, it is not allowed to add a participant with the same code as a previously added participant (the addition is ignored, and the complexity of this method must be `O(1)`).

* `void listByCity(String city)`
  prints all participants from the given city sorted by name, and if the names are the same, sorted by age
  (the complexity of this method must not exceed `O(n*log₂(n))`, where `n` is the number of participants in the given city).

### Starter code
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuditionTest {
	public static void main(String[] args) {
		Audition audition = new Audition();
		List<String> cities = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			if (parts.length > 1) {
				audition.addParticpant(parts[0], parts[1], parts[2],
						Integer.parseInt(parts[3]));
			} else {
				cities.add(line);
			}
		}
		for (String city : cities) {
			System.out.printf("+++++ %s +++++\n", city);
			audition.listByCity(city);
		}
		scanner.close();
	}
}
```

### Solution
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class AuditionTest {
	public static void main(String[] args) {
		Audition audition = new Audition();
		List<String> cities = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			if (parts.length > 1) {
				audition.addParticpant(parts[0], parts[1], parts[2],
						Integer.parseInt(parts[3]));
			} else {
				cities.add(line);
			}
		}
		for (String city : cities) {
			System.out.printf("+++++ %s +++++\n", city);
			audition.listByCity(city);
		}
		scanner.close();
	}
}

class Participant implements Comparable<Participant> {
    private String code;
    private String name;
    private int age;

    public Participant(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Participant other) {
        int compare1 = this.name.compareTo(other.name);
        if (compare1 == 0) return Integer.compare(this.age, other.age);
        return compare1;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d",code,name,age);
    }
}

class City {
    private String name;
    private Map<String, Participant> participants;

    public City(String name) {
        this.name = name;
        this.participants = new HashMap<>();
    }

    public void addParticpant(String code, String name, int age){
        participants.putIfAbsent(code, new Participant(code, name, age));
    }

    public void printSortedByNameAndAge(){
        participants.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(e.getValue()));
    }
}

class Audition {
    private Map<String, City> cities;

    public Audition() {
        this.cities = new HashMap<>();
    }

    public void addParticpant(String city, String code, String name, int age) {
        cities.computeIfAbsent(city, City::new).addParticpant(code, name, age);
    }

    public void listByCity(String city) {
        City c = cities.get(city);
        c.printSortedByNameAndAge();
    }
}
```