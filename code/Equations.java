import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Equations extends JFrame implements ActionListener,MouseListener,KeyListener{
    JButton start,theme;
    JPanel instructPanel,mathPanel;
    JLabel instruct,problem,backIcon;
    int randomOne,randomTwo,randomOP;
    double correct=0.0,timeTaken,timeLimit=1400;
    long startTime,endTime;
    char[] operators = {'+','-','*','/'};
    int x=180;
    byte counter=0;
    JButton[] options;
    ImageIcon backD,backL;
    public Equations(){

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


        randomOne = 1+ (int)(Math.random()*15);
        randomOP = (int)(Math.random()*4);
        do {
            randomTwo = 1 + (int) (Math.random() * 15);
        } while ((randomOne/randomTwo)<=0 || randomOne==randomTwo);
        problem = new JLabel();
        problem.setText(randomOne +" "+ operators[randomOP]+" "+randomTwo+" = __");
        problem.setBounds(300,50,300,70);
        problem.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        problem.setFont(new Font("Consolas",Font.PLAIN,30));
        if (Home.Theme) {
            problem.setForeground(Color.white);
        } else {
            problem.setForeground(Color.black);
        }
        options = new JButton[4];
        for (byte i=0;i<4;++i) {
            options[i] = new JButton();
        }
        switch (randomOP){
            case 0:
                correct = randomOne+randomTwo;
                break;
            case 1:
                correct = randomOne-randomTwo;
                break;
            case 2:
                correct = randomOne*randomTwo;
                break;
            case 3:
                correct = (double)randomOne/randomTwo;
                break;
        }
        options[randomOP].setText(String.format("%.1f",correct));
        for (byte i=0;i<4;++i){

            if (Home.Theme){
                options[i].setBackground(Color.black);
                options[i].setForeground(Color.white);
            } else {
                options[i].setBackground(Color.white);
                options[i].setForeground(Color.black);
            }
            options[i].setFont(new Font("Impact",Font.PLAIN,30));
            options[i].setBounds(x,200,100,80);
            options[i].setFocusable(false);
            options[i].addActionListener(this);
            x+=110;
            if (i==randomOP){continue;}
            if (i==0) {
                do {
                    randomOne = 1 + (int) (Math.random() * 50);
                } while (correct == randomOne);
                options[i].setText((double)randomOne+"");
            } else {
                do {
                    randomOne = 1 + (int) (Math.random() * 50);
                } while (correct == randomOne || options[i-1].getText().equals(String.format("%.1f",(double)randomOne)));
                options[i].setText((double)randomOne+"");
            }
        }


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

        instruct = new JLabel("CHOOSE THE CORRECT ANSWER ASAP");
        instruct.setBounds(100,10,600,70);
        if (Home.Theme) {
            instruct.setBackground(Color.black);
            instruct.setForeground(Color.white);
        } else {
            instruct.setBackground(Color.white);
            instruct.setForeground(Color.black);
        }
        instruct.setFont(new Font("Consolas",Font.BOLD,22));


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

        mathPanel = new JPanel();
        mathPanel.setPreferredSize(new Dimension(800, 500));
        if (Home.Theme) {
            mathPanel.setBackground(Color.black);
        } else {
            mathPanel.setBackground(Color.white);
        }
        mathPanel.setLayout(null);
        for (byte i=0;i<4;++i){
            mathPanel.add(options[i]);
        }
        mathPanel.add(problem);
        mathPanel.setVisible(false);

        this.setTitle("MATH");
        this.setSize(800,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        if (Home.Theme){
            this.getContentPane().setBackground(Color.BLACK);
        } else {
            this.getContentPane().setBackground(Color.white);
        }
        this.add(instructPanel,BorderLayout.NORTH);
        this.add(mathPanel,BorderLayout.SOUTH);
        this.addKeyListener(this);
        this.setIconImage(Home.logo.getImage());
        this.setResizable(false);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){

        if (e.getSource()==start){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            mathPanel.setVisible(true);
            Games.played = true;
        }
        if (e.getSource() == theme){
            if (counter%2==0){
                    instruct.setForeground(Color.white);
                    theme.setText("LIGHT MODE");
                    problem.setForeground(Color.white);
                    theme.setBackground(Color.white);
                    theme.setForeground(Color.black);
                    start.setBackground(Color.black);
                    start.setForeground(Color.white);
                    backIcon.setIcon(backD);
                    instructPanel.setBackground(Color.black);
                    mathPanel.setBackground(Color.black);
                    this.getContentPane().setBackground(Color.black);
                    counter++;
                    Home.Theme=true;
            } else {
                    instruct.setForeground(Color.black);
                    problem.setForeground(Color.black);
                    theme.setText("DARK MODE");
                    theme.setBackground(Color.black);
                    start.setBackground(Color.white);
                    start.setForeground(Color.black);
                    backIcon.setIcon(backL);
                    theme.setForeground(Color.white);
                    instructPanel.setBackground(Color.white);
                    mathPanel.setBackground(Color.white);
                    this.getContentPane().setBackground(Color.white);
                    counter++;
                    Home.Theme=false;
            }
        }
        for (byte i=0;i<4;++i){
            if (e.getSource()==options[i]){
                endTime = System.currentTimeMillis();
                timeTaken = endTime-startTime;
                if (options[i].getText().equals(String.format("%.1f",correct))){
                    new Result(1);
                    Result.time.setText("YOU TOOK "+ timeTaken +" ms");
                    if (timeTaken<timeLimit) {
                        Result.note.setText("<html><h1>EXERCISE IS PASSED.</h1><br><h1>THE REQUIRED TIME BARRIER WAS 1400 ms</h1></html>");
                    } else {
                        Result.note.setText("<html><h1>EXERCISE IS FAILED.</h1><br><h1>THE REQUIRED TIME BARRIER WAS 1400 ms</h1></html>");
                    }
                    this.dispose();
                    new Result();
                } else {
                    new Result(1);
                    Result.note.setText("<html><h1>YOU CHOSE THE WRONG OPTION.</h1></html>");
                    this.dispose();
                    new Result();
                }
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
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

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
            Games.playedSpace = true;
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
