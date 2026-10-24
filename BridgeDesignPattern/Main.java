package BridgeDesignPattern;



interface Device {
    void playAudio(String fileName);
    void playVideo(String fileName);
}

class WindowsDevice implements Device {
    public void playAudio(String fileName) {
        System.out.println("Windows playing audio: " + fileName);
    }

    public void playVideo(String fileName) {
        System.out.println("Windows playing video: " + fileName);
    }
}

class MacDevice implements Device {
    public void playAudio(String fileName) {
        System.out.println("Mac playing audio: " + fileName);
    }

    public void playVideo(String fileName) {
        System.out.println("Mac playing video: " + fileName);
    }
}

abstract class MediaFile {
    protected Device device;

    public MediaFile(Device device) {
        this.device = device;
    }

    abstract void play();
}

class AudioFile extends MediaFile {
    private String fileName;

    public AudioFile(Device device, String fileName) {
        super(device);
        this.fileName = fileName;
    }

    @Override
    public void play() {
        device.playAudio(fileName);
    }
}

class VideoFile extends MediaFile {
    private String fileName;

    public VideoFile(Device device, String fileName) {
        super(device);
        this.fileName = fileName;
    }

    @Override
    public void play() {
        device.playVideo(fileName);
    }
}

public class Main {
    public static void main(String[] args) {
        Device windows = new WindowsDevice();
        Device mac = new MacDevice();

        MediaFile audio = new AudioFile(windows, "song.mp3");
        MediaFile video = new VideoFile(mac, "movie.mp4");

        audio.play();
        video.play();

        MediaFile macAudio = new AudioFile(mac, "music.mp3");
        MediaFile windowsVideo =
                new VideoFile(windows, "film.mp4");

        macAudio.play();
        windowsVideo.play();
    }
}
