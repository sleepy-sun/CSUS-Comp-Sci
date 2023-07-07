package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandExit extends Command {
	private GameWorld gw;
	
	public CommandExit(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		Command yes = new Command("Yes");
		Command no = new Command("No");
		Command c = Dialog.show("Quit", "Do you want to exit?", yes, no);
		if(c == yes) {
			gw.quit();
		} else if (c == no) {
			return;
		} else {
			System.out.println("Not a valid command!");
			return;
		}
	}
}
