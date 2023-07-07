/*
 * This class serves as a command to set the sound in the GameWorld
 * and in the represented checkbox in Game{}.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;
import com.codename1.ui.Form;

import com.codename1.ui.Command;
import com.codename1.ui.CheckBox;
public class SetSound extends Command{

	private GameWorld gw = new GameWorld();
	
	public SetSound(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}

	/*
	 * This method is invoked by an event, meaning that the sound must 
	 * be changed into the opposite it was before it.
	 */
	public void actionPerformed(ActionEvent ev) {
		BGSound bgSound = gw.getBGSong();
		//obtain the sound value
		boolean sound = gw.getSound();
		
		//set the sound to be opposite to its value
		if(sound) {
			gw.setSound(false);
			bgSound.pause();
		}
		else {
			gw.setSound(true);
			bgSound.play();
		
		}
	
		//Values have changed --> notifyObservers()
		gw.notifyObservers();
	}
}