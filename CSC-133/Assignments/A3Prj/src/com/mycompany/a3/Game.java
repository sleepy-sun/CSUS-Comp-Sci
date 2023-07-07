package com.mycompany.a3;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private int tickRate;
	private boolean isPaused;
	
	//Commands
	private CommandAccelerate accelCommand;
	private CommandAbout aboutCommand;
	private CommandBreak breakCommand;
	private CommandExit exitCommand;
	private CommandLeftTurn leftCommand;
	private CommandRightTurn rightCommand;
	private CommandSound soundCommand;
	private CommandHelp helpCommand;
	private CommandPause pauseCommand;
	private CommandPosition posCommand;
	
	//Buttons
	private Button accelButton;
	private Button leftButton;
	private Button rightButton;
	private Button breakButton;
	private Button pauseButton;
	private Button posButton;

	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld(); 
		mv = new MapView();
		sv = new ScoreView(gw); 
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		//Pop-out menu
		Toolbar tb = new Toolbar();
		this.setToolbar(tb);
		tb.setTitle("Ant Life");
		
		//Accelerate
		accelCommand = new CommandAccelerate(gw);
		tb.addCommandToSideMenu(accelCommand);
		
		//Sound
		CheckBox chkBox = new CheckBox();
		chkBox.getAllStyles().setBgTransparency(255);
		chkBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCommand = new CommandSound(gw);
		chkBox.setCommand(soundCommand);
		tb.addComponentToSideMenu(chkBox);
		
		//About
		aboutCommand = new CommandAbout();
		tb.addCommandToSideMenu(aboutCommand);
		
		//Exit
		exitCommand = new CommandExit(gw);
		tb.addCommandToSideMenu(exitCommand);
		
		//Help
		helpCommand = new CommandHelp(gw);
		tb.addCommandToRightBar(helpCommand);
		
		
		//Left Side
		Container leftSide = new Container();
		leftSide.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		leftSide.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		//Accelerate
		accelButton = new Button(accelCommand);
		accelButton = leftSide(accelButton);
		leftSide.add(accelButton);
		
		//Left-turn
		leftCommand = new CommandLeftTurn(gw);
		leftButton = new Button(leftCommand);
		leftButton = leftSide(leftButton);
		leftSide.add(leftButton);
		
		//Right Side
		Container rightSide = new Container();
		rightSide.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		rightSide.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		
		//Break
		breakCommand = new CommandBreak(gw);
		breakButton = new Button(breakCommand);
		breakButton = rightSide(breakButton);
		rightSide.add(breakButton);
		
		//Right-turn
		rightCommand = new CommandRightTurn(gw);
		rightButton = new Button(rightCommand);
		rightButton = rightSide(rightButton);
		rightSide.add(rightButton);
		
		//Bottom side
		Container bottomSide = new Container();
		bottomSide.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		bottomSide.setLayout((new FlowLayout(Component.CENTER)));
		
		//Pause Game
		pauseButton = new Button("Pause");
		pauseCommand = new CommandPause(this);
		pauseButton = bottomSide(pauseButton);
		pauseButton.setCommand(pauseCommand);
		bottomSide.add(pauseButton);
		
		//Position
		posCommand = new CommandPosition(gw);
		posButton = new Button(posCommand);
		posButton = bottomSide(posButton);
		bottomSide.add(posButton);
		
		this.add(BorderLayout.WEST,leftSide);
		this.add(BorderLayout.EAST,rightSide);
		this.add(BorderLayout.SOUTH,bottomSide);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		
		gw.init();
		this.show();
		
		gw.createSound();
		revalidate();
		tickRate = 20;
		timer = new UITimer(this);
		timer.schedule(tickRate, true, this);
		
		GameWorld.setWidth(mv.getWidth());
		GameWorld.setHeight(mv.getHeight());
		
		mv.setOriginalMapView(new Point(mv.getX(), mv.getY()));
		MapView.setMapHeight(mv.getHeight());
		MapView.setMapWidth(mv.getWidth());
		
		isPaused = false;
	}

	public void run() {
		gw.worldTick();
	}
	
	//Created to easily change and apply styles to different Container Buttons
	private Button leftSide(Button obj) {
		obj.getAllStyles().setBgTransparency(255);
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		obj.getAllStyles().setPadding(TOP, 5);
		obj.getAllStyles().setPadding(BOTTOM, 5);
		return obj;
	}
	private Button rightSide(Button obj) {
		obj.getAllStyles().setBgTransparency(255);
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		obj.getAllStyles().setPadding(TOP, 5);
		obj.getAllStyles().setPadding(BOTTOM, 5);
		return obj;
	}
	private Button bottomSide(Button obj) {
		obj.getAllStyles().setBgTransparency(255);
		obj.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		obj.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.rgb(0, 0, 0)));
		obj.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		obj.getAllStyles().setPadding(TOP, 5);
		obj.getAllStyles().setPadding(BOTTOM, 5);
		return obj;
	}
	
	//Anything to do with pausing will be down here
	public void pause() {
		if(isPaused) {
			isPaused = false;
			pauseButton.setText("Pause");
			keyListeners(isPaused);
			disableButtons(isPaused);
			gw.BGsoundOn();
			timer.schedule(tickRate, true, this);
		} else {
			timer.cancel();
			isPaused = true;
			pauseButton.setText("Play");
			keyListeners(isPaused);
			disableButtons(isPaused);
			gw.BGsoundOff();
		}
	}
	
	public void keyListeners(boolean paused) {
		if(paused) {
			removeKeyListener('a', accelCommand);
			removeKeyListener('l', leftCommand);
			removeKeyListener('b', breakCommand);
			removeKeyListener('r', rightCommand);
		} else {
			addKeyListener('a', accelCommand);
			addKeyListener('l', leftCommand);
			addKeyListener('b', breakCommand);
			addKeyListener('r', rightCommand);
		}
	}
	
	public void disableButtons(boolean paused) {
		if(paused) {
			accelCommand.setEnabled(false);
			accelButton.setEnabled(false);
			leftButton.setEnabled(false);
			breakButton.setEnabled(false);
			rightButton.setEnabled(false);
			posButton.setEnabled(true);
		} else {
			accelCommand.setEnabled(true);
			accelButton.setEnabled(true);
			leftButton.setEnabled(true);
			breakButton.setEnabled(true);
			rightButton.setEnabled(true);
			posButton.setEnabled(false);
		}
	}
}
