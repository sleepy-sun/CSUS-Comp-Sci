package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class GameWorld extends Observable {
	   GameObjectCollection gameObject;
	   private int ticker;
	   private PlayerAnt player;
	   private boolean soundOn;
	   Random rand = new Random();
	   
	   public GameWorld() {
		   gameObject = new GameObjectCollection();
		   player = new PlayerAnt();
	   }
	   
	   public void init(){
		   
		   this.ticker = 0;
		   this.soundOn = false;
		   
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
		   gameObject.add(player);
		   
		   this.setChanged();
		   this.notifyObservers(this);
		   
	}
	   //Methods for Game
	   
	   //Ant speeds up
	   public void antGo() {
		   //System.out.println("Ant goes a bit faster!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   ((PlayerAnt)tempObj).speedUp();
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   //Ant slows down
	   public void antSlow() {
		   //System.out.println("Ant slows down a bit!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   ((PlayerAnt)tempObj).speedDown();
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   //Ant turns left
	   public void antLeft() {
		   //System.out.println("Ant turns a bit to the left!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   ((PlayerAnt)tempObj).antLeft();
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   //Ant turns right
	   public void antRight() {
		   //System.out.println("Ant turns a bit to the right!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   ((PlayerAnt)tempObj).antRight();
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   //Ant reaches a flag
	   public void flagCollision(int c) {
		   System.out.println("Ant has reached a flag!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   int newflag = ((PlayerAnt)tempObj).getLastFlagReached();
				   newflag++;
				   if (newflag == c && c != 9) {
					   System.out.println("Ant has reached the next flag!");
					   ((PlayerAnt) tempObj).setLastFlagReached(c);
				   } else if (newflag == 9) {
					   System.out.println("Game Over, you win! Total time: " + this.ticker);
					   System.exit(0);
				   } else { 
						  System.out.println("That isnt the next flag!");
				   }
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   //Ant comes across some food
	   public void foodCollision() {
		  // System.out.println("Ant has collected some food!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   IIterator element_2 = gameObject.getIterator();
				   while(element_2.hasNext()) {
					   GameObject tempObj_2 = element_2.getNext();
					   if(tempObj_2 instanceof FoodStation) {
						   int cap = ((FoodStation) tempObj_2).getCapacity();
						   int food = ((PlayerAnt) tempObj).getFoodLevel();
						   ((PlayerAnt) tempObj).setFoodLevel(cap + food);
						   ((FoodStation) tempObj_2).setCapacity(0);
						   ((FoodStation) tempObj_2).setColor(0);
						   gameObject.add(new FoodStation());
						   break;
					   }
				   }
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   //Ant gets attacked by spider
	   public void spiderCollision() {
		   //System.out.println("Ant got bit by a spider!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   int health = ((PlayerAnt) tempObj).getHealthLevel();
				   ((PlayerAnt) tempObj).setHealthLevel(health - 1);
				   ((PlayerAnt) tempObj).colorFade(ColorUtil.rgb(255,0,0));
				   ((PlayerAnt) tempObj).checkHealth();
				   if(health <= 1) {
					   System.out.println("The ant has lost a life!");
					   ((PlayerAnt) tempObj).antReset(100, 100);
				   }
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	  
	   //World and timer will increase 
	   public void worldTick() {
		   //System.out.println("The world moves forward in time!");
		   ticker++;
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   ((PlayerAnt) tempObj).move();
				   ((PlayerAnt) tempObj).foodTick();
			   }
			   if(tempObj instanceof Spider) {
				   ((Spider) tempObj).move();
			   }
		   }
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	
	   //Displays the status of the world
	   public void gameDisplay() {
		  // System.out.println("Ant checks the current status of things!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   System.out.println("The amount of Ant lives is: " + ((PlayerAnt) tempObj).getAntLives());
				   System.out.println("The time elapsed is: " + ticker);
				   System.out.println("The last flag reached was: " + ((PlayerAnt) tempObj).getLastFlagReached());
				   System.out.println("The current food level is: " + ((PlayerAnt) tempObj).getFoodLevel());
				   System.out.println("The amount of Ant health is: " + ((PlayerAnt) tempObj).getHealthLevel());
			   }
		   }
	   }
	   
	   //Prints map
	   public void printMap() {
		   //System.out.println("Ant pulls out the Map!");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   System.out.println(((GameObject)element.getNext()).toString());
		   }
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
	
	//Things below are so the GameWorld can get things for ScoreView
	 public GameObjectCollection getCollection() {
		   return gameObject;
	   }
	 
	 //Ant lives
	 public int getLives() {
		 return player.getAntLives();
	 }
	 
	 //Ant food level
	 public int getFoodLevel() {
		 IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   return ((PlayerAnt) tempObj).getFoodLevel();
			   }
		   }
		   return 0;
	 }
	 
	 //Last flag Ant has reached
	 public int getLastFlagReached() {
		 IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   return ((PlayerAnt) tempObj).getLastFlagReached();
			   }
		   }
		   return 0;
	 }
	 
	 //Ant health left
	 public int getHealthLevel() {
		 IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   return ((PlayerAnt) tempObj).getHealthLevel();
			   }
		   }
		   return 0;
	 }
	 
	 //World time
	 public int getTime() {
		 return this.ticker;
	 }
	 
	 //Sound
	   public void setSound(boolean s) {
		   this.soundOn = s;
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   public boolean getSound() {
		   return soundOn;
	   }
}
