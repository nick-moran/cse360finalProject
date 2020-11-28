package main.model;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.*;

public class LoadRoster extends Observable implements Observer{
	private ArrayList<ArrayList<String>> data;
	private boolean rosterLoaded;
	
	public LoadRoster() {
		this.data = new ArrayList<ArrayList<String>>();
		rosterLoaded = false;
	}
	
	private File findPath() {
		FileFilter filter = new FileNameExtensionFilter("csv file", new String[] {"csv"});
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile();
		
	}
	
	public void updateState(String newState){
		this.setChanged();
		this.notifyObservers(newState);
	}
	
	public String[][] getPassedData(){
		int rows = this.data.size();
		int rowLength = this.data.get(0).size();
		int index = 0;
		
		String[][] passedData = new String[rows][rowLength];
		for(ArrayList<String> row : data) {
			passedData[index++] = row.toArray(new String[row.size()]);
		}
		
		return passedData;
	}
	
	@Override
	public void update(Observable updater, Object newState){
		if(newState.toString() == "Load") {
			this.readCSVFile(this.findPath());
		}	
		else if(newState.toString() == "Add Attendance" && this.rosterLoaded) {
			this.readAttendanceData(this.findPath());
		}
		this.updateState(newState.toString());
	}
	
	public void readCSVFile(File filePath) {
		try {
			String fileLine;
			BufferedReader bReader = new BufferedReader(new FileReader(filePath));
			while ((fileLine = bReader.readLine()) != null) {
				this.data.add(new ArrayList<>(Arrays.asList(fileLine.split(","))));
			}
			bReader.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch(IOException e) {
			System.out.println("IOexception");
		}
		this.rosterLoaded = true;
	}
	
	public void readAttendanceData(File filePath) {
		HashMap<String, Integer> attendance = new HashMap<String, Integer>();
		try {
			String fileLine;
			BufferedReader bReader = new BufferedReader(new FileReader(filePath));
			while ((fileLine = bReader.readLine()) != null) {
				String[] elements = fileLine.split(",");
				String asurite = elements[0];
				int minutes = Integer.parseInt(elements[1]);
				if(attendance.containsKey(asurite)) {
					attendance.put(asurite, attendance.get(asurite) + minutes);
				} 
				else {
					attendance.put(asurite, minutes);
				}
			}
			bReader.close();
		}
		catch(IOException e) {
			System.out.println("IOException has occured");
		}
		this.mapToData(attendance);
		
	}
	
	public void mapToData(HashMap<String, Integer> attendance) {
		for(int i = 0; i < this.data.size(); i++) {
			if(attendance.containsKey(this.data.get(i).get(5))) {
				this.data.get(i).add(Integer.toString(attendance.get(this.data.get(i).get(5))));
			}
			else {
				this.data.get(i).add(" ");
			}
		}
	}
	
}