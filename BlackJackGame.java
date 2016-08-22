
/**
 * Write a description of class BlackJackGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.util.*;

public class BlackJackGame 
{

    private Player player;   
    private Dealer dealer;
    
    private Deck deck;
    private int deckNum;//number of decks

    public BlackJackGame()
    {
        player= new Player();               
        dealer = new Dealer();
        deckNum=2;
        deck = new Deck(2);
        deck.shuffleDeck();
        deck.shuffleDeck();
        deck.shuffleDeck();        
    }
    
    public BlackJackGame(int d) //number of decks
    {
        player= new Player();               
        dealer = new Dealer();
        deckNum = d;
        deck = new Deck(deckNum);
        for(int i=0; i<deckNum*2; i++)        
            deck.shuffleDeck();
               
    }

    
    public void deal()
    {
        dealCardTo(player);
        dealCardTo(dealer);
        dealCardTo(player);
        dealCardTo(dealer);
    }

    public void dealCardTo(Hand p)
    {
        if (deck.getDeckSize()<deckNum*52){            
            deck = new Deck(deckNum);
            for(int i=0; i<deckNum*2; i++)        
                deck.shuffleDeck();
        }
        p.addCardToHand(deck.dealCard());
    }    
   
    public Player getPlayer()
    {
        return player;
    }

    public Dealer getDealer()
    {
        return dealer;
    }
    
    public void setPlayer(Player p)
    {
        player = p;
        
    }

    public int checkWinner() 
    //pre-cond: player and dealer not bust
    //1 = winner, 0 = tie, -1 = loser
    {
        if (player.getHandValue()>dealer.getHandValue())
            return 1;

        else if (player.getHandValue()==dealer.getHandValue())
            return 0;

        else
            return -1;
    }
   
    public void endOfRound()
    {
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
        else if (dealer.isBust())
        {
            player.addMoney(player.getBet()*2);
            JOptionPane.showMessageDialog(null,
                "Dealer bust!",
                "Winner!",
                JOptionPane.PLAIN_MESSAGE);
        }
        else
        {
            if (checkWinner()>0)
            {
                player.addMoney(player.getBet()*2);
                JOptionPane.showMessageDialog(null,
                    "Winner!",
                    "Congratulations!",
                    JOptionPane.PLAIN_MESSAGE);
            }
            else if (checkWinner()==0)
            {
                player.addMoney(player.getBet()) ;

                JOptionPane.showMessageDialog(null,
                    "It's a tie!",
                    "Tie!",
                    JOptionPane.PLAIN_MESSAGE);
            }
            else
            {

                JOptionPane.showMessageDialog(null,
                    "Sorry! You lost!",
                    "Loser!",
                    JOptionPane.PLAIN_MESSAGE);
            }
        }
        player.resetHand();
        dealer.resetHand();
    }

    

}