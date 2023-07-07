
public class Shark extends Critter {
    private int count;     
    private int max;     
    private Direction direction;     
    
    public Shark() {         
        count = 0;         
        max = 1;         
        direction = Direction.NORTH;     
    }     
    public Direction getMove() {         
        count++;         
        if (count > max) {             
            count = 1;             
            max++;
            if (direction == Direction.NORTH) {                 
                direction = Direction.SOUTH;             
            } else {                 
                direction = Direction.NORTH;             
            } 
        }  return direction;     
    } 
}