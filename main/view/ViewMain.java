package main.view;

import javax.swing.JFrame;

import main.model.LoadRoster;
import main.model.StateManager;

public class ViewMain extends JFrame{
	
	public ViewMain(){
		super("CSE360 Final Project");
		StateManager state = new StateManager();
		LoadRoster dataReader = new LoadRoster();
		PageManager pageToShow = new PageManager(dataReader);
		state.addObserver(pageToShow);
		state.addObserver(dataReader);
		
		
		MenuBar menu = new MenuBar(state);
		setJMenuBar(menu);
		add(pageToShow);
		

		setVisible(true);
	}

	public static void main(String[] args) {
		ViewMain frame = new ViewMain();
		
		frame.setSize(1000,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
