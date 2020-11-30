/**
 * This is the LoadRoster file. It contains all the methods for rosterLoader.
 * This finds CSV file paths and manages the state of the homepage. It also
 * takes in the data for the table and manages the writing to CSV files
 * 
 * @author Shakib Ahmed, Nick Moran, Madeleine Householder
 * @version 1.0
 * @since 2020-11-29
 */

package main.model;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.*;
import java.util.*;

/**
 * This is the LoadRoster class. It has tableData which is a 2d array 
 * list that is used to read in CSV file data. It also has the bool
 * rosterLoaded which checks if a roster has been loaded to the table. 
 * Then there is headers which is all the headers for the table.
 * There is also usersWithTime which is an integer.
 */
public class Roster extends Observable implements Observer{
	private ArrayList<ArrayList<String>> tableData;
	private ArrayList<String[]> extraUsers;
	private ArrayList<String> headers;
	private int usersWithTime;
	private boolean rosterLoaded;
	
	/**
	 * this is the loadRoster constructor and it initializes all values
	 * in the class
	 * @return nothing
	 */
	public Roster() {
		this.tableData = new ArrayList<ArrayList<String>>();
		rosterLoaded = false;
		usersWithTime = 0;
		this.extraUsers = new ArrayList<String[]>();
		this.headers = new ArrayList<>(Arrays.asList("ID", "First Name",
				"Last Name", "Program", "Level", "ASURITE"));
	}
	
	/**
	 * This is updateState which takes in a newState and passes it on
	 * @param newState is a string which represents the state it is in
	 * @return nothing
	 */
	public void updateState(String newState){
		this.setChanged();
		this.notifyObservers(newState);
	}
	
	/**
	 * this is getPassedData which is a getter for the data as a 2d array
	 * @return data in form of a 2d array
	 */
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
	
	/**
	 * this is getExtraUsers which is a getter for extraUsers
	 * @return a 2d of extraUsers
	 */
	public String[][] getExtraUsers(){
		int numUsers = extraUsers == null ? 0 : extraUsers.size();
		String[][] extraUsersArr = new String[numUsers][2];
		int index = 0;
		for(String[] extraUser : extraUsers) {
			extraUsersArr[index++] = extraUser;
		}
		return extraUsersArr;
		
	}
	
	public void flushExtraUsers() {
		extraUsers = new ArrayList<String[]>();
	}
	
	/**
	 * This is getUsersWithTime which returns the amount of users with
	 * attendance
	 * @return an int that represents how many users were added
	 */
	public int getUsersWithTimeAdded() {
		return usersWithTime;
	}
	
	/**
	 * This is update which takes in a state and reacts accordingly.
	 * if it is load it reads the csv file. if it is findPath, it also reads
	 * a csv file. If it is Save, it writes to a CSV file.
	 * @return nothing
	 */
	@Override
	public void update(Observable updater, Object newState){
		if(newState.toString() == "Load") {
			this.readCSVFile(this.findPath());
		}	
		else if(newState.toString() == "Add Attendance" && this.rosterLoaded) {
			this.readAttendanceData(this.findPath());
		}
		else if(newState.toString() == "Save") {
			this.writeToCSV(this.findPath());
		}
		this.updateState(newState.toString());
	}
	
	/**
	 * AppendToDates takes in a date and appends it to the headers array
	 * @param date is the new date that is being appended
	 */
	public void appendToDates(String date) {
		this.headers.add(date);
	}
	
	/**
	 * This is the getter for the dates which is a subarray of headers
	 * @return an array of dates from header
	 */
	public String[] getDates(){
		List<String> subList = this.headers.subList(6, this.headers.size() + 1);
		return ((String[]) subList.toArray());
	}
	
	/**
	 * Create a XYDataSet based on the data read in from load and attendance
	 * @param data is the data from load and attendance
	 * @return the XYDataset that has been created
	 */
	public XYDataset createPlotData(String[][] data) {
		String date = headers.get(6);
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries dataSeries = new  XYSeries(date);
		
		
		for(int dateNum = 6; dateNum < data[0].length; dateNum++) {
			int[] dataArray = new int [11];
			
			for(int userNum = 0; userNum < data.length; userNum++) {
				int time = 0;
				time = Integer.parseInt(data[userNum][dateNum]);
				
				time = (int) ( (float) (time / 75.0) * 10);
				time = Math.min(time, 10);
				dataArray[time]++;

			}
			
			for(int iterator = 0; iterator < 11; iterator++) {
				dataSeries.add((double) iterator / 10, dataArray[iterator]);
			}
			
			dataset.addSeries(dataSeries);
			
			//check there is a next element
			if(dateNum + 1 < data[0].length)
			{
				//reset dataSeries after adding to dataset
				date = headers.get(dateNum+1);
				dataSeries = new XYSeries(date);
			}
		}
		
		return dataset;
	}
	
	/**
	 * This is writeToCSV which writes the data from loading and attendance
	 * to a csvFile given with headers added to it
	 * @param filePath is the destination of the CSV file
	 */
	private void writeToCSV(File filePath) {
		try {
			FileWriter csvWriter = new FileWriter(filePath);
			csvWriter.append(String.join(",", this.headers));
			csvWriter.append("\n");
			for(ArrayList<String> row : tableData) {
				csvWriter.append(String.join(",", row));
				csvWriter.append("\n");
			}
			csvWriter.close();
		}
		catch(IOException e) {
			System.out.println("IOException has occured");
		}
	}
	
	/**
	 * findPath returns a path from JFileChooser that the user selects
	 * @return the file selected by the user
	 */
	private File findPath() {
		FileFilter filter = new FileNameExtensionFilter("csv file",
				new String[] {"csv"});
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile();
		
	}
	
	/**
	 * This will read a csvFile and write it contents to tableData
	 * @param filePath is the path the readCSV file is on
	 */
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
	
	/**
	 * This method reads a csvFile from the path passed in. It stores them in a 
	 * hashMap. Then if there are duplicates, it will increment their values
	 * @param filePath is the path of the CSV file for attendance
	 */
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
	
	/**
	 * This method is called after the data of attendance is added to a
	 * hashmap. It will find corresponding ASURITES and assign their value
	 * @param attendance is the hashmap containing all the data
	 */
	private void mapToData(HashMap<String, Integer> attendance) {
		usersWithTime = 0;
		for(int i = 0; i < this.tableData.size(); i++) {
			if(attendance.containsKey(this.tableData.get(i).get(5))) {
				this.tableData.get(i).add(Integer.toString(attendance.get(this.tableData.get(i).get(5))));
				attendance.remove(this.tableData.get(i).get(5));
				usersWithTime++;
			}
			else {
				this.tableData.get(i).add("0");
			}
		}
		
		for(Map.Entry<String, Integer> extraUser: attendance.entrySet()) {
			extraUsers.add(new String[] {extraUser.getKey(),Integer.toString(extraUser.getValue())});
		}
	}

}