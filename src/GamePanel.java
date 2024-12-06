import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    static int[] screenSize = {WIDTH, HEIGHT};

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenSize[0], screenSize[1]));
        this.setLayout(null);
        this.setFocusable(true);

    }
}
