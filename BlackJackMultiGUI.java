
/*
 * Write a description of class BlackJackMultiGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class BlackJackMultiGUI implements ActionListener
{
    private BlackJackMulti game;
    private JFrame frame;
    private JButton bet;
    private JButton stay;
    private JButton hit;

    //private JPanel playerCards;
    private JPanel player1Cards;
    private JLabel player1Money;

    private JPanel dealerCards;

    private JPanel player2Cards;
    private JLabel player2Money;

    private JPanel player3Cards;
    private JLabel player3Money;

    private ArrayList<JPanel> cardPanels;
    private ArrayList<JLabel> playerTexts;

    private JComboBox cBox;

    private JPanel dealerFirstCardPanel;
    private JLabel dealerFirstCardLabel;
    ImageIcon backCardImage = new ImageIcon("cardImages/back.png");

    private javax.swing.Timer t;

    private int playerTurn;

    public BlackJackMultiGUI()
    {
        playerTurn = 0;
        game = new BlackJackMulti();       
        t = new javax.swing.Timer(800, this); //timer for dealer card delay

        frame = new JFrame("BlackJack");
        frame.setVisible(true);  
        frame.setSize(750,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.green.darker());

        hit = new JButton("Hit");
        hit.setBounds(170,350,75,25);
        contentPane.add(hit);
        hit.addActionListener(this);

        stay = new JButton("Stay");
        stay.setBounds(280,350,75,25);
        contentPane.add(stay);
        stay.addActionListener(this);

        bet = new JButton("Bet");
        bet.setBounds(390,350,75,25);
        contentPane.add(bet);
        bet.addActionListener(this);

        resetButtons();

        JLabel bet = new JLabel("Bet Amount: ");      
        bet.setBounds(510,350,75,25);
        contentPane.add(bet);

        String[] betChoices = {"50","100", "250", "500","1000","2000","5000"};
        cBox = new JComboBox(betChoices);      
        cBox.setBounds(590,350,75,25);
        contentPane.add(cBox);

        JLabel player1Text = new JLabel("Player 1 Cards");
        player1Text.setBounds(10,10,140,28);
        contentPane.add(player1Text);
        player1Text.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel player2Text = new JLabel("Player 2 Cards");
        player2Text.setBounds(10,175,140,28);
        contentPane.add(player2Text);
        player2Text.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel player3Text = new JLabel("Player 3 Cards");
        player3Text.setBounds(10,335,140,28);
        contentPane.add(player3Text);
        player3Text.setFont(new Font("Serif", Font.BOLD, 20));

        playerTexts = new ArrayList<JLabel>();
        playerTexts.add(player1Text);
        playerTexts.add(player2Text);
        playerTexts.add(player3Text);

        player1Money = new JLabel("Money: " + game.getPlayer(0).getMoney());
        player1Money.setBounds(10,35,125,25);
        contentPane.add(player1Money);
        player1Money.setFont(new Font("Serif", Font.PLAIN, 20));

        player2Money = new JLabel("Money: " + game.getPlayer(1).getMoney());
        player2Money.setBounds(10,200,125,25);
        contentPane.add(player2Money);
        player2Money.setFont(new Font("Serif", Font.PLAIN, 20));

        player3Money = new JLabel("Money: " + game.getPlayer(2).getMoney());
        player3Money.setBounds(10,365,125,25);
        contentPane.add(player3Money);
        player3Money.setFont(new Font("Serif", Font.PLAIN, 20));

        player1Cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));       
        player1Cards.setBounds(10,60,700,110);     
        player1Cards.setOpaque(false);
        contentPane.add(player1Cards);

        player2Cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));       
        player2Cards.setBounds(10,225,700,110);     
        player2Cards.setOpaque(false);
        contentPane.add(player2Cards);

        player3Cards = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));       
        player3Cards.setBounds(10,390,700,110);     
        player3Cards.setOpaque(false);
        contentPane.add(player3Cards);

        cardPanels = new ArrayList<JPanel>();
        cardPanels.add(player1Cards);
        cardPanels.add(player2Cards);
        cardPanels.add(player3Cards);

        JLabel dealerText = new JLabel("Dealer's Cards");
        dealerText.setBounds(10,500,150,25);       
        contentPane.add(dealerText);
        dealerText.setFont(new Font("Serif", Font.BOLD, 20));

        dealerCards = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));       
        dealerCards.setBounds(10,525,700,110);     
        dealerCards.setOpaque(false);
        contentPane.add(dealerCards);

        dealerFirstCardPanel = new JPanel();        
        dealerFirstCardLabel = new JLabel();
        dealerFirstCardPanel.add(dealerFirstCardLabel);

        indicateTurn();
    }

    public void updateMoney()
    {
        player1Money.setText("Money: " + game.getPlayer(0).getMoney());
        player2Money.setText("Money: " + game.getPlayer(1).getMoney());
        player3Money.setText("Money: " + game.getPlayer(2).getMoney());
    }

    public ImageIcon cardImage(Card c)
    {
        ImageIcon image = new ImageIcon("cardImages/"+c.getSuit()+"/"+
                c.getName()+".png");
        return image;
    }

    public void addCardImage(JPanel p, Hand h)
    {

        Card c = h.getCard(h.numOfCards()-1);       
        JPanel card = new JPanel();
        card.add(new JLabel(cardImage(c))); 
        p.add(card);
        frame.validate();
        frame.repaint();
    }

    MatteBorder border = BorderFactory.createMatteBorder(3,3,3,3, Color.YELLOW);
    // Border border = BorderFactory.createLineBorder(Color.BLACK);
    public void indicateTurn()
    {
        for(JLabel l: playerTexts)
        {
            l.setBorder(null);
        }

        playerTexts.get(playerTurn).setBorder(border);
    }

    public void checkBlackJack(int n)
    {
        if (game.getPlayer(n).isBlackJack())
        {
            game.endTurn(n);
            //resetCards();
            //resetButtons();
            playerTurn++;
            indicateTurn();
        }    
    }

    public static void main (String [] args)
    {
        new BlackJackMultiGUI();
    }

    public void resetCards()
    {
        player1Cards.removeAll();   
        player2Cards.removeAll();    
        player3Cards.removeAll();    
        dealerCards.removeAll();
        frame.validate();
        frame.repaint();
    }

    public void resetButtons()
    {
        hit.setEnabled(false);
        stay.setEnabled(false);
        bet.setEnabled(true);
    }
    
    public void offButtons()
    {
         hit.setEnabled(false);
        stay.setEnabled(false);
        bet.setEnabled(false);
    }

    public void deal()
    {

        for (int i =0; i<3; i++)
        {  

            game.dealCardTo(game.getPlayer(i));
            addCardImage(cardPanels.get(i), game.getPlayer(i));
        }

        game.dealCardTo(game.getDealer());        
        dealerFirstCardLabel.setIcon(backCardImage);           
        dealerCards.add(dealerFirstCardPanel);
        for (int i =0; i<3; i++)
        {  

            game.dealCardTo(game.getPlayer(i));
            addCardImage(cardPanels.get(i), game.getPlayer(i));
        }

        game.dealCardTo(game.getDealer());
        addCardImage(dealerCards, game.getDealer());

    }

    public void playerBlackJack()
    {
        indicateTurn();
        game.endTurn(playerTurn);
        playerTurn++;
        
       

        if(playerTurn==3)
        {
            startDealerTurn();
        }
         else if (game.getPlayer(playerTurn).isBlackJack())
                    playerBlackJack();
        updateMoney();
        indicateTurn();
    }

    public void startDealerTurn()
    {
        offButtons();
        Dealer d = game.getDealer();

        dealerFirstCardLabel.setIcon(cardImage(d.getCard(0)));

        t.start();   
        

        playerTurn =0;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==t)
        {
            Dealer d = game.getDealer();
            if(!d.softStand())
            {     
                game.dealCardTo(d);
                addCardImage(dealerCards, d);
            }
            else
            {
                t.stop();
                game.endOfRound();
                updateMoney();
                resetCards();
                resetButtons();
            }
        }
        if (e.getSource() == hit)
        {
            game.dealCardTo(game.getPlayer(playerTurn));
            addCardImage(cardPanels.get(playerTurn), game.getPlayer(playerTurn));

            if(game.getPlayer(playerTurn).isBust())
            {                        
                game.endTurn(playerTurn);
                playerTurn++;

                if(playerTurn==3)
                {
                    startDealerTurn();
                }

            }
        }
        if (e.getSource() == stay)
        {
            playerTurn++;

            if (playerTurn<3)
            { if(game.getPlayer(playerTurn).isBlackJack())
                    playerBlackJack();
            }
            else
            {
                startDealerTurn();
            }

        }
        if (e.getSource() == bet)
        {

            String s = cBox.getSelectedItem().toString();    
            Player p = game.getPlayer(playerTurn);
            int b = Integer.parseInt(s);
            p.placeBet(b);
            p.subtractMoney(b);
            updateMoney();
            playerTurn++;

            if(playerTurn==3)
            {
                playerTurn =0;

                deal();

                hit.setEnabled(true);
                stay.setEnabled(true);
                bet.setEnabled(false);

                if (game.getPlayer(playerTurn).isBlackJack())
                    playerBlackJack();


            }
        }

        indicateTurn();

    }    
}

