package Exam.SecondMidtermExam.Task43;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> contactsByNameMap;
    private Map<String, Set<Contact>> contactsByNumberMap;
    private Set<String> numbers;

    public PhoneBook() {
        this.contactsByNameMap = new HashMap<>();
        this.contactsByNumberMap = new HashMap<>();
        this.numbers = new HashSet<>();
    }

    public void addContact(String name, String number) {
        if (numbers.contains(number)) {
            throw new DuplicateNumberException(number);
        }

        contactsByNameMap.computeIfAbsent(name, k -> new TreeSet<>()).add(number);

        List<String> subStrings = createSubStrings(number);

        Contact contact = new Contact(name, number);
        subStrings.forEach(s -> {
            contactsByNumberMap.computeIfAbsent(s, k -> new TreeSet<>()).add(contact);
        });


    }

    private List<String> createSubStrings(String number) {
        List<String> subStrings = new ArrayList<>();
        int len = number.length();
        for (int i = 0; i <= len; i++) {
            for (int j = i + 3; j <= len; j++) {
                subStrings.add(number.substring(i, j));
            }
        }
        return subStrings;
    }

    public void contactsByNumber(String number) {
        Set<Contact> contacts = contactsByNumberMap.get(number);

        if(contacts == null){
            System.out.println("NOT FOUND");
            return;
        }

        contacts.forEach(System.out::println);
    }

    public void contactsByName(String name) {
        Set<String> contacts = contactsByNameMap.get(name);
        if(contacts == null){
            System.out.println("NOT FOUND");
            return;
        }
        contacts.forEach(c -> System.out.println(name + " " + c));
    }
}