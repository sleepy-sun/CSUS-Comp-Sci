package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandSound extends Command{
private GameWorld gw;
	
	public CommandSound(GameWorld gw) {
		super("Sound ON/OFF");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(((CheckBox) e.getComponent()).isSelected()) {
			gw.setSound(true);
		} else {
			gw.setSound(false);
		}
	}
}
