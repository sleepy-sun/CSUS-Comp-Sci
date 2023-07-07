package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed {
	
	private int sequenceNumber;
	
	//creates the color and size of flag as well as initializes sequenceNumber
	public Flag(int sequenceNumber) {
		super(ColorUtil.BLUE,10);
		this.sequenceNumber = sequenceNumber;
	}
	
	//get and set for sequenceNumber
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	//leave empty as flags are one of the objects that can't change color
	public void setColor(int color) {
		
	}
	@Override
	public String toString() {
		return "Flag: loc= " + Math.round(this.getX()* 10.0)/ 10.0  + "," + Math.round(this.getY()*10.0)/10.0 +
				" color= " + super.toString() + 
				" size= " + super.getSize() + 
				" seqNum= " + sequenceNumber;
	}
	
	
}
