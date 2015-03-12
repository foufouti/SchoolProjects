package searchTree;

import java.util.Collection;


/*This class will create an empty tree and extends the comparable class and implements the tree interface. Once it 
 * is made, this class controls certain actions that can be performed on the empty tree.*/
 public class EmptyTree<K extends Comparable<K>,V> implements Tree<K,V> {
	/**
	 * This static field references the one and only instance of this class.
	 * We won't declare generic types for this one, so the same singleton
	 * can be used for any kind of EmptyTree.
	 */
	private static EmptyTree SINGLETON = new EmptyTree();


	public static  <K extends Comparable<K>, V> EmptyTree<K,V> getInstance() {
		return SINGLETON;
	}

	/**
	 * Constructor is private to enforce it being a singleton
	 *  
	 */
	private EmptyTree() {
		// Nothing to do
	}
	
	/*This method returns null since the tree is empty and there is nothing to search through.*/
	public V search(K key) {
		return null;
	}
	
	/*This method will return a non-empty tree containing the given key and value to be inserted.*/
	public NonEmptyTree<K, V> insert(K key, V value) {
		return new NonEmptyTree<K, V>(key, value);
	}

	/*This method will return the current empty tree since there is nothing in the tree to be deleted.*/
	public Tree<K, V> delete(K key) {
		return this;
	}
	
	/*This method throws a TreeIsEmptyException since the tree is empty. And there is no max key.*/
	public K max() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	/*This method throws a TreeIsEmptyException since the tree is empty. And there is no min key.*/
	public K min() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	/*This method returns zero. Because the tree is empty, then the size would be zero.*/
	public int size() {
		return 0;
	}

	/*This method immediately ends after it is called because there are no keys to be added to the collection.*/
	public void addKeysToCollection(Collection<K> c) {
		return;	
		
	}

	/*This method returns an empty tree because since the tree is empty, there are no subtrees in it.*/
	public Tree<K,V> subTree(K fromKey, K toKey) {
		return EmptyTree.getInstance();
	}
}