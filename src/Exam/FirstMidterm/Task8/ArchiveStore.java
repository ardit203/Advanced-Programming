package Exam.FirstMidterm.Task8;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ArchiveStore {

    private Map<Integer, Archive> archiveMap;
    private final StringBuilder sb = new StringBuilder();

    public ArchiveStore() {
        this.archiveMap = new HashMap<>();
    }

    public void archiveItem(Archive item, Date date) {
        item.setDateArchived(date);
        archiveMap.put(item.getId(), item);
        sb.append(String.format("Item %d archived at %s\n", item.getId(), date));
    }

    public void openItem(int id, Date date){
        Archive archive = archiveMap.get(id);
        
        if(archive == null){
            throw new NonExistingItemException(id);
        }
        
        archive.openItem(date, sb);
    }

    public String getLog() {
        return sb.toString();
    }
}
