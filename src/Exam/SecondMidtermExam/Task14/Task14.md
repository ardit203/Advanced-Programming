## Define a class `Component` that stores:

* the **color**
* the **weight**
* a **collection of inner components** (references to class `Component`)

In this class, implement the following methods:

* `Component(String color, int weight)` – constructor with arguments color and weight
* `void addComponent(Component component)` – adds a new component to the internal collection
  (In this collection, the components must **always be sorted by weight in ascending order**;
  components with equal weight must be sorted **alphabetically by color**.)


## Define a class `Window` that stores:

* the **name**
* the **components**

In this class, implement the following methods:

* `Window(String)` – constructor

* `void addComponent(int position, Component component)` – adds a new component at a given **position** (integer).
  Each position can contain only one component; if we try to add a component to an already taken position,
  the method must throw an `InvalidPositionException` with the message
  `Invalid position [pos], already taken!`
  Components should be **sorted in ascending order based on their position**.

* `String toString()` – returns a string representation of the object (as shown in the sample output)

* `void changeColor(int weight, String color)` – changes the color of all components with weight **less than** the provided weight

* `void swichComponents(int pos1, int pos2)` – swaps the components at the provided positions


### Starter code
```java
import java.util.Scanner;
public class ComponentTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		Window window = new Window(name);
		Component prev = null;
		while (true) {
            try {
				int what = scanner.nextInt();
				scanner.nextLine();
				if (what == 0) {
					int position = scanner.nextInt();
					window.addComponent(position, prev);
				} else if (what == 1) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev = component;
				} else if (what == 2) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev.addComponent(component);
                    prev = component;
				} else if (what == 3) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev.addComponent(component);
				} else if(what == 4) {
                	break;
                }
                
            } catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
            scanner.nextLine();			
		}
		
        System.out.println("=== ORIGINAL WINDOW ===");
		System.out.println(window);
		int weight = scanner.nextInt();
		scanner.nextLine();
		String color = scanner.nextLine();
		window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
		System.out.println(window);
		int pos1 = scanner.nextInt();
		int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
		window.swichComponents(pos1, pos2);
		System.out.println(window);
	}
}
```

### Solution
```java

```