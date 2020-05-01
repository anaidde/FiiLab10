import com.sun.security.ntlm.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null;
    public ClientThread (Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (!socket.isClosed()) {
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                String request = input.readLine();

                String response = "N-am inteles ce zici acolo";


                System.out.println("from client: " + request);

                if (request.equals("stop")) {
                    response = "Server stopped";
                    out.println(response); // ii da raspunsul
                    out.flush();
                    System.exit(1);
                    this.socket.close();
                } else if (request.equals("exit")) {
                    response = "Client closed";
                    out.println(response); // ii da raspunsul
                    out.flush();
                    this.socket.close();
                } else {
                    response = "Server received the request ... ";
                }
                out.println(response); // ii da raspunsul
                out.flush();
            }
        } catch (IOException exception) {
            System.err.println("Communication error..." + exception);
        }
    }


}
