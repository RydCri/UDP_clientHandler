import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

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
    public void run(){
        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Received Server Packet: " + new String(packet.getData()));
            // new String(packet.getData(), 0, packet.getLength))
            }
        }
    }

}