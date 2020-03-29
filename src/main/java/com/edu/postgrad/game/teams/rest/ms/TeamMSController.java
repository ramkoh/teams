package com.edu.postgrad.game.teams.rest.ms;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import brave.ScopedSpan;
import brave.Tracer;
import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.exception.PlayerException;
import com.edu.postgrad.game.teams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/ms", produces = "application/json")
public class TeamMSController {

    @Autowired
    TeamService teamService;

    @Autowired
    Tracer tracer;

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }


    @PostMapping("/team")
    public ResponseEntity addTeam(@RequestBody Team team) {
        teamService.saveTeam(team);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(team.getId())
                .toUri();
        return ResponseEntity.created(location).build();
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
    public List<Player> getPlayersOfTeam(@PathVariable Long id) {
        List<Player> players = Optional.ofNullable(teamService.getPlayersOfTeam(id))
                .orElseThrow(PlayerException::new);
        return players;
    }

    @GetMapping("/teams")
    public Page<Team> getAllTeams(final Pageable pageable) {
        ScopedSpan scopedSpan = tracer.startScopedSpan("teams-service");
        scopedSpan.annotate("getAllTeams");
        Page<Team> teams = Optional.ofNullable(teamService.getAllTeams())
                .orElseThrow(PlayerException::new);
        return teams;
    }

    @GetMapping("team/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable  String name){
        Team team = teamService.getTeamByName(name);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }
}
