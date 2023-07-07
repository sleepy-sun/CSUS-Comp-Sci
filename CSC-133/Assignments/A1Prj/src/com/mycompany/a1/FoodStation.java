package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed{
	private int capacity;
	Random r = new Random();

	public FoodStation() {
		super(ColorUtil.BLUE);
		super.setSize(r.nextInt(50-10)+10);
		capacity = getSize();

	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String toString() {
		return "FoodStation: loc=" + Math.round(this.getX()* 10.0)/ 10.0  + "," + Math.round(this.getY()*10.0)/10.0 +
				" color=" + super.toString() + 
				" size=" + super.getSize() + 
				" capacity=" + getCapacity();
	}
}
