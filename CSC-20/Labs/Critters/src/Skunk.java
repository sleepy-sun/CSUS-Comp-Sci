import java.awt.*;

public class Skunk extends Critter{
	private int moves;
	private boolean hungry;
	
	public Skunk() {
		hungry = false;
		moves = -1;
	}
	
	 public Color getColor() {
	     return Color.BLACK;
	    }
	
	public boolean eat() {
		hungry = true;
		return true;
	}
	
	public Direction getMoves() {
		moves++;
		moves %= 3;
		
		if (moves == 0 && moves == 1) {
			return Direction.WEST;
		} else if (hungry) {
			return Direction.NORTH;
		} else if (!hungry) {
			return Direction.SOUTH;
		}
		return null;
	}
}
