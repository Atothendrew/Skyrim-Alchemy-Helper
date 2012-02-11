import java.util.ArrayList;


public class Potion {

	private double value;
	private ArrayList<Effect> effectList = new ArrayList<Effect>();
	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

	private Sorter sort = new Sorter();

	public Potion(ArrayList<Ingredient> ingredientList, ArrayList<Effect> effectList, Double value){ // Constructor
		this.effectList = effectList;
		this.ingredientList = ingredientList;
		this.value = value;

		sort.sortEffectNames(effectList);
		sort.sortIngredientNames(ingredientList);
	}

	public Object[] convertToArray(int i){
		
		/*****************************
		 * Method Name: convertToArray
		 * Parameters: an int value that shows the position in the list that the current potion has
		 * Purpose: This method is extremely important because it makes it easy to format the table with all the values
		 * How?: This method creates an object array that can hold 10 values. The first value is its order in the potionList array,
		 * the next three values are the ingredients. The next 3-5 values are the effects and the last value is the value of the potion (in game)
		 *****************************/
		
		Object[] temp = new Object[10];

		temp[0] = i;
		temp[1] = ingredientList.get(0).returnName();
		temp[2] = ingredientList.get(1).returnName();
		temp[3] = ingredientList.get(2).returnName();

		temp[4] = effectList.get(0).returnName() + " (" + effectList.get(0).returnNumber() + ")";
		temp[5] = effectList.get(1).returnName() + " (" + effectList.get(1).returnNumber() + ")";

		if (effectList.size() == 2){
			temp[6] = "---------------";
			temp[7] = "---------------";
			temp[8] = "---------------";
		}

		else if (effectList.size() == 3) {
			temp[6] = effectList.get(2).returnName() + " (" + effectList.get(2).returnNumber() + ")";
			temp[7] = "---------------";
			temp[8] = "---------------";
		}

		else if (effectList.size() == 4) {
			temp[6] = effectList.get(2).returnName() + " (" + effectList.get(3).returnNumber() + ")";
			temp[7] = effectList.get(3).returnName() + " (" + effectList.get(3).returnNumber() + ")";
			temp[8] = "---------------";
		}
		else if (effectList.size() == 5) {
			temp[6] = effectList.get(2).returnName() + " (" + effectList.get(3).returnNumber() + ")";
			temp[7] = effectList.get(3).returnName() + " (" + effectList.get(3).returnNumber() + ")";
			temp[8] = effectList.get(4).returnName() + " (" + effectList.get(3).returnNumber() + ")";
		}

		temp[9] = value;
		
		return temp;
	}

	public String toString(){

		String returner = "";

		for (int x = 0; x < ingredientList.size(); x++){
			returner = returner + ingredientList.get(x).returnName();

			if (x + 1 >= ingredientList.size()){
				returner = returner + " = ";
			}
			else {
				returner = returner + " + ";
			}
		}

		for (int y = 0; y < effectList.size(); y++){
			returner = returner + effectList.get(y).returnName() + " (" + effectList.get(y).returnNumber() + ") | ";
		}

		returner = returner + "Value: " + value + "\n";

		return returner;

	}

	public ArrayList<Ingredient> getIngredientList(){ // returns the ingredientList
		return ingredientList;
	}

	public ArrayList<Effect> getEffectList(){ // returns the effectList
		return effectList;
	}

}
