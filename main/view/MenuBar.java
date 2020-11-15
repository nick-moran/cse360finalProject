package main.view;


import javax.swing.JMenuBar;

import main.controller.ClickableOptions.*;
import main.model.StateManager;

public class MenuBar extends JMenuBar{
	public MenuBar(StateManager state) {		
		MenuOption fileMenu = new MenuOption("File");
		MenuOption aboutMenu = new MenuOption("About");
		
		fileMenu.addActions(new LoadRosterOption(state), new AddAttendanceOption(state),
				new SaveOption(state), new PlotDataOption(state));
		
		aboutMenu.addActions(new AboutOption(state));
		
		add(fileMenu);
		add(aboutMenu);
	}
}
