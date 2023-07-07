package com.mycompany.a4;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Transform;

public class Triangle {
	private Point top, bottomLeft, bottomRight;
	private int color;
	private int printValue;
	private Transform myTranslate, myScale, myRotate;
	
	//If want no value printed --> value should be -1
	public Triangle(int base, int height, int color, int value) {
		top = new Point(0, height/2);
		bottomLeft = new Point(-(base/2), -(height/2));
		bottomRight = new Point(base/2, -(height/2));
		this.color = color;
		printValue = value;
		
		myTranslate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		myRotate = Transform.makeIdentity();
	}
	
	public void translate(float x, float y) {
		myTranslate.translate(x, y);
	}
	
	public void scale(float x, float y) {
		myScale.scale(x, y);
	}
	
	public void rotate(float degrees) {
		myRotate.rotate((float) Math.toRadians(degrees), 0, 0);
	}
	
	public void resetTransform() {
		myRotate.setIdentity();
		myScale.setIdentity();
		myTranslate.setIdentity();
	}
	
	public void draw(Graphics g, Point mapOrigin, Point screenOrigin) {
		
		g.setColor(color);
		
		//Make a copy of Graphic's transform, apply Local Transformations, and copy it to Graphic's transform
		Transform gTransform = Transform.makeIdentity();
		g.getTransform(gTransform);
		Transform gCopy = gTransform.copy();
		
		gTransform.translate(screenOrigin.getX(), screenOrigin.getY());
		gTransform.concatenate(myRotate);
		gTransform.translate(myTranslate.getTranslateX(), myTranslate.getTranslateY());
		gTransform.scale(myScale.getScaleX(), myScale.getScaleY());
		gTransform.translate(-screenOrigin.getX(), -screenOrigin.getY());
		
		g.setTransform(gTransform);
		
		//Calculate points and draw lines
		float mapX = mapOrigin.getX();
		float mapY= mapOrigin.getY();
		
		float topX = top.getX() + mapX;
		float topY = top.getY() + mapY;
		
		float bLeftX = bottomLeft.getX()+ mapX;
		float bLeftY = bottomLeft.getY() + mapY;
		
		float bRightX = bottomRight.getX() + mapX;
		float bRightY = bottomRight.getY() + mapY;
		
		//Draw line from top to bottomLeft
		g.drawLine((int) topX, (int) topY, (int) bLeftX, (int) bLeftY);
		
		//draw line from top to bottomRight
		g.drawLine((int) topX, (int) topY, (int) bRightX, (int) bRightY);
		
		//draw line from bottomLeft to bottomRight
		g.drawLine((int) bLeftX, (int) bLeftY, (int) bRightX, (int) bRightY);
		
		//Print value wanted in center, black text, if applicable
		if(printValue > 0) {
			g.setColor(ColorUtil.BLACK);
			g.drawString(Integer.toString(printValue), (int) mapOrigin.getX(), (int) mapOrigin.getY());
		}
		
		g.setTransform(gCopy);
	}
	
	public void fill(Graphics g, Point mapOrigin) {
		float mapX = mapOrigin.getX();
		float mapY = mapOrigin.getY();
		
		float topX = top.getX() + mapX;
		float topY = top.getY() + mapY;
		
		float bLeftX = bottomLeft.getX()+ mapX;
		float bLeftY = bottomLeft.getY() + mapY;
		
		float bRightX = bottomRight.getX() + mapX;
		float bRightY = bottomRight.getY() + mapY;
		
		int[] xPoints = {(int) (topX), (int) (bLeftX), (int) (bRightX)};
		int[] yPoints = {(int) (topY), (int) (bLeftY), (int) (bRightY)};
		
		g.setColor(color);
		
		g.fillPolygon(xPoints, yPoints, 3);
	}
}
