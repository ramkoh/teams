package com.edu.postgrad.game.teams.dao;

import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long>{
    @Query("select t.players from Team t where t.id = ?1")
    public List<Player> findPlayersById(Long teamId);
}
