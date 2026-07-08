package Exam.FirstMidterm.Task18;

public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        Category other = (Category) obj;
        return this.name.equals(other.name);
    }
}
