package org.softuni.university.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "modules")
public class Module extends BaseEntity {

    private String name;

    public Module() {
    }

    @Column(name = "module_name",nullable = false, unique = true, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
