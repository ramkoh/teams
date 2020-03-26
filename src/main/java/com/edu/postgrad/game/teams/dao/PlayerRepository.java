package com.edu.postgrad.game.teams.dao;

import java.util.List;
import java.util.Optional;

import com.edu.postgrad.game.common.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
     Optional<Player> findPlayerByFirstName(String firstName);
     List<Player> findPlayerByCountryOfBirth(String countryOfBirth);

}
