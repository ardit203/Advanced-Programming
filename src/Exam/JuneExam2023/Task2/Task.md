To implement a class `Post` in which the information for a post on a social network will be stored. In the class, implement the following methods:

* Constructor `Post(String username, String postContent)`
* `void addComment(String username, String commentId, String content, String replyToId)` – method for adding a comment with ID `commentId` and content `content` from the user with username `username`.
  A comment can be added **directly to the post** (`replyToId = null` in that case) or it can be a **reply to an already existing comment/reply**.
* `void likeComment(String commentId)` – method for liking a comment.
* `String toString()` – `toString` representation of a post in the format shown below. Comments are listed in **descending order** according to the **number of likes** (the total number of likes includes likes on replies to the comment, as well as likes on replies of replies, etc.).

** The solutions that allow only comments on the post will be graded with **50% of the points**. This is also reflected in the test cases (50% of the test cases contain only comments on the post, while 50% contain nested comments and replies).

### Starter code
```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class PostTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String postAuthor = sc.nextLine();
        String postContent = sc.nextLine();

        Post p = new Post(postAuthor, postContent);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            String testCase = parts[0];

            if (testCase.equals("addComment")) {
                String author = parts[1];
                String id = parts[2];
                String content = parts[3];
                String replyToId = null;
                if (parts.length == 5) {
                    replyToId = parts[4];
                }
                p.addComment(author, id, content, replyToId);
            } else if (testCase.equals("likes")) { //likes;1;2;3;4;1;1;1;1;1 example
                for (int i = 1; i < parts.length; i++) {
                    p.likeComment(parts[i]);
                }
            } else {
                System.out.println(p);
            }

        }
    }
}
```