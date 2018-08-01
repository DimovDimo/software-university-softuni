package P02_FileStream;

public class File implements Streamble {

    private String name;
    private int length;
    private int bytesSent;

    public int getLength() {
        return length;
    }

    public int getBytesSent() {
    return bytesSent;
    }
}
