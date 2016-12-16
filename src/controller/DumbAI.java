package controller;

import model.*;

/**
 * A DumbAI is a Controller that always chooses the blank space with the
 * smallest column number from the row with the smallest row number.
 */
public class DumbAI extends Controller {

	public DumbAI(Player me) {
		super(me);
	}

	protected @Override Location nextMove(Game g) {
		// Note: Calling delay here will make the CLUI work a little more
		// nicely when competing different AIs against each other.
		
		delay();
		
		for (int r = 0; r < Board.NUM_ROWS; r++)
			for (int c = 0; c < Board.NUM_COLS; c++)
				if (g.getBoard().get(r, c) == null)
					return new Location(r, c);
		
		return null; // the board is full
	}
}
