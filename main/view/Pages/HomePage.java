package main.view.Pages;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.model.LoadRoster;

public class HomePage extends JPanel implements Observer{
	private JTable table;
	private ArrayList<String> columnNames = new ArrayList<>(Arrays.asList( "ID", "First Name", "Last Name", "Program", "Level", "ASURITE"));
	
	public HomePage() {
		table = new JTable(new DefaultTableModel(columnNames.toArray(),0));
  		JScrollPane scrollPane = new JScrollPane(table);
  		add(scrollPane);
	}
	
	@Override
	public void update(Observable updater, Object newState) {
		
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		if(newState.toString() == "Add Attendance") {
			model.addColumn("Nov 27");
		}
		
		if(newState.toString() == "Load" || newState.toString() == "Add Attendance") {
	  		String[][] data = ((LoadRoster)updater).getPassedData();
	  		model.setRowCount(0);
	  		for(String[] row : data) {
	  			model.addRow(row);
	  		}
		}
		repaint();
		revalidate();
	}
	
	
	
}
