/*
 * This class serves as a command that displays a dialog
 * box to show all the key commands the user may invoke.
 */
package com.mycompany.a4;
import com.codename1.ui.events.*;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;

public class Help extends Command{

	private GameWorld gw = new GameWorld();
	
	public Help(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent ev) {
		String help = "Keys\n - 'a' = accelerate\n - 'b' = brake\n -'e' = collision with energy station\n - 'g' = collision with drone\n - 'l' = go left\n - 'r' = go right\n 't' = tick\n ";
		Boolean ok = Dialog.show("Help", help, "Ok", "Cancel");
	}
}