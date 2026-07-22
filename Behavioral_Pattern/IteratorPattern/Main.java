import java.util.*;

class Video {
    private String title;
    
    public Video(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}

class YouTubePlaylist {
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
    }

    public List<Video> getVideos() {
        return videos;
    }
}

interface PlaylistIterator {
    boolean hasNext();
    Video next();
}

//concrete iterator - traversal algo-1
class YouTubePlaylistIterator implements PlaylistIterator {
    private List<Video> videos;
    private int position;

    public YouTubePlaylistIterator(List<Video> videos){
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext(){
        // 0 < 3
        return position < videos.size();
    }

    @Override
    public Video next() {
        return hasNext() ? videos.get(position++) : null;
    }
}

// // Iterable interface
// interface Playlist {
//     PlaylistIerator createIterator();
//     PlaylistIterator createCopyrightIterator();
// }

// class YouTubePlaylist implements Playlist {
//     private List<Video> videos = new ArrayList<>();

//     public void addVideo(Video video) {
//         videos.add(video);
//     }

//     @Override
//     public PlaylistIterator createIterator(){
//         return new YouTubePlaylistIterator(videos);  
//     }

//     @Override
//     public PlaylistIterator createCopyrightIterator(){
//         return new YouTubePlaylistCRightIterator(videos);
//     }
// }

public class Main{
    public static void main(String[] args){
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        // for(Video v : playlist.getVideos()){
        //     System.out.println(v.getTitle());
        // }

        PlaylistIterator iterator = new YouTubePlaylistIterator(playlist.getVideos());
        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }
    }
}