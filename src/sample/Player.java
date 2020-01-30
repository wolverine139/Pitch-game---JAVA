package sample;

import java.util.ArrayList;

public class Player {
    private boolean playerType;
    private String name;
    private Integer biddingValue;
    //private Card playerDeck;
    private ArrayList<Card> hand;
    private boolean playerTurn;

    public Player(boolean playerType, String name) {
        this.playerType = playerType;
        this.name = name;
    }

    public void setHand(ArrayList<Card> setHand) {
        hand = setHand;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }

    public Integer getBiddingValue() {
        return biddingValue;
    }

    public void setBiddingValue(Integer biddingValue) {
        this.biddingValue = biddingValue;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public String getName() {
        return name;
    }

}
