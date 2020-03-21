package com.edu.postgrad.game.teams.service;

import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.dao.TeamRepository;
import com.edu.postgrad.game.teams.exception.TeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public Page<Team> getAllTeams(){
        Pageable  pageable = PageRequest.of(0, 20, Sort.by("name"));
        Page<Team> teams = teamRepository.findAll(pageable);
        return teams;
    }

    public List<Team> getSortedTeams(Pageable pageable){
        List<Team> teams = teamRepository.findAll(pageable.getSort());
        //List<Team> teams = teamRepository.findAll(Sort.by("name").descending().and(Sort.by("code")));
        return teams;
    }

    public List<Player> getPlayersOfTeam(final Long teamId){
        List<Player> players = teamRepository.findPlayersById(teamId);
        return players;
    }

    public Team getTeamById(final Long teamId){
        return teamRepository.findById(teamId).orElseThrow(TeamException::new);
    }

    public void deleteTeam(final Long teamId){
        final Team team = getTeamById(teamId);
        teamRepository.delete(team);
    }

}
