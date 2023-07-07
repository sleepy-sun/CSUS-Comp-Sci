//Max Tran CSE 143, April 9, 2016
/*this program is used to keep track of an inventory of letters of the alphabet.
the input is a string and the program will compute the number of alphabet letters*/

public class LetterInventory {
   //the number of letter in the alphabet
   private static final int ALPHABET_SIZE = 26;
   //the number of alphabet letters in the string
   private int sum = 0;
   //the array to represent the count of the number of the alphabet
   private int[] count;
   
   // constructor to define the default behavior for the LetterInventory class
   public LetterInventory (String data){
      count = new int[ALPHABET_SIZE];
      data = data.toLowerCase();
      for(int i = 0; i <data.length();i++){
         if(Character.isLetter(data.charAt(i))){
            count[data.charAt(i)-97]++;
            sum++;
         }
      }
   }
   
   //this method is used to return the number of letter in the input string.
   public int get(char letter){
      if(!Character.isLetter(letter)){
         throw new IllegalArgumentException("non-alphabetic character: " + letter);
      }  
      return count[Character.toLowerCase(letter)-97];
   }
   
   //this method is used to replace the number of letters in the alphabet by a new number
   public void set(char letter, int value){
      if ((int)(letter-'a') < ALPHABET_SIZE && (int) (letter - 'a') >= 0 && value >= 0){
         sum += value - count[(int)(letter - 'a')];
         count[(int) (letter - 'a')] = value;
      } else {
         throw new IllegalArgumentException("Value too low or incorrect character. Letter: " + letter +", Value: " +value);
      }
   }
   
   //this method returns the number of alphabet letters in the string.
   public int size(){
      return sum;
   }
   
   //this methods will return true if its empty and false if its not.
   public boolean isEmpty(){
      return sum == 0;
   }
   
   // this method to represent the LetterInventory class on a console
   public String toString(){
      String inventory = "[";
      for(int i = 0;i < ALPHABET_SIZE;i++){
         char ch = (char)(i+97);;
         for(int j=0; j<count[i];j++){ 
            inventory += ch;
         }
      }
      return inventory +"]";
   }
   
   /*this method allows you to combine two different LetterInventory to get a new LetterInventory 
   which is a result of adding the two string together*/
   public LetterInventory add(LetterInventory other) {
      String fusion = "";
      LetterInventory total = new LetterInventory(fusion);
      for(int i = 0; i < ALPHABET_SIZE;i++){
         total.count[i] = this.count[i]+other.count[i];
      }
      total.sum = this.sum + other.sum;
      return total;
   }
   
   /*this method allows you to subtract one LetterInventory from the other to get a new LetterInventory
   with new sum, toString, etc...*/
   public LetterInventory subtract(LetterInventory other){
      String fusion = "";
      LetterInventory difference = new LetterInventory(fusion);
      for(int i = 0; i < ALPHABET_SIZE;i++){
         difference.count[i] = this.count[i] - other.count[i];
         if(difference.count[i]<0){
            return null;
         }
      }
      difference.sum = this.sum - other.sum;
      return difference;
   }
}





/*
public class LetterInventory {
    private int size;
    private int[] elementData;

    public static final int ALPHABET = 26;

    // post: constructs an inventory(elementData) for the letters in a given string
    //       and counts the number of times each letter occurred and then puts them
    //       in inventory and then increases the size
    //       upper case is same as lower in the inventory
    public LetterInventory(String data) {
        elementData = new int[ALPHABET];
        data = data.toLowerCase();
        for (int i = 0; i < data.length(); i++) {
            if (Character.isLetter(data.charAt(i))) {
                size++;
                elementData[data.charAt(i) - 'a']++;
            }
        }
    }

    // pre: throws IllegalArgumentException if letters are not alphabets
    // post: returns the count stored in inventory
    public int get(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException();
        }
        letter = Character.toLowerCase(letter);
        return elementData[letter - 'a'];
    }

    // pre: throws IllegalArgumentException if letters are not alphabets
    //      and positive or 0
    // post: sets the value in inventory
    public void set(char letter, int value) {
        if (!Character.isLetter(letter) || value < 0) {
            throw new IllegalArgumentException();
        }
        letter = Character.toLowerCase(letter);
        size -= elementData[letter - 'a'];
        elementData[letter - 'a'] = value;
        size += value;
    }

    // post: returns the size of inventory
    //       size -> sum of character counts
    public int size() {
        return size;
    }

    // post: returns true if inventory is Empty
    //       returns false if inventory is not Empty
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns an alphabetically organized version of the
    //       inventory with brackets
    public String toString() {
        String result = "[";
        for (int i = 0; i < ALPHABET; i++) {
            for (int j = 0; j < elementData[i]; j++) {
                result += (char) (i + 'a');
            }
        }
        return result + "]";
    }

    // Pre:  Accepts LetterInventory 'other' as parameter
    // Post: Returns new LetterInventory object with counts of each letter
    //       equal to the sum of counts of each letter in 'other' and the
    //       counts of each letter in current LetterInventory object
    public LetterInventory add(LetterInventory other) {
        LetterInventory sum = new LetterInventory("");
        for (int i = 0; i < ALPHABET; i++) {
            char ch = (char) ('a' + i);
            int value = elementData[i] - other.get(ch);
            sum.set(ch, value);
        }
        return sum;
    }

    // Pre:  Accepts LetterInventory 'other' as parameter
    // Post: Returns new LetterInventory object with counts of each letter
    //       equal to the difference of counts of each letter in 'other' and the
    //       counts of each letter in current LetterInventory object
    //       null if the difference is negative
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory diff = new LetterInventory("");
        for (int i = 0; i < ALPHABET; i++) {
            char ch = (char) ('a' + i);
            int value = elementData[i] - other.get(ch);
            if (value < 0) {
                return null;
            }
            diff.set(ch, value);
        }
        return diff;
    }
}
*/
