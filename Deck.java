
/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Deck
{
    private ArrayList<Card> cards = new ArrayList<Card>();
    String[] values = {"A", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "J", "Q", "K"};
    String[] suits = {"spades", "hearts", "diamonds", "clubs"};                               

    Deck()
    {
        for (String v: values)
        {
            for (String s: suits)
            {
                cards.add(new Card(v,s));
            }
        }
    }
    
    
    Deck(int n) 
    //n = sets of decks
    {
        for (int i = 0; i <n; i++)
        {
        for (String v: values)
        {
            for (String s: suits)
            {
                cards.add(new Card(v,s));
            }
        }
    }
    }

    public void shuffleDeck()
    {
        Random random = new Random();
        
        for (int i=0; i<150; i++)            
        {            
            int index1 = random.nextInt(cards.size()) ;       
            int index2 = random.nextInt(cards.size()) ;
            Card temp = (Card) cards.get( index1 );
            cards.set( index1 , cards.get( index2 ) );
            cards.set( index2, temp );
        }

    }
    
    public void printCards()
    {
        for(Card c: cards)
        System.out.println(c);
    }
    
    public Card dealCard()
    {
        if (cards.size()==0)
        return null;
        
        return cards.remove(0);
    }

    
    public void getACard()
    {
        int choice = (int) (Math.random()*cards.size());
        System.out.println(cards.get(choice));
    }
    
    public int getDeckSize()
    {
        return cards.size();
    }
}
