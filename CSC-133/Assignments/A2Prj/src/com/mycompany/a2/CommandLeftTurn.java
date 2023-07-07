package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandLeftTurn extends Command{
private GameWorld gw;
	
	public CommandLeftTurn(GameWorld gw) {
		super("Left");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ant turns left!!");
		gw.antLeft();
	}
}
