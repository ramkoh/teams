//package com.edu.postgrad.game.teams.integration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.edu.postgrad.game.common.Player;
//import com.edu.postgrad.game.common.Team;
//import com.edu.postgrad.game.teams.service.TeamService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class TeamsTest {
//    @Autowired
//    TeamService teamService;
//
//    @Test
//    public void getAllTeams(){
//        Iterable<Team> iterable = teamService.getAllTeams();
//        List<Team> teams = new ArrayList<Team>();
//        iterable.forEach(teams::add);
//        int preSize = teams.size();
//
//        createTeam();
//
//        iterable = teamService.getAllTeams();
//        teams = new ArrayList<Team>();
//        iterable.forEach(teams::add);
//        int postSize = teams.size();
//        assert postSize == preSize + 1;
//
//    }
//
//    private void createTeam(){
//
//    }
//    private Team getTeamDto(){
//        Team team = new Team();
//        team.setName("Ireland");
//        team.setCode("IRL");
//        return team;
//    }
//}
