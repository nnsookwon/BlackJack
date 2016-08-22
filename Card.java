
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card
{
    private String name; // A,2,3,4,5,6,7,8,9,10,J,Q,K
    private String suit;  // spades, hearts, diamonds, clubs
    
    Card ()
    {
        name = "A";
        suit = "spades";
    }
    
    Card(String v, String s)
    {
        name = v;
        suit = s;
    }
    
  
    
    public String getName()
    {
        return name;
    }
    
    public int getValue()
    {
        if (name.equals("J") || name.equals("Q") ||
        name.equals("K"))
        return 10;
        
        if (name.equals("A")) // 11 and 1 coded in Hand class
        return 1;
        
        return Integer.parseInt(name);
    }
    
    public String getSuit()
    {
        return suit;
    }
    
    public String toString()
    {
        return "value: " + name + "\nsuit: " + suit;

    }
    
    
}
