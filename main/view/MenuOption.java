package main.view;

import javax.swing.AbstractAction;
import javax.swing.JMenu;

public class MenuOption extends JMenu{
	public MenuOption(String name) {
		super(name);
	}
		
	public void addActions(AbstractAction ...actions) {
		for(AbstractAction action : actions) {
			add(action);
		}
	}
}
