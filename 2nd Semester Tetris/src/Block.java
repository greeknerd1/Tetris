import java.awt.Color;
import java.awt.Graphics;

public class Block {

	private int width;
	private int height;
	private int x;
	private int y;
	private Color color;
	
	Block(Color color, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(y*width, x*height, width, height); 
		g.setColor(Color.BLACK);
		g.drawRect((y*width), (x*height), width, height); 
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean equals(Block b) {
		if (x == b.x && y == b.y && color.equals(b.color)) {
			return true;
		}
		return false;
	}
	
}
