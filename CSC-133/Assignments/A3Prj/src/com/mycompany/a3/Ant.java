package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public abstract class Ant extends Moveable implements ISteerable{
	private int maximumSpeed = 40;
	private int foodLevel = 2000;
	private double foodConsumptionRate = .5;
	private int healthLevel = 10;
	private int lastFlagReached = 1;
	private int antLives = 3;
	private int heading;
	public Ant(GameWorld gw) {
		super(ColorUtil.rgb(199,0,57),35, gw);
		super.setSpeed(0);
		super.setHeading(0);
	}
	
	public void colorFade(int color) {
		for (int i = 0; i < healthLevel; i++) {
			if(healthLevel != 0) {
				int newcolor = getColor() - 25;
				setColor(ColorUtil.rgb(newcolor, 0, 0));
			}
		}
	   }
	
	public void speedUp() {
		int speed = getSpeed();
		if (speed != maximumSpeed) {
			setSpeed(++speed);
		} else {
			System.out.println("You've already reached max speed!!");
		}
	}
	
	public void speedDown() {
		int speed = getSpeed();
		if (speed > 0) {
			setSpeed(--speed);
		}
	}
	
	public void antLeft() {
		if (getHeading() == 0) {
			setHeading(getHeading() + 360);
			setHeading(getHeading() - 30);
		} else {
			setHeading(getHeading() - 30);
		}
		
	}
	
	public void antRight() {
			setHeading(getHeading() + 30);
	}
	
	public void foodTick() {
		if(foodLevel != 1) {
			setFoodLevel((int) (getFoodLevel() - foodConsumptionRate));
		} else {
			System.out.println("The ant has starved to death!!");
			antReset(100,100);
		}
	}
	
	public void antReset(float x, float y) {
		if (antLives != 0) {
			setX(x);
			setY(y);
			setMaximumSpeed(40);
			setFoodLevel(2000);
			setHealthLevel(10);
			setFoodConsumptionRate(1);
			setSpeed(0);
			antLives--;
		} else {
			System.out.println("You have lost the game!");
			System.exit(0);
		}
	}
	
	public void checkHealth() {
		if(healthLevel != 1 && healthLevel < 10) {
			if(getSpeed() < getMaximumSpeed()) {
				setMaximumSpeed(getMaximumSpeed() - 4);
			} else {
				setSpeed(getMaximumSpeed());
			}
		}
	}
	
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}
	public int getFoodLevel() {
		return foodLevel;
	}
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	public int getHealthLevel() {
		return healthLevel;
	}
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	public int getAntLives() {
		return antLives;
	}

	public int getHeading() {
		return heading;
	}
	
	public void setSize(int size) {
		
	}
	
	public String toString() {
		return "Ant: loc=" + Math.round(this.getX()* 10.0)/ 10.0  + "," + Math.round(this.getY()*10.0)/10.0 +
				" color=" + super.toString() + 
				" size=" + super.getSize() + 
				" heading=" + getHeading() +
				" speed=" + getSpeed() + 
				" maxSpeed=" + getMaximumSpeed() +
				" foodConsumptionRate=" + foodLevel +
				" foodLevel=" + getFoodLevel() +
				" health=" + getHealthLevel();
	}
}
