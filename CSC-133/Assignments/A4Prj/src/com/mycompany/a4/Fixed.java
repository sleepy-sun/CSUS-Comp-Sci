package com.mycompany.a4;
import com.codename1.charts.util.*;
import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject implements ISelectable{

	private int size;
	private float x;
	private float y;
	private Point location;
	private int color;

	public Fixed() {}
	
	public abstract int getSize();
	
	public abstract Point getLocation();
	
	public abstract void setLocation(float x, float y);
	
	public abstract void setX(float x);
	
	public abstract void setY(float y);
	
	public abstract int getColor();
	
	public abstract void setColor();
}
