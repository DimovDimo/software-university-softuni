package p05_BorderControl;

public abstract class Society implements Idable {
    protected String id;

    public Society(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isEndWith(String endString) {
        if (this.id.endsWith(endString)){
            return true;
        } else {
            return false;
        }
    }
}
