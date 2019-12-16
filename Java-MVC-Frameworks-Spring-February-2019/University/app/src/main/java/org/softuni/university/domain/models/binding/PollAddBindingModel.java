package org.softuni.university.domain.models.binding;

public class PollAddBindingModel {

    private String liking;
    private String notLiking;
    private String easiest;
    private String mostDifficult;
    private String whatYouWantChanges;
    private String whoYouSaid;
    private String yourWork;
    private String whatEmployersAreLookingFor;

    public PollAddBindingModel() {
    }

    public String getLiking() {
        return liking;
    }

    public void setLiking(String liking) {
        this.liking = liking;
    }

    public String getNotLiking() {
        return notLiking;
    }

    public void setNotLiking(String notLiking) {
        this.notLiking = notLiking;
    }

    public String getEasiest() {
        return easiest;
    }

    public void setEasiest(String easiest) {
        this.easiest = easiest;
    }

    public String getMostDifficult() {
        return mostDifficult;
    }

    public void setMostDifficult(String mostDifficult) {
        this.mostDifficult = mostDifficult;
    }

    public String getWhatYouWantChanges() {
        return whatYouWantChanges;
    }

    public void setWhatYouWantChanges(String whatYouWantChanges) {
        this.whatYouWantChanges = whatYouWantChanges;
    }

    public String getWhoYouSaid() {
        return whoYouSaid;
    }

    public void setWhoYouSaid(String whoYouSaid) {
        this.whoYouSaid = whoYouSaid;
    }

    public String getYourWork() {
        return yourWork;
    }

    public void setYourWork(String yourWork) {
        this.yourWork = yourWork;
    }

    public String getWhatEmployersAreLookingFor() {
        return whatEmployersAreLookingFor;
    }

    public void setWhatEmployersAreLookingFor(String whatEmployersAreLookingFor) {
        this.whatEmployersAreLookingFor = whatEmployersAreLookingFor;
    }
}
