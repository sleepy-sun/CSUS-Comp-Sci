/*
 * This class acts as a way to collect all GameObjects into 
 * one data field. It provides an iterator for itself to allow
 * other classes to traverse it's collection without knowing extra 
 * information.
 */
package com.mycompany.a4;
import java.util.Collection;
import java.util.Vector;

public class GameObjectCollection implements ICollection {

	private Vector collection;
	private int id;
	
	public GameObjectCollection() {
		collection = new Vector();
	}

	/*
	 * This method adds an Object to the collection.
	 */
	public void add(Object newObject) {
		collection.addElement(newObject);
		
	}
	

	/*
	 * This method returns an IIterator using the private class
	 * GameObjectIterator().
	 */
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	/*
	 * This method returns the size of the collection
	 */
	public int getSize() {
		int index = 0;
		for(Object obj : collection) {
			index++;
		}
		return index;
	}
	
	public void clear() {
		collection.clear();
	}
	
	public int getIndexOf(GameObject o) {
		return collection.indexOf(o);
	}
	
	public void remove(int i) {
		collection.removeElementAt(i);
	}
	/*
	 * This class acts as an Iterator for GameObjects.
	 */
	private class GameObjectIterator implements IIterator{
		private int currIndex;
		
		public GameObjectIterator() {
			currIndex = -1;
		}
		
		/*
		 * Resets the Iterator to traverse from the beginning
		 */
		public void resetIterator() {
			currIndex = 0;
		}
		
		
		/*
		 * Returns whether or not there is an object next in the collection
		 */
		public boolean hasNext() {
			//if the collection is empty --> false
			if(collection.size() <= 0) {
				return false;
			}
			//if the current index is at the last element --> false
			if(currIndex == collection.size() - 1) {
				return false;
			}
			
			return true;
		}
		
		/*
		 * Gets and returns the next object in the collection
		 */
		public Object getNext() {
			currIndex++;
			return(collection.elementAt(currIndex));
		}
		
	}
}
