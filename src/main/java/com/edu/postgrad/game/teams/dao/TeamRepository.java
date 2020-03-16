package com.edu.postgrad.game.teams.dao;

import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t.players from Team t where t.id = ?1")
    public List<Player> findPlayersById(Long teamId);
}
