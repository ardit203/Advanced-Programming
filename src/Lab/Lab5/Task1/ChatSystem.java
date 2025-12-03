package Lab.Lab5.Task1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChatSystem {
    private Map<String, ChatRoom> rooms;
    private Set<String> users;

    public ChatSystem() {
        this.rooms = new TreeMap<>();
        this.users = new TreeSet<>();
    }

    public void addRoom(String roomName) {
        rooms.putIfAbsent(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName) {
        rooms.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) {
        ChatRoom room = rooms.get(roomName);
        if (room == null) {
            throw new NoSuchRoomException(roomName);
        }
        return room;
    }

    private void existsUser(String userName) {
        if (!users.contains(userName)) {
            throw new NoSuchUserException(userName);
        }
    }

    public void register(String userName) {
        users.add(userName);
        rooms.values()
                .stream()
                .sorted(Comparator.comparingInt(ChatRoom::numUsers).thenComparing(ChatRoom::getName))
                .limit(1)
                .forEach(cr -> cr.addUser(userName));
    }

    public void registerAndJoin(String userName, String roomName) {
        users.add(userName);
        rooms.get(roomName).addUser(userName);
    }

    public void joinRoom(String userName, String roomName) {
        existsUser(userName);

        ChatRoom room = getRoom(roomName);
        room.addUser(userName);
    }

    public void leaveRoom(String username, String roomName) {
        existsUser(username);

        ChatRoom room = getRoom(roomName);
        room.removeUser(username);
    }

    public void followFriend(String username, String friend_username) {
        if (username.equals(friend_username)) {
            return;
        }

        existsUser(username);
        existsUser(friend_username);

        rooms.values()
                .stream()
                .filter(cr -> cr.hasUser(friend_username))
                .forEach(cr -> cr.addUser(username));


    }

    // Additional Requirement
    public Map<String, Set<String>> getAllRoomsByUsers() {
        Map<String, Set<String>> result = new HashMap<>();

        users.forEach(u -> {
            Set<String> userRooms = rooms.values()
                    .stream()
                    .filter(r -> r.hasUser(u))
                    .map(ChatRoom::getName)
                    .collect(Collectors.toSet());

            result.put(u, userRooms);
        });

        return result;
    }

    public Map<ChatRoom, Integer> getChatRoomStatistics(){
        return rooms.values()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),        //key
                        ChatRoom::numUsers,         //value
                        (a, b) -> a,  //merge logic
                        () -> new TreeMap<>(Comparator.comparing(ChatRoom::numUsers).reversed())
                ));
    }

}
