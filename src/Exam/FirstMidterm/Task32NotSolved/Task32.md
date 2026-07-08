For the needs of the Ministry of Health, it is necessary to make an application that will manage the users of the application and the contacts they have been in close proximity with.
Define a class `StopCoronaApp` and implement the following for it:

* default constructor
* method `void addUser(String name, String id)` – which registers a new user of the application; the method should throw an exception of type `UserIdAlreadyExistsException` if a user with the same id already exists.
* method `void addLocations (String id, List<ILocation> iLocations)` – which, for the user with ID identical to the first argument, will register all of their detected locations. `ILocation` is an interface given in the starter code and provides information about the longitude and latitude of the location, as well as the time when the location was detected.
* method `void detectNewCase (String id, LocalDateTime timestamp)` – which simulates reporting that a given user is a virus carrier. The first argument is the user’s ID, and the second is the time when the user reported that they are a carrier.
* method `Map<User, Integer> getDirectContacts (User u)` – which returns a map where the keys are all close contacts of the user `u`, and the corresponding values in the map are how many times close contacts were recorded with the user `u`.
* method `Collection<User> getIndirectContacts (User u)` – which returns a collection of indirect contacts of the user `u`. Indirect contacts are the close contacts of the direct contacts of `u`, with the condition that a user cannot be both a direct and an indirect contact of the same user.
* method `void createReport ()` – which will create and print a report for the Ministry of Health in the following format, for all users detected as virus carriers:

    ```
    [user_name] [user_id] [timestamp_detected]
    Direct contacts:
    [contact1_name] [contact1_first_five_letters_of_id] [number_of_detected_contacts1]
    [contact2_name] [contact2_first_five_letters_of_id] [number_of_detected_contacts2]
    ...
    [contactN_name] [contactN_first_five_letters_of_id] [number_of_detected_contactsN]
    Count of direct contacts: [sum]
    Indirect contacts:
    [contact1_name] [contact1_first_five_letters_of_id] 
    [contact2_name] [contact2_first_five_letters_of_id] 
    ...
    [contactN_name] [contactN_first_five_letters_of_id]
    Count of indirect contacts: [count]
    ```

Additionally, at the end of the report, print the average number of direct and indirect contacts of the users who are virus carriers.

---

**Note:**

* A close contact is considered when the Euclidean distance between any of their locations is `<= 2`, and the time difference of the corresponding measured locations is less than 5 minutes.
* Virus carriers should be sorted by the time they were detected as carriers.
* The direct contacts of the carriers should be sorted by the number of detected close contacts in descending order.
* The indirect contacts should be sorted lexicographically by their name, and if identical, then by the user ID.

### Starter code
```java

```

### Solution
```java

```