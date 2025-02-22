//package com.edu.postgrad.game.teams.rest;
//
//
//import java.util.List;
//
//import com.edu.postgrad.game.common.Player;
//import com.edu.postgrad.game.common.Team;
//import com.edu.postgrad.game.teams.exception.PlayerException;
//import com.edu.postgrad.game.teams.exception.TeamException;
//import com.edu.postgrad.game.teams.dao.PlayerRepository;
//import com.edu.postgrad.game.teams.dao.TeamRepository;
//
//import com.edu.postgrad.game.teams.service.TeamService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.validation.Valid;
//
//@Controller
//public class TeamController {
//
//    @Autowired
//    TeamService teamService;
//
//
//    @GetMapping("/team")
//    public String showAddTeamForm(final Model model) {
//        model.addAttribute("team", new Team());
//        return "add-team";
//    }
//
//    @PostMapping("/team")
//    public String addTeam(@Valid Team team, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-team";
//        }
//        teamService.saveTeam(team);
//        model.addAttribute("name", team.getName());
//        return "welcome";
//    }
//
//
//
//   /* @GetMapping("/addPlayerToTeam")
//    public String addPlayerToTeam(@Valid Team team, BindingResult result, Model model) {
//        Team existingTeam = teamRepository.findById(team.getId()).orElseThrow(TeamException::new);
//        if (result.hasErrors()) {
//            return "add-player-to-team";
//        }
//        Player player = playerRepository.findById(Long.parseLong( model.getAttribute("playerId").toString()))
//                .orElseThrow(PlayerException::new);
//        existingTeam.addPlayer(player);
//
//        teamRepository.save(team);
//        return "player/view-players";
//    }*/
//
//    @GetMapping("/team/{id}/players")
//    public String getPlayers(@PathVariable Long id, Model model) {
//        List<Player> players = teamService.getPlayersOfTeam(id);
//        model.addAttribute("players", players);
//        model.addAttribute("source", "teams");
//        return "players/view-players";
//    }
//
//    @GetMapping("/teams")
//    public String getAllPlayers(final Model model) {
//        Iterable<Team> teams = teamService.getAllTeams();
//        model.addAttribute("teams", teams);
//        return "view-teams";
//    }
//}
