package com.edu.postgrad.game.teams.rest.ms;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import brave.ScopedSpan;
import brave.Tracer;
import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.dao.PlayerRepository;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    Tracer tracer;

    @GetMapping("/team/{id}")
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

   /* @PatchMapping("team/{teamId}")
    public ResponseEntity addPlayerToTeam(@RequestBody Team toBePatched, @PathVariable Long teamId) {
        toBePatched.setId(teamId);
        Team team = teamService.getTeamById(teamId);
        List<Long> playerIds = new ArrayList<>();
        toBePatched.getPlayers().forEach(player -> playerIds.add(player.getId()));
        List<Player> players = playerRepository.findAllById(playerIds);

        team.addPlayers(players);
        teamService.patch(toBePatched);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }*/

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
        Page<Team> teams = Optional.ofNullable(teamService.getAllTeams(pageable))
                .orElseThrow(PlayerException::new);
        return teams;
    }

    @GetMapping("team/name/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable  String name){
        Team team = teamService.getTeamByName(name);
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }
}
