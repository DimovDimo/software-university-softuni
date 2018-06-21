package p05_OnlineRadioDatabase;

public class Song {
    private String artistName;
    private String songName;
    private int minutes;
    private int seconds;

    public Song(String artistName, String songName, int minutes, int seconds) {
        this.setArtistName(artistName);
        this.setSongName(songName);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }

    private void setArtistName(String artistName) {
        if (artistName.length() < 3 || 20 < artistName.length()){
            throw new IllegalArgumentException("Artist name should be between 3 and 20 symbols.");
        }

        this.artistName = artistName;
    }

    private void setSongName(String songName) {
        if (songName.length() < 3 || 20 < songName.length()){
            throw new IllegalArgumentException("Song name should be between 3 and 30 symbols.");
        }

        this.songName = songName;
    }

    private void setMinutes(int minutes) {
        if (minutes < 0 || 14 < minutes){
            throw new IllegalArgumentException("Song minutes should be between 0 and 14.");
        }

        this.minutes = minutes;
    }

    private void setSeconds(int seconds) {
        if (seconds < 0 || 59 < seconds){
            throw new IllegalArgumentException("Song seconds should be between 0 and 59.");
        }

        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
