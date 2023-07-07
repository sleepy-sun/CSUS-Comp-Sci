/*
* RandomWalker will randomly generate a number between 0 and 1
* It then moves the x and y coordinates according to 
* the number generated. In order to work with TestRandomWalker
* @author Javier Briseno
* */

public class RandomWalker {
// declare instance variables
	public int x;
	public int y;
	public int steps;

// constructor to set x and y values
public RandomWalker(int x, int y) {
	this.x = x;
	this.y = y;
	}
//default constructor
	public RandomWalker() {
		x = 0;
		y = 0;
	}
	
	 public int getX() {
	    	return x;
	    }
	    
	    public int getY() {
	    	return y;
	    }
	   
	    public int getSteps() {
	    	return steps;
	    }
	   
// move should generate a random number
// range from 0 to 1
public void movement() {
// increment steps by 1
	steps++;

// generate a random number in a range of 0-1
	double rand = Math.random();
	
		if (rand < 0.25)
			++x;	// move right

		else if (rand < 0.5)
			--y; // move up

		else if (rand < 0.75)
			--x; // move left

		else if (rand < 1.0)
			++y;// move down
			
	}

}