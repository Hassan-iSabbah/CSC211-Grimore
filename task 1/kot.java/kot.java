
import java.util.HashMap;

public class Kot {
    public static void main(String[] args) {

        HashMap<String, Integer> friends_ages = new HashMap<String, Integer>();
        Integer[] ages = {30, 25, 28, 35};
        String[] names = {"Владимир", "Vegeta", "Gohan", "Trunks"};

        for (int i = 0; i < names.length; i++) { //traversing the array 
            friends_ages.put(names[i], ages[i]);
        }

        System.out.println("My Best friends age is: " + String.valueOf(friends_ages.get("Владимир"))); 
    
    }
    
} 

/*  
    uyindoda = "You are a real man";
*/