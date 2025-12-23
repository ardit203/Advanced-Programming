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