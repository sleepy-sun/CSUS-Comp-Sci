package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandSpiderCollision extends Command{
private GameWorld gw;
	
	public CommandSpiderCollision(GameWorld gw) {
		super("Collide with Spider");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Ant hits a Spider!!");
		gw.spiderCollision();
	}
}
