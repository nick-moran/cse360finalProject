/**
 * The 'Plot data' menu option
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.controller.ClickableOptions;

import java.awt.event.ActionEvent;

import main.controller.ClickableItem;
import main.model.StateManager;

/**
* PlotDataOption extends ClickableItem, so it is a 
* listener. it updates the state to move to
* the home page and run the the plot data option.
* 
* @author Shakib Ahmed, Nick Moran, Madeleine Householder
*/
public class PlotDataOption extends ClickableItem{
	/**
	 * Makes the name of the option Plot Data. 
	 * and maps the state object to the parent.
	 * @param state
	 */
	public PlotDataOption(StateManager state) {
		super(state, "Plot Data");
	}
	
	/**
	 * a realization of the parent actionPerformed method
	 * updates the state to change the page and then
	 * enter the plot data section.
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
	}
}
