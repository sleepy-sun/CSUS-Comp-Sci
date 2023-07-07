
public class Goblin extends MagicalCreature{

	public Goblin(String name, String type, String color, int age) {
		super(name, type, color, age);
	}
	public String toString() {
		return super.toString() + " I Kill elves if they do not have shields ";
	}
	
}
