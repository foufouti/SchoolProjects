package searchTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.List;


/**
 * This class provides a partial implementation of the Map interface. The
 * implementation uses classes implementing the Tree interface to represent the
 * actual search tree.  All the methods of this class have been implemented, except
 * keyList and subMap.
 *  
 */
public class SearchTreeMap<K extends Comparable<K>, V>  {
	Tree<K,V> root = EmptyTree.getInstance();

	/**
	 * Find the value the key is mapped to
	 * 
	 * @param k -
	 *            Search key
	 * @return value k is mapped to, or null if there is no mapping for the key
	 */
	public V get(K k) {
		return root.search(k);
	}

	/**
	 * Update the mapping for the key
	 * 
	 * @param k -
	 *            key value
	 * @param v -
	 *            value the key should be bound to
	 */
	public void put(K k, V v) {
		root = root.insert(k, v);
	}

	/**
	 * Return number of keys bound by this map
	 * 
	 * @return number of keys bound by this map
	 */
	public int size() {
		return root.size();
	}

	/**
	 * Remove any existing binding for a key
	 * 
	 * @param k -
	 *            key to be removed from the map
	 */
	public void remove(K k) {
		root = root.delete(k);
	}

	/*This method returns a set that contains all of the keys from the tree. It does this by first creating an empty 
	 * HashSet. It then adds the keys of the root tree to the HashSet. The new HashSet will be returned.*/
	public Set<K> keySet() {
		Set<K> result = new HashSet<K>();
		root.addKeysToCollection(result);
		return result;
	}

	/**
	 * Return the minimum key value in the map
	 * 
	 * @return the minimum key value in the map
	 * @throws NoSuchElementException if the map is empty
	 */
	public K getMin() {
		try {
			return root.min();
		} catch (TreeIsEmptyException e) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Return the maximum key value in the map
	 * 
	 * @return the maximum key value in the map
	 * @throws NoSuchElementException if the map is empty
	 */
	public K getMax() {
		try {
			return root.max();
		} catch (TreeIsEmptyException e) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Return a string representation of the tree
	 */
	public String toString() {
		return root.toString();
	}


	/*This method returns a list that contains all of the keys from the tree. It does this by first creating an empty 
	 * ArrayList. It then adds the keys of the root tree to the HashSet. The new ArrayList will be returned.*/
	public List<K> keyList() {
		List<K> listOfKeys = new ArrayList<K>();
		root.addKeysToCollection(listOfKeys);
		return listOfKeys;
	}

	/*This method will return a map of all of the keys and values that are in the sub trees between two specified 
	 * keys. It will do this by creating a new SearchTreeMap and inserting the keys and values from the subtree to the 
	 * subMap. The subMap will then be returned.*/
	public SearchTreeMap<K, V> subMap(K fromKey, K toKey) {

		SearchTreeMap<K, V> subMap = new SearchTreeMap<K, V>();
		subMap.root = root.subTree(fromKey, toKey);
		return subMap;
	}
}