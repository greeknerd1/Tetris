import java.awt.Color;
import java.util.ArrayList;

public class TShape extends Shape {

	int width;
	int height;
	ArrayList<Block> blocks;
	
	
	public TShape(int width, int height) {
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
		blocks.add(new Block(Color.YELLOW, 0, 1, width, height));
		blocks.add(new Block(Color.YELLOW, 1, 0, width, height));
		blocks.add(new Block(Color.YELLOW, 1, 1, width, height));
		blocks.add(new Block(Color.YELLOW, 1, 2, width, height));
		isSetup = true;
	}

	@Override
	void rotateRight() {
		// TODO Auto-generated method stub
		if(count == 0) {
			count++;
			blocks.get(0).setX(blocks.get(0).getX() + 1);
			blocks.get(1).setX(blocks.get(1).getX() - 1);
			blocks.get(2).setY(blocks.get(2).getY() - 1);
			blocks.get(3).setX(blocks.get(3).getX() + 1);
			blocks.get(3).setY(blocks.get(3).getY() - 2);
		}
		else if (count == 1) {
			count++;
			blocks.get(1).setY(blocks.get(1).getY() + 2);
			blocks.get(2).setY(blocks.get(2).getY() + 1);
			blocks.get(2).setX(blocks.get(2).getX() - 1);
			blocks.get(3).setX(blocks.get(3).getX() - 2);
		}
		else if (count == 2) {
			count++;
			blocks.get(0).setY(blocks.get(0).getY() - 1);
			
			blocks.get(1).setX(blocks.get(1).getX() + 2);
			
			blocks.get(1).setY(blocks.get(1).getY() - 1);
			
			blocks.get(2).setX(blocks.get(2).getX() + 1);
			blocks.get(3).setY(blocks.get(3).getY() + 1);
		}
		else if(count == 3) { 
			count = 0;
			blocks.get(0).setX(blocks.get(0).getX() - 1);
			blocks.get(0).setY(blocks.get(0).getY() + 1);
			blocks.get(1).setX(blocks.get(1).getX() - 1);
			blocks.get(1).setY(blocks.get(1).getY() - 1);
			blocks.get(3).setX(blocks.get(3).getX() + 1);
			blocks.get(3).setY(blocks.get(3).getY() + 1);
		}
	}

}
