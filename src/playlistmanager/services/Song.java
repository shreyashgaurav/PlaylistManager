package playlistmanager.services;

public class Song {
    String title;
    String artist;
    double duration;

    Song next;
    public Song(String title, String artist, double duration){
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.next = null;
    }
    @Override
    public String toString(){
        return String.format("Title: %s, Artist: %s, Duration: %.2f mins", title, artist, duration);
    }


}
