import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.lang.Thread;

public class GameServer {
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

                // Trim the byte array to actual data length
                String received = new String(packet.getData(), 0, packet.getLength()).trim();
                System.out.println("Received Server Packet: " + received);
            } catch (IOException e) {
                System.err.println("Error receiving packet: " + e.getMessage());
            }
                //Ping pong test
                String msg = new String(packet.getData());
                if (msg.equalsIgnoreCase("ping")) {
                    System.out.println("Client connected: " + msg);
                    sendPacket("pong".getBytes());
                }
        }
    }

    public void sendPacket(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, IpAddress, 1501);
        try {
            socket.send(packet);
        }catch (IOException e ) {
            e.printStackTrace();
        }
    }
}