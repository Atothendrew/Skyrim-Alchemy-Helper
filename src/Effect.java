

public class Effect {

	private String name;
	private double number;

	public Effect(String name, double number){ // Constructor
		this.name = name;
		this.number = number;
	}

	public String returnName(){ //returns the name of the Effect object
		return name;
	}

	public double returnNumber(){ //returns the number associated with this object
		return number;
	}
	

	public int compareTo(Effect other){
		
		/*****************************
		 * Method Name: compareTo
		 * Parameters: an Effect object that is used to compare itself with the owner of this class's 'this' object
		 * Method Purpose: The purpose of this method is to help with the sorting process.
		 * This method is called in Sorter.java and is used to compare two effect objects.
		 * Reason: I did it this way because it seemed the most logical way to do it. I experimented with boolean values instead of
		 * comparing the values to '0' in the if statements below, but it became tedious in the long run having to change many methods when
		 * this way works just as well.
		 *****************************/

		int compareName = name.compareTo(other.returnName()); 

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
