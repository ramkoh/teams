package com.edu.postgrad.game.teams.dao;

import java.util.List;

import com.edu.postgrad.game.common.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    public List<Player> findByTeamId(Long teamId);
}
