package com.edu.postgrad.game.teams.dao;

import com.edu.postgrad.game.common.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
