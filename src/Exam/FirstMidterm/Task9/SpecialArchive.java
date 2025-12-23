package Exam.FirstMidterm.Task9;

import java.time.LocalDate;

public class SpecialArchive extends Archive {
    private int maxOpen;
    private int numOpened;

    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        this.numOpened = 0;
    }

    @Override
    public void openItem(LocalDate date, StringBuilder sb) {
        if (numOpened == maxOpen) {
            sb.append(String.format("Item %d cannot be opened more than %d times\n", id, maxOpen));
            return;
        }
        sb.append(String.format("Item %d opened at %s\n", id, date));
        numOpened++;
    }
}