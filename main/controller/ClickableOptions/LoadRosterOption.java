package main.controller.ClickableOptions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import main.controller.ClickableItem;
import main.model.StateManager;

public class LoadRosterOption extends ClickableItem{
	public LoadRosterOption(StateManager state) {
		super(state, "Load the Roster");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
		super.updateState("Load");
	}
}
