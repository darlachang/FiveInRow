package gui;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import model.Board;
import model.Game;
import model.GameListener;

/**
 * GUI board for GUI Main
 * is a 2D array of Squares
 */
public class GUIBoard extends JPanel implements GameListener {
	private Square[][] board = new Square[Board.NUM_ROWS][Board.NUM_COLS];
	private MouseClick squareClick = new MouseClick();
    private Game g;
    
    public GUIBoard(Game g) {
        super();
        this.g = g;
        for (int row = 0; row < Board.NUM_ROWS; row++) {
        	for (int col = 0; col < Board.NUM_COLS; col++) {
        		board[row][col]= new Square(row,col); // populate board with Squares
        		add(board[row][col]); // add Square to GUIBoard JPanel 
                board[row][col].addMouseListener(squareClick); // make all Squares click-able
        	}
        }
        setLayout(new GridLayout(Board.NUM_ROWS, Board.NUM_COLS)); // GridLayout is perfect for 2D board
    }
    
    // GUIController needs access to board so when a GUIController is constructed, it listens to all GUIBoard Squares
    public Square[][] getBoard() {
    	return board;
    }
    
    /**
     * MouseEvent that calls squareClicked()
     */
    class MouseClick extends MouseInputAdapter {
    	public void mouseClicked(MouseEvent e) {
    		squareClicked((Square) e.getSource(), g);
    	}
    }
    
    /**
     * when Square is clicked, update all squareListeners aka Human controllers
     * Note: but only have it work for the current turn player's Square  
     */
    private void squareClicked(Square s, Game g) {
    	for (SquareListener listener : s.getListeners())
    		listener.squareClicked(s, g);
    }
    
    /**
     * repaint all squares in GUI
     * every time the game is changed
     * i.e. a controller submits a move
     */
    @Override
	public void gameChanged(Game g) {
    	for (int row = 0; row < Board.NUM_ROWS; row++)
        	for (int col = 0; col < Board.NUM_COLS; col++)
        		board[row][col].remark(g);
	}
}