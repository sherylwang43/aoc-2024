import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Arrays;

public class day4 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Sheryl\\Downloads\\input4"; 
        ArrayList<String> lines = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Boolean> trues = new ArrayList<Boolean>();
        int numXmasMatches=0;
        int numX_Mas=0;

        // Since the input is a bunch of lines of Strings with the same length, it can be thought of as a 2d array of characters
        // Here we iterate through each line and each character in line
        for(int i = 0; i<lines.size(); i++) {
            String line = lines.get(i);
            for(int j=0; j< line.length(); j++) {
                int[] directions = {1,-1};
                for(int k: directions) {
                    // Each checker function runs on the selected character, if it works, add a TRUE to the trues array
                    horizontalMatch(i, j, k, lines, trues);
                    verticalMatch(i, j, k, lines, trues);
                    diagonalMatch(i, j, k, 1, lines, trues);
                    diagonalMatch(i, j, k, -1, lines, trues);
                }
                numXmasMatches = trues.size();

                // Part two
                if(lines.get(i).charAt(j) == 'A') {
                    try {
                        String one = ""+ lines.get(i-1).charAt(j-1) + lines.get(i+1).charAt(j+1); // string with one diagonal
                        String two = ""+lines.get(i-1).charAt(j+1) + lines.get(i+1).charAt(j-1); // string with other diagonal

                        if( (one.equals("SM") | one.equals("MS")) && 
                        (two.equals("SM") | two.equals("MS")) ) {
                            numX_Mas++;
                        }
                    } catch (IndexOutOfBoundsException e) {

                    }
                }
            }            
        }
     
        System.out.println(numXmasMatches);
        System.out.println(numX_Mas);
    }

    // Here "direction" indicated whether we are moving right (direction=1), or left (direction=-1). It also serves as a useful multiplier
    public static void horizontalMatch(int vertIndex, int horIndex, int direction, ArrayList<String> lines, ArrayList<Boolean> arr) {
        try {
            String a = "";
            for(int i=0; i<4; i++) {
                a+=lines.get(vertIndex).charAt(horIndex+direction*i);
            }
            if (a.equals("XMAS")) {
                arr.add(true);
            }
        } catch (IndexOutOfBoundsException e) {
                
        }
    }
    // Direction indicates whether we are moving upwards (-1) or downwards (1)
    public static void verticalMatch(int vertIndex, int horizontalIndex, int direction, ArrayList<String> lines, ArrayList<Boolean> arr) {
        try {
            String a = "";
            for(int i=0; i<4; i++) {
                a+=lines.get(vertIndex+direction*i).charAt(horizontalIndex);
            }
            if (a.equals("XMAS")) { 
                arr.add(true);
            }
        } catch (IndexOutOfBoundsException e) {
                
        }
    }
        
    // Two direction variables here, horizontal and vertical so we can check all four diagonals
    public static void diagonalMatch(int vertIndex, int horIndex, int vertDirection, int horDirection, ArrayList<String> lines, ArrayList<Boolean> arr) {
            try {
                String a = "";
                for(int i=0; i<4; i++) {
                    a+=lines.get(vertIndex+vertDirection*i).charAt(horIndex+horDirection*i);
                }
                if (a.equals("XMAS")) { 
                    arr.add(true);
                }
            } catch (IndexOutOfBoundsException e) {

            }
        }

        
    }
