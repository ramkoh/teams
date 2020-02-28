package com.edu.postgrad.game.teams.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Column(name = "jersey_number")
    private int jerseyNumber;

    @Column( columnDefinition = "DATE")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_position")
    private Position position;

    @ManyToOne(targetEntity = Team.class)
    private Team team;

    public Player(){

    }

/*    public Player(final String name, final int jerseyNumber, final LocalDate dob){
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.dob = dob;
    }*/

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}
