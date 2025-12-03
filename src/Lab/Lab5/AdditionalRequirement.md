### Req 1
Additional tasks for `LibrarySystem`:

- Implement a method `getBooksAndNumberOfBorrowings(): Map<Book, Integer>` which returns a map containing each book and the number of times it has been borrowed, sorted by the title of the book.
- Implement a method `getAuthorsWithBooks(): Map<String, TreeSet<String>>` which returns a map where the key is the author’s name, and the value is a `TreeSet` containing the ISBN numbers of all books written by that author.


### Req 2
Addition to `ChatSystem`:

* Implement a method **`getAllRoomsByUsers(): Map<String, Set<String>>`** which returns a map where the **key** is the *username* and the **value** is a *set* containing all chat rooms in which that user is included.

* Implement a method **`getChatRoomStatistics(): Map<ChatRoom, Integer>`** which returns a **sorted map** by the name of the `ChatRoom` **in descending order**, and the value is the number of users in that room.
