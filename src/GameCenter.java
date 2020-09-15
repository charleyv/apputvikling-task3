
import mouseclicker.MouseClicker;
import rolladice.RollADice;

import javax.swing.*;

public class GameCenter {

    public static void main(String[] args) {

        // setting up the frame itself
        JFrame frame = new JFrame();
        frame.setSize(400,450);
        frame.setLayout(null);  //using no layout managers
        frame.setTitle("Game Center");

        // This makes sure that game windows are closed if the main window is
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // buttons
        JButton diceButton = new JButton("Roll a Dice");
        diceButton.setBounds(100,100,150, 40);
        diceButton.addActionListener(e -> {
            RollADice.runGame();
        });
        frame.add(diceButton);


        JButton clickButton = new JButton("Maximum Clicks");
        clickButton.setBounds(100,150,150, 40);
        clickButton.addActionListener(e -> {
            MouseClicker.runGame();
        });
        frame.add(clickButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(280, 350, 90, 40);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        frame.add(exitButton);



        frame.setVisible(true);
    }

}
