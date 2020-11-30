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
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.model.LoadRoster;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class HomePage extends JPanel implements Observer{
	private JTable table;
	
	public HomePage() {
		table = new JTable(new DefaultTableModel(
				new String[] { "ID", "First Name", "Last Name", "Program", "Level", "ASURITE"},
				0));
  		JScrollPane scrollPane = new JScrollPane(table);
  		add(scrollPane);
	}
	
	@Override
	public void update(Observable updater, Object newState) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		
		if(newState.toString() == "Add Attendance") {
			String enterDate = JOptionPane.showInputDialog("Enter Date as 'Month day'");
			
			altertUserOfDates(((LoadRoster)updater).getUsersWithTimeAdded(),
					((LoadRoster)updater).getExtraUsers());
			
			model.addColumn(enterDate);
			((LoadRoster)updater).appendToDates(enterDate);
		}
		
		if(newState.toString() == "Load" || newState.toString() == "Add Attendance") {
	  		String[][] data = ((LoadRoster)updater).getPassedData();
	  		model.setRowCount(0);
	  		for(String[] row : data) {
	  			model.addRow(row);
	  		}
	  	
		}
		if(newState.toString() == "plot data") {
			String[][] data = ((LoadRoster)updater).getPassedData();
			XYDataset dataset = ((LoadRoster)updater).createPlotData(data);
			 
		    JFreeChart chart = ChartFactory.createScatterPlot(
		        "Count of Attendees Based on Attendance", 
		        "X-Axis", "Count", dataset);
		    
		    XYPlot plot = (XYPlot)chart.getPlot();
		    plot.setBackgroundPaint(new Color(255,228,196));
		    
		    ChartPanel panel = new ChartPanel(chart);
		    JOptionPane.showMessageDialog(null, panel);
		}
		repaint();
		revalidate();
	}
	
	public void altertUserOfDates(int usersWithTimeAdded, String[][] extraUsers) {
		String usersMessage = String.format("Data added for %d users in the roster\n\n", usersWithTimeAdded);
		
		if(extraUsers.length > 0) {
			usersMessage += String.format("%s additional attendee was found:\n", extraUsers.length);
			
			for(String[] userData : extraUsers) {
				usersMessage += String.format("%s was connected for %s minutes\n",userData[0], userData[1]);
			}
		}
		
		JOptionPane.showMessageDialog(null,usersMessage);
	}
	
	
}
