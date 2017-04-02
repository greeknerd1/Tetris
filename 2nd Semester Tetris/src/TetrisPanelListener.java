
import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;  //*** not java.util.Timer!!!
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TetrisPanelListener implements KeyListener, FocusListener, ActionListener, ChangeListener, MouseListener  {

	private TetrisPanel gamePanel;
	private Timer timer;
	private ScorePanel scorePanel;

	public TetrisPanelListener(TetrisPanel gamePanel, ScorePanel scorePanel) {
		this.gamePanel = gamePanel;
		gamePanel.addKeyListener(this);
		gamePanel.addFocusListener(this);
		gamePanel.addMouseListener(this);
		scorePanel.getSlider().addChangeListener(this);
		scorePanel.getRestart().addActionListener(this);
		scorePanel.getRestart().setActionCommand("restart");
		scorePanel.getHighlightShape().addActionListener(this);
		scorePanel.getHighlightShape().setActionCommand("highlight");
		timer = new Timer(gamePanel.getGameSpeed(), this);
		timer.setActionCommand("TimerReset"); 
		this.scorePanel = scorePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_LEFT && gamePanel.getPaused() == false && !gamePanel.getGameOver()) {
			Shape shape = gamePanel.getCurrentShape();
			if (gamePanel.canMoveLeft(shape)) { 
				gamePanel.removeShapeFromBoard(shape);
				shape.moveLeft();
				gamePanel.addShapeToBoard(shape);
			}
		}
		else if(code == KeyEvent.VK_UP && gamePanel.getPaused() == false && !gamePanel.getGameOver()) { 
			Shape shape = gamePanel.getCurrentShape();
			if (shape != null) {
				gamePanel.removeShapeFromBoard(shape);
				Shape newShape = gamePanel.sameShape(shape);
				newShape.rotateRight();
				if (gamePanel.canAddShapeToBoard(newShape)) {
					gamePanel.setCurrentShape(newShape);
					gamePanel.addShapeToBoard(newShape);
				}
				else {
					gamePanel.addShapeToBoard(shape);
				}
			}
		}
		else if (code == KeyEvent.VK_RIGHT && gamePanel.getPaused() == false && !gamePanel.getGameOver()) {  
			Shape shape = gamePanel.getCurrentShape();
			if (gamePanel.canMoveRight(shape)) { 
				gamePanel.removeShapeFromBoard(shape);
				shape.moveRight();
				gamePanel.addShapeToBoard(shape);
			}
		}
		else if (code == KeyEvent.VK_DOWN && gamePanel.getPaused() == false && !gamePanel.getGameOver()) {
			Shape shape = gamePanel.getCurrentShape();
			if (gamePanel.canMoveDown(shape)) {
				gamePanel.removeShapeFromBoard(shape);
				shape.moveDown();
				gamePanel.addShapeToBoard(shape);
				timer.restart(); 
			}
		}
		else if(code == KeyEvent.VK_P && !gamePanel.getGameOver()) {
			if (gamePanel.getPaused() == true) {
				timer.start();
			}
			else {
				timer.stop();
			}
			gamePanel.setPaused(!gamePanel.getPaused());
		}
		else if(code == KeyEvent.VK_SPACE && gamePanel.getPaused() == false && !gamePanel.getGameOver()) {
			Shape shape = gamePanel.getCurrentShape();
			if (shape != null) {
				gamePanel.removeShapeFromBoard(shape);
				while (gamePanel.canMoveDown(shape)) {
					shape.moveDown();
				}
				gamePanel.addShapeToBoard(shape);
				gamePanel.setCurrentShape(null);
				timer.restart();
			}
		}
		gamePanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		Shape shape = gamePanel.getCurrentShape();
		if(str.equals("TimerReset")) {
			if (gamePanel.canMoveDown(shape) && !gamePanel.getGameOver()) {
				gamePanel.removeShapeFromBoard(shape);
				shape.moveDown();
				gamePanel.addShapeToBoard(shape);
				timer.restart();
			}
			else {
				gamePanel.removeRowIfNecessary(gamePanel.getGraphics());
				gamePanel.setCurrentShape(gamePanel.getRandomShape());

				for (int i = 0; i < 4; i++) {
					gamePanel.getCurrentShape().moveRight();
				}
				
				if (gamePanel.canAddShapeToBoard(gamePanel.getCurrentShape()) && !gamePanel.getGameOver()) {
					gamePanel.addShapeToBoard(gamePanel.getCurrentShape());
					timer.restart();
				}
				else {
					timer.stop();
					gamePanel.setGameOver(true);
				}
			}
		}
		else if(str.equals("restart")) {
			gamePanel.restart();
			gamePanel.setCurrentShape(gamePanel.getRandomShape());
			for (int i = 0; i < 4; i++) 
				gamePanel.getCurrentShape().moveRight();
			gamePanel.setGameOver(false);
			scorePanel.setScore(0); 
			gamePanel.setPaused(true);
			gamePanel.addShapeToBoard(gamePanel.getCurrentShape());
		}
		else if(str.equals("highlight")) {
			gamePanel.setHighlightShape(!gamePanel.isHighlightShape());
		}
		gamePanel.repaint();

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		gamePanel.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		timer.stop();
		gamePanel.setPaused(true);
		gamePanel.repaint();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		JSlider source = (JSlider)e.getSource();
		if (source.getValueIsAdjusting()) {
			timer.stop();
			gamePanel.setPaused(true);
		}
		else if (!source.getValueIsAdjusting()) {
			gamePanel.setPaused(false);
			int difficultyLevel = source.getValue();
			gamePanel.setSliderLevel(difficultyLevel);
			timer.setInitialDelay(gamePanel.getGameSpeed());
			timer.start();
		}
		gamePanel.repaint();
		gamePanel.requestFocusInWindow();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		gamePanel.setPaused(false);
		timer.start();
		gamePanel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
