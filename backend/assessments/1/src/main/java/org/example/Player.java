package org.example;

/**
 * Class for creating players and there attributes
 */
public class Player {
   private String name;
   private String teamName;
   private String playerRole;
   private int matchPlayed;
   private int runsScored;
   private float average;
   private float strikeRate;
   private int wicketsTaken;

    /**
     * constructor to assign values to player
     * @param name         name of the player
     * @param teamName     name of the team of the player
     * @param playerRole   role of the player in team

     */
   public Player(String name,String teamName,String playerRole){
       this.name = name;
       this.teamName = teamName;
       this.playerRole  = playerRole;

   }

    /**
     *  to handle problem of assignment of parameters to constructor ,only 7 parameter can be assigned maximum
     *
     * @param matchPlayed  number of matches played
     * @param runsScored   total runs scored by the player
     * @param average      average of the player
     * @param strikeRate   strike rate of the player
     * @param wicketsTaken numbers of wicket taken by the player
     */
   public void  execute(int matchPlayed,int runsScored,float average,float strikeRate,int wicketsTaken){
       this.matchPlayed = matchPlayed;
       this.runsScored = runsScored;
       this.average = average;
       this.strikeRate =strikeRate;
       this.wicketsTaken = wicketsTaken;

       Logging.getmsg().info("Successfully  Created a Player !");
   }

   //getters and setters
    public String getName(){
       return name;
    }

    public String getTeamName(){return teamName;}

    public String getPlayerRole(){return playerRole;}

    public int getMatchPlayed(){return matchPlayed;}

    public int getRunsScored(){return runsScored;}

    public float getAverage(){return average;}

    public float getStrikeRate() {
        return strikeRate;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public void setMatchPlayed(int matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public void setStrikeRate(float strikeRate) {
        this.strikeRate = strikeRate;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }


}
