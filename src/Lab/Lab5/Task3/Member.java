package Lab.Lab5.Task3;

public class Member {
    private String id;
    private String name;
    private int borrowed;
    private int totalBorrowed;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowed = 0;
        this.totalBorrowed = 0;
    }

    public void increment() {
        borrowed++;
    }

    public void decrement() {
        if (borrowed > 0) {
            borrowed--;
        }
    }

    public void incrementTotal(){
        totalBorrowed++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public int getTotalBorrowed() {
        return totalBorrowed;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - borrowed now: %d, total borrows: %d", name, id, borrowed, totalBorrowed);
    }
}
