import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
public class Sobaka {
    public static void main(String[] args) {


        HashMap<String, Integer> friends_ages = new HashMap<String, Integer>();
        read_in_file(friends_ages);
        
        System.out.println("\n\n");
        System.out.println("Missing persons age: " + String.valueOf(friends_ages.get("Itachi"))); 
        System.out.println("\n\n");
    
    }

    public static void read_in_file(HashMap<String, Integer> friends_ages){
        try {
            String file_path = "Phonebook.txt";
            BufferedReader bf = new BufferedReader(new FileReader(file_path));

            String line;

            while ((line = bf.readLine()) != null){
                /* System.out.println(line);  */
                int delimiter = line.indexOf("-");
                String name = line.substring(0, delimiter);
                int age = Integer.parseInt(line.substring(delimiter + 1, line.length()));
                /* System.out.println(name);
                System.out.println(String.valueOf(age)); */
                //String-manipulation


                friends_ages.put(name, age);
            }

        } catch (Exception e) {
        }
    }
    
} 

/*  
    uyindoda = "You are a real man";
*/