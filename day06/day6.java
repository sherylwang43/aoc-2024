import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;


public class day6 extends ParserSix {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Sheryl\\Downloads\\daySix";
        ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();
        ArrayList<ArrayList<Character>> matrix2 = new ArrayList<ArrayList<Character>>();
        int yBound=0;
        int xBound=0;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                ArrayList<Character> line = new ArrayList<Character>();
                for(int i =0; i<s.length(); i++) {
                    line.add(s.charAt(i));
                }
                matrix.add(line); 
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        yBound=matrix.size();
        xBound=matrix.get(0).size();

        for(ArrayList<Character> a: matrix) {
            ArrayList<Character> aa = new ArrayList<Character>();
            for(Character b: a) {
                aa.add(b);
            }
            matrix2.add(aa);
        }
        
     //  printMatrix(matrix2);
        Guard guard = new Guard(0,0,0,0,matrix);
       

        for(int i=0; i<matrix.size(); i++) {
            for(int j=0; j<matrix.get(0).size(); j++) {
                if(matrix.get(i).get(j).equals('^')) {
                    guard = new Guard(j,i,xBound,yBound,matrix);
                    matrix.get(i).set(j,'X');
                    i=matrix.size();
                    break;
                }
            }
        }
        while(guard.canMove()) {
            guard.move();
        }
        matrix.get(guard.y()).set(guard.x(),'X');
        int count=0;
       for(ArrayList<Character> a: matrix) {
        for(Character c: a) {
            if(c.equals('X')) {
                count++;
            }
        }
       }
       System.out.println(count);   
    Guard guard2=new Guard(1,1,1,1,null);
       for(int i=0; i<matrix2.size(); i++) {
        for(int j=0; j<matrix2.get(0).size(); j++) {
            if(matrix2.get(i).get(j).equals('^')) {
                guard2 = new Guard(j,i,xBound,yBound,matrix2);
                matrix2.get(i).set(j,'X');
                i=matrix2.size();
                break;
            }
        }
    }
   
    int initX=guard2.x();
    int initY=guard2.y();
    System.out.println(initY);
    ArrayList<ArrayList<String>> obstaclesHit = new ArrayList<ArrayList<String>>();
    ArrayList<int[]> coords = new ArrayList<int[]>(); 
    for(int i=0; i<matrix2.size();i++) {
        ArrayList<Character> row = matrix2.get(i);
        guard2.setX(initX);
        guard2.setY(initY);
        guard2.setMoving();
        matrix2.get(initY).set(initX,'^');
        for(int j=0; j<row.size(); j++) {
            obstaclesHit.clear();
            guard2.setX(initX);
            guard2.setY(initY);
            guard2.setDirection("up");
            guard2.setMoving();
            matrix2.get(initY).set(initX,'^');
            if(!(row.get(j).equals('#') | row.equals('^'))) {
            row.set(j,'O');   
            while(guard2.canMove()) {
                ArrayList<String> hit = firstObstacleHit(matrix2,guard2);
                if(hit==null) {
                    guard2.cantMove();
                }
                boolean duplicate = false;
                if(hit!=null) {
                for(ArrayList<String> obstacle: obstaclesHit) {
                    if(obstacle!=null) {
                    if((obstacle.get(0).equals(hit.get(0))) &&
                    (obstacle.get(1).equals(hit.get(1))) &&
                    (obstacle.get(2).equals(hit.get(2)))) {
                        duplicate=true;
                        int[] coord = {i,j};
                        coords.add(coord);
                        guard2.cantMove();
                        
                    }
                }
                }
                }
                if(!duplicate) {
                    obstaclesHit.add(hit);
                }
                }  
            }
            clear(matrix2);
        }  
    }
    System.out.println(coords.size());
    }
    
    
    public static void clear(ArrayList<ArrayList<Character>> a) {
        for(ArrayList<Character> b:a) {
            for(int i=0;i<b.size();i++) {
                if(b.get(i).equals('X') | b.get(i).equals('O') | b.get(i).equals('^')) {
                    b.set(i,'.');
                }
            }
        }
    }
    public static ArrayList<String> firstObstacleHit(ArrayList<ArrayList<Character>> matrix2, Guard guard2) {
        String currentDirection = guard2.direction;
      
        while(guard2.direction.equals(currentDirection)) {
            guard2.move();
            if(!guard2.canMove()) {
                return null;
            }
        }
        ArrayList<String> obstacle = new ArrayList<String>();
        switch(currentDirection) {
            case("up"):
                obstacle.add(""+guard2.x());
                obstacle.add(""+(guard2.y()-1));
                break;
            case("down"):
                obstacle.add(""+guard2.x());
                obstacle.add(""+(guard2.y()+1));
                break;
            case("right"):
                obstacle.add (""+(guard2.x()+1));
                obstacle.add(""+guard2.y());
                break;
            case("left"):
                obstacle.add(""+(guard2.x()-1));
                obstacle.add(""+guard2.y());
                break;
        }
        
       
        obstacle.add(currentDirection);
        return obstacle;
       
    }
    public static void printMatrix(ArrayList<ArrayList<Character>> matrix){
        for(ArrayList<Character> a: matrix) {
            System.out.println(a);
        }
    }

   
    }

