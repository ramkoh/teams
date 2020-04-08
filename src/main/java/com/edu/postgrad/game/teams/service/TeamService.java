package com.edu.postgrad.game.teams.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.NullAwareBeanUtilsBean;
import com.edu.postgrad.game.teams.dao.TeamRepository;
import com.edu.postgrad.game.teams.exception.TeamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    NullAwareBeanUtilsBean nullAwareBeanUtilsBean;


    public void saveTeam(final Team team){
        teamRepository.save(team);
    }

    public Page<Team> getAllTeams(Pageable pageable){
        Page<Team> teams = teamRepository.findAll(pageable);
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

    public Team getTeamByName(final String name){
        return teamRepository.findTeamByName(name).orElseThrow(TeamException::new);
    }

    public Long patch(Team toBePatched){
        Optional<Team> optionalManager = teamRepository.findById(toBePatched.getId());
        if (optionalManager.isPresent()) {
            Team fromDb = optionalManager.get();
            // bean utils will copy non null values from toBePatched to fromDb team.
            try {
                nullAwareBeanUtilsBean.copyProperties(fromDb, toBePatched);
                saveTeam(fromDb);
            }catch (IllegalAccessException | InvocationTargetException e){
                LOGGER.error(e.getMessage());
            }
            return fromDb.getId();
        }
      return -1L;
    }

}
