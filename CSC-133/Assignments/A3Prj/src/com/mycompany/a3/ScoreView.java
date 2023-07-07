package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	
	private GameWorld gw;
	private Label livesValue;
	private Label timeValue;
	private Label lastFlagValue;
	private Label foodLevelValue;
	private Label healthValue;
	private Label soundValue;

	public ScoreView(Observable data) {
		gw = ((GameWorld) data);
		data.addObserver(this);
		this.setLayout(new FlowLayout(Component.CENTER));
		this.labelSet();
	}
	public void labelSet() {
		
		
		//Time
		Label timeLabel = new Label("Time:");
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeValue = new Label("" + gw.getTime());
		timeValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(timeLabel);
		this.add(timeValue);
		
		//Lives
		Label livesLabel = new Label("Lives Left:");
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesValue = new Label("" + gw.getLives());
		livesValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(livesLabel);
		this.add(livesValue);
		
		//Last Flag Reached 
		Label lastFlagLabel = new Label("Last Flag Reached:");
		lastFlagLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagValue = new Label("" + gw.getLastFlagReached());
		lastFlagValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(lastFlagLabel);
		this.add(lastFlagValue);
		
		//Food Level
		Label foodLevelLabel = new Label("Food Level:");
		foodLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelValue = new Label("" + gw.getFoodLevel());
		foodLevelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(foodLevelLabel);
		this.add(foodLevelValue);
		
		//Health Level
		Label healthLabel = new Label("Health:");
		healthLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthValue = new Label("" + gw.getHealthLevel());
		healthValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(healthLabel);
		this.add(healthValue);
		
		//Sound
		Label soundLabel = new Label("Sound:");
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundValue = new Label("" + gw.getSound());
		soundValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		this.add(soundLabel);
		this.add(soundValue);
	}
	
	public void update (Observable o, Object arg) {
		// code here to update labels from the game/ant state data
		gw = (GameWorld) arg;
		this.timeValue.setText("" + gw.getTime());
		this.livesValue.setText("" + gw.getLives());
		this.lastFlagValue.setText("" + gw.getLastFlagReached());
		this.foodLevelValue.setText("" + gw.getFoodLevel());
		this.healthValue.setText("" + gw.getHealthLevel());
		if (gw.getSound()) {
			this.soundValue.setText("ON");
		} else {
			this.soundValue.setText("OFF");
		}
		this.repaint();
	}
}