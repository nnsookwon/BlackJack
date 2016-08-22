
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Hand
{
    private ArrayList<Card> hand;
    private int handValue; 
    private boolean hasAce;
    
    public Hand()
    {
        hand = new ArrayList<Card>();
        handValue = 0;    
        hasAce = false;
    }   

       
    public void addCardToHand(Card c)
    {
        hand.add(c);
        
        calculateHandValue();
    }
    
    public void calculateHandValue()
    {
        int value =0;
        for (Card c: hand)
        {
            value+=c.getValue();
            if (c.getName().equals("A"))
            {
               hasAce=true;
            }
        }
        
        if(hasAce && value<12)
        value+=10;
        hasAce=false;
        handValue=value;
    }
  
    
    public int getHandValue()
    {        
        return handValue;
    }
    
    public boolean isBust()
    {
        return (handValue>21);
    }
    
    public Card getCard(int n)
    {
        return hand.get(n);
    }
    
    public int numOfCards()
    {
        return hand.size();
    }
    
    public void resetHand()
    {
        hand.clear();
    }
}
