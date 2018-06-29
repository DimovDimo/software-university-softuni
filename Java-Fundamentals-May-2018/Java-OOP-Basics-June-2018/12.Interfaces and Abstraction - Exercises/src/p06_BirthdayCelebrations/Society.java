package p06_BirthdayCelebrations;

public abstract class Society implements Idable, Birthdateable {
    protected String id;
    protected String birthdate;

    protected Society(String id, String birthdate) {
        this.id = id;
        this.birthdate = birthdate;
    }

    protected Society(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public boolean isEndWith(String endString) {
        if (this.id.endsWith(endString)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEndWithYear(String year) {
        if (this.birthdate.endsWith(year)){
            return true;
        } else {
            return false;
        }
    }
}
