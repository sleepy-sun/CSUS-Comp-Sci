package com.mycompany.a4;

public interface IIterator {

	public abstract boolean hasNext();

	public abstract Object getNext();
	
	public void resetIterator();

}