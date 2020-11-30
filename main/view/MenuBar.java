/**
 * Creates the menu bar in the left hand corner
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.view;

import javax.swing.JMenuBar;

import main.controller.ClickableOptions.*;
import main.model.StateManager;

/**
 * Extends JMenuBar, so it acts as the menu on the frame
 * Creates the "File" and "About" sections
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 */
public class MenuBar extends JMenuBar{
	/**
	 * Takes in state so that the sub options have access to 
	 * the state manager.
	 * 
	 * @param state
	 */
	public MenuBar(StateManager state) {
		//Creates the top menu options
		MenuOption fileMenu = new MenuOption("File");
		MenuOption aboutMenu = new MenuOption("About");
		
		//adds the sub options to the top menu
		fileMenu.addActions(new LoadRosterOption(state), new AddAttendanceOption(state),
				new SaveOption(state), new PlotDataOption(state));
		
		aboutMenu.addActions(new AboutOption(state));
		
		//adds to the menu bar
		add(fileMenu);
		add(aboutMenu);
	}
}
