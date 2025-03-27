import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.lang.Thread;

public class GameClient {
    private InetAddress IpAddress;
    private DatagramSocket socket;

    public Game game;

    public GameClient(Game game, String IpAddress) {
        this.game = game;
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            this.IpAddress = InetAddress.getByName(IpAddress);
        }
        catch (UnknownHostException e) {
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