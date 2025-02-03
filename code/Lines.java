import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Lines extends JFrame implements ActionListener, MouseListener,KeyListener {
    JPanel instructPanel,playPanel;
    JLabel instruct,backIcon;
    JPanel[] lines;
    long startTime,endTime,timeTaken,timeLimit=1200;
    int randomPosition ,y=100,width = 300,longest;
    JButton theme,start;
    byte counter=0;
    ImageIcon backD,backL;
    public Lines(){

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


        lines = new JPanel[7];
        for (byte i=0;i<7;++i){
            randomPosition = 3 + (int) (Math.random() * 100);
            lines[i] = new JPanel();
            lines[i].setBounds(0,y,width+randomPosition,20);
            if (Home.Theme){
                lines[i].setBackground(Color.white);
            } else {
                lines[i].setBackground(Color.black);
            }
            lines[i].addMouseListener(this);
            y+=60;
        }

        longest = lines[0].getWidth();
        for (byte i=1;i<7;++i){
            if (lines[i].getWidth()>longest){
                longest = lines[i].getWidth();
            }
        }

        playPanel = new JPanel();
        playPanel.setPreferredSize(new Dimension(800,500));
        if (Home.Theme){
            playPanel.setBackground(Color.black);
        } else {
            playPanel.setBackground(Color.white);
        }
        playPanel.setLayout(null);
        playPanel.setVisible(false);

        for (byte i=0;i<7;++i){
            playPanel.add(lines[i]);
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

        instruct = new JLabel("FIND THE LONGEST LINE");
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

        this.setTitle("LINES");
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
        this.add(instructPanel,BorderLayout.NORTH);
        this.add(playPanel,BorderLayout.SOUTH);
        this.addKeyListener(this);
        this.setIconImage(Home.logo.getImage());
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){

        if (e.getSource()==start){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            playPanel.setVisible(true);
            Games.played=true;
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
                for (byte i=0;i<7;++i){
                    lines[i].setBackground(Color.black);
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
                for (byte i=0;i<7;++i){
                    lines[i].setBackground(Color.white);
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
        for (byte i=0;i<7;++i){
            if (e.getSource()==lines[i]){
                endTime = System.currentTimeMillis();
                timeTaken = endTime-startTime;
                this.dispose();
                if (lines[i].getWidth()==longest){
                    new Result(1);
                    Result.time.setText("YOU TOOK "+timeTaken+" ms.");
                    if (timeTaken<timeLimit){
                        Result.note.setText("<html><h1>EXERCISE IS PASSED</h1><br><h1>THE TIME LIMIT WAS 1200 ms</h1></html>");
                    } else {
                        Result.note.setText("<html><h1>EXERCISE IS FAILED</h1><br><h1>THE TIME LIMIT WAS 1200 ms</h1></html>");
                    }
                    new Result();
                } else {
                    new Result(1);
                    Result.time.setText("INCORRECT LINE CHOSEN.");
                    new Result();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==start) {
            if (Home.Theme) {
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
        if (e.getSource()==start) {
            if (Home.Theme) {
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
            Games.playedSpace=true;
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
