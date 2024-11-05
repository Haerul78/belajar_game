import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {
    public MainMenu(JFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton playButton = new JButton("Play");
        JButton exitButton = new JButton("Exit");

        playButton.addActionListener((ActionEvent e) -> {
            ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "StageSelection");
        });

        exitButton.addActionListener((ActionEvent e) -> System.exit(0));

        add(playButton, gbc);
        add(exitButton, gbc);
    }
}
//menu
//raka
//java