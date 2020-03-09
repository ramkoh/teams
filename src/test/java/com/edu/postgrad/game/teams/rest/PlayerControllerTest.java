package com.edu.postgrad.game.teams.rest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.teams.dao.PlayerRepository;
import com.edu.postgrad.game.teams.service.PlayerService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlayerService playerService;



    @Before
    public void setup() {

    }

    @Test
    public void testShowDesignForm() throws Exception {
        Iterable<Player> players = Lists.newArrayList() ;
        given(playerService.getAllPlayers()).willReturn(players);
        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(view().name("players/add-player"));
    }
}
