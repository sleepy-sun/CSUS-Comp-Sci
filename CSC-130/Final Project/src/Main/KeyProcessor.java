/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')		{
			Main.wPress = false;
			Main.aPress = false;
			Main.sPress = false;
			Main.dPress = false;
			return;
		}
		
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		

		/* TODO: You can modify values below here! */
		switch(key){
		
		case '%':	// ESC key
			System.exit(0);
			break;
			
		case 'w':
			Main.wPress = true;
			Main.aPress = false;
			Main.sPress = false;
			Main.dPress = false;
			Main.spacePress = false;
			Main.facingItemUp = true;
			Main.facingItemDown = false;
			break;
			
		case 'a':
			Main.aPress = true;
			Main.sPress = false;
			Main.dPress = false;
			Main.wPress = false;
			Main.spacePress = false;
			Main.facingItemDown = false;
			Main.facingItemUp = false;
			break;
			
		case 's':
			Main.sPress = true;
			Main.aPress = false;
			Main.dPress = false;
			Main.wPress = false;
			Main.spacePress = false;
			Main.facingItemDown = true;
			Main.facingItemUp = false;
			break;
			
		case 'd':
			Main.dPress = true;
			Main.aPress = false;
			Main.sPress = false;
			Main.wPress = false;
			Main.spacePress = false;
			Main.facingItemDown = false;
			Main.facingItemUp = false;
			break;
			
		case '$':
			/*
			 * if (blank.item is there) {
			 * 		get the tag of item (either wall or object) and respond with appropriate sentence.
			 * 
			 * } else if (blank.item is not there and player is interacting with empty space) {
			 * 		respond with quirky line about how nothing is there. 
			 * }
			 */
			Main.spacePress = true;
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
			
		}
	}
}