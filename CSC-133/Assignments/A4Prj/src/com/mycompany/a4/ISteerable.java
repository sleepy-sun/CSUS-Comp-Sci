package com.mycompany.a4;

public interface ISteerable {

	static int steeringDirection = 0;
	
	public abstract void leftSteer();
	
	public abstract void rightSteer();
}
