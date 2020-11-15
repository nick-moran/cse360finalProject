package main.controller.ClickableOptions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.controller.ClickableItem;
import main.model.StateManager;

public class AboutOption extends ClickableItem{
	public AboutOption(StateManager state) {
		super(state, "About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("about");
	}
}
