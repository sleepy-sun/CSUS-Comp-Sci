package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandBreak extends Command{
	private GameWorld gw;
	
	public CommandBreak(GameWorld gw) {
		super("Brake");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ant is slowing down!!");
		gw.antSlow();
	}
}
