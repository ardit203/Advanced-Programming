package Lab.Lab8.Task1;

public abstract class State implements IState {
    protected MP3Player mp3Player;

    public State(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    public void handleFWD() {
        int index = mp3Player.getCurrentSong() + 1;
        if (mp3Player.getSongs().size() - 1 == mp3Player.getCurrentSong()) {
            index = 0;
        }
        mp3Player.setCurrentSong(index);
    }

    public void handleREW() {
        int index = mp3Player.getCurrentSong() - 1;
        if (mp3Player.getCurrentSong() == 0) {
            index = mp3Player.getSongs().size() - 1;
        }

        mp3Player.setCurrentSong(index);
    }
}
