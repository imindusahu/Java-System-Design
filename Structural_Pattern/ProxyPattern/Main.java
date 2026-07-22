import java.util.*;

class RealVideoDownloader {
    public String downloadVideo(String videoUrl){
        //caching
        //filtering
        //access
        System.out.println("Downloading video from URL: " + videoUrl);
        return "Video content from " + videoUrl;
    }
}

public class Main {
    public static void main(String[] args){
        RealVideoDownloader realVideoDownloader = new RealVideoDownloader();
        realVideoDownloader.downloadVideo("proxy-pattern");

        RealVideoDownloader realVideoDownloader2 = new RealVideoDownloader();
        realVideoDownloader2.downloadVideo("proxy-patter2");
    }
}