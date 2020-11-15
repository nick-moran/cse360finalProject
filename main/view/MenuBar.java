package main.view;


import javax.swing.JMenuBar;

import main.controller.*;
import main.model.StateManager;

public class MenuBar extends JMenuBar{
	public MenuBar(StateManager state) {		
		MenuOption fileMenu = new MenuOption("File");
		MenuOption aboutMenu = new MenuOption("About");
		
		fileMenu.addActions(new LoadRoster(state));
		aboutMenu.addActions(new AboutClicked(state));
		
		add(fileMenu);
		add(aboutMenu);
	}
}
