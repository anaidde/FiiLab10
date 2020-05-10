package Graphics.Lobbies;
import Game.Player;
import Graphics.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JoinGameLobby extends JFrame {
    final MainFrame mainFrame;
    private JTextField roomCode = new JTextField();
    private JButton enterButton = new JButton("Enter");
    private Boolean joinedGame = false;

    public JoinGameLobby(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 3));
        add(new JLabel("Enter room code: "));
        add(roomCode);
        enterButton.addActionListener(this::EnterButtonClicked);
        add(enterButton);

        setSize(350, 100);
        setVisible(true);
    }

    private void EnterButtonClicked(ActionEvent event) {
        if(checkRoomCode(this.roomCode.getText())) {
            System.out.println("corect");
            this.joinedGame = true;
            this.mainFrame.setPlayer2(new Player("player2"));
        }
    }

    public Boolean checkRoomCode(String code) {
        try{
            File myFile = new File("currentGamesCodes.txt");
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                return data.equals(code);
            }
        } catch (FileNotFoundException exception) {
            System.out.println("An error occured.");
            exception.printStackTrace();
        }
        return false;
    }

    public Boolean getIfPlayerHasJoined() {
        return this.joinedGame;
    }
}
