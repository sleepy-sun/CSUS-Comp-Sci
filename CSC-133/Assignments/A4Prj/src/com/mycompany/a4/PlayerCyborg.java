/*
 * This class specifies a type PlayerCyborg that is a Cyborg but can
 * only have one instance of itself.
 */
package com.mycompany.a4;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public class PlayerCyborg extends Cyborg{

	private static PlayerCyborg player;
	
	/*
	 * Contructor is private to ensure only this class can create
	 * an instance of itself
	 */
	private PlayerCyborg(float x, float y, int maxDamage, GameWorld gw) {
		super(x, y, maxDamage, gw);
		
	}
	
	
	/*
	 * If another class wants to create a PlayerCyborg, must invoke 
	 * this method which will construct the one available player
	 */
	public static PlayerCyborg getPlayer(float x, float y, int maxDamage, GameWorld gw) {
			if(player == null) {
				player = new PlayerCyborg(x, y, maxDamage, gw);
			}
			
			return player;
	}
	
	/*
	 * In order to restart the player's life, this method sets the one avaliable 
	 * player to null, in order to erase all of the previous data in the lost life.
	 */
	public void resetPlayer() {
		player = null;
	}
	
	/*
	 * An overridden method of the Cyborg in order to include the "Player Cyborg" label.
	 */
	public String toString() {
		return "Player Cyborg: " + super.toString();
	}

	public void draw(Graphics g, Point mapOrigin) {
		int size = super.getSize();
		float x = super.getX();
		float y = super.getY();
		
		g.setColor(ColorUtil.rgb(255, 0, 0));
		g.drawRect((int) (mapOrigin.getX() + (x - (size/2))), (int) (mapOrigin.getY() + (y - (size/2))), size, size );
		g.fillRect((int) (mapOrigin.getX() + (x - (size/2))), (int) (mapOrigin.getY() + (y - (size/2))), size, size );
		
	}
	
}
