package main.view.Pages;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import main.model.LoadRoster;

public class HomePage extends JPanel implements Observer{
	private String[][] data;
	JTable table;
	static String[] columnNames = { "ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
	
	public HomePage() {
		
	}
	
	@Override
	public void update(Observable updater, Object data) {

		if(data.toString() == "Load") {
			this.removeAll();
	  		this.data = ((LoadRoster)updater).getPassedData();
	  		table = new JTable(this.data, this.columnNames); 
	  		JScrollPane scrollPane = new JScrollPane(table);
	  		this.add(scrollPane);
	  		this.repaint();
	  		revalidate();
		}
		
		if(data.toString() == "AddAttendance") {
			this.removeAll();
	  		this.data = ((LoadRoster)updater).getPassedData();
		}
	}
	
	
	
}
