package searchTree;

import java.util.Collection;

/*This class will create a non-empty tree and extends the comparable class and implements the tree interface. Once it 
 * is made, this class controls certain actions that can be performed on the non-empty tree.*/
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	private K key;
	private V value;
	private Tree<K, V> left, right;

	/*This constructor initializes the keys and the values for for the non-empty tree being made. It also initializes 
	 * the left and right trees to empty trees for the current non-empty tree.*/
	public NonEmptyTree(K keys, V values) {
		this.key = keys;
		this.value = values;

		left = EmptyTree.getInstance();
		right = EmptyTree.getInstance();

	}

	/*This method searches through the tree to find out where the given key is located within the tree. It does this by
	 * first comparing the given key to the current key. If the comparison number is 0, then the current value will be 
	 * returned. If the comparison number is positive then the right tree will then be searched. If the comparison 
	 * value is negative then the left tree will be searched. If none of these are true, then the method will return 
	 * null.*/
	public V search(K key) {
		int comp = key.compareTo(this.key);
		if (comp == 0){
			return this.value;
		}
		if (comp > 0){
			return this.right.search(key);
		}
		if(comp < 0){
			return  this.left.search(key);
		}
		return null;
	}

	/*This method will insert a new non-empty tree into the current tree. It will do this by fist comparing the given 
	 * key to the current key. If the comparison value is equal to zero, then the current value will become the given 
	 * value and the current non-empty tree will be returned. If the comparison value is positive then the current 
	 * right tree will call the insert method. If the comparison value is negative then the current left tree will call 
	 * the insert method and the currnt non-emoty tree will be returned. Otherwise a new non-empty tree will be 
	 * returned containing the given key and value.*/
	public NonEmptyTree<K, V> insert(K key, V value) {
		int comp = key.compareTo(this.key);
		if (comp == 0){
			this.value = value;
			return this;
		}else if (comp > 0) { 
			this.right = this.right.insert(key, value);
			return this;
		}else if (comp < 0) { 
			this.left = this.left.insert(key, value); 
			return this;
		}
		return new NonEmptyTree<K, V>(key, value);
	}

	/*This method will delete a specified key. It will do this by first comparing the given key to the current key. If 
	 * the comparison value is positive, then the right non-empty tree will call delete. If the comparison value is 
	 * negative, then the left non-empty tree will call delete. Otherwise, the current key and value will be the 
	 * maximum key and value on the left. And the non-empty tree that the information was taken from will then be 
	 * turned to an EmptyTree. If the TreeIsEmptyException is caught, then the right tree will be returned.*/
	public Tree<K, V> delete(K key) {
		int comp = key.compareTo(this.key);

		if (comp > 0) {
			this.right = this.right.delete(key);
			return this;
		}
		if(comp < 0){
			this.left = this.left.delete(key);
			return this;
		}else{
			try {
				this.key = this.left.max();
				this.value = this.left.search(this.key);
				this.left = EmptyTree.getInstance();
				return this;


			} catch (TreeIsEmptyException e) {
				return this.right; 
			}
		}


	}

	/*This method returns the maximum key. It does this by getting the right non-empty tree to call max() method. If 
	 * the TreeIsEmptyException is caught then the key will be returned.*/
	public K max() {
		try {
			return this.right.max();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}

	/*This method returns the minimum key. It does this by getting the left non-empty tree to call min() method. If 
	 * the TreeIsEmptyException is caught then the key will be returned.*/
	public K min() {
		try {
			return this.left.min();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}

	/*This method will return the size of the non-empty tree. It will do this by returning the sum of the size of the 
	 * left non-empty tree, the right non-empty tree, and one (for the root).*/
	public int size() {
		return 1 + left.size() + right.size();	
	}

	/*This method takes the keys from the non-empty tree and adds them to a collection. It does this by getting the 
	 * left non-empty tree to call this method. Then it will add the key to the collection. Then the right non-empty 
	 * tree will call this method.*/
	public void addKeysToCollection(Collection<K> c) {
		this.left.addKeysToCollection(c);
		c.add(key);
		this.right.addKeysToCollection(c);
	}

	/*This method will return a subtree that is between two given keys. It will do this by first comparing the fromKey 
	 * to to the current key, then comparing the toKey to the current key. If the comparison value for fromKey is 
	 * positive, then the right non-empty tree will call the subtree method. If the comparison value for toKey is 
	 * negative then the left non-empty tree will call the method. Otherwise, a new non-empty tree will be made and the
	 * left non-empty tree in the new tree will call this method. The right non-empty tree will also call this method. 
	 * Afterwards, the /*This method returns the maximum key. It does this by getting the right non-empty tree to call 
	 * max() method. If the TreeIsEmptyException is caught then the key will be returned.*/
	public Tree<K,V> subTree(K fromKey, K toKey) {
		int compFromKey = fromKey.compareTo(this.key);
		int compToKey = toKey.compareTo(this.key);
		if (compFromKey > 0){
			this.right = this.right.subTree(fromKey, toKey);
			return this.right;
		}
		else if(compToKey < 0){
			this.left = this.left.subTree(fromKey, toKey);
			return this.left;
		}
		else{
			NonEmptyTree<K, V> tree = new NonEmptyTree<K, V>(key,value);
			tree.left = this.left.subTree(fromKey, toKey);
			tree.right = this.right.subTree(fromKey, toKey);
			return tree;
		}
	}



}