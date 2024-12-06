import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    static int[] screenSize = {WIDTH, HEIGHT};
    static int[][] backgroundArray = new int[WIDTH][HEIGHT];
    static Flash[] Flashes = new Flash[100];

    Thread gameThread;
    final int FPS = 60;

    long lastTime = LocalDateTime.now().getSecond();
    long presentTime;

    int numberOfVerticals = 17;
    int numberOfHorizontals = 30;

    static Random random = new Random();
    static int randomInt;

    boolean firstRun = true;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenSize[0], screenSize[1]));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.setFocusable(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (firstRun) {
            createBackgroundArray(g2);
            firstRun = false;
        }
        paintBackground(g2);

        randomInt = random.nextInt(numberOfHorizontals)+1;
        presentTime = LocalDateTime.now().getSecond();

        long differenceBetweenTimes = 0;
        if(lastTime>presentTime){
            differenceBetweenTimes = lastTime-presentTime;
        }else{
            differenceBetweenTimes = presentTime-lastTime;
        }

        int countOfFlash = 5;
        if(differenceBetweenTimes>=3){
            for (int j = 0; j < Flashes.length; j++) {
                if (countOfFlash > 0 && Flashes[j] == null) {
                    countOfFlash --;
                    Flashes[j] = new Flash(0, randomInt*50, false,3);
                    randomInt = random.nextInt(numberOfHorizontals)+1;
                }
            }
            lastTime = presentTime;
        }
        drawFlashes(g2);
    }

    private static void createBackgroundArray(Graphics2D g2) {

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                randomInt = random.nextInt(100) + 1;
                if (randomInt >= 50) {
                    backgroundArray[i][j] = 1;
                } else {
                    backgroundArray[i][j] = 2;
                }
            }
        }

        drawingVerticalAndHorizontalLines(50, 50, 4);
        drawingVerticalAndHorizontalLines(25, 25, 5);

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                randomInt = random.nextInt(100) + 1;
                if (randomInt >= 80) {
                    backgroundArray[i][j] = 3;
                }
            }
        }
    }

    private static void drawingVerticalAndHorizontalLines(int yValue, int xValue, int arrayValue) {
        do {
            for (int i = 0; i < WIDTH; i++) {
                backgroundArray[i][yValue] = arrayValue;
            }
            yValue = yValue + 50;
        } while (yValue < HEIGHT);

        do {
            for (int i = 0; i < HEIGHT; i++) {
                backgroundArray[xValue][i] = arrayValue;
            }
            xValue = xValue + 50;
        } while (xValue < WIDTH);
    }

    private static void paintBackground(Graphics2D g2) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (backgroundArray[i][j] == 1) {
                    g2.setColor(new Color(25, 65, 150));
                } else if (backgroundArray[i][j] == 2) {
                    g2.setColor(new Color(23, 55, 130));
                } else if (backgroundArray[i][j] == 3) {
                    g2.setColor(new Color(24, 60, 140));
                } else if (backgroundArray[i][j] == 4) {
                    g2.setColor(new Color(40, 100, 230));
                } else if (backgroundArray[i][j] == 5) {
                    g2.setColor(new Color(44, 111, 255));
                }
                g2.fillRect(i, j, 1, 1);
            }
        }
    }

    public static void drawFlashes(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        for (Flash flash : Flashes) {
            if (flash != null) {
                g2.drawLine(flash.getxCoordinate(), flash.getyCoordinate(), 20, flash.getyCoordinate());
            }
        }
    }

    public void update() {

    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
