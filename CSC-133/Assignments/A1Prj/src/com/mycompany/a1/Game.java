package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Game extends Form{
	private GameWorld gw;

	public Game() {
		gw = new GameWorld(); 
		gw.init();
		play();
	}
   
	private void play() {
		// code here to accept and execute user commands that operate on the game world 
		// (refer to “Appendix - CN1 Notes” below for accepting keyboard commands via a text 
		// field located on the form)
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
						case 'a':
							gw.antGo();
						     break;
						case 'b':
							gw.antSlow();
						     break;
						case 'l':
							gw.antLeft();
						     break;
						case 'r':
							gw.antRight();
						     break;
						case '1':
							gw.flagCollision(1);
						     break;
						case '2':
							gw.flagCollision(2);
						     break;
						case '3':
							gw.flagCollision(3);
						     break;
						case '4':
							gw.flagCollision(4);
						     break;
						case '5':
							gw.flagCollision(5);
						     break;
						case '6':
							gw.flagCollision(6);
						     break;
						case '7':
							gw.flagCollision(7);
						     break;
						case '8':
							gw.flagCollision(8);
						     break;
						case '9':
							gw.flagCollision(9);
						     break;
						case 'f':
							gw.foodCollision();
						     break;
						case 'g':
							gw.spiderCollision();
						     break;
						case 't':
							gw.worldTick();
						     break;
						case 'd':
							gw.gameDisplay();
						     break;
						case 'm':
							gw.printMap();
						     break;
						case 'x':
							myLabel.setText("Are you sure you want to quit?");
							gw.gameExit();
						     break;
						case 'y':
							gw.quit();
						     break;
						case 'n':
							myLabel.setText("Enter a Command:");
						     break;
						default:
							System.out.println("\nNot a valid input!! Try again please!!");
						//add code to handle rest of the commands
			     } //switch
			} //actionPerformed
			} //new ActionListener()
			); //addActionListener
			} //play
			
			//add code to handle rest of the commands
	}
