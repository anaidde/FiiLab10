import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8310;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (!socket.isClosed()) {
                BufferedReader clientInput = new BufferedReader(
                        new InputStreamReader(System.in));
                BufferedReader serverOutput = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                String request = clientInput.readLine();


                out.println(request);

                String response = serverOutput.readLine();

                System.out.println(response);
                if(request.equals("exit")) {
                    socket.close();
                }
                if(request.equals("stop")) {
                    socket.close();
                }
            }

        } catch (UnknownHostException exception) {
            System.err.println("No server listening... " + exception);
        }
    }
}
