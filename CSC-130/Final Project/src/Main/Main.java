package Main;

//Javier Briseno
//CSC 130
//5-13-2022

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import Data.Vector2D;
import Data.boundingBox;
import Data.spriteInfo;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	
	//Text color
	public static Color c = new Color(255,215,0);
	
	//Things pertaining to Steven 
	public static Vector2D location = new Vector2D(500, 330);
	public static spriteInfo steven = new spriteInfo(location, "guy");
	public static HashMap<String, String> map = new HashMap<>();
	
	//Button press booleans
	public static boolean dPress = false;
	public static boolean sPress = false;
	public static boolean aPress = false;
	public static boolean wPress = false;
	public static boolean spacePress = false;
	public static boolean facingItemDown = false;
	public static boolean facingItemUp = false;
	
	//Movement key things
	public static stopWatchX moveSpeed = new stopWatchX(10);
	public static stopWatchX frames = new stopWatchX(60);
	
	public static ArrayList<spriteInfo> spritesUp = new ArrayList<>();
	public static int currentSpriteIndexUp = 0;
		
	public static ArrayList<spriteInfo> spritesLeft = new ArrayList<>();
	public static int currentSpriteIndexLeft = 0;
	
	public static ArrayList<spriteInfo> spritesRight = new ArrayList<>();
	public static int currentSpriteIndexRight = 0;
	
	public static ArrayList<spriteInfo> spritesDown = new ArrayList<>();
	public static int currentSpriteIndexDown = 0;
		
		
	//Bounding Boxes
	public static ArrayList<boundingBox> boundingBoxes = new ArrayList<>();
		
		
	//Code for Checkpoint 5
	//public static String trigger = "";
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		//Wall collisions

		//Top (SIDE NOTE) Clips slightly into top wall but only because if you 
		//don't then you can't get over the Potion
		boundingBoxes.add(new boundingBox(0,1280,-20,-10));
		//Bottom
		boundingBoxes.add(new boundingBox(0,1280,660,800));
		//Left
		boundingBoxes.add(new boundingBox(0, -20,-500,800));
		//Right
		boundingBoxes.add(new boundingBox(1180,1240,-500,800));
		
		
		//Item  collisions
		
		//Potion
		boundingBoxes.add(new boundingBox(970, 1010, 30, 100)); 
		//Tablet
		boundingBoxes.add(new boundingBox(130, 170, 420, 480)); 

		
		//Up Movement (w)
		int guyNumberU = 1;
		for (int i = 800; i != (0); i-=5){

			Vector2D location = new Vector2D(0,i);
			String currentGuyU = "u" + guyNumberU;

			spriteInfo sprite = new spriteInfo(location,currentGuyU);
			spritesUp.add(sprite);
			guyNumberU++;
			
			if (guyNumberU == 6) {
				guyNumberU = 1;
			}
		}
		
		//Left Movement (a)
		int guyNumberL = 1;
		 for (int i = 1280; i != (0); i-=5){

			 Vector2D location = new Vector2D(i,0);
			 String currentGuyL = "l" + guyNumberL;

			 spriteInfo sprite = new spriteInfo(location,currentGuyL);
			 spritesLeft.add(sprite);
			 guyNumberL++;
			
			 if (guyNumberL == 5) {
				 guyNumberL = 1;
			 }
		 }
			
		 //Right Movement (d)
		 int guyNumberR = 1;
		 for (int i = 0; i < (1280); i+=5){

			 Vector2D location = new Vector2D(i,0);
			 String currentGuyR = "r" + guyNumberR;

			 spriteInfo sprite = new spriteInfo(location,currentGuyR);
			 spritesRight.add(sprite);
			 guyNumberR++;
					
			 if (guyNumberR == 5) {
				 guyNumberR = 1;
			 }
		 }
				
	     // Down Movement (s)
		 int guyNumberD = 1;
		 for (int i = 0; i < (800); i+=5){

			 Vector2D location = new Vector2D(0,i);
			 String currentGuyD = "d" + guyNumberD;

			 spriteInfo sprite = new spriteInfo(location,currentGuyD);
			 spritesDown.add(sprite);
			 guyNumberD++;
					
			 if (guyNumberD == 6) {
				 guyNumberD = 1;
			 }
		 }
		 
		
		//Hashmap Strings
		EZFileRead ezr = new EZFileRead("Steven.txt");

		for (int i = 0; i < ezr.getNumLines(); i++){
			String raw = ezr.getLine(i);
			 StringTokenizer st = new StringTokenizer(raw, "*");
			 String Key = st.nextToken(); 
			 String Value = st.nextToken(); 
			 map.put(Key, Value); 
		
		}
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		
		//Load in the Dungeon and Items
		ctrl.addSpriteToFrontBuffer(0, 0, "room");
		ctrl.addSpriteToFrontBuffer(987, 87, "potion");
		ctrl.addSpriteToFrontBuffer(150, 490, "tablet");

		//Item interaction Hashmap grabs
		String upsideDown = map.get("string5");
		String normalTab = map.get("string4");
		String nothing = map.get("string3");
		String drink = map.get("string2");
		
		//Boundingbox things
		boundingBox tablet = boundingBoxes.get(5);
		boundingBox potion = boundingBoxes.get(4);
		boundingBox itemtest1 = new boundingBox(steven.getCoords().getX() - 10, steven.getCoords().getX() + 10, steven.getCoords().getY() - 20, steven.getCoords().getY() + 10);
		boundingBox itemtest2 = new boundingBox(steven.getCoords().getX() - 10, steven.getCoords().getX() + 10, steven.getCoords().getY() - 20, steven.getCoords().getY() + 20);
		boundingBox itemtest3 = new boundingBox(steven.getCoords().getX() - 20, steven.getCoords().getX() + 20, steven.getCoords().getY() - 20, steven.getCoords().getY() + 30);

		//Item interactions
		
		//Tablet interaction
		if (!boundCheck(itemtest3,tablet) && !boundCheck(itemtest3,potion)) {
			if (spacePress) {
				ctrl.drawString(steven.getCoords().getX() + 10, steven.getCoords().getY() + 10, nothing, c);
			}
		}
		if (boundCheck(itemtest2, tablet) && facingItemDown) {
		if (spacePress) {
			ctrl.drawString(120, 596, upsideDown, c);
			}  
		} else if (boundCheck(itemtest1, tablet) && facingItemUp) {
			if (spacePress) {
				ctrl.drawString(80, 500, normalTab, c);
			}
		}
		
		
		//Potion interaction
		if (boundCheck(itemtest1,potion) && facingItemUp) {
			if (spacePress) {
				ctrl.drawString(930, 120, drink, c);
				}  
			}
		
	    //Movement Right
		if(dPress) {
			spriteInfo right = spritesRight.get(currentSpriteIndexRight);
			boundingBox player = new boundingBox(steven.getCoords().getX() - 10, steven.getCoords().getX() + 20, steven.getCoords().getY() - 10, steven.getCoords().getY() + 10);
			if (moveSpeed.isTimeUp() && !boundCheck(player)) {
				steven.getCoords().adjustX(3);
				moveSpeed.resetWatch();
			}	
			
			if (frames.isTimeUp()) {
				steven.setTag(right.getTag());
				currentSpriteIndexRight++;
				frames.resetWatch();
			}
				
				
			if (currentSpriteIndexRight == spritesRight.size()) {
					currentSpriteIndexRight = 0;
			}
		}
		
		//Movement Left
		if(aPress) {
			spriteInfo left = spritesLeft.get(currentSpriteIndexLeft);
			boundingBox player = new boundingBox(steven.getCoords().getX() - 20, steven.getCoords().getX() + 10, steven.getCoords().getY() - 10, steven.getCoords().getY() + 10);
			if (moveSpeed.isTimeUp() && !boundCheck(player)) {
				steven.getCoords().adjustX(-3);
				moveSpeed.resetWatch();
			}
			
			if (frames.isTimeUp()) {
				steven.setTag(left.getTag());
				currentSpriteIndexLeft++;
				frames.resetWatch();
			}
					
				
			if (currentSpriteIndexLeft == spritesLeft.size()) {
				currentSpriteIndexLeft = 0;
			}
		}
				
		//Movement Up
		if(wPress) {
			spriteInfo up = spritesUp.get(currentSpriteIndexUp);
			boundingBox player = new boundingBox(steven.getCoords().getX() - 10, steven.getCoords().getX() + 10, steven.getCoords().getY() - 20, steven.getCoords().getY() + 10);
			if (moveSpeed.isTimeUp() && !boundCheck(player)) {
				steven.getCoords().adjustY(-3);
				moveSpeed.resetWatch();
			}
			
			if (frames.isTimeUp()) {
				steven.setTag(up.getTag());
				currentSpriteIndexUp++;
				frames.resetWatch();
			}
					
				
			if (currentSpriteIndexUp == spritesUp.size()) {
				currentSpriteIndexUp = 0;
			}
		}
				
		//Movement Down
		if(sPress) {
			spriteInfo down = spritesDown.get(currentSpriteIndexDown);
			boundingBox player = new boundingBox(steven.getCoords().getX() - 10, steven.getCoords().getX() + 10, steven.getCoords().getY() - 10, steven.getCoords().getY() + 20);
			if (moveSpeed.isTimeUp() && !boundCheck(player)) {
				steven.getCoords().adjustY(3);
				moveSpeed.resetWatch();
			}	
			
			if (frames.isTimeUp()) {
				steven.setTag(down.getTag());
				currentSpriteIndexDown++;
				frames.resetWatch();
			}
					
				
			if (currentSpriteIndexDown == spritesDown.size()) {
				currentSpriteIndexDown = 0;
			}
		}
		
		ctrl.addSpriteToFrontBuffer(steven.getCoords().getX(), steven.getCoords().getY(), steven.getTag());
		
	}
	
	// Additional Static methods below...(if needed)
	
	public static boolean boundCheck(boundingBox box2) {
		 boolean test = false;
		for (boundingBox box1 : boundingBoxes) {
        	if ((box1.getX1() > box2.getX2()) || (box1.getX2() < box2.getX1()) || (box1.getY1() > box2.getY2()) || (box1.getY2() < box2.getY1())) {
        		test = false;
        	} else {
        		return true;
        	}

    	}
		return test;
	}
	
	public static boolean boundCheck(boundingBox box1, boundingBox box2) {
       	if ((box1.getX1() > box2.getX2()) || (box1.getX2() < box2.getX1()) || (box1.getY1() > box2.getY2()) || (box1.getY2() < box2.getY1())) {
       		return false;
       	} else {
       		return true;
       	}
	}
   
}
