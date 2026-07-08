<div class="clearfix" id="yui_3_18_1_1_1783428067116_88">
<p>Write a class for an <strong>MP3Player</strong> in which a list of songs (<code>List&lt;Song&gt;</code>) is stored, as well as the song that is currently being listened to (i.e. the one that is next in line to be played).<br>The MP3Player has four buttons: <strong>Play</strong>, <strong>Stop</strong>, <strong>FWD</strong>, and <strong>REW</strong>.</p>
<ul>
<li>
<p>If the <strong>Play</strong> button is pressed, the current song is played (the screen prints<br><code>"Song i is playing"</code>, where <code>i</code> is the index of the current song, starting from 0).</p>
</li>
<li>
<p>If the <strong>Stop</strong> button is pressed:</p>
<ul>
<li>the currently playing song is paused (the screen prints<br><code>"Song i is paused"</code>, where <code>i</code> is the current song that was playing).</li>
<li>the list is completely reset from the beginning if the current song was already paused<br>(the screen prints <code>"Songs are stopped"</code>).</li>
</ul>
</li>
<li>
<p>If the <strong>FWD</strong> button is pressed, the current song is paused and the next song from the list becomes the current one<br>(Circular repetition of songs should be taken into account).</p>
</li>
<li>
<p>If the <strong>REW</strong> button is pressed, the current song is paused and the previous song from the list becomes the current one<br>(Circular repetition of songs should be taken into account).</p>
</li>
</ul>
<p>For each song (<strong>Song</strong>), the song title (<strong>String</strong>) and the performer/artist (<strong>String</strong>) are stored.</p></div>

### Starter code
```java
import java.util.ArrayList;
import java.util.List;

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);
        
        
        System.out.println(player.toString());
        System.out.println("First test");
        
        
        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();
        
        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();
        
        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();
        
        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();
        
        
        System.out.println(player.toString());
        System.out.println("Second test");
        
        
        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();
        
        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();
        
        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();
        
        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();
        
        
        System.out.println(player.toString());
        System.out.println("Third test");
        
        
        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();
        
        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();
        
        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();
        
        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();
        
        
        System.out.println(player.toString());  
    }
}

//Vasiot kod ovde
```

### Solution
```java
// package Lab.Lab8.Task1;

import java.util.ArrayList;
import java.util.List;

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        //Song{title=first-title, artist=first-artist}
        return String.format("Song{title=%s, artist=%s}", title, artist);
    }
}

class MP3Player {
    private List<Song> songs;
    private int currentSong = -1;
    private PlayerState state;

    public MP3Player(List<Song> songs) {
        this.songs = songs;
        this.state = new StoppedState(this);
    }

    public void pressPlay() {
        state.play();
    }

    public void pressStop() {
        state.stop();
    }

    public void pressFWD() {
        state.fwd();
    }

    public void pressREW() {
        state.rew();
    }

    public void printCurrentSong() {
        System.out.println(songs.get(currentSong));
    }

    public void setCurrentSong(int currentSong) {
        this.currentSong = currentSong;
    }

    public int getCurrentSong() {
        return currentSong;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public List<Song> getSongs() {
        return songs;
    }


    @Override
    public String toString() {
        return String.format("MP3Player{currentSong = %d, songList = %s}", currentSong, songs);
    }
}

interface IState {
    void play();

    void stop();

    void fwd();

    void rew();
}

abstract class PlayerState implements IState {
    protected MP3Player mp3Player;

    public PlayerState(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    public void handleFwd() {
        int currentSong = mp3Player.getCurrentSong() + 1;
        if (currentSong >= mp3Player.getSongs().size()) {
            currentSong = 0;
        }
        mp3Player.setCurrentSong(currentSong);
    }

    public void handleRew() {
        int currentSong = mp3Player.getCurrentSong() - 1;
        if (currentSong < 0) {
            if (mp3Player.getSongs().isEmpty()) {
                currentSong = 0;
            } else {
                currentSong = mp3Player.getSongs().size() - 1;
            }
        }
        mp3Player.setCurrentSong(currentSong);
    }
}

class PlayState extends PlayerState {

    public PlayState(MP3Player mp3Player) {
        super(mp3Player);
    }

    @Override
    public void play() {
        System.out.println("Song is already playing");
    }

    @Override
    public void stop() {
        System.out.printf("Song %d is paused\n", mp3Player.getCurrentSong());
        mp3Player.setState(new StoppedState(mp3Player));
    }

    @Override
    public void fwd() {
        handleFwd();
        System.out.println("Forward...");
        mp3Player.setState(new PausedFwdRewState(mp3Player));
    }

    @Override
    public void rew() {
        handleRew();
        System.out.println("Reward...");
        mp3Player.setState(new PausedFwdRewState(mp3Player));
    }
}

class StoppedState extends PlayerState {

    public StoppedState(MP3Player mp3Player) {
        super(mp3Player);
        mp3Player.setCurrentSong(0);
    }

    @Override
    public void play() {
        System.out.printf("Song %d is playing\n", mp3Player.getCurrentSong());
        mp3Player.setState(new PlayState(mp3Player));
    }

    @Override
    public void stop() {
        System.out.println("Songs are already stopped");
    }

    @Override
    public void fwd() {
        handleFwd();
        System.out.println("Forward...");
        mp3Player.setState(new PausedFwdRewState(mp3Player));
    }

    @Override
    public void rew() {
        handleRew();
        System.out.println("Reward...");
        mp3Player.setState(new PausedFwdRewState(mp3Player));
    }
}

class PausedFwdRewState extends PlayerState {

    public PausedFwdRewState(MP3Player mp3Player) {
        super(mp3Player);
    }

    @Override
    public void play() {
        System.out.printf("Song %d is playing\n", mp3Player.getCurrentSong());
        mp3Player.setState(new PlayState(mp3Player));
    }

    @Override
    public void stop() {
        System.out.println("Songs are stopped");
        mp3Player.setState(new StoppedState(mp3Player));
    }

    @Override
    public void fwd() {
        handleFwd();
        System.out.println("Forward...");
    }

    @Override
    public void rew() {
        handleRew();
        System.out.println("Reward...");
    }
}

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}
```