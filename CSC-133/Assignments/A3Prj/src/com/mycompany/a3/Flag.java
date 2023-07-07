package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Flag extends Fixed implements IDrawable{
	
	private int sequenceNumber;
	private GameWorld gw;
	private boolean selected;
	
	//creates the color and size of flag as well as initializes sequenceNumber
	public Flag(int sequenceNumber, GameWorld gw) {
		super(ColorUtil.rgb(67, 84, 90), 50, gw);
		this.sequenceNumber = sequenceNumber;
		this.gw = gw;
	}
	
	//get and set for sequenceNumber
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	//leave empty as flags are one of the objects that can't change color
	public void setColor(int color) {
		
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
		if ( super.isSelected()) {
			g.drawPolygon(xPoints, yPoints, 3);
		} else {
			g.fillPolygon(xPoints, yPoints, 3);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + this.sequenceNumber, CenterX - 10, CenterY - 8);
	}
	
	@Override
	public String toString() {
		return "Flag: loc= " + Math.round(this.getX()* 10.0)/ 10.0  + "," + Math.round(this.getY()*10.0)/10.0 +
				" color= " + super.toString() + 
				" size= " + super.getSize() + 
				" seqNum= " + sequenceNumber;
	}
	
	
}
