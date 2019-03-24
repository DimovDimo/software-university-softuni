package org.softuni.residentevil.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity{
    private String name;
    private String description;
    private String sideEffects;
    private Creator creator;
    private Boolean isDeadly;
    private Boolean isCurable;
    private Mutation mutation;
    private Integer turnoverRate;
    private Integer hoursUntilTurn;
    private Magnitude magnitude;
    private LocalDate releasedOn;
    private List<Capital> capitalList;

    public Virus() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "side_effects")
    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "creator")
    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @Column(name = "is_deadly")
    public Boolean getDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    @Column(name = "is_curable")
    public Boolean getCurable() {
        return this.isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation")
    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Column(name = "turnover_rate")
    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Column(name = "hours_until_turn")
    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "magnitude")
    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Column(name = "released_on")
    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @OneToMany(targetEntity = Capital.class)
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id")
    )
    public List<Capital> getCapitalList() {
        return this.capitalList;
    }

    public void setCapitalList(List<Capital> capitalList) {
        this.capitalList = capitalList;
    }
}
