package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.eclipse.jdt.annotation.NonNull;

import model.Game;
import model.Location;
import model.Player;

/**
 * A Square is a unit of the Board.
 * Can have image of default.png, cross.png, or circle.jpg
 * depending on the Square's Player mark
 */
public class Square extends JLabel {
    private int x, y; // Coordinates of square on board
    private List<SquareListener> listeners; // list of GUIControllers listening to the square. it's a list because it could be 0,1,2 GUIControllers

    /** Constructor: a blank GUIBoard square at (x,y) */
    public Square(int x, int y) {
        this.x= x;
        this.y= y;
        this.listeners = new ArrayList<SquareListener>();
        setIcon(new ImageIcon("images/default.png"));
    }
    
	public Location getSquareLocation() {
		return new Location(x, y);
	}
	
	public List<SquareListener> getListeners() {
    	return listeners;
    }
    
	public void addListener(@NonNull SquareListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * repaint the Square relative to new Game
	 * @param g
	 */
	public void remark(Game g) {
		Player mark = g.getBoard().get(x, y); 
        if (mark == null)
        	setIcon(new ImageIcon("images/default.png"));
        else if (mark == Player.X)
        	setIcon(new ImageIcon("images/cross.png"));
        else
        	setIcon(new ImageIcon("images/circle.jpg"));
	}
}