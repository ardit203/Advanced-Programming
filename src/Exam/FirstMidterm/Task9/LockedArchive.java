package Exam.FirstMidterm.Task9;

import java.time.LocalDate;

public class LockedArchive extends Archive {
    LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public void openItem(LocalDate date, StringBuilder sb) {
        if (date.isBefore(this.dateToOpen)) {
            sb.append(String.format("Item %d cannot be opened before %s\n", id, dateToOpen));
            return;
        }
        sb.append(String.format("Item %d opened at %s\n", id, date));
    }
}
