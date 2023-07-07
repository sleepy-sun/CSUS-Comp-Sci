package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;



public class Spider extends Moveable implements IDrawable {
	Random r = new Random();
	public Spider(GameWorld gw) {
		super(ColorUtil.rgb(128,128,128),gw);
		super.setSize(r.nextInt(50-10)+10);
		super.setHeading(r.nextInt(359));
		super.setSpeed(r.nextInt(10-3)+1);
	}
	
	public void moveSpider() {
		setHeading(getHeading()+r.nextInt(5));
	}
	
	//empty since color can't be changed and is set upon creation
	public void setColor (int color) {
		
	}
	
	//empty since size can't be changed and is set upon creation
	public void setSize(int size) {
		
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int size = this.getSize();
		int CenterX = (int) (this.getX() + pCmpRelPrnt.getX());
		int CenterY = (int) (this.getY() + pCmpRelPrnt.getY());
		int xTop = CenterX;
		int yTop = CenterY - size;
		int xLeft = CenterX - size;
		int yLeft = CenterY + size;
		int xRight = CenterX + size;
		int yRight = CenterY + size;
		int[] xPoints = {xTop, xLeft, xRight};
		int[] yPoints = {yTop, yLeft, yRight};
		g.setColor(super.getColor());
		g.drawPolygon(xPoints, yPoints, 3);
	}
	
	public String toString() {
		return "Spider: loc=" + Math.round(this.getX()* 10.0)/ 10.0  + "," + Math.round(this.getY()*10.0)/10.0 +
				" color=" + super.toString() + 
				" size=" + super.getSize() + 
				" heading=" + getHeading() +
				" speed=" + getSpeed();
	}
}
