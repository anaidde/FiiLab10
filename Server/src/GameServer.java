import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    public static final int PORT = 8310;
    private static List<ClientThread> clients = new ArrayList();
    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while(true) {
                System.out.println("Waiting for a client..");
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException exception) {
            System.err.println("Oops.." + exception);
        } finally {
            serverSocket.close();
        }
    }

    public static List<ClientThread> getClients() {
        return clients;
    }
}
