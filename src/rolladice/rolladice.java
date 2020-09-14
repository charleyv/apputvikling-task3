package rolladice;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollADice {
    public static void main(String[] args) {
        JFrame f=new JFrame("Roll A Dice");//creating instance of JFrame
        int SCREEN_HEIGHT = 800;
        int SCREEN_WIDTH = 600;

        JLabel desc = new JLabel("<html><p>Each player takes three alternate turns to roll a dice. <br/>The one who scores maximum after three turns wins the game!</p></html>", SwingConstants.CENTER);
        desc.setBounds(0, 0, SCREEN_WIDTH, 100);

        JButton p1=new JButton("Player 1");
        JButton p2=new JButton("Player 2");
        p1.setBounds(130,150,150, 40);
        p2.setBounds(320,150,150, 40);

        JLabel diceValue = new JLabel();
        Border border = BorderFactory.createDashedBorder(Color.BLACK);
        diceValue.setBorder(border);
        diceValue.setBounds(SCREEN_WIDTH / 2 - 5, 250, 10, 20);


        f.add(p1);
        f.add(p2);
        f.add(desc);
        f.add(diceValue);

        f.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);  // Frame size
        f.setLayout(null);                      // Use no layout managers
        f.setVisible(true);                     // Frame visible


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /** Score tracking **/
        final int[] score_p1 = {0};
        int score_p2 = 0;

        /** Button logic **/
        p1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Dice d = new Dice();
                int r = d.Roll();
                score_p1[0] += r;

                String rs = String.valueOf(r);
                diceValue.setText(rs);





            }
        });
    }
}
