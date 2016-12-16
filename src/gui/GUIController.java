package gui;

import controller.Controller;
import model.Board;
import model.Game;
import model.Location;
import model.Player;

/**
 * A GUIController is a Controller whose next move is a clicked Square from the GUIBoard.
 * A GUIController listens to all Squares on the GUIBoard and is the only implementer of SquareListener.
 * When a Square is clicked, all the Square's listeners (aka all GUIControllers) call squareClicked()
 * which inputs the clickedSquare as the current player's next move. 
 */
public class GUIController extends Controller implements SquareListener {
	private Location clickedSquareLocation; // the location of the Square the user clicked 
	private boolean clicked = false; // dirty trick to make GUIController wait for user's Square click
	
	public GUIController(Player me, GUIBoard b) {
		super(me);
		
		// make this GUIController listen to all squares
		for (int i = 0; i < Board.NUM_ROWS; i++)
        	for (int j = 0; j < Board.NUM_COLS; j++)
        		b.getBoard()[i][j].addListener(this);
	}
	
	/**
	 * Uses a dirty programming work-around trick
	 * to have the game wait until a square is clicked.
	 */
	@Override
	protected Location nextMove(Game g) {
		while (!clicked)
			delay();	
		clicked = false;
		return clickedSquareLocation;
	}

	/**
	 * squareClicked() is called when a Square is clicked.
	 *  
	 */
	@Override
	public void squareClicked(Square s, Game g) {
		if (g.nextTurn() == this.me) { // if there are two Human players, make sure only the currentPlayer's square is marked
			clickedSquareLocation = s.getSquareLocation();			
			clicked = true;
		}
	}	
}