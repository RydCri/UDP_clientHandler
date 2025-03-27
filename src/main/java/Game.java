import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Ping_Pong";

    private JFrame frame;

    public boolean running = false;
    public int tickCount = 0;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private int[] colors = new int[7 * 7 * 7];

    private Screen screen;
    public InputHandler input;
    public Level level;
    public Player player;

    private GameClient socketClient;
    private GameServer socketServer;

    public Game() {
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        socketClient = new GameClient(this, "localhost");
        socketClient.start();

    }

    public void init() {
        int index = 0;
        for (int r = 0; r < 6; r++) {
            for (int g = 0; g < 6; g++) {
                for (int b = 0; b < 6; b++) {
                    int rr = (r * 255 / 5);
                    int gg = (g * 255 / 5);
                    int bb = (b * 255 /5);

                    colors[index++] = rr << 16 | gg << 8 | bb;

                }
            }
        }
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();

        int dialogue = JOptionPane.showConfirmDialog(this, "Start Server?");
        if (dialogue == 0 ) {
            socketServer = new GameServer(this);
            socketServer.start();
            System.out.println("Server Running." + " Thread ID: " + socketServer.threadId());
        }

        socketClient = new GameClient(this, "localhost");
        socketClient.start();
        System.out.println("Client Running." + " Thread ID: " + socketClient.threadId());
    }

    public synchronized void stop() {
        running = false;
    }

    public void run() {
        long lastTime = System.nanoTime();
        double nsTick = 100000000D / 60D;
    }

    public static void main(String[] args){
        Game bobp = new Game();
        bobp.start();
        bobp.socketClient.sendPacket("ping".getBytes());
    }
}

