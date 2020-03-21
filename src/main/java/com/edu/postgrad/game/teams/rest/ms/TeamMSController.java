package com.edu.postgrad.game.teams.rest.ms;


import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/ms", produces = "application/json")
public class TeamMSController {

    @Autowired
    TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        teamService.saveTeam(team);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }


    @PutMapping("/team/{id}")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        teamService.saveTeam(team);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

    @DeleteMapping("/team/{id}")
    public ResponseEntity deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/team/{id}/players")
    public ResponseEntity<List<Player>> getPlayers(@PathVariable Long id) {
        List<Player> players = teamService.getPlayersOfTeam(id);
        return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
    }

    @GetMapping("/teams")
    public ResponseEntity<Iterable<Team>> getAllTeams(final Pageable pageable) {
        Iterable<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<Iterable<Team>>(teams, HttpStatus.OK);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }

}
