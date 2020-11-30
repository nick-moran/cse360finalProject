/**
 * The parent class for the Menu bar titles.
 * Creates a menu bar entry and includes
 * possible sub options.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.view;

import javax.swing.AbstractAction;
import javax.swing.JMenu;

/**
 * Extends JMenu, so they are menu title options.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 */
public class MenuOption extends JMenu{
	/**
	 * Creates the MenuOption and gives it a name
	 * @param name
	 */
	public MenuOption(String name) {
		super(name);
	}
	
	/**
	 * Adds sub menu actions to the title
	 * @param actions
	 */
	public void addActions(AbstractAction ...actions) {
		for(AbstractAction action : actions) {
			add(action);
		}
	}
}
