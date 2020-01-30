package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Card attribute class

public class Card {
    private String suit;
    private String rank;
    private ImageView imageCard;
    private Integer cardValue;
    public Card() {
    }

    public Card(String suit, String rank, ImageView imageCard, Integer cardValue) {
        this.suit = suit;
        this.rank = rank;
        this.imageCard = imageCard;
        this.cardValue = cardValue;
    }

    public Card(String suit, String rank, Integer cardValue) {
        this.suit = suit;
        this.rank = rank;
        this.cardValue = cardValue;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public ImageView getImageCard() {
        return imageCard;
    }

    public Integer getCardValue() {
        return cardValue;
    }

    public void setCardValue(Integer cardValue) {
        this.cardValue = cardValue;
    }
}
