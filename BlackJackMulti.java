
/**
 * Write a description of class BlackJackMulti here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import javax.swing.*;
public class BlackJackMulti extends BlackJackGame
{
    // instance variables - replace the example below with your own
    private ArrayList<Player> players;
   // private int playerTurn;
    private boolean endTurn;
    private Player player1;
    private Player player2;   
    private Player player3;
    
    
    
    public BlackJackMulti()
    {
        super();
        player1 = new Player();
        player2= new Player();  
        player3= new Player();  
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
         //playerTurn = 0;
        
        endTurn=false;
        
    }
    
    public Player getPlayer(int n)
    {
        return players.get(n);
    }
    
    public int checkWinner(int n)
    {
        setPlayer(players.get(n));
        return checkWinner();
    }
    
   
    
    public void endOfRound() // player number
    {
       // Player player = players.get(n);
        
        if (getDealer().isBust())
        {
            for(Player p: players)
            {
                if(!p.isBust() && !p.isBlackJack())
                 p.addMoney(p.getBet()*2);
                 p.resetHand();
                }
                JOptionPane.showMessageDialog(null,
                "Dealer bust!",
                "Bust!",
                JOptionPane.PLAIN_MESSAGE);
        }
        
        else
        {
            for (Player p: players)
            {
                if(!p.isBust()&& !p.isBlackJack())
                {
                setPlayer(p);
                
                if (checkWinner()>0)
                p.addMoney(p.getBet()*2);
                
                else if(checkWinner()==0)
                p.addMoney(p.getBet());     
            }
                
                p.resetHand();
            }
            
            JOptionPane.showMessageDialog(null,
                    "End of Round",
                    "Done",
                    JOptionPane.PLAIN_MESSAGE);
        }
        getDealer().resetHand();
        
    }
    
    public void endTurn(int n) //player number
    {      
        Player player = players.get(n);
        
        if (player.isBlackJack())
        {
            player.addMoney((int)(player.getBet()*2.5));
            JOptionPane.showMessageDialog(null,
                "BlackJack!",
                "Winner!",
                JOptionPane.PLAIN_MESSAGE);
        }
        
        else if(player.isBust())
        {
            JOptionPane.showMessageDialog(null,
                    "Sorry! It's a bust!",
                    "Loser!",
                    JOptionPane.PLAIN_MESSAGE);
        }   
        
    }
    
    
}
