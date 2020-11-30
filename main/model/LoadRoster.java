package main.model;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


import java.io.*;
import java.util.*;

public class LoadRoster extends Observable implements Observer{
	private ArrayList<ArrayList<String>> tableData;
	private ArrayList<String[]> extraUsers;
	private ArrayList<String> headers;
	private int usersWithTime;
	
	private boolean rosterLoaded;
	
	public LoadRoster() {
		this.tableData = new ArrayList<ArrayList<String>>();
		rosterLoaded = false;
		usersWithTime = 0;
		this.extraUsers = new ArrayList<String[]>();
		this.headers = new ArrayList<>(Arrays.asList("ID", "First Name", "Last Name", "Program", "Level", "ASURITE"));
	}
	
	public void updateState(String newState){
		this.setChanged();
		this.notifyObservers(newState);
	}
	
	public String[][] getPassedData(){
		int rows = this.tableData.size();
		int rowLength = this.tableData.get(0).size();
		int index = 0;
		
		String[][] passedData = new String[rows][rowLength];
		for(ArrayList<String> row : tableData) {
			passedData[index++] = row.toArray(new String[row.size()]);
		}
		
		return passedData;
	}
	
	public String[][] getExtraUsers(){
		int numUsers = extraUsers == null ? 0 : extraUsers.size();
		String[][] extraUsersArr = new String[numUsers][2];
		int index = 0;
		for(String[] extraUser : extraUsers) {
			extraUsersArr[index++] = extraUser;
		}
		return extraUsersArr;
	}
	
	public int getUsersWithTimeAdded() {
		return usersWithTime;
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
	
	public void appendToDates(String date) {
		this.headers.add(date);
	}
	
	public String[] getDates(){
		List<String> subList = this.headers.subList(6, this.headers.size() + 1);
		return ((String[]) subList.toArray());
	}
	
	// WIll be completed shortly
//	private void writeToCSV(File filePath) {
//		try {
//			
//		}
//	}
	
	private File findPath() {
		FileFilter filter = new FileNameExtensionFilter("csv file", new String[] {"csv"});
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile();
		
	}
	
	private void readCSVFile(File filePath) {
		try {
			String fileLine;
			BufferedReader bReader = new BufferedReader(new FileReader(filePath));
			while ((fileLine = bReader.readLine()) != null) {
				this.tableData.add(new ArrayList<>(Arrays.asList(fileLine.split(","))));
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
	
	private void readAttendanceData(File filePath) {
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
	
	private void mapToData(HashMap<String, Integer> attendance) {
		usersWithTime = 0;
		for(int i = 0; i < this.tableData.size(); i++) {
			if(attendance.containsKey(this.tableData.get(i).get(5))) {
				this.tableData.get(i).add(Integer.toString(attendance.get(this.tableData.get(i).get(5))));
				attendance.remove(this.tableData.get(i).get(5));
				usersWithTime++;
			}
			else {
				this.tableData.get(i).add(" ");
			}
		}
		
		for(Map.Entry<String, Integer> extraUser: attendance.entrySet()) {
			extraUsers.add(new String[] {extraUser.getKey(),Integer.toString(extraUser.getValue())});
		}
	}
}