
public class Genie extends MagicalCreature{
public boolean wand;

	public Genie(String name, String type, String color, int age) {
		super(name, type, color, age);
		this.wand = false;
	}
	public String toString() {
		if(this.wand = true) {
			return super.toString() + " I eat leaves, I have a wand";
		} else {
			return super.toString() + " I eat leaves, I have no wand";
		}
	}
	void setWand(boolean wand) {
		this.wand = wand;
	}
	public boolean getWand() {
		return this.wand;
	}
	public void kill(MagicalCreature other) {
		if (this == other) {
			System.out.println("I cannot kill myself");
		} else {
			boolean attack = (this.getWand()) ? true : false;
			if (attack) {
				other.die();
			} else {
			 System.out.println("I cannot kill without my wand!");
			}
		}
	}
}
