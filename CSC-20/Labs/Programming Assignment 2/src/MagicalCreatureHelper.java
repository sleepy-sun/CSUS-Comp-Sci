import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MagicalCreatureHelper {
	public static File file = new File("creatures.txt");
	static Scanner scan;
	public static ArrayList<MagicalCreature> creatures = new ArrayList<>();
	public static int MOVES;
	public static int ALIVE;
	
	// fillData() takes the file and reads the contents then proceeds to place the data into a 
	// listArray and splits it every ",". If it can't open the file it prints an error message.
	public void fillData(){
		String line;
		String[] temp;
		
		
		try {
			 scan = new Scanner(file);
		} catch (Exception e) {
			System.out.println("Error opening file!");
		}
		
		while (scan.hasNextLine()) {
			line = scan.nextLine();	
			temp = line.split(",");
			switch(temp[1]) {
			//These cases take the data from the file and place them into their own temporary array
			case "Dragon":
				creatures.add(new Dragon(temp[0], temp[1], temp[2], Integer.valueOf(temp[3])));
				break;
			case "Elf":
				creatures.add(new Elf(temp[0], temp[1], temp[2], Integer.valueOf(temp[3])));
				break;	
			case "Goblin":
				creatures.add(new Goblin(temp[0], temp[1], temp[2], Integer.valueOf(temp[3])));
				break;
			case "Genie":
				creatures.add(new Genie(temp[0], temp[1], temp[2], Integer.valueOf(temp[3])));
				break;	
			default:
				creatures.add(new MagicalCreature(temp[0], temp[1], temp[2], Integer.valueOf(temp[3])));
			}
			ALIVE++;
		} 
	}
	// war() inputs the creature arrayList and scanner and randomly selects killer
	// and victim. Then has the killer invoke the kill method in order to kill
	// victim
	public static void war() {
		Random rand = new Random();
		int alpha, beta, i = 0;
		System.out.println("How many moves do you want to play the game for: ");
		scan = new Scanner(System.in);
		MOVES = scan.nextInt();
		
		while (i < MOVES) {
			alpha = (rand.nextInt(creatures.size()));
			beta = (rand.nextInt(creatures.size()));
			
			MagicalCreature killer = creatures.get(alpha);
			MagicalCreature victim = creatures.get(beta);
			
			System.out.println("Killer: " + killer.type + " " + killer.name );
			System.out.println("Victim: " + victim.type + " " + victim.name );
				
			killer.kill(victim);
			
			if (!victim.alive) {
				System.out.println(victim.getName() + " has been killed.");
				creatures.remove(victim);
				ALIVE--;
				
			}
			creatureDisplay();
			i++;
		}
		
	}
	// creatureDisplay() prints the amount of creatures still alive
	public static void creatureDisplay() {
		System.out.println("--------------------------------------");
		System.out.println("Creatures still alive this move: " + ALIVE);
		System.out.println("--------------------------------------");
		for (MagicalCreature e : creatures) {
			System.out.println(creatures.indexOf(e) + " : " + e);
		}
		System.out.println();
	}
}