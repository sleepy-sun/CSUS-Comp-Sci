
public class Main {
	public static final int STEPS = 500;
	public static void main(String[] args) {
		RandomWalker test = new RandomWalker();
		
		int testX = test.getX();
		int testY = test.getY();
		for (int i = 1; i <= STEPS; i++) {
			test.movement();
			testX = test.getX();
			testY = test.getY();
			
			System.out.println("The number of steps is: " + test.getSteps());
			System.out.println("x is at " + test.getX());
			System.out.println("y is at " + test.getY());

	}

	}
}
