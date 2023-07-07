package com.mycompany.a4;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Label;

public class PlayPause extends Command {

	private boolean pause = false;
	private Game g;

	
	public PlayPause(Game g) {
		super("Pause");
		this.g = g;
	}
	
	public void actionPerformed(ActionEvent ev) {
		if(pause) {	//If it is paused right now, play it
			pause = !pause;
			this.setCommandName("Pause");	//Set the option to "pause" the game
			
			g.startTimer();
			g.BGSoundOn();
		}
		
		else {	//Game is playing right now, pause it
			pause = !pause;
			this.setCommandName("Play");	//Set the option to "play" the game
			
			g.stopTimer();
			g.BGSoundOff();
		}
	}

}
