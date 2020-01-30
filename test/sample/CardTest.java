package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card c = new Card ("Hearts","Jack",11);
    //Testing card Suit
    @Test
    public void Test1()
    {
        assertEquals("Hearts",c.getSuit());
    }

    //Testing card rank
    @Test
    public void Test2()
    {
        assertEquals("Jack",c.getRank());
    }

    //Testing card value
    @Test
    public void Test3()
    {
        assertEquals(11,c.getCardValue());
    }

    //Testing setvalue for card
    @Test
    public void Test4()
    {
        c.setCardValue(12);
        assertEquals(12,c.getCardValue());
    }

    //Testing for setvalue and default value
    @Test
    public void Test5()
    {
        assertEquals(11,c.getCardValue());
    }
}