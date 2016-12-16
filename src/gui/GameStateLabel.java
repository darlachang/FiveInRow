package gui;

import javax.swing.JLabel;

import model.Game;
import model.GameListener;

/**
 * GUI JLabel indicates game state: win, draw, ongoing
 */
public class GameStateLabel extends JLabel implements GameListener {

	public GameStateLabel() {
		super();
	}
	
	@Override
	public void gameChanged(Game g) {
		switch(g.getBoard().getState()) {
		case HAS_WINNER:
			setText(g.getBoard().getWinner().winner + " wins!");
			break;
		case DRAW:
			setText("Game ended in a draw!");
			break;
		case NOT_OVER:
			setText("It is " + g.nextTurn() + "'s turn");
			break;
		}
	}

}
