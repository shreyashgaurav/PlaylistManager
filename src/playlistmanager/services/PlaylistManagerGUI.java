package playlistmanager.services;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlaylistManagerGUI {
    private final Playlist playlist;  // Your backend logic object
    private final JTextField titleField;
    private final JTextField artistField;
    private final JTextField durationField;
    private final JTextArea displayArea;

    public PlaylistManagerGUI() {
        playlist = new Playlist();  // Initialize your Playlist

        // Setup the JFrame
        JFrame frame = new JFrame("Music Playlist Manager");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Title Label and Text Field
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 20, 50, 20);
        frame.add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(80, 20, 150, 20);
        frame.add(titleField);

        // Artist Label and Text Field
        JLabel artistLabel = new JLabel("Artist:");
        artistLabel.setBounds(20, 50, 50, 20);
        frame.add(artistLabel);

        artistField = new JTextField();
        artistField.setBounds(80, 50, 150, 20);
        frame.add(artistField);

        // Duration Label and Text Field
        JLabel durationLabel = new JLabel("Duration:");
        durationLabel.setBounds(20, 80, 60, 20);
        frame.add(durationLabel);

        durationField = new JTextField();
        durationField.setBounds(80, 80, 150, 20);
        frame.add(durationField);

        // Add Song Button
        JButton addSongButton = new JButton("Add Song");
        addSongButton.setBounds(250, 20, 100, 30);
        frame.add(addSongButton);
        addSongButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String artist = artistField.getText();
                double duration = Double.parseDouble(durationField.getText());
                playlist.addSong(title, artist, duration);
                updateDisplay();  // Refresh display
            }
        });

        // Display Area
        displayArea = new JTextArea();
        displayArea.setBounds(20, 150, 550, 200);
        frame.add(displayArea);

        // Show the frame
        frame.setVisible(true);
    }

    private void updateDisplay() {
        displayArea.setText("");  // Clear the text area
        playlist.displayPlaylist();  // Call your playlist display logic to show the current playlist
    }

    public static void main(String[] args) {
        new PlaylistManagerGUI();
    }
}
