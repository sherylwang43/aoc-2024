import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Guard extends ParserSix {
    public int x;
    public  int y;
    public String[] directions = {"up", "right", "down", "left"};
    public int dirIndex=0;
    public String direction = directions[dirIndex];
    public ArrayList<ArrayList<Character>> matrix;
    public int yBound;
    public int xBound;

    public boolean movable;

    public Guard(int x, int y, int xBound, int yBound, ArrayList<ArrayList<Character>> matrix) {
        this.x=x;
        this.y=y;
        this.direction="up";
        this.xBound=xBound;
        this.yBound=yBound;
        this.matrix=matrix;
        this.movable=true;
    }
    public int x() { 
        return x;
    }
    public int y(){
        return y;
    }
    
    public void setX(int x) {
        this.x=x;
    }
    public void setY(int y) {
        this.y=y;
    }
    public void setDirection(String y) {
        this.direction=y;
        this.dirIndex=0;
    }
    
    public void setMoving() {
        movable=true;
        canMove();
    }
    
    public boolean move() {
        char potentialNext=',';
        int xNext=x;
        int yNext=y;
        switch(direction) {
            case ("up"):
                if(y==0) return false;
                potentialNext = matrix.get(y-1).get(x);
                yNext=y-1;
                //System.out.println(yNext);
                break;
            case("down"):
                if(y==yBound-1) return false;
                potentialNext = matrix.get(y+1).get(x);
                yNext=y+1;
                break;
            case("left"):
                if(x==0) return false;
                potentialNext = matrix.get(y).get(x-1);
                xNext=x-1;
                break;
            case("right"):
                if(x==xBound-1) return false;
                potentialNext=matrix.get(y).get(x+1);
                xNext=x+1;
                break;
        }
        
        if((potentialNext!='#')&& (potentialNext!='O')) {
            matrix.get(yNext).set(xNext,'^');
            matrix.get(y).set(x,'X');
            x=xNext;
            y=yNext;
            return true;
        } else {
            changeDirections();
            return false;
        }
    }

    public void changeDirections() {
        if(dirIndex==3) {
            dirIndex=0;
            direction=directions[dirIndex];
        } else {
            dirIndex++;
            direction=directions[dirIndex];
        }
    }
    public boolean canMove() {
        if(!movable) return false;

        if(x==xBound-1&&direction.equals("right")) return false;
        if(x==0&&direction.equals("left")) return false;
        if(y==yBound-1&&direction.equals("down")) return false;
        if(y==0&&direction.equals("up")) return false;
        return true;
    }
    public void cantMove() {
        movable=false;
    }
}
