import java.util.*;

/*This class implements the HuffmanTree interface. It holds all of the information pertaining to the leaves in the 
 * Huffman tree.*/
public class HuffmanLeaf implements HuffmanTree {

	// You may not add any other fields to this class
	private Character data;
	private int frequency;

	public HuffmanLeaf(Character label, int count) {
		this.data = label;
		this.frequency = count;
	}

	/**
	 *  Increment this leaf's frequency
	 */
	public void incrementFrequency() {
		frequency++;
	}

	/*This method returns the data held in the leaf.*/
	public Character getData() {
		return data;
	}

	/*This method returns the frequency of the data in the leaf.*/
	public int getFrequency() {
		return frequency;
	}

	/*This method returns a string of 0s and 1s that represents the location of the symbol in the tree. It does this by
	 * checking to see if the symbol equals the data in the leaf. If so, a string of nothing will be returned. 
	 * Otherwise, null is returned.*/
	public String findCode(Character symbol) {
		if(symbol.equals(getData())){
			return "";
		}else{
			return null;
		}

	}

	/*This method returns the character that is represented by a given string of 0s and 1s. Since the current node is 
	 * the leaf, this method will return the data in the current leaf.*/
	public Character decode(Iterator<Character> it) {
		return getData();
	}

	/*This method counts the total nodes in the subtree. Since this is a leaf, this method will return 1 because the 
	 * leaf only consists of 1 node.*/
	public int countNodes() {
		return 1;
	}

	/*This method finds out what level from the current node the character is. Since this is a leaf, the method will 
	 * check if the leaf's data is equal to the character given in the parameter. If so, then 0 is returned. Otherwise, 
	 * -1 is returned.*/
	public int findDepth(Character c) {
		if(c.equals(getData())){
			return 0;
		}
		return -1;
	}

	public String display(String indentation) {
		return indentation + toString();
	}

	public String toString() {
		return frequency + ": " + data;
	}
}
