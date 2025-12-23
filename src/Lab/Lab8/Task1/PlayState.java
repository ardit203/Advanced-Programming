package Lab.Lab8.Task1;

public class PlayState extends State {

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
        mp3Player.setState(new PausedFwdRewState(mp3Player));
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
