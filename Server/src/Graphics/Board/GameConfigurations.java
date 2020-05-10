package Graphics.Board;

import Graphics.Lobbies.GameLobby;

import javax.swing.*;


public class GameConfigurations extends JPanel {
    private final GameLobby gameFrame;
    private String[] sizes = {"10", "11", "12", "13", "14", "15"};
    private JComboBox panelSize = new JComboBox(sizes);
    public GameConfigurations(GameLobby gameFrame) {
        this.gameFrame = gameFrame;
        init();
    }

    private void init() {
        add(new JLabel("Size: "));
        add(panelSize);
    }

    public int getBoardSize() {
        String number = this.panelSize.getSelectedItem().toString();
        int boardSize = Integer.parseInt(number);
        return boardSize;
    }

}
