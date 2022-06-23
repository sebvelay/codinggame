package com.greenit.game;

public class Team {
    private final int playerLocation;
    private final int playerScore;
    private final int playerPermanentDailyRoutineCards;
    private final int playerPermanentArchitectureStudyCards;

    public Team(int playerLocation, int playerScore, int playerPermanentDailyRoutineCards, int playerPermanentArchitectureStudyCards) {
        this.playerLocation = playerLocation;
        this.playerScore = playerScore;
        this.playerPermanentDailyRoutineCards = playerPermanentDailyRoutineCards;
        this.playerPermanentArchitectureStudyCards = playerPermanentArchitectureStudyCards;

    }

    public int nextZone(){
        if(playerLocation==7){
            return 0;
        }
        return playerLocation+1;
    }

    public void debug(){
        System.err.println("playerLocation: " + playerLocation +"   playerScore: " + playerScore + "   playerPermanentDailyRoutineCards: " + playerPermanentDailyRoutineCards + "   playerPermanentArchitectureStudyCards: " + playerPermanentArchitectureStudyCards);
    }
}
