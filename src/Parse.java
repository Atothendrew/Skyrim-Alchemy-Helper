
import java.awt.Toolkit;
import java.io.*;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;

public class Parse extends SwingWorker<List<Integer>, Integer>{

	private String allPotions;
	private ArrayList<Potion> potionList = new ArrayList<Potion>();
	private DownloadPanel structureObject;
	private JLabel downloadingLabel;
	private JProgressBar progressBar = new JProgressBar(0, 100);
	public int howManyDone = 0;
	private JComboBox comboBox;
	private Sorter sort = new Sorter();
	private ArrayList<Potion> sortedList = new ArrayList<Potion>();


	public Parse(JLabel downloadingLabel, JProgressBar progressBar, JTable table, DownloadPanel panel, JComboBox combo) throws Exception {

		this.downloadingLabel = downloadingLabel;
		this.progressBar = progressBar;
		this.structureObject = panel;
		this.comboBox = combo;

	}

	public void downloadRecipies() throws Exception{
		//		URL alchemyListSite = new URL("http://glitchylife.com/alch_values.txt");
		//		BufferedReader in = new BufferedReader(new InputStreamReader(alchemyListSite.openStream()));

		//		while ((inputLine = in.readLine()) != null) {
		//
		//					//				  if (debug){
		////					System.out.println(inputLine);
		//					//				  }
		//
		//					allPotions = allPotions + inputLine + "\n";
		//					Potion temp = parsePotion(inputLine);
		//					potionList.add(temp);
		//					count++;
		//				}


		this.getClass().getResourceAsStream("values.txt");
		
		InputStream in = null;

		String inputLine;
		int count = 0;

		in = this.getClass().getResourceAsStream("values.txt");

		Scanner scan = new Scanner(in);
		
		for (int x = 0; x < 2604; x++){
				inputLine = scan.nextLine();
				allPotions = allPotions + inputLine + "\n";
				Potion temp = parsePotion(inputLine);
				potionList.add(temp);
				count++;
		}

		if(comboBox.getSelectedIndex() == 1){
			sortedList = sort.sortByName(potionList);
		}
		else if(comboBox.getSelectedIndex() == 2){
			sortedList = sort.sortByEffect(potionList);
		}

		in.close();

	}


