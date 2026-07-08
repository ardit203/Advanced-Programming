Implement a class for a phone book `PhoneBook` with the following methods:

* `void addContact(String name, String number)` – adds a new contact to the phone book. If we try to add a contact with an already existing number, an exception of class `DuplicateNumberException` should be thrown with the message `Duplicate number: [number]`. The complexity of this method should not exceed $O(\log N)$ for $N$ contacts.

* `void contactsByNumber(String number)` – prints all contacts whose number contains the number passed as an argument to the method (the minimum length of the number [number] is 3). The complexity of this method should not exceed *O(log N)* for *N* contacts.

* `void contactsByName(String name)` – prints all contacts with the given name. The complexity of this method should be *O(1)*.

In both methods, the contacts are printed sorted lexicographically by name, and those with the same name are then sorted by number.


### Starter code
```java
import java.util.Scanner;

public class PhoneBookTest {

	public static void main(String[] args) {
		PhoneBook phoneBook = new PhoneBook();
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(":");
			try {
				phoneBook.addContact(parts[0], parts[1]);
			} catch (DuplicateNumberException e) {
				System.out.println(e.getMessage());
			}
		}
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
            System.out.println(line);
			String[] parts = line.split(":");
			if (parts[0].equals("NUM")) {
				phoneBook.contactsByNumber(parts[1]);
			} else {
				phoneBook.contactsByName(parts[1]);
			}
		}
	}

}
```

### Solution
```java

```