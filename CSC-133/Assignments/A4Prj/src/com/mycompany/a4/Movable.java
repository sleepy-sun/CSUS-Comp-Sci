package com.mycompany.a4;
import com.codename1.charts.util.*;
import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {

	private int size;
	private float x;
	private float y;
	private Point location;
	private int color;
	private int heading;
	private int speed;
	
	public Movable() {}

	public abstract int getSize();
	
	public abstract Point getLocation();
	
	public abstract void setLocation();
	
	public abstract int getColor();
	
	public abstract void setColor();
	
	public abstract void move(int elapsedTime);
}
