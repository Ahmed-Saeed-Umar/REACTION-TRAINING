import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Number extends JFrame implements ActionListener, MouseListener, KeyListener {
    JPanel instructPanel,playPanel;
    JLabel instruct,theNumber,backIcon;
    JButton theme,start;
    JButton[] options;
    byte randomOpt,counter=0;
    long startTime,endTime,timeTaken,timeLimit=1200;
    long Number;
    long[] option;

    ImageIcon backD,backL;
    public Number(){
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



        Number = 20000+(int)(Math.random()*20000);

        option = new long[4];
        option[0] = Number + (int)(Math.random()*100);
        option[1] = Number - (int)(Math.random()*100);
        option[2] = Number + (int)(Math.random()*1000);
        option[3] = Number - (int)(Math.random()*1000);

        randomOpt = (byte) (Math.random()*4);
        options = new JButton[4];
        for (byte i=0;i<4;++i){
            options[i] = new JButton();
        }
        options[randomOpt].setText(Number+"");
        for (byte i=0;i<4;++i){
            if (Home.Theme){
                options[i].setBackground(Color.black);
                options[i].setForeground(Color.WHITE);
            } else {
                options[i].setBackground(Color.white);
                options[i].setForeground(Color.black);
            }
            options[i].setFont(new Font("Impact",Font.PLAIN,36));
            options[i].setFocusable(false);
            options[i].addActionListener(this);
            if (i==randomOpt){continue;}
            options[i].setText(option[i]+"");
        }
        options[0].setBounds(180,250,200,50);
        options[1].setBounds(430,250,200,50);
        options[2].setBounds(180,350,200,50);
        options[3].setBounds(430,350,200,50);


        theNumber = new JLabel(Number+"");
        theNumber.setBounds(350,50,300,70);
        if (Home.Theme){
            theNumber.setForeground(Color.white);
        } else {
            theNumber.setForeground(Color.black);
        }
        theNumber.setFont(new Font("Impact",Font.PLAIN,40));

        playPanel = new JPanel();
        playPanel.setPreferredSize(new Dimension(800,500));
        if (Home.Theme){
            playPanel.setBackground(Color.black);
        } else {
            playPanel.setBackground(Color.white);
        }
        playPanel.setLayout(null);
        playPanel.add(theNumber);
        for (byte i=0;i<4;++i){
            playPanel.add(options[i]);
        }
        playPanel.setVisible(false);

        start = new JButton("START");
        start.setBounds(330,80,150,70);
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

        instruct = new JLabel("FIND THE NUMBER");
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

        this.setTitle("FIND THE NUMBER");
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
        if (Home.Theme){
            this.getContentPane().setBackground(Color.black);
        } else {
            this.getContentPane().setBackground(Color.white);
        }
        this.add(instructPanel,BorderLayout.NORTH);
        this.add(playPanel,BorderLayout.SOUTH);
        this.addKeyListener(this);
        this.setIconImage(Home.logo.getImage());
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==start){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            playPanel.setVisible(true);
            }
        for (byte i=0;i<4;++i){
            if (e.getSource()==options[i]){
                endTime = System.currentTimeMillis();
                timeTaken = endTime - startTime;
                if (options[i].getText().equals(Number+"")){
                    new Result(1);
                    Result.time.setText("YOU TOOK "+timeTaken+" ms");
                    if (timeTaken<timeLimit) {
                        Result.note.setText("<html><h1>EXERCISE IS PASSED</h1><br><h1>THE TIME LIMIT WAS 1200 ms</h1></html>");
                    } else {
                        Result.note.setText("<html><h1>EXERCISE IS FAILED</h1><br><h1>THE TIME LIMIT WAS 1200 ms</h1></html>");
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
                theNumber.setBackground(Color.white);
                theNumber.setForeground(Color.black);
                for (byte i=0;i<4;++i){
                    options[i].setBackground(Color.white);
                    options[i].setForeground(Color.black);
                }
                counter++;
                Home.Theme=false;

            } else{
                this.getContentPane().setBackground(Color.black);
                instructPanel.setBackground(Color.black);
                instruct.setForeground(Color.white);
                theme.setText("LIGHT MODE");
                theme.setBackground(Color.white);
                theme.setForeground(Color.black);
                start.setBackground(Color.black);
                start.setForeground(Color.white);
                backIcon.setIcon(backD);
                playPanel.setBackground(Color.black);
                theNumber.setBackground(Color.black);
                theNumber.setForeground(Color.white);
                for (byte i=0;i<4;++i){
                    options[i].setBackground(Color.black);
                    options[i].setForeground(Color.white);
                }
                counter++;
                Home.Theme =true;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == backIcon) {
            this.dispose();
            new Games();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

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
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
