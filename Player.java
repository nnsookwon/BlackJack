
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Player extends Hand
{
   
    private int money;
    private int bet;
    
    Player()
    {
        super();
        money = 5000;
        bet = 0;
    }
    
    Player(int m)
    {
        super();
        money = m;
        bet = 0;
    }
    
    public void placeBet(int b)
    {
        bet = b;
    }
    
    public int getBet()
    {
        return bet;
    }
    
    public void addMoney(int m)
    {
        money+=m;
    }
    
    public void subtractMoney(int m)
    {
        money-=m;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    public boolean isBlackJack()
    {
        if (numOfCards()==2&&getHandValue() == 21)
        return true;
        return false;
    }
    
    public void doubleDown()
    {
        money-=bet;
        bet*=2;
        
    }
    
    public boolean canDoubleDown()
    {
        if (numOfCards()==2&&getHandValue()==11)
        return true;
        return false;
    }
  
    
    
}
