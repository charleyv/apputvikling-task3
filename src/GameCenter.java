
import rolladice.RollADice;

import javax.swing.*;

public class GameCenter {

    public static void main(String[] args) {

        // setting up the frame itself
        JFrame frame = new JFrame();
        frame.setSize(400,500);
        frame.setLayout(null);  //using no layout managers
        frame.setVisible(true);

        // This makes sure that game windows are closed if the main window is
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // buttons
        JButton diceButton = new JButton("Roll a Dice");
        diceButton.setBounds(130,100,100, 40);
        diceButton.addActionListener(e -> {
            RollADice.runGame();
        });

        frame.add(diceButton);
    }

}
