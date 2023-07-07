package com.mycompany.a2;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> gameObject;
	
	public GameObjectCollection() {
		gameObject = new ArrayList<GameObject>();
	}
	
	private class GameIterator implements IIterator {
		private int currIndex = 0;
		
		@Override
		public boolean hasNext() {
			if(gameObject.size() <= 0) 
				return false;
			if(currIndex == gameObject.size() - 1) {
				return false;
			}
			return true;
		}

		@Override
		public GameObject getNext() {
			currIndex++;
			return gameObject.get(currIndex);
		}

		@Override
		public GameObject getCurrent() {
			return gameObject.get(currIndex);
		}
	}

	@Override
	public void add(GameObject object) {
		gameObject.add(object);
		
	}
	
	public IIterator getIterator() {
		return new GameIterator();
	}
}
