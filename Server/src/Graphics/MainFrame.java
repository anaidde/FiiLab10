package Graphics;

import Game.Player;
import Graphics.Lobbies.CreateGameLobby;
import Graphics.Lobbies.GameLobby;
import Graphics.Lobbies.JoinGameLobby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private final JButton createGameButton = new JButton();
    private final JButton joinGameButton = new JButton();
    private final JButton exitGameButton = new JButton();
    public JoinGameLobby joinGameLobby = new JoinGameLobby(this);
    public CreateGameLobby createGameLobby = new CreateGameLobby(this);
    private Player player1;
    private Player player2;

    public MainFrame() {
        super("Gomoku");
        init();
    }

    private void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createGameButton.setText("Create New Game.Game");
        joinGameButton.setText("Join a Game.Game");
        exitGameButton.setText("Exit Game.Game");
        setLayout(new GridLayout(3, 1));
        createGameButton.addActionListener(this::setCreateGameButton);
        joinGameButton.addActionListener(this::setJoinGameButton);

        add(createGameButton);
        add(joinGameButton);
        add(exitGameButton);
        setSize(300, 400);
    }

    private void setCreateGameButton(ActionEvent event) {
        this.setVisible(false);
        createGameLobby.setVisible(true);
    }

    private void setJoinGameButton(ActionEvent event) {
        this.setVisible(false);
        joinGameLobby.setVisible(true);
    }

    public JoinGameLobby getJoinGameLobby() {
        return joinGameLobby;
    }

    public CreateGameLobby getCreateGameLobby() {
        return createGameLobby;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void startGame() {
        this.createGameLobby.setVisible(false);
        this.joinGameLobby.setVisible(false);
        new GameLobby(this.player1, this.player2).setVisible(true);
    }
}
// - CREATE GAME
// - JOIN GAME
// -SUBMIT MOVE