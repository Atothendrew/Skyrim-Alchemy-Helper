
public class Ingredient {
	
	private String name;

	public Ingredient(String name){ // Constructor
		this.name = name;
	}
	
	public String returnName(){ // returns the name associated with this ingredient.
		return name;
	}
	
	public int compareTo(Ingredient other){
		
		/*****************************
		 * Method Name: compareTo
		 * Parameters: an Ingredient object that is used for comparing
		 * Purpose: The purpose of this method is to make sorting faster, smarter and easier to understand.
		 * Reason: I did it this way because it seemed the most logical way to do it. I experimented with boolean values instead of
		 * comparing the values to '0' in the if statements below, but it became tedius in the long run having to change many methods when
		 * this way works just as well.
		 *****************************/

		int compareName = name.compareTo(other.returnName()); // creates an integer that checks and compares the courses

		if (compareName < 0)
		{  
			return compareName;
		}  
		else if (compareName > 0)
		{  
			return compareName;
		}
		else if (compareName == 0) {
			return compareName;
		}

		return (Integer) null;

	}
	
}
