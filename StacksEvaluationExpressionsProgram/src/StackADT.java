/*Name: Taha Rangwala
 * Date: December 5, 2016
 * Purpose: This is the interface and this allows multiple classes to have the same methods and also
 * have the same object name as well if they do similar actions
 */

//StackADT interface header
public interface StackADT<T> {

	/*Purpose: This method adds elements to arrays, linked lists, and more
	 * @param element This is the element that is added arrays, linked lists, or more
	 */
	public void push(T element);
	
	/*Purpose: This method takes off the last element of arrays, linked lists, or more and then returns the
	 * element taken off as well
	 * @return This is the element that is being returned
	 */
	public T pop();
	
	/*Purpose: This method returns the last element in the array, linked list, or more
	 * @return This is the element that is being returned
	 */
	public T peek();
	
	/*Purpose: This checks if the array, linked list, or more is empty
	 * @return This returns a boolean value denoting if the array or linked list is empty or not
	 */
	public boolean isEmpty();
	
	/*Purpose: This gets the current amount of elements in the array, linked list, or more
	 * @return This returns an integer value of the elements in the array, linked list, or more
	 */
	public int size();
	
	/*Purpose: This gets all of the information in the array, linked list, or more
	 * @return This returns a string value of all of the information
	 */
	public String toString();
	
}
