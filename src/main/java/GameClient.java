import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GameClient {
    private InetAddress IpAddress;
    private DatagramSocket socket;

    public Game game;

    public GameClient(Game game, String IpAddress) {
        this.game = game;
        this.IpAddress = InetAddress.IpAddress;
        //todo: Socket + Host Exception
        // void run()
    }

}