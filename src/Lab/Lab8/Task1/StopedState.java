package Lab.Lab8.Task1;

public class StopedState extends State{

    public StopedState(MP3Player mp3Player) {
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
        handleFWD();
        System.out.println("Forward...");
        mp3Player.setState(new PausedFwdRewState(mp3Player));
    }

    @Override
    public void rew() {
        handleREW();
        System.out.println("Reward...");
        mp3Player.setState(new PausedFwdRewState(mp3Player));
    }
}