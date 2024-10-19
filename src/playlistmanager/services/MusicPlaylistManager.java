package playlistmanager.services;

import java.util.Scanner;
//
public class MusicPlaylistManager {
    public static void main(String[] args){
        Playlist playlist = new Playlist();
        Scanner scanner = new Scanner(System.in);
        playlist.loadFromFile("src/playlistmanager/services/playlist.txt");
        while(true){
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. Display Playlist");
            System.out.println("4. Reorder");
            System.out.println("5. Search by Title");
            System.out.println("6. Search by Artist");
            System.out.println("7. Shuffle");
            System.out.println("8. Sort by title");
            System.out.println("9. Sort by artist");
            System.out.println("10. Sort by duration");
            System.out.println("11. Exit");
            System.out.println("Choose an option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:     // Add song
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter Artist: ");
                    String artist = scanner.nextLine();
                    System.out.println("Enter Duration: ");
                    double duration = scanner.nextDouble();

                    playlist.addSong(title, artist,duration);
                    playlist.saveToFile("src/playlistmanager/services/playlist.txt");
                    break;
                case 2:     // Remove Song
                    System.out.println("Enter song title to remove: ");
                    String removeTitle = scanner.nextLine();
                    playlist.removeSong(removeTitle);
                    break;
                case 3:     // Display Playlist
                    playlist.saveToFile("src/playlistmanager/services/playlist.txt");
                    playlist.displayPlaylist();
                    break;
                case 4:
                    // Reorder
                    System.out.println("Enter the position of the song to be reordered.");
                    int s1 = scanner.nextInt();
                    int s2 = scanner.nextInt();
                    playlist.reorderPlaylist(s1, s2);
                    break;
                case 5:
                    //Search By title
                    System.out.println("Enter song Title to search : ");
                    String searchTitle = scanner.nextLine().trim();
                    playlist.searchSongByTitle(searchTitle);
                    break;
                case 6:
                    //Search by artist
                    System.out.println("Enter the Artist Name: ");
                    String searchArtist = scanner.nextLine().trim();
                    playlist.searchSongByArtist(searchArtist);
                    break;
                case 7:
                    // Shuffle
                    playlist.shufflePlaylist();
                    break;
                case 8:
                    // Sort By Title
                    playlist.sortByTitle();
                    break;
                case 9:
                    // Sort by artist
                    playlist.sortByArtist();
                    break;
                case 10:
                    // Sort by playtime
                    playlist.sortByDuration();
                    break;
                case 11:
                    System.out.println("Exiting......");
                    return;
                default:
                    System.out.println("Invalid option. Try Again.");
            }
        }
    }
}
