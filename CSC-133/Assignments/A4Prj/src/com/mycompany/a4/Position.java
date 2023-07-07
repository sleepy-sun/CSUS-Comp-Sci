package com.mycompany.a4;
import com.codename1.ui.events.*;
import com.codename1.ui.Command;
import com.codename1.charts.models.Point;

public class Position extends Command{

	private MapView mv;

	public Position(MapView mv) {
		super("Position");
		this.mv = mv;
	}

	public void actionPerformed(ActionEvent ev) {
		mv.setPositionCom(true);
	}
		
}
