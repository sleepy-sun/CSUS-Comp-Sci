package com.mycompany.a4;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.charts.models.Point;


public class Square {

	private Point topLeft, topRight, bottomLeft, bottomRight;
	private int color;
	private Transform myTranslate, myScale, myRotate;
	
	public Square(int size, int color) {
		topLeft = new Point(-(size/2), size/2);
		topRight = new Point(size/2, size/2);
		bottomLeft = new Point(-(size/2), -(size/2));
		bottomRight = new Point(size/2, -(size/2));
		
		this.color = color;
		
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
				
		
		float mapX = mapOrigin.getX();
		float mapY= mapOrigin.getY();
		
		float tLeftX = topLeft.getX() + mapX;
		float tLeftY = topLeft.getY() + mapY;
		
		float tRightX = topRight.getX() + mapX;
		float tRightY = topRight.getY() + mapY;
		
		float bLeftX = bottomLeft.getX()+ mapX;
		float bLeftY = bottomLeft.getY() + mapY;
		
		float bRightX = bottomRight.getX() + mapX;
		float bRightY = bottomRight.getY() + mapY;
		
		
		//Draw line from topLeft to topRight
		g.drawLine ((int) tLeftX, (int) tLeftY, (int) tRightX, (int) tRightY);
				
		//draw line from topLeft to bottomleft
		g.drawLine ((int) tLeftX, (int) tLeftY, (int) bLeftX, (int) bLeftY);
		
		//draw line from bottomLeft to bottomRight
		g.drawLine ((int) bLeftX, (int) bLeftY, (int) bRightX, (int) bRightY);
		
		//draw line from topRight to bottomRight
		g.drawLine ((int) tRightX, (int) tRightY, (int) bRightX, (int) bRightY);
	
		g.setTransform(gCopy);
	}

}
