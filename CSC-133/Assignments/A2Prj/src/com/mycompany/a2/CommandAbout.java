package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandAbout extends Command {
	public CommandAbout() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent e) {
		String infotxt = "Ant Game\n" +
				"Created By: Javier Briseno\n" +
				"CSC 133 Assignment 2\n" + 
				"Version: 1.0";
		Dialog.show("About", infotxt,"OK", null);
	}
}
