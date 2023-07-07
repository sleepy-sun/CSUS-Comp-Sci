package com.mycompany.a1;

public class Moveable extends GameObject {
	private int heading;
	private int speed;
	
	public Moveable(int color, int size) {
		super(color,size);
	}
	
	public Moveable(int color) {
		super(color);
	}

	public void move() {
		float tempX = (getX() + (float)(Math.cos(Math.toRadians(90-heading))) * speed);
		float tempY = (getY() + (float)(Math.sin(Math.toRadians(90-heading))) * speed);
		setLocation(tempX,tempY);
		//setX(tempX);
		//setY(tempY);
	}
	
	public int getHeading() {
		return heading;
	}
	public void setHeading(int heading) {
		this.heading = heading;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

}
