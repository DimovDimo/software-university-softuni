package P02_FileStream;

public class StreamProgressInfo {
    private Streamble cource;

    public StreamProgressInfo(Streamble cource) {
        this.cource = cource;
    }

    public int calculateCurrentPercent() {
        return (this.cource.getBytesSent() * 100) / this.cource.getLength();
    }
}
