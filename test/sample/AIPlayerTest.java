package sample;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {

    AIPlayer ai = new AIPlayer(true,"Peter");
    //Testing for playername
    @Test
    public void Test1()
    {
        assertEquals("Peter",ai.getName());
    }

    //Testing for playertype
    @Test
    public void Test2()
    {
        ai.setPlayerTurn(true);
        assertEquals(true,ai.isPlayerTurn());
    }

    //Testing for biddingValue
    @Test
    public void Test3()
    {
        ai.setBiddingValue(5);
        assertEquals(5,ai.getBiddingValue());
    }

    //Testing for setHand
    @Test
    public void Test4()
    {
        ai.setHand(null);
        assertNull(ai.getHand());
    }

    //Testing for setHand
    @Test
    public void Test5()
    {
        ArrayList<Card> i = new ArrayList<>();
        ai.setHand(i);
        assertEquals(0,ai.getHand().size());
    }
}