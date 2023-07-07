package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class FoodStation extends Fixed implements IDrawable{
	private int capacity;
	Random r = new Random();

	public FoodStation(GameWorld gw) {
		super(ColorUtil.GREEN, gw);
		super.setSize(r.nextInt(75-30)+20);
		capacity = getSize();
		this.gw = gw;
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
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int size = this.getSize();
		int CenterX =(int) (this.getX() + pCmpRelPrnt.getX());
		int CenterY = (int) (this.getY() + pCmpRelPrnt.getY());
		int xTop = CenterX + size;
		int yTop = CenterY - size;
		int xLeft = CenterX + size;
		int yLeft = CenterY + size;
		int xRight = CenterX - size;
		int yRight = CenterY - size;
		int xBottom = CenterX - size;
		int yBottom = CenterY + size;
		int[] xPoints = {xTop, xLeft, xBottom, xRight,};
		int[] yPoints = {yTop, yLeft, yBottom, yRight};
		g.setColor(super.getColor());
		if ( super.isSelected()) {
			g.drawPolygon(xPoints, yPoints, 4);
		} else {
			g.fillPolygon(xPoints, yPoints, 4);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.capacity, CenterX - size/4, CenterY - size/4);
	}
	
}
