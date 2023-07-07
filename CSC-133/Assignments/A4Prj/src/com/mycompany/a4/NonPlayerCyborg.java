/*
 * This class specifies a NonPlayerCyborg as a type of Cyborg. NonPlayerCyborgs
 * have strategies that may change or be invoked.
 */
package com.mycompany.a4;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
import com.codename1.ui.Transform;

public class NonPlayerCyborg extends Cyborg {

	private IStrategy currStrategy; 
	private GameWorld gw;
	GameObjectCollection bases;
	private Square shape;

	public NonPlayerCyborg() {}
	
	/*
	 * Contstructor: Must give information to construct a normal Cyborg, and a reference
	 * to the GameWorld's iterator in order to invoke it's strategies towards it's targets
	 */
	public NonPlayerCyborg(float x, float y, int maxDamage, GameWorld gw) {
		super(x, y, maxDamage, gw);
		this.gw = gw;
		shape = new Square(super.getSize(), ColorUtil.rgb(255, 0, 0));
		
		//Get a collection of Bases for the NextBase{} Strategy: stored in private field
				GameObjectCollection c = gw.getCollection();
				IIterator i = c.getIterator();
				bases = new GameObjectCollection();
				
				//Traverse the iterator for all GameObjects of instance Base
				while(i.hasNext()) {
					GameObject obj = (GameObject) i.getNext();
					if(obj instanceof Base) {
						Base b = (Base) obj;
						bases.add(b);
					}
				}
				
				
		//choose strategy at random
		Random r = new Random();
		int strat = r.nextInt(2);	//can only choose 0 or 1
		
		//Assigns currentStrategy according to the random number generated
		if(strat == 0) {
			currStrategy = new NextBase(this, bases);
		}
		else {
			currStrategy = new Attack(this, gw.getPlayer());
		}
	}
	
	/*
	 * This method returns the collection of bases in the GameWorld. 
	 * Method should be used when switching strategies to NextBase{}
	 */
	public GameObjectCollection getBasesInGW() {
		return bases;
	}
	
	/*
	 * List of getter methods, self-explanatory
	 */
	public PlayerCyborg getPlayer() {
		return gw.getPlayer();
	}
	
	public IStrategy getStrategy() {
		return currStrategy;
	}
	
	/*
	 * This method changes the currentStrategy to the parameter "s"
	 */
	public void setStrategy(IStrategy s) {
		currStrategy = s;
	}
	
	/*
	 * This method will call the currentStrategy's apply(). It puts the
	 * strategy in action.
	 */
	public void invokeStrategy() {
		currStrategy.apply();
	}
	
	/*
	 * An overridden toString method to include the NPC's strategy
	 */
	public String toString() {
		
		return "Non-Player Cyborg: " + super.toString() + " Current Strategy = " + currStrategy;
	}
	
	public void draw(Graphics g, Point mapOrigin) {
		shape.draw(g,  mapOrigin, new Point(0, 0));				//I'm coming back here after awhile so I'm sure this is bug
	}
	
}
