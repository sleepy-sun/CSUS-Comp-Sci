package com.mycompany.a3;

import com.codename1.ui.geom.Point;

public abstract class Fixed extends GameObject implements ISelectable {
	
	private boolean selected;
	
	public Fixed(int color, GameWorld gw) {
		super(color, gw);
	}
	
	public Fixed(int color, int size, GameWorld gw){
		super(color,size, gw);
		
	}
	
	public void setSelected(boolean y) {
		selected = y;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	//This will hopefully change the location of the selected object
	public void setLocation(float x, float y) {
		if(selected) {
			super.setLocation(x, y);
		}
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int pointX = (int) pPtrRelPrnt.getX();
		int pointY = (int) pPtrRelPrnt.getY();
		int shpX = (int) (super.getX() + pCmpRelPrnt.getX());
		int shpY = (int) (super.getY() + pCmpRelPrnt.getY());
		boolean checkX = pointX > shpX && pointX < shpX+super.getSize();
		boolean checkY = pointY > shpY && pointY < shpY+super.getSize();
		return checkX && checkY;
	}
}
