package com.mycompany.a4;
import com.codename1.ui.Transform;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class PlayerShape {
	private Square head, leftArm, rightArm, leftLeg, rightLeg;
	private Circle body;
	private Transform myTranslate, myRotate, myScale;
	
	public PlayerShape(int size, int color) {
		myTranslate = Transform.makeIdentity();
		myRotate = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		
		//Body == 50% of the total size
		body = new Circle(size/2, color, -1);
		
		//Head and legs = %50 of the total size: Head = %20, Legs = 30%
		//Arms do not count for size since they hang at the side of body, but to proportion --> arms = 25%
		head = new Square((size*2)/10, color);
		leftLeg = new Square((size*3)/10, color);
		rightLeg = new Square((size*3)/10, color);
		leftArm = new Square((int)((size*2.5)/10), color);
		
		leftArm.scale((float) 0.25, 1);
		leftArm.rotate(-45);
		
		rightArm = new Square((int)((size*2.5)/10), color);
		rightArm.scale((float) 0.25, 1);
		rightArm.rotate(45);
		
		
	}
	
	public void rotate (float degrees) {
		myRotate.rotate((float)Math.toRadians(degrees), (float) 0, (float) 0);
	}
	
	public void scale (float x, float y) {
		myScale.scale(x, y);
	}
	
	public void translate (float x, float y) {
		myTranslate.translate(x, y);
	}

	public void draw(Graphics g, Point mapOrigin, Point screenOrigin) {
		Transform gTransform = Transform.makeIdentity();
		g.setTransform(gTransform);
		
		Transform gCopy = gTransform.copy();
		
		gTransform.translate(screenOrigin.getX(), screenOrigin.getY());
		gTransform.concatenate(myRotate);
		gTransform.translate(myTranslate.getTranslateX(), myTranslate.getTranslateY());
		gTransform.scale(myScale.getScaleX(), myScale.getScaleY());
		gTransform.translate(-screenOrigin.getX(), -screenOrigin.getY());
		
		g.setTransform(gCopy);
		
		
		
	}
}
