# Lab Exercise 5 - Advanced Programming
## Task 1

<div class="clearfix" id="yui_3_18_1_1_1764777340765_89">
<p>A class for administration of a chat system needs to be developed. The system consists of multiple chat rooms, objects of the class <strong>ChatRoom</strong>. In <strong>ChatRoom</strong> we store the name of the room and the names of the users who are currently in that room (for the users use an appropriate <strong>Set</strong>).</p>
<ul>
<li><code>ChatRoom(String name)</code> – creates a new empty chat room (empty means without users).</li>
<li><code>addUser(String username)</code> – adds the user with that name to the room.</li>
<li><code>removeUser(String username)</code> – removes the user with that name from the room if such exists, otherwise does nothing.</li>
<li><code>toString():String</code> – returns a string that contains the room name and all users that are in the room, each separated by a new line. Users are sorted alphabetically. If the room is empty, the room name is returned on one line, and on the second line the string "EMPTY" (quotes only for clarification).</li>
<li><code>hasUser(String username):boolean</code> – returns true if a user with that name exists in the room.</li>
<li><code>numUsers():int</code> – returns the number of users in the room.</li>
</ul>
<p>The main class <strong>ChatSystem</strong> contains all rooms and all users. Users may be members of one, more, or none of the rooms. For management of the rooms you should provide the following three methods:</p>
<ul>
<li><code>addRoom(String roomName)</code> – adds a new empty room to the list of rooms.</li>
<li><code>removeRoom(String roomName)</code> – removes the room from the list.</li>
<li><code>getRoom(String roomName):ChatRoom</code> – returns the object that represents the room with name <code>roomName</code>. Throw <code>NoSuchRoomException(roomName)</code> if a room with that name does not exist.</li>
</ul>
<p><strong>Note</strong>: Store the rooms in a <strong>TreeMap</strong> so they are always sorted by name.</p>
<p>Additionally, in the class ChatSystem the following methods exist for working with users:</p>
<ul>
<li><code>ChatSystem()</code> – default constructor</li>
<li><code>register(String userName)</code> – registers the user in the system. Adds the user to the room with the fewest users. If there are multiple such rooms, then adds them to the first room by lexicographic order.</li>
<li><code>registerAndJoin(String userName, String roomName)</code> – registers the user in the system. Additionally adds them to the room with name <code>roomName</code>.</li>
<li><code>joinRoom(String userName, String roomName)</code> – adds the user to the room with the corresponding name if it exists, otherwise throws an exception of type <code>NoSuchRoomExcеption(roomName)</code>. If there is no registered user with that name, throw <code>NoSuchUserException(userName)</code>.</li>
<li><code>leaveRoom(String username, String roomName)</code> – removes the user from the room with the corresponding name if it exists; otherwise throws an exception of type <code>NoSuchRoomExcеption(roomName)</code>. If there is no registered user with that name, throw <code>NoSuchUserException(userName)</code>.</li>
<li><code>followFriend(String username, String friend_username)</code> – the user with the name username joins all rooms in which the user with the name <code>friendUsername</code> is a member. If a registered user with that name does not exist, throw <code>NoSuchUserException(userName)</code>.</li>
</ul></div>

### Starter code:
```java
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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
			System.out.println("");
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
						m.invoke(cs,params);
					}
				}				
			}
		}
	}

}
```