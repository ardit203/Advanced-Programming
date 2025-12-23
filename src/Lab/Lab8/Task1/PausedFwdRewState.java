package Lab.Lab8.Task1;

public class PausedFwdRewState extends State{
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
        mp3Player.setState(new StopedState(mp3Player));
    }

    @Override
    public void fwd() {
        handleFWD();
        System.out.println("Forward...");
    }

    @Override
    public void rew() {
        handleREW();
        System.out.println("Reward...");
    }
}
