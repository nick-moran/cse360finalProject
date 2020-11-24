package main.model;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.*;

public class LoadRoster extends Observable implements Observer{
	private File pathToCSV;
	private ArrayList<ArrayList<String>> data;
	private String[][] passedData;
	private File pathToAttendance;
	private boolean rosterLoaded;
	
	public LoadRoster() {
		this.pathToCSV = null;
		this.data = new ArrayList<ArrayList<String>>();
		rosterLoaded = false;
	}
	
	public void findPath() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		this.pathToCSV =  chooser.getSelectedFile();
		
	}
	
	public File getPath() {
		return this.pathToCSV;
	}
	
	public void readCSVFile() {
		try {
			String fileLine;
			BufferedReader bReader = new BufferedReader(new FileReader(this.pathToCSV));
			while ((fileLine = bReader.readLine()) != null) {
				ArrayList<String> tempArray = new ArrayList<String>(); 
				String[] elements = fileLine.split(",");
				for(int i = 0; i < elements.length; i++) {
					tempArray.add(elements[i]);
				}
				this.data.add(tempArray);
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
	
	
	public String[][] getPassedData(){
		return this.passedData;
	}
	
	@Override
	public void update(Observable updater, Object newState){
		if(newState.toString() == "Load") {
			this.findPath();
			this.readCSVFile();
			this.convertToArrays(6);
			this.updateState(newState.toString());
		}	
		if(newState.toString() == "Add Attendance") {
			if(this.rosterLoaded) {
				this.findAttendancePath();
				this.readAttendanceData();
				this.convertToArrays(this.passedData.length + 1);
				this.updateState(newState.toString());
			}
			else {
				System.out.println("LOL NO");
			}
		}
	}
	
	public void updateState(String newState){
		this.setChanged();
		this.notifyObservers(newState);
	}
	
	public void findAttendancePath() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		this.pathToAttendance =  chooser.getSelectedFile();
		System.out.println(this.pathToAttendance.toString());
	}
	
	public void convertToArrays(int rowLength) {
		int rows = this.data.size();
		this.passedData = new String[rows][rowLength];
		for(int iterator = 0; iterator < rows; iterator++) {
			ArrayList<String> tempList = (ArrayList<String>) this.data.get(iterator);
			String[] addingToPassedData = Arrays.copyOf(tempList.toArray(), tempList.toArray().length, String[].class);
			this.passedData[iterator] = addingToPassedData;
		}
	}
	
	public void readAttendanceData() {
		HashMap<String, Integer> attendance = new HashMap<String, Integer>();
		try {
			String fileLine;
			BufferedReader bReader = new BufferedReader(new FileReader(this.pathToAttendance));
			while ((fileLine = bReader.readLine()) != null) {
				ArrayList<String> tempArray = new ArrayList<String>(); 
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