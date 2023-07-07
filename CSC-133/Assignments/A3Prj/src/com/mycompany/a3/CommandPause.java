package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandPause extends Command{
	private GameWorld gw;
	private Game game;
	private Button button;
	private Game obj;
	
	public CommandPause (Game obj) {
		super("Pause");
		this.obj = obj;

		
	}
	public void actionPerformed(ActionEvent e) {
		obj.pause();
	}

}
