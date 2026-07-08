# Lab Exercise 9 - Advanced Programming

## Task 3

<div class="clearfix" id="yui_3_18_1_1_1767631949465_85">
<p>Implement a mailing list system that allows users to subscribe to a specific mailing list and automatically receive e-mail messages whenever a new post is published on that list.</p>
<p>The system should enable centralized management of mailing lists, with each list maintaining its own collection of subscribed users and being responsible for sending notifications when new content is published. Users should not directly check for new posts, but notification should occur automatically.</p>
<p>It is necessary to implement a <strong>User</strong> interface that contains a method:</p>
<ul>
<li><code data-start="898" data-end="948">void notify(String mailingListName, String text)</code></li>
</ul>
<p>This method is called whenever a new post is published on the mailing list to which the user is subscribed.</p>
<p>There can be several types of users:</p>
<ul>
<li><strong>MailingListUser</strong> - receives all messages from the mailing list</li>
<li><strong>FilteredMailingListUser</strong> - receives all messages that contain a certain keyword</li>
<li><strong>AdminUser</strong> - reads all messages but treats them as administrator logs</li>
</ul>
<p>In addition, you need to define an interface <strong>MailingList</strong> that contains the methods:</p>
<ul>
<li data-start="1570" data-end="1646">
<p data-start="1572" data-end="1646"><code data-start="1572" data-end="1599">void subscribe(User user)</code> – adds a user to the list of subscribers</p>
</li>
<li data-start="1647" data-end="1718">
<p data-start="1649" data-end="1718"><code data-start="1649" data-end="1678">void unsubscribe(User user)</code> – removes a user from the list</p>
</li>
<li data-start="1719" data-end="1812">
<p data-start="1721" data-end="1812"><code data-start="1721" data-end="1748">void publish(String text)</code> – publishes a new text and notifies all subscribed users</p>
</li>
</ul></div>

### Starter code
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO

public class MailingListTest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<String, MailingList> mailingLists = new HashMap<>();
        Map<String, User> usersByEmail = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] parts = line.split(" ");

            String command = parts[0];

            switch (command) {

                case "CREATE_LIST": {
                    String listName = parts[1];
                    mailingLists.put(listName, new SimpleMailingList(listName));
                    break;
                }

                case "ADD_USER": {
                    String listName = parts[1];
                    String type = parts[2];
                    String name = parts[3];
                    String email = parts[4];

                    User user;
                    if (type.equals("NORMAL")) {
                        user = new MailingListUser(name, email);
                    } else if (type.equals("FILTERED")) {
                        String keyword = parts[5];
                        user = new FilteredMailingListUser(name, email, keyword);
                    } else { // ADMIN
                        user = new AdminUser(name, email);
                    }

                    usersByEmail.put(email, user);
                    mailingLists.get(listName).subscribe(user);
                    break;
                }

                case "REMOVE_USER": {
                    String listName = parts[1];
                    String email = parts[2];

                    User user = usersByEmail.get(email);
                    mailingLists.get(listName).unsubscribe(user);
                    break;
                }

                case "PUBLISH": {
                    String listName = parts[1];
                    String text = line.substring(
                            line.indexOf(listName) + listName.length() + 1
                    );
                    mailingLists.get(listName).publish(text);
                    break;
                }
            }
        }
    }
}
```

### Solution
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


interface User {
    void notify(String mailingListName, String text);
}

abstract class AbstractUser implements User {
    protected String name;
    protected String email;

    public AbstractUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class MailingListUser extends AbstractUser {

    public MailingListUser(String name, String email) {
        super(name, email);
    }

    @Override
    public void notify(String mailingListName, String text) {
        //[USER] Ana received email from FINKI: Lab 1 is out
        System.out.printf("[USER] %s received email from %s: %s\n",name, mailingListName, text);
    }
}

class FilteredMailingListUser extends AbstractUser {
    private String keyword;

    public FilteredMailingListUser(String name, String email, String keyword) {
        super(name, email);
        this.keyword = keyword;
    }

    @Override
    public void notify(String mailingListName, String text) {
        if (text.toLowerCase().contains(keyword.toLowerCase())) {
           //[FILTERED USER] David received filtered email from JAVA: Homework 3 is assigned
            System.out.printf("[FILTERED USER] %s received filtered email from %s: %s\n",name, mailingListName, text);
        }
    }
}

class AdminUser extends AbstractUser {

    public AdminUser(String name, String email) {
        super(name, email);
    }

    @Override
    public void notify(String mailingListName, String text) {
        //[ADMIN LOG] MailingList=FINKI | Message=Exam schedule is updated
        System.out.printf("[ADMIN LOG] MailingList=%s | Message=%s\n",mailingListName, text);
    }
}


interface MailingList {
    void subscribe(User user);

    void unsubscribe(User user);

    void publish(String text);
}


class SimpleMailingList implements MailingList {
    private String name;
    private List<User> subscribers;

    public SimpleMailingList(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(User user) {
        subscribers.add(user);
    }

    @Override
    public void unsubscribe(User user) {
        subscribers.remove(user);
    }

    @Override
    public void publish(String text) {
        notifySubscribers(text);
    }

    private void notifySubscribers(String text) {
        subscribers.forEach(s -> s.notify(name, text));
    }
}

public class MailingListTest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<String, MailingList> mailingLists = new HashMap<>();
        Map<String, User> usersByEmail = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] parts = line.split(" ");

            String command = parts[0];

            switch (command) {

                case "CREATE_LIST": {
                    String listName = parts[1];
                    mailingLists.put(listName, new SimpleMailingList(listName));
                    break;
                }

                case "ADD_USER": {
                    String listName = parts[1];
                    String type = parts[2];
                    String name = parts[3];
                    String email = parts[4];

                    User user;
                    if (type.equals("NORMAL")) {
                        user = new MailingListUser(name, email);
                    } else if (type.equals("FILTERED")) {
                        String keyword = parts[5];
                        user = new FilteredMailingListUser(name, email, keyword);
                    } else { // ADMIN
                        user = new AdminUser(name, email);
                    }

                    usersByEmail.put(email, user);
                    mailingLists.get(listName).subscribe(user);
                    break;
                }

                case "REMOVE_USER": {
                    String listName = parts[1];
                    String email = parts[2];

                    User user = usersByEmail.get(email);
                    mailingLists.get(listName).unsubscribe(user);
                    break;
                }

                case "PUBLISH": {
                    String listName = parts[1];
                    String text = line.substring(
                            line.indexOf(listName) + listName.length() + 1
                    );
                    mailingLists.get(listName).publish(text);
                    break;
                }
            }
        }
    }
}
```