package Exam.FirstMidterm.Task9;

import java.time.LocalDate;
import java.util.*;

public class ArchiveStore {

//    private Map<Integer, Archive> archiveMap;
    private List<Archive> archiveList;
    private final StringBuilder sb = new StringBuilder();

    public ArchiveStore() {
//        this.archiveMap = new HashMap<>();
        this.archiveList = new ArrayList<>();
    }

    public void archiveItem(Archive item, LocalDate date) {
        item.setDateArchived(date);
//        archiveMap.put(item.getId(), item);
        archiveList.add(item);

        sb.append(String.format("Item %d archived at %s\n", item.getId(), date));
    }

    public void openItem(int id, LocalDate date){
//        Archive archive = archiveMap.get(id);
//        if(archive == null){
//            throw new NonExistingItemException(id);
//        }
        Archive archive = archiveList
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NonExistingItemException(id));


        archive.openItem(date, sb);
    }

    public String getLog() {
        return sb.toString();
    }
}
