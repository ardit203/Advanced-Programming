package Exam.FirstMidterm.Task8;

import java.util.Date;

public abstract class Archive {
    int id;
    Date dateArchived;

    public Archive(int id) {
        this.id = id;
        this.dateArchived = null;
    }

    public int getId() {
        return id;
    }

    public void setDateArchived(Date dateArchived) {
        this.dateArchived = dateArchived;
    }

    public abstract void openItem(Date date, StringBuilder sb);
}
