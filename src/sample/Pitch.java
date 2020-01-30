package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.security.KeyStore;
import java.util.*;


public class Pitch extends Application implements DealerType{
    Scene scene;
    Stage primaryStagenext;
    Stage primaryStagemain;
    BorderPane boardpane;
    HBox hboxBottom;
    HBox hboxTop;
    HBox hboxCenter;
    VBox vboxLeft;
    VBox vboxRight;
    VBox vboxbidding;
    VBox vboxscoring;
    HBox hboxbiddingScore;
    Button biddingYou;
    Button biddingNorth;
    Button biddingWest;
    Button biddingEast;
    Button biddingContinue;
    Button button = new Button("Continue");
    Image image;
    ComboBox<Integer>bidding;
    boolean player2check = false;
    boolean player1check = false;
    Player player1 = new Player(false,"You");
    AIPlayer player2 = new AIPlayer(true,"North");
    AIPlayer player3 = new AIPlayer(true,"West");
    AIPlayer player4 = new AIPlayer(true,"East");
    BiddingAI biddingAI = new BiddingAI();
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Card> player1Score = new ArrayList<>();
    ArrayList<Card> player2Score = new ArrayList<>();
    ArrayList<Card> player3Score = new ArrayList<>();
    ArrayList<Card> player4Score = new ArrayList<>();
    TreeMap<Card,Integer> highAndLow = new TreeMap<Card,Integer>(new highestCard());
    TreeMap<Integer,Integer> gameScore = new TreeMap<>();
    public Integer number;
    public Integer maxBidder;
    public Integer cardCounter = 6;
    private Integer playerPlaying;
    //public Integer roundCounter = 1;
    public Integer highest = 0;
    private Integer p1Score = 0;
    private Integer p2Score = 0;
    private Integer p3Score = 0;
    private Integer p4Score = 0;
    private String max;
    Integer highestId;
    Integer i;
    Card highestCard;
    PitchDealer dealer = new PitchDealer();
    //HashMap<Card, Integer> dealerHand = new HashMap<>();
    //NavigableMap nmap = dealerHand.descendingMap();
    @Override
    public Dealer createDealer() {
        return dealer;
    }
    public void start(Stage primaryStage){
        //createDealer();
        boardpane = new BorderPane();
        hboxBottom = new HBox();
        hboxTop = new HBox();
        hboxbiddingScore = new HBox();
        vboxRight = new VBox();
        vboxLeft = new VBox();
        vboxbidding = new VBox();
        vboxscoring = new VBox();
        hboxCenter = new HBox();
        biddingYou = new Button();
        biddingNorth = new Button();
        biddingEast = new Button();
        biddingWest = new Button();
        biddingContinue = new Button("Set Bid");
        image = new Image("bg-2.jpg");

        //BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        boardpane.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        Label label = new Label("You   North   West   East");
        bidding = new ComboBox<>();
        bidding.getItems().addAll(0,2,3,4,5);
        hboxbiddingScore.getChildren().addAll(biddingYou,biddingNorth,biddingWest,biddingEast);
        hboxbiddingScore.setSpacing(15.0);
        hboxbiddingScore.setAlignment(Pos.CENTER);
        hboxbiddingScore.setStyle("-fx-background-color: transparent");
        vboxbidding.getChildren().addAll(label,hboxbiddingScore,bidding,biddingContinue);
        hboxBottom.setAlignment(Pos.CENTER);
        hboxTop.setAlignment(Pos.CENTER);
        vboxLeft.setAlignment(Pos.CENTER);
        vboxRight.setAlignment(Pos.CENTER);
        vboxbidding.setAlignment(Pos.CENTER);
        hboxCenter.setAlignment(Pos.CENTER);
        vboxbidding.setSpacing(10.0);
        vboxbidding.setStyle("-fx-background-color: transparent");
        hboxBottom.setDisable(true);
        hboxTop.setDisable(true);
        vboxLeft.setDisable(true);
        vboxRight.setDisable(true);
        hboxCenter.setDisable(true);

        if (number >= 2) {
            player1.setHand(createDealer().dealHand());
            player2.setHand(createDealer().dealHand());
            player2.setBiddingValue(biddingAI.BiddingAI(player2));
            player3.setBiddingValue(0);
            player4.setBiddingValue(0);

            biddingContinue.setOnAction(e ->
            {
                player1.setBiddingValue(bidding.getValue());
                biddingYou.setText(player1.getBiddingValue().toString());
                bidding.setDisable(true);
                biddingContinue.setDisable(true);
                maxBidder = Math.max(Math.max(Math.max(player1.getBiddingValue(), player2.getBiddingValue()), player3.getBiddingValue()),player4.getBiddingValue());
                if(player1.getBiddingValue().equals(maxBidder))
                {
                    max = "p1";
                    highestBidder(1);
                    player1.setPlayerTurn(true);
                }
                else if(player2.getBiddingValue().equals(maxBidder))
                {
                    max = "p2";
                    highestBidder(2);
                    player2.setPlayerTurn(true);
                }
                else if(player3.getBiddingValue().equals(maxBidder))
                {
                    max = "p3";
                    highestBidder(3);
                    player3.setPlayerTurn(true);
                }
                else if(player4.getBiddingValue().equals(maxBidder))
                {
                    max = "p4";
                    highestBidder(4);
                    player4.setPlayerTurn(true);
                }
                if (player1.getBiddingValue().equals(0) && player2.getBiddingValue().equals(0) && player3.getBiddingValue().equals(0) && player4.getBiddingValue().equals(0)) {
                    start(primaryStage);
                }
            });
                for(Card c : player1.getHand())
                {
                    hboxBottom.getChildren().addAll(c.getImageCard());
                }
            i = 0;
            for(Card c : player2.getHand())
            {
              i++;
              ImageView backimg = new ImageView(new Image("gray_back" + i.toString() + ".png"));
                backimg.setFitHeight(100);
                backimg.setFitWidth(100);
                backimg.setPreserveRatio(true);
              hboxTop.getChildren().add(backimg);
            }
            boardpane.setBottom(hboxBottom);
            boardpane.setTop(hboxTop);
        } if (number >= 3) {
            //player3.setHand(dealer.dealHand());
            player3.setHand(createDealer().dealHand());
            player3.setBiddingValue(biddingAI.BiddingAI(player3));
            player4.setBiddingValue(0);
            i = 0;
            for(Card c : player3.getHand())
            {
                i++;
                ImageView backimg = new ImageView(new Image("gray_back" + i.toString() + ".png"));
                backimg.setFitHeight(100);
                backimg.setFitWidth(100);
                backimg.setPreserveRatio(true);
                vboxLeft.getChildren().add(backimg);
            }
            boardpane.setLeft(vboxLeft);
        } if (number == 4){
            player4.setHand(createDealer().dealHand());
            player4.setBiddingValue(biddingAI.BiddingAI(player4));
            i = 0;
            for(Card c : player4.getHand())
            {
                i++;
                ImageView backimg = new ImageView(new Image("gray_back" + i.toString() + ".png"));
                backimg.setFitHeight(100);
                backimg.setFitWidth(100);
                backimg.setPreserveRatio(true);
                vboxRight.getChildren().add(backimg);
            }
            boardpane.setRight(vboxRight);
        }
        playerPlaying = number;
        boardpane.setCenter(vboxbidding);
        scene = new Scene(boardpane,1000,800);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStagenext = primaryStage;
        primaryStagemain = primaryStage;
        primaryStage.show();
    }
    public void highestBidder(int i)
    {
        dealer.setHighestBidder(i);
        biddingNorth.setText(player2.getBiddingValue().toString());
        biddingWest.setText(player3.getBiddingValue().toString());
        biddingEast.setText(player4.getBiddingValue().toString());
        biddingContinue.setDisable(false);
        biddingContinue.setText("Continue");
        biddingContinue.setOnAction(e -> biddingDone());
    }
    public void biddingDone() {
        vboxbidding.getChildren().clear();
        boardpane.setCenter(hboxCenter);
        playerTurn();

    }

