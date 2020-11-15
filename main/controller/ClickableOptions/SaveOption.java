package main.controller.ClickableOptions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.controller.ClickableItem;
import main.model.StateManager;

public class SaveOption extends ClickableItem{
	public SaveOption(StateManager state) {
		super(state, "Plot Data");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
	}
}
