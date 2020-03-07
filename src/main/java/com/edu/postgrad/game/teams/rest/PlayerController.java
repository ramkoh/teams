package com.edu.postgrad.game.teams.rest;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Position;
import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.exception.PlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/player")
    public String addPlayerStartUp(final Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", Position.values());
        model.addAttribute("message", null);
        return "players/add-player";
    }

    @GetMapping("/player/{id}")
    public String getPlayerById(final Model model, @PathVariable final Long id,
                                @RequestParam String show) {
        Player player = playerRepository.findById(id).orElseThrow(PlayerException::new);
        LocalDate now = LocalDate.now();
        player.setAge(Period.between(player.getDob(), now).getYears());
        model.addAttribute("player", player);
        if("update".equals(show)){
            model.addAttribute("positions", Position.values());
            return "players/update-player";
        }
        return "players/view-player";
    }

    @PutMapping("/player/{id}")
    public String updatePlayer( @PathVariable final Long id,
                                @Valid Player player, BindingResult result, Model model) {
         if (result.hasErrors()) {
            return "players/update-player";
        }
      Player existingPlayer = playerRepository.findById(id).orElseThrow(PlayerException::new);
      //Player updatedPlayer = (Player) model.getAttribute("player");
         /*if(player.getAge() != updatedPlayer.getAge()){
             new PlayerException("Age cannot be changed, it's not saved to DB.");
        }*/
        playerRepository.save(existingPlayer);
        return "players/update-player";
    }

    @DeleteMapping("/player/{id}")
    public String deletePlayer( @PathVariable final Long id, Model model) {
        Player player = playerRepository.findById(id).orElseThrow(PlayerException::new);
        playerRepository.deleteById(id);
        Iterable<Player> players = playerRepository.findAll();
        LocalDate now = LocalDate.now();
        players.forEach(p -> p.setAge(Period.between(p.getDob(), now).getYears()));
        model.addAttribute("players", players);
        model.addAttribute("message", String.format("Player %s %s deleted successfully", player.getFirstName(),
                player.getLastName()));
        return "players/view-players";
    }

    @PostMapping("/player")
    public String addPlayer(@Valid Player player, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("positions", Position.values());
            return "players/add-player";
        }

        playerRepository.save(player);
        model.addAttribute("player", player);
        model.addAttribute("message", String.format("Player %s %s created successfully", player.getFirstName(),
                player.getLastName()));
        return "players/add-player";
    }

    @GetMapping("/players")
    public String getAllPlayers(final Model model) {
        Iterable<Player> players = playerRepository.findAll();
        LocalDate now = LocalDate.now();
        players.forEach(p -> p.setAge(Period.between(Optional.of(p.getDob()).orElse(now), now).getYears()));
        model.addAttribute("players", players);
        model.addAttribute("source", "players");
        return "players/view-players";
    }
}
