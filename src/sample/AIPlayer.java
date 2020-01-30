package sample;

import java.util.*;

//AI algo to choose which card to play. Use of min-max algo will be perfect here but we are restricted to use

public class AIPlayer extends Player{
    private List<Card> clubsList = new ArrayList<>();
    private List<Card> diamondsList = new ArrayList<>();
    private List<Card> heartsList = new ArrayList<>();
    private List<Card> spadesList = new ArrayList<>();
    private List<Card> trumpList = new ArrayList<>();
    //private Card lowestCard;
    Card returnCard = new Card();
    private Integer lowestCardValue;
    TreeSet<Card> lowest = new TreeSet<Card>(new lowestCard());
    TreeSet<Card> highest = new TreeSet<Card>(new highestCard());
    TreeMap<Integer,List> trump = new TreeMap<Integer,List>(Collections.reverseOrder());
    public AIPlayer(boolean playerType, String name) {
        super(playerType, name);
    }

    private void gettingList(Player player)
    {
        clubsList.clear();
        diamondsList.clear();
        heartsList.clear();
        spadesList.clear();
        trumpList.clear();

        for(Card c : player.getHand())
        {
            //lowest.add(c);
            //highest.add(c);
            if(c.getSuit().equals("Clubs"))
            {
                clubsList.add(c);
            }
            if(c.getSuit().equals("Diamonds"))
            {
                diamondsList.add(c);
            }
            if(c.getSuit().equals("Hearts"))
            {
                heartsList.add(c);
            }
            if(c.getSuit().equals("Spades"))
            {
                spadesList.add(c);
            }
        }
        clubsList.sort(Comparator.comparing(Card::getCardValue));
        diamondsList.sort(Comparator.comparing(Card::getCardValue));
        heartsList.sort(Comparator.comparing(Card::getCardValue));
        spadesList.sort(Comparator.comparing(Card::getCardValue));
        trump.put(clubsList.size(),clubsList);
        trump.put(diamondsList.size(),diamondsList);
        trump.put(heartsList.size(),heartsList);
        trump.put(spadesList.size(),spadesList);
    }
    public Card AITrump(Player player)
    {
        gettingList(player);
        trumpList = trump.firstEntry().getValue();
        trumpList.sort(Comparator.comparing(Card::getCardValue).reversed());
        returnCard = trumpList.get(0);
        trumpList.remove(0);
        return returnCard;
    }
    public Card AIfirst(Player player,String trumpSuit)
    {
        for(Card c : player.getHand())
        {
            if(c.getSuit().equals(trumpSuit))
            {
                continue;
                //highest.remove(c);
            }
            else
                highest.add(c);
        }
        trumpList.sort(Comparator.comparing(Card::getCardValue).reversed());
        if(trumpList.size() > 0)
        {
            returnCard = trumpList.get(0);
            trumpList.remove(0);
        }
        else
        {
            returnCard = highest.pollFirst();
        }
        highest.clear();
        return returnCard;
    }

    public Card AIreturn(Player player, String trumpSuit, String Suit, Integer higgestCard, Integer trumpHighest, boolean trumpUsed)
    {
        gettingList(player);
        setTrumpList(trumpSuit);
        trumpList.sort(Comparator.comparing(Card::getCardValue));
        for(Card c : player.getHand())
        {
            if(c.getSuit().equals(trumpSuit))
            {
                continue;
                //lowest.remove(c);
            }
            else
                lowest.add(c);
        }
        if(Suit.equals(trumpSuit)) {
            if (trumpList.size() > 0) {
                int index;
                index = nextHighest(trumpList, higgestCard);
                returnCard = trumpList.get(index);
                trumpList.remove(index);
            }
            else {

                returnCard = lowest.pollFirst();
            }
        }
        else
        {
            if(Suit.equals("Clubs"))
                returnCard = returnCard(clubsList,trumpList,higgestCard,trumpHighest,trumpUsed);
            if(Suit.equals("Diamonds"))
                returnCard = returnCard(diamondsList,trumpList,higgestCard,trumpHighest,trumpUsed);
            if(Suit.equals("Hearts"))
                returnCard = returnCard(heartsList,trumpList,higgestCard,trumpHighest,trumpUsed);
            if(Suit.equals("Spades"))
                returnCard = returnCard(spadesList,trumpList,higgestCard,trumpHighest,trumpUsed);
        }
        lowest.clear();
        return returnCard;
    }

    private void setTrumpList(String trumpSuit)
    {
        if(trumpSuit.equals("Clubs"))
            trumpList = clubsList;
        if(trumpSuit.equals("Diamonds"))
            trumpList = diamondsList;
        if(trumpSuit.equals("Hearts"))
            trumpList = heartsList;
        if(trumpSuit.equals("Spades"))
            trumpList = spadesList;
    }

    private Card returnCard(List<Card> list,List<Card> trumplist, Integer higgestValue, Integer trumpHighest, boolean trumpUsed)
    {
        if(list.size() > 0) {
            int index;
            index = nextHighest(list, higgestValue);
            if(trumpUsed)
            {
                if(trumplist.size() > 0 && trumplist.get(nextHighest(trumplist,trumpHighest)).getCardValue() > trumpHighest)
                    return trumplist.get(nextHighest(trumplist,trumpHighest));
                else
                    return list.get(0);
            }
            else
                return list.get(index);
        }
        else {
                if (trumplist.size() > 0 && trumplist.get(nextHighest(trumplist, trumpHighest)).getCardValue() > trumpHighest)
                    return trumplist.get(nextHighest(trumplist, trumpHighest));
                else
                    return lowest.pollFirst();
            }
    }

    private Integer nextHighest(List<Card> list, Integer higgestValue)
    {
        int i = -1;
        boolean found = false;
        for(Card c : list)
        {
            i++;
            if(higgestValue < c.getCardValue())
            {
                found = true;
                break;
            }
        }
        if(found)
            return i;
        else
            return  0;
    }
}

class lowestCard implements Comparator<Card>{
    @Override
    public int compare(Card o1, Card o2) {
        if(o1.getCardValue() > o2.getCardValue())
            return 1;
        else
            return -1;
    }
}
