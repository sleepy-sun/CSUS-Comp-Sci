package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandGameTick extends Command{
	private GameWorld gw;
	
	public CommandGameTick(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Game has ticked!");
		gw.worldTick();
	}
}
