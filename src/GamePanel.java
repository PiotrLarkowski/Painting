import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    static int[] screenSize = {WIDTH, HEIGHT};

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenSize[0], screenSize[1]));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.setFocusable(true);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Random random = new Random();
        int randomInt;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                randomInt = random.nextInt(100)+1;
                if(randomInt>=50){
                    g2.setColor(new Color(25,65,150));
                }else{
                    g2.setColor(new Color(20,55,130));
                }
                g2.fillRect(i,j,1,1);
            }
        }
        int xValue = 50;
        int yValue = 50;
        g2.setColor(new Color(40,100,230));

        do {
            g2.drawLine(0, yValue, WIDTH, yValue);
            yValue += 50;
        } while (yValue <= HEIGHT);

        do {
            g2.drawLine(xValue, 0, xValue, HEIGHT);
            xValue += 50;
        } while (xValue <= WIDTH);

        g2.setColor(new Color(23,60,140));
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                randomInt = random.nextInt(100)+1;
                if(randomInt>=80){
                    g2.fillRect(i,j,1,1);
                }
            }
        }
        drawFlash(g2);
    }

    public static void drawFlash(Graphics2D g2){
    }
}
