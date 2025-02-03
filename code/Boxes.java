import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Boxes extends JFrame implements ActionListener, MouseListener ,KeyListener{
    JPanel instructPanel,playPanel;
    JButton theme,start;
    JLabel instruct,backIcon;
    long startTime,endTime,timeTaken,timeLimit=2000;
    int randomNum,randomX,randomY,randomOP,randomRight,optionX=140;
    byte counter=0;
    JPanel[] boxes;
    JButton[] options;
    ImageIcon backL,backD;
    public Boxes(){

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

        randomNum = 5+(int)(Math.random()*8);
        boxes = new JPanel[randomNum];
        for (byte i=0;i<randomNum;++i){
            boxes[i] = new JPanel();
            if (i==0){
                randomX = (int) (Math.random() * 750);
                randomY = 10 + (int) (Math.random() * 450);
            }
            if (i>0) {
                do {
                    randomX = (int) (Math.random() * 750);
                } while (randomX==boxes[i-1].getX());
                do {
                    randomY = 10 + (int) (Math.random() * 450);
                } while (randomY==boxes[i-1].getY());
            }
            boxes[i].setBounds(randomX,randomY,50,50);
            if (Home.Theme){
                boxes[i].setBackground(Color.white);
            } else {
                boxes[i].setBackground(Color.black);
            }
        }

        randomRight = (int)(Math.random()*4);

        options = new JButton[4];
        for (byte i=0;i<4;++i){
            options[i] = new JButton();
        }
        options[randomRight].setText(randomNum+"");
        for (byte i=0;i<4;++i){
            if (i==0){
                do{
                    randomOP = 5+ (int)(Math.random()*8);
                } while (randomOP==randomNum);
            }
            if (i>0) {
                do {
                    randomOP = 5 + (int) (Math.random() * 8);
                } while (randomOP == randomNum || randomOP == Integer.parseInt(options[i - 1].getText()));
            }
            if (Home.Theme){
                options[i].setBackground(Color.black);
                options[i].setForeground(Color.white);
            } else {
                options[i].setBackground(Color.white);
                options[i].setForeground(Color.black);
            }
            options[i].setBounds(optionX,100,80,50);
            options[i].setFont(new Font("Impact",Font.PLAIN,30));
            options[i].setFocusable(false);
            options[i].addActionListener(this);
            options[i].setVisible(false);
            optionX+=150;
            if (i==randomRight){continue;}
            options[i].setText(randomOP+"");
        }

        playPanel = new JPanel();
        playPanel.setPreferredSize(new Dimension(800,500));
        if (Home.Theme){
            playPanel.setBackground(Color.black);
        } else {
            playPanel.setBackground(Color.white);
        }
        playPanel.setLayout(null);
        for (byte i=0;i<randomNum;++i){
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

        instruct = new JLabel("HOW MANY SQUARES ?");
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
        for (byte i=0;i<4;++i){
            instructPanel.add(options[i]);
        }
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
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==start){
            startTime = System.currentTimeMillis();
            start.setVisible(false);
            playPanel.setVisible(true);
            for (byte i=0;i<4;++i){
                options[i].setVisible(true);
            }
            Games.played=true;
        }
        for (byte i=0;i<4;++i){
            if (e.getSource()==options[i]){
                endTime = System.currentTimeMillis();
                timeTaken = endTime-startTime;
                this.dispose();
                if (options[i].getText().equals(randomNum+"")){
                    new Result(1);
                    Result.time.setText("YOU TOOK "+timeTaken+" ms");
                    if (timeTaken<timeLimit){
                        Result.note.setText("<html><h1>EXERCISE IS PASSED</h1><br><h1>THE TIME LIMIT WAS 2000 ms</h1></html>");
                    } else {
                        Result.note.setText("<html><h1>EXERCISE IS FAILED</h1><br><h1>THE TIME LIMIT WAS 2000 ms</h1></html>");
                    }
                    new Result();
                } else {
                    new Result(1);
                    Result.time.setText("<html><h1>EXERCISE IS FAILED.</h1></html>");
                    Result.note.setText("<html><h1>YOU CHOSE THE WRONG OPTION.</h1></html>");
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
                for (byte i=0;i<4;++i){
                    options[i].setBackground(Color.white);
                    options[i].setForeground(Color.black);
                }
                for (byte i=0;i<randomNum;++i){
                    boxes[i].setBackground(Color.black);
                }
                counter++;
                Home.Theme=false;
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
                for (byte i=0;i<4;++i){
                    options[i].setBackground(Color.black);
                    options[i].setForeground(Color.white);
                }
                for (byte i=0;i<randomNum;++i){
                    boxes[i].setBackground(Color.white);
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
            playPanel.setVisible(true);
            for (byte i=0;i<4;++i){
                options[i].setVisible(true);
            }
            Games.playedSpace = true;
        }
            if (e.getKeyChar()==27){
                this.dispose();
                new Games();
            }
            if (e.getKeyChar()==10 && !Games.playedSpace && !Games.played){
                startTime = System.currentTimeMillis();
                start.setVisible(false);
                playPanel.setVisible(true);
                for (byte i=0;i<4;++i){
                    options[i].setVisible(true);
                }
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
