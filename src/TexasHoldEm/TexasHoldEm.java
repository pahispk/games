package TexasHoldEm;

import Startmenu.Startmenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class TexasHoldEm extends JFrame{
    
    private JPanel basePanel = new JPanel();
    
    private JLabel aiCard1 = new JLabel("aicard1");
    private JLabel aiCard2 = new JLabel("aicard2");
    
    private JLabel table1 = new JLabel("table1");
    private JLabel table2 = new JLabel("table2");
    private JLabel table3 = new JLabel("table3");
    private JLabel table4 = new JLabel("table4");
    private JLabel table5 = new JLabel("table5");
    
    private JLabel pCard1 = new JLabel("pcard1");
    private JLabel pCard2 = new JLabel("pcard2");
    
    private JTextField fundsLeft = new JTextField("100");
    private JLabel betAmount = new JLabel("10");
    
    private JButton mainMenu = new JButton("Main menu");
    private JButton match = new JButton("Match");
    private JButton checkFold = new JButton("Check/fold");
    private JButton raise = new JButton("Raise");
    
    private JLabel fundsText = new JLabel("Funds:");
    private JLabel betText = new JLabel("Bet:");
    
    public TexasHoldEm(final int myPoints, final String myUser) {
        
        GroupLayout layout = new GroupLayout(basePanel);
        basePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup topRowX = layout.createSequentialGroup();
        topRowX.addComponent(aiCard1,100,100,100);
        //topRowX.addGap(30);
        topRowX.addComponent(aiCard2,100,100,100);
        topRowX.addComponent(mainMenu);

        GroupLayout.SequentialGroup midRowX = layout.createSequentialGroup();
        midRowX.addComponent(table1,100,100,100);
        midRowX.addComponent(table2,100,100,100);
        midRowX.addComponent(table3,100,100,100);
        midRowX.addComponent(table4,100,100,100);
        midRowX.addComponent(table5,100,100,100);

        GroupLayout.SequentialGroup botRowX = layout.createSequentialGroup();
        botRowX.addComponent(fundsText);
        botRowX.addComponent(fundsLeft,100,100,100);
        botRowX.addComponent(pCard1,100,100,100);
        botRowX.addComponent(pCard2,100,100,100);
        botRowX.addComponent(betText);
        botRowX.addComponent(betAmount);
        
        GroupLayout.SequentialGroup extraButtonGroupX = layout.createSequentialGroup();
        extraButtonGroupX.addComponent(match);
        extraButtonGroupX.addComponent(checkFold);
        extraButtonGroupX.addComponent(raise);

        GroupLayout.ParallelGroup baseX = layout.createParallelGroup();
        baseX.addGroup(topRowX);
        baseX.addGroup(midRowX);
        baseX.addGroup(botRowX);
        baseX.addGroup(extraButtonGroupX);

        layout.setHorizontalGroup(baseX);
        
        GroupLayout.ParallelGroup topRowY = layout.createParallelGroup();
        topRowY.addComponent(aiCard1,50,50,50);
        topRowY.addComponent(aiCard2,50,50,50);
        topRowY.addComponent(mainMenu,50,50,50);

        GroupLayout.ParallelGroup midRowY = layout.createParallelGroup();
        midRowY.addComponent(table1,50,50,50);
        midRowY.addComponent(table2,50,50,50);
        midRowY.addComponent(table3,50,50,50);
        midRowY.addComponent(table4,50,50,50);
        midRowY.addComponent(table5,50,50,50);

        GroupLayout.ParallelGroup botRowY = layout.createParallelGroup();
        botRowY.addComponent(fundsText,50,50,50);
        botRowY.addComponent(fundsLeft,50,50,50);
        botRowY.addComponent(pCard1,50,50,50);
        botRowY.addComponent(pCard2,50,50,50);
        botRowY.addComponent(betText,50,50,50);
        botRowY.addComponent(betAmount,50,50,50);
        
        GroupLayout.ParallelGroup extraButtonGroupY = layout.createParallelGroup();
        extraButtonGroupY.addComponent(match);
        extraButtonGroupY.addComponent(checkFold);
        extraButtonGroupY.addComponent(raise);

        GroupLayout.SequentialGroup baseY = layout.createSequentialGroup();
        baseY.addGroup(topRowY);
        baseY.addGroup(midRowY);
        baseY.addGroup(botRowY);
        baseY.addGroup(extraButtonGroupY);

        layout.setVerticalGroup(baseY);
        
        this.add(basePanel);
        this.setSize(500, 300);
        this.setTitle("Texas hold em");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
//        aiCard1.setEditable(false);
//        aiCard1.setBorder(BorderFactory.createLineBorder(Color.red));
//        aiCard2.setEditable(false);
//        aiCard2.setBorder(BorderFactory.createLineBorder(Color.red));
//        
//        table1.setEditable(false);
//        table2.setEditable(false);
//        table3.setEditable(false);
//        table4.setEditable(false);
//        table5.setEditable(false);
//        
//        pCard1.setEditable(false);
//        pCard2.setEditable(false);
//        
        fundsLeft.setEditable(false);
        
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Startmenu(myPoints, myUser).setVisible(true);
                dispose();
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TexasHoldEm(1000,"Test").setVisible(true);
            }
        });
    }
    
}
