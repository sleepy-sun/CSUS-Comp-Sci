package com.mycompany.a3;

public abstract class Moveable extends GameObject {
	private int heading;
	private int speed;
	
	public Moveable(int color, int size, GameWorld gw) {
		super(color,size, gw);
	}
	
	public Moveable(int color, GameWorld gw) {
		super(color, gw);
	}

	public void move() {
		float newX = (getX() + (float)(Math.cos(Math.toRadians(90-heading))) * speed);
		float newY = (getY() + (float)(Math.sin(Math.toRadians(90-heading))) * speed);
		int offset = this.getSize()/2;
		float oldX = (int)MapView.getOriginalMapView().getX();
		float oldY = (int)MapView.getOriginalMapView().getY();
		
		if(oldX + newX + offset >= MapView.getMapWidth() + oldX ) { 
			setHeading(360-heading);
		}
		
		if(this instanceof Spider) {
			if(oldX + newX <= oldX + 20 ) {
				setHeading(360-heading);
			}
		} else {
			if(oldX + newX <= oldX ) {
				setHeading(360-heading);
			}
		}
		
		if(oldY + newY + offset >= oldY + GameWorld.getHeight() ) {
			setHeading((360-heading+180)%180);
		}
		
		if(this instanceof Spider) {
			if(oldY + newY <= oldY + 25) {
				setHeading((360-heading+180)%180);
			} 
			} else {
				if(oldY + newY <= oldY) {
					setHeading((360-heading+180)%180);
				}
		}
		
		this.setLocation(newX,newY);
		
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