    public void playerTurn()
    {
        if(player1.isPlayerTurn())
        {
                for (Card c : player1.getHand()) {
                    hboxBottom.setDisable(false);
                    if(player1check)
                        break;
                    else
                        c.getImageCard().setOnMouseClicked(e -> player1(c));
                }

        }
        else if(player2.isPlayerTurn())
        {
            player2Turn();
        }
        //if(number >= 3)
       // {
        else if(player3.isPlayerTurn())
            {
                player3Turn();
            }
        else if(player4.isPlayerTurn())
        {
            player4Turn();
        }

    }

    private void player1(Card c)
    {

        if(playerPlaying.equals(number) && cardCounter != 6)
        {
            hboxBottom.getChildren().remove(c.getImageCard());
            hboxCenter.getChildren().add(c.getImageCard());
            dealer.setRoundCard(c.getSuit());
            if(c.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(c.getCardValue());
            }
            hand.add(c);
            highest = highestCard(1);
            player1.getHand().remove(c);
            playerPlaying--;
            player1check = true;
            player1.setPlayerTurn(false);
            if(number >= 3)
                player3.setPlayerTurn(true);
            else
                player2.setPlayerTurn(true);
            hboxBottom.setDisable(true);
            playerTurn();
        }
        else if(playerPlaying.equals(number) && cardCounter == 6)
        {
            hboxBottom.getChildren().remove(c.getImageCard());
            hboxCenter.getChildren().add(c.getImageCard());
            dealer.setTrumpSuit(c.getSuit());
            dealer.setRoundCard(c.getSuit());
            dealer.setTrumpUsed(true);
            dealer.setTrumpHigh(c.getCardValue());
            hand.add(c);
            highest = highestCard(1);
            player1.getHand().remove(c);
            playerPlaying--;
            player1check = true;
            player1.setPlayerTurn(false);
            if(number >= 3)
                player3.setPlayerTurn(true);
            else
                player2.setPlayerTurn(true);
            hboxBottom.setDisable(true);
            playerTurn();
        }
        else if (playerPlaying < number && playerPlaying > 1)
        {
            hboxBottom.getChildren().remove(c.getImageCard());
            hboxCenter.getChildren().add(c.getImageCard());
            hboxBottom.setDisable(true);
            if(c.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(c.getCardValue());
            }
            player1.setPlayerTurn(false);
            if(number >= 3)
                player3.setPlayerTurn(true);
            else
                player2.setPlayerTurn(true);
            playerPlaying--;
            hand.add(c);
            highest = highestCard(1);
            player1check = true;
            player1.getHand().remove(c);
            playerTurn();
        }
        else if(playerPlaying == 1)
        {
            hboxBottom.getChildren().remove(c.getImageCard());
            hboxCenter.getChildren().add(c.getImageCard());
            hboxBottom.setDisable(true);
            if(c.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(c.getCardValue());
            }
            player1.setPlayerTurn(false);
            cardCounter--;
            playerPlaying--;
            hand.add(c);
            highest = highestCard(1);
            player1check = true;
            player1.getHand().remove(c);
            higherHand();
        }

    }
    private void player2Turn()
    {

        if(playerPlaying.equals(number) && cardCounter != 6)
        {
            Card c = player2.AIfirst(player2,dealer.getTrumpSuit());
            hboxTop.getChildren().remove(0);
            hboxCenter.getChildren().add(c.getImageCard());
            //System.out.println("Card " + c.getSuit() + c.getRank());
            dealer.setRoundCard(c.getSuit());
            if(c.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(c.getCardValue());
            }
            hand.add(c);
            highest = highestCard(2);
            //System.out.println("Size " + player2.getHand().size());
            //System.out.println("Card " + c.getSuit() + c.getRank());
            player2.getHand().remove(c);
            playerPlaying--;
            player2.setPlayerTurn(false);
            if(number == 4)
                player4.setPlayerTurn(true);
            else
                player1.setPlayerTurn(true);
            //player2check = true;
            playerTurn();
        }
        else if(playerPlaying.equals(number) && cardCounter == 6)
        {
            //hboxCenter.getChildren().clear();
            Card c = player2.AITrump(player2);
            hboxTop.getChildren().remove(0);
            hboxCenter.getChildren().add(c.getImageCard());
            dealer.setTrumpSuit(c.getSuit());
            dealer.setRoundCard(c.getSuit());
            dealer.setTrumpUsed(true);
            dealer.setTrumpHigh(c.getCardValue());
            hand.add(c);
            highest = highestCard(2);
            player2.getHand().remove(c);
            playerPlaying--;
            player2.setPlayerTurn(false);
            if(number == 4)
                player4.setPlayerTurn(true);
            else
                player1.setPlayerTurn(true);
            //player2check = true;
            playerTurn();
        }
        else if(playerPlaying < number && playerPlaying > 1)
        {
            Card aIreturn = player2.AIreturn(player2,dealer.getTrumpSuit(),dealer.getRoundCard(),highestCard.getCardValue(),dealer.getTrumpHigh(),dealer.isTrumpUsed());
            hboxTop.getChildren().remove(0);
            hboxCenter.getChildren().add(aIreturn.getImageCard());
            if(aIreturn.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(aIreturn.getCardValue());
            }
            player2.setPlayerTurn(false);
            if(number == 4)
                player4.setPlayerTurn(true);
            else
                player1.setPlayerTurn(true);
            playerPlaying--;
            hand.add(aIreturn);
            highest = highestCard(2);
            player2.getHand().remove(aIreturn);
            playerTurn();
           // player2check = true;
            //higherHand();
        }
        else if(playerPlaying == 1)
        {
            Card aIreturn = player2.AIreturn(player2,dealer.getTrumpSuit(),dealer.getRoundCard(),highestCard.getCardValue(),dealer.getTrumpHigh(),dealer.isTrumpUsed());
            hboxTop.getChildren().remove(0);
            hboxCenter.getChildren().add(aIreturn.getImageCard());
            if(aIreturn.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(aIreturn.getCardValue());
            }
            player2.setPlayerTurn(false);
            cardCounter--;
            playerPlaying--;
            hand.add(aIreturn);
            highest = highestCard(2);
            player2.getHand().remove(aIreturn);
            // player2check = true;
            higherHand();
        }
    }

    private void player3Turn()
    {
        //Highest bidder
        if(playerPlaying.equals(number) && cardCounter == 6)
        {
            //hboxCenter.getChildren().clear();
            Card c = player3.AITrump(player3);
            vboxLeft.getChildren().remove(0);
            hboxCenter.getChildren().add(c.getImageCard());
            dealer.setTrumpSuit(c.getSuit());
            dealer.setRoundCard(c.getSuit());
            dealer.setTrumpUsed(true);
            dealer.setTrumpHigh(c.getCardValue());
            hand.add(c);
            highest = highestCard(3);
            player3.getHand().remove(c);
            playerPlaying--;
            player2.setPlayerTurn(true);
            player3.setPlayerTurn(false);
            playerTurn();
        }
        else if(playerPlaying.equals(number) && cardCounter != 6)
        {
            Card c = player3.AIfirst(player3,dealer.getTrumpSuit());
            vboxLeft.getChildren().remove(0);
            hboxCenter.getChildren().add(c.getImageCard());
            dealer.setRoundCard(c.getSuit());
            if(c.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(c.getCardValue());
            }
            hand.add(c);
            highest = highestCard(3);
            player3.getHand().remove(c);
            playerPlaying--;
            player3.setPlayerTurn(false);
            player2.setPlayerTurn(true);
            playerTurn();
        }
        else if(playerPlaying < number && playerPlaying > 1)
        {
            Card aIreturn = player3.AIreturn(player3,dealer.getTrumpSuit(),dealer.getRoundCard(),highestCard.getCardValue(),dealer.getTrumpHigh(),dealer.isTrumpUsed());
            vboxLeft.getChildren().remove(0);
            hboxCenter.getChildren().add(aIreturn.getImageCard());
            if(aIreturn.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(aIreturn.getCardValue());
            }
            player3.setPlayerTurn(false);
            player2.setPlayerTurn(true);
            playerPlaying--;
            hand.add(aIreturn);
            highest = highestCard(3);
            player3.getHand().remove(aIreturn);
            playerTurn();
            // player2check = true;
            //higherHand();
        }
        else if(playerPlaying == 1)
        {
            Card aIreturn = player3.AIreturn(player3,dealer.getTrumpSuit(),dealer.getRoundCard(),highestCard.getCardValue(),dealer.getTrumpHigh(),dealer.isTrumpUsed());
            vboxLeft.getChildren().remove(0);
            hboxCenter.getChildren().add(aIreturn.getImageCard());
            if(aIreturn.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(aIreturn.getCardValue());
            }
            player3.setPlayerTurn(false);
            playerPlaying--;
            cardCounter--;
            hand.add(aIreturn);
            highest = highestCard(3);
            player3.getHand().remove(aIreturn);
            higherHand();
        }
    }

    private void player4Turn()
    {
        //Highest bidder
        if(playerPlaying.equals(number) && cardCounter == 6)
        {
            //hboxCenter.getChildren().clear();
            Card c = player4.AITrump(player4);
            vboxRight.getChildren().remove(0);;
            hboxCenter.getChildren().add(c.getImageCard());
            dealer.setTrumpSuit(c.getSuit());
            dealer.setRoundCard(c.getSuit());
            dealer.setTrumpUsed(true);
            dealer.setTrumpHigh(c.getCardValue());
            hand.add(c);
            highest = highestCard(4);
            player4.getHand().remove(c);
            playerPlaying--;
            player1.setPlayerTurn(true);
            player4.setPlayerTurn(false);
            playerTurn();
        }
        else if(playerPlaying.equals(number) && cardCounter != 6)
        {
            Card c = player4.AIfirst(player4,dealer.getTrumpSuit());
            vboxRight.getChildren().remove(0);;
            hboxCenter.getChildren().add(c.getImageCard());
            dealer.setRoundCard(c.getSuit());
            if(c.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(c.getCardValue());
            }
            hand.add(c);
            highest = highestCard(4);
            player4.getHand().remove(c);
            playerPlaying--;
            player4.setPlayerTurn(false);
            player1.setPlayerTurn(true);
            playerTurn();
        }
        else if(playerPlaying < number && playerPlaying > 1)
        {
            Card aIreturn = player4.AIreturn(player4,dealer.getTrumpSuit(),dealer.getRoundCard(),highestCard.getCardValue(),dealer.getTrumpHigh(),dealer.isTrumpUsed());
            vboxRight.getChildren().remove(0);;
            hboxCenter.getChildren().add(aIreturn.getImageCard());
            if(aIreturn.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(aIreturn.getCardValue());
            }
            player4.setPlayerTurn(false);
            player1.setPlayerTurn(true);
            playerPlaying--;
            hand.add(aIreturn);
            highest = highestCard(4);
            player4.getHand().remove(aIreturn);
            playerTurn();
            // player2check = true;
            //higherHand();
        }
        else if(playerPlaying == 1)
        {
            Card aIreturn = player4.AIreturn(player4,dealer.getTrumpSuit(),dealer.getRoundCard(),highestCard.getCardValue(),dealer.getTrumpHigh(),dealer.isTrumpUsed());
            vboxRight.getChildren().remove(0);;
            hboxCenter.getChildren().add(aIreturn.getImageCard());
            if(aIreturn.getSuit().equals(dealer.getTrumpSuit()))
            {
                dealer.setTrumpUsed(true);
                dealer.setTrumpHigh(aIreturn.getCardValue());
            }
            player4.setPlayerTurn(false);
            playerPlaying--;
            cardCounter--;
            hand.add(aIreturn);
            highest = highestCard(4);
            player4.getHand().remove(aIreturn);
            higherHand();
        }
    }

