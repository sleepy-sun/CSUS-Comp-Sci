package com.mycompany.a4;
import com.codename1.charts.util.*;
import java.util.Collection;
import java.util.Vector;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;


public class Base extends Fixed{

	private int size;
	private float x;
	private float y;
	private Point location;
	private int color;
	private int sequenceNumber;
	private GameWorld gw;
	private boolean isSelected = false;
	private Vector collisionsHandled;
	private Triangle shape;
	
	public Base() {}
	
	public Base(float x, float y, int sn, GameWorld gw) {
		size = 115;
		ColorUtil c = new ColorUtil();
		color = c.rgb(51, 153, 255);			//all bases are blue
		if((x >= 0 && x <= 1479) && (y >=0 && y <= 1213)) { 	//x and y are accepted if b/w 0-1000
			this.x = x;
			this.y = y;
			location = new Point(x,y);
		}
		sequenceNumber = sn;
		this.gw = gw;
		collisionsHandled = new Vector();
		shape = new Triangle(size, size, color, sequenceNumber);
		
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
	
	public int getNumber() {
		return sequenceNumber;
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
	
	public void setLocation() {}
	
	public void setLocation(float x, float y) {
		location.setX(x);
		location.setY(y);
	}
	
	public void setX(float x) {
		x = this.x;
	}
	
	public void setY(float y) {
		y = this.y;
	}
	public int getColor() {
		return color;
	}
	public void setColor() {}
	
	public String toString() {
		return "Base: loc = " + location.toString() + "color = [" + color + "] size = " + size + " seqNum = " + sequenceNumber;
	}
	
	public void draw(Graphics g, Point mapOrigin) {
		if(isSelected()) {
			shape.fill(g, mapOrigin);
		}
		else {
			shape.draw(g, mapOrigin, new Point(0, 0));
		}
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
		
		//otherwise, collision with a Drone in which does nothing
	}
	
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public boolean contains(Point ptrRelToPrnt, Point mapOrigin) {
		float ptrX = ptrRelToPrnt.getX();
		float ptrY = ptrRelToPrnt.getY();
		
		float thisX = mapOrigin.getX() + x;
		float thisY = mapOrigin.getY() + y;
		
		if((ptrX >= thisX - (size/2)) && (ptrX <= (thisX + (size/2)) && (ptrY >= thisY - (size/2)) && (ptrY <= (thisY + (size/2))))) {
			return true;
		}
		
		else {
			return false;		
		}
	}
	
	public boolean checkIfHandled(GameObject o) {
		
		return collisionsHandled.contains(o);
	}
	
}
