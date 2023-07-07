
public class MagicalCreature {
	// declaring the values that will be used
	public String name;
    public String type;
    public String color;
    public int age;
    public boolean alive = true;
	
    //constructor
	public MagicalCreature (String name, String type, String color, int age) {
		this.name = name;
    	this.type = type;
    	this.color = color;
    	this.age = age;
    	this.alive = true;
	}
	
	public MagicalCreature() {
		this.name = "";
    	this.type = "";
    	this.color = "";
    	this.age = 0;
    	this.alive = true;
	}
	// classic getters and setters
	public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public String getType() {
    	return type;
    }
    public void setType(String type) {
    	this.type = type;
    }
    public String getColor() {
    	return color;
    }
    public void setColor(String color) {
    	this.color = color;
    }
    public int getAge() {
    	return age;
    }
    public void setAge(int age) {
    	this.age = age;
    }
    public boolean getAlive() {
    	return alive;
    }
    public void setAlive(boolean alive) {
    	this.alive = alive;
    }
    
    //kill() will kill the other creature unless creature doesn't have their license
    public void kill(MagicalCreature other) {
    	System.out.println("I do not have the license to kill ");
    }
    //die() just sets creature alive status to false which is dead
    public void die() {
    	this.alive = false;
    }
    // toString() prints string that lists all the different variables for the creatures
    public String toString() {
    	return "My name is " + name + " I am a " + color + " " + type + " I am " + age + " years old ";
    }
    
}
