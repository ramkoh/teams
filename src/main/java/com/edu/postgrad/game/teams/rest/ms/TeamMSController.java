package com.edu.postgrad.game.teams.rest.ms;


import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import com.edu.postgrad.game.teams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/ms", produces = "application/json")
public class TeamMSController {

    @Autowired
    TeamService teamService;





   /* @GetMapping("/addPlayerToTeam")
    public String addPlayerToTeam(@Valid Team team, BindingResult result, Model model) {
        Team existingTeam = teamRepository.findById(team.getId()).orElseThrow(TeamException::new);
        if (result.hasErrors()) {
            return "add-player-to-team";
        }
        Player player = playerRepository.findById(Long.parseLong( model.getAttribute("playerId").toString()))
                .orElseThrow(PlayerException::new);
        existingTeam.addPlayer(player);

        teamRepository.save(team);
        return "player/view-players";
    }*/

    @GetMapping("/team/{id}/players")
    public String getPlayers(@PathVariable Long id, Model model) {
        List<Player> players = teamService.getPlayersOfTeam(id);
        model.addAttribute("players", players);
        model.addAttribute("source", "teams");
        return "players/view-players";
    }

    @GetMapping("/teams")
    public String getAllPlayers(final Pageable pageable) {
        Iterable<Team> teams = teamService.getAllTeams();
        return "view-teams";
    }
}
