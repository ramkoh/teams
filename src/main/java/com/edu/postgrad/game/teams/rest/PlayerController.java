package com.edu.postgrad.game.teams.rest;

import java.util.List;

import com.edu.postgrad.game.teams.entity.Player;
import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping({"/hello"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "welcome";
    }

    @GetMapping("/player")
    public String showSignUpForm(final Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", Position.values());
        return "add-player";
    }

    @PostMapping("/player")
    public String addUser(@Valid Player player, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-player";
        }

        playerRepository.save(player);
        model.addAttribute("name", player.getName());
        System.out.println("jersey number..."+ player.getJerseyNumber());
        return "welcome";
    }

    @GetMapping("/players")
    public String getAllPlayers(final Model model) {
        Iterable<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "view-players";
    }
}
