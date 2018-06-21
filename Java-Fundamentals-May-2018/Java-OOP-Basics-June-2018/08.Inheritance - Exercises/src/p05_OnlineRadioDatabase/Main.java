package p05_OnlineRadioDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Playlist playlist = new Playlist();
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            try {
            String[] tokens = reader.readLine().split(";");
            String artistName = tokens[0];
            String songName = tokens[1];

            String[] lenghtTokens = tokens[2].split(":");
            int seconds = 0;
            int minutes = 0;
            try {
                seconds = Integer.parseInt(lenghtTokens[1]);
                minutes = Integer.parseInt(lenghtTokens[0]);
            }catch (Exception ex){
                throw new IllegalArgumentException("Invalid song length.");
            }


                Song song = new Song(artistName, songName, minutes, seconds);
                playlist.setSong(song);
                System.out.println("Song added.");
            } catch (IllegalArgumentException error){
                System.out.println(error.getMessage());
            }
        }

        System.out.printf("Songs added: %d%n", playlist.getPlaylist().size());
        System.out.printf("Playlist length: %s", playlist.getPlaylistLenght());
    }
}
