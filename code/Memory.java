import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;

public class Memory extends JFrame implements ActionListener, MouseListener,KeyListener {
    JPanel instructPanel,playPanel;
    JButton theme,start;
    JLabel instruct,backIcon;
    JButton[] boxes;
    long startTime,endTime,timeTaken,timeLimit=2000;
    int randomOne,randomTwo,randomThree;
    byte correctCount=0,counter=0;
    ImageIcon backD,backL;
    public Memory(){

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


        randomOne = (int)(Math.random()*16);
        do {
            randomTwo = (int) (Math.random() * 16);
        } while (randomTwo==randomOne);
        do {
            randomThree = (int) (Math.random() * 16);
        } while (randomThree==randomTwo || randomThree==randomOne);

        boxes = new JButton[16];
        for (byte i=0;i<16;++i){
            boxes[i] = new JButton();
            if (i==randomOne || i==randomTwo || i==randomThree){
                boxes[i].setText(".");
            }
            boxes[i].setForeground(Color.red);
            if (Home.Theme) {
                boxes[i].setBackground(Color.gray.brighter());
            } else {
                boxes[i].setBackground(Color.white);
            }
            boxes[i].setFont(new Font("Impact",Font.BOLD,50));
            boxes[i].setFocusable(false);
            boxes[i].addActionListener(this);
        }


        playPanel = new JPanel();
        playPanel.setPreferredSize(new Dimension(800,400));
        if (Home.Theme){
            playPanel.setBackground(Color.black);
        } else {
            playPanel.setBackground(Color.white);
        }
        playPanel.setLayout(new GridLayout(4,4));
        for (byte i=0;i<16;++i){
            playPanel.add(boxes[i]);
        }
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

        instruct = new JLabel("CLICK ON BOXES WITH RED DOTS");
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

        this.setTitle("VISUAL MEMORY");
        this.setSize(800,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setIconImage(Home.logo.getImage());
        if (Home.Theme){
            this.getContentPane().setBackground(Color.BLACK);
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
            Games.played=true;
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                byte count=1;
                @Override
                public void run() {
                    if (count>0){
                        playPanel.setVisible(true);
                        --count;
                    } else {
                        for (byte i=0;i<16;++i){
                            boxes[i].setText("");
                            boxes[i].setBackground(Color.black);
                        }
                        timer.cancel();
                    }
                }
            } ;
            timer.scheduleAtFixedRate(timerTask,0,600);
        }
        for (byte i=0;i<16;++i) {
            if (e.getSource() == boxes[i]) {
                if (i == randomOne || i == randomTwo || i == randomThree) {
                    boxes[i].setEnabled(false);
                    if (correctCount == 2) {
                        endTime = System.currentTimeMillis();
                        timeTaken = endTime - startTime;
                        this.dispose();
                        new Result(1);
                        Result.time.setText("YOU TOOK " + timeTaken + " ms.");
                        if (timeTaken < timeLimit) {
                            Result.note.setText("<html><h1>EXERCISE IS PASSED</h1><br><h1>THE TIME LIMIT WAS 2000 ms</h1></html>");
                        } else {
                            Result.note.setText("<html><h1>EXERCISE IS FAILED</h1><br><h1>THE TIME LIMIT WAS 2000 ms</h1></html>");
                        }
                        new Result();
                    }
                    correctCount++;
                    boxes[i].setText(".");
                    boxes[i].setBackground(Color.black);
                } else {
                    boxes[i].setBackground(Color.black);
                    boxes[i].setEnabled(false);
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
                    for (byte i=0;i<16;++i){
                        boxes[i].setBackground(Color.white);
                    }
                    counter++;
                    Home.Theme =false;
                } else {
                    playPanel.setBackground(Color.black);
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
                    for (byte i=0;i<16;++i){
                        boxes[i].setBackground(Color.gray.brighter());
                    }
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
        if (e.getKeyChar()==' ' && !Games.played && !Games.playedEnter) {
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            Games.playedSpace = true;
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                byte count = 1;
                @Override
                public void run() {
                    if (count > 0) {
                        playPanel.setVisible(true);
                        --count;
                    } else {
                        for (byte i = 0; i < 16; ++i) {
                            boxes[i].setText("");
                            boxes[i].setBackground(Color.black);
                        }
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 600);
            }
        if (e.getKeyChar()==27){
            this.dispose();
            new Games();
        }

        if (e.getKeyChar()==10 && !Games.played && !Games.playedSpace){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            Games.playedEnter = true;
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                byte count = 1;
                @Override
                public void run() {
                    if (count > 0) {
                        playPanel.setVisible(true);
                        --count;
                    } else {
                        for (byte i = 0; i < 16; ++i) {
                            boxes[i].setText("");
                            boxes[i].setBackground(Color.black);
                        }
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 600);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
