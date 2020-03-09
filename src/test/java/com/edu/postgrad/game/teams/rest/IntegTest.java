//package com.edu.postgrad.game.teams.rest;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import com.edu.postgrad.game.common.Player;
//import com.edu.postgrad.game.common.Position;
//import com.edu.postgrad.game.teams.TeamsApplication;
//import com.edu.postgrad.game.teams.service.PlayerService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.web.client.RestTemplate;
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static
//        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static
//        org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest/*(classes = TeamsApplication.class)*/
////@AutoConfigureMockMvc
////@TestPropertySource(
////        locations = "classpath:application-integration-tests.properties")
//public class IntegTest {
//  /*  @Autowired
//    private MockMvc mvc;*/
//
//    @Autowired
//    PlayerService playerService;
//
//
//    TestRestTemplate restTemplate = new TestRestTemplate();
//    @Test
//    public void whenGetPlayers_thenStatus200()
//            throws Exception {
//        createPlayer();
//        Object players = this.restTemplate.  getForObject("http://localhost:8081/players", Object.class);
//        System.out.println(players);
//        assert players != null;
//      /*  mvc.perform(get("/players")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));*/
//                //.andExpect(jsonPath("$[0].name", is("bob")));*/
//    }
//    HttpHeaders headers = new HttpHeaders();
//    @Test
//    public void testRetrieveStudent() throws Exception {
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/players"), HttpMethod.GET, entity, String.class);
//        String expected = "{\"id\":1,\"name\":\"Rajesh Bhojwani\",\"description\":\"Class 10\"}";
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        //JSONAssert.assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//    private void createPlayer(){
//
//        Player player = new Player();
//        player.setFirstName("Raman");
//        player.setLastName("Kohli");
//        player.setJerseyNumber(10);
//        player.setPosition(Position.DEFENDER);
//        player.setDob(LocalDate.of(1990,1,1));
//        playerService.savePlayer(player);
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost:8081"  + uri;
//    }
//}
