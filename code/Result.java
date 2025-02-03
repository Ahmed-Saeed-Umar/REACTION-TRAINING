import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Result extends JFrame implements ActionListener, MouseListener, KeyListener {
        static JLabel resultTxt,time,note;
        static JPanel resultBox;
        static JButton restart;
        ImageIcon backD,backL;
        JLabel backIcon;
        public Result(int a){
            resultTxt = new JLabel();
            time = new JLabel();
            note = new JLabel();
            resultBox = new JPanel();
            restart = new JButton();
        }
    public Result(){

        backD = new ImageIcon("back D.png");
        backL = new ImageIcon("back L.png");

        backIcon = new JLabel();
        backIcon.setBounds(3,3,80,60);
        if (Home.Theme) {
            backIcon.setIcon(backD);
        } else {
            backIcon.setIcon(backL);
        }
        backIcon.addMouseListener(this);

        restart.setText("PLAY AGAIN");
        restart.setBounds(120,220,150,50);
        if (Home.Theme) {
            restart.setForeground(Color.WHITE);
            restart.setBackground(Color.black);
        } else {
            restart.setForeground(Color.black);
            restart.setBackground(Color.white);
        }
        restart.setFocusable(false);
        restart.setFont(new Font("Impact",Font.PLAIN,25));
        restart.addActionListener(this);
        restart.addMouseListener(this);

        if (Home.Theme) {
            note.setForeground(Color.white);
        } else {
            note.setForeground(Color.black);
        }
        note.setFont(new Font("Consolas",Font.ITALIC,26));
        note.setLayout(new FlowLayout());


        if (Home.Theme) {
            time.setForeground(Color.white);
        } else {
            time.setForeground(Color.black);
        }
        time.setPreferredSize(new Dimension(150,100));
        time.setFont(new Font("Consolas",Font.ITALIC,26));
        time.setLayout(new FlowLayout());

        resultBox.setBounds(150,200,500,300);
        resultBox.setLayout(new BorderLayout());
        if (Home.Theme) {
            resultBox.setBackground(Color.darkGray.darker());
        } else {
            resultBox.setBackground(Color.white.darker());
        }
        resultBox.add(time,BorderLayout.NORTH);
        resultBox.add(note,BorderLayout.CENTER);
        resultBox.add(restart,BorderLayout.SOUTH);


        resultTxt.setText("RESULT");
        if (Home.Theme) {
            resultTxt.setForeground(Color.white);
            resultTxt.setBackground(Color.black);
        } else {
            resultTxt.setForeground(Color.black);
            resultTxt.setBackground(Color.white);
        }
        resultTxt.setBounds(350,100,200,100);
        resultTxt.setFont(new Font("Impact",Font.PLAIN,36));


        this.setSize(800,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (Home.Theme) {
            this.getContentPane().setBackground(Color.BLACK);
        } else {
            this.getContentPane().setBackground(Color.white);
        }
        this.setLayout(null);
        this.add(resultTxt);
        this.add(resultBox);
        this.add(backIcon);
        this.setIconImage(Home.logo.getImage());
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setResizable(false);
        this.setVisible(true);

    }
    public void actionPerformed(ActionEvent e){

        if (e.getSource()==restart){
            this.dispose();
            switch (Games.gameNumber){
                case 1:
                    new Equations();
                    break;
                case 2:
                    new Maths();
                    break;
                case 3:
                    new Lines();
                    break;
                case 4:
                    new Memory();
                    break;
                case 5:
                    new Boxes();
                    break;
                case 6:
                    new Hundred();
                    break;
                case 7:
                    new Number();
                    break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            if (e.getSource()==backIcon){
                this.dispose();
                new Games();
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==restart){
            if (Home.Theme){
                restart.setBackground(Color.white);
                restart.setForeground(Color.black);
            } else {
                restart.setBackground(Color.black);
                restart.setForeground(Color.white);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == restart) {
            if (Home.Theme) {
                restart.setBackground(Color.black);
                restart.setForeground(Color.white);
            } else {
                restart.setBackground(Color.white);
                restart.setForeground(Color.black);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar()==' '){
            this.dispose();
            switch (Games.gameNumber){
                case 1:
                    new Equations();
                    break;
                case 2:
                    new Maths();
                    break;
                case 3:
                    new Lines();
                    break;
                case 4:
                    new Memory();
                    break;
                case 5:
                    new Boxes();
                    break;
                case 6:
                    new Hundred();
                    break;
                case 7:
                    new Number();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"ERROR 404");
            }
        }
        if (e.getKeyChar()==27){
            this.dispose();
            new Games();
        }
        if (e.getKeyChar()==10) {
            this.dispose();
            switch (Games.gameNumber) {
                case 1:
                    new Equations();
                    break;
                case 2:
                    new Maths();
                    break;
                case 3:
                    new Lines();
                    break;
                case 4:
                    new Memory();
                    break;
                case 5:
                    new Boxes();
                    break;
                case 6:
                    new Hundred();
                    break;
                case 7:
                    new Number();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR 404");
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
