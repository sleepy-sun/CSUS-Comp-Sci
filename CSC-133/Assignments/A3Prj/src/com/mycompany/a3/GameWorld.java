package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class GameWorld extends Observable {
	   GameObjectCollection gameObject;
	   private int ticker;
	   private PlayerAnt player;
	   private static int height = 1000;
	   private static int width = 1000;
	   private boolean isPause;
	   private boolean sound;
	   private boolean positionable;
	   private String antEatFile = "ant_eat.wav";
	   private String checkpointFile = "flag_reached.wav";
	   private String antDamageFile = "ant_damage.mp3";
	   private Sound bgSong;
	   private Sound antDamage,antEat,checkpoint;
	   
	   
	   Random rand = new Random();
	   
	   public GameWorld() {
		   gameObject = new GameObjectCollection();
		   player = new PlayerAnt(this);
	   }
	   
	   public void init(){
		   
		   this.ticker = 0;
		   
		   //Add flags to game
		   gameObject.add(new Flag(1,this));
		   gameObject.add(new Flag(1,this));
		   gameObject.add(new Flag(2,this));
		   gameObject.add(new Flag(3,this));
		   gameObject.add(new Flag(4,this));
		   
		   //Add spiders to game
		   gameObject.add(new Spider(this));
		   gameObject.add(new Spider(this));
		   
		   //Add food stations to game
		   gameObject.add(new FoodStation(this));
		   gameObject.add(new FoodStation(this));

		   //Add player ant
		   gameObject.add(player);
		   
		   this.setChanged();
		   this.notifyObservers(this);
		   
	}
	   //Methods for Game
	   
	   //Ant speeds up
	   public void antGo() {
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
		   checkpoint.play();
		   System.out.println("hit a flag");
		   IIterator element = gameObject.getIterator();
		   while(element.hasNext()) {
			   GameObject tempObj = element.getNext();
			   if(tempObj instanceof PlayerAnt) {
				   int newflag = ((PlayerAnt)tempObj).getLastFlagReached();
				   newflag++;
				   if (newflag == c && c != 4) {
					   System.out.println("Ant has reached the next flag!");
					   ((PlayerAnt) tempObj).setLastFlagReached(c);
				   } else if (newflag == 4) {
					   System.out.println("Game Over, you win! Total time: " + getTime());
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
	   public void foodCollision(GameObject object) {
		   antEat.play();
		   System.out.println("hit a food");
		  if (((FoodStation)object).getCapacity() != 0) {
			  int cap = ((FoodStation)object).getCapacity();
			  player.setFoodLevel(cap + player.getFoodLevel());
			  ((FoodStation)object).setCapacity(0);
			  object.setColor(ColorUtil.rgb(255, 240, 240));
		  }
		  gameObject.add(new FoodStation(this));
		  
		  this.setChanged();
		  this.notifyObservers(this);
	   }
	   
	   //Ant gets attacked by spider
	   public void spiderCollision() {
		   antDamage.play();
		   System.out.println("hit a spider");
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
		   ticker++;
		   printMap();
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
		   IIterator otherElement = gameObject.getIterator();
		   while(otherElement.hasNext()) {
			   GameObject curObj = (GameObject) otherElement.getNext();
			   if (player.collidesWith(curObj)) {
				   player.handleCollision(curObj);
			   } else {
				   ArrayList<GameObject> objectsCollideWith = player.getObjectsCollideWith();
				   if (objectsCollideWith.contains(curObj)) {
					   objectsCollideWith.remove(curObj);
					   player.setObjectsCollideWith(objectsCollideWith);
				   }
			   }
		   }
		   if (getSound())
				bgSong.play();
		   this.setChanged();
		   this.notifyObservers(this);
	   }
	   
	   //Displays the status of the world
	   public void gameDisplay() {
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
		 return this.ticker/100;
	 }

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int h) {
		GameWorld.height = h;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int w) {
		GameWorld.width = w;
	}

	public boolean getPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}
	
	public void setPositionable(boolean positionable) {
		this.positionable = positionable;
	}
	public boolean isPositionable() {
		return positionable;
	}
	
	//Sound Elements
	public void setSound(boolean s) {
		this.sound = s;
		this.setChanged();
		this.notifyObservers(this);
	}
	   
	public boolean getSound() {
		return sound;
	}
	
	public void createSound() {
		antDamage = new Sound(antDamageFile);
		antEat = new Sound(antEatFile);
		checkpoint = new Sound(checkpointFile);
		bgSong = new Sound("forest4.wav");
	}
	
	public void BGsoundOn() {
		if(getSound()) {
			bgSong.play();
		}
	}
	
	public void BGsoundOff() {
		bgSong.pause();
	}
}
