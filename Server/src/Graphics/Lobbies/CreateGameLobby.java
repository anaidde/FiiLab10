package Graphics.Lobbies;
import Game.Player;
import Graphics.Board.BoardGraphics;
import Graphics.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateGameLobby extends JFrame {
    final MainFrame mainFrame;
    private JLabel gameKeyLabel = new JLabel("Game.Game Key: ");
    private JLabel gameKey = new JLabel();

    public CreateGameLobby(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        int keyNumber = new Random().nextInt(9999-1000+1) + 1000;
        gameKey.setText(Integer.toString(keyNumber));
        writeCode(keyNumber);
        setLayout(new GridLayout(1, 3));
        add(new JLabel());
        add(gameKeyLabel);
        add(gameKey);
        setSize(300, 100);
        setVisible(true);
        waitForAMate(keyNumber);

    }

    public void writeCode(int keyNumber) {
        try {
            FileWriter myWriter = new FileWriter("currentGamesCodes.txt");
            myWriter.write(Integer.toString(keyNumber));
            myWriter.close();
        } catch (IOException exception) {
            System.out.println("An error occured");
            exception.printStackTrace();
        }
    }

    public void waitForAMate(int roomCode) {
        while (true) {
            if(this.mainFrame.getJoinGameLobby().getIfPlayerHasJoined()) {
                System.out.println(this.mainFrame.getJoinGameLobby().getIfPlayerHasJoined());
                this.mainFrame.setPlayer1(new Player("player1"));
                this.mainFrame.startGame();
                break;
            }
        }
    }


}
