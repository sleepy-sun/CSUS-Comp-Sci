/*
 * This method is a Strategy that steers and changes an NPC's heading
 * towards the PlayerCyborg. 
 */
package com.mycompany.a4;

import com.codename1.util.MathUtil;

public class Attack implements IStrategy{

	private NonPlayerCyborg cy;
	private GameWorld gw;
	private PlayerCyborg player;
	
	private double targetHeading;
	
	public Attack() {}
	
	public Attack(NonPlayerCyborg cy, PlayerCyborg player) {
		this.cy = cy;
		this.player = player;
	}

	/*
	 * This method calculates the heading the NPC must strive to achieve in order
	 * to go towards the player's location. All targetHeadings calculated in the positive (+) 
	 * East direction
	 */
	public void getTargetHeading() {
	
		//1.) Obtain the X and Y points for both the player and the NPC
		float playerX = player.getX();
		float playerY = player.getY();
		
		float npcX = cy.getX();
		float npcY = cy.getY();
		
		
		//2.)If their locations make a diagonal line --> !(if the two points are on the same axis)
		if(!((playerX == npcX) || (playerY == npcY))) {

			//2a.)if the NPC is the bottom point: --> if(player is at a higher location)
			if(playerY > npcY) {
				
				//Find out direction of diagonal: down or up 
				
				//Diagonal up:	if(the player is to the right of the NPC)	--> CASE 1: NPC is at the bottom and to the left
				if(playerX > npcX) {
					targetHeading = 90 - MathUtil.atan2(playerY, playerX);
				}
				
				else {	//Diagonal down: Player is to the left of the NPC	--> CASE 2: NPC is at the bottom and to the right
					
					targetHeading = 270 + MathUtil.atan2(playerY, playerX);
				}
			}
			
			//2b.) Otherwise, the NPC is the top point
			else { 
				//Find out direction of diagonal: down or up
				
				//Diagonal up: if(NPC is to the right of the player) --> CASE 3: NPC is Taller and to the right
				if(npcX > playerX) {
					targetHeading = 180 + MathUtil.atan2(npcX,  npcY);
				}
				
				//Diagonal down: NPC must be to the left of the player --> CASE 4: NPC is taller and to the left
				else {	
					targetHeading = 180 - MathUtil.atan2(npcX,  npcY);
				}
			}
		}
		
		//3.) If their points don't make a diagonal --> on the same axis
		else {
			
			//3a.)On the same vertical
			if(playerX == npcX) {
				if(playerY > npcY) {		//if(Player is higher than the NPC)
					targetHeading = 0;
				}
				
				else {						//Otherwise, NPC is higher than player
					targetHeading = 180;
				
				}
			}
			
			//3b.) On the same horizontal
			else { 

				if(playerX > npcX) {	//if(player is to the right of NPC)
					targetHeading = 90;
				}
				else {					//Otherwise, player is to the left of NPC
					targetHeading = 270;	
				}
			}
		}
	}
	
	/*
	 * This method calls the getTargetHeading() to update the correct heading. 
	 * Then uses the targetHeading to compare to the NPC's currentHeading. It finds
	 * which of the two paths, either westward (-) or eastward (+) is shorter. Once the shortest
	 * path is found, NPC turns left or right accordingly and sets the new heading.
	 */
	public void apply() {
		//1.) Update the targetHeading, and obtain the NPC's currentHeading
		getTargetHeading();
		
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
