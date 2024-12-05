
import java.util.*;

public class dayFive extends Parser {
    /*  Two more 2d ArrayLists represent the sets that are already following the rules, and the sets that still need fixing */
    public static ArrayList<ArrayList<Integer>> noFix = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<ArrayList<Integer>> needsFixing = new ArrayList<ArrayList<Integer>>(); 
    public static void main(String[] args) {
        // initialize parser to read in data
        Parser p = new Parser();
        // Determine which sets are valid
        sortLists();
        // Fix invalid sets
        allValid();
        // Count mids for sets that didn't need fixing 
        System.out.println(countMids(noFix));
        // Count mids for sets that needed fixing
        System.out.println(countMids(needsFixing));
    }

    // Goes through the sets and determines which are valid and which are not
    public static void sortLists() {
        for(ArrayList<Integer> set: Parser.sets) {
            // boolean valid represents the validity of each individual set
            Boolean valid = true;
            // go through every single "rule" (remember each rule has the format [first, second])
            for(ArrayList<Integer> rule: Parser.rules) {
                int first = rule.get(0);
                int second = rule.get(1);
                if(indexOf(first, set)!=-1 && indexOf(second, set) != -1) {
                    if(indexOf(first, set)>indexOf(second, set)) {
                        // if the set breaks even one rule, it is not valid, add to needsFixing, break
                        valid = false;
                        needsFixing.add(set);
                        break;
                    }
                 }
            }
            // if the set follows all rules, add it to noFix 
            if(valid) {
                noFix.add(set);
            }
    
        }
    }

    // find/return index of int n in an array arr of integers
    public static int indexOf(int n, ArrayList<Integer> arr) {
        for(int i=0; i<arr.size(); i++) {
            if(arr.get(i)==n) {
                return i;
            }
        }
        return -1;
    }

    // modify needsFixing until it is valid
    public static void allValid() {
        // iterate through each element in needsFixing
        for(ArrayList<Integer> element: needsFixing) {
            // Check whether each element in needsFixing abides by every rule
            for(ArrayList<Integer> rule: Parser.rules) {
                int first = rule.get(0);
                int second = rule.get(1);
                if(indexOf(first, element)!=-1 && indexOf(second, element) != -1) {
                    if(indexOf(first, element)>indexOf(second, element)) {
                        // if an element in needsFixing breaks any rule, swap so that the rule is followed
                        element.set(indexOf(first, element), second);
                        element.set(indexOf(second, element), first);  
                        // post-swap, we need to check that all rules are followed again, so we run the method again
                        allValid();
                    }
                 }
            }
        }
        /*  If we search through each element in needsFixing, 
        and for each element we make sure it is following every rule,
        and it does not violate any rule and cause a swap,
        then the entire needsFixing arraylist is fixed and we can exit the method!! */
    }
    
    // Method to add up the middle values of a 2d integer arraylist and return the sum
    public static int countMids(ArrayList<ArrayList<Integer>> arr) {
        int count = 0;
        for(ArrayList<Integer> element: arr) {
            int middle = element.size()/2;
            count+=element.get(middle);
        }
        return count;
    }
}
    

