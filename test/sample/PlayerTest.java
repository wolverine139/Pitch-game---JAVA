package sample;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player kahil = new Player(false,"Khali");
    //Testing for playername
    @Test
    public void Test1()
    {
        assertEquals("Khali",kahil.getName());
    }

    //Testing for playertype
    @Test
    public void Test2()
    {
        kahil.setPlayerTurn(true);
        assertEquals(true,kahil.isPlayerTurn());
    }

    //Testing for biddingValue
    @Test
    public void Test3()
    {
        kahil.setBiddingValue(5);
        assertEquals(5,kahil.getBiddingValue());
    }

    //Testing for setHand
    @Test
    public void Test4()
    {
        kahil.setHand(null);
        assertNull(kahil.getHand());
    }

    //Testing for setHand
    @Test
    public void Test5()
    {
        ArrayList<Card> i = new ArrayList<>();
        kahil.setHand(i);
        assertEquals(0,kahil.getHand().size());
    }
}