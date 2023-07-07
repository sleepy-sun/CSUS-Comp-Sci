/*
 * This method is a Strategy that steers and changes an NPC's heading
 * towards the Base after its lastBaseReached. 
 */
package com.mycompany.a4;
import com.codename1.util.*;


public class NextBase implements IStrategy{

	private NonPlayerCyborg cy;
	private int cyBase;
	private GameObjectCollection b;
	
	private double targetHeading;
	
	public NextBase() {}
	
	public NextBase(NonPlayerCyborg cy, GameObjectCollection b) {
		this.cy = cy;
		this.b = b;
		cyBase = cy.getLastBaseReached();
	}
	
	/*
	 * This method obtains and returns the Base object that the NPC needs to go toward.
	 */
	public Base getTargetedBase() {
		
		//1.)Create a Base and get the GameObjectCollection's iterator
		Base base = new Base();
		IIterator i = b.getIterator();
		
		//Traverse the iterator for all GameObjects of instance Base
		while(i.hasNext()) {
			base = (Base) i.getNext();						//For each Base found, get its number
			int baseNum = base.getNumber();
			if(baseNum == (cyBase + 1)) {			//If the base's number is the one right after the NPC's then the target Base is found
				break;
			}
		}
		
		return base;
	}
	
	/*
	 * This method calculates the heading the NPC needs to strive towards to head 
	 * in the direction of the Base
	 */
	public void updateTargetHeading() {
		//1.) Call getTargetedBase() to obtain the correct Base, get it's X and Y points, as well as the NPC's points
		Base target = getTargetedBase();
		float baseX = target.getX();
		float baseY = target.getY();
		
		float npcX = cy.getX();
		float npcY = cy.getY();
		
		//2.) If the two points make a diagonal --> if (the two points are on the same axis)
		if(!(baseX == npcX) || (baseY == npcY)) {
			
			//2a.)if the NPC is the bottom point:
			if(baseY > npcY) {
				
				//Find out direction of diagonal: down or up
				
				//Diagonal up:	if(the base is to the right of the NPC) --> CASE1: NPC is at the bottom and to the left
				if(baseX > npcX) {
					targetHeading = 90 - MathUtil.atan2(baseY, baseX);
				}
				
				else {	//Diagonal down: NPC must be to the right of the base --> CASE 2: NPC is at the bottom and to the right
					targetHeading = 270 + MathUtil.atan2(baseY, baseX);
				}
			}
			
			//2b.) Otherwise the NPC must be the top point
			else { 
				
				//Diagonal up:	if(NPC is to the right of the base) --> CASE 3: NPC is at the top and to the right
				if(npcX > baseX) {
					targetHeading = 180 + MathUtil.atan2(npcX,  npcY);
				}
				
				//Diagonal down: NPC must be to the left of the base --> CASE 4: NPC is at the top and to the left
				else {	
					targetHeading = 180 - MathUtil.atan2(npcX,  npcY);
				}
			}
		}
		
		//3.) Otherwise, the two points are on the same axis
		else {
			
			//On the same vertical
			if(baseX == npcX) {
				if(baseY > npcY) {		//Base is higher than the NPC
					targetHeading = 0;
				}
				
				else {					//Base is lower
					targetHeading = 180;
				}
			}
			
			//on the same Horizontal
			else { 
				if(baseX > npcX) {		//base is to the right of NPC
					targetHeading = 90;
				}
				else {					//base is to the left of NPC
					targetHeading = 270;
				}
			}
		}
	}
	
	/*
	 * This method obtains the targetedHeading, compares it to the NPC's currentHeading
	 * and finds the shorter path of either going west around (-) or east around (+).
	 * Then, it steers the NPC left or right accordingly and sets its heading.
	 */
	public void apply() {
		//1.) Update the targetedHeading, and get the NPC's currentHeading
		updateTargetHeading();
		
		int currHeading = cy.getHeading();
		
		//2.)Choose the shortest path, either going east around or west around
		
		//**Find which value is bigger, so when subtract --> result is not Neg.
		if(currHeading > targetHeading) {
			
			//Option1 = go the distance between the two headings
			//Option2 = go the other way around that
			double firstOption= currHeading - targetHeading;
			double secOption = 360 - firstOption;
			
			//Whichever option has the lower value, steer that way
			if(firstOption < secOption) {
				cy.leftSteer();
			}
			
			else {
				cy.rightSteer();
			}
		}
		
		//Repeat step 2: for a different case
		if(targetHeading > currHeading) {
			double firstOption= targetHeading - currHeading;
			double secOption = 360 - firstOption;
			
			if(firstOption < secOption) {
				cy.rightSteer();
			}
			
			else {
				cy.leftSteer();
			}
		}
		
		//3.)Set the NPC's heading
		int steerDir = cy.getSteeringDir();
		cy.setHeading(steerDir);
	}
		
}

