package tests;

import searchTree.EmptyTree;
import searchTree.SearchTreeMap;
import junit.framework.TestCase;

/*This class are the student tests that assure that the methods for the binary search tree are right.*/
public class StudentTests extends TestCase {

	/*This method will test the delete method. It will do this by creating a new empty SearchTreeMap. It will then 
	 * check to make sure the size method is right by making sure it equals to zero. The keys and values will then be 
	 * inserted into the map(which also checks the insert method). After this the method will make sure that the size 
	 * is returning 3. Afterwards, the method will remove 2, then check that the size decremented by 1. It does the 
	 * same thing with 1 and 3.*/
	public void testDelete() {
		SearchTreeMap<Integer,String> tree = new SearchTreeMap<Integer,String>();
		assertEquals(0, tree.size());
		tree.put(2, "B");
		tree.put(1, "A");
		tree.put(3, "C");
		tree.put(1, "A2");
		assertEquals(3, tree.size());

		tree.remove(2);
		assertEquals(2, tree.size());

		tree.remove(1);
		assertEquals(1, tree.size());

		tree.remove(3);
		assertEquals(0, tree.size());

		tree.remove(3);


	}

	/*This method will test that the min() and max() methods will work. It will do this by first creating an empty 
	 * SearchTreeMap and defining the max key as 3 and the min key as 1. It will then put the keys and values into the 
	 * tree. If the max is equal to what getMax() returns and the min is equal to what getMin() returns, then this test 
	 * will pass.*/
	public void testMinMax(){
		SearchTreeMap<Integer,String> tree = new SearchTreeMap<Integer,String>();

		Integer max = 3;
		Integer min = 1;

		tree.put(2, "B");
		tree.put(1, "A");
		tree.put(3, "C");
		tree.put(1, "A2");
		assertEquals(max, tree.getMax());
		assertEquals(min, tree.getMin());

	}

	/*This method tests that the keySet method works. It does this by creating a new SearchTreeMap and inserting all of
	 * the keys and values into the map. Makes sure that the keySet is the right size.*/
	public void testKeySet(){
		SearchTreeMap<Integer,String> tree = new SearchTreeMap<Integer,String>();

		tree.put(2, "B");
		tree.put(1, "A");
		tree.put(3, "C");
		tree.put(1, "A2");
		assertEquals(3, tree.keySet().size());
	}

	/*This method tests that the subTree method works. It does this by creating a new SearchTreeMap and inserting all 
	 * of the keys and values into the map. Makes sure that the subMap is the right size.*/
	public void testSubTree(){
		SearchTreeMap<Integer,String> tree = new SearchTreeMap<Integer,String>();
		tree.put(1, "A");
		tree.put(2, "B");
		tree.put(3, "C");
		tree.put(5, "E");

		assertEquals(2, tree.subMap(1,2).size());
	}

	/*This method tests that the keyList method works. It does this by creating a new SearchTreeMap and inserting all 
	 * of the keys and values into the map. Makes sure that the keyList is the right size.*/
	public void testKeyList(){
		SearchTreeMap<Integer,String> tree = new SearchTreeMap<Integer,String>();

		tree.put(1, "A");
		tree.put(2, "B");
		tree.put(3, "C");
		assertEquals(3, tree.keyList().size());
	}

	/*This method tests that the toString method works. It does this by creating a new SearchTreeMap and inserting all 
	 * of the keys and values into the map. The toString method is then called.*/
	public void testToString(){
		SearchTreeMap<Integer,String> tree = new SearchTreeMap<Integer,String>();
		tree.put(1, "A");
		tree.put(2, "B");
		tree.put(3, "C");
		tree.toString();
	}


}