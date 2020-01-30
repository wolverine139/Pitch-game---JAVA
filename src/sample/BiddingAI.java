package sample;


public class BiddingAI {
    private Integer noClubs;
    private Integer noDiamonds;
    private Integer noHearts;
    private Integer noSpades;
    private Integer faceValue;
    private Integer bidding;

    public BiddingAI() {
    }

    public Integer BiddingAI(Player player) {
        faceValue = 0;
        bidding = 0;
        noClubs = 0;
        noDiamonds = 0;
        noHearts = 0;
        noSpades = 0;
        for(Card c : player.getHand())
        {
            if(c.getSuit().equals("Clubs"))
            {
                noClubs++;
            }
            if(c.getSuit().equals("Diamonds"))
            {
                noDiamonds++;
            }
            if(c.getSuit().equals("Hearts"))
            {
                noHearts++;
            }
            if(c.getSuit().equals("Spades"))
            {
                noSpades++;
            }
            faceValue += c.getCardValue();
        }
        for(int i = 3; i <= 6 ; i++) {
            if (noClubs == i || noSpades == i || noHearts == i || noDiamonds == i)
                bidding = i - 1;
        }
        if(faceValue >= 50 && bidding < 6 && (noClubs == 2 || noSpades == 2 || noHearts == 2 || noDiamonds == 2))
        {
            bidding = 2;
        }
        if(faceValue >= 60 && bidding < 5)
        {
            bidding++;
        }
        return bidding;
    }
}
