package com.edu.postgrad.game.teams.rest.ms;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.dao.TeamRepository;
import com.edu.postgrad.game.teams.exception.PlayerException;
import com.edu.postgrad.game.teams.exception.TeamException;
import com.edu.postgrad.game.teams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CommonController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamService teamService;


    //@PutMapping("/addPlayerToTeam/{playerId}/team/{teamId}")
    @PatchMapping("team/{teamId}")
    public ResponseEntity addPlayerToTeam(@RequestBody Team toBePatched, @PathVariable Long teamId) {
        List<Long> playerIds = new ArrayList<>();
        toBePatched.getPlayers().forEach(player -> playerIds.add(player.getId()));
        List<Player> players = playerRepository.findAllById(playerIds);
        Team team = teamService.getTeamById(teamId);
        team.addPlayers(players);
        toBePatched.setId(teamId);
        teamService.patch(toBePatched);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/")
                .buildAndExpand(teamId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
