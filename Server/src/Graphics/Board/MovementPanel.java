package Graphics.Board;

import javax.swing.*;

public class MovementPanel extends JPanel {
    private JButton submitMove = new JButton("Submit Move");
    private JButton exitGame = new JButton("Exit Game.Game");
    public MovementPanel() {
        init();
    }

    private void init() {
        add(submitMove);
        add(exitGame);
    }
}
