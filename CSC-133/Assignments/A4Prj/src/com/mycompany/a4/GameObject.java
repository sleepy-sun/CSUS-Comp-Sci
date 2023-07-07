package com.mycompany.a4; 
import com.codename1.charts.models.Point;
import com.codename1.charts.util.*;
import java.util.Collection;
import java.util.Vector;

public abstract class GameObject implements IDrawable, ICollider {

	private int size;
	private float x;
	private float y;
	private Point location;
	private int color;
	private Vector collisionsHandled;
		
	public GameObject() {
		
	}
	
	public abstract Vector getCollisions();
	
	public abstract void clear();
	
	public abstract void add(GameObject o);
	
	public abstract int getSize();
	
	public abstract Point getLocation();
	
	public abstract void setLocation();
	
	public abstract int getColor();
	
	public abstract void setColor();
	
	public abstract float getX();
	
	public abstract float getY();
	

}
