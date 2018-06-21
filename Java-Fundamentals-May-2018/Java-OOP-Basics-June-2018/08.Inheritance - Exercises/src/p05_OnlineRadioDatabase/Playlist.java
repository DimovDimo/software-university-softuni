package p05_OnlineRadioDatabase;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Playlist {
    private List<Song> playlist;

    public Playlist() {
        this.playlist = new LinkedList<>();
    }

    public List<Song> getPlaylist() {
        return Collections.unmodifiableList(this.playlist);
    }

    public void setSong(Song song) {
        this.playlist.add(song);
    }

    public String getPlaylistLenght(){
        int totalSeconds = 0;
        for (Song song : this.playlist) {
            totalSeconds += song.getMinutes() * 60 + song.getSeconds();
        }

        int hoursOutput = totalSeconds / 60 / 60;
        int minutesOutput = (totalSeconds / 60) - (hoursOutput * 60);
        int secondsOutput = totalSeconds % 60;

        return String.format("%dh %dm %ds", hoursOutput, minutesOutput, secondsOutput);
    }
}
