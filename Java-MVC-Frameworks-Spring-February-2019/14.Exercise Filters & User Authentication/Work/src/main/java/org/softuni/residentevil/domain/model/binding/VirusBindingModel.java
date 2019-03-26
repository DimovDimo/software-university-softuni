package org.softuni.residentevil.domain.model.binding;

import org.softuni.residentevil.domain.entities.Creator;
import org.softuni.residentevil.domain.entities.Magnitude;
import org.softuni.residentevil.domain.entities.Mutation;
import org.softuni.residentevil.validations.CapitalsListValidation;
import org.softuni.residentevil.validations.CreatorEnumValidation;
import org.softuni.residentevil.validations.DateValidation;
import org.softuni.residentevil.validations.MutationEnumValidation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public abstract class VirusBindingModel {
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
    private List<String> capitalList;

    public VirusBindingModel() {
    }

    @NotNull(message = "Cannot be empty")
    @Size(min = 3, max = 10, message = "Should be between 3 and 10 symbols.")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "Cannot be empty, should be between 5 and 100 symbols. Represented as Text in the database")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Size(min = 1, max = 50, message = "Should have a maximum of 50 symbols.")
    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @CreatorEnumValidation(enumClazz = Creator.class)
    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Boolean getDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return this.isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    @MutationEnumValidation(enumClazz = Mutation.class)
    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @NotNull(message = "Cannot be empty")
    @DecimalMin(value = "1", message = "Number, between 1 and 100.")
    @DecimalMax(value = "100", message = "Number, between 1 and 100.")
    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @NotNull(message = "Cannot be empty")
    @DecimalMin(value = "1", message = "Number, between 1 and 12.")
    @DecimalMax(value = "12", message = "Number, between 1 and 12.")
    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateValidation
    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    @CapitalsListValidation
    public List<String> getCapitalList() {
        return this.capitalList;
    }

    public void setCapitalList(List<String> capitalList) {
        this.capitalList = capitalList;
    }
}
