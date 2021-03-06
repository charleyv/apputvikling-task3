package mouseclicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;


public class MouseClicker {


    private static final long timeLimit = 5L;
    private static final JButton clickBtn = new JButton("CLICK");
    private static final JButton startBtn = new JButton("START");
    private static JLabel highScore = new JLabel("High-score: ");

    private static class MyThread implements Runnable {

        private static AtomicReference<Long> seconds = new AtomicReference<Long>(0L);
        private static AtomicReference<Boolean> active = new AtomicReference<Boolean>(true);
        private static JLabel timer;

        public MyThread(JLabel clockTimer){
            timer = clockTimer;
        }


        @Override
        public void run() {
            active.set(true);
            Duration deltaTime = Duration.ZERO;
            Instant beginTime = Instant.now();

            while(active.get()) {
                deltaTime = Duration.between(beginTime, Instant.now());
                seconds.set(deltaTime.getSeconds());
                timer.setText(String.format("Time Left: %d Seconds", (timeLimit - seconds.get())));
                if(seconds.get() >= timeLimit) {
                    try {
                        endGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public static void off() {
            amountOfClicks = 0;
            active.set(false);
        }

        public static long getSeconds() {
            return seconds.get();
        }

    }


    private static int amountOfClicks = 0;
    private static boolean firstRun = true;
    private final static String RECORD_FILE = "./record.txt";


    public static void runGame() {
        JFrame f=new JFrame("Maximum Clicks");//creating instance of JFrame
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int SCREEN_HEIGHT = 800;
        int SCREEN_WIDTH = 600;

        String currentHighScore = checkHighScore(); // Fetch the current high-score
        if(currentHighScore.length() == 0) {
            currentHighScore = "0 Clicks";
        }

        highScore.setText("High-score: " + currentHighScore);
        highScore.setBounds(SCREEN_WIDTH/2 - 150, SCREEN_HEIGHT/4 -25, 300, 50);
        highScore.setFont(new Font("Verdana", Font.BOLD, 20));

        JLabel infoTxt = new JLabel("See how many clicks you can do in a minute!");
        infoTxt.setBounds(SCREEN_WIDTH/2 - 150, SCREEN_HEIGHT/3 - 25, 300, 50);


        startBtn.setBounds(SCREEN_WIDTH/2 - 200, SCREEN_HEIGHT/3 + 25, 300, 50);
        startBtn.setFont(new Font("Verdana", Font.BOLD, 20));


        clickBtn.setFont(new Font("Verdana", Font.BOLD, 16));
        clickBtn.setBounds(SCREEN_WIDTH/2 - 200,SCREEN_HEIGHT/2 - 20,100, 40);//x axis, y axis, width, height
        clickBtn.setEnabled(false);

        JLabel counter = new JLabel("COUNTER: 0");//creating instance of JButton
        counter.setFont(new Font("Verdana", Font.BOLD, 16));
        counter.setBounds(SCREEN_WIDTH/2,SCREEN_HEIGHT/2 - 20,200, 40);//x axis, y axis, width, height

        JLabel timeCounter = new JLabel("Time Left: 60 Seconds");
        timeCounter.setBounds(SCREEN_WIDTH/2 - 200,SCREEN_HEIGHT - 300,200, 40);
        timeCounter.setFont(new Font("Verdana", Font.BOLD, 16));

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startClick(timeCounter, counter);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                clickBtn.setEnabled(true);
                startBtn.setEnabled(false);
            }
        });


        f.add(highScore);
        f.add(infoTxt); //adding text
        f.add(startBtn);
        f.add(clickBtn);   //adding button in JFrame
        f.add(counter);
        f.add(timeCounter);

        f.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }

    private static void startClick(JLabel timer, JLabel count) {

        MyThread timerThread = new MyThread(timer);
        Thread thread = new Thread(timerThread);
        count.setText("COUNTER: 0");

        if(firstRun) {
            MouseClicker.clickBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    ++amountOfClicks;  // Increment
                    count.setText("COUNTER: " + Integer.toString(amountOfClicks));  // Convert back to string
                }
            });
            firstRun = false;
        }
        thread.start();

    }

    private static String checkHighScore() {
        String line = "0";
        Scanner reader;
        File fileObj = new File(RECORD_FILE);
        // Attempt to open the record file
        try {
            reader = new Scanner(fileObj);
            if(reader.hasNextLine()) {
                line = reader.nextLine(); // If there is a high-score there, fetch it
            }
            reader.close();
        } catch (IllegalAccessError | FileNotFoundException e) {
            System.out.println("Failed to open file.");
        }
        return line;
    }

    private static void checkNewHighScore() throws IOException {
        int currentScoreInt = Integer.parseInt(checkHighScore());

        if (amountOfClicks > currentScoreInt) {    // If the amount of clicks is higher than the high-score
            try {
                FileWriter filewriter = new FileWriter(RECORD_FILE);
                filewriter.write(String.format("%d\n", amountOfClicks));
                filewriter.close();
                highScore.setText("High-score: " + amountOfClicks);
            } catch (IOException e){
                System.out.println(e + ", failed to write to file");
            }
        }
    }

    private static void endGame() throws IOException {
        clickBtn.setEnabled(false);     // Disable click button
        startBtn.setEnabled(true);
        checkNewHighScore();
        MyThread.off();
    }

}
