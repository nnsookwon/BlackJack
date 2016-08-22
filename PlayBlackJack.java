
/**
 * Write a description of class PlayBlackJack here.
 * 
 * @author (Nate Sookwongse) 
 * @version (May 31, 2013)
 *                  ~~~~~ Honesty Pledge ~~~~~
 *    I, Nate Sookwongse, pledge that this program is my own 
 *    independent work and conforms to Oxford Academy's
 *    Academic Honesty Guidelines.
 *    I used tutorials online to help me with the graphics portion of
 *    this program. *    
 *    JFrame tutorial: http://www.macs.hw.ac.uk/guidebook/?name=JFrame&page=1
 *    JOption tutorial: http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
 *    I also downloaded the card images from online. But the coding is my own.
 *    
 * */

import javax.swing.*;
public class PlayBlackJack

{
    public static void main (String [] args)
    {
        String[] options = {"Single Player",
                "Multi-Player"};

        JFrame dummyFrame = new JFrame("");
        dummyFrame.setVisible(false);
        dummyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //allows JOption dialog to show on top of BlueJ window

        int n = JOptionPane.showOptionDialog(dummyFrame,
                "Would you like by yourself, or with other human players?",
                "Ready to Play BlackJack?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title

        if (n==0)
            playSinglePlayerBlackJack();
        if(n==1)
            playMultiPlayerBlackJack();
    }

    public static void playSinglePlayerBlackJack()
    {
        new BlackJackGUI();
    }

    public static void playMultiPlayerBlackJack()
    {
        new BlackJackMultiGUI();
    }
}