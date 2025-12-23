package Exam.FirstMidterm.Task8;

import java.util.Date;

public class SpecialArchive extends Archive {
    private int maxOpen;
    private int numOpened;

    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        this.numOpened = 0;
    }

    @Override
    public void openItem(Date date, StringBuilder sb) {
        if (numOpened == maxOpen) {
            sb.append(String.format("Item %d cannot be opened more than %d times\n", id, maxOpen));
            return;
        }
        sb.append(String.format("Item %d opened at %s\n", id, date));
        numOpened++;
    }
}
