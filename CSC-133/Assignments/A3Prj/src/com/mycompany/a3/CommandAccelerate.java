package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandAccelerate extends Command {
	private GameWorld gw;
	
	public CommandAccelerate(GameWorld gw) {
		super("Accelerate");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ant speeding up!!");
		gw.antGo();
	}
}
