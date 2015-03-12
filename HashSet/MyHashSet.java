import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;





/*This class creates a Hash Set and does specific actions to it. These actions include add, remove, contains, get size 
 * and capacity, and it has an iterator inner class.*/
public class MyHashSet<T> implements Iterable<T> {


	public final static int DEFAULT_INITIAL_CAPACITY = 4;
	public final static double LOAD_FACTOR = .75;

	private Node<T>[] table;
	private int size; // number of elements in the table
	private int sizeOfTable;


	/*This inner class keeps track of the location and information within each node in each linked list in the Hash 
	 * Table.*/ 
	private static class Node<T> {
		private T data;
		private Node<T> next;

		/*This is the constructor for the Node inner class. IT initializes the data and the next node.*/
		private Node(T data) {
			this.data = data;
			next = null;
		}

		/*This method creates a new array with the size given in the parameter.*/
		@SuppressWarnings("unchecked")
		private static <T> Node<T>[] makeArray(int size) {
			return new Node[size];
		}

		/*This toString method creates a string version of the Node.*/
		public String toString() {
			String retValue = "[" + data.toString() + ", ";
			retValue += (next == null)? "null" : next.toString();
			retValue += "]";
			return retValue;
		}
	}

	//Signifies the front of each linked list.
	private Node<T> front = null;

	/*This is the constructor for the MyHashSet class. It creates a new table that is the size of the given initial 
	 * capacity. It then initializes the size of the table.*/
	public MyHashSet(int initialCapacity) {
		table = Node.makeArray(initialCapacity);
		sizeOfTable = table.length;
	}

	/*This is a constructor for the myHashSet class and it creates a new table that is the size of the default initial 
	 * capacity. It then initializes the size of the table.*/
	public MyHashSet() {
		table = Node.makeArray(DEFAULT_INITIAL_CAPACITY);
		sizeOfTable = table.length;
	}

	/*This getter returns the size of the table (counting each element in the Hash Table).*/
	public int getSize(){
		return size;
	}

	/*This getter returns the capacity of the table (counting only the "buckets" holding the linked lists in the hash 
	 * table).*/
	public int getCapacity(){
		return sizeOfTable;
	}

	/*This method will return true if the element specified in the parameter is anywhere in the Hash Set and false 
	 * otherwise. It will do this by first doing a for-each loop through the table and making a node that keeps track 
	 * of the current node. A while loop is then made to loop through the linked list in the current bucket and if the 
	 * element's data is found in a node, then the method will return true. Otherwise, the loop will continue and if 
	 * the end is reached and the method still has not returned true, then the method will return false.*/
	public boolean contains(T element) {
		for(Node<T> currNode: table){
			Node<T> current = front;
			while (current != null) {
				if (current.data.equals(element)) {
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}

	/*This method adds the element specified in the parameter to the linked list that is within the bucket of the 
	 * number obtained when the Hash Set is hashed. It does this by first checking to see if the element is already in 
	 * the table. If it is, then the method will end. Otherwise, the method will loop through the buckets of the table 
	 * and once the loop reaches the hashed bucket, then a new node will be created containing the element. This node 
	 * will then be inserted into the front of the current link list and the size will also increment. After the 
	 * element is added, then the method will compute the quotient of the size and the capacity of the table. If the 
	 * quotient is larger than or equal to the LOAD_FACTOR, then the capacity of the table will double. The method will 
	 * then loop through the table and re-hash each element in the table.*/
	public void add(T element) {
		if(this.contains(element)){
			return;

		}
		else{
			for(int index = 0; index < getCapacity(); index++){
				if(index == Hashing(element)){
					Node<T> node = new Node<T>(element);
					node.next = front;
					front = node;

					size++;
				}
			}
		}
		double ratio = (double)getSize()/(double)getCapacity();

		if(ratio >= LOAD_FACTOR){
			sizeOfTable = sizeOfTable*2;
			for(int j = 0; j < sizeOfTable; j++){
				Node<T> current = front;
				while(current.next != null){
					Hashing(current.data);
					current = current.next;

				}
			}
		}
	}

	/*This method will remove the specified element from the table. It will do this by first checking to see if the 
	 * element is in the table. If the element is not in the table, then the method will end. Otherwise, the method 
	 * will loop through the table and 2 nodes will be created. One node to keep track of the previous node and one 
	 * node to keep track of the current node. The method will then loop through each linked list and when the element 
	 * is found, and it is in the front of the linked list, then the front will then refer to the next node. If the 
	 * element is not at the front, then the data in the next of the previous will then move to the node in the next of 
	 * the current. If the element is not found in the table, then the previous and current will keep moving through 
	 * the table.*/
	public void remove(T element) {
		if(!this.contains(element)){
			return;
		}else{
			for(int index = 0; index < getCapacity(); index++){
				Node<T> previous = null, current = front;
				while (current != null) {
					if (current.data.equals(element)) {
						if (current == front)
							front = front.next;
						else{
							previous.next = current.next;
						}
						break;
					} else {
						previous = current;
						current = current.next;
					}
				}
			}
		}
	}

	/*This method takes a given element and returns a number that is computed by the absolute value of a random prime 
	 * number multiplied by the element's hashCode moded by the capacity of the table.*/
	public int Hashing(T currElement){
		int bucketNumber = 0;
		bucketNumber += Math.abs((661*currElement.hashCode())%getCapacity());
		return bucketNumber;
	}

	/*This method returns a new iterator using the MyIterator inner class.*/
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	/*This class is an iterator inner class used to iterate through the table.*/
	class MyIterator implements Iterator<T> {

		private int counter = 0;
		private boolean callNext = false;
		private Node<T> current = front;
		private int amntRemoved = 0;

		/*This method checks to see if there is a next object in the Hash Set. It does this by checking if the counter 
		 * (incremented in the next() method) is less than the size of the table. If it is, then the method will return 
		 * true. Otherwise the method will return false.*/
		public boolean hasNext(){
			if (counter < getSize()){
				return true;
			}
			return false;
		}

		/*This method returns the next element in the table. It does this by first checking to make sure that hasNext() 
		 * is true. If it is true, the method will then increment the counter. If the counter is equal to 1, then the 
		 * callNext variable will be equal to true and the method will return the current node's data. Otherwise, if 
		 * (the linked list is not at the end, callNext will be true and the data in the next node will be returned. If
		 * the table does not have a next element, then an IllegalStateException will be thrown. Otherwise, callNext is
		 * true and null is returned.*/
		public T next(){
			amntRemoved = 0;
			if(hasNext()){
				counter++;
				if(counter == 1){
					callNext = true;
					return current.data;
				}else{
					//if(current.next != null){
						current = current.next;
						callNext = true;
						return current.data;


					//}
				}
			}else { 
				throw new IllegalStateException(); 

			} 
			//callNext = true;
			//return null;

		}


		/*This method will remove the current node. It will do this by making sure the next() method is called, then it
		 *  will make sure that the number of times removed was called is zero. If both of those conditions are true, 
		 *  then the remove from the MyHashSet outer class will be called to remove the current data. Otherwise, an 
		 *  IllegalStateException will be thrown. In the end the variable that keeps track of the number of times 
		 *  remove has been called will be incremented.*/
		public void remove(){
			if(callNext == true && amntRemoved == 0){
				MyHashSet.this.remove(current.data);
			}else{
				throw new IllegalStateException();
			}
			amntRemoved++;
		}
	}

}
