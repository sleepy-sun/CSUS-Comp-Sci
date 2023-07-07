/*
 * This class is an Observer for the GameWorld class. It displays
 * the GameWorld's fields through a container.
 */
package com.mycompany.a4;
import java.util.Observer;
import java.util.*;
import com.codename1.ui.Component;
import com.codename1.ui.Label;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.*;
import com.codename1.charts.util.*;


public class ScoreView extends Container implements Observer{

	private GameWorld gw;
	private Container sv;
	private Label clock;
	private Label livesLeft;
	private Label lastBase;
	private Label energyLev;
	private Label damage;
	private Label sound;
	
	/*
	 * Constructor initializes all data needed to display, stylizes them, and adds all 
	 * Labels to the ScoreView container
	 */
	public ScoreView() {
		sv = new Container(new FlowLayout(Component.CENTER));
		
		clock = new Label("Time: \t");
		clock.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		clock.getUnselectedStyle().setPadding(Component.RIGHT, 8, false);
		
		livesLeft = new Label("Lives Left: \t");
		livesLeft.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		livesLeft.getUnselectedStyle().setPadding(Component.RIGHT, 8, false);
		
		lastBase = new Label("Player Last Base Reached: \t");
		lastBase.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		lastBase.getUnselectedStyle().setPadding(Component.RIGHT, 8, false);
		
		energyLev = new Label("Player Energy Level: \t");
		energyLev.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		energyLev.getUnselectedStyle().setPadding(Component.RIGHT, 8, false);
		
		damage = new Label("Player Damage Level: \t");
		damage.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		damage.getUnselectedStyle().setPadding(Component.RIGHT, 8, false);
		
		sound = new Label("Sound: \t");
		sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		sound.getUnselectedStyle().setPadding(Component.RIGHT, 8, false);
		
		sv.add(clock);
		sv.add(livesLeft);
		sv.add(lastBase);
		sv.add(energyLev);
		sv.add(damage);
		sv.add(sound);
	}

	/*
	 * This method typecasts the Observable object into a GameWorld Object. Then uses the GameWorld's 
	 * getter methods to set/reset the texts of each Label. The Container will be displayed 
	 * once again to re-show the updated data values.
	 */
	public void update(Observable o, Object obj) {
		gw = (GameWorld) obj; 
		String s;								//GameWorld and String object to re-use
		
		s = Integer.toString(gw.getTime());				//The following lines obtain the data needed, and resets the corresponding Label
		clock.setText("Time: \t" + s);

		s = Integer.toString(gw.getLivesLeft());
		livesLeft.setText("Lives Left: \t" + s);
		
		s = Integer.toString(gw.getLastBase());
		lastBase.setText("Player Last Base Reached: \t" +s);
		
		s = Integer.toString(gw.getEnergyLev());
		energyLev.setText("Player Energy Level: \t" + s);
		
		s = Integer.toString(gw.getDamageLev());
		damage.setText("Player Damage Level: \t" + s);
		
		boolean newsound= gw.getSound();					//Obtains the value of sound, and prints ON or OFF depending on value
		if(newsound) {
			sound.setText("Sound: \tON");
		}
		else {
			sound.setText("Sound: \tOFF");
		}
		
		sv.setVisible(true);							//refreshes the visibility of the ScoreView container
	}
	
	/*
	 * Returns the ScoreView container
	 */
	public Container getContainer() {
		return sv;
	}

	
}

