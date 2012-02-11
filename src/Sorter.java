import java.util.ArrayList;


public class Sorter {

	public ArrayList<Effect> sortEffectNames(ArrayList<Effect> old){

		for (int x=0; x<old.size()-1; x++) { // this for loop implements selection sort algorithm
			for (int y=x+1; y<old.size(); y++) {
				Effect first = (Effect) old.get(x); // creates a Effect object
				Effect second = (Effect) old.get(y); // creates a Effect object
				if (first.compareTo(second) > 0) { // compares the Effect objects
					//... Exchange elements
					Effect temp = old.get(x); // swaps the Effect objects
					old.set(x, old.get(y)); // swaps the Effect objects
					old.set(y, temp); // swaps the Effect objects

				}
			}
		}
		return old;
	}

	public ArrayList<Ingredient> sortIngredientNames(ArrayList<Ingredient> old){

		for (int x=0; x<old.size()-1; x++) {
			for (int y=x+1; y<old.size(); y++) {
				Ingredient first = (Ingredient) old.get(x);
				Ingredient second = (Ingredient) old.get(y); 
				if (first.compareTo(second) > 0) { 
					//... Exchange elements
					Ingredient temp = old.get(x); 
					old.set(x, old.get(y)); 
					old.set(y, temp); 

				}
			}
		}
		return old;
	}

	public ArrayList<Potion> sortByName(ArrayList<Potion> old){ 
		
		ArrayList<Potion> newList = new ArrayList<Potion>();
		
		for (int b = 0; b < old.size(); b++){
			newList.add(old.get(b));
		}
		
		for (int x=0; x<newList.size()-1; x++) {
			for (int y=x+1; y<newList.size(); y++) {
				Potion first = (Potion) newList.get(x);
				Potion second = (Potion) newList.get(y); 
				ArrayList<Ingredient> firstIList = first.getIngredientList();
				ArrayList<Ingredient> secondIList = second.getIngredientList();
				Ingredient firstIngredient = firstIList.get(0);
				Ingredient secondIngredient = secondIList.get(0);
				
				if (firstIngredient.compareTo(secondIngredient) > 0) { 
					//... Exchange elements
					Potion temp = newList.get(x); 
					newList.set(x, newList.get(y)); 
					newList.set(y, temp); 

				}
				
				else if (firstIngredient.compareTo(secondIngredient) == 0) { 
					Ingredient firstIngredientTwo = firstIList.get(1);
					Ingredient secondIngredientTwo = secondIList.get(1);
					
					if (firstIngredientTwo.compareTo(secondIngredientTwo) > 0) { 
						//... Exchange elements
						Potion temp = newList.get(x); 
						newList.set(x, newList.get(y)); 
						newList.set(y, temp); 

					}
					
					else if (firstIngredientTwo.compareTo(secondIngredientTwo) == 0) { 
						Ingredient firstIngredientThree = firstIList.get(2);
						Ingredient secondIngredientThree = secondIList.get(2);
						
						if (firstIngredientThree.compareTo(secondIngredientThree) > 0) { 
							//... Exchange elements
							Potion temp = newList.get(x); 
							newList.set(x, newList.get(y)); 
							newList.set(y, temp); 
						}
					}
				}
			}
		}
		
		return newList;
	}
	
	public ArrayList<Potion> sortByEffect(ArrayList<Potion> old){ 
		
		ArrayList<Potion> newList = new ArrayList<Potion>();
		
		for (int b = 0; b < old.size(); b++){
			newList.add(old.get(b));
		}
		
		for (int x=0; x<newList.size()-1; x++) {
			for (int y=x+1; y<newList.size(); y++) {
				Potion first = (Potion) newList.get(x);
				Potion second = (Potion) newList.get(y); 
				ArrayList<Effect> firstEffectList = first.getEffectList();
				ArrayList<Effect> secondEffectList = second.getEffectList();
				Effect firstEffect = firstEffectList.get(0);
				Effect secondEffect = secondEffectList.get(0);
				
				if (firstEffect.compareTo(secondEffect) > 0) { 
					//... Exchange elements
					Potion temp = newList.get(x); 
					newList.set(x, newList.get(y)); 
					newList.set(y, temp); 

				}
				
				else if (firstEffect.compareTo(secondEffect) == 0) { 
					Effect firstIngredientTwo = firstEffectList.get(1);
					Effect secondIngredientTwo = secondEffectList.get(1);
					
					if (firstIngredientTwo.compareTo(secondIngredientTwo) > 0) { 
						//... Exchange elements
						Potion temp = newList.get(x); 
						newList.set(x, newList.get(y)); 
						newList.set(y, temp); 

					}
					
					else if (firstIngredientTwo.compareTo(secondIngredientTwo) == 0) { 
						Effect firstIngredientThree;
						Effect secondIngredientThree;
						
						if (firstEffectList.size() == 2){
							firstIngredientThree = firstEffectList.get(1);
						}
						else {
							firstIngredientThree = firstEffectList.get(2);
						}
						
						if (secondEffectList.size() == 2){
							secondIngredientThree = secondEffectList.get(1);
						}
						else {
							secondIngredientThree = secondEffectList.get(2);
						}
						
						if (firstIngredientThree.compareTo(secondIngredientThree) > 0) { 
							Potion temp = newList.get(x); 
							newList.set(x, newList.get(y)); 
							newList.set(y, temp); 
						}
					}
				}
			}
		}
		
		return newList;
	}
}
