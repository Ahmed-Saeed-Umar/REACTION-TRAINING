import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Games extends JFrame implements ActionListener, MouseListener,KeyListener {
    JButton[] games;
    JPanel gamesPanel;
    JButton theme;
    byte counter = 0;
    static byte gameNumber;
    int yCoordinate = 160,playCounter=0;
    ImageIcon backD,backL;
    static boolean played=false,playedSpace=false,playedEnter=false;
    JLabel backIcon;
    Games() {

        backD = new ImageIcon("back D.png");
        backL = new ImageIcon("back L.png");

        backIcon = new JLabel();
        backIcon.setBounds(3,3,80,60);
        backIcon.setForeground(Color.white);
        if (Home.Theme) {
            backIcon.setIcon(backD);
        } else {
            backIcon.setIcon(backL);
        }
        backIcon.addMouseListener(this);

        theme = new JButton();
        theme.setBounds(620, 15, 120, 40);
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

        this.setTitle("REACTION TRAINING");
        this.setSize(800, 700);
        this.setLayout(null);
        this.setBackground(Color.black);

        gamesPanel = new JPanel();
        gamesPanel.setSize(800, 700);
        gamesPanel.setLayout(null);
        if (Home.Theme){
            gamesPanel.setBackground(Color.black);
        } else{
            gamesPanel.setBackground(Color.white);
        }
        gamesPanel.add(theme);
        gamesPanel.add(backIcon);
        games = new JButton[7];
        for (int i = 0; i < 7; ++i) {
            games[i] = new JButton();
        }
            games[0].setText("SOLVE SIMPLE EQUATIONS");
            games[1].setText("FIND THE NUMBERS IN ASCENDING ORDER");
            games[2].setText("FIND THE LONGEST LINE");
            games[3].setText("VISUAL MEMORY");
            games[4].setText("SQUARE COUNT");
            games[5].setText("MORE 100");
            games[6].setText("FIND THE NUMBER");
            for (int i=0;i<7; ++i){
            games[i].setBounds(50, yCoordinate, 700, 50);

            games[i].setFont(new Font("Consolas", Font.ITALIC, 30));
            games[i].setFocusable(false);
            games[i].addActionListener(this);
            games[i].addMouseListener(this);
            gamesPanel.add(games[i]);
            yCoordinate += 60;
            if (i==0){continue;}
                if (Home.Theme){
                    games[i].setBackground(Color.darkGray.darker().darker());
                    games[i].setForeground(Color.white);
                } else {
                    games[i].setBackground(Color.white);
                    games[i].setForeground(Color.BLACK);
                }
        }
        if (Home.Theme){
            games[0].setBackground(Color.white);
            games[0].setForeground(Color.black);
        } else {
            games[0].setBackground(Color.black);
            games[0].setForeground(Color.white);
        }
        this.add(gamesPanel);
        this.setIconImage(Home.logo.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == theme) {
            if (counter%2==0) {
                for (byte i = 0; i < 7; ++i) {
                    games[i].setBackground(Color.white);
                    games[i].setForeground(Color.black);
                }
                backIcon.setIcon(backL);
                gamesPanel.setBackground(Color.white);
                theme.setText("DARK MODE");
                theme.setForeground(Color.white);
                theme.setBackground(Color.black);
                counter++;
                Home.Theme=false;
            } else if (counter%2==1 ) {
                for (byte i = 0; i < 7; ++i) {
                    games[i].setBackground(Color.black);
                    games[i].setForeground(Color.white);
                }
                gamesPanel.setBackground(Color.black);
                theme.setText("LIGHT MODE");
                theme.setForeground(Color.black);
                theme.setBackground(Color.white);
                backIcon.setIcon(backD);
                counter++;
                Home.Theme=true;
            }
        }
        if (e.getSource()==games[0]){
            this.dispose();
            gameNumber=1;
            new Equations();
        }
        if (e.getSource()==games[1]){
            this.dispose();
            gameNumber=2;
            new Maths();
        }
        if (e.getSource()==games[2]){
            this.dispose();
            gameNumber=3;
            new Lines();
        }
        if (e.getSource()==games[3]){
            this.dispose();
            gameNumber=4;
            new Memory();
        }
        if (e.getSource()==games[4]){
            this.dispose();
            gameNumber=5;
            new Boxes();
        }
        if (e.getSource()==games[5]){
            this.dispose();
            gameNumber=6;
            new Hundred();
        }
        if (e.getSource()==games[6]){
            this.dispose();
            gameNumber=7;
            new Number();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==backIcon){
            this.dispose();
            new Home();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        for (byte i=0;i<7;++i){
            if (e.getSource()==games[i]){
                if (Home.Theme){
                    games[i].setBackground(Color.white);
                    games[i].setForeground(Color.black);
                    for (byte j=0;j<7;++j){
                        if (j==i){continue;}
                        games[j].setBackground(Color.darkGray.darker().darker());
                        games[j].setForeground(Color.white);
                    }
                } else {
                    games[i].setBackground(Color.black);
                    games[i].setForeground(Color.white);
                    for (byte j=0;j<7;++j){
                        if (j==i){continue;}
                        games[j].setBackground(Color.white);
                        games[j].setForeground(Color.black);
                    }
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (byte i=0;i<7;++i){
            if (e.getSource()==games[i]){
                if (Home.Theme){
                games[i].setBackground(Color.darkGray.darker().darker());
                games[i].setForeground(Color.white);
                for (byte j=0;j<7;++j){
                    if (j==i){continue;}
                    games[j].setBackground(Color.darkGray.darker().darker());
                    games[j].setForeground(Color.white);
                }
                } else {
                games[i].setBackground(Color.white);
                games[i].setForeground(Color.black);
                    for (byte j=0;j<7;++j){
                        if (j==i){continue;}
                        games[j].setBackground(Color.WHITE);
                        games[j].setForeground(Color.BLACK);
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar()==27){
            this.dispose();
            new Home();
        }
        if (e.getKeyChar()==10){
            this.dispose();
            switch (playCounter){
                case 0:
                    new Equations();
                    gameNumber=1;
                    break;
                case 1:
                    new Maths();
                    gameNumber=2;
                    break;
                case 2:
                    new Lines();
                    gameNumber=3;
                    break;
                case 3:
                    new Memory();
                    gameNumber=4;
                    break;
                case 4:
                    new Boxes();
                    gameNumber=5;
                    break;
                case 5:
                    new Hundred();
                    gameNumber=6;
                    break;
                case 6:
                    new Number();
                    gameNumber=7;
                    break;
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 40) {
            if (playCounter + 1 < 7) {
                playCounter++;
                    if (Home.Theme) {
                        games[playCounter].setBackground(Color.white);
                        games[playCounter].setForeground(Color.black);
                        for (byte i = 0; i < 7; ++i) {
                            if (i == playCounter) {
                                continue;
                            }
                            games[i].setBackground(Color.darkGray.darker().darker());
                            games[i].setForeground(Color.white);
                        }
                    } else {
                        games[playCounter].setBackground(Color.black);
                        games[playCounter].setForeground(Color.white);
                        for (byte i = 0; i < 7; ++i) {
                            if (i == playCounter) {
                                continue;
                            }
                            games[i].setBackground(Color.white);
                            games[i].setForeground(Color.black);
                        }
                    }
                }
            }

        if (e.getKeyCode() == 38) {
            if (playCounter - 1 >= 0) {
                playCounter--;
                    if (Home.Theme) {
                        games[playCounter].setBackground(Color.white);
                        games[playCounter].setForeground(Color.black);
                        for (byte i = 0; i < 7; ++i) {
                            if (i == playCounter) {
                                continue;
                            }
                            games[i].setBackground(Color.darkGray.darker().darker());
                            games[i].setForeground(Color.white);
                        }
                    } else {
                        games[playCounter].setBackground(Color.black);
                        games[playCounter].setForeground(Color.white);
                        for (byte i = 0; i < 7; ++i) {
                            if (i == playCounter) {
                                continue;
                            }
                            games[i].setBackground(Color.white);
                            games[i].setForeground(Color.black);
                        }
                    }
                }
            }
        }

    @Override
    public void keyReleased(KeyEvent e) {}
}
