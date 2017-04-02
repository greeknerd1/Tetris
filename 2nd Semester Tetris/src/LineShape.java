import java.awt.Color;
import java.util.ArrayList;

public class LineShape extends Shape{

	int width;
	int height;
	ArrayList<Block> blocks;

	public LineShape(int width, int height) {
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
		blocks = new ArrayList<Block>();
		blocks.add(new Block(Color.BLUE, 0, 0, width, height));
		blocks.add(new Block(Color.BLUE, 0, 1, width, height));
		blocks.add(new Block(Color.BLUE, 0, 2, width, height));
		blocks.add(new Block(Color.BLUE, 0, 3, width, height));
		isSetup = true;
	}

	@Override
	void rotateRight() {
		// TODO Auto-generated method stub
		if(count == 0) {
			count++;
			blocks.get(1).setX(blocks.get(1).getX() + 1);
			blocks.get(2).setX(blocks.get(2).getX() + 2);
			blocks.get(3).setX(blocks.get(3).getX() + 3);
			blocks.get(1).setY(blocks.get(1).getY() - 1);
			blocks.get(2).setY(blocks.get(2).getY() - 2);
			blocks.get(3).setY(blocks.get(3).getY() - 3);
			
		}
		else if (count == 1) {
			count = 0;
			blocks.get(1).setX(blocks.get(1).getX() - 1);
			blocks.get(2).setX(blocks.get(2).getX() - 2);
			blocks.get(3).setX(blocks.get(3).getX() - 3);
			blocks.get(1).setY(blocks.get(1).getY() + 1);
			blocks.get(2).setY(blocks.get(2).getY() + 2);
			blocks.get(3).setY(blocks.get(3).getY() + 3);
		}
	}
}
