/*
 * This class serves as a style format for the buttons created in the Game{}
 */
package com.mycompany.a4;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Button;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;

public class MyButton extends Form{

	private Button b;
	
	public MyButton() {
		b = new Button();
		
		b.getUnselectedStyle().setBgTransparency(255);
		b.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		b.getUnselectedStyle().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		b.getAllStyles().setPadding(5, 5, 5, 5);
		//b.getAllStyles().setPadding(Component.BOTTOM, 10);
		b.getAllStyles().setAlignment(CENTER);
		
	}
	
	/*
	 * This is a getter method to return a stylized button.
	 */
	public Button getButton(String s) {
		b.setText(s);
		return b;
	}

}
