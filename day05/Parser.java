import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;
/**
 * Class to parse text data since putting it in one file was a bit too much
 */
public class Parser {
    /* Static ArrayLists of Integer ArrayLists (inception!) representing the data in a more convenient way.
    rules contains the "rules" in [[first, second], [first, second]] format. 
    sets contains the pages in [[A, B, C], [A, D, C, B]] format */

    protected static ArrayList<ArrayList<Integer>> rules;
    protected static ArrayList<ArrayList<Integer>> sets;

    protected static String filePath = "C:\\Users\\Sheryl\\Downloads\\day5Files\\day5";

    public Parser() {
        rules = new ArrayList<ArrayList<Integer>>();
        sets = new ArrayList<ArrayList<Integer>>(); 
        
        // Parse all input text into one big string
        ArrayList<String> input = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] temp = scanner.nextLine().split("\n");
                String temp2 = "";
                for(String a: temp) {
                    temp2+=a;
                }
                
                input.add(temp2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Each rule/set is on a seperate line
        for(String line: input) {
             // To determine if a line is a rule, see if splitting it by | results in a valid array
            // If yes, change the array to an ArrayList and append to rules
            String[] rule = line.split(Pattern.quote("|"));
            if(rule.length>1) {
               ArrayList<Integer> ints = new ArrayList<Integer>();
               for(String a: rule) {
                   ints.add(Integer.parseInt(a));
               }
               rules.add(ints);
           } 
            // To determine if a line is a set, see if splitting it by , results in a valid array
            // If yes, change the array to an ArrayList and append to sets
           String[] set = line.split(Pattern.quote(","));
           if(set.length>1) {
               ArrayList<Integer> ints = new ArrayList<Integer>();
               for(String a: set) {
                   ints.add(Integer.parseInt(a));
               }
               sets.add(ints);
           }     
       }
    }
}
