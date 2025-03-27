import java.io.IOException;
import java.net.*;


public class GameServer extends Thread{
    private DatagramSocket socket;

    public Game game;

    public GameServer(Game game) {
        this.game = game;
        try {
            this.socket = new DatagramSocket(1501);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                System.out.println("Server received packet from /" + packet.getSocketAddress());
            } catch (IOException e) {
                System.err.println("Error receiving packet: " + e.getMessage());
            }
                //Ping pong test
                String msg = new String(packet.getData());
                System.out.println("Client says " + msg.trim());
                if (msg.trim().equalsIgnoreCase("ping")) {
                    sendPacket("pong".getBytes(), packet.getAddress(), packet.getPort());
                }
        }
    }

    public void sendPacket(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        }catch (IOException e ) {
            e.printStackTrace();
        }
    }
}