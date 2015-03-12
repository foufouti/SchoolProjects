import java.util.*;

/*This class implements the HuffmanTree interface. It holds all of the information pertaining to the internal nodes in 
 * the Huffman tree.*/
public class HuffmanInternalNode implements HuffmanTree {

	// You may NOT add any other fields to this class
	private HuffmanTree leftChild, rightChild;

	public HuffmanInternalNode(HuffmanTree left, HuffmanTree right) {
		leftChild = left;
		rightChild = right;
	}

	/*This method gets the Frequency of the current internal node. It does this by recursively adding the frequencies 
	 * of the left and right children.*/
	public int getFrequency() {
		return leftChild.getFrequency() + rightChild.getFrequency();
	}

	/*This method returns a string of 0s and 1s that represents the location of the symbol in the tree. It does this by
	 * recursively checking to make sure that the left and right children of the current internal node are not null. If 
	 * the left child is not null, a zero is added to the string and go recursively back through the method through the 
	 * left child and also return the current string.  If the right child is not null, a one is added to the string and 
	 * go recursively back through the method through the right child and also return the current string. If they are 
	 * both null, then null will be returned.*/
	public String findCode(Character symbol) {
		String concan = "";

		if(leftChild.findCode(symbol) != null){
			concan += "0";
			return concan + leftChild.findCode(symbol);
		}else if(rightChild.findCode(symbol) != null){
			concan += "1";
			return concan + rightChild.findCode(symbol);
		}

		return null;

	}

	/*This method returns the character that is represented by a given string of 0s and 1s. It will do this by looping 
	 * through the iterator given in the parameter. If the current character is equal to 1, the method will recursively 
	 * be called with the right child. If the current character is equal to 0, the method will recursively 
	 * be called with the left child. Otherwise, null is returned. */
	public Character decode(Iterator<Character> it) {
		Character zero = new Character ('0');
		Character one = new Character ('1');
		Character next;

		while(it.hasNext()){

			next = it.next();
			if(next.equals(one)){
				return rightChild.decode(it);
			}else if(next.equals(zero)){
				return leftChild.decode(it);
			}

		}

		return null;

	}

	/*This method counts the total nodes in the subtree. It does this by returning the sum of all of the left nodes, 
	 * all of the right nodes, and the current node.*/
	public int countNodes() {

		return 1 + leftChild.countNodes() + rightChild.countNodes();

	}

	/*This method finds out what level from the current node the character is. It does this by checking to make sure 
	 * the depth of the left child is at least 0. If so, then the depth of the left child plus one is returned. It then 
	 * checks to make sure the depth of the right child is at least 0. If so, then the depth of the right child plus 
	 * one is returned. Otherwise, -1 is returned.*/
	public int findDepth(Character c) {

		if(leftChild.findDepth(c) >= 0){
			return 1 + leftChild.findDepth(c);
		}
		else if(rightChild.findDepth(c) >= 0){
			return 1 + rightChild.findDepth(c);
		}

		return -1;

	}

	public String toString() {
		return display("");
	}

	public String display(String indentation) {
		return indentation + "Left: \n" + leftChild.display(indentation + "  ") + 
				"\n" + indentation + "Right: \n" + rightChild.display(indentation + "  ");
	}
}
