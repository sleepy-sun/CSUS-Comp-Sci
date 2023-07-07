package com.mycompany.a3;



import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class PlayerAnt extends Ant{
public PlayerAnt(GameWorld gw) {
		super(gw);
		// TODO Auto-generated constructor stub
	}

public static PlayerAnt player;
	
	public String toString() {
		String parentDesc = super.toString();
		return parentDesc;
	}
	
	public static PlayerAnt getPlayerAnt(GameWorld gw) {
		if (player == null) 
			player = new PlayerAnt(gw);
		return player;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int x  = (int) this.getX() + (int)pCmpRelPrnt.getX();
		int y  = (int) this.getY() + (int)pCmpRelPrnt.getY();
		g.fillArc(x, y, this.getSize(), this.getSize(), 0, 360);
	}
}
