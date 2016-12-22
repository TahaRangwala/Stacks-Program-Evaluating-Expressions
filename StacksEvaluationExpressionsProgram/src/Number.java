/*Name: Taha Rangwala
 * Date: February 8, 2016
 * Purpose: The purpose of this class is to do all the calculations for determining the numbers
 * in the equation the user has inputted.
 */

//Number class header
public class Number {
	
	//Declaring private instance variables
	private double numValue;//value of the number
	
	//Constructor method to initialize instance variables
	public Number(){
		numValue = 0;//num value
	}
	
	/*Purpose: The purpose of this method is to return the number back to the database class
	 * @return this returns a double value of the number value
	 */
	public double getNumValue(){
		return numValue;
	}
	
	/*Purpose: This calculates/determines the numbers that are in the string equation
	 * @parm Equation this is the equation or part of the equation the user has inputted
	 * @return this returns a string value of the remaing part of the equation that is needed
	 * in order to complete the calculations
	 */
	public String determineNum(String Equation){
		boolean isDone = false, isNegativeNum = false, isDecimalNum = false;
		double decMultiplier = 1;
		int Count = 0;
		numValue = 0;
		if(Equation.charAt(Count) == '-'){
			isNegativeNum = true;
			Equation = Equation.substring(0,Count) + Equation.substring(Count+1);
		}
		while(!isDone){
			if(Character.isDigit(Equation.charAt(Count))){
				if(!isDecimalNum){numValue = (numValue * 10) + Character.getNumericValue(Equation.charAt(Count));}
				else {numValue = numValue +  ((decMultiplier/=10) * Character.getNumericValue(Equation.charAt(Count)));}
			}
			else if(Equation.charAt(Count) == '.' && !isDecimalNum && !(Count == Equation.length() -1 || !Character.isDigit(Equation.charAt(Count+1))))	
				isDecimalNum = true;
			else if(((isOperator(Equation.charAt(Count))) || (Equation.charAt(Count) == ' ') && FindDigit(Equation,Count) > -1) && Count != Equation.length()-1){
				isDone = true;	
				if(isNegativeNum){numValue *= -1;}
				return Equation.substring(Count,Equation.length()).trim();
			}
			else {throw new IllegalArgumentException("Equation is invalid!");}
			if(Count == Equation.length() -1 && Character.isDigit(Equation.charAt(Equation.length()-1))){
				isDone = true;
				if(isNegativeNum){numValue *= -1;}
			}
			Count++;
		}
		return Equation.substring(Count, Equation.length()).trim();
	}
	
	/*Purpose: The purpose of this method is to tell whether or not a character is an operator
	 * @parm C this is the character being checked to see if it is an operator
	 * @return this method returns a boolean value of whether or not the character is an operator
	 */
	private boolean isOperator(char C){
		if(C == '+' || C == '-' || C == '*' || C == '/')
			return true;
		else return false;
	}
	
	/*Purpose: This finds a position of a digit for error checking
	 * @parm Equation this is the equation that the user has inputted
	 * @parm Count this is the current position of the Equation that is being checked
	 * @return this returns an integer value of the position of the digit
	 */
	private int FindDigit(String Equation, int Count){
		int Limit = FindOperator(Equation);
		if(!(Limit > -1))Limit = Equation.length()-1;
		for(int i = Count; i < Limit; i++){
			if(Character.isDigit(Equation.charAt(i)))
				return i;
		}
		return -1;
	}
	
	/*Purpose: This finds a position of the operator
	 * @parm Equation this is the equation that the user has inputted
	 * @return this returns an integer value of the position of an operator
	 */
	private int FindOperator(String Equation){
		for(int i = 0; i < Equation.length(); i++){
			if(isOperator(Equation.charAt(i))){
				return i;
			}
		}
		return -1;
	}
	
}
