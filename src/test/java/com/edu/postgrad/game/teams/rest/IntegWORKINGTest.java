package com.edu.postgrad.game.teams.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.common.Position;
import com.edu.postgrad.game.teams.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegWORKINGTest {

    @Autowired
    PlayerService playerService;

    @Test
    void getAllPlayersReturnsCorrectNumber() throws Exception {

        Iterable<Player> iterable = playerService.getAllPlayers();
        List<Player> players = new ArrayList<Player>();
        iterable.forEach(players::add);
        int preSize = players.size();

        createPlayer();

        iterable = playerService.getAllPlayers();
        players = new ArrayList<Player>();
        iterable.forEach(players::add);
        int postSize = players.size();
        assert postSize == preSize + 1;

    }
    private void createPlayer(){

        Player player = new Player();
        player.setFirstName("Raman");
        player.setLastName("Kohli");
        player.setJerseyNumber(10);
        player.setPosition(Position.DEFENDER);
        player.setDob(LocalDate.of(1990,1,1));
        playerService.savePlayer(player);
    }
}
