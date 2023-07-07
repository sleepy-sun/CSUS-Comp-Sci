package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandHelp extends Command {
	private GameWorld gw;
	
	public CommandHelp(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		String infotxt ="A: Accelerate\n" +
						"B: Brake\n" +
						"L: Left Turn\n" + 
						"R: Right Turn\n" +
						"F: Food\n" +
						"G: Spider\n" +
						"T: Tick\n" +
						"X: Exit";
		Dialog.show("Help Commands", infotxt,"OK", null);
	}

}
