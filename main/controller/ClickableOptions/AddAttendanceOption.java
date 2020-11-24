package main.controller.ClickableOptions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.controller.ClickableItem;
import main.model.StateManager;

public class AddAttendanceOption extends ClickableItem{
	public AddAttendanceOption(StateManager state) {
		super(state, "Add attendance");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
		super.updateState("Add Attendance");
	}
}
