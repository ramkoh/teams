package com.edu.postgrad.game.teams.rest;

import java.time.LocalDate;
import java.time.Period;

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

    @GetMapping({"/"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "welcome";
    }

    @GetMapping("/player")
    public String addPlayerStartUp(final Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", Position.values());
        return "add-player";
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
            return "update-player";
        }
        return "view-player";
    }

    @PutMapping("/player/{id}")
    public String updatePlayer( @PathVariable final Long id,
                                @Valid Player player, BindingResult result, Model model) {
         if (result.hasErrors()) {
            return "update-player";
        }
      Player existingPlayer = playerRepository.findById(id).orElseThrow(PlayerException::new);
      //Player updatedPlayer = (Player) model.getAttribute("player");
         /*if(player.getAge() != updatedPlayer.getAge()){
             new PlayerException("Age cannot be changed, it's not saved to DB.");
        }*/
        playerRepository.save(existingPlayer);
        return "update-player";
    }

    @DeleteMapping("/player/{id}")
    public String deletePlayer( @PathVariable final Long id, Model model) {
        playerRepository.deleteById(id);
        Iterable<Player> players = playerRepository.findAll();
        LocalDate now = LocalDate.now();
        players.forEach(p -> p.setAge(Period.between(p.getDob(), now).getYears()));
        model.addAttribute("players", players);
        return "view-players";
    }

    @PostMapping("/player")
    public String addPlayer(@Valid Player player, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add-player";
        }

        playerRepository.save(player);
        model.addAttribute("lastName", player.getLastName());
        return "welcome";
    }

    @GetMapping("/players")
    public String getAllPlayers(final Model model) {
        Iterable<Player> players = playerRepository.findAll();
        LocalDate now = LocalDate.now();
        players.forEach(p -> p.setAge(Period.between(p.getDob(), now).getYears()));
        model.addAttribute("players", players);
        return "view-players";
    }
}
