
/**
 * Write a description of class BlackJackGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackJackGUI implements ActionListener
{
    private BlackJackGame game;
    private JFrame frame;
    private JButton deal;
    private JButton stay;
    private JButton hit;

    private JPanel playerCards;
    private JPanel dealerCards;
    private JLabel playerMoney;
    private JComboBox cBox;

    private JPanel dealerFirstCardPanel;
    private JLabel dealerFirstCardLabel;
    ImageIcon backCardImage = new ImageIcon(this.getClass().getResource("cardImages/back.png"));

    private Timer t;

    public BlackJackGUI()
    {
        game = new BlackJackGame();       
        t = new Timer(800, this); //timer for dealer card delay

        frame = new JFrame("BlackJack");
        frame.setVisible(true);  
        frame.setSize(750,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.green.darker());

        hit = new JButton("Hit");
        hit.setBounds(100,285,75,25);
        contentPane.add(hit);
        hit.addActionListener(this);

        stay = new JButton("Stay");
        stay.setBounds(225,285,75,25);
        contentPane.add(stay);
        stay.addActionListener(this);

        deal = new JButton("Deal");
        deal.setBounds(350,285,75,25);
        contentPane.add(deal);
        deal.addActionListener(this);

        resetButtons();

        JLabel bet = new JLabel("Bet Amount: ");      
        bet.setBounds(475,285,75,25);
        contentPane.add(bet);

        String[] betChoices = {"50","100", "250", "500","1000","2000","5000"};
        cBox = new JComboBox(betChoices);      
        cBox.setBounds(555,285,75,25);
        contentPane.add(cBox);

        JLabel playerText = new JLabel("Player's Cards");
        playerText.setBounds(10,10,125,25);
        contentPane.add(playerText);
        playerText.setFont(new Font("Serif", Font.BOLD, 20));

        playerMoney = new JLabel("Money: " + game.getPlayer().getMoney());
        playerMoney.setBounds(10,35,125,25);
        contentPane.add(playerMoney);
        playerMoney.setFont(new Font("Serif", Font.PLAIN, 20));

        playerCards = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));       
        playerCards.setBounds(10,60,700,110);     
        playerCards.setOpaque(false);
        contentPane.add(playerCards);

        JLabel dealerText = new JLabel("Dealer's Cards");
        dealerText.setBounds(10,360,150,25);       
        contentPane.add(dealerText);
        dealerText.setFont(new Font("Serif", Font.BOLD, 20));

        dealerCards = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));       
        dealerCards.setBounds(10,390,700,110);     
        dealerCards.setOpaque(false);
        contentPane.add(dealerCards);

        dealerFirstCardPanel = new JPanel();        
        dealerFirstCardLabel = new JLabel();
        dealerFirstCardPanel.add(dealerFirstCardLabel);
    }

    public void updateMoney()
    {
        playerMoney.setText("Money: " + game.getPlayer().getMoney());
    }

    public ImageIcon cardImage(Card c)
    {
        ImageIcon image = new ImageIcon(this.getClass().getResource("cardImages/"+c.getSuit()+"/"+
                c.getName()+".png"));
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

    public static void main (String [] args)
    {
        new BlackJackGUI();
    }

    public void resetCards()
    {
        playerCards.removeAll();        
        dealerCards.removeAll();
        frame.validate();
        frame.repaint();
    }

    public void resetButtons()
    {
        hit.setEnabled(false);
        stay.setEnabled(false);
        deal.setEnabled(true);
    }

    public void offButtons()
    {
        hit.setEnabled(false);
        stay.setEnabled(false);
        deal.setEnabled(false);
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
            game.dealCardTo(game.getPlayer());
            addCardImage(playerCards, game.getPlayer());

            if(game.getPlayer().isBust())
            {                        
                game.endOfRound();
                resetCards();
                resetButtons();
            }
        }
        if (e.getSource() == stay)
        {
            Dealer d = game.getDealer();

            dealerFirstCardLabel.setIcon(cardImage(d.getCard(0)));

            t.start();   
            offButtons();

        }
        if (e.getSource() == deal)
        {
            String s = cBox.getSelectedItem().toString();    
            Player p = game.getPlayer();
            int b = Integer.parseInt(s);
            p.placeBet(b);
            p.subtractMoney(b);
            updateMoney();

            game.dealCardTo(game.getPlayer());
            addCardImage(playerCards, game.getPlayer());
            game.dealCardTo(game.getDealer());           

            dealerFirstCardLabel.setIcon(backCardImage);           
            dealerCards.add(dealerFirstCardPanel);

            game.dealCardTo(game.getPlayer());
            addCardImage(playerCards, game.getPlayer());
            game.dealCardTo(game.getDealer());
            addCardImage(dealerCards, game.getDealer());

            hit.setEnabled(true);
            stay.setEnabled(true);
            deal.setEnabled(false);

            if (game.getPlayer().isBlackJack())
            {
                game.endOfRound();
                resetCards();
                resetButtons();
            }           
        }
    }    
}