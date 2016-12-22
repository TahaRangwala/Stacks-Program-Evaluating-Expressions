/*Name: Taha Rangwala
 * Date: December 14, 2016
 * Purpose: The purpose of this class is to allow to hold all of the information needed to do
 * all of the calculations in this program, and it outputs the calculations back to the GUI.
 */

//Expression class header
public class Expression <T extends Character> {

	//Declaring private instance variables
	private String theExpression;//String value of the inputted expression
	private StackADT stackObj;//the stack object

	/*Purpose: The purpose of this method is to initialize the expression and make the stack an array stack
	 * @param theExpression the expression the user inputted
	 * @param array the array stack
	 */
	public Expression(String theExpression, Array array) {
		this.theExpression = theExpression;
		stackObj = array;
	}
	
	/*Purpose: The purpose of this method is to initialize the expression and make the stack a linked list stack
	 * @param theExpression the expression the user inputted
	 * @param List the linked list stack
	 */
	public Expression(String theExpression, LinkedList List){
		this.theExpression = theExpression;
		stackObj = List;
	}

	/*Purpose: The purpose of this method is to convert the inputted expression which is infix into postfix
	 * @return This returns a string value of the postfix expression
	 */
	public String convertToPostfix(){
		checkExpression();
		String subExpression = theExpression.trim(), postFix = "";
		stackObj.push('(');
		subExpression += ")";
		char currentChar;
		boolean operatorHappened = false;
		int leftCount = 0, rightCount = 0;
		while(!stackObj.isEmpty() && !subExpression.equals("")){
			currentChar = subExpression.charAt(0);
			subExpression = subExpression.substring(1).trim();
			if(Character.isDigit(currentChar) || (currentChar == '-' && Character.isDigit(subExpression.charAt(0))) 
			&& (operatorHappened || postFix.equals(""))){
				postFix += currentChar;
				operatorHappened = false;
			}
			else if(currentChar == '('){
				stackObj.push(currentChar);
				leftCount++;
			}
			else if(isOperator(currentChar) && !operatorHappened && !subExpression.equals("")){
				postFix += " " + popSpecificOperators(currentChar);
				stackObj.push(currentChar);
				operatorHappened = true;
			}
			else if(currentChar == ')'){
				postFix += " " + popAllOperators();
				if(!subExpression.equals(""))
					rightCount++;
				else if(leftCount != rightCount && !subExpression.equals(""))
					throw new IllegalArgumentException("Check Your Parenthesees!");
			}
			else
				throw new IllegalArgumentException("The Expression Is Invalid!");
		}
		if(leftCount != rightCount)
			throw new IllegalArgumentException("Check Your Parenthesees!");
		return postFix;
	}
	
	//This method makes sure the inputted expression is not empty
	private void checkExpression(){
		if(theExpression.trim().equals(""))
			throw new IllegalArgumentException("Your Expression Is Empty!");
	}
	
	/*Purpose: This method pops all of the operators in the stack in the postfix method
	 * @return This returns a string value of all of the operators that were popped off
	 */
	private String popAllOperators(){
		String Output = "";
		while((Character)stackObj.peek() != '('){
			if(stackObj.isEmpty())
				throw new IllegalArgumentException("The Expression Is Invalid!");
			if(isOperator((char) stackObj.peek()))
				Output += stackObj.pop() + "";
		}
		stackObj.pop();
		return Output;
	}
	
	/*Purpose: This method pops specific operators off of the stack by checking the precedence of each operator in the stack
	 * @return This returns a string value of all of the operators that were popped off
	 */
	private String popSpecificOperators(char C){
		int Precedence = checkPrecedence(C);
		String Output = "";
		while((Character)stackObj.peek() != '('){
			if(isOperator((char) stackObj.peek()) && Precedence <= checkPrecedence((char) stackObj.peek()))
				Output += stackObj.pop() + " ";
			else
				return Output;
		}
		return Output + "";
	}

	/*Purpose: This method checks to make sure if a character is an operator
	 * @param C This is the character being checked
	 * @return This returns a boolean value of whether or not the character is an operator
	 */
	private boolean isOperator(char C) {
		return C == '+' || C == '-' || C == '*' || C == '/' || C == '%' || C == '^';
	}
	
	/*Purpose: This method determines the precedence of each operator according to the order of operations
	 * @param C This is the operator being checked
	 * @return This returns an integer value of the precedence of the operator
	 */
	private int checkPrecedence(char C){
		if(C == '^')
			return 3;
		else if(C == '*' || C == '/' || C == '%')
			return 2;
		else if(C == '+' || C == '-')
			return 1;
		throw new IllegalArgumentException("This Is Not An Operator!");
	}

	/*Purpose: The purpose of this method is to calculate and determine the result of the postfix expression
	 * @return This returns a string value of the result of the postfix expression
	 */
	public String getCalculation() {
		theExpression = convertToPostfix();
		int Count = theExpression.length();
		String currentNum = "";
		while(Count != 0){
			char currentChar = theExpression.charAt(0);
			theExpression = theExpression.substring(1);
			Count--;
			if(Character.isDigit(currentChar) || (!theExpression.equals("") && currentChar == '-' && Character.isDigit(theExpression.charAt(0))))
				currentNum += currentChar;
			else if(currentChar == ' ' && !currentNum.equals("")){
				stackObj.push(convertToNumber(currentNum)); 
				currentNum = "";
			}
			else if(isOperator(currentChar)){
				double X = (double)stackObj.pop(), Y = (double)stackObj.pop();
				stackObj.push(Calculation(X,Y,currentChar));
			}
		}
		return "" + stackObj.pop();
	}
	
	/*Purpose: This method converts String values that contain digits or even negative signs into number values
	 * @param Num This is the string being converted into a number
	 * @return This method returns a double value of the number determined from the string value Num
	 */
	private double convertToNumber(String Num){
		Num = Num.trim() + ")";
		double finalNum = 0;
		boolean isNegative =  false;
		if(Num.charAt(0) == '-'){
			isNegative = true;
			Num = Num.substring(1);
		}
		while(!(Num.equals(")"))){
			if(Character.isDigit(Num.charAt(0))){
				finalNum = (finalNum * 10) + Character.getNumericValue(Num.charAt(0));
				Num = Num.substring(1);
			}
		}
		if(isNegative)
			return finalNum * -1;
		return finalNum;	
	}
	
	/*Purpose: The purpose of this method is to find the result of the two values according to their operator
	 * @param X double value of one of the values in the postfix
	 * @param Y double value of another one of the values in the postfix
	 * @param Operator This is the operator that will be determining the calculation
	 * @return This returns a double value of the result of the calculation
	 */
	private double Calculation(double X, double Y, char Operator){
		if(Operator == '^')
			return Math.pow(Y,X);
		else if(Operator == '*')
			return X * Y;
		else if(Operator == '/'){
			if(X == 0)
				throw new IllegalArgumentException("Don't Divide By 0!");
			return Y / X;
		}
		else if(Operator == '%'){
			if(X == 0)
				throw new IllegalArgumentException("Don't Mod By 0!");
			return Y % X;
		}
		else if(Operator == '+')
			return Y + X;
		else if(Operator == '-')
			return Y - X;
		throw new IllegalArgumentException("There Was An Error With The Calculation!");
	}

}


