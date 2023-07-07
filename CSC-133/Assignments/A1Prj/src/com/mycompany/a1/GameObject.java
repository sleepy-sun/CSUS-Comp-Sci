package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	
	private Point location;
	private int color;
	private int size;
	
	public GameObject(int color){ 
		this.color = color;
		Random r = new Random();
		float x = r.nextFloat() * 1000;
		float y = r.nextFloat() * 1000;
		location = new Point(x,y);
		
		
	}
	
	
	public GameObject(int color, int size){
		this.color = color;
		this.size = size;
		Random r = new Random();
		float x = r.nextFloat() * 1000;
		float y = r.nextFloat() * 1000;
		location  = new Point(x,y);
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
	
	
	

}
