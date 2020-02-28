package com.edu.postgrad.game.teams.dao;

import com.edu.postgrad.game.common.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
