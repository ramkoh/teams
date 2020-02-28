package com.edu.postgrad.game.teams.rest;

import static org.mockito.Mockito.verify;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.edu.postgrad.game.teams.dao.PlayerRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerRepository playerRepository;

    @Before
    public void setup() {

    }

    @Test
    public void testShowDesignForm() throws Exception {
        mockMvc.perform(get("/player"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-player"));
    }
}
