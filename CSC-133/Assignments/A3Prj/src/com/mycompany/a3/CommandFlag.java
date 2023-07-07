package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CommandFlag extends Command {
	private GameWorld gw;
	
	public CommandFlag(GameWorld gw) {
		super("Collide with Flag");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		Command ok = new Command("OK");
		TextField flagNum = new TextField();
		Dialog.show("What flag have you reached?", flagNum, ok);
		
		String text = flagNum.getText().toString();
		
		//Flag try/catch in case they enter a non-numerical value
		try {
			int temp = Integer.parseInt(text);
			if (temp > 0 && temp < 10) {
				gw.flagCollision(temp);
				
			} else {
				String info = "Please enter again a number between 1 - 9!!";
				Dialog.show("Error", info, "OK", null);
				System.out.println("Please enter again a number between 1 - 9!");
			}
		} catch(NumberFormatException e1) {
			String info = "Please enter number again, not a letter!!!";
			Dialog.show("Error",info,"OK",null);
		}
	}
}
