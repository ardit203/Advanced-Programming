# Lab Exercise 8 - Advanced Programming

## Task 3

<div class="clearfix" id="yui_3_18_1_1_1766434404199_89">
<p>It is required to implement a system for managing multiple game rooms in which players can join, attack, and leave the game.</p>
<p>The system manages:</p>
<ul>
<li>Players</li>
<li>Player actions</li>
<li>Game rooms</li>
<li>A central server (Game Server)</li>
</ul>
<p>You are given a <code>Player</code> class that contains the following data:</p>
<ul>
<li><code>id: String</code> – a unique identifier of the player</li>
<li><code>score: int</code> – the current score of the player</li>
</ul>
<p>Implement the synchronized method <code>addScore(int delta)</code> which increases the player’s score by the given value.<br>The method must be synchronized in order to ensure thread-safe access to the score.</p>
<p>An enum <code>ActionType</code> is also defined with the following values:</p>
<ul>
<li><code>JOIN_GAME</code> – action for joining a game</li>
<li><code>LEAVE_GAME</code> – action for leaving a game</li>
<li><code>ATTACK</code> – action for attacking</li>
</ul>
<p>The <code>PlayerAction</code> class contains the following data:</p>
<ul>
<li><code>playerId: String</code> – the identifier of the player</li>
<li><code>action: ActionType</code> – the type of action</li>
</ul>
<p>A method <code>getProcessingTime()</code> is implemented, which returns the processing time of the action in milliseconds:</p>
<ul>
<li><code>JOIN_GAME</code>: 20 ms</li>
<li><code>LEAVE_GAME</code>: 30 ms</li>
<li><code>ATTACK</code>: 5 ms</li>
<li>Default: 0 ms</li>
</ul>
<p>The <code>RoomAction</code> class is a helper class that contains:</p>
<ul>
<li><code>roomId: String</code> – the identifier of the room</li>
<li><code>action: PlayerAction</code> – the action to be executed</li>
</ul>
<p>The <code>GameRoom</code> class represents a single game room and contains:</p>
<ul>
<li><code>roomId: String</code> – unique identifier of the room</li>
<li><code>players: Map&lt;String, Player&gt;</code> – a map of players in the room (thread-safe)</li>
<li><code>actionQueue: BlockingQueue&lt;PlayerAction&gt;</code> – a queue for pending actions</li>
<li><code>executor: ExecutorService</code> – a single-thread executor for processing</li>
<li><code>running: boolean</code> – flag indicating whether the room is active</li>
</ul>
<p>Implement the method <code>startProcessor()</code> which starts a separate thread for processing actions.<br>This thread:</p>
<ul>
<li>Continuously reads actions from the queue</li>
<li>Processes them while the room is active or while there are actions in the queue</li>
<li>Uses <code>poll()</code> with a timeout of 100 ms to avoid infinite blocking</li>
</ul>
<p>The method <code>submitAction(PlayerAction action)</code>:</p>
<ul>
<li>Prints the received action</li>
<li>Adds the action to the processing queue</li>
</ul>
<p>Implement the method <code>processAction(PlayerAction action)</code> which:</p>
<ul>
<li>First waits an appropriate amount of time based on the action type (simulating processing time)</li>
<li>Then executes the action:
<ul>
<li><code>JOIN_GAME</code>: Adds the player to the map (if not already present) and prints a message</li>
<li><code>LEAVE_GAME</code>: Removes the player from the map and prints a message (or prints that the player is not in the room)</li>
<li><code>ATTACK</code>: Increases the player’s score by 10 points (or prints that the player is not in the room)</li>
</ul>
</li>
</ul>
<p>Implement the method <code>shutdown()</code> which:</p>
<ul>
<li>Sets the <code>running</code> flag to <code>false</code></li>
<li>Shuts down the executor (with a grace period of 5 seconds)</li>
<li>Prints the final scores of all players in the room</li>
</ul>
<p>The <code>GameServer</code> class is the central server that manages multiple rooms and contains:</p>
<ul>
<li><code>inputQueue: BlockingQueue&lt;RoomAction&gt;</code> – main queue for all actions</li>
<li><code>rooms: ConcurrentHashMap&lt;String, GameRoom&gt;</code> – map of active rooms</li>
<li><code>dispatcher: ExecutorService</code> – single-thread executor for dispatching</li>
<li><code>running: boolean</code> – flag indicating whether the server is active</li>
</ul>
<p>Implement the method <code>startDispatcher()</code> which starts a dispatcher thread that:</p>
<ul>
<li>Continuously reads actions from the main queue</li>
<li>Finds the appropriate room (or creates it if it does not exist)</li>
<li>Forwards the action to the room</li>
</ul>
<p>The method <code>submit(String roomId, PlayerAction action)</code> adds the action to the main queue wrapped in a <code>RoomAction</code>.</p>
<p>Implement the method <code>shutdown()</code> which:</p>
<ul>
<li>Shuts down the dispatcher</li>
<li>Shuts down all active game rooms</li>
</ul>
<p>Important notes:</p>
<ul>
<li>All operations on shared data must be thread-safe</li>
<li>Use <code>ConcurrentHashMap</code> for maps accessed by multiple threads</li>
<li>Use <code>BlockingQueue</code> for communication between threads</li>
<li>Each room has its own dedicated thread for processing actions (FIFO order)</li>
<li>The dispatcher thread distributes actions to the appropriate rooms</li>
<li>During shutdown, ensure a graceful shutdown by waiting for all active actions</li>
</ul>
<p>Due to the non-deterministic nature of the tests, a failed execution of a single test does not indicate an incorrect implementation of the solution.</p></div>

### Starter code
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.*;


enum ActionType {
    JOIN_GAME,
    LEAVE_GAME,
    ATTACK
}


class Player {
    private final String id;
    private int score;

    public Player(String id) {
        this.id = id;
        this.score = 0;
    }

    // TODO: Implement addScore function

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", score=" + score +
                '}';
    }
}


class PlayerAction {
    private final String playerId;
    private final ActionType action;

    public PlayerAction(String playerId, ActionType action) {
        this.playerId = playerId;
        this.action = action;
    }

    public String getPlayerId() {
        return playerId;
    }

    public ActionType getActionType() {
        return action;
    }

    public int getProcessingTime() {
        switch (action) {
            case JOIN_GAME:
                return 20;
            case LEAVE_GAME:
                return 30;
            case ATTACK:
                return 5;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "playerId='" + playerId + '\'' +
                ", action=" + action +
                '}';
    }
}

class RoomAction {
    final String roomId;
    final PlayerAction action;

    RoomAction(String roomId, PlayerAction action) {
        this.roomId = roomId;
        this.action = action;
    }
}


class GameRoom {

    public final String roomId;
    public final Map<String, Player> players = new ConcurrentHashMap<>();

    private final BlockingQueue<PlayerAction> actionQueue =
            new LinkedBlockingQueue<>();

    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();

    public volatile boolean running = true;

    public GameRoom(String roomId) {
        this.roomId = roomId;
        startProcessor();
    }

    // TODO: Implement startProcessor
    private void startProcessor() {

    }

    public void submitAction(PlayerAction action) {
        System.out.println("[" + roomId + "] RECEIVED: " + action);
        actionQueue.offer(action);
    }

    private void processAction(PlayerAction action) {
        try {
            Thread.sleep(action.getProcessingTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        switch (action.getActionType()) {
            case JOIN_GAME:
                players.putIfAbsent(
                        action.getPlayerId(),
                        new Player(action.getPlayerId())
                );
                System.out.println("[" + roomId + "] JOIN: "
                        + action.getPlayerId());
                break;

            case LEAVE_GAME:
                if (players.remove(action.getPlayerId()) != null) {
                    System.out.println("[" + roomId + "] LEAVE: "
                            + action.getPlayerId());
                } else {
                    System.out.println("[" + roomId
                            + "] LEAVE IGNORED (not in room): "
                            + action.getPlayerId());
                }
                break;

            case ATTACK:
                Player p = players.get(action.getPlayerId());
                if (p == null) {
                    System.out.println("[" + roomId
                            + "] ATTACK IGNORED (not in room): "
                            + action.getPlayerId());
                } else {
                    p.addScore(10);
                    System.out.println("[" + roomId + "] ATTACK: " + p);
                }
                break;
        }
    }

    public void shutdown() {
        // TODO: Add missing logic

        System.out.println("[" + roomId + "] FINAL PLAYERS:");
        players.values().forEach(p ->
                System.out.println("  " + p));
    }
}


class GameServer {

    private final BlockingQueue<RoomAction> inputQueue =
            new LinkedBlockingQueue<>();

    private final ConcurrentHashMap<String, GameRoom> rooms =
            new ConcurrentHashMap<>();

    private final ExecutorService dispatcher =
            Executors.newSingleThreadExecutor();

    private volatile boolean running = true;

    public GameServer() {
        startDispatcher();
    }

    // TODO: Implement startDispatcher()
    private void startDispatcher() {

    }

    public void submit(String roomId, PlayerAction action) {
        inputQueue.offer(new RoomAction(roomId, action));
    }

    // TODO: Implement GameServer shutdown() method
    public void shutdown() {

    }
}


public class Main {

    public static void main(String[] args) throws IOException {

        GameServer server = new GameServer();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = reader.readLine()) != null && !line.isBlank()) {

            final String input = line.trim();

            try {
                String[] parts = input.split(",");
                if (parts.length != 3) {
                    System.err.println("Invalid input: " + input);
                    return;
                }

                String roomId = parts[0].trim();
                String playerId = parts[1].trim();
                ActionType actionType =
                        ActionType.valueOf(parts[2].trim());

                PlayerAction action =
                        new PlayerAction(playerId, actionType);

                server.submit(roomId, action);

            } catch (Exception e) {
                System.err.println(
                        "Failed to process line: " + input
                );
                e.printStackTrace();
            }
        }

        reader.close();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        server.shutdown();

        System.out.println("Game server stopped.");
    }
}
```

### Solution
```java
// package Lab.Lab8.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.*;


enum ActionType {
    JOIN_GAME,
    LEAVE_GAME,
    ATTACK
}


class Player {
    private final String id;
    private int score;

    public Player(String id) {
        this.id = id;
        this.score = 0;
    }

    // TODO: Implement addScore function
    public synchronized void addScore(int score){
        this.score += score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", score=" + score +
                '}';
    }
}


class PlayerAction {
    private final String playerId;
    private final ActionType action;

    public PlayerAction(String playerId, ActionType action) {
        this.playerId = playerId;
        this.action = action;
    }

    public String getPlayerId() {
        return playerId;
    }

    public ActionType getActionType() {
        return action;
    }

    public int getProcessingTime() {
        switch (action) {
            case JOIN_GAME:
                return 20;
            case LEAVE_GAME:
                return 30;
            case ATTACK:
                return 5;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "PlayerAction{" +
                "playerId='" + playerId + '\'' +
                ", action=" + action +
                '}';
    }
}

class RoomAction {
    final String roomId;
    final PlayerAction action;

    RoomAction(String roomId, PlayerAction action) {
        this.roomId = roomId;
        this.action = action;
    }
}


class GameRoom {

    public final String roomId;
    public final Map<String, Player> players = new ConcurrentHashMap<>();

    private final BlockingQueue<PlayerAction> actionQueue =
            new LinkedBlockingQueue<>();

    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();

    public volatile boolean running = true;

    public GameRoom(String roomId) {
        this.roomId = roomId;
        startProcessor();
    }

    // TODO: Implement startProcessor
    private void startProcessor() {
        Thread processingThread = new Thread(() -> {
            try {
                while (running){

                    PlayerAction playerAction = actionQueue.poll(200, TimeUnit.MILLISECONDS);
                    processAction(playerAction);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        this.executor.submit(processingThread);
    }

    public void submitAction(PlayerAction action) {
        System.out.println("[" + roomId + "] RECEIVED: " + action);
        actionQueue.offer(action);
    }

    private void processAction(PlayerAction action) {
        try {
            Thread.sleep(action.getProcessingTime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        switch (action.getActionType()) {
            case JOIN_GAME:
                players.putIfAbsent(
                        action.getPlayerId(),
                        new Player(action.getPlayerId())
                );
                System.out.println("[" + roomId + "] JOIN: "
                        + action.getPlayerId());
                break;

            case LEAVE_GAME:
                if (players.remove(action.getPlayerId()) != null) {
                    System.out.println("[" + roomId + "] LEAVE: "
                            + action.getPlayerId());
                } else {
                    System.out.println("[" + roomId
                            + "] LEAVE IGNORED (not in room): "
                            + action.getPlayerId());
                }
                break;

            case ATTACK:
                Player p = players.get(action.getPlayerId());
                if (p == null) {
                    System.out.println("[" + roomId
                            + "] ATTACK IGNORED (not in room): "
                            + action.getPlayerId());
                } else {
                    p.addScore(10);
                    System.out.println("[" + roomId + "] ATTACK: " + p);
                }
                break;
        }
    }

    public void shutdown() throws InterruptedException {
        // TODO: Add missing logic
        running = false;

        executor.shutdown();
        boolean isTerminated = executor.awaitTermination(5, TimeUnit.SECONDS);
        if(!isTerminated){
            executor.shutdown();
        }

        System.out.println("[" + roomId + "] FINAL PLAYERS:");
        players.values().forEach(p ->
                System.out.println("  " + p));
    }
}


class GameServer {

    private final BlockingQueue<RoomAction> inputQueue =
            new LinkedBlockingQueue<>();

    private final ConcurrentHashMap<String, GameRoom> rooms =
            new ConcurrentHashMap<>();

    private final ExecutorService dispatcher =
            Executors.newSingleThreadExecutor();

    private volatile boolean running = true;

    public GameServer() throws InterruptedException {
        startDispatcher();
    }

    // TODO: Implement startDispatcher()
    private void startDispatcher() throws InterruptedException {
        Thread dispatcherThread = new Thread(() -> {
            try {
                while (running){

                    RoomAction roomAction = inputQueue.poll(200, TimeUnit.MILLISECONDS);

                    GameRoom room = rooms.compute(roomAction.roomId, (key, existing) -> {
                        if(existing != null){
                            return existing;
                        }
                        return new GameRoom(key);
                    });
                    room.submitAction(roomAction.action);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        this.dispatcher.submit(dispatcherThread);
    }

    public void submit(String roomId, PlayerAction action) {
        inputQueue.offer(new RoomAction(roomId, action));
    }

    // TODO: Implement GameServer shutdown() method
    public void shutdown() throws InterruptedException {
        running = false;

        for (GameRoom room : rooms.values()) {
            room.shutdown();
        }
        dispatcher.shutdown();
    }
}


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        GameServer server = new GameServer();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = reader.readLine()) != null && !line.isBlank()) {

            final String input = line.trim();

            try {
                String[] parts = input.split(",");
                if (parts.length != 3) {
                    System.err.println("Invalid input: " + input);
                    return;
                }

                String roomId = parts[0].trim();
                String playerId = parts[1].trim();
                ActionType actionType =
                        ActionType.valueOf(parts[2].trim());

                PlayerAction action =
                        new PlayerAction(playerId, actionType);

                server.submit(roomId, action);

            } catch (Exception e) {
                System.err.println(
                        "Failed to process line: " + input
                );
                e.printStackTrace();
            }
        }

        reader.close();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        server.shutdown();

        System.out.println("Game server stopped.");
    }
}
```