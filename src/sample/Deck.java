package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

//Class where deck get created

public class Deck {

    private List<Card> Cards;
    private int numberCard = 52;

    String[] Suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    String[] Ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"};
    Integer[] cardValue = {2,3,4,5,6,7,8,9,10,11,12,13,14};
    public Deck() {
        Cards = new ArrayList<Card>();
        for(int i = 0; i < Suits.length; i++)
        {
            for(int j = 0;j < Ranks.length;j++)
            {
                //Image image = new Image(Ranks[j] + Suits[i] + ".png");
                ImageView imageView = new ImageView(new Image(Ranks[j] + Suits[i] + ".png"));
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                Card c =new Card(Suits[i],Ranks[j],imageView,cardValue[j]);
                Cards.add(c);
            }
        }
        //Collections.shuffle(Cards);
    }

    public Card randomCard()
    {
        Random rand = new Random();
        int x = rand.nextInt(numberCard);
        Card c = Cards.get(x);
        Cards.remove(x);
        numberCard--;
        //System.out.println(x);
        return c;
    }

    public List<Card> getCards() {
        return Cards;
    }

}
