package Exam.SecondMidtermExam.Task43;

import java.util.Comparator;

public class Contact implements Comparable<Contact> {
    String name;
    String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(Contact o) {
        return Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber).compare(this, o);
    }

    @Override
    public String toString() {
        return String.format("%s %s",name, number);
    }
}