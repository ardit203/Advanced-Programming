package Lab.Lab5.Task1;

import java.util.Set;
import java.util.TreeSet;

public class ChatRoom {
    private String name;
    private Set<String> users;

    public ChatRoom(String name) {
        this.name = name;
        this.users = new TreeSet<>();
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public boolean hasUser(String username) {
        return users.contains(username);
    }

    public int numUsers() {
        return users.size();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        if (numUsers() == 0) {
            sb.append("EMPTY\n");
        } else {
            users.forEach(u -> sb.append(u).append("\n"));
        }
        return sb.toString();
    }
}
