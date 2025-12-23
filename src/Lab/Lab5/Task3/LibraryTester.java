package Lab.Lab5.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// todo: implement the necessary classes

public class LibraryTester {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      String libraryName = br.readLine();
    //   System.out.println(libraryName); //test.txt
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
