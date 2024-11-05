import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StageSelectionMenu extends JPanel {
    public StageSelectionMenu(JFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints stageGbc = new GridBagConstraints();
        stageGbc.insets = new Insets(10, 10, 10, 10);

        for (int i = 1; i <= 5; i++) {
            int stage = i;
            JButton stageButton = new JButton("Stage " + i);
            stageButton.addActionListener((ActionEvent stageEvent) -> {
                TopDownShooterGame gamePanel = new TopDownShooterGame(frame);
                frame.add(gamePanel, "Game");
                ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Game");
                gamePanel.startGame(stage);
                gamePanel.requestFocusInWindow();
            });
            add(stageButton, stageGbc);
        }
    }
}
//menu