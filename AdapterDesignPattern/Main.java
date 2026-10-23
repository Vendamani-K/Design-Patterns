package AdapterDesignPattern;


    
 // Target interface: the existing player understands this interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee: MP4 player has its own method
class MP4Player {
    public void playMP4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}

// Adaptee: VLC player has its own method
class VLCPlayer {
    public void playVLC(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}

// Adapter: connects MP4 and VLC players to MediaPlayer
class MediaAdapter implements MediaPlayer {

    private MP4Player mp4Player;
    private VLCPlayer vlcPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player = new MP4Player();
        } else if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer = new VLCPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player.playMP4(fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVLC(fileName);
        }
    }
}

// Existing audio player
class AudioPlayer implements MediaPlayer {

    private MediaAdapter adapter;

    @Override
    public void play(String audioType, String fileName) {

        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4")
                || audioType.equalsIgnoreCase("vlc")) {

            adapter = new MediaAdapter(audioType);
            adapter.play(audioType, fileName);

        } else {
            System.out.println("Invalid media format: " + audioType);
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        AudioPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        player.play("vlc", "video.vlc");
        player.play("avi", "video.avi");
    }
}

