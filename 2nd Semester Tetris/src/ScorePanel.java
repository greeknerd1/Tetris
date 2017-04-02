import java.awt.*;
import javax.swing.*;

public class ScorePanel extends JPanel{

	private int score;
	private JSlider slider;
	private JLabel lineLabel;
	private JButton restart;
	private JButton highlightShape;
	private JLabel instructionLabel;
	private JLabel instructionPauseLabel;
	private JLabel instructionMoveLabel;
	private JLabel instructionSpaceLabel;
	
	public ScorePanel(){
		this.setLayout(new GridLayout(8,1));
		
		//You can add components to a JPanel
		JPanel container = new JPanel();
		JPanel instructionContainer = new JPanel();
		instructionContainer.setLayout(new GridLayout(5, 1));
		JLabel level = new JLabel("Level: ");
		slider = new JSlider(1, 5, 1);
		slider.setMajorTickSpacing(2);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		
		container.add(level);
		container.add(slider);
		
		lineLabel = new JLabel("Lines: " + score);
		lineLabel.setFont(new Font("", Font.BOLD, 30));
		
		instructionLabel = new JLabel("Instructions:");
		instructionPauseLabel = new JLabel("Click P to pause and unpause");
		instructionMoveLabel = new JLabel("Use the arrow keys to move the game piece");
		instructionSpaceLabel = new JLabel("Press the Space Bar to hard drop");
		instructionLabel.setFont(new Font("", Font.PLAIN, 15));
		instructionPauseLabel.setFont(new Font("", Font.PLAIN, 11));
		instructionMoveLabel.setFont(new Font("", Font.PLAIN, 11));
		instructionSpaceLabel.setFont(new Font("", Font.PLAIN, 11));
		
		
		restart = new JButton("RESTART");
		highlightShape = new JButton("Enable/Disable Highlighting");
		
		this.add(container);
		this.add(instructionContainer);
		this.add(lineLabel);
		instructionContainer.add(instructionLabel);
		instructionContainer.add(instructionPauseLabel);
		instructionContainer.add(instructionMoveLabel);
		instructionContainer.add(instructionSpaceLabel);
		this.add(highlightShape);
		this.add(restart);
	}

	public JButton getHighlightShape() {
		return highlightShape;
	}

	public void setHighlightShape(JButton highlightShape) {
		this.highlightShape = highlightShape;
	}

	public JButton getRestart() {
		return restart;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		lineLabel.setText("Lines: " + score);
	}

	public JSlider getSlider() {
		return slider;
	}

	public void setSlider(JSlider slider) {
		this.slider = slider;
	}

	public JLabel getScoreLabel() {
		return lineLabel;
	}

	public void setScoreLabel(JLabel scoreLabel) {
		this.lineLabel = scoreLabel;
	}
	
	public void incrementScore() {
		score++;
		lineLabel.setText("Lines: " + score);
	}
}
