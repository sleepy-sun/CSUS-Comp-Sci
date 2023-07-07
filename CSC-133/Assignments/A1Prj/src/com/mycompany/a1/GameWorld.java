package com.mycompany.a1;

import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	   private ArrayList<GameObject> gameObject = new ArrayList<GameObject>();
	   private int ticker = 0;
	   public void init(){
		   
		   //Add flags to game
		   gameObject.add(new Flag(1));
		   gameObject.add(new Flag(2));
		   gameObject.add(new Flag(3));
		   gameObject.add(new Flag(4));
		   
		   //Add spiders to game
		   gameObject.add(new Spider());
		   gameObject.add(new Spider());
		   
		   //Add food stations to game
		   gameObject.add(new FoodStation());
		   gameObject.add(new FoodStation());

		   //Add player ant
		   gameObject.add(new Ant());
		   
	}
	   //Methods for Game
	   public void antGo() {
		   System.out.println("Ant goes a bit faster!");
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   ((Ant) gameObject.get(i)).speedUp();
			   }
		   }
	   }
	   public void antSlow() {
		   System.out.println("Ant slows down a bit!");
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   ((Ant) gameObject.get(i)).speedDown();
			   }
		   }
	   }
	   public void antLeft() {
		   System.out.println("Ant turns a bit to the left!");
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   ((Ant) gameObject.get(i)).antLeft();
			   }
		   }
	   }
	   public void antRight() {
		   System.out.println("Ant turns a bit to the right!");
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   ((Ant) gameObject.get(i)).antRight();
			   }
		   }
	   }
	   public void flagCollision(int c) {
		   System.out.println("Ant has reached a flag!");
		   for (int i = 0; i < gameObject.size(); i++) {
			   if (gameObject.get(i) instanceof Ant) {
				  int newflag = ((Ant) gameObject.get(i)).getLastFlagReached();
				  newflag++;
				  if (newflag == c) {
					  ((Ant) gameObject.get(i)).setLastFlagReached(c);
				  } else {
					  System.out.println("That isnt the next flag!");
				  }
			   }
		   }
	   }
	   public void foodCollision() {
		   System.out.println("Ant has collected some food!");
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   for (int j = 0; j < gameObject.size(); j++) {
					   if (gameObject.get(j) instanceof FoodStation) {
						   int cap = ((FoodStation) gameObject.get(j)).getCapacity();
						   int food = ((Ant) gameObject.get(i)).getFoodLevel();
						   ((Ant) gameObject.get(i)).setFoodLevel(cap + food);
						   ((FoodStation) gameObject.get(j)).setCapacity(0);
						   ((FoodStation) gameObject.get(j)).setColor(0);
						   gameObject.add(new FoodStation());
						   break;
					   }

				   }
			   }
		   }
		   
	   }
	   public void spiderCollision() {
		   System.out.println("Ant got bit by a spider!");
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   int health = ((Ant) gameObject.get(i)).getHealthLevel();
				   ((Ant) gameObject.get(i)).setHealthLevel(health - 1);
				   ((Ant) gameObject.get(i)).colorFade(ColorUtil.rgb(255,0,0));
				   ((Ant) gameObject.get(i)).checkHealth();
				   if(health <= 1) {
					   System.out.println("The ant has lost a life!");
					   ((Ant) gameObject.get(i)).antReset(100,100);
				   } 
			   }
		   }
	   }
	   public void worldTick() {
		   System.out.println("The world moves forward in time!");
		   ticker++;
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   ((Ant) gameObject.get(i)).move();
				   ((Ant) gameObject.get(i)).foodTick();  
			   }
			   if (gameObject.get(i) instanceof Spider) {
				   ((Spider) gameObject.get(i)).move(); 
			   }
		   }
	   }
	   
	   public void gameDisplay() {
		   System.out.println("Ant checks the current status of things!");
		   for (int i = 0; i < gameObject.size(); i++) { 
			   if (gameObject.get(i) instanceof Ant) {
				   System.out.println("The amount of Ant lives is: " + ((Ant) gameObject.get(i)).getAntLives());
				   System.out.println("The time elapsed is: " + ticker);
				   System.out.println("The last flag reached was: " + ((Ant) gameObject.get(i)).getLastFlagReached());
				   System.out.println("The current food level is: " + ((Ant) gameObject.get(i)).getFoodLevel());
				   System.out.println("The amount of Ant health is: " + ((Ant) gameObject.get(i)).getHealthLevel());

			   }
		   } 
		   // Below code returns NullPointerException, only works if invoked as above
		   // will have to do further testing to understand why i get NullPointerException
		   // System.out.println("The amount of Ant lives is: " + ant.getAntLives());

	   }
	   public void printMap() {
		   System.out.println("Ant pulls out the Map!");
		   for (int i = 0; i < gameObject.size(); i++) {
			   System.out.println(gameObject.get(i).toString());
		   } 
		   //System.out.println(gameObject);
	   }
	   public void gameExit() {
		   
	   }
	   public void quit() {
		   System.out.println("Thank you for playing!");
		   System.exit(0);
	   }
	   
	public int getTicker() {
		return ticker;
	}
	public void setTicker(int ticker) {
		this.ticker = ticker;
	}
}
