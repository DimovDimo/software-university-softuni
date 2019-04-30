package org.softuni.university.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "camps")
public class Camp extends BaseEntity {

    private int nights;
    private int beds;
    private int parkingLots;
    private String place;
    private String drinks;
    private String dishes;
    private String music;
    private String touristSitesInTheVicinity;
    private String teamGame;
    private String whatDontYouWantHave;
    User user;

    public Camp() {
    }

    @Column(name = "camp_nights",nullable = false, unique = false, updatable = false)
    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    @Column(name = "camp_beds",nullable = false, unique = false, updatable = false)
    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    @Column(name = "camp_parking_lots",nullable = false, unique = false, updatable = false)
    public int getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(int parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Column(name = "camp_place",nullable = false, unique = false, updatable = false)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "camp_drinks",nullable = true, unique = false, updatable = false)
    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    @Column(name = "camp_dishes",nullable = true, unique = false, updatable = false)
    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    @Column(name = "camp_music",nullable = true, unique = false, updatable = false)
    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @Column(name = "camp_tourist_sites_in_the_vicinity",nullable = true, unique = false, updatable = false)
    public String getTouristSitesInTheVicinity() {
        return touristSitesInTheVicinity;
    }

    public void setTouristSitesInTheVicinity(String touristSitesInTheVicinity) {
        this.touristSitesInTheVicinity = touristSitesInTheVicinity;
    }

    @Column(name = "camp_team_game",nullable = true, unique = false, updatable = false)
    public String getTeamGame() {
        return teamGame;
    }

    public void setTeamGame(String teamGame) {
        this.teamGame = teamGame;
    }

    @Column(name = "camp_what_dont_you_want_have",nullable = true, unique = false, updatable = false)
    public String getWhatDontYouWantHave() {
        return whatDontYouWantHave;
    }

    public void setWhatDontYouWantHave(String whatDontYouWantHave) {
        this.whatDontYouWantHave = whatDontYouWantHave;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
