package P02_FileStream;

public class StreamProgressInfo {
    private File file;

    // If we want to stream a music file, we can't
    public StreamProgressInfo(File file) {
        this.file = file;
    }

    public int calculateCurrentPercent() {
        return (this.file.getBytesSent() * 100) / this.file.getLength();
    }
}
