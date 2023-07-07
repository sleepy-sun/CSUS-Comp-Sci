/*
 * This class serves as a command to show a dialog box
 * to ask the user for confirmation to exit the game.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;

public class Exit extends Command{
	
	public Exit() {
		super("Exit");
	}

	public void actionPerformed(ActionEvent ev) {
		Boolean ok = Dialog.show("Confirm quit", "Are you sure you want to exit?", "Yes", "No");
		if(ok) {
			System.exit(0);
		}
	}
}