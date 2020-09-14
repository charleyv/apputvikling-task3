package mouseclicker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mouseclicker {
    public static void main(String[] args) {
        JFrame f=new JFrame("Maximum Clicks");//creating instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int SCREEN_HEIGHT = 800;
        int SCREEN_WIDTH = 600;

        String currentHighScore = checkHighScore(); // Fetch the current high-score
        if(currentHighScore.length() == 0) {
            currentHighScore = "0 Clicks";
        }

        JLabel highScore = new JLabel("High-score: " + currentHighScore);
        highScore.setBounds(SCREEN_WIDTH/2 - 150, SCREEN_HEIGHT/4 -25, 300, 50);
        highScore.setFont(new Font("Verdana", Font.BOLD, 20));

        JLabel infoTxt = new JLabel("See how many clicks you can do in a minute!");
        infoTxt.setBounds(SCREEN_WIDTH/2 - 150, SCREEN_HEIGHT/3 - 25, 300, 50);

        JButton startTxt = new JButton("START");
        startTxt.setBounds(SCREEN_WIDTH/2 - 200, SCREEN_HEIGHT/3 + 25, 300, 50);
        startTxt.setFont(new Font("Verdana", Font.BOLD, 20));

        JButton b = new JButton("CLICK");//creating instance of JButton
        b.setFont(new Font("Verdana", Font.BOLD, 16));
        b.setBounds(SCREEN_WIDTH/2 - 200,SCREEN_HEIGHT/2 - 20,100, 40);//x axis, y axis, width, height
        b.setEnabled(false);

        JLabel counter = new JLabel("COUNTER: 0");//creating instance of JButton
        counter.setFont(new Font("Verdana", Font.BOLD, 16));
        counter.setBounds(SCREEN_WIDTH/2,SCREEN_HEIGHT/2 - 20,200, 40);//x axis, y axis, width, height

        JLabel timeCounter = new JLabel("Time Left: 60 Seconds");
        timeCounter.setBounds(SCREEN_WIDTH/2 - 200,SCREEN_HEIGHT - 300,200, 40);
        timeCounter.setFont(new Font("Verdana", Font.BOLD, 16));

        f.add(highScore);
        f.add(infoTxt); //adding text
        f.add(startTxt);
        f.add(b);   //adding button in JFrame
        f.add(counter);
        f.add(timeCounter);

        f.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }

    private static String checkHighScore() {
        String line = "";
        Scanner reader;
        File fileObj = new File("./src/mouseclicker/record.txt");
        // Attempt to open the record file
        try {
            reader = new Scanner(fileObj);
            if(reader.hasNext()) {
                line = reader.nextLine(); // If there is a high-score there, fetch it
            }
            reader.close();
        } catch (IllegalAccessError | FileNotFoundException e) {
            System.out.println("Couldn't open file");
        }
        return line;
    }
}
