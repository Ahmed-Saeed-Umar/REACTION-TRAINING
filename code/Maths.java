import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

 public class Maths extends JFrame implements ActionListener, MouseListener,KeyListener {
    JButton[] numbers;
    JLabel instruct;
    static JLabel backIcon;
    static double timeLimit = 15;
    static long startTime,endTime;
    JButton theme,start;
    String number;
    static double timeTaken=0.0;
    JPanel instructPanel, mathPanel;
    byte counter=0,count=0,yesCount=0;
    int[] randoms;
    static ImageIcon backD,backL;
    public Maths() {

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

        start = new JButton("START");
        start.setBounds(300,80,200,70);
        if (Home.Theme) {
            start.setBackground(Color.black);
            start.setForeground(Color.white);
        } else {
            start.setBackground(Color.white);
            start.setForeground(Color.black);
        }
        start.setFont(new Font("Impact",Font.PLAIN,30));
        start.setFocusable(false);
        start.addActionListener(this);
        start.addMouseListener(this);

        theme = new JButton();
        theme.setBounds(650, 15, 120, 40);
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
        theme.setFont(new Font("Impact", Font.PLAIN, 18));
        theme.addActionListener(this);

        instruct = new JLabel("CLICK THE NUMBERS IN THE ASCENDING ORDER");
        instruct.setBounds(100,10,600,70);
        if (Home.Theme) {
            instruct.setBackground(Color.black);
            instruct.setForeground(Color.white);
        } else {
            instruct.setBackground(Color.white);
            instruct.setForeground(Color.black);
        }
        instruct.setFont(new Font("Consolas",Font.BOLD,22));

        mathPanel = new JPanel();
        mathPanel.setPreferredSize(new Dimension(800, 500));
        if (Home.Theme) {
            mathPanel.setBackground(Color.black);
        } else {
            mathPanel.setBackground(Color.white);
        }
        mathPanel.setLayout(new GridLayout(4, 4));
        mathPanel.setVisible(false);

        randoms = new int[16] ;
        for (byte i=0;i<16;++i){
            randoms[i] = 1+(int)(Math.random()*16);
            if (i>0){
                for (byte j=0;j<=i-1;++j){
                    if (randoms[j]==randoms[i]){
                        i--;
                    }
                }
            }
        }


        numbers = new JButton[16];
        for (int i = 0; i < 16; ++i) {
        numbers[i] = new JButton(randoms[i]+"");
        if (Home.Theme) {
            numbers[i].setBackground(Color.black);
            numbers[i].setForeground(Color.white);
        }
        else {
            numbers[i].setBackground(Color.white);
            numbers[i].setForeground(Color.black);
        }
        numbers[i].setFocusable(false);
        numbers[i].setFont(new Font("Impact", Font.PLAIN, 30));
        numbers[i].addActionListener(this);
        mathPanel.add(numbers[i]);
}
        mathPanel.add(start);
        Arrays.sort(randoms);

        instructPanel = new JPanel();
        instructPanel.setPreferredSize(new Dimension(800,200));
        if (Home.Theme) {
            instructPanel.setBackground(Color.black);
        } else {
            instructPanel.setBackground(Color.white);
        }
        instructPanel.add(backIcon);
        instructPanel.add(instruct);
        instructPanel.add(theme);
        instructPanel.add(start);
        instructPanel.setLayout(null);

        this.setTitle("MATH");
        this.setSize(800,700);
        this.setLayout(new BorderLayout());
        if (Home.Theme) {
            this.getContentPane().setBackground(Color.black);
        } else {
            this.getContentPane().setBackground(Color.white);
        }
        this.add(mathPanel,BorderLayout.SOUTH);
        this.add(instructPanel,BorderLayout.NORTH);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(Home.logo.getImage());
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == theme) {
            if (counter % 2 == 0) {
                instructPanel.setBackground(Color.white);
                instruct.setBackground(Color.white);
                instruct.setForeground(Color.black);
                for (byte i = 0; i < 16; ++i) {
                    numbers[i].setBackground(Color.white);
                    numbers[i].setForeground(Color.black);
                }
                theme.setText("DARK MODE");
                theme.setBackground(Color.black);
                theme.setForeground(Color.white);
                backIcon.setIcon(backL);
                start.setBackground(Color.white);
                start.setForeground(Color.black);
                counter++;
                Home.Theme =false;
            } else if (counter % 2 == 1) {
                instructPanel.setBackground(Color.black);
                instruct.setBackground(Color.black);
                instruct.setForeground(Color.white);
                for (byte i = 0; i < 16; ++i) {
                    numbers[i].setBackground(Color.black);
                    numbers[i].setForeground(Color.white);
                }
                theme.setText("LIGHT MODE");
                backIcon.setIcon(backD);
                start.setBackground(Color.black);
                start.setForeground(Color.white);
                counter++;
                Home.Theme=true;
            }
        }
        if (e.getSource() == start) {
            startTime = System.currentTimeMillis();
            mathPanel.setVisible(true);
            start.setVisible(false);
            Games.played = true;
        }

        for (byte i = 0; i < 16; ++i) {
            if (e.getSource() == numbers[i]) {
                number = numbers[i].getText();
                int num = Integer.parseInt(number);
//                System.out.println(yesCount);
                if(yesCount==15){
                    endTime = System.currentTimeMillis();
                    new Result(1);
                    this.dispose();
                    timeTaken = (double) ((endTime - startTime) / 1000);
                    Result.time.setText("YOU TOOK " + timeTaken + " SECONDS.");
                    if (timeTaken < timeLimit) {
                        Result.note.setText("<html><h1>EXERCISE IS PASSED.</h1><br><h1>THE REQUIRED TIME BARRIER WAS 15 s.</h1></html>");
                    } else {
                        Result.note.setText("<html><h1>EXERCISE IS FAILED.</h1><br><h1>THE REQUIRED TIME BARRIER WAS 15 s.</h1></html>");
                    }
                    new Result();
                }
                if (num == randoms[count]) {
                    numbers[i].setBackground(Color.darkGray.darker());
                    numbers[i].setEnabled(false);
                    yesCount++;
                } else {
                    endTime = System.currentTimeMillis();
                    for (byte j = 0; j < 16; ++j) {
                        numbers[j].setEnabled(false);
                    }
                    new Result(1);
                    Result.time.setText("YOU FAILED THE TASK");
                    this.dispose();
                    new Result();
                }
                count++;
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
        if (e.getSource()==start){
            if (Home.Theme){
                start.setBackground(Color.white);
                start.setForeground(Color.black);
            } else {
                start.setBackground(Color.black);
                start.setForeground(Color.white);
            }
        }
     }

     @Override
     public void mouseExited(MouseEvent e) {
        if (e.getSource()==start){
            if (Home.Theme){
                start.setBackground(Color.black);
                start.setForeground(Color.white);
            } else {
                start.setBackground(Color.white);
                start.setForeground(Color.black);
            }
        }
     }

     @Override
     public void keyTyped(KeyEvent e) {

         if (e.getKeyChar()==27){
             this.dispose();
             new Games();
         }
         if (e.getKeyChar()==' ' && !Games.played && !Games.playedEnter){
             startTime = System.currentTimeMillis();
             start.setVisible(false);
             mathPanel.setVisible(true);
             Games.playedSpace=true;
         }

         if (e.getKeyChar()==10 && !Games.played && !Games.playedSpace){
             startTime = System.currentTimeMillis();
             start.setVisible(false);
             mathPanel.setVisible(true);
             Games.playedEnter = true;
         }
     }

     @Override
     public void keyPressed(KeyEvent e) {

     }

     @Override
     public void keyReleased(KeyEvent e) {

     }
 }
