package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class Game extends Form{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;

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
		Command accelCommand = new CommandAccelerate(gw);
		tb.addCommandToSideMenu(accelCommand);
		
		//Sound
		CheckBox chkBox = new CheckBox();
		chkBox.getAllStyles().setBgTransparency(255);
		chkBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		Command soundCommand = new CommandSound(gw);
		chkBox.setCommand(soundCommand);
		tb.addComponentToSideMenu(chkBox);
		
		//About
		Command aboutCommand = new CommandAbout();
		tb.addCommandToSideMenu(aboutCommand);
		
		//Exit
		Command exitCommand = new CommandExit(gw);
		tb.addCommandToSideMenu(exitCommand);
		
		//Help
		Command helpCommand = new CommandHelp(gw);
		tb.addCommandToRightBar(helpCommand);
		
		
		//Left Side
		Container leftSide = new Container();
		leftSide.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		leftSide.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		//Accelerate
		//Command accelCommand = new CommandAccelerate(gw);
		Button accelButton = new Button(accelCommand);
		accelButton = leftSide(accelButton);
		leftSide.add(accelButton);
		addKeyListener('a', accelCommand);
		
		//Left-turn
		Command leftCommand = new CommandLeftTurn(gw);
		Button leftButton = new Button(leftCommand);
		leftButton = leftSide(leftButton);
		leftSide.add(leftButton);
		addKeyListener('l', leftCommand);
		
		//Right Side
		Container rightSide = new Container();
		rightSide.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		rightSide.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		
		//Break
		Command breakCommand = new CommandBreak(gw);
		Button breakButton = new Button(breakCommand);
		breakButton = rightSide(breakButton);
		rightSide.add(breakButton);
		addKeyListener('b', breakCommand);
		
		//Right-turn
		Command rightCommand = new CommandRightTurn(gw);
		Button rightButton = new Button(rightCommand);
		rightButton = rightSide(rightButton);
		rightSide.add(rightButton);
		addKeyListener('r', rightCommand);
		
		//Bottom side
		Container bottomSide = new Container();
		bottomSide.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.rgb(0, 0, 0)));
		bottomSide.setLayout((new FlowLayout(Component.CENTER)));
		
		//Collide with Flag
		Command flagCommand = new CommandFlag(gw);
		Button flagButton = new Button(flagCommand);
		flagButton = bottomSide(flagButton);
		bottomSide.add(flagButton);
		
		//Collide with Spider
		Command spiderCommand = new CommandSpiderCollision(gw);
		Button spiderButton = new Button(spiderCommand);
		spiderButton = bottomSide(spiderButton);
		bottomSide.add(spiderButton);
		addKeyListener('g',spiderCommand);
		
		//Collide with Food Station
		Command foodCommand = new CommandFoodStationCollision(gw);
		Button foodButton = new Button(foodCommand);
		foodButton = bottomSide(foodButton);
		bottomSide.add(foodButton);
		addKeyListener('f',foodCommand);
		
		//Tick
		Command tickCommand = new CommandGameTick(gw);
		Button tickButton = new Button(tickCommand);
		tickButton = bottomSide(tickButton);
		bottomSide.add(tickButton);
		addKeyListener('t',tickCommand);
		
		this.add(BorderLayout.WEST,leftSide);
		this.add(BorderLayout.EAST,rightSide);
		this.add(BorderLayout.SOUTH,bottomSide);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		
		gw.init();
		this.show();
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
}
