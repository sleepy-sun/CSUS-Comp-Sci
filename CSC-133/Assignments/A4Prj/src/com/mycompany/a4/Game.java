package com.mycompany.a4;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Button;
import com.codename1.ui.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private static final int TICK_TIME = 10;
	
	public Game() {
		
		//Initialize Observable and Observers, register together
		gw = new GameWorld();		//Model 
		mv = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(sv);
		gw.addObserver(mv);
		
		
		//create the GUI page:
		
		//BorderLayout for the entire Form:
		this.setLayout(new BorderLayout());	//divides into the N,E,S,W, and Center
				
				
		//Containers for the West, East, and South:
		Container west = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container east = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container south = new Container(new FlowLayout(Component.CENTER));
		
				
		//Toolbar at the top and SideMenu
		Toolbar myToolBar = new Toolbar();
		setToolbar(myToolBar);
		this.setTitle("Sili-Challenge Game");

				
		//Create single instances of each command
		Accelerate accel = new Accelerate(gw);
		Brake brake = new Brake(gw);
		ChangeStrategy stratChange = new ChangeStrategy(gw);
		LeftTurn left = new LeftTurn(gw);
		RightTurn right = new RightTurn(gw);
		About about = new About(gw);
		Help help = new Help(gw);
		Exit exit = new Exit();
		SetSound soundCom = new SetSound(gw);
		PlayPause pause = new PlayPause(this);
		Position moveSelectable = new Position(mv);
		
	
		//Attach commands to components:
		
			//**Declare MyButton for set style of all buttons
				MyButton b = new MyButton();
				
				
		//1.) accelerate --> keybinding, side menu, and button
		addKeyListener('a', accel);
		myToolBar.addCommandToSideMenu(accel);
				
			//Button created, attached, and added to the WEST Container
			Button b1 = b.getButton("Accelerate");
			b1.setCommand(accel);
			west.add(b1);
				
				
		//2.) brake -->	keybinding, button
		addKeyListener('b', brake);
			
			//Button created, attached, and added to the EAST Container
			b = new MyButton();
			Button b2 = b.getButton("Brake");
			b2.setCommand(brake);
			east.add(b2);
			
			
		//3.) left turn --> keybinding, button
		addKeyListener('l', left);
		
			//Button created, attached, and added to the WEST Container
			b = new MyButton();
			Button b3 = b.getButton("Left Turn");
			b3.setCommand(left);
			west.add(b3);
			
		
		//4.) right turn --> keybinding, button
		addKeyListener('r', right);
		
			//Button created, attached, and added to the EAST Container
			b = new MyButton();
			Button b4 = b.getButton("Right Turn");
			b4.setCommand(right);
			east.add(b4);
		
		//10.) exit --> side Menu
		myToolBar.addCommandToSideMenu(exit);
		
		//11.) change strategy --> Button
		b = new MyButton();
		Button b10 = b.getButton("Change Strategies");
		b10.setCommand(stratChange);
		west.add(b10);
		
		//12.)Sound --> side Menu and CheckBox
		
		CheckBox check = new CheckBox();
		check.setSelected(true);
		check.getAllStyles().setBgTransparency(255);
		check.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		check.setCommand(soundCom);
		myToolBar.addComponentToSideMenu(check);
		
		//13. and 14.) About and Help --> side Menu
		myToolBar.addCommandToSideMenu(about);
		myToolBar.addCommandToRightBar(help);
		
		//15.) Play/Pause button:
		b = new MyButton();
		Button b11 = b.getButton("Pause");
		b11.setCommand(pause);
		south.add(b11);
		
		//16.)Position
		b = new MyButton();
		Button b12 = b.getButton("Position");
		b12.setCommand(moveSelectable);
		south.add(b12);
		
		//Add completed W, E, S Containers onto Form
		add(BorderLayout.WEST, west);
		add(BorderLayout.EAST, east);
		add(BorderLayout.SOUTH, south);
				
		//ScoreView Container for the North
		add(BorderLayout.NORTH, sv.getContainer());
				
		//MapView Container for Center
		add(BorderLayout.CENTER, mv);
						
		
		gw.init(); //initialize the world
		this.show();
		
		//Query for MapView's width and height
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		
		gw.createSounds();
		
		//UITimer
		timer = new UITimer(this);
		timer.schedule(TICK_TIME, true, this);	//ticks every 20 msec
	}
	
	public void run() {
		gw.tick(TICK_TIME);
	}
	
	public void stopTimer() {
		timer.cancel();
	}
	
	public void startTimer() {
		timer.schedule(TICK_TIME, true, this);
	}
	
	public void BGSoundOff() {
		BGSound bgSong = gw.getBGSong();
		bgSong.pause();
	}
	
	public void BGSoundOn() {
		BGSound bgSong = gw.getBGSong();
		bgSong.play();
	}
	
}
		

		
		
		
