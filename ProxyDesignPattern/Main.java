package ProxyDesignPattern;


interface FileDownloader {
    void download(String fileName, String user);
}

// Real Subject
class RealFileDownloader implements FileDownloader {

    @Override
    public void download(String fileName, String user) {
        System.out.println("Downloading file: " + fileName);
        System.out.println("File downloaded successfully!");
    }
}

// Proxy
class FileDownloadProxy implements FileDownloader {

    private RealFileDownloader realDownloader;

    public FileDownloadProxy() {
        realDownloader = new RealFileDownloader();
    }

    @Override
    public void download(String fileName, String user) {

        System.out.println("Logging: " + user +
                           " requested " + fileName);

        if (user.equals("admin")) {
            System.out.println("Access granted!");

            realDownloader.download(fileName, user);

        } else {
            System.out.println("Access denied! Only admin can download files.");
        }
    }
}

// Client
public class Main {

    public static void main(String[] args) {

        FileDownloader downloader = new FileDownloadProxy();

        downloader.download("large-file.zip", "admin");

        System.out.println();

        downloader.download("large-file.zip", "guest");
    }
}
