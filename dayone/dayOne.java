import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Arrays;
public class ReadFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Sheryl\\Downloads\\input"; 
        ArrayList<String> one = new ArrayList<String>();
        ArrayList<String> two = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                one.add(line[0]);
                two.add(line[3]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int diff = 0;
        // Find and remove mins from both lists until they are empty
        // On each iteration, add difference b/n mins to the diff variable
        while(one.size()>0 && two.size()>0) {
            int oneMin = findMin(one);
            int twoMin = findMin(two);
            diff+= Math.abs(oneMin-twoMin);
        }

        System.out.println(diff);
        // lazy reset of both arrays lol
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                one.add(line[0]);
                two.add(line[3]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        int similarity = 0;
        // iterate through one array find similarity with two
        for(String a: one) {
            // for each string, parse into int before running function
            int b = Integer.parseInt(a);
            similarity += findNumMatches(b, two);
        }

        System.out.println(similarity);

    }

    // finds and removes the minimum of array
    public static int findMin(ArrayList<String> arr) {
        int min = Integer.parseInt(arr.get(0));
        int ind = 0;
        for(int i=0; i<arr.size(); i++) {
            int curr = Integer.parseInt(arr.get(i));
            if(curr<min) {
                min=curr;
                ind = i;
            }
        }
        arr.remove(ind);
        return min;
    }

    // find count = the # of times that an int n appears in an array, and return that count * n
    public static int findNumMatches(int n, ArrayList<String>  arr) {
        int count=0;
        for(String a: arr) {
            int current = Integer.parseInt(a);
            if(current==n) {
                count++;
            }
        }
        return count*n;
    }
}
