import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Arrays;
public class ReadFile {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Sheryl\\Downloads\\input"; // Replace with your file path
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
     //   System.out.println(one.toString());
      //  System.out.println(two.toString());
        int diff = 0;
        while(one.size()>0 && two.size()>0) {
            int oneMin = findMin(one);
            int twoMin = findMin(two);
            diff+= Math.abs(oneMin-twoMin);
        }

        System.out.println(diff);
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
        for(String a: one) {
            int b = Integer.parseInt(a);
            similarity += findNumMatches(b, two);
        }

        System.out.println(similarity);

    }

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

    public static int findNumMatches(int n, ArrayList<String>  arr) {
        int count=0;
        for(String a: arr) {
            int current = Integer.parseInt(a);
            if(current==n) {
                count++;
            }
        }
       // System.out.println(count);
        return count*n;
    }
}
