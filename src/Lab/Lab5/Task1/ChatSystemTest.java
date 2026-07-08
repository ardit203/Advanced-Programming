package Lab.Lab5.Task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;

class NoSuchRoomException extends RuntimeException{
    public NoSuchRoomException(String roomName) {
        super(String.format("Room %s doesnt exists!", roomName));
    }
}

class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String userName) {
        super(String.format("User with %s doesnt exists!", userName));
    }
}

class ChatRoom implements Comparable<ChatRoom>{
    private String roomName;
    private Set<String> users;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.users = new TreeSet<>();
    }

    public void addUser(String username){
        users.add(username);
    }

    public void removeUser(String username){
        users.remove(username);
    }

    public boolean hasUser(String username){
       return users.contains(username);
    }

    public int numUsers(){
        return users.size();
    }

    public String getRoomName() {
        return roomName;
    }

    @Override
    public String toString() {
        String usersString = users.isEmpty() ? "EMPTY" : String.join("\n", users);
        return String.format("%s\n%s\n",roomName,usersString);
    }

    @Override
    public int compareTo(ChatRoom o) {
        return Comparator.comparingInt(ChatRoom::numUsers).thenComparing(ChatRoom::getRoomName).compare(this,o);
    }
}

class ChatSystem{
    private Set<String> users;
    private Map<String, ChatRoom> rooms;

    public ChatSystem() {
        this.users = new TreeSet<>();
        this.rooms = new TreeMap<>();
    }

    public void addRoom(String roomName){
        rooms.putIfAbsent(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName){
        rooms.remove(roomName);
    }

    public ChatRoom getRoom(String roomName){
        ChatRoom room = rooms.get(roomName);
        if(room == null){
            throw new NoSuchRoomException(roomName);
        }
        return room;
    }

    private void hasUser(String username) {
        if(!users.contains(username)){
            throw new NoSuchUserException(username);
        }
    }

    public void register(String username){
        users.add(username);
        rooms.values().stream().sorted()
                .limit(1)
                .forEach(r -> r.addUser(username));
    }

    public void registerAndJoin(String username, String roomName) {
        users.add(username);
        rooms.get(roomName).addUser(username);
    }

    public void joinRoom(String username, String roomName) {
        hasUser(username);
        getRoom(roomName).addUser(username);
    }

    public void leaveRoom(String username, String roomName) {
        hasUser(username);
        getRoom(roomName).removeUser(username);
    }

    public void followFriend(String username, String friendUsername) {
        if(username.equals(friendUsername)){
            return;
        }
        hasUser(username);
        hasUser(friendUsername);
        rooms.values()
                .stream()
                .filter(room -> room.hasUser(friendUsername))
                .forEach(room -> room.addUser(username));
    }

    @Override
    public String toString() {
        return rooms.values().stream().map(ChatRoom::toString).toString();
    }
}

public class ChatSystemTest {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
		Scanner jin = new Scanner(System.in);
		int k = jin.nextInt();
		if ( k == 0 ) {
			ChatRoom cr = new ChatRoom(jin.next());
			int n = jin.nextInt();
			for ( int i = 0 ; i < n ; ++i ) {
				k = jin.nextInt();
				if ( k == 0 ) cr.addUser(jin.next());
				if ( k == 1 ) cr.removeUser(jin.next()); 
				if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));  
			}
//			System.out.println("");
			System.out.println(cr.toString());
			n = jin.nextInt();
			if ( n == 0 ) return;
			ChatRoom cr2 = new ChatRoom(jin.next());
			for ( int i = 0 ; i < n ; ++i ) {
				k = jin.nextInt();
				if ( k == 0 ) cr2.addUser(jin.next());
				if ( k == 1 ) cr2.removeUser(jin.next()); 
				if ( k == 2 ) cr2.hasUser(jin.next());  
			}
            System.out.println(cr2.toString());
		}	
       if ( k == 1 ) {
			ChatSystem cs = new ChatSystem();
			Method mts[] = cs.getClass().getMethods();
			while ( true ) {
				String cmd = jin.next();
				if ( cmd.equals("stop") ) break;
				if ( cmd.equals("print") ) {
					System.out.println(cs.getRoom(jin.next())+"\n");continue;
				}
				for ( Method m : mts ) {
					if ( m.getName().equals(cmd) ) {
						String params[] = new String[m.getParameterTypes().length];
						for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
						m.invoke(cs,(Object[]) params);
					}
				}				
			}
		}
	}

}
