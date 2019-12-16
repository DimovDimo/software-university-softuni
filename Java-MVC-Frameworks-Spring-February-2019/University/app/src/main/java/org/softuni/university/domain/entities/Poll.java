package org.softuni.university.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "polls")
public class Poll extends BaseEntity {

    private String liking;
    private String notLiking;
    private String easiest;
    private String mostDifficult;
    private String whatYouWantChanges;
    private String whoYouSaid;
    private String yourWork;
    private String whatEmployersAreLookingFor;
    User user;

    public Poll() {
    }

    @Column(name = "poll_liking",nullable = false, unique = false, updatable = false)
    public String getLiking() {
        return liking;
    }

    public void setLiking(String liking) {
        this.liking = liking;
    }

    @Column(name = "poll_not_liking",nullable = false, unique = false, updatable = false)
    public String getNotLiking() {
        return notLiking;
    }

    public void setNotLiking(String notLiking) {
        this.notLiking = notLiking;
    }

    @Column(name = "poll_easiest",nullable = true, unique = false, updatable = false)
    public String getEasiest() {
        return easiest;
    }

    public void setEasiest(String easiest) {
        this.easiest = easiest;
    }

    @Column(name = "poll_most_difficult",nullable = true, unique = false, updatable = false)
    public String getMostDifficult() {
        return mostDifficult;
    }

    public void setMostDifficult(String mostDifficult) {
        this.mostDifficult = mostDifficult;
    }

    @Column(name = "poll_what_you_want_changes",nullable = true, unique = false, updatable = false)
    public String getWhatYouWantChanges() {
        return whatYouWantChanges;
    }

    public void setWhatYouWantChanges(String whatYouWantChanges) {
        this.whatYouWantChanges = whatYouWantChanges;
    }

    @Column(name = "poll_who_you_said",nullable = true, unique = false, updatable = false)
    public String getWhoYouSaid() {
        return whoYouSaid;
    }

    public void setWhoYouSaid(String whoYouSaid) {
        this.whoYouSaid = whoYouSaid;
    }

    @Column(name = "poll_your_work",nullable = true, unique = false, updatable = false)
    public String getYourWork() {
        return yourWork;
    }

    public void setYourWork(String yourWork) {
        this.yourWork = yourWork;
    }

    @Column(name = "poll_what_employers_are_looking_for",nullable = true, unique = false, updatable = false)
    public String getWhatEmployersAreLookingFor() {
        return whatEmployersAreLookingFor;
    }

    public void setWhatEmployersAreLookingFor(String whatEmployersAreLookingFor) {
        this.whatEmployersAreLookingFor = whatEmployersAreLookingFor;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
