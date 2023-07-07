
public class Dragon extends MagicalCreature{

	public Dragon(String name, String type, String color, int age) {
		super(name, type, color, age);
	}

	public String toString() {
		return super.toString() + " I breathe fire ";
	}
	public void kill(MagicalCreature other) {
		if (this == other) {
			System.out.println("I cannot kill myself");
		} else {
			if (other.type.equals("Dragon")) {
				System.out.println("Dragons cannot die!");
			} else {
				if (this.age >= 40) {
					other.die();
				} else {
					System.out.println("I am too young to kill!");
				}
			}
		}
	}
	public void die() {
		System.out.println(" I am a dragon - nobody gets to kill me !");
	}
}
