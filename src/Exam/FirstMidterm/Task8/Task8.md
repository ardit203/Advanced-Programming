You need to implement a class `ArchiveStore` that stores a list of archives (elements for archiving).

Each element for archiving `Archive` has:

* id – integer
* dateArchived – the date when it was archived

There are two types of archivable elements:
`LockedArchive`, which additionally stores the date until which it must not be opened `dateToOpen`, and
`SpecialArchive`, which stores the maximum number of allowed openings `maxOpen`.

For archivable elements, the following constructors must be provided:

* `LockedArchive(int id, Date dateToOpen)` – constructor for a locked archive
* `SpecialArchive(int id, int maxOpen)` – constructor for a special archive

For the class `ArchiveStore`, the following methods must be ensured:

* `ArchiveStore()` – default constructor
* `void archiveItem(Archive item, Date date)` – method to archive an element `item` on a given date `date`
* `void openItem(int id, Date date)` – method to open an element from the archive with the given `id` on a specified date `date`. If an element with the given `id` does not exist, an exception of type
  `NonExistingItemException` should be thrown with the message
  `Item with id [id] doesn't exist.`
* `String getLog()` – returns a string containing the log messages for archiving and opening archives, each on a separate line.

For every archiving action, the following message must be added to the log:
`Item [id] archived at [date]`

For every archive-opening action, the following message must be added:
`Item [id] opened at [date]`

If the archive is a `LockedArchive` and the opening date is before the allowed date, add the message:
`Item [id] cannot be opened before [date]`

If the archive is a `SpecialArchive` and the item is attempted to be opened more times than the allowed number (`maxOpen`), add the message:
`Item [id] cannot be opened more than [maxOpen] times`


### Starter code
```java
import java.util.Date;
import java.util.Scanner;

public class ArchiveStoreTest {
	public static void main(String[] args) {
		ArchiveStore store = new ArchiveStore();
        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.NOVEMBER, 7,0,0,0);
        Date date = cal.getTime();
		Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
		int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
		int i;
		for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
			long days = scanner.nextLong();
			Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
					* 60 * 1000));
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
import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

public class ArchiveStoreTest {
	public static void main(String[] args) {
		ArchiveStore store = new ArchiveStore();

        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.NOVEMBER, 7,0,0,0);
        Date date = cal.getTime();

		Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
		int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
		int i;
		for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
			long days = scanner.nextLong();
			Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
					* 60 * 1000));
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


class LockedArchive extends Archive {
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


class SpecialArchive extends Archive {
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

class ArchiveStore {

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
```