import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MultiThreadingAnimation {
    public static void main(String[] args) {
        JFrame mainFrame =new JFrame ("Multi Threading Animation");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,500);
        mainFrame.setResizable(false);

        JPanel panel = new JPanel ();
        panel.setBackground(Color.black);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.orange));
        mainFrame.setContentPane(panel);

        JButton square1 = new JButton ();
        JButton square2 = new JButton ();
        JButton square3 = new JButton () ;

        JButton button1 = new JButton ("Task 1") ;
        button1.setBounds(30,400,100,50);
        JButton button2 = new JButton ("Task 2") ;
        button2.setBounds(130,400,100,50);
        JButton button3 = new JButton ("Task 3") ;
        button3.setBounds(230,400,100,50);
        JButton button4 = new JButton ("All Tasks") ;
        button4.setBounds(330,400,150,50);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        square1.setBounds(40,300,50,50);
        square1.setBackground(Color.orange);
        square1.setEnabled(false);

        square2.setBounds(390,300,50,50);
        square2.setBackground(Color.orange);
        square2.setEnabled(false);

        square3.setBounds(40,40,100,50);
        square3.setBackground(Color.orange);
        square3.setEnabled(false);
        panel.add(square1);
        panel.add(square2);
        panel.add(square3);
        mainFrame.setVisible(true);

        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setEnabled(false);

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        Point point1 = square1.getLocation();
                        Point point2 = square2.getLocation();
                        while (point1.x!=201) {
                            square1.setLocation(point1.x,point1.y);
                            point1.x++ ;
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException ex) {}
                        }
                        while(square2.getLocation().x != 250){/* wait */}
                        point1 = square1.getLocation();
                        point2 = square2.getLocation();
                        while(point1.y!=90) {
                            square2.setLocation(point2.x,point2.y);
                            square1.setLocation(point1.x,point1.y);
                            point1.y-- ;
                            point2.y-- ;
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException ex) {}
                        }
                    }
                };
                thread.start();
            }
        });
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.setEnabled(false);
                Thread thread = new Thread() {
                    Point point1 = square2.getLocation();
                    Point point2 = square1.getLocation();
                    @Override
                    public void run() {
                        while (point1.x!=249) {
                            square2.setLocation(point1.x,point1.y);
                            point1.x-- ;
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException ex) {}
                        }
                        while(square1.getLocation().x != square2.getLocation().x -50){/* wait */}

                        point1 = square2.getLocation();
                        point2 = square1.getLocation();

                        while (point1.y!=90) {
                            square2.setLocation(point1.x,point1.y);
                            square1.setLocation(point2.x,point2.y);
                            point1.y--;
                            point2.y-- ;
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException ex) {}
                        }
                    }
                };
                thread.start();
            }
        });
        button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                button3.setEnabled(false);
                Thread thread = new Thread() {
                    Point p3 = square3.getLocation();
                    Point p1 = square1.getLocation();
                    Point p2 = square2.getLocation();
                    @Override
                    public void run() {
                        while (p3.x!=202) {
                            square3.setLocation(p3.x,40);
                            p3.x++ ;
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException ex) {}
                        }
                        while(square1.getLocation().y != 91){System.out.println();/* wait */}

                        p3 = square3.getLocation();
                        p1 = square1.getLocation();
                        p2 = square2.getLocation();

                        while (p3.x!=500) {
                            square3.setLocation(p3.x,p3.y);
                            square1.setLocation(p1.x,p1.y);
                            square2.setLocation(p2.x,p2.y);
                            p3.x++ ;
                            p1.x++ ;
                            p2.x++ ;
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException ex) {}
                        }
                    }
                };
                thread.start();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button4.setEnabled(false);
                button1.doClick();
                button2.doClick();
                button3.doClick();
            }
        });
    }
}
