package rolladice;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class RollADice {
    public static void main(String[] args) {
        JFrame f=new JFrame("Roll A Dice");//creating instance of JFrame
        int SCREEN_HEIGHT = 800;
        int SCREEN_WIDTH = 600;

        JLabel desc = new JLabel("<html><p>Each player takes three alternate turns to roll a dice. <br/>The one who scores maximum after three turns wins the game!</p></html>", SwingConstants.CENTER);
        desc.setBounds(0, 0, SCREEN_WIDTH, 100);

        JButton p1=new JButton("Player 1");
        JButton p2=new JButton("Player 2");

        p1.setEnabled(true);
        p2.setEnabled(false);

        p1.setBounds(130,150,150, 40);
        p2.setBounds(320,150,150, 40);

        JLabel diceValue = new JLabel();
        Border border = BorderFactory.createDashedBorder(Color.BLACK);
        diceValue.setBorder(border);
        diceValue.setBounds(SCREEN_WIDTH / 2 - 5, 250, 10, 20);

        JLabel p1_score_text = new JLabel();
        p1_score_text.setBorder(border);
        p1_score_text.setBounds(SCREEN_WIDTH / 4 - 25, 500, 50, 20);

        JLabel p2_score_text = new JLabel();
        p2_score_text.setBorder(border);
        p2_score_text.setBounds(SCREEN_WIDTH / 4 * 3 - 25, 500, 50, 20);

        AtomicReference<String> result = new AtomicReference<>("");
        JLabel resultText = new JLabel("", SwingConstants.CENTER);
        resultText.setBorder(border);
        int resultTextWidth = 100;
        resultText.setBounds(SCREEN_WIDTH / 2 - resultTextWidth / 2, 600, 100, 20);



        f.add(p1);
        f.add(p2);
        f.add(desc);
        f.add(diceValue);
        f.add(p1_score_text);
        f.add(p2_score_text);
        f.add(resultText);

        f.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);  // Frame size
        f.setLayout(null);                      // Use no layout managers
        f.setVisible(true);                     // Frame visible


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /** Score tracking **/
        final int[] p1_score = {0};
        final int[] p2_score = {0};

        /** Turn count **/
        final int[] p1_turns = {0};
        final int[] p2_turns = {0};

        /** Button logic **/
        p1.addActionListener(e -> {
            Dice d = new Dice();
            int r = d.Roll();
            p1_score[0] += r;
            p1_turns[0]++;

            String rs = String.valueOf(r);
            diceValue.setText(rs);

            p1_score_text.setText(String.valueOf(p1_score[0]));

            p1.setEnabled(false);
            if (p2_turns[0] < 3) {
                p2.setEnabled(true);
            }

            if (p1_turns[0] == 3 && p2_turns[0] == 3) {
                if (p1_score[0] > p2_score[0]) {
                    result.set("p1 wins");
                } else if (p1_score[0] == p2_score[0]) {
                    result.set("tie");
                } else {
                    result.set("p2 wins");
                }
                resultText.setText(result.get());
            }
        });

        p2.addActionListener(e -> {
            Dice d = new Dice();
            int r = d.Roll();
            p2_score[0] += r;
            p2_turns[0]++;

            String rs = String.valueOf(r);
            diceValue.setText(rs);

            p2_score_text.setText(String.valueOf(p2_score[0]));

            p2.setEnabled(false);
            if (p1_turns[0] < 3) {
                p1.setEnabled(true);
            }

            if (p1_turns[0] == 3 && p2_turns[0] == 3) {
                if (p1_score[0] > p2_score[0]) {
                    result.set("p1 wins");
                } else if (p1_score[0] == p2_score[0]) {
                    result.set("tie");
                } else {
                    result.set("p2 wins");
                }
                resultText.setText(result.get());
            }
        });


    }
}
