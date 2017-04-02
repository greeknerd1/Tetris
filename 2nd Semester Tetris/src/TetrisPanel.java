
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel {

	ScorePanel scorePanel;

	private final int ROWS = 20;
	private final int COLS = 10;
	private final int BLOCK_WIDTH;
	private final int BLOCK_HEIGHT; 

	private Block[][] board;

	private Shape[] allShapes; 
	private Shape currentShape;
	private Shape nextShape;

	private int sliderLevel = 1;
	private boolean paused = false;
	private boolean gameOver = false;
	private boolean highlightShape = true;

	public TetrisPanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		board = new Block[ROWS][COLS];
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(300, 600);
		BLOCK_WIDTH = (this.getWidth() + this.getHeight())/(ROWS + COLS);
		BLOCK_HEIGHT = (this.getWidth() + this.getHeight())/(ROWS + COLS);
		currentShape = getRandomShape(); 
		nextShape = getRandomShape();

		for (int i = 0; i < 4; i++) 
			currentShape.moveRight();

		this.addShapeToBoard(currentShape); 

		this.addMouseListener( new MouseAdapter() { 
			public void mousePressed(MouseEvent evt) {
				requestFocusInWindow();
			}
		} );

	}

	public boolean isHighlightShape() {
		return highlightShape;
	}

	public void setHighlightShape(boolean highlightShape) {
		this.highlightShape = highlightShape;
	}

	public void setGameOver(boolean bool) {
		gameOver = bool;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void getNewBoard() {
		board = new Block[ROWS][COLS];
	}

	public Block[][] getBoard() {
		return board;
	}

	public boolean getPaused() {
		return paused;
	}

	public void setPaused(boolean b) {
		paused = b;
	}

	public Shape getNextShape() {
		return nextShape;
	}

	public void setNextShape(Shape shape) {
		nextShape = shape;
	}

	public Shape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
	}

	public void setSliderLevel(int sliderLevel) {
		this.sliderLevel = sliderLevel;
	}

	public int getGameSpeed() {
		if (sliderLevel == 1)
			return 500;
		if (sliderLevel == 2) {
			return 400;
		}
		if (sliderLevel == 3)
			return 300;
		if (sliderLevel == 4) {
			return 200;
		}
		if (sliderLevel == 5) {
			return 100;
		}
		return 500;
	}

	public Shape getRandomShape() {
		int rand = (int)(Math.random() * 7);
		getAllNewShapes();
		return allShapes[rand];
	}

	public void getAllNewShapes() { 
		allShapes = new Shape[7];
		allShapes[0] = new LineShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		allShapes[1] = new LShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		allShapes[2] = new MirroredLShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		allShapes[3] = new SquareShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		allShapes[4] = new SShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		allShapes[5] = new TShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		allShapes[6] = new ZShape(BLOCK_WIDTH, BLOCK_HEIGHT);
	}

	public void addShapeToBoard(Shape shape) { 
		if (shape == null)
			return;
		for (int i = 0; i < shape.getBlockArray().size(); i++) {
			board[shape.getBlockArray().get(i).getX()][shape.getBlockArray().get(i).getY()] = shape.getBlockArray().get(i);
		}
	}

	public boolean canAddShapeToBoard(Shape shape) {
		if (shape == null)
			return false;
		for (int i = 0; i < shape.getBlockArray().size(); i++) {
			int x = shape.getBlockArray().get(i).getX();
			int y = shape.getBlockArray().get(i).getY();
			if (x >= ROWS || x < 0 || y >= COLS || y < 0 )
				return false;
			if (board[x][y] != null)
				return false;
		}
		return true;

	}

	public void restart() {
		getNewBoard();
	}

	public Shape sameShape(Shape shape) {
		if (shape == null)
			return null;
		Shape newShape;
		if (shape instanceof LineShape) 
			newShape = new LineShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		else if (shape instanceof LShape) 
			newShape = new LShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		else if (shape instanceof MirroredLShape) 
			newShape = new MirroredLShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		else if (shape instanceof SquareShape) 
			newShape = new SquareShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		else if (shape instanceof SShape) 
			newShape = new SShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		else if (shape instanceof TShape) 
			newShape = new TShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		else
			newShape = new ZShape(BLOCK_WIDTH, BLOCK_HEIGHT);
		for (int i = 0; i < shape.getBlockArray().size(); i++) {
			newShape.getBlockArray().get(i).setX(shape.getBlockArray().get(i).getX());
			newShape.getBlockArray().get(i).setY(shape.getBlockArray().get(i).getY());
		}
		newShape.setCount(shape.getCount());
		return newShape;
	}

	public void removeShapeFromBoard(Shape shape) {
		if (shape == null)
			return;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				for (int a = 0; a < shape.getBlockArray().size(); a++) {
					if (board[i][j] != null && board[i][j].equals(shape.getBlockArray().get(a)))
						board[i][j] = null;
				}
			}
		}

	}

	public boolean canMoveRight(Shape shape) { 
		if (shape == null)
			return false;
		for (Block b: shape.getBlockArray()) {
			if(b.getY() + 1 >= COLS) {
				return false;
			}
			if (board[b.getX()][b.getY() + 1] != null && !shape.contains(board[b.getX()][b.getY() + 1])) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveLeft(Shape shape) {
		if (shape == null)
			return false;
		for (Block b: shape.getBlockArray()) {
			if(b.getY() - 1 < 0) {
				return false;
			}
			if (board[b.getX()][b.getY() - 1] != null && !shape.contains(board[b.getX()][b.getY() - 1])) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveDown(Shape shape) {
		if (shape == null)
			return false;
		for (Block b: shape.getBlockArray()) {
			if(b.getX() + 1 >= ROWS) {
				return false;
			}
			if (board[b.getX() + 1][b.getY()] != null && !shape.contains(board[b.getX() + 1][b.getY()])) {
				return false;
			}
		}
		return true;
	}

	public void drawOutOfFocus(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);  
		g.drawRect(1,1,this.getWidth()-3,this.getHeight()-3);
		g.drawRect(2,2,this.getWidth()-5,this.getHeight()-5);
		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		g.drawString("CLICK TO ACTIVATE", 20, 300);
	}

	public void removeRow(int row) {
		for (int j = 0; j < board[0].length; j++) 
			board[row][j] = null;
	}

	public void moveAllDown(int row) { 
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) {
					board[i][j].setX(board[i][j].getX() + 1);
				}

			}
		}
		updateBoard(row); 
	}

	public void updateBoard(int row) { 
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = board[i - 1][j];
			}
		}
	}

	public void removeRowIfNecessary(Graphics g) {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null)
					count++;
				if (count == 10) {
					removeRow(i);
					moveAllDown(i);
					scorePanel.incrementScore();
				}
			}
			count = 0;
		}
	}

	public Shape highlightShape(Shape shape) {
		if (shape != null) {
			Shape newShape = sameShape(shape);
			while(canMoveDown(newShape)) {
				newShape.moveDown();
			}
			for(Block b: newShape.getBlockArray()) {
				b.setColor(Color.GRAY);
			}
			return newShape;
		}
		return null;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (highlightShape) {
			Shape highlightedShape = highlightShape(currentShape);
			if (highlightedShape != null) {
				highlightedShape.drawShape(g);
			}
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (!hasFocus() || paused) {
			drawOutOfFocus(g);
		}


		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null)
					board[i][j].draw(g);
			}
		}



		if (gameOver) {
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("GAME OVER", 0, 150);
		}


	}

}
