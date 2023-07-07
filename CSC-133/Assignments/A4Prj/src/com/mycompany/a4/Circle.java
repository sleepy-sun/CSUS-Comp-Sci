package com.mycompany.a4;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.charts.models.Point;
import com.codename1.ui.Transform;

public class Circle {
	private int size;
	private int radius;
	private int color;
	private int printValue;
	private Transform myTranslate, myScale, myRotate;
	
	//If want no value printed --> showValue should be -1
	public Circle(int size, int color, int showValue) {
		this.size = size;
		radius = size/2;
		printValue = showValue;
		
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
		
		//Draw circle using approximated bottom left corner of circle, (bottom left in local space == top left in display
		g.drawArc(((int)(mapOrigin.getX()+(-(radius - (int)(radius*.35))))), (int)(mapOrigin.getY() + ((int)(-(radius - radius*.17)))), size, size, 0, 360);
		
		//Draw value label if applicable, in the center of circle
		if(printValue > 0) {
			g.setColor(ColorUtil.BLACK);
			g.drawString(Integer.toString(printValue), (int) (mapOrigin.getX()), (int) (mapOrigin.getY()));
		}
		
		g.setTransform(gCopy);
	}
	
	/*
	 * This method will fill a circle
	 */
	public void fill(Graphics g, Point mapOrigin) {
		g.setColor(color);
		g.fillArc(((int)(mapOrigin.getX()+(-(radius - (int)(radius*.35))))), (int)(mapOrigin.getY() + ((int)(-(radius - radius*.17)))), size, size, 0, 360);
	}
}
