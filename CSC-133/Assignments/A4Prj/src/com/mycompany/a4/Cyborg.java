package com.mycompany.a4;
import com.codename1.charts.util.*;
import java.util.Collection;
import java.util.Vector;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.util.MathUtil;

import java.util.Random;

public class Cyborg extends Movable implements ISteerable{

	//Define Cyborg constants, can be changed so Cyborgs act differently
	private static int speed = 50;
	private static int energy = 1000;
	private static final int energyConRate = 1;
	private static final int size = 115;
	private static int color = ColorUtil.rgb(0, 255, 0); 		//all cyborgs are green
	
	//Class fields to be initialized in constructor
	private float x;
	private float y;
	private Point location;
	private static int steeringDirection;
	private int heading;
	private int maxSpeed;
	private int damageLevel;
	private int lastBaseReached;
	private int maxDamage;
	private int speedCanAccel;
	private GameWorld gw;
	private Vector collisionsHandled;
	
	public Cyborg() {};
	
	public Cyborg(float x, float y, int maxDamage, GameWorld gw) {
		if((x >= 0 && x <= 1479) && (y >=0 && y <= 1213)) { 	//x and y are accepted if b/w 0-1000
			this.x = x;
			this.y = y;
			location = new Point(x,y);
		}
		this.maxDamage = maxDamage;
		
		Random p = new Random();
		maxSpeed = 40 + p.nextInt(75);		//max speed is random for every cyborg, b/w 15-50
		heading = 0;
		damageLevel = 0;		
		lastBaseReached = 0;
		steeringDirection = 0;
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
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(){}
	
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
	
	public void setColor(int newColor) {
		color = newColor;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	public int getHeading() {
		return heading;
	}
	public int getEnergyLevel(){
		return energy;
	}
	public void setEnergyLevel(int level) {
		energy= level;
	}
	
	public int getEnergyConRate() {
		return energyConRate;
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
		
		steeringDirection = 0;		
	}
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	
	public void setLastBaseReached(int base) {
		lastBaseReached = base;
		
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}
	public void setDamageLevel(int damage) {
		damageLevel = damage;
	}
	
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public int getSteeringDir() {
		return steeringDirection;
	}
	
	public int getMaxDamage() {
		return maxDamage;
	}
	
	
	/**
	 * Updates the available speed the Cyborg can increase. It can only increase speed if it meets 
	 * the constraints: it's not at its max speed, it doesn't have max damage, and it still has energy.
	 * If there is no damage to the Cyborg, it can accelerate up to it's max speed. Otherwise, it can only
	 * accelerate up to the ratio of damage.
	 * 
	 * @return
	 */
	public int updateSpeedCanAccel() {
		if((speed < maxSpeed) && (damageLevel < maxDamage) && (energy > 0)) {		//can only accelerate when not yet at maxSpeed, not at maxDamage, and still have energy left
			if(damageLevel == 0) {						//if no damage at all --> can accelerate up to maxSpeed
				speedCanAccel = maxSpeed;
			}
			
			else {		//otherwise, can only accelerate according to ratio of damage
				double damageRatio = (double) damageLevel/(double)maxDamage;
				speedCanAccel = (int) damageRatio*maxSpeed;
			}
		}
		
		else {			//if any requirements not met --> cannot be accelerated
			speedCanAccel = 0;
			System.out.println("You cannot accelerate anymore. You have either reached maxSpeed, maxDamage, or have no energy");
		}
		return speedCanAccel;
	}
	
	/**
	 * Cyborgs accelerate steadily. This updates the available speed that can be accelerated to,
	 * if value >= 5 speed will increase by a steady 5. Otherwise, it accelerates what it can.
	 */
	public void accelerate() {
		/*updateSpeedCanAccel();			//ensure updated value
		
		if(speedCanAccel >= 5) {		//if able to accelerate at least 5, speed increases by 5
			speed = speed + 5;
			System.out.println("- You have accelerated by 5. \n");
		}
		
		else {							//otherwise, accelerate whatever is available (may not be any avail. at all)
			speed = speed + speedCanAccel;
			
			System.out.println("- You have accelerated by " + speedCanAccel + "\n");
		}*/
		
		speed = speed + 5;
	}
	
	
	/**
	 * As long as the cyborg has a speed, decrease it by 5
	 */
	public void brake() {
		if(speed > 0) {
			speed = speed - 5;
			
			System.out.println("- Brakes have been applied and you slowed by 5 \n");
		}
		
		else {
			System.out.println("- You have no speed to brake for. \n");
		}
	}
	
	/**
	 * Decreases speed accordingly due to damage. If not yet dead, speed will 
	 * decrease the same ratio as damage increased. 
	 */
	public void speedDueToColl() {
		//assumes the damage level has been updated first
		if(damageLevel >= maxDamage) {
			speed = 0;
		}
		
		else {
			double damageRatio = (double)damageLevel/ (double)maxDamage;
			double speedDecrease = damageRatio*speed;
			speed = speed - ((int) speedDecrease);
		}
	}
	
	
	/**
	 * Steer left 15 degrees as long as it doesn't exceed 40 deg. left of due North
	 */
	public void leftSteer() {
		steeringDirection = steeringDirection - 15;
	}
	
	/**
	 * Steers right 15 degree as long as it doesn't exceed 40 deg. right of due North
	 */
	public void rightSteer() {
		steeringDirection = steeringDirection + 15;
	}
	
	/**
	 * Updates the location according to the current heading and speed. However, the full values stored in heading 
	 * and speed are meant to apply only when a full second (1000 ms) has passed. Therefore, this method takes the amount
	 * of time elapsed, calculates how much of a second has gone by, and adjusts the movement according to that ratio.
	 */
	public void move(int elapsedTime) {
		if((damageLevel < maxDamage) && (energy > 0) && (speed > 0)) {		//as long as there is enough energy, some speed, and not dead
		
			double ratio = (double) elapsedTime/1000;
			double theta = Math.toRadians(90 - heading);
			double deltaX = Math.cos(theta)*(speed*ratio);
			double deltaY = Math.sin(theta)*(speed*ratio);
	
			float newX = (float) (x + deltaX);
			float newY = (float) (y + deltaY);

			float maxX = (1479 - (size/2));
			float maxY = (1213 - (size/2));
		
			if((newX <= (size/2)) || (newX >= maxX) || (newY <= (size/2)) || (newY >= maxY)){
				Random p = new Random();
				setHeading(p.nextInt(360));	//random heading
			}
			
			setLocation(newX, newY);
		}	
	}

	public void draw(Graphics g, Point mapOrigin) {}
	

	public String toString() {
		return " loc = " + location.toString() + "X = " + x + "Y = " + y + "color = [" + color + "] heading = " + heading + " speed = " + speed + "\n size = " + size + " maxSpeed = " + maxSpeed + " steeringDirection = " + steeringDirection + " energyLevel = " + energy + " damageLevel = " + damageLevel;
	}
	
	public boolean collidesWith(GameObject otherObject) {
		boolean result;
	
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
		//1.) Find which object is left of the other
		GameObject leftObj;
		float otherX = otherObject.getX();
				
		if(x <= otherX) {
			leftObj = this;
		}
		else {
			leftObj = otherObject;
		}
		
		//2.) Apply method according to GameObject type:
		if(otherObject instanceof Cyborg) {
			Cyborg cy2 = (Cyborg) otherObject;
			gw.collideWithCyborg(this, cy2, leftObj);
		}
		
		else if(otherObject instanceof Drone) {
			Drone d = (Drone) otherObject;
			gw.collideWithDrone(this, d, leftObj);
		}
		
		else if(otherObject instanceof Base) {
			Base b = (Base) otherObject;
			
			gw.collideWithBase(this, b, leftObj);
		}
		
		else {	//must have collided with EnergyStation
			EnergyStation es = (EnergyStation) otherObject;
			gw.collideWithES(this, es, leftObj);
		}
			
	}
	
}
