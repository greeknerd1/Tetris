import java.awt.Color;
import java.util.ArrayList;

public class ZShape extends Shape {

	int width;
	int height;
	ArrayList<Block> blocks;
	
	public ZShape(int width, int height) {
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
		blocks.add(new Block(Color.RED, 0, 1, width, height));
		blocks.add(new Block(Color.RED, 1, 0, width, height));
		blocks.add(new Block(Color.RED, 1, 1, width, height));
		blocks.add(new Block(Color.RED, 2, 0, width, height));
		isSetup = true;
	}

	@Override
	void rotateRight() {
		// TODO Auto-generated method stub
		if(count == 0) {
			count++;
			blocks.get(0).setX(blocks.get(0).getX() + 1);
			blocks.get(0).setY(blocks.get(0).getY() + 1);
			blocks.get(1).setX(blocks.get(1).getX() - 1);
			blocks.get(1).setY(blocks.get(1).getY() + 1);
			blocks.get(3).setX(blocks.get(3).getX() - 2);
		}
		else if (count == 1) {
			count = 0;
			blocks.get(0).setX(blocks.get(0).getX() - 1);
			blocks.get(0).setY(blocks.get(0).getY() - 1);
			blocks.get(1).setX(blocks.get(1).getX() + 1);
			blocks.get(1).setY(blocks.get(1).getY() - 1);
			blocks.get(3).setX(blocks.get(3).getX() + 2);
		}
	}
	
}

