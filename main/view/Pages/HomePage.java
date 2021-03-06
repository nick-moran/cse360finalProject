/**
 * This is the HomePage file. This renders the table and plots needed 
 * for the home page. It chooses what to render based on the state.
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */

package main.view.Pages;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.model.Roster;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This is the homepage class. It renders all the tables and plots 
 * needed for the home page based on what state it is in. 
 */

public class HomePage extends JPanel implements Observer{
	private JTable table;
	
	/**
	 * This is the constructor. It initializes a a table at a class
	 * level
	 * @return nothing
	 */
	public HomePage() {
		table = new JTable(new DefaultTableModel(
				new String[] { "ID", "First Name", "Last Name", 
						"Program", "Level", "ASURITE"},
				0));
  		JScrollPane scrollPane = new JScrollPane(table);
  		scrollPane.setPreferredSize(new Dimension(1000, 1000));
  		add(scrollPane);
	}
	
	/**
	 * This is the update method. It is observing the Load roster method.
	 * This manages what state the page is in and calls the corresponding 
	 * methods
	 * @param updater is the load roster object being used
	 * @param newState is the state the homepage should be in
	 * @return nothing
	 */
	@Override
	public void update(Observable updater, Object newState) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		
		if(newState.toString() == "Add Attendance") {
			String enterDate = JOptionPane.showInputDialog("Enter Date as 'MM/DD'");
			boolean invalidDate = false;
			if(enterDate.length() != 5) {
				invalidDate = true;
			}
			if(enterDate.charAt(2) != '/') {
				invalidDate = true;
			}
			try {
				if(Integer.parseInt(enterDate.substring(0,2)) > 12 ||
						Integer.parseInt(enterDate.substring(0,2)) <= 0) {
					invalidDate = true;
				}
				if(Integer.parseInt(enterDate.substring(3,5)) > 31 ||
						Integer.parseInt(enterDate.substring(3,5)) <= 0) {
					invalidDate = true;
				}
			}
			catch(NumberFormatException e) {
				invalidDate = true;
			}
			if(!invalidDate) {
				altertUserOfDates(((Roster)updater).getUsersWithTimeAdded(),
						((Roster)updater).getExtraUsers());
				
				model.addColumn(enterDate);
				((Roster)updater).appendToDates(enterDate);
			}
			else {
				String message = "This date is invalid. Please follow the pattern "
						+ "MM/DD";
				JOptionPane.showMessageDialog(null,message);
			}
			((Roster)updater).flushExtraUsers();
		}
		
		if(newState.toString() == "Load" || newState.toString() == "Add Attendance") {
	  		String[][] data = ((Roster)updater).getPassedData();
	  		model.setRowCount(0);
	  		for(String[] row : data) {
	  			model.addRow(row);
	  		}
	  	
		}
		if(newState.toString() == "plot data") {
			String[][] data = ((Roster)updater).getPassedData();
			XYDataset dataset = ((Roster)updater).createPlotData(data);
			 
		    JFreeChart chart = ChartFactory.createScatterPlot(
		        "Count of Attendees Based on Attendance", 
		        "% attended", "Count", dataset);
		    
		    XYPlot plot = (XYPlot)chart.getPlot();
		    plot.setBackgroundPaint(new Color(255,228,196));
		    
		    ChartPanel panel = new ChartPanel(chart);
		    JOptionPane.showMessageDialog(null, panel);
		}
		repaint();
		revalidate();
	}
	
	/**
	 * This prints out a message about the attendance file thats been input
	 * @param usersWithTimeAdded is the amount users added to the table
	 * @param extraUsers is used to store the users not added to the table
	 * @return nothing
	 */
	public void altertUserOfDates(int usersWithTimeAdded, String[][] extraUsers) {
		String usersMessage = String.format("Data added for %d users in the "
				+ "roster\n\n", usersWithTimeAdded);
		
		if(extraUsers.length > 0) {
			usersMessage += String.format("%s additional attendee was found:\n",
					extraUsers.length);
			
			for(String[] userData : extraUsers) {
				usersMessage += String.format("%s was connected for %s minutes\n",
						userData[0], userData[1]);
			}
		}
		
		JOptionPane.showMessageDialog(null,usersMessage);
	}
	
	
}
