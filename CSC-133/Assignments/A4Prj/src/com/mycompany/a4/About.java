/*
 * This class shows a Dialog box displaying "About" information
 * about the game, and its maker.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;

public class About extends Command{

	private GameWorld gw = new GameWorld();
	
	public About(GameWorld gw) {
		super("About");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent ev) {
			boolean ok = Dialog.show("About", "Josephine Nguyen\n CSC 133\n Sili-Challenge Game\n", "Ok", "Cancel");
			
	}
}