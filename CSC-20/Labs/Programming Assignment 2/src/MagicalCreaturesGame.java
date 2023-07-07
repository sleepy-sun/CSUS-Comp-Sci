/*
 * This program is going to run a game that when the number of moves is inputed will
 * run that many times. A killer and victim will be chosen and when the killer has killed 
 * the victim will be removed from the game and will not be alive in the final round.
 * 
 *  @author Javier Briseno
 *  
 *  @param age how old the creature is
 *  @param type type of creature
 *  @param name name of the creature
 *  @param color color of the creature
 *  
 *  @throws file read error
 */

public class MagicalCreaturesGame {
	
	public static void main(String[] args) {
		MagicalCreatureHelper help = new MagicalCreatureHelper();
		//calls the methods needed to make the game work
		help.fillData();
		help.war();
	
		
	}
		
}