
/**
 * Write a description of class Dealer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dealer extends Hand
{
    Dealer()
    {
        super();
    }
    
    public boolean softStand()
    {
        return (getHandValue()>=17);
    }
    
    
    
    
}