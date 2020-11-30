package main.controller.ClickableOptions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.controller.ClickableItem;
import main.model.StateManager;

public class PlotDataOption extends ClickableItem{
	public PlotDataOption(StateManager state) {
		super(state, "Plot Data");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
		super.updateState("plot data");
	}
}
