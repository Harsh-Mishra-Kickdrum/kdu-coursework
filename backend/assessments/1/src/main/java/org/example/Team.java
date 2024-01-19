package org.example;

import java.util.ArrayList;
import java.util.List;


public class Team {

private String homeGround;
ArrayList<Player> teamPlayers = new ArrayList<>();

//constructor
    public Team(String homeGround,ArrayList<Player> teamPlayers){
        this.homeGround = homeGround;
        this.teamPlayers = teamPlayers;
        Logging.getmsg().info("Successfully created a Team!");
    }


//getters and setters
    public String getHomeGround(){return homeGround;}

    public List<Player> getTeamPlayers(){return teamPlayers;}

}
