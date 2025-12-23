Write a class for a book `Book` in which the following are stored:

* title
* category
* price.

Implement a constructor with the following arguments
`Book(String title, String category, float price)`.

Then write a class `BookCollection` in which a collection of books is stored.
In this class the following methods should be implemented:

* `public void addBook(Book book)` – adds a book to the collection
* `public void printByCategory(String category)` – prints all books from the given category (the string is compared ignoring upper/lower case), sorted by the title of the book (if the title is the same, they are sorted by price)
* `public List<Book> getCheapestN(int n)` – returns a list of the N cheapest books (if there are fewer than N books in the collection, it returns all of them).
