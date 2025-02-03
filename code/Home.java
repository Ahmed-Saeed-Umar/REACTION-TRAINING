import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener, MouseListener, KeyListener {

    JLabel title1,title2;
    JButton play,exit,theme;
    JPanel home;
    byte counter = 0;
    static boolean Theme = true;
    static ImageIcon logo ;
    Home(){

        theme = new JButton();
        theme.setBounds(600,30,150,50);
        if (Home.Theme) {
            theme.setText("LIGHT MODE");
            theme.setBackground(Color.white);
            theme.setForeground(Color.black);
        } else {
            theme.setText("DARK MODE");
            theme.setBackground(Color.black);
            theme.setForeground(Color.white);
        }
        theme.setFocusable(false);
        theme.setFont(new Font("Impact",Font.PLAIN,24));
        theme.addActionListener(this);

        exit = new JButton("EXIT");
        exit.setBounds(350,500,100,50);
        if (Home.Theme) {
            exit.setBackground(Color.black);
            exit.setForeground(Color.white);
        } else {
            exit.setBackground(Color.white);
            exit.setForeground(Color.black);
        }
        exit.setFocusable(false);
        exit.setFont(new Font("Impact",Font.PLAIN,30));
        exit.addMouseListener(this);
        exit.addActionListener(this);

        play = new JButton("PLAY");
        play.setBounds(350,400,100,50);
        if (Home.Theme) {
            play.setForeground(Color.white);
            play.setBackground(Color.black);
        } else {
            play.setBackground(Color.white);
            play.setForeground(Color.black);
        }
        play.setFocusable(false);
        play.setFont(new Font("Impact",Font.PLAIN,30));
        play.addMouseListener(this);
        play.addActionListener(this);

        title1 = new JLabel("REACTION");
        title1.setBounds(290,170,240,50);
        if (Home.Theme) {
            title1.setForeground(Color.white);
        } else {
            title1.setForeground(Color.black);
        }
        title1.setFont(new Font("Impact", Font.ITALIC,55));

        title2 = new JLabel("TRAINING");
        title2.setBounds(290,235,235,50);
        if (Home.Theme){
            title2.setForeground(Color.white);
        } else {
            title2.setForeground(Color.black);
        }
        title2.setFont(new Font("Impact",Font.ITALIC,55));

        home = new JPanel();
        home.setSize(800,700);
        home.setLayout(null);
        home.add(title1);
        home.add(title2);
        home.add(play);
        home.add(exit);
        home.add(theme);
        if (Home.Theme) {
            home.setBackground(Color.black);
        } else {
            home.setBackground(Color.white);
        }

        logo = new ImageIcon("logo.png");

        this.setTitle("REACTION TRAINING");
        this.setSize(800,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setIconImage(logo.getImage());
        this.addKeyListener(this);
        this.add(home);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == theme) {
            if (counter%2==1){
                home.setBackground(Color.black);
                title1.setBackground(Color.black);
                title1.setForeground(Color.white);
                title2.setBackground(Color.black);
                title2.setForeground(Color.white);
                play.setBackground(Color.black);
                play.setForeground(Color.white);
                exit.setBackground(Color.black);
                exit.setForeground(Color.white);
                theme.setText("LIGHT MODE");
                theme.setBackground(Color.white);
                theme.setForeground(Color.black);
                counter++;
                Theme =true;
            } else  {
                home.setBackground(Color.white);
                title1.setBackground(Color.white);
                title1.setForeground(Color.black);
                title2.setBackground(Color.white);
                title2.setForeground(Color.black);
                play.setBackground(Color.white);
                play.setForeground(Color.black);
                exit.setBackground(Color.white);
                exit.setForeground(Color.black);
                theme.setText("DARK MODE");
                theme.setBackground(Color.black);
                theme.setForeground(Color.white);
                counter++;
                Theme = false;
            }
        }
        if (e.getSource()==play){
            this.dispose();
            new Games();
        }
        if (e.getSource()==exit){
            int exit = JOptionPane.showConfirmDialog(null,"Are you sure you want to EXIT ?","REACTION TRAINING",JOptionPane.YES_NO_OPTION);
            if (exit==0) {
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == play) {
            if (Home.Theme) {
                play.setBackground(Color.white);
                play.setForeground(Color.black);
            } else {
                play.setBackground(Color.black);
                play.setForeground(Color.white);
            }
        } else if (e.getSource() == exit) {
            if (Home.Theme) {
                exit.setBackground(Color.white);
                exit.setForeground(Color.black);
            } else {
                exit.setBackground(Color.black);
                exit.setForeground(Color.white);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==play) {
            if (Home.Theme) {
                play.setBackground(Color.black);
                play.setForeground(Color.white);
            } else{
                play.setBackground(Color.white);
                play.setForeground(Color.black);
            }
        } else if (e.getSource()==exit){
            if (Home.Theme) {
                exit.setBackground(Color.black);
                exit.setForeground(Color.white);
            } else {
                exit.setBackground(Color.white);
                exit.setForeground(Color.black);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar()==' '){
            this.dispose();
            new Games();
        }
        if (e.getKeyChar()==27){
            int exit = JOptionPane.showConfirmDialog(null,"Are you sure you want to EXIT ?","REACTION TRAINING",JOptionPane.YES_NO_OPTION);
            if (exit==0) {
                System.exit(0);
            }}
        if (e.getKeyChar()==10){
                this.dispose();
                new Games();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
