package Graphics.Board;

import Game.Board;
import Graphics.Lobbies.GameLobby;

import javax.swing.*;
import java.awt.*;

public class BoardGraphics extends JPanel {
    private final GameLobby gameFrame;
    private Graphics g;

    public BoardGraphics(GameLobby gameFrame) {
        this.gameFrame = gameFrame;
        int size = this.gameFrame.getGameConfigurations().getBoardSize();
        init(size, size);
    }

    private void init(int width, int height) {
        for(int x=0;x<width;x++)
        {
            for(int y=0;y<height;y++)
            {
                g.drawRect(x*10,y*10,10,10);
            }
        }
    }



}
