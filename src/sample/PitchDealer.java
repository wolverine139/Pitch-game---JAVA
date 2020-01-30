package sample;

import java.util.ArrayList;
import java.util.Comparator;

public class PitchDealer implements Dealer{
    Deck deck = new Deck();
    Card card = new Card();
    private Integer highestBidder;
    private String trumpSuit;
    private String roundCard;
    private boolean trumpUsed;
    private Integer trumpHigh;

    public PitchDealer() {
    }

    @Override
    public ArrayList<Card> dealHand() {
        ArrayList<Card> sixCard = new ArrayList<>();
        for(int i = 0; i < 6; i++)
        {
            card = deck.randomCard();
            sixCard.add(card);
        }
        return sixCard;
    }

    public Integer getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(Integer highestBidder) {
        this.highestBidder = highestBidder;
    }

    public String getTrumpSuit() {
        return trumpSuit;
    }

    public void setTrumpSuit(String trumpSuit) {
        this.trumpSuit = trumpSuit;
    }

    public String getRoundCard() {
        return roundCard;
    }

    public void setRoundCard(String roundCard) {
        this.roundCard = roundCard;
    }

    public boolean isTrumpUsed() {
        return trumpUsed;
    }

    public void setTrumpUsed(boolean trumpUsed) {
        this.trumpUsed = trumpUsed;
    }

    public Integer getTrumpHigh() {
        return trumpHigh;
    }

    public void setTrumpHigh(Integer trumpHigh) {
        this.trumpHigh = trumpHigh;
    }
}
