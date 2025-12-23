Implement a class `ArchiveStore` which stores a list of archives (archivable elements).

Each archivable element `Archive` has:

* id – integer
* dateArchived – the date when it was archived.

There are two types of archivable elements: `LockedArchive`, which additionally stores the date until which it must not be opened `dateToOpen`, and `SpecialArchive`, which stores the maximum number of allowed openings `maxOpen`. For the archivable elements, the following constructors must be provided:

* `LockedArchive(int id, LocalDate dateToOpen)` – constructor for a locked archive
* `SpecialArchive(int id, int maxOpen)` – constructor for a special archive

For the class `ArchiveStore`, the following methods must be provided:

* `ArchiveStore()` – default constructor
* `void archiveItem(Archive item, LocalDate date)` – method for archiving the element item on the given date date
* `void openItem(int id, LocalDate date)` – method for opening an element from the archive with the given id and the specified date. If an element with the given id does not exist, an exception of type `NonExistingItemException` must be thrown with message
  `Item with id [id] doesn't exist.`
* `String getLog()` – returns a string with the log messages for archiving and opening archives, each on a separate line.

For every archiving action, the following message must be added to the text:
`Item [id] archived at [date]`

For every archive-opening action, the following message must be added:
`Item [id] opened at [date]`

If the archive is a `LockedArchive` and the opening date is before the date when it may be opened, add the message:
`Item [id] cannot be opened before [date]`

If it is a `SpecialArchive` and the element is opened more times than allowed (`maxOpen`), add the message:
`Item [id] cannot be opened more than [maxOpen] times`


### Starter code
```java


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();

            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}
```


### Solution
```java

import java.time.LocalDate;
import java.util.*;

public class ArchiveStoreTest {
    public static void main(String[] args) {
        ArchiveStore store = new ArchiveStore();
        LocalDate date = LocalDate.of(2013, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();

            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}

class NonExistingItemException extends RuntimeException{
    public NonExistingItemException(int id) {
        super(String.format("Item with id %d doesn't exist", id));
    }
}

abstract class Archive {
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

class LockedArchive extends Archive {
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

class SpecialArchive extends Archive {
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

class ArchiveStore {

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
```