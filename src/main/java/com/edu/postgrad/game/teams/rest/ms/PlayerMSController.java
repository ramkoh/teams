package com.edu.postgrad.game.teams.rest.ms;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.teams.exception.PlayerException;
import com.edu.postgrad.game.teams.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PlayerMSController {
    @Autowired
    PlayerService playerService;

    @GetMapping("player/{id}")
    public ResponseEntity<Player> getPlayerById( @PathVariable final Long id) {
        final Player player = playerService.getPlayerById(id);
        return new ResponseEntity<Player>(player, HttpStatus.OK);
    }

    @PostMapping("player")
    public ResponseEntity createPlayer(@RequestBody Player player){
        playerService.savePlayer(player);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(player.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("player/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        playerService.savePlayer(player);
        return new ResponseEntity<Player>(player, HttpStatus.OK);
    }

    @DeleteMapping("player/{id}")
    public ResponseEntity deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("players")
    public List<Player> getAllPlayers(){
        List<Player> players = Optional.ofNullable(playerService.getAllPlayers())
                .orElseThrow(PlayerException::new);
        return players;
    }

    @GetMapping("player/name/{name}")
    public ResponseEntity<Player> getPayerByName(@PathVariable String name){
        Player player = playerService.getPlayerByFirstName(name);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping("player/countryOfBirth/{countryOfBirth}")
    public List<Player> getPayerByCountryOfBirth(@PathVariable String countryOfBirth){
        List<Player> players = playerService.getPlayerByCountryOfBirth(countryOfBirth);
        return players;
    }


}
