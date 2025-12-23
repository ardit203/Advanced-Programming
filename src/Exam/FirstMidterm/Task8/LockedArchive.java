package Exam.FirstMidterm.Task8;

import java.util.Date;

public class LockedArchive extends Archive {
    Date dateToOpen;

    public LockedArchive(int id, Date dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public void openItem(Date date, StringBuilder sb) {
        if (date.before(this.dateToOpen)) {
            sb.append(String.format("Item %d cannot be opened before %s\n", id, dateToOpen));
            return;
        }
        sb.append(String.format("Item %d opened at %s\n", id, date));
    }
}
