package playlistmanager.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Playlist {
    private Song head;
    public void addSong(String title, String artist, double duration){
        Song newSong = new Song(title, artist, duration);
        if(head == null){
            head = newSong;
        }
        else{
            Song current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newSong;
        }
    }


    public void removeSong(String title){
        if(head == null){
            System.out.println("The Playlist is Empty.");
            return;
        }
        if(head.title.equals(title)){
            head = head.next;
            return;
        }

        Song current = head;
        while(current.next != null && !current.next.title.equals(title)){
            current = current.next;
        }
        if(current.next == null){
            System.out.println("Song Not Found.");
        }
        else{
            current.next = current.next.next;
        }
    }

    public void displayPlaylist(){
        if(head == null){
            System.out.println("The playlist is empty.");
            return;
        }

        Song current = head;
        int position = 1;
        while(current != null){
            System.out.println(position + " . " + current);
            current = current.next;
            position++;
        }
    }
//    To DO
    public void reorderPlaylist(int s1, int s2){
        if(head == null){
            System.out.println("The Playlist is empty.");
            return;
        }
        if (s1 == s2) {
            System.out.println("The positions are the same, no need to reorder.");
            return;
        }
        Song prevS1 = null;
        Song currS1 = head;
        int position = 1;

        while (currS1 != null && position < s1) {
            prevS1 = currS1;
            currS1 = currS1.next;
            position++;
        }
//        if (currS1 == null) {
//            System.out.println("Position " + s1 + " is out of bounds.");
//            return;
//        }



    }

    public void searchSongByTitle(String title) {
        if (head == null) {
            System.out.println("The playlist is empty.");
            return;
        }
        Song current = head;
        int position = 1;

        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                System.out.println("Song found: " + current.title + " by " + current.artist + " at position " + position);
                return;
            }
            current = current.next;
            position++;
        }

        System.out.println("Song with title '" + title + "' not found in the playlist.");
    }


    public void searchSongByArtist(String artist) {
        if (head == null) {
            System.out.println("The playlist is empty.");
            return;
        }
        Song current = head;
        int position = 1;
        boolean found = false;
        while (current != null) {
//            System.out.println(current.artist);
            if (current.artist.trim().equalsIgnoreCase(artist)) {
                System.out.println("Song found: " + current.title + " by " + current.artist + " at position " + position);
                found = true;
            }
            current = current.next;
            position++;
        }
        if (!found) {
            System.out.println("No songs by artist '" + artist + "' found in the playlist.");
        }
    }


    public void shufflePlaylist() {
        if (head == null) {
            System.out.println("The playlist is empty, cannot shuffle.");
            return;
        }

        // Storing Songs in an ArrayList - For Easier Shuffling
        ArrayList<Song> songList = new ArrayList<>();
        Song current = head;

        while (current != null) {
            songList.add(current);  // Add the current song to the list
            current = current.next;
        }

        // Shuffling
        Collections.shuffle(songList);

        // Step 3: Rebuild the linked list from the shuffled ArrayList
        head = songList.getFirst();  // After shuffling -> head = songList.get(0)
        current = head;  // Set current to the new head

        for (int i = 1; i < songList.size(); i++) {
            current.next = songList.get(i);
            current = current.next;
        }
        current.next = null;
        System.out.println("Playlist shuffled successfully.");
    }

    public void sortByTitle(){
        if(head == null){
            System.out.println("The playlist is empty, cannot sort.");
            return;
        }
        ArrayList<Song> songList = new ArrayList<>();
        Song current = head;

        while(current != null){
            songList.add(current);
            current = current.next;
        }
        songList.sort(new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.title.compareToIgnoreCase(s2.title);
            }
        });
        head = songList.getFirst();
        current = head;

        for(int i = 0; i < songList.size(); i++){
            current.next = songList.get(i);
            current = current.next;
        }
        current.next = null;

        System.out.println("Playlist is Sorted by Title Successfully.");
    }


    public void sortByArtist(){
        if(head == null){
            System.out.println("The playlist is empty, cannot sort.");
            return;
        }
        ArrayList<Song> songList = new ArrayList<>();
        Song current = head;

        while(current != null){
            songList.add(current);
            current = current.next;
        }
        songList.sort(new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.artist.compareToIgnoreCase(s2.artist);
            }
        });
        head = songList.getFirst();
        current = head;

        for(int i = 0; i < songList.size(); i++){
            current.next = songList.get(i);
            current = current.next;
        }
        current.next = null;

        System.out.println("Playlist is Sorted by artist Successfully.");
    }


    public void sortByDuration(){
        if(head == null){
            System.out.println("The playlist is empty, cannot sort.");
            return;
        }
        ArrayList<Song> songList = new ArrayList<>();
        Song current = head;

        while(current != null){
            songList.add(current);
            current = current.next;
        }
        songList.sort(new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return Double.compare(s1.duration, s2.duration);
            }
        });
        head = songList.getFirst();
        current = head;

        for(int i = 0; i < songList.size(); i++){
            current.next = songList.get(i);
            current = current.next;
        }
        current.next = null;

        System.out.println("Playlist is Sorted by Duration Successfully.");
    }


    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Assuming CSV format: title,artist,duration
                if (parts.length == 3) {
                    String title = parts[0];
                    String artist = parts[1];
                    double duration = Double.parseDouble(parts[2]);
                    addSong(title, artist, duration); // Reuse the addSong method
                }
            }
            System.out.println("Playlist loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while loading the playlist.");
            e.printStackTrace();
        }
    }

    public void saveToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Song current = head;
            while (current != null) {
                writer.write(current.title + "," + current.artist + "," + current.duration + "\n");
                current = current.next;
            }
            System.out.println("Playlist saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the playlist.");
            e.printStackTrace();
        }
    }

}
