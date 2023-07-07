package com.mycompany.a4;
import com.codename1.ui.Display;
import com.codename1.ui.util.UITimer;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

public class BGSound implements Runnable{

	private Media m;
	
	public BGSound(String file) {
		if(Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Must create sound objects after show()");
			System.exit(0);
		}
		
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + file);
		
			m = MediaManager.createMedia(is,  "audio/wav", this);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		m.pause();
	}
	
	public void play() {
		m.play();
	}
	
	//run() should be used when song has finished playing
	public void run() {
		m.setTime(0);
		m.play();
	}

}
