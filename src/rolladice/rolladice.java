package rolladice;

import javax.swing.*;

public class rolladice {
    public static void main(String[] args) {
        JFrame f=new JFrame("Roll A Dice");//creating instance of JFrame
        int SCREEN_HEIGHT = 800;
        int SCREEN_WIDTH = 600;

        JLabel txt = new JLabel("<html><p>Each player takes three alternate turns to roll a dice. <br/>The one who scores maximum after three turns wins the game!</p></html>", SwingConstants.CENTER);
        txt.setBounds(0, 0, SCREEN_WIDTH, 100);

        JButton p1=new JButton("Player 1");
        JButton p2=new JButton("Player 2");
        p1.setBounds(130,150,150, 40);//x axis, y axis, width, height
        p2.setBounds(320,150,150, 40);//x axis, y axis, width, height

        f.add(p1);//adding button in JFrame
        f.add(p2);//adding button in JFrame
        f.add(txt);//adding txt in JFrame

        f.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        /** button logic **/
        p1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Dice d = new Dice();
                //d.Roll();

            }
        });
    }
}
