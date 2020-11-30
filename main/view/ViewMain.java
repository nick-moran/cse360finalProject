/**
 * This is the ViewMain file. It contains the main method for the entire 
 * project. It renders an instance of StateManager, Roster, and PageManager.
 * It will the connect observers to observables and initialize the Frame.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */
package main.view;

import javax.swing.JFrame;

import main.model.Roster;
import main.model.StateManager;

/**
 * This is the ViewMain class. It extends Jframe which is used to display
 * the panels throughout the project. It also creates instances of 
 * StateManager, Roster, and PageManger and connects the Observers to the
 * observables. It then displays the frames.
 */
public class ViewMain extends JFrame{
	
	/**
	 * This is the Constructor of ViewMain. It creates an instance of 
	 * Statemanager which is used to manage the state of the application. 
	 * It then creates an instance of Roster which is linked to PageManager.
	 * It then creates an instance of MenuBar and adds it to the page.
	 */
	public ViewMain(){
		super("CSE360 Final Project");
		StateManager state = new StateManager();
		Roster dataReader = new Roster();
		PageManager pageToShow = new PageManager(dataReader);
		state.addObserver(pageToShow);
		state.addObserver(dataReader);
		
		
		MenuBar menu = new MenuBar(state);
		setJMenuBar(menu);
		add(pageToShow);
		

		setVisible(true);
	}
	
	/**
	 * This is the main method which initializes everything in the 
	 * application
	 * @param args 
	 */
	public static void main(String[] args) {
		ViewMain frame = new ViewMain();
		
		frame.setSize(1000,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
