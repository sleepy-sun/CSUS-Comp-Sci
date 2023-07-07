package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	private TextArea info;
	private static int mapHeight;
	private static int mapWidth;
	
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(10, ColorUtil.rgb(255,0, 0)));
		MapView.mapHeight = this.getHeight();
		MapView.mapWidth = this.getWidth();
		this.setWidth(1000);
		this.setHeight(1000);
	}
	
	public void update (Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console
		gw = (GameWorld) arg;
		IIterator elements = gw.getCollection().getIterator();
		String display = "";
		while(elements.hasNext()) {
			display = elements.getNext().toString();
		}
		
	}

	public static int getMapHeight() {
		return mapHeight;
	}

	public static void setMapHeight(int height) {
		MapView.mapHeight = height;
	}

	public static int getMapWidth() {
		return mapWidth;
	}

	public static void setMapWidth(int mapWidth) {
		MapView.mapWidth = mapWidth;
	}
}