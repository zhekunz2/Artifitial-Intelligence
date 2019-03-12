
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
 
public class ZeroSum extends JFrame  {

  static JLayeredPane layeredPane;
  static JPanel chessBoard;
  static JLabel chessPiece;
  static int xAdjustment;
  static int yAdjustment;
  public static Board board = new Board();
  public static ImageIcon imageIcon_blue = new ImageIcon("chess_blue.png");// load the image to a imageIcon
  public static ImageIcon imageIcon = new ImageIcon("chess.png"); // load the image to a imageIcon
 
  public ZeroSum(Board board){

    Dimension boardSize = new Dimension(600, 600);
   
    //  Use a Layered Pane for this this application
    layeredPane = new JLayeredPane();
    getContentPane().add(layeredPane);
    layeredPane.setPreferredSize(boardSize);
    // layeredPane.addMouseListener(this);
    // layeredPane.addMouseMotionListener(this);

    //Add a chess board to the Layered Pane 
   
    chessBoard = new JPanel();
    layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
    chessBoard.setLayout( new GridLayout(8, 8) );
    chessBoard.setPreferredSize( boardSize );
    chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
   
    for (int i = 0; i < 64; i++) {
    JPanel square = new JPanel( new BorderLayout() );
    chessBoard.add( square );
   
    int row = (i / 8) % 2;
    if (row == 0)
    square.setBackground( i % 2 == 0 ? Color.black : Color.white );
    else
    square.setBackground( i % 2 == 0 ? Color.white : Color.black );
    }

    // red chessman
    
    Image image = imageIcon.getImage(); // transform it
    Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    imageIcon = new ImageIcon(newimg);  // transform it back

    // blue chessman
   
    Image image_blue = imageIcon_blue.getImage(); // transform it
    Image newimg_blue = image_blue.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
    imageIcon_blue = new ImageIcon(newimg_blue);  // transform it back

    // adding chessman to the board
    for(int i =  0; i< 16; i++){
      JLabel piece = new JLabel( imageIcon );
      JPanel panel = (JPanel)chessBoard.getComponent(i);
      panel.add(piece);
    }

    for(int i =  48; i< 64; i++){
      JLabel piece = new JLabel( imageIcon_blue );
      JPanel panel = (JPanel)chessBoard.getComponent(i);
      panel.add(piece);
    }
    
  }


  public static void selectChess(int x,int y){
    chessPiece = null;
    Component c =  chessBoard.findComponentAt(x, y);
   
    if (c instanceof JPanel) 
    return;
   
    Point parentLocation = c.getParent().getLocation();
    xAdjustment = parentLocation.x - x;
    yAdjustment = parentLocation.y - y;
    chessPiece = (JLabel)c;
    chessPiece.setLocation(x + xAdjustment, y + yAdjustment);
    chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
    layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
  }
  
  public static void moveChess(int new_x, int new_y){
     if(chessPiece == null) return;
   
    chessPiece.setVisible(false);
    Component c =  chessBoard.findComponentAt(new_x, new_y);
   
    if (c instanceof JLabel){
    Container parent = c.getParent();
    parent.remove(0);
    parent.add( chessPiece );
    }
    else {
    Container parent = (Container)c;
    parent.add( chessPiece );
    }
   
    chessPiece.setVisible(true);
  }

  public static void testprint(Board board){
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){

        Component currSquare =  chessBoard.findComponentAt((j*75), (i*75));
        
        // add blue chess
      
        if(board.board[j][i].value == Board.WHITE ){

          if(currSquare instanceof JLabel){
            JLabel piece = (JLabel) currSquare;
            piece.setIcon(imageIcon_blue);
            chessBoard.revalidate();
            chessBoard.repaint();
          }
          else{
            JLabel piece = new JLabel(imageIcon_blue);
            JPanel panel = (JPanel)chessBoard.getComponent(i*8 + j);
            panel.add(piece);
            chessBoard.revalidate();
            chessBoard.repaint();
          }
        }
        //add red chess
        if(board.board[j][i].value == Board.BLACK){

          if(currSquare instanceof JLabel){

            JLabel piece = (JLabel) currSquare;
            piece.setIcon(imageIcon);
            chessBoard.revalidate();
            chessBoard.repaint();

          }
          else{

            JLabel piece = new JLabel(imageIcon);
            JPanel panel = (JPanel)chessBoard.getComponent(i*8 + j);
            panel.add(piece);
            chessBoard.revalidate();
            chessBoard.repaint();

          }

        }

        if(board.board[j][i].value == '_' && currSquare instanceof JLabel){

            Container parent = currSquare.getParent();
            parent.remove(currSquare);
            chessBoard.revalidate();
            chessBoard.repaint();

        }
      }
    }
  }
 
 
  public static void main(String[] args) {

    Board a = new Board();
    Board b = new Board();
    // a.board[0][0].value = '_';
    // a.board[0][1].value = '_';
    JFrame frame = new ZeroSum(a);
    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
    frame.pack();
    frame.setResizable(true);
    frame.setLocationRelativeTo( null );
    frame.setVisible(true);




    Component currSquare =  chessBoard.findComponentAt(1 * 75, 1*75);

    // System.out.println(c instanceof JLabel);

          
    //       if(c instanceof JLabel){
    //         System.out.println("asdsa");
    //         Container parent = c.getParent();
    //         parent.remove(0);
            
    //       }
        


    // JLabel piece = new JLabel(imageIcon_blue);
    // JPanel panel = (JPanel)chessBoard.getComponent(5*8 + 5);

    // panel.add(piece);

    // Container parent = currSquare.getParent();
    // parent.remove(currSquare);

    // currSquare = chessBoard.findComponentAt(1 * 75, 2 * 75);
    // parent = currSquare.getParent();
    // parent.remove(currSquare);
    // testprint(a);
    // a.printBoard();

    // JLabel testlable = (JLabel)currSquare;
    // testlable.setIcon(imageIcon_blue);
    // testprint(a);
    int i = 0;
    while(!a.isGoal() && !b.isGoal()){
      if(i%2 == 0){
        b = MiniMax.getState(a);
        testprint(b);
        b.printBoard();
      }
      else{
        a = test.getState(b);
        testprint(a);
        a.printBoard();
      }
      i++;
    
    }
  
  }
}