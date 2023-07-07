/*
 * This class serves as the Observer of the GameWorld. It will 
 * print out descriptions of every GameObject in the GameWorld every
 * time the GameWorld changes.
 */
package com.mycompany.a4;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
import java.util.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Border;
import java.lang.Object;
import com.codename1.charts.util.ColorUtil;

public class MapView extends Container implements Observer{

	private GameWorld gw;
	private Point ptrRelToPrnt;
	private float px;
	private float py;
	private boolean position;
	
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255,0,0)));
		this.setVisible(true);
		position = false;
	}
	/*
	 * This method obtains the GameWorld's GameObjects and prints each of their toString()
	 */
	public void update(Observable o, Object obj) {
		System.out.println("__________________________________________________________________________\n");
		System.out.println("This is the MapView:\n");
		//1.)Obtain the GameWorld, its collection, and iterator
		gw = (GameWorld) obj;
		
		GameObjectCollection c = gw.getCollection();
		IIterator objects = c.getIterator();
		
		//2.)For every element in the iterator, print its toString(). If the object is the player, update first
		while(objects.hasNext()) {
			GameObject ob = (GameObject) objects.getNext();
			if(ob instanceof PlayerCyborg) {
				System.out.println("- " + gw.getPlayer());
			}
			
			else {
				String descrip = ob.toString();
				System.out.println("- " + descrip);
			}
		}
		
		repaint();
		
		System.out.println("\nEnd of MapView");
		System.out.println("___________________________________________________________________________\n");
		
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		Point mapOrigin = new Point(getX(), getY());
		
		GameObjectCollection c = gw.getCollection();
		IIterator objects = c.getIterator();
		
		while(objects.hasNext()) {
			GameObject obj = (GameObject) objects.getNext();
			obj.draw(g, mapOrigin);
			
		}
	}
	
	public void pointerPressed(float x, float y) {
		px = x - getParent().getAbsoluteX();
		py = y - getParent().getAbsoluteY();
		
		ptrRelToPrnt = new Point(px, py);
		Point mapOrigin = new Point(getX(), getY());
		
		GameObjectCollection c = gw.getCollection();
		IIterator objects = c.getIterator();
		
		if(position == false) {
			while(objects.hasNext()) {
				GameObject obj = (GameObject) objects.getNext();
				if(obj instanceof ISelectable) {
					ISelectable o = (ISelectable) obj;
				
					if(o.contains(ptrRelToPrnt, mapOrigin)) {
						o.setSelected(true);
					}
				
					else {
						o.setSelected(false);
					}
				}
			}
			repaint();
		}
		
		else{
			while(objects.hasNext()) {
				GameObject obj = (GameObject) objects.getNext();
				if(obj instanceof Fixed) {
					Fixed o = (Fixed) obj;
					
					if(o.isSelected()) {
						o.setLocation(px, py);
						o.setX(px);
						o.setY(py);
						o.setSelected(false);
						break;
					}
				}
			}
			repaint();
			position = false;
		}

	}
	
	public void setPositionCom(boolean value) {
		position = value;
	}
}
