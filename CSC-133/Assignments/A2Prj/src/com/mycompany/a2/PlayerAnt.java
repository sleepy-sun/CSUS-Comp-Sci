package com.mycompany.a2;

public class PlayerAnt extends Ant {
public static PlayerAnt player;
	
	public String toString() {
		String parentDesc = super.toString();
		return parentDesc;
	}
	
	public static PlayerAnt getPlayerAnt() {
		if (player == null) 
			player = new PlayerAnt();
		return player;
	}
}
