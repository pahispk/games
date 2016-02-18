package Cointoss;
//By: Eero, Niko & Thien

import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import java.sql.*;

public class Cointoss extends JFrame {
    
    Connection con;
    Statement st;
    ResultSet rs;
    
    JFrame loginWindow = new JFrame("User login");
    JLabel userLabel = new JLabel("Username:");
    JLabel passLabel = new JLabel("Password:");
    JTextField userField = new JTextField(10);
    JTextField passField = new JTextField(10);
    JButton loginButton = new JButton("Login");
            
    

    private JPanel basePanel = new JPanel();

    private JLabel flipText = new JLabel("Flip");
    private JLabel betText = new JLabel("Your bet: ");
    private JLabel fundsText = new JLabel("Your funds: ");

    private String[] choices = {"Heads", "Tails"};

    private JComboBox coinList = new JComboBox(choices);

    private JButton playButton = new JButton("Play!");

    private JTextField moneyLeft = new JTextField("100");
    private JTextField betAmount = new JTextField("0");
    private JTextField coinSide = new JTextField("");

    private JRadioButton allIn = new JRadioButton("All in!");

    public Cointoss() {
     
        connect();
        frame();
        
        
        GroupLayout layout = new GroupLayout(basePanel);
        basePanel.setLayout(layout);

        GroupLayout.SequentialGroup upperSideBySideGroupX = layout.createSequentialGroup();
        upperSideBySideGroupX.addComponent(flipText);
        upperSideBySideGroupX.addComponent(coinSide);

        GroupLayout.SequentialGroup midSideBySideGroupX = layout.createSequentialGroup();
        midSideBySideGroupX.addComponent(betText);
        midSideBySideGroupX.addComponent(betAmount);
        midSideBySideGroupX.addComponent(coinList);
        midSideBySideGroupX.addComponent(playButton);
        midSideBySideGroupX.addComponent(allIn);

        GroupLayout.SequentialGroup botSideBySideGroupX = layout.createSequentialGroup();
        botSideBySideGroupX.addComponent(fundsText);
        botSideBySideGroupX.addComponent(moneyLeft);

        GroupLayout.ParallelGroup baseX = layout.createParallelGroup();
        baseX.addGroup(upperSideBySideGroupX);
        baseX.addGroup(midSideBySideGroupX);
        baseX.addGroup(botSideBySideGroupX);

        layout.setHorizontalGroup(baseX);

        GroupLayout.ParallelGroup botRowY = layout.createParallelGroup();
        botRowY.addComponent(flipText);
        botRowY.addComponent(coinSide);

        GroupLayout.ParallelGroup topRowY = layout.createParallelGroup(BASELINE);
        topRowY.addComponent(betText);
        topRowY.addComponent(betAmount);
        topRowY.addComponent(coinList);
        topRowY.addComponent(playButton);
        topRowY.addComponent(allIn);

        GroupLayout.ParallelGroup midRowY = layout.createParallelGroup(BASELINE);
        midRowY.addComponent(fundsText);
        midRowY.addComponent(moneyLeft);

        GroupLayout.SequentialGroup baseY = layout.createSequentialGroup();
        baseY.addGroup(topRowY);
        baseY.addGroup(midRowY);
        baseY.addGroup(botRowY);

        layout.setVerticalGroup(baseY);

        this.add(basePanel);
        //this.pack();
        this.setTitle("Coin Tossing Game");
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.getRootPane().setDefaultButton(playButton);

        moneyLeft.setEditable(false);
        coinSide.setEditable(false);

        allIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (allIn.isSelected()) {
                    betAmount.setText(moneyLeft.getText());
                }
                //This disables or enables the editing depnding if the button is selected or not
                betAmount.setEditable(!allIn.isSelected());
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                int intMoneyLeft = Integer.parseInt(moneyLeft.getText());
                int intBetAmount = Integer.parseInt(betAmount.getText());
                
                //This happens if the bet is fine to do
                if (intBetAmount <= intMoneyLeft && intBetAmount>0) {
                    
                    //Created some temporary variables here to subtract from your available money
                    int updatedMoney = intMoneyLeft - intBetAmount;
                    String stringUpdatedMoney = Integer.toString(updatedMoney);

                    moneyLeft.setText(stringUpdatedMoney);

                    flipTheCoin();
                    if (finalCoin == coinList.getSelectedItem()) {
                        int winAmount = Integer.parseInt(betAmount.getText()) * 2;
                        String message = "You won " + winAmount + "!";
                        String title = "Winner!";
                        JOptionPane.showMessageDialog(playButton, message, title, JOptionPane.INFORMATION_MESSAGE);

                        int newMoneyLeft = Integer.parseInt(moneyLeft.getText()) + winAmount;//sick skills 
                        String stringMoneyLeft = Integer.toString(newMoneyLeft);
                        moneyLeft.setText(stringMoneyLeft);
                    } else {
                        int lostAmount = Integer.parseInt(betAmount.getText());
                        String message = "You lost " + lostAmount + "!";
                        String title = "Loser!";
                        JOptionPane.showMessageDialog(playButton, message, title, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                //This happens if you don't have any money left and you try to play
                else if (intMoneyLeft==0) {
                    String message="You're too poor to play, restart the program to play again";
                    JOptionPane.showMessageDialog(playButton, message, "Too poor", JOptionPane.INFORMATION_MESSAGE);
                }
                //This happens if your trying to bet 0
                else if (intBetAmount==0){
                    String message="You gotta at least bet something!";
                    JOptionPane.showMessageDialog(playButton, message, "Bet more", JOptionPane.INFORMATION_MESSAGE);
                }
                //This happens if you bet more than you have
                else {
                    String message="Don't bet your house on it!";
                    JOptionPane.showMessageDialog(playButton, message, "Bank account error...", JOptionPane.INFORMATION_MESSAGE);
                }
                allIn.setSelected(false);
                betAmount.setEditable(true);
                }
                catch(Exception error) {
                    String message="Please type numbers only...";
                    JOptionPane.showMessageDialog(playButton, message,"Error in betting!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }//Constructor ends here
    
    public void connect() {
        try
        {
            String driver = "org.apache.derby.jdbc.EmeddedDriver";
            Class.forName(driver);
            
            String db = "";
            con = DriverManager.getConnection(db);
            st = con.createStatement();
            
        }
        catch(Exception ex){
               
        }
    }
    
    public void frame(){
        loginWindow.setSize(500,300);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setVisible(true);
        loginWindow.setLocationRelativeTo(null);
        
        JPanel p = new JPanel();
        p.add(userLabel);
        p.add(passLabel);
        p.add(userField);
        p.add(passField);
        p.add(loginButton);
        
        loginWindow.add(p);
    }

    String finalCoin;
    int result;

    //The actual code to flip the coin
    private void flipTheCoin() {

        Random random = new Random();

        result = random.nextInt(2);
        if (result == 0) {
            finalCoin = "Heads";
        } else {
            finalCoin = "Tails";
        }

        coinSide.setText(finalCoin);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cointoss().setVisible(true);
            }
        });
    }
}
