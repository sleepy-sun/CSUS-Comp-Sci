package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable, ICollider{
	
	private Point location;
	private int color;
	private int size;
	private ArrayList<GameObject> objectsCollideWith = new ArrayList<GameObject>();
	GameWorld gw;
	
	public GameObject(int color, GameWorld newGw){ 
		this.color = color;
		Random r = new Random();
		float x = r.nextInt(1000 - 100) + 100;
		float y = r.nextInt(1000 - 100) + 100;
		this.gw = newGw;
		this.location = new Point(x,y);
	}
	
	
	public GameObject(int color, int size, GameWorld newGw){
		this.color = color;
		this.size = size;
		Random r = new Random();
		float x = r.nextInt(1000 - 100) + 100;
		float y = r.nextInt(1000 - 100) + 100;
		this.gw = newGw;
		this.location  = new Point(x,y);
	}

	public ArrayList<GameObject> getObjectsCollideWith() {
		return objectsCollideWith;
	}
	
	public void setObjectsCollideWith(ArrayList<GameObject> objectsCollideWith) {
		this.objectsCollideWith = objectsCollideWith;
	}
	
	public float getX() {
		return location.getX();
	}
	
	public void setX(float x) {
		location.setX(x);
	}
	
	public float getY() {
		return location.getY();
	}
	
	public void setY(float y) {
		location.setY(y);
	}
	
	public void setLocation(float x, float y) {
		this.location = new Point(x,y);
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		return "[" + ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color) + "]";
	}
	
	//Taken from 11-IntroToAnimation
	public boolean collidesWith(GameObject otherObject) {
		boolean result = false;
		
		// find centers
		double thisCenterX = this.getX() + (otherObject.size/2); 
		double thisCenterY = this.getY() + (otherObject.size/2);
		
		double otherCenterX = otherObject.getX();
		double otherCenterY = otherObject.getY();
		
		// find dist between centers (use square, to avoid taking roots)
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		double distBetweenCentersSqr = (dx*dx + dy*dy);
		
		// find square of sum of radii
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true ; 
			} return result ;
		} 
	
	public void handleCollision(GameObject otherObject ) {
			if(!objectsCollideWith.contains(otherObject)) {
				objectsCollideWith.add(otherObject);
				this.setObjectsCollideWith(objectsCollideWith);
				if(otherObject instanceof FoodStation) {
						gw.foodCollision(otherObject);
				} else if(otherObject instanceof Spider) {
						gw.spiderCollision();
				} else if(otherObject instanceof Flag) {
						gw.flagCollision(((Flag)otherObject).getSequenceNumber());
				}
			}
	}
}
