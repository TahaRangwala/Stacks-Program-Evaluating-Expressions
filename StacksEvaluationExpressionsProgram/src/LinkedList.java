/*Name: Taha Rangwala
 * Date: December 14, 2016
 * Purpose: The purpose of this class is to allow is to use the methods from the StackADT in order
 * to manage elements in a linked list
 */

//LinkedList class header
public class LinkedList<T extends Character> implements StackADT{
	
	//Declaring private instance variables
	private ListNode Head;//the head of the linked list
	
	//Initializing the private instance variables
	public LinkedList(){
		Head = null;
	}

	/*Purpose: This method adds elements to the linked list
	 * @param element This is the element that is added to the linked list
	 */
	public void push(Object element) {
		if(Head == null)
			Head = new ListNode(element, null);
		else{
			ListNode Temp = Head;
			while(Temp.getNext() != null)
				Temp = Temp.getNext();
			Temp.setNext(new ListNode(element,null));
		}
	}

	/*Purpose: This method takes off the last element of the linked list and returns the element
	 * @return This is the element that is being returned
	 */
	public Object pop() {
		if(isEmpty())
			throw new IllegalArgumentException("The Expression Is Invalid!");
		ListNode Temp = Head, Previous = null;
		while(Temp.getNext() != null){
			Previous = Temp;
			Temp = Temp.getNext();
		}
		if(Previous == null)
			Head = null;
		else
			Previous.setNext(null);
		return Temp.getValue();
	}

	/*Purpose: This method returns the last element in linked list
	 * @return This is the element that is being returned
	 */
	public T peek() {
		if(isEmpty())
			throw new IllegalArgumentException("The Expression Is Invalid!");
		ListNode Temp = Head;
		while(Temp.getNext() != null)
			Temp = Temp.getNext();
		return (T) Temp.getValue();
	}

	/*Purpose: This checks if the linked list is empty
	 * @return This returns a boolean value denoting if the linked list is empty
	 */
	public boolean isEmpty() {
		return Head == null;
	}
	
	/*Purpose: This gets the current amount of elements in the linked list
	 * @return This returns an integer value of the elements in the linked list
	 */
	public int size() {
		int Size = 1;
		ListNode Temp = Head;
		while(Temp != null){
			Size++;
			Temp = Temp.getNext();
		}
		return Size;
	}
	
	/*Purpose: This gets all of the information in the linked list
	 * @return This returns a string value of all of the information
	 */
	public String toString(){
		String Output = "";
		while(!isEmpty()){
			Output += pop();
		}
		return Output;
	}

}