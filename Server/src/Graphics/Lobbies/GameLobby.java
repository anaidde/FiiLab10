package Graphics.Lobbies;
import Game.Board;
import Game.Player;
import Graphics.Board.BoardGraphics;
import Graphics.Board.GameConfigurations;
import Graphics.Board.MovementPanel;

import javax.swing.*;
import java.awt.*;

public class GameLobby extends JFrame {
    private Player player1;
    private Player player2;
    private Board board;
    private BoardGraphics boardGraphics = new BoardGraphics(this);
    private GameConfigurations gameConfigurations = new GameConfigurations(this);
    private MovementPanel movementPanel = new MovementPanel();

    public GameLobby(Player player1, Player player2) {
        super("Gomoku Game.Game");
        this.player1 = player1;
        this.player2 = player2;
        init();
    }

    private void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(gameConfigurations, BorderLayout.NORTH);
        add(boardGraphics, BorderLayout.CENTER);
        add(movementPanel, BorderLayout.SOUTH);
        setSize(750, 500);
    }

    public GameConfigurations getGameConfigurations() {
        return gameConfigurations;
    }

    public BoardGraphics getBoardGraphics() {
        return boardGraphics;
    }

    public MovementPanel getMovementPanel() {
        return movementPanel;
    }
}
