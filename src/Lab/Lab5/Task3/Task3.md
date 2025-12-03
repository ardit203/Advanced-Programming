# Lab Exercise 5 - Advanced Programming
## Task 3
<div class="clearfix" id="yui_3_18_1_1_1764716849579_88">
<p id="yui_3_18_1_1_1764716849579_87">Implement a class <code>LibrarySystem</code> that will manage book reservations in a library. The class should contain <strong>members</strong> and <strong>books</strong>.</p>
<h5>Book class</h5>
<p>The class <code>Book</code> consists of:</p>
<ul>
<li>identification number - isbn</li>
<li>title</li>
<li>year of publication</li>
<li>and other attributes needed for successfully tracking copies and loans.</li>
</ul>
<h5>Member class</h5>
<p>The class <code>Member</code> consists of:</p>
<ul>
<li>identification number - id</li>
<li>name</li>
<li>and other attributes needed for borrowing books.</li>
</ul>
<h5>Methods for the <code>LibrarySystem</code> class</h5>
<ul>
<li>Constructor: <code>LibrarySystem(String name)</code></li>
<li><code>void registerMember(String id, String fullName)</code> – registers a member among the other members, where each member initially has <strong>no</strong> borrowed books.</li>
<li><code>addBook(String isbn, String title, String author, int year)</code> – adds a book to the library, where a single book may have multiple <strong>copies</strong>.
<ol>
<li>If a book with the same <strong>ISBN</strong> already exists, then the number of copies is increased by 1.</li>
<li>If it does not exist – it is added with 1 copy.</li>
</ol>
</li>
<li><code>void borrowBook(String memberId, String isbn)</code> – the member wants to borrow a book.
<p><strong>Rules:</strong></p>
<ol>
<li>If the book does not exist, the action is ignored.</li>
<li>If the book exists but has no free copies, the member is placed on a <strong>waiting list</strong> for that book.</li>
<li>If there is a free copy: it is assigned to the member and the number of available copies is <strong>decreased</strong>.</li>
</ol>
</li>
<li><code>void returnBook(String memberId, String isbn)</code> – when a member returns a book:
<ol>
<li>The number of available copies is <strong>increased</strong>.</li>
<li>If there is a waiting list for that book – the <strong>first member</strong> on the list is automatically assigned a loan of the book (same as <code>borrowBook</code>).</li>
</ol>
</li>
<li><code>void printMembers()</code> – print all members sorted by number of borrowed books (descending), and if equal, by member name (ascending).
<p><strong>Example for one line:</strong></p>
<pre><code>Gorazd (id27) - borrowed now: 5, total borrows: 17</code></pre>
</li>
<li><code>void printBooks()</code> – print all books sorted by number of borrowings so far (descending), and if equal by year of publication (ascending).
<p><strong>Example for one line:</strong></p>
<pre><code>isbn1 - “The Hobbit” by Goch (2025), available: 199, total borrows: 2</code></pre>
</li>
<li><code>void printBookCurrentBorrowers(String isbn)</code> – print the current ID numbers of the borrowers of the book with that ISBN, sorted and separated by commas.</li>
<li><code>void printTopAuthors()</code> – print the authors sorted by number of borrowings of their books (descending), and if equal, by name (ascending).
<p><strong>Example for one line:</strong></p>
<pre><code>Goch - 127</code></pre>
</li>
</ul></div>

### Starter code:
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// todo: implement the necessary classes

public class LibraryTester {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      String libraryName = br.readLine();
    //   System.out.println(libraryName); //test
      if (libraryName == null) return;

      libraryName = libraryName.trim();
      LibrarySystem lib = new LibrarySystem(libraryName);

      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.equals("END")) break;
        if (line.isEmpty()) continue;

        String[] parts = line.split(" ");

        switch (parts[0]) {

          case "registerMember": {
            lib.registerMember(parts[1], parts[2]);
            break;
          }

          case "addBook": {
            String isbn = parts[1];
            String title = parts[2];
            String author = parts[3];
            int year = Integer.parseInt(parts[4]);
            lib.addBook(isbn, title, author, year);
            break;
          }

          case "borrowBook": {
            lib.borrowBook(parts[1], parts[2]);
            break;
          }

          case "returnBook": {
            lib.returnBook(parts[1], parts[2]);
            break;
          }

          case "printMembers": {
            lib.printMembers();
            break;
          }

          case "printBooks": {
            lib.printBooks();
            break;
          }

          case "printBookCurrentBorrowers": {
            lib.printBookCurrentBorrowers(parts[1]);
            break;
          }

          case "printTopAuthors": {
            lib.printTopAuthors();
            break;
          }

          default:
            break;
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
```