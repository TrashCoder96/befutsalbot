package ru.bifutsal.dao;

import javax.persistence.*;

/**
 * Created by asus-pc on 17.12.2017.
 */
@Entity
@Table(name = "teams")
public class TeamDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String externalId;

    @Column
    private String name;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
