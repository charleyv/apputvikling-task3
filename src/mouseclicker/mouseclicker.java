package mouseclicker;

import javax.swing.*;

public class mouseclicker {
    public static void runGame(String[] args) {
        JFrame f=new JFrame("Maximum Clicks");//creating instance of JFrame
        int SCREEN_HEIGHT = 800;
        int SCREEN_WIDTH = 600;

        JLabel txt = new JLabel("See how many clicks you can do in a minute!");
        txt.setBounds(SCREEN_HEIGHT/4, SCREEN_WIDTH/2, 300, 50);

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame
        f.add(txt);

        f.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

    }
}
