import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

public abstract class Shape {

	int width;
	int height;
	boolean isSetup = false;
	int count = 0;

	abstract void setUpBlockArray();
	
	abstract ArrayList<Block> getBlockArray();
	
	abstract void rotateRight();

	int getCount() {
		return count;
	}
	
	void setCount(int c) {
		count = c;
	}
	
	boolean contains(Block block) {
		for (Block b: getBlockArray()) {
			if (b.equals(block))
				return true;
		}
		return false;
	}
	
	void moveDown() {
		for(Block b: getBlockArray()) {
			b.setX(b.getX() + 1);
		}
	}
	
	void moveRight() {
		for(Block b: getBlockArray()) {
			b.setY(b.getY() + 1);
		}
	}
	
	void moveLeft() {
		for(Block b: getBlockArray()) {
			b.setY(b.getY() - 1);
		}
	}

	void drawShape(Graphics g) {
		for(Block b: getBlockArray())
			b.draw(g);
	}
	
	
}



