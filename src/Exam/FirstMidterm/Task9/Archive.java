package Exam.FirstMidterm.Task9;

import java.time.LocalDate;

public abstract class Archive {
    int id;
    LocalDate dateArchived;

    public Archive(int id) {
        this.id = id;
        this.dateArchived = null;
    }

    public int getId() {
        return id;
    }

    public void setDateArchived(LocalDate dateArchived) {
        this.dateArchived = dateArchived;
    }

    public abstract void openItem(LocalDate date, StringBuilder sb);
}