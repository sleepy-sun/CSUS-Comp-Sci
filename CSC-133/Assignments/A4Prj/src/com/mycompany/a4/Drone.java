package com.mycompany.a4;
import java.util.Random;
import java.util.Collection;
import java.util.Vector;
import com.codename1.charts.util.*;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class Drone extends Movable{
	
	//Define Drone constants, can be changed to fit needs
	private static int color = ColorUtil.rgb(255,0, 255);
	private static int speed = 25;
	
	private int size;
	private float x;
	private float y;
	private Point location;
	private int heading;
	private GameWorld gw;
	private Vector collisionsHandled;
	
	public Drone(GameWorld gw) {
		Random p = new Random();
		size = 50 + p.nextInt(100); 	//size is a random number b/w 150 - 215
		x = (size/2) + p.nextInt(1478 - (size/2));
		y = (size/2) + p.nextInt(1213 - (size/2));	//x & y are random coordinates b/w (shape boundary) - 1113
		location = new Point(x,y);
		
		heading = p.nextInt(360);	//random heading
		this.gw = gw;
		collisionsHandled = new Vector();
	}
	
	public Vector getCollisions() {
		return collisionsHandled;
	}
	public void clear() {
		collisionsHandled.clear();
	}
	
	public void add(GameObject o) {
		collisionsHandled.add(o);
	}
	public int getSize() {
		return size;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation() {}
	
	public void setLocation(float newX, float newY) {
		location.setX(newX);
		location.setY(newY);
		x = newX;
		y = newY;
		
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor() {}
	
	public void updateHeading() {
		Random p = new Random();
		int randomSteer = p.nextInt(35);		//Generate random steer from 0-10 (able to not steer at all)
		int negOrPos = p.nextInt(1);		//Generates 1 or 0, if 0 --> add steer, if 1 --> sub steer
		if(negOrPos == 1) {
			randomSteer = -1*randomSteer;
		}
		setHeading(randomSteer);
	}
	
	/*
	 * This method changes the heading by the angle of the steering wheel. If the total angle after this exceeds
	 * 360 deg. it will reset the heading according by 0 deg. steeringDirection will be reset at 0.
	 */
	public void setHeading(int steerDir) {
		//if the angle goes over 360 deg, going in the east direction, reset angle starting at 0 deg
		if((heading + steerDir) >= 360) {
			int leftOver = (heading + steerDir) - 360;
			heading = leftOver;
		}
		
		//if the total angle is negative, set the correct heading based on positive value
		else if (steerDir < 0 && heading + steerDir < 0 ){
			heading = (heading + steerDir + 360);
		}
		
		else {	//Otherwise, changing the heading by the steeringDirection poses no complications --> add normally
			heading = heading + steerDir;
		}
	}
	
	/**
	 * Updates the location according to the current heading and speed. However, the full values stored in heading 
	 * and speed are meant to apply only when a full second (1000 ms) has passed. Therefore, this method takes the amount
	 * of time elapsed, calculates how much of a second has gone by, and adjusts the movement according to that ratio.
	 */
	public void move(int elapsedTime) {
			double ratio = (double) elapsedTime/1000;
			double theta = Math.toRadians(90 - heading);
			double deltaX = Math.cos(theta)*(speed*ratio);
			double deltaY = Math.sin(theta)*(speed*ratio);
		
			float newX = (float) (x + deltaX);
			float newY = (float) (y + deltaY);
	
			float maxX = (1378 - (size/2));
			float maxY = (1113 - (size/2));
			
			if((newX <= (size/2)) || (newX >= maxX) || (newY <= (size/2)) || (newY >= maxY)){
				Random p = new Random();
				setHeading(p.nextInt(360));	//random heading
			}
			
			setLocation(newX, newY);
	}
		
	public String toString() {
		return "Drone: loc = " + location.toString() +  "X = " + x + "Y = " + y +" color = [" + color + "] heading = " + heading + " speed = " + speed + " size = " + size;
	}
	
	public void draw(Graphics g, Point mapOrigin) {
		float originX = mapOrigin.getX();
		float originY = mapOrigin.getY();
		double halfSize = size/2;
		
		int[] xPoints = {(int) (originX + (x + halfSize)), (int) (originX + (x - halfSize)), (int) (originX + x)};
		int[] yPoints = {(int) (originY + (y + halfSize)), (int) (originY + (y + halfSize)), (int) (originY + (y - halfSize))};
	
		g.setColor(ColorUtil.BLACK);
		g.drawPolygon(xPoints, yPoints, 3);
	
	}
	
	public boolean collidesWith(GameObject otherObject) {
		boolean result = true;
		//1.) Find which object is left of the other
				float otherX = otherObject.getX();
				
				if(x <= otherX) {
					result = findCollision(this, otherObject);
				}
				else {
					result = findCollision(otherObject, this);
				}
		return result;
	}
	
	public boolean findCollision(GameObject leftObj, GameObject rightObj) {
		//All points and evaluations based on origin of coordinate system in top left
		//For testing purposes, know what X and Y for both:
		float leftX = leftObj.getX();
		float leftY = leftObj.getY();
		
		float rightX = rightObj.getX();
		float rightY = rightObj.getY();
		
		//Obtain Left and Right points for both objects
		float leftObjRadius = (float) (leftObj.getSize()/2.1);
		
		float R1 = leftObj.getX() + leftObjRadius;
		float L1 = leftObj.getX() - leftObjRadius;
		
		float rightObjRadius = (float) (rightObj.getSize()/2.1);
		float R2 = rightObj.getX() + rightObjRadius;
		float L2 = rightObj.getX() - rightObjRadius;
		
		//Obtain top and bottom points for both objects (y points)
		float T1 = leftObj.getY() + leftObjRadius;
		float B1 = leftObj.getY() - leftObjRadius;
		
		float T2 = rightObj.getY() + rightObjRadius;
		float B2 = rightObj.getY() - rightObjRadius;
		
		boolean result = true;
		
		if((R1 < L2) || (L1 > R2) || (T2 < B1) ||(T1 < B2)) {
			result = false;
		}
	
		return result;
		
	}
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Cyborg) {
			Cyborg c = (Cyborg) otherObject;
			c.handleCollision(this);
		}
	
		//Otherwise, --> collision with Drone, which Homework instructions does not ask for any effects. 
	}
}
