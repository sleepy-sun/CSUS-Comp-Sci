package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;


public class Spider extends Moveable {
	Random r = new Random();
	public Spider() {
		super(ColorUtil.rgb(128,128,128));
		super.setSize(r.nextInt(50-10)+10);
		super.setHeading(r.nextInt(359));
		super.setSpeed(r.nextInt(10));
	}
	
	public void move() {
		setHeading(getHeading()+r.nextInt(5));
	}
	
	public void spiderBounds() {
		//stuff goes here
	}
	
	//empty since color can't be changed and is set upon creation
	public void setColor (int color) {
		
	}
	
	//empty since size can't be changed and is set upon creation
	public void setSize(int size) {
		
	}
	
	public String toString() {
		return "Spider: loc=" + Math.round(this.getX()* 10.0)/ 10.0  + "," + Math.round(this.getY()*10.0)/10.0 +
				" color=" + super.toString() + 
				" size=" + super.getSize() + 
				" heading=" + getHeading() +
				" speed=" + getSpeed();
	}
}
