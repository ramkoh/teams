package com.edu.postgrad.game.teams.service;

import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.dao.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class TeamService {

    @Autowired
    TeamRepository teamRepository;


    public void saveTeam(final Team team){
        teamRepository.save(team);
    }

    public Iterable<Team> getAllTeams(){
        Pageable  pageable = PageRequest.of(0, 20, Sort.by("name"));
        Iterable<Team> teams = teamRepository.findAll(pageable);
        return teams;
    }

    public List<Player> getPlayersOfTeam(final Long teamId){
        List<Player> players = teamRepository.findPlayersById(teamId);
        return players;
    }



}
