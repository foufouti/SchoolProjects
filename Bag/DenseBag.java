import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

/*This class creates the dense bag and holds all of the information pertaining to it.*/
public class DenseBag<T> extends AbstractCollection<T> {

	private Map<T, Integer> denseBagMap;
	private int size; // Total number of elements in the bag

	/*This is the constructor which creates an empty dense bag.*/
	public DenseBag() {
		denseBagMap = new HashMap<T, Integer>();
	}

	/*This toString method returns a string of the denseBagMap object.*/
	public String toString() {
		return denseBagMap.toString();

	}



	/*This equals method takes the parameter object o and matches it with the curret dense bag object. If the object in 
	 * the parameter is equal to the current dense bag then the method will return true. If the object in the parameter 
	 * is not an instance of the DenseBag class, then the method will return false. The method will then make an object
	 * of type DenseBag<T> and make it equal to the object o by casting it. If the size and the key set of the current 
	 * dense bag is equal to  the size and the key set of d, then the method will return true. Otherwise it will return 
	 * false.*/
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof DenseBag))
			return false;
		DenseBag<T> denseBag = (DenseBag) o;

		return (size == denseBag.size)&& (denseBagMap.keySet().equals(denseBag.denseBagMap.keySet()));

	}

	/*This hash code method returns the hash code of the current dense bag.*/
	public int hashCode() {
		return denseBagMap.hashCode();
	}



	/*This method calls the Iterator inner class.*/ 
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	/*This inner class creates an iterator for the dense bag.*/
	class MyIterator implements Iterator<T> {


		private Set<Entry<T, Integer>> bagSet = denseBagMap.entrySet();
		private Iterator<Entry<T, Integer>> iterate = bagSet.iterator();
		private Entry<T, Integer> next = iterate.next();
		private int counter = 0;
		private int value = 0;
		private int valueTracker = 0;//Keeps count of how many of each key is in the bag.
		private T key;


		/*This method returns true if the dense bag has a next element and false otherwise. it does this by checking if
		 * the counter is less than the size of the dense bag. As long as the counter (which is incremented in the 
		 * next() method) is less than the size, this method will return true. Otherwise it will return false.*/
		public boolean hasNext() {
			if (counter < size()) {
				return true;
			}
			return false;
		}

		/*This method returns the next element in the dense bag. It does this by first checking if the hasNext() method 
		 * is true, if it is then it will make the val int equal to the current element's value. The int that keeps 
		 * track of the value then increments. If the valTrack is less than or equal to the current value, the key to 
		 * be returned will be the key of the current element. Otherwise, the iterator will go to the next key, the key 
		 * will also change, and the valTrack will be equal to 1. If the hasNext() method is equal to false, then this 
		 * method will throw a NoSuchElementException. In the end, the key will be returned.*/
		public T next() {
			if (hasNext()) { 
				counter++;
				value = next.getValue();
				valueTracker++;
				
				if(valueTracker <= value){
					key = next.getKey();
				}else{
					next =iterate.next();
					key = next.getKey();
					valueTracker = 1;
				}
			} else { 
				throw new NoSuchElementException(); 

			} 
			return key;
			
		}

		/*This method throws an UnsupportedOperationException.*/
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
	}

	/*This uniqueElements method returns the key set of the denseBagMap.*/
	public Set<T> uniqueElements() {
		return denseBagMap.keySet();
	}

	/*This getCount method returns how many instances of a given object there are. It does this by first creating a 
	 * set of the denseBagMap. Afterwards, an iterator will be initialized to iterate through the set. While the 
	 * iterator has next, a new entry will be created that gets the next element of the iterator. If the current 
	 * entry's key is equal to the object in the parameter then the value of the entry will be added to the count. 
	 * In the end, the count will be returned.*/
	public int getCount(Object o) {

		Set<Entry<T, Integer>> bagSet = denseBagMap.entrySet(); 
		Iterator<Entry<T, Integer>> iterate = bagSet.iterator(); 
		int count = 0; 
		while (iterate.hasNext()) { 
			Entry<T, Integer> curr = iterate.next(); //current element in iterator	 
			if (curr.getKey().equals(o)) { 
				count += curr.getValue(); 
			} 
		} 	 
		return count; 

	}

	/*This method returns a random key. It does this by first initializing two ints. One int will keep track of the 
	 * sum of the total values. The other int is the random value. The method will then go through a for-each loop 
	 * through the key set and start adding up all of the values of each key. If the sum ever goes over the random 
	 * number, then the key that the loop is currently at will be returned. Otherwise, the method will return null.*/
	public T choose(Random r) {
		int totalValues = 0;
		int randomNum = r.nextInt(size());
		for (T randomElem : uniqueElements()) {
			totalValues += getCount(randomElem);
			if (totalValues > randomNum) {
				return randomElem;
			}
		}
		return null;
	}

	/*This method checks to see if the object given in the parameter is inside the denseBagMap. It does this by using 
	 * the method contains to see if denseBagMap contains key or if it contains the value, then the method will return
	 * true. Otherwise, it will return false. */
	public boolean contains(Object o) {
		if (denseBagMap.containsKey(o) || denseBagMap.containsValue(o)) {
			return true;
		}
		return false;
	}

	/*This add method adds an instance to the bag. It does this by checking to see if the given object in the parameter 
	 * is in the denseBagMap. If it is then the method will put the object in the denseBagMap and add 1 to the value. 
	 * If the object is not in the denseBagMap, the method will put the object in the denseBagMap and the vaue will be 
	 * one. In the end the method will return true.*/

	public boolean add(T o) {
		if (denseBagMap.containsKey(o)) {
			denseBagMap.put(o, denseBagMap.get(o) + 1);
		} else {
			denseBagMap.put(o, 1);
		}
		return true;
	}

	/*This remove method will remove the object specified in the parameter. It will do this by first checking to see if 
	 * the object is not in the denseBagMap. If it isn't then the method will return false. The method will then check 
	 * to see if the value of the object is 1. If so, the method will remove the objet and return true. Otherwise, the 
	 * method will subtract 1 from the value and return true;*/
	public boolean remove(Object o) {
		if (!denseBagMap.containsKey(o)) {
			return false;
		}

		if (getCount(o) == 1) {
			denseBagMap.remove(o);
			return true;
		} else {
			denseBagMap.put((T) o, denseBagMap.get(o) - 1);
			return true;
		}
	}

	/*This size method returns the sum of the values of each key. It does this by first creating a 
	 * set of the denseBagMap. Afterwards, an iterator will be initialized to iterate through the set. While the 
	 * iterator has next, a new entry will be created that gets the next element of the iterator. The values are added 
	 * together in the size variable. The method will then return the size.*/

	public int size() {
		Set<Entry<T, Integer>> bagSet = denseBagMap.entrySet();
		Iterator<Entry<T, Integer>> iterate = bagSet.iterator();
		size = 0;
		while (iterate.hasNext()) {
			Entry<T, Integer> curr = iterate.next();

			size += curr.getValue();
		}
		return size;
	}
}