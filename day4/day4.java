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
        int numXmasMatches=0;
        // Since the input is a bunch of lines of Strings with the same length, it can be thought of as a 2d array of characters
        // Here we iterate through each line and each character in line
        for(int i = 0; i<lines.size(); i++) {
            String line = lines.get(i);
            for(int j=0; j< line.length(); j++) {
                // I feel like there is a java shortcut for this, but IDK what it is
                // If any of these functions find an XMAS "centered" at the character in the line, increase counter
                
                if(horizontalMatch(i, j, 1, lines)) { 
                    numXmasMatches++;
                }
                if(horizontalMatch(i, j, -1, lines)) {
                    numXmasMatches++;
                }

                if(verticalMatch(i, j, 1, lines)) {
                    numXmasMatches++;
                }
                if(verticalMatch(i, j, -1, lines)) {
                    numXmasMatches++;
                }

                if(diagonalMatch(i, j, 1, 1, lines) ) {
                    numXmasMatches++;
                }

                if(diagonalMatch(i, j, -1, -1, lines)){
                    numXmasMatches++;
                }

                if(diagonalMatch(i, j, 1, -1, lines) ) {
                    numXmasMatches++;
                }
                
                if(diagonalMatch(i, j, -1, 1, lines)) {
                    numXmasMatches++;
                }

            }

            
            
        }
     
        System.out.println(numXmasMatches);

        int numX_Mas=0;
            for(int i=0; i<lines.size(); i++) {
                for(int j=0; j<lines.get(i).length(); j++) {
                    try {
                        // Very lazy code because I didn't want to think through making more functions or for loops
                        
                        // See if left-leaning diagonal centered at i is a "MAS" (either forwards or backwards)
                        Boolean d1=false;
                        String diagonal = "";
                        diagonal+=lines.get(i-1).charAt(j-1);
                        diagonal+=lines.get(i).charAt(j);
                        diagonal+=lines.get(i+1).charAt(j+1);
                        if (diagonal.equals("MAS") | diagonal.equals("SAM")) {
                            d1 = true;
                        }

                        // See if right-leaning diagonal centered at i is a "MAS" (either forwards or backwards)
                        Boolean d2=false;
                        diagonal="";
                        diagonal+=lines.get(i-1).charAt(j+1);
                        diagonal+=lines.get(i).charAt(j);
                        diagonal+=lines.get(i+1).charAt(j-1);
                        if (diagonal.equals("MAS") | diagonal.equals("SAM")) {
                            d2 = true;
                        }
                        // if both right and left leaning diagonals are "MAS", then this is a valid X-MAS, increase counter
                        if(d1&&d2) {
                            numX_Mas++;
                        }

                    /*  If any indexoutofbounds occurs, then the character is too close to the edge for a valid X-MAS so we move onto
                    another character in the line*/
                    } catch (IndexOutOfBoundsException e) {

                    }
                }
                    
                
            }
            System.out.println(numX_Mas);
    }

    // Here "direction" indicated whether we are moving right (direction=1), or left (direction=-1). It also serves as a useful multiplier
    public static boolean horizontalMatch(int vertIndex, int horIndex, int direction, ArrayList<String> lines) {
        try {
            String a = "";
            for(int i=0; i<4; i++) {
                a+=lines.get(vertIndex).charAt(horIndex+direction*i);
            }
            if (a.equals("XMAS")) return true;
        } catch (IndexOutOfBoundsException e) {
                
        }
        return false;
    }
    // Direction indicates whether we are moving upwards (-1) or downwards (1)
    public static boolean verticalMatch(int vertIndex, int horizontalIndex, int direction, ArrayList<String> lines) {
        try {
            String a = "";
            for(int i=0; i<4; i++) {
                a+=lines.get(vertIndex+direction*i).charAt(horizontalIndex);
            }
            if (a.equals("XMAS")) return true;
        } catch (IndexOutOfBoundsException e) {
                
        }
        return false;
    }
        
    // Two direction variables here, horizontal and vertical so we can check all four diagonals
    public static boolean diagonalMatch(int vertIndex, int horIndex, int vertDirection, int horDirection, ArrayList<String> lines) {
            try {
                String a = "";
                for(int i=0; i<4; i++) {
                    a+=lines.get(vertIndex+vertDirection*i).charAt(horIndex+horDirection*i);
                }
                if (a.equals("XMAS")) return true;
            } catch (IndexOutOfBoundsException e) {

            }
            return false;
        }

        
    }
