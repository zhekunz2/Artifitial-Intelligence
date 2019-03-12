import java.util.ArrayList;
import java.util.List;

class Node {

    public int x;//current position for x

    public int y;//current position for y

    public char value; //black 'B' or white 'W'

    public boolean hasChess;

    //copy constructor
    public Node(Node other) {
        this.x = other.x;
        this.y = other.y;
        this.value = other.value;
    }

    //constructor
    public Node(int x, int y, char value) {
        this.x = x;
        this.y = y;

        this.value = value;


    }
    //default constructor
    public Node() {
        this.x = x;
        this.y = y;

        this.value = value;
    }


    public boolean equals(Node other) {
        return ((this.x == other.x) && (this.y == other.y) && (this.value == other.value));
    }


}
