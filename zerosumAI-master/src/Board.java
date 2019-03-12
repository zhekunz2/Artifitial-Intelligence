import java.util.*;

class Board{

  public static final char WHITE = 'W';
  public static final char BLACK = 'B';
  public double value;
  public Node[][] board = new Node[8][8];

   // copy constructor
   public Board(Board other){
      for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){
          this.board[j][i] = new Node(other.board[j][i]);
        }
      }
      this.value = other.value;
   }
  
   // default constructor
   public Board(){
     for(int i = 0; i < 8; i++){
        for(int j = 0; j < 8; j++){
          if(i == 0 || i == 1){
            board[j][i] = new Node(j, i, BLACK);
          }
          else if(i == 6 || i == 7){
            board[j][i] = new Node(j, i, WHITE);
          }
          else{
            board[j][i] = new Node(j, i, '_');
          }
          }
        }
   }

   public void printBoard(){
       for (int i = 0; i < 8; i++) {
         for(int j = 0; j < 8; j++) {
            System.out.print(board[j][i].value + " ");
          }
         System.out.println();
         
     }
   }

   public Boolean isEqual(Board other){
     for(int i = 0; i < 8; i++){
       for(int j = 0; j < 8; j++){
         if(!this.board[j][i].equals(other.board[j][i])){
           return false;
         };
       }
     }
     return true;
   }

   //return the number of remaining chess of color val
   public int numRem(char val){
     if(val != 'W' && val != 'B'){
        return -1; //invalid
     }
     int count = 0;
     for(int i = 0; i < 8; i++){
       for(int j = 0; j < 8; j++){
         if(this.board[j][i].value == val){
           count++;
         }
       }
     }
     return count;
   }

   //return all of the next possible states of Board by moving each color of val
   public List<Board> getSuccessors(char val){
     List<Board> retval = new ArrayList<Board>();
     if(val != 'W' && val != 'B'){
        System.out.println("INVALID VALUE!");
        return retval; //invalid
     }
     if(val == 'B'){
       List<Node> blacks = this.getBlack();

         for(Node curr : blacks){
           int j = curr.x;
           int i = curr.y;
           //left down
           if(j > 0 && i < 7){
             Board ldState = new Board(this);
             if(ldState.board[j-1][i+1].value != 'B'){
             ldState.board[j-1][i+1].value = 'B';
             ldState.board[j][i].value = '_';

             if(!retval.contains(ldState))
               retval.add(ldState);
             }
           }
           //straight down
           if(i < 7){
             Board sdState = new Board(this);
             if(sdState.board[j][i+1].value != 'B' && sdState.board[j][i+1].value != 'W'){
             sdState.board[j][i+1].value = 'B';
             sdState.board[j][i].value = '_';

             if(!retval.contains(sdState))
               retval.add(sdState);
             }
           }
           //right down
           if(j < 7 && i < 7){
             Board rdState = new Board(this);
             if(rdState.board[j+1][i+1].value != 'B'){
             rdState.board[j+1][i+1].value = 'B';
             rdState.board[j][i].value = '_';

             if(!retval.contains(rdState))
               retval.add(rdState);
             }
             }
           }
        }

    if(val == 'W'){
      List<Node> whites = this.getWhite();

        for(Node curr : whites){
          int j = curr.x;
          int i = curr.y;
          //left up
          if(j > 0 && i > 0){
            Board luState = new Board(this);
            if(luState.board[j-1][i-1].value != 'W'){
            luState.board[j-1][i-1].value = 'W';
            luState.board[j][i].value = '_';
            if(!retval.contains(luState))
              retval.add(luState);
            }
          }
          //straight up
          if(i > 0){
            Board suState = new Board(this);
            if(suState.board[j][i-1].value != 'W' && suState.board[j][i-1].value != 'B'){
            suState.board[j][i-1].value = 'W';
            suState.board[j][i].value = '_';
            if(!retval.contains(suState))
              retval.add(suState);
            }
          }
          //right up
          if(j < 7 && i > 0){
            Board ruState = new Board(this);
            if(ruState.board[j+1][i-1].value != 'W'){
            ruState.board[j+1][i-1].value = 'W';
            ruState.board[j][i].value = '_';
            if(!retval.contains(ruState))
              retval.add(ruState);
            }
          }
    }
   }
   return retval;
  }

  //helper function to get a list of black nodes
  public List<Node> getBlack(){
    List<Node> retval = new ArrayList<Node>();
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(this.board[j][i].value == 'B'){
          retval.add(this.board[j][i]);
        }
      }
    }
    return retval;
  }

  //helper function to get a list of white nodes, similar to getBlack
  public List<Node> getWhite(){
    List<Node> retval = new ArrayList<Node>();
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(this.board[j][i].value == 'W'){
          retval.add(this.board[j][i]);
        }
      }
    }
    return retval;
  }

  public boolean isGoal(){
  
    for(int j = 0; j < 8; j++){
        if(board[j][0].value == WHITE){
          return true;
        }
        if(board[j][7].value == BLACK){
          return true;
        }
    }
    if(numRem(WHITE)==0 | numRem(BLACK) == 0){
      return true;
    }
    return false;

  }





 }
