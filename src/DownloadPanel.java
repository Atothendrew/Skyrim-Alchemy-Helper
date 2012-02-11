
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.JTabbedPane;

public class DownloadPanel extends JApplet
{
	private static final long serialVersionUID = 2199947785476613501L;
	private ArrayList<Potion> potionList; // the ArrayList that stores the unsorted list from the .txt file (sorted by value)
	private ArrayList<Potion> sortedList; // the ArrayList that stores the sorted potionList
	private Parse parser; // creates an instance of the parser class
	private JButton downloadButton; // the button that the user clicks to download and parse the list
	private JPanel topPanel; // the top level JPanel
	private JComboBox sortPicker; // the combo box that allows the user to choose how the data will be sorted
	private String sortChoices[]; // the string that contains the sorting options
	private JLabel sortLabel; // the label that makes it obvious what the sortPicker does for the user
	private JLabel downloadingLabel; // the label that makes it obvious what the downloadButton button does
	private JProgressBar progressBar = new JProgressBar(0, 100); // the progress bar that shows the progress of the download
	private ViewPanel viewPanel; // an instance of the viewPanel class
	private JTabbedPane tab; // the controller of the tabPane
	private Sorter sort = new Sorter(); // an instance of the sorter class
	private boolean alreadyDownloaded = false; // a boolean that is changed when the list has been downloaded and parsed
	private JTable table; // **Not used in this class, just created in this class for use in the Parse class**
	private DownloadPanel thisClass; // an instance of this class, sends it to the Parse class	

	 public void init()
	  {
		 setSize (1010, 700); // controls the size of the applet
	  }

	public DownloadPanel() // constructor
	{
		thisClass = this;

		ButtonListener buttonListener = new ButtonListener();	
		ComboListener comboListener = new ComboListener();

		progressBar.setVisible(false);

		sortChoices = new String[]{"Value", "Name", "Effects"};
		sortLabel = new JLabel("Sort by:");
		downloadingLabel = new JLabel("Not started yet");
		JLabel welcomeLabel = new JLabel("<html>Welcome to the Skyrim Alchemy Helper! To get started, " +
				"Choose how you want the results to be sorted using the selection box below and then click on " +
				"'Download List' below!</html>");
		welcomeLabel.setPreferredSize(new Dimension(1, 1)); 

		downloadButton = new JButton("Download list");

		sortPicker = new JComboBox(sortChoices);
		downloadButton.addActionListener(buttonListener);

		sortPicker.addActionListener(comboListener);
		JPanel sortPanel = new JPanel(new GridLayout(0,1));
		sortPanel.add(sortLabel);
		sortPanel.add(sortPicker);

		topPanel = new JPanel(new GridLayout(5,0));
		topPanel.add(welcomeLabel);
		topPanel.add(sortPanel);
		topPanel.add(progressBar);
		topPanel.add(downloadingLabel);
		topPanel.add(downloadButton);

		tab = new JTabbedPane();
		add(tab, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		JButton button = new JButton("Add Tab");
		panel.add(button);
		tab.add("Download and Sort", topPanel);

	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{

			if (event.getSource() == downloadButton){

				downloadingLabel.setText("Downloading...");
				progressBar.setVisible(true);

				try {
					parser = new Parse(downloadingLabel, progressBar, table, thisClass, sortPicker);
					parser.addPropertyChangeListener(new PropertyChangeListener() { 
						public void propertyChange(PropertyChangeEvent evt) { 
							if ("progress".equals(evt.getPropertyName())) { 
								progressBar.setValue((Integer) evt.getNewValue()); 
							} 
						} 
					}); 
					parser.execute();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	} 

	public void downloadDone(ArrayList<Potion> array){

		potionList = array;

		if (parser.isDone()){
			viewPanel = new ViewPanel(potionList, sortPicker);
			tab.add("View Results", viewPanel);

			downloadButton.setText("List already downloaded! (Sort the list on the 'View Results' panel)");
			downloadButton.setEnabled(false);

			setAlreadyDownloaded(true);
			sortLabel.setVisible(false);
		}
	}
	
	public void downloadDone(ArrayList<Potion> sortedList, ArrayList<Potion> potionList){
		this.sortedList = sortedList;
		this.potionList = potionList;

		if (parser.isDone()){
			viewPanel = new ViewPanel(potionList, sortPicker);
			tab.add("View Results", viewPanel);

			downloadButton.setText("List already downloaded! (Sort the list on the 'View Results' panel)");
			downloadButton.setEnabled(false);

			setAlreadyDownloaded(true);
			sortLabel.setVisible(false);

		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			downloadingLabel.setText(String.format(
					"Completed %d%% of task.\n", parser.getProgress()));
		} 
	}


	public void setAlreadyDownloaded(boolean alreadyDownloaded) {
		this.alreadyDownloaded = alreadyDownloaded;
	}

	public boolean getAlreadyDownloaded() {
		return alreadyDownloaded;
	}

	private class ComboListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{

			if (event.getSource() == sortPicker)
			{
				if(sortPicker.getSelectedIndex() == 0){
					if (getAlreadyDownloaded()){

						viewPanel.setTableDataAnotherClass(potionList);

					}
				}
				else if(sortPicker.getSelectedIndex() == 1){

					if (getAlreadyDownloaded()){

						sortedList = sort.sortByName(potionList);

						viewPanel.setTableDataAnotherClass(sortedList);
					}

				}
				else if(sortPicker.getSelectedIndex() == 2){
					if (getAlreadyDownloaded()){

						sortedList = sort.sortByEffect(potionList);

						viewPanel.setTableDataAnotherClass(sortedList);
					}
				}

			}
		}
	} 

}
