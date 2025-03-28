import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Game";

    boolean running = false;
    public int tickCount = 0;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private JFrame frame;

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
    }

    public synchronized void start() {
    running = true;
    new Thread(this).start();
    }
    public synchronized void stop() {
    running = false;
    }
    public void run() {

    long lastTime = System.nanoTime();
    double nsTick = 1000000000D/60D;

    int ticks = 0;
    int frames = 0;

    long Timer = System.currentTimeMillis();
    double delta = 0;

    while (running){
        long now = System.nanoTime();
        delta += (now - lastTime) / nsTick;
        lastTime = now;
        boolean shouldRender = false;

        while (delta >= 1) {
            ticks++;
            tick();
            delta -= 1;
            shouldRender = true;
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(shouldRender){
            frames++;
            render();
        }
        frames++;

        if (System.currentTimeMillis() - Timer >= 1000) {
            Timer += 1000;
            System.out.println(frames + "," + ticks);
            frames = 0;
            ticks = 0;
        }
    }
    }

    public void tick() {
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();

    }

    public static void main(String[] args){
        new Game().start();
    }
}