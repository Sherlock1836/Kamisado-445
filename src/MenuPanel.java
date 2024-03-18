import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
    private JButton playButton;
    private JButton exitButton;
    private JPanel buttonPanel;
    public MenuPanel(){
        setLayout(new GridBagLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Kamisado.showPanel("GamePanel");
            }
        });
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                Kamisado.endGame();
            }
        });

        configureButton(playButton);
        configureButton(exitButton);
        
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitButton);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonPanel, gbc);
    }

    private void configureButton(JButton b) {
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFocusPainted(false);
    }
}