	private Potion parsePotion(String line){

		Potion potion;

		ArrayList<Character> listOfChars = new ArrayList<Character>();

		int count = 0;

		ArrayList<Effect> effectList = new ArrayList<Effect>();
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

		Double value = null;

		for (int x = 0; x <= line.length(); x++){

			if (line.charAt(x) == '+') {

				if (listOfChars.get(0) == ' '){
					listOfChars.remove(0);
				}
				if (listOfChars.get(listOfChars.size()-1) == ' '){
					listOfChars.remove(listOfChars.size()-1);
				}

				String tempString = String.valueOf(listOfChars);

				tempString = removeChar(tempString, '[');
				tempString = removeChar(tempString, ']');
				tempString = removeChar(tempString, ',');

				ingredientList.add(new Ingredient(tempString));

				count = 0;
				listOfChars.clear();

			}
			else if (line.charAt(x) == '=') {

				if (listOfChars.get(0) == ' '){
					listOfChars.remove(0);
				}
				if (listOfChars.get(listOfChars.size()-1) == ' '){
					listOfChars.remove(listOfChars.size()-1);
				}

				String tempString = String.valueOf(listOfChars);

				tempString = removeChar(tempString, '[');
				tempString = removeChar(tempString, ']');
				tempString = removeChar(tempString, ',');

				ingredientList.add(new Ingredient(tempString));

				count = 0;
				listOfChars.clear();
			}
			else if (line.charAt(x) == '|'){
				String tempString = String.valueOf(listOfChars);
				double worth;
				String name;

				count = 0;

				ArrayList<Character> nameList = new ArrayList<Character>();

				for (int i = 0; i <= tempString.length(); i++){

					char numberVal[] = new char[5];

					if (listOfChars.get(i) == '('){

						if (nameList.get(0) == ' '){
							nameList.remove(0);
						}
						if (nameList.get(nameList.size()-1) == ' '){
							nameList.remove(nameList.size()-1);
						}


						String nameListString = String.valueOf(nameList);

						nameListString = removeChar(nameListString, '[');
						nameListString = removeChar(nameListString, ']');
						nameListString = removeChar(nameListString, ',');

						name = nameListString;

						try{
							if (isInteger(listOfChars.get(i+1)) || listOfChars.get(i+1) == '.') {
								numberVal[0] = (listOfChars.get(i+1));
							}
							else{
								worth = 0;
							}
							if (isInteger(listOfChars.get(i+2)) || listOfChars.get(i+2) == '.') {
								numberVal[1] = (listOfChars.get(i+2));
							}
							if (isInteger(listOfChars.get(i+3)) || listOfChars.get(i+3) == '.') {
								numberVal[2] = (listOfChars.get(i+3));
							}
							if (isInteger(listOfChars.get(i+4)) || listOfChars.get(i+4) == '.') {
								numberVal[3] = (listOfChars.get(i+4));
							}
							if (isInteger(listOfChars.get(i+5)) || listOfChars.get(i+5) == '.') {
								numberVal[4] = (listOfChars.get(i+5));
							}

							String valueString = String.valueOf(numberVal);

							try{
								worth = Double.parseDouble(valueString);
							}
							catch(NumberFormatException e){
								worth = Integer.parseInt(valueString);
							}
						}
						catch(IndexOutOfBoundsException e){
							worth = 0;
						}

						count = 0;
						listOfChars.clear();

						effectList.add(new Effect(name, worth));

						break;
					}
					else {
						nameList.add(count, listOfChars.get(i));
						count++;
					}

				}

			}
			else if(line.charAt(x) == ':' && line.charAt(x+1) == ' '){

				ArrayList<Character> eachValue = new ArrayList<Character>();

				for (int y = x; y < line.length(); y++){
					if (isInteger(line.charAt(y)) || line.charAt(y) == '.') {
						eachValue.add(line.charAt(y));
					}
					count++;
				}

				char temp[] = new char[eachValue.size()];

				for (int y = 0; y < temp.length; y++){
					temp[y] = eachValue.get(y);
				}

				String valueOfWorth = String.valueOf(temp);

				try{
					value = Double.parseDouble(valueOfWorth);
				}
				catch(NumberFormatException e){
					value = (double) Integer.parseInt(valueOfWorth);
				}

				break;
			}
			else {
				listOfChars.add(count, line.charAt(x));
				count++;
			}


		}

		potion = new Potion(ingredientList, effectList, value);

		howManyDone++;

		int prog = (int)Math.floor((howManyDone*100)/2603);
		downloadingLabel.setText("Downloading " + howManyDone + " of " + 2603);

		setProgress(prog);

		return potion;
	}

	public ArrayList<Potion> returnArray(){
		return potionList;
	}

	public boolean isInteger( char c )
	{  
		try  
		{  
			String temp = String.valueOf(c);
			Integer.parseInt( temp );  
			return true;  
		}  
		catch(Exception e)  
		{  
			return false;  
		}  
	}  

	public static String removeChar(String s, char c) {

		String r = "", t = "";

		for (int i = 0; i < s.length(); i ++) {
			if (s.charAt(i) != c){
				r += s.charAt(i);
			}
		}

		if (c == ','){

			String temp = removeChar(r, ' ');

			for (int x = 0; x < temp.length(); x++){
				try{
					char tempChar = temp.charAt(x+1);

					if (!Character.isUpperCase(tempChar)){
						t += temp.charAt(x);
					}
					else{
						t += temp.charAt(x) + " ";
					}
				}
				catch(IndexOutOfBoundsException e){
					t += temp.charAt(x);
					return t;
				}

			}

			return t;
		}
		else{
			return r;
		}
	}

	@Override
	protected List<Integer> doInBackground() throws Exception {
		this.downloadRecipies();
		return null;
	}

	protected void process(List<Integer> chunks) { 
		for (int i : chunks) 
			System.out.println(i); 
	} 

	protected void done() {
		try
		{
			Toolkit.getDefaultToolkit().beep();
			downloadingLabel.setText("All Recipies Downloaded!");
			progressBar.setValue(100);

			if(comboBox.getSelectedIndex() == 1){
				structureObject.downloadDone(sortedList, potionList);
			}
			else if(comboBox.getSelectedIndex() == 2){
				structureObject.downloadDone(sortedList, potionList);
			}
			else {
				structureObject.downloadDone(potionList);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}