    private Integer highestCard(Integer p)
    {

        if(hand.size() == 1) {
            highestId = p;
            highestCard = hand.get(0);

        }
        if(hand.size() > 1)
        {
            for(Card c : hand)
            {
                if(dealer.isTrumpUsed())
                {
                    if(c.getSuit().equals(dealer.getTrumpSuit()) && highestCard.getSuit().equals(dealer.getTrumpSuit()))
                    {
                        if(c.getCardValue() > highestCard.getCardValue())
                        {
                            highestCard = c;
                            highestId = p;
                        }
                    }
                    if((c.getSuit().equals(dealer.getTrumpSuit())) && (!highestCard.getSuit().equals(dealer.getTrumpSuit())))
                    {
                        highestCard = c;
                        highestId = p;
                    }
                }
                else
                {
                    if(c.getSuit().equals(dealer.getRoundCard()) && highestCard.getSuit().equals(dealer.getRoundCard()))
                    {
                        if(c.getCardValue() > highestCard.getCardValue())
                        {
                            highestCard = c;
                            highestId = p;
                        }
                    }
                    if((c.getSuit().equals(dealer.getRoundCard())) && (!highestCard.getSuit().equals(dealer.getRoundCard())))
                    {
                        highestCard =c;
                        highestId = p;
                    }
                }
            }
        }
        return highestId;
    }


    private void higherHand()
    {
        Label highlabel = new Label("Player " + highest.toString() + " bags this");
        highlabel.setFont(Font.font(20));
        highlabel.setTextFill(Color.web("#B22222"));
        vboxbidding.getChildren().addAll(hboxCenter,highlabel,button);
        //hboxCenter.getChildren().clear();
        boardpane.setCenter(vboxbidding);
        //System.out.println("Card Counter " + cardCounter);
        //System.out.println("Hand " + hand.size());
        if(highest == 1)
        {
            player1Score.addAll(hand);
            hand.clear();
            dealer.setTrumpUsed(false);
            playerPlaying = number;
            player2.setPlayerTurn(false);
            player3.setPlayerTurn(false);
            player4.setPlayerTurn(false);
            player1.setPlayerTurn(true);
            //player2check = false;
            buttonaction();
        }
        //else if(dealerHand.firstEntry().getValue() == 2)
        else if(highest == 2)
        {
            player2Score.addAll(hand);
            hand.clear();
            playerPlaying = number;
            dealer.setTrumpUsed(false);
            player1.setPlayerTurn(false);
            player3.setPlayerTurn(false);
            player4.setPlayerTurn(false);
            player2.setPlayerTurn(true);
            //player2check = false;
            buttonaction();
        }
        else if (highest == 3)
        {
            player3Score.addAll(hand);
            hand.clear();
            playerPlaying = number;
            dealer.setTrumpUsed(false);
            player1.setPlayerTurn(false);
            player2.setPlayerTurn(false);
            player4.setPlayerTurn(false);
            player3.setPlayerTurn(true);
            //player2check = false;
            buttonaction();
        }
        else if (highest == 4)
        {
            player4Score.addAll(hand);
            hand.clear();
            playerPlaying = number;
            dealer.setTrumpUsed(false);
            player1.setPlayerTurn(false);
            player2.setPlayerTurn(false);
            player3.setPlayerTurn(false);
            player4.setPlayerTurn(true);
            //player2check = false;
            buttonaction();
        }
    }
    public void buttonaction() {
        if (cardCounter > 0) {
            button.setOnAction(actionEvent -> continuebutton());
        }
        if(cardCounter == 0)
        {
            button.setOnAction(e -> {
                //roundCounter++;
                scoring();
                if(p1Score >= 7 || p2Score >= 7 || p3Score >= 7 || p4Score >= 7)
                {
                    Label title = new Label("Hand Over");
                    title.setFont(Font.font(20));
                    title.setTextFill(Color.web("#B22222"));
                    Label label = new Label("You   North   West   East");
                    label.setFont(Font.font(20));
                    label.setTextFill(Color.web("#B22222"));
                    Label scorelabel = new Label("Score     " + p1Score.toString() + "     " + p2Score.toString() + "      " + p3Score.toString() + "      " + p4Score.toString());
                    scorelabel.setFont(Font.font(20));
                    scorelabel.setTextFill(Color.web("#B22222"));
                    Label winner1 = new Label();
                    Label winner2 = new Label();
                    Label winner3 = new Label();
                    Label winner4 = new Label();
                    winner1.setFont(Font.font(20));
                    winner1.setTextFill(Color.web("#B22222"));
                    winner2.setFont(Font.font(20));
                    winner2.setTextFill(Color.web("#B22222"));
                    winner3.setFont(Font.font(20));
                    winner3.setTextFill(Color.web("#B22222"));
                    winner4.setFont(Font.font(20));
                    winner4.setTextFill(Color.web("#B22222"));
                    if(p1Score >= 7)
                        winner1.setText("Winner : " + player1.getName());
                    if(p2Score >= 7)
                        winner2.setText("Winner : " + player2.getName());
                    if(p3Score >= 7)
                        winner3.setText("Winner : " + player3.getName());
                    if(p4Score >= 7)
                        winner4.setText("Winner : " + player4.getName());
                    Button startagain = new Button("Start Again");
                    Button exit = new Button("Exit");
                    vboxbidding.getChildren().clear();
                    vboxbidding.getChildren().addAll(title, label, scorelabel, winner1, winner2, winner3, winner4,startagain,exit);
                    vboxbidding.setSpacing(5.0);
                    vboxbidding.setAlignment(Pos.CENTER);
                    //hboxCenter.setDisable(true);
                    boardpane.setCenter(vboxbidding);
                    startagain.setOnAction(actionEvent -> exitbutton());
                    exit.setOnAction(actionEvent -> primaryStagenext.close());
                }
                else {
                    Label title = new Label("Hand Over");
                    title.setFont(Font.font(20));
                    title.setTextFill(Color.web("#B22222"));
                    Label label = new Label("You   North   West   East");
                    label.setFont(Font.font(20));
                    label.setTextFill(Color.web("#B22222"));
                    Label scorelabel = new Label("Score     " + p1Score.toString() + "     " + p2Score.toString() + "      " + p3Score.toString() + "      " + p4Score.toString());
                    scorelabel.setFont(Font.font(20));
                    scorelabel.setTextFill(Color.web("#B22222"));
                    Button next = new Button("Next");
                    Button start = new Button("Start");
                    Button exit = new Button("Exit");
                    HBox round = new HBox();
                    round.getChildren().addAll(next, start, exit);
                    round.setAlignment(Pos.CENTER);
                    round.setSpacing(10.0);
                    vboxscoring.getChildren().addAll(title, label, scorelabel, round);
                    vboxscoring.setSpacing(5.0);
                    vboxscoring.setAlignment(Pos.CENTER);
                    boardpane.setCenter(vboxscoring);
                    next.setOnAction(actionEvent -> next());
                    start.setOnAction(actionEvent -> startbutton());
                    exit.setOnAction(actionEvent -> exitbutton());
                }
            });
        }
    }
//    public void ba() {
//
//    }

    private void scoring()
    {
        Integer totalSize = number * 6;
        Integer game1 = 0;
        Integer game2 = 0;
        Integer game3 = 0;
        Integer game4 = 0;
        if(number >= 2) {
            if (player1Score.size() == totalSize)
                p1Score++;
            if (player1Score.size() > 0) {
                for (Card c : player1Score) {
                    if (c.getSuit().equals(dealer.getTrumpSuit()))
                        highAndLow.put(c, 1);
                    if (c.getSuit().equals(dealer.getTrumpSuit()) && c.getCardValue() == 11)
                        p1Score++;
                    if (c.getCardValue() == 10)
                        game1 += 10;
                    if (c.getCardValue() == 14)
                        game1 += 4;
                    if (c.getCardValue() == 13)
                        game1 += 3;
                    if (c.getCardValue() == 12)
                        game1 += 2;
                    if (c.getCardValue() == 11)
                        game1 += 1;

                }
            }
            if (player2Score.size() == totalSize)
                p2Score++;
            if (player2Score.size() > 0) {
                for (Card c : player2Score) {
                    //System.out.println("Card : " + c.getSuit()+c.getCardValue());
                    if (c.getSuit().equals(dealer.getTrumpSuit()))
                        highAndLow.put(c, 2);
                    if (c.getSuit().equals(dealer.getTrumpSuit()) && c.getCardValue() == 11)
                        p2Score++;
                    if (c.getCardValue() == 10)
                        game2 += 10;
                    if (c.getCardValue() == 14)
                        game2 += 4;
                    if (c.getCardValue() == 13)
                        game2 += 3;
                    if (c.getCardValue() == 12)
                        game2 += 2;
                    if (c.getCardValue() == 11)
                        game2 += 1;
                }
            }
            if (player3Score.size() == totalSize)
                p3Score++;
            if (player3Score.size() > 0) {
                for (Card c : player3Score) {
                    if (c.getSuit().equals(dealer.getTrumpSuit()))
                        highAndLow.put(c, 3);
                    if (c.getSuit().equals(dealer.getTrumpSuit()) && c.getCardValue() == 11)
                        p3Score++;
                    if (c.getCardValue() == 10)
                        game3 += 10;
                    if (c.getCardValue() == 14)
                        game3 += 4;
                    if (c.getCardValue() == 13)
                        game3 += 3;
                    if (c.getCardValue() == 12)
                        game3 += 2;
                    if (c.getCardValue() == 11)
                        game3 += 1;

                }
            }
            if (player4Score.size() == totalSize)
                p4Score++;
            if (player4Score.size() > 0) {
                for (Card c : player4Score) {
                    if (c.getSuit().equals(dealer.getTrumpSuit()))
                        highAndLow.put(c, 4);
                    if (c.getSuit().equals(dealer.getTrumpSuit()) && c.getCardValue() == 11)
                        p4Score++;
                    if (c.getCardValue() == 10)
                        game4 += 10;
                    if (c.getCardValue() == 14)
                        game4 += 4;
                    if (c.getCardValue() == 13)
                        game4 += 3;
                    if (c.getCardValue() == 12)
                        game4 += 2;
                    if (c.getCardValue() == 11)
                        game4 += 1;

                }
            }
        }
                    if(number == 2)
                    {
                        gameScore.put(game1,1);
                        gameScore.put(game2,2);
                    }
                    if(number == 3)
                        gameScore.put(game3,3);
                    if(number == 4)
                        gameScore.put(game4,4);

                    if(highAndLow.lastEntry().getValue() == 1)
                        p1Score++;
                    if(highAndLow.lastEntry().getValue() == 2)
                        p2Score++;
                    if(highAndLow.lastEntry().getValue() == 3)
                        p3Score++;
                    if(highAndLow.lastEntry().getValue() == 4)
                        p4Score++;
            if(highAndLow.firstEntry().getValue() == 1)
                p1Score++;
            if(highAndLow.firstEntry().getValue() == 2)
                p2Score++;
            if(highAndLow.firstEntry().getValue() == 3)
                p3Score++;
            if(highAndLow.firstEntry().getValue() == 4)
                p4Score++;
            if(highAndLow.lastEntry().getValue() == 1)
                p1Score++;
            if(highAndLow.lastEntry().getValue() == 2)
                p2Score++;
            if(highAndLow.lastEntry().getValue() == 3)
                p3Score++;
            if(highAndLow.lastEntry().getValue() == 4)
                p4Score++;


        if(player1.getBiddingValue() > p1Score && max.equals("p1"))
        {
            p1Score = -player1.getBiddingValue();
        }
        if(player2.getBiddingValue() > p2Score && max.equals("p2"))
        {
            p2Score = -player2.getBiddingValue();
        }
        if(player3.getBiddingValue() > p3Score && max.equals("p3"))
        {
            p3Score = -player3.getBiddingValue();
        }
        if(player4.getBiddingValue() > p4Score && max.equals("p4"))
        {
            p4Score = -player4.getBiddingValue();
        }
        player1Score.clear();
        player2Score.clear();
        player3Score.clear();
        player4Score.clear();
        highAndLow.clear();
    }

    private void next()
    {
        player1.setPlayerTurn(false);
        player2.setPlayerTurn(false);
        player3.setPlayerTurn(false);
        player4.setPlayerTurn(false);
        player1check = false;
        //player2check = false;
        cardCounter = 6;
        try {
            start(primaryStagenext);
        }catch (Exception e){
            //
        }
        //start(primaryStagenext);

    }
    private void startbutton()
    {
        player1.setPlayerTurn(false);
        player2.setPlayerTurn(false);
        player3.setPlayerTurn(false);
        player4.setPlayerTurn(false);
        player1check = false;
        //player2check = false;
        cardCounter = 6;
        p1Score = 0;
        p2Score = 0;
        p3Score = 0;
        p4Score = 0;

        try {
            start(primaryStagenext);
        }catch (Exception e){
            //
        }

    }
    private void exitbutton()
    {
        mainPageGUI a = new mainPageGUI();
        try {
            a.start(primaryStagemain);
        }catch (Exception e){
            //
        }
    }

    private void continuebutton()
    {
        hboxCenter.getChildren().clear();
        vboxbidding.getChildren().clear();
        boardpane.setCenter(hboxCenter);
        playerTurn();
    }
}


class highestCard implements Comparator<Card>{
    @Override

    public int compare(Card o1, Card o2) {
        if(o1.getCardValue() < o2.getCardValue())
        {
            return 1;
        }
        else
            return -1;
    }
}