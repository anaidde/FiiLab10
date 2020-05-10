import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class GameClient {
    List<Integer> roomCodes = new ArrayList();

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8310;
        int ok = 0;
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
                String response;
                if(request.equals("exit")) {
                    out.println(request);
                    response = serverOutput.readLine();
                    System.out.println(response);
                    socket.close();
                }
                if(request.equals("stop")) {
                    out.println(request);
                    response = serverOutput.readLine();
                    System.out.println(response);
                    socket.close();
                }
                if(request.equals("create game")){
                    out.println(request);
                    out.flush();
                    response = serverOutput.readLine(); // "Room code: ...
                    System.out.println(response);
                    while(ok == 0) {
                        response = serverOutput.readLine();

                        System.out.println(response);
                        ok =1;
                    }
                    response = serverOutput.readLine();
                    System.out.println(response);
                }
                if(request.equals("join game")) {
                    out.println(request);
                    response = serverOutput.readLine();
                    System.out.println(response);
                    BufferedReader codeReader =
                            new BufferedReader(new InputStreamReader(System.in));
                    String code = codeReader.readLine();
                    out.println(code);
                    response = serverOutput.readLine();
                    System.out.println(response);
                }
                else{
                    out.println(request);
                    response = serverOutput.readLine();
                    System.out.println(response);
                }

            }

        } catch (UnknownHostException exception) {
            System.err.println("No server listening... " + exception);
        }
    }
}
