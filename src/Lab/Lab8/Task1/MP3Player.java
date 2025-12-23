package Lab.Lab8.Task1;

import java.util.List;

public class MP3Player {
    private List<Song> songs;
    private int currentSong;
    private State state;


    public MP3Player(List<Song> songs) {
        this.songs = songs;
        this.state = new StopedState(this);
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

    public List<Song> getSongs() {
        return songs;
    }

    public int getCurrentSong() {
        return currentSong;
    }

    public State getState() {
        return state;
    }

    public void setCurrentSong(int currentSong) {
        this.currentSong = currentSong;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "MP3Player{" +
                "currentSong = " + currentSong +
                ", songList = " + songs +
                '}';
    }
}
