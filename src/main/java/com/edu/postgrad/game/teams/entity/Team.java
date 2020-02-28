//package com.edu.postgrad.game.teams.entity;
//
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "teams")
//public class Team {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @Size(max = 3, message = "More than 3 characters are not allowed.")
//    private String code;
//
//    private String web;
//
//    private String representsState;
//
//    @Size(min = 1, message = "Team must have at least 1 player.")
//    @OneToMany(targetEntity = Player.class)
//    List<Player> players;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getRepresentsState() {
//        return representsState;
//    }
//
//    public void setRepresentsState(String representsState) {
//        this.representsState = representsState;
//    }
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getWeb() {
//        return web;
//    }
//
//    public void setWeb(String web) {
//        this.web = web;
//    }
//
//    public List<Player> getPlayers() {
//        return players;
//    }
//
//    public void addPlayers(List<Player> players) {
//       getPlayers().addAll(players);
//    }
//
//    public void addPlayer(Player player) {
//        getPlayers().add(player);
//    }
//}
