
public class Elf extends MagicalCreature{
public boolean shield;
	public Elf(String name, String type, String color, int age) {
		super(name, type, color, age);
		this.shield = false;
	}
	public String toString() {
		if(this.shield = true) {
			return super.toString() + " I eat leaves, I have a shield";
		} else {
			return super.toString() + " I eat leaves, I have no shield";
		}
	}
	public void setShield(boolean shield) {
		this.shield = shield;
	}
	public boolean getShield() {
		return this.shield;
	}
}
