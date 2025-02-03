import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Hundred extends JFrame implements ActionListener, MouseListener,KeyListener {
    JPanel instructPanel,playPanel;
    JButton theme,start,more,less;
    long startTime,endTime,timeTaken,timeLimit=1000;
    JLabel instruct,first,second,third,fourth,plus,backIcon;
    int num1,num2,num3,num4;
    int sum=0,counter=0;
    ImageIcon backL,backD;
    public Hundred(){

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


        more = new JButton("> 100");
        more.setBounds(440,350,150,60);
        if (Home.Theme){
            more.setBackground(Color.black);
            more.setForeground(Color.white);
        } else{
            more.setBackground(Color.white);
            more.setForeground(Color.black);
        }
        more.setFont(new Font("Impact",Font.PLAIN,36));
        more.setFocusable(false);
        more.addActionListener(this);

        less = new JButton("< 100");
        less.setBounds(200,350,150,60);
        if (Home.Theme){
            less.setBackground(Color.black);
            less.setForeground(Color.white);
        } else{
            less.setBackground(Color.white);
            less.setForeground(Color.black);
        }
        less.setFont(new Font("Impact",Font.PLAIN,36));
        less.setFocusable(false);
        less.addActionListener(this);

        plus = new JLabel("+");
        plus.setBounds(380,150,50,50);
        if (Home.Theme){
            plus.setForeground(Color.white);
        } else{
            plus.setForeground(Color.black);
        }
        plus.setFont(new Font("Consolas",Font.BOLD,40));

        num1 = (int)(Math.random()*50);
        first = new JLabel();
        first.setText(num1+"");
        first.setBounds(330,100,50,50);
        if(Home.Theme){
            first.setForeground(Color.white);
        } else{
            first.setForeground(Color.black);
        }
        first.setFont(new Font("Consolas",Font.BOLD,40));

        num2 = (int)(Math.random()*50);
        second = new JLabel();
        second.setText(num2+"");
        second.setBounds(430,100,50,50);
        if(Home.Theme){
            second.setForeground(Color.white);
        } else{
            second.setForeground(Color.black);
        }
        second.setFont(new Font("Consolas",Font.BOLD,40));

        num3 = (int)(Math.random()*50);
        third = new JLabel();
        third.setText(num3+"");
        third.setBounds(330,200,50,50);
        if(Home.Theme){
            third.setForeground(Color.white);
        } else{
            third.setForeground(Color.black);
        }
        third.setFont(new Font("Consolas",Font.BOLD,40));

        do {
            num4 = (int) (Math.random() * 50);
        } while ((num1+num2+num3+num4)==100);
        fourth = new JLabel();
        fourth.setText(num4+"");
        fourth.setBounds(430,200,50,50);
        if(Home.Theme){
            fourth.setForeground(Color.white);
        } else{
            fourth.setForeground(Color.black);
        }
        fourth.setFont(new Font("Consolas",Font.BOLD,40));

        sum = num1+num2+num3+num4;

        playPanel = new JPanel();
        playPanel.setPreferredSize(new Dimension(800,500));
        if (Home.Theme){
            playPanel.setBackground(Color.black);
        } else {
            playPanel.setBackground(Color.white);
        }
        playPanel.setLayout(null);
        playPanel.add(first);
        playPanel.add(second);
        playPanel.add(third);
        playPanel.add(fourth);
        playPanel.add(plus);
        playPanel.add(less);
        playPanel.add(more);
        playPanel.setVisible(false);

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

        instruct = new JLabel("FIND THE SUM OF NUMBERS");
        instruct.setBounds(100,10,600,70);
        if (Home.Theme) {
            instruct.setBackground(Color.black);
            instruct.setForeground(Color.white);
        } else {
            instruct.setBackground(Color.white);
            instruct.setForeground(Color.black);
        }
        instruct.setFont(new Font("Consolas",Font.ITALIC,26));


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

        this.setTitle("HUNDRED");
        this.setSize(800,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        if (Home.Theme){
            this.getContentPane().setBackground(Color.BLACK);
        } else {
            this.getContentPane().setBackground(Color.white);
        }
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setIconImage(Home.logo.getImage());
        if (Home.Theme){
            this.getContentPane().setBackground(Color.black);
        } else {
            this.getContentPane().setBackground(Color.white);
        }
        this.add(instructPanel,BorderLayout.NORTH);
        this.add(playPanel,BorderLayout.SOUTH);
        this.addKeyListener(this);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){

        if (e.getSource()==start){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            playPanel.setVisible(true);
            Games.played=true;
        }
        if (e.getSource()==less){
            endTime = System.currentTimeMillis();
            timeTaken = endTime - startTime;
            this.dispose();
            if (sum<100){
                new Result(1);
                Result.time.setText("YOU TOOK "+timeTaken+" ms");
                if (timeTaken<timeLimit) {
                    Result.note.setText("<html><h1>EXERCISE IS PASSED</h1><br><h1>THE TIME LIMIT WAS 1000 ms</h1></html>");
                } else{
                    Result.note.setText("<html><h1>EXERCISE IS FAILED</h1><br><h1>THE TIME LIMIT WAS 1000 ms</h1></html>");
                }
            } else {
                new Result(1);
                Result.time.setText("<html><h1>EXERCISE IS FAILED</h1></html>");
                Result.note.setText("<html><h1>INCORRECT ANSWER</h1></html>");
            }
            new Result();
        }
        if (e.getSource()==more){
            endTime = System.currentTimeMillis();
            timeTaken = endTime - startTime;
            this.dispose();
            if (sum>100){
                new Result(1);
                Result.time.setText("YOU TOOK "+timeTaken+" ms");
                if(timeTaken<timeLimit) {
                    Result.note.setText("<html><h1>EXERCISE IS PASSED</h1><br><h1>THE TIME LIMIT WAS 1000 ms</h1></html>");
                } else {
                    Result.note.setText("<html><h1>EXERCISE IS FAILED</h1><br><h1>THE TIME LIMIT WAS 1000 ms</h1></html>");
                }
                new Result();
            } else {
                new Result(1);
                Result.time.setText("<html><h1>EXERCISE IS FAILED</h1></html>");
                Result.note.setText("<html><h1>INCORRECT ANSWER</h1></html>");
                new Result();
            }
        }
        if (e.getSource()==theme){
                if (counter%2==0){
                    this.getContentPane().setBackground(Color.white);
                    playPanel.setBackground(Color.white);
                    instructPanel.setBackground(Color.white);
                    instruct.setForeground(Color.black);
                    theme.setText("DARK MODE");
                    backIcon.setIcon(backL);
                    theme.setBackground(Color.black);
                    theme.setForeground(Color.white);
                    start.setBackground(Color.white);
                    start.setForeground(Color.black);
                    less.setBackground(Color.white);
                    less.setForeground(Color.black);
                    more.setBackground(Color.white);
                    more.setForeground(Color.black);
                    first.setForeground(Color.black);
                    second.setForeground(Color.black);
                    third.setForeground(Color.black);
                    fourth.setForeground(Color.black);
                    plus.setForeground(Color.black);
                    counter++;
                    Home.Theme=false;

            } else{
                this.getContentPane().setBackground(Color.black);
                instructPanel.setBackground(Color.black);
                instruct.setForeground(Color.white);
                theme.setText("LIGHT MODE");
                backIcon.setIcon(backD);
                theme.setBackground(Color.white);
                theme.setForeground(Color.black);
                start.setBackground(Color.black);
                start.setForeground(Color.white);
                playPanel.setBackground(Color.black);
                less.setBackground(Color.black);
                less.setForeground(Color.white);
                more.setBackground(Color.black);
                more.setForeground(Color.white);
                first.setForeground(Color.white);
                second.setForeground(Color.white);
                third.setForeground(Color.white);
                fourth.setForeground(Color.white);
                plus.setForeground(Color.white);
                counter++;
                Home.Theme =true;
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
        if (e.getKeyChar()==' ' && !Games.played && !Games.playedEnter){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            playPanel.setVisible(true);
            Games.playedSpace = true;
        }

        if (e.getKeyChar()==27){
            this.dispose();
            new Games();
        }
        if (e.getKeyChar()==10 && !Games.played && !Games.playedSpace){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            playPanel.setVisible(true);
            Games.playedEnter = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
