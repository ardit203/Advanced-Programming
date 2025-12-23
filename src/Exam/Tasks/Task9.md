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
