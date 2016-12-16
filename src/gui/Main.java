/* NetId(s): gat58, mc2639. Time spent: 12 hours, 30 minutes. */

package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.*;

import model.Game;
import model.Player;

/**
 * Main application class for the GUI
 */
public class Main {
	
	private static boolean isSubmited = false; // was the button clicked, i.e. controllers submitted?
	private static Controller player_x_controller, player_o_controller; // game's player x and o
	
	/**
	 * return a controller depending on selected JComboBox input 
	 */
	public static Controller createController(String s, Player p, GUIBoard b){
		switch(s){
		case "Human":
		      return new GUIController(p, b); 
		case "DumbAI":
			 return new DumbAI(p);
		case "RandomAI":
			return new RandomAI(p);
		case "SmartAI":
			return new SmartAI(p);
		default:
			return null;
		}
	}
	
		
	/**
	 * Run a game using a java Swing GUI.
	 * Wait for the user to select player controllers from drop-downs,
	 * then let them compete! */
	public static void main(String[] args) {
		Game g = new Game();
		
		// set up JFrame
		JFrame f = new JFrame("5 In A Row");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout());

		// add GameStateLabel and GUIBoard
		GameStateLabel gsl = new GameStateLabel();
		GUIBoard guib = new GUIBoard(g);
		gsl.setVisible(false); // hide GameStateLabel until user chooses two controllers
		guib.setVisible(false); // hide the GUIBoard until user chooses two controllers
		f.add(gsl);
		f.add(guib);
		
		// set up controller drop-downs and controllers submit button
		String[] playerStrings = {"Human","DumbAI","RandomAI", "SmartAI"};
		JComboBox<String> player_x_comboBox = new JComboBox<>(playerStrings);
		JComboBox<String> player_o_comboBox = new JComboBox<>(playerStrings);
		JButton submitControllersButton = new JButton("Submit");
		submitControllersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player_x_controller = createController(player_x_comboBox.getSelectedItem().toString(), Player.X, guib);
				player_o_controller = createController(player_o_comboBox.getSelectedItem().toString(), Player.O, guib);
				isSubmited = true;
			}
		});
		
		// format GUI player selection
		Box selectPlayerXBox = new Box(BoxLayout.X_AXIS); // place Label adjacent to ComboBox 
		selectPlayerXBox.add(new JLabel("Player X"));
		selectPlayerXBox.add(player_x_comboBox);
		
		Box selectPlayerOBox = new Box(BoxLayout.X_AXIS);
		selectPlayerOBox.add(new JLabel("Player O"));
		selectPlayerOBox.add(player_o_comboBox);
		
		Box controllerSetupBox = new Box(BoxLayout.Y_AXIS); // place selectPlayerBoxes and submitControllersButton on top each other 
		controllerSetupBox.add(selectPlayerXBox);
		controllerSetupBox.add(selectPlayerOBox);
		controllerSetupBox.add(submitControllersButton);
		controllerSetupBox.setVisible(true);
		f.add(controllerSetupBox);
				
		f.pack();
		f.setVisible(true);
		
		// wait until button is clicked and controllers are submitted 
		while (!isSubmited) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		
		// set up gui game, show relevant components & hide irrelevant ones
		gsl.setVisible(true);
		guib.setVisible(true);
		controllerSetupBox.setVisible(false);
		f.setSize(360, 420); // resize to show new components
		
		// add game listeners and play
		g.addListener(gsl);
		g.addListener(guib);
		g.addListener(player_x_controller);
		g.addListener(player_o_controller);
	}
}
