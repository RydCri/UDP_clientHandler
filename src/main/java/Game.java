import javax.swing.*;
import java.awt.*;


public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Ping_Pong";

    private JFrame frame;

    public boolean running = false;


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

    public void run() {}

    public static void main(String[] args){
        Game pingPong = new Game();
        pingPong.start();
        pingPong.socketClient.sendPacket("ping".getBytes());
    }
}

