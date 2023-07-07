package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	private static int mapHeight;
	private static int mapWidth;
	static private Point originalMapView;
	
	public MapView() {
		MapView.mapHeight = this.getHeight();
		MapView.mapWidth = this.getWidth();
		this.getAllStyles().setBorder(Border.createLineBorder(10, ColorUtil.rgb(255,0, 0)));
		this.setLayout(new BorderLayout());
	}
	
	public void update (Observable o, Object arg) {
		gw = (GameWorld) arg;
		this.repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		IIterator itr = gw.getCollection().getIterator();
		while (itr.hasNext()) {
			GameObject object = itr.getNext();
			((IDrawable)object).draw(g, pCmpRelPrnt);
		}
	}
	
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		IIterator theElements = gw.gameObject.getIterator();
		while(theElements.hasNext()) {
			GameObject curObj = (GameObject)theElements.getNext();
			if (curObj instanceof Flag || curObj instanceof FoodStation) {
				if(((ISelectable)curObj).isSelected() && gw.isPositionable()) {
					curObj.setX(x-getX() - curObj.getSize()/2);
					curObj.setY(y-getY() - curObj.getSize()/2);
					((ISelectable)curObj).setSelected(false);
					gw.setPositionable(false);
				} else if(((ISelectable)curObj).contains(pPtrRelPrnt, pCmpRelPrnt)) {
					((ISelectable)curObj).setSelected(true);
				} else {
					((ISelectable)curObj).setSelected(false);
				}
			}
			repaint();
		} 
	}
			
	public static int getMapHeight() {
		return mapHeight;
	}

	public static void setMapHeight(int mapHeight) {
		MapView.mapHeight = mapHeight;
	}

	public static int getMapWidth() {
		return mapWidth;
	}

	public static void setMapWidth(int mapWidth) {
		MapView.mapWidth = mapWidth;
	}
	
	public void setOriginalMapView(Point p) { 
		MapView.originalMapView = p; 
		}
	public static Point getOriginalMapView() { 
		return originalMapView; 
		}
}