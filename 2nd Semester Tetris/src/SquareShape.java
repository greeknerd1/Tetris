import java.awt.Color;
import java.util.ArrayList;

public class SquareShape extends Shape {
	int width;
	int height;
	private ArrayList<Block> blocks;
	
	public SquareShape(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	ArrayList<Block> getBlockArray() {
		// TODO Auto-generated method stub
		if (isSetup == false)
			setUpBlockArray();
		return blocks;
	}

	@Override
	void setUpBlockArray() {
		// TODO Auto-generated method stub
		blocks = new ArrayList<Block>();
		blocks.add(new Block(Color.MAGENTA, 0, 0, width, height));
		blocks.add(new Block(Color.MAGENTA, 0, 1, width, height));
		blocks.add(new Block(Color.MAGENTA, 1, 0, width, height));
		blocks.add(new Block(Color.MAGENTA, 1, 1, width, height));
		isSetup = true;
	}

	@Override
	void rotateRight() {
		// TODO Auto-generated method stub
		
	}

}
