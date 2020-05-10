import Game.Board;
import Game.Game;
import Game.Player;
import Graphics.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientThread extends Thread {
    private Socket socket = null;
    private static Player player1;
    private static Player player2;
    private List<ClientThread> clients = new ArrayList();
    private List<Integer> roomCodes = new ArrayList();
    private List<Integer> enteredRooms = new ArrayList<>();
    public ClientThread (Socket socket) {
        this.socket = socket;
    }
    int ok = 0;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public List<Integer> getRoomCodes() {
        return roomCodes;
    }

    public List<Integer> getEnteredRooms() {
        return enteredRooms;
    }

    public void run() {
        int keyNumber = new Random().nextInt(9999-1000+1) + 1000;
        setOk(0);
        //new MainFrame().setVisible(true);
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (!socket.isClosed()) {
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                this.clients = GameServer.getClients();
                clients.add(this);

                for(ClientThread clientThread : this.clients) {
                    clientThread.getRoomCodes().add(keyNumber);
                }
                String request = input.readLine();

                String response = null;

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
                } else if(request.equals("join game")) {
                    response = "Enter room code: ";
                    out.println(response);
                    out.flush();
                    String code = input.readLine();
                    System.out.println(code);
                    for(ClientThread clientThread : this.clients) {
                        for(int code1 : clientThread.getRoomCodes()){
                            if(code1 == keyNumber)
                            {
                                for(ClientThread clientThread1 : this.clients) {
                                    clientThread1.getEnteredRooms().add(code1);
                                }
                                ok = 1;
                                break;
                            }
                        }
                    }

                    if(ok == 1 ) {
                        response = "Joined Game Successfully!";
                        player2 = new Player("player2");
                        Game game = new Game(new Board(15), player1, player2);

                    } else response = "Incorred room code";

                } else if (request.equals("create game")) {
                    player1 = new Player("player1");
                    response = "Room code: " + keyNumber + ". Waiting for a mate..";
                    out.println(response);
                    out.flush();
                    System.out.println("ok" + ok);
                    response = Integer.toString(getOk());
                    out.println();
                    out.flush();
                    while(ok == 0) {
                        for(ClientThread clientThread : this.clients){
                            for(int number : clientThread.enteredRooms) {
                                if (number == keyNumber) {
                                    setOk(1);
                                    break;
                                }
                            }
                            response = Integer.toString(getOk());
                            System.out.println("Raspuns:" + response);
                            out.println();
                            out.flush();
                        }
                    }

                }
                else {
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
