/*
 * This class serves as a command to update the player's lastBaseReached.
 * The class will ask for user input for the base number, and will test
 * for invalidities.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;
//import com.codename1.ui.Command;
import com.codename1.ui.*;

public class CollideWithBase extends Command{

	private GameWorld gw = new GameWorld();
	private int base;
	private int count;
	private boolean pass;
	
	public CollideWithBase(GameWorld gw) {
		super("Collision with Base");
		this.gw = gw;
		count = 0;
		pass = false;
		
	}

	/*
	 * Creates a TextField and Dialog to ask the user which base number was reached.
	 * Then, it uses the user's input to invoke the GameWorld's collisionBase().
	 */
	public void actionPerformed(ActionEvent ev) {
		//Calls query() to recursively ask for input of the base if invalid, "base" should be updated
		query(count, pass);
		
		PlayerCyborg player = gw.getPlayer();
		
		//1.)Test whether the base inputted is the correct base to have collided with
		System.out.println("_____________________________________________________________________");
		System.out.println("CollideWithBase Command");
		
		int lastBase = player.getLastBaseReached();
		
		
			// a.)If yes, then state so
		if(base == lastBase + 1) {
			System.out.println("- Your Cyborg has collided with base #" + base + " !\n");
			player.setLastBaseReached(base);
			
				//-If the base is the last base, win the game and exit
			if(base == 9) {
				System.out.println("- You have reached the last base, you won the Game! \n");
				System.exit(0);
			}
			else {
				System.out.println("- That was the correct base, you are on track!\n");
			}
		}
		
			//b.) Otherwise, direct user to the correct base
		else {
			System.out.println("- Your Cyborg collided with base #" + base + ", but it wasn't the correct base. \n");
			System.out.println("- Your last base that was reached is #" + lastBase+ ". You must reach base #" + (lastBase +1) + " to be on track\n");
		}
		
		System.out.println("\nEnd of Collide With Base Command");
		System.out.println("_______________________________________________________________\n");
		
		
		//2.) Variables have been updated, notify the Observers
		gw.notifyObservers();
		
		
	}
	
	
	/*
	 * A recursive method that continuously queries for the base number if the number inputted
	 * is invalid. Then, inputs the value in the "base" field.
	 */
	public void query(int counter, boolean pass) {
		
		//1.) Create a Command and TextField to input into dialog.show()
		Command ok = new Command("Ok");
		TextField TF = new TextField();
		Command c = new Command("");
	
		//2.)If this is the first time asking, output first output
		if(counter == 0) {
			 c = Dialog.show("What base number was reached?", TF, ok);
		}
		else {	//else(this is not the first time, and user must be asked again)
			c = Dialog.show("That base number is invalid, please try again", TF, ok);
		}
		
		//3.)Every time "ok" is pushed, test whether the input is valid
		String baseNum = "0";
		if(c == ok) {
			baseNum = TF.getText();
			
			//a.) if not valid input, recursively recall method, and increment counter to notify that
			if(!(baseNum.equals("1") || baseNum.equals("2") || baseNum.equals("3") || baseNum.equals("4") || baseNum.equals("5") || baseNum.equals("6") || baseNum.equals("7") || baseNum.equals("8") || baseNum.equals("9"))){
				counter++;
				query(counter, pass);
			}	
			
			//Otherwise, set flag == true to end recursion
			else {
				pass = true;
			}
		}
		
		//4.) Convert valid input into an int
		if(pass) {
			base = Integer.parseInt(baseNum);		
		}
	
	}
	
}
