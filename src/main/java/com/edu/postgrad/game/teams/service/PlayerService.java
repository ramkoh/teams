package com.edu.postgrad.game.teams.service;

import java.time.LocalDate;
import java.time.Period;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.exception.PlayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayerById(Long playerId){
        Player player = playerRepository.findById(playerId).orElseThrow(PlayerException::new);
        LocalDate now = LocalDate.now();
        player.setAge(Period.between(player.getDob(), now).getYears());
        return player;
    }

    public void savePlayer(final Player player){
        playerRepository.save(player);
    }

    public void deletePlayerById(final Long playerId){
        playerRepository.deleteById(playerId);
    }

    public Iterable<Player> getAllPlayers(){
        Iterable<Player> players = playerRepository.findAll();
        LocalDate now = LocalDate.now();
        players.forEach(p -> p.setAge(Period.between(p.getDob(), now).getYears()));
        return players;
    }
}
