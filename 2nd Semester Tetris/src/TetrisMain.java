import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TetrisMain {
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Tetris Game");
		
		ScorePanel scorePanel = new ScorePanel();
		TetrisPanel gamePanel = new TetrisPanel(scorePanel);
		TetrisPanelListener listener = new TetrisPanelListener(gamePanel, scorePanel);
		
		JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(gamePanel, BorderLayout.CENTER);
        container.add(scorePanel, BorderLayout.EAST);
        
        window.setContentPane(container);
		
        window.setSize(546, 623);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);  
        window.setVisible(true);
	}
}
