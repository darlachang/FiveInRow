package gui;

import model.Game;

/**
 * A SquareListener is a GUIController that responds when a square is clicked
 * @see Square 
 * @see Square#addListener
 * @see GUIController 
 */
public interface SquareListener {
	void squareClicked(Square s, Game g);
}