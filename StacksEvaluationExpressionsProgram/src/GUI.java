/*Name: Taha Rangwala
 * Date: December 14, 2016
 * Purpose: The purpose of this class is to give the user a friendly interface that allows
 * the user to use the program. This gives the user buttons, input and output fields, and more 
 * so that he or she can use the program.
 */

import BreezySwing.*;//allows for more window objects

import java.awt.Color;//allows for color

import javax.swing.*;//allows for more window objects

//GUI class header
public class GUI extends GBFrame{

	//Declaring private instance variables and window objects
	private Expression theExpression;//theExpression object
	private JLabel expressionLabel, calculationLabel;
	private JTextField expressionField;
	private JTextArea calculationArea;
	private JButton CalculateArray, CalculateList, Exit;
	
	//Constructor method that initializes private instance variables and window objects
	public GUI(){
		expressionLabel = addLabel("Expression",1,1,1,1);
		expressionField = addTextField("",1,2,1,1);
		calculationLabel = addLabel("Calculation",2,1,1,1);
		calculationArea = addTextArea("",2,2,1,1);
		CalculateArray = addButton("Calculate with Array",3,1,1,1);
		CalculateList = addButton("Calculate with List",3,2,1,1);
		Exit = addButton("Exit",4,2,1,1);
		//calculationArea.setEditable(false);
	}
	
	/*Purpose: The purpose of this method is to detect which button the user presses and then it performs whatever task
	 * that the user wants
	 * @param buttonObj This is the button object which holds the value of what button the user has pressed
	 */
	public void buttonClicked(JButton buttonObj){
		if(buttonObj == CalculateArray){//with array
			try{
				theExpression = new Expression(expressionField.getText(), new Array());
				calculationArea.setText(theExpression.convertToPostfix() + "\n" + theExpression.getCalculation());
				expressionField.selectAll();
				expressionField.grabFocus();
			}
			catch(Exception E){
				JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(buttonObj == CalculateList){//with linked list
			try{
				theExpression = new Expression(expressionField.getText(), new LinkedList());
				calculationArea.setText(theExpression.convertToPostfix() + "\n" + theExpression.getCalculation());
				expressionField.selectAll();
				expressionField.grabFocus();
			}
			catch(Exception E){
				JOptionPane.showMessageDialog(new JFrame(),E.getLocalizedMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			this.dispose();//closes the program
	}
	
	//Main method to set up the GUI
	public static void main (String [] args){
		GUI theMainGUI = new GUI();//GUI object
		theMainGUI.setSize(380,300);//size of the GUI interface
		theMainGUI.setTitle("Taha's Calculator");//title of GUI interface
		theMainGUI.setVisible(true);//visibility of interface
		theMainGUI.setLocationRelativeTo(null);//Location is in center of screen
		//theMainGUI.setLookAndFeel("MOTIF");//This changes the look of the GUI interface
		theMainGUI.getContentPane().setBackground(new Color(169,229,255));//background of GUI is light blue
	}
}
