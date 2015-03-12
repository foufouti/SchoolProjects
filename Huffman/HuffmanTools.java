import java.util.*;
import java.util.Map.Entry;

/*This class builds the Huffman tree, and does all of the encoding and decoding as well.*/
public class HuffmanTools {

	/*This method builds the Huffman Tree. It does this by first looping through the given iterator and if the 
	 * map that keeps track of the frequency for each character (freq) does not contain the current character, then the 
	 * character will be added to freq and the value will be set to 1. If freq does contain thcurrent character, then 
	 * the value of the key the character is at will be incremented. After freq is finished being created, then the map
	 * will then be turned into a list (listOfTrees) made of Huffman leaves. The method will then loop through 
	 * listOfTrees while the size is greater than 1. If the size of the list that holds the leaves with the two lowest 
	 * frequencies(twoLowestFreqs) is equal to 0, then the method will loop through listOfTrees and find the leaf with 
	 * the lowest frequency. Once that leaf is found, that leaf will be removed from the listOfTrees an added to 
	 * twoLowestFreqs. If the size of twoLowestFreqs is equal to 1, then the method will loop through listOfTrees and 
	 * find the leaf with the lowest frequency. Once that leaf is found, that leaf will be removed from the listOfTrees 
	 * an added to twoLowestFreqs. If the size of twoLowestFreqs is equal to 2, then an internal node will be made with 
	 * the leaves being the leaves in twoLowestFreqs. This newly made tree will then be added to listOfTrees and 
	 * twoLowestFreqs will be cleared. In the end, the final tree will be returned.*/
	public static HuffmanTree buildHuffmanTree(Iterator<Character> symbols) {
		Map<Character, Integer> freq = new HashMap<Character, Integer>();
		List<HuffmanTree> listOfTrees = new ArrayList<HuffmanTree>();
		List<HuffmanTree> twoLowestFreqs = new ArrayList<HuffmanTree>(); 
		HuffmanTree tree;
		HuffmanTree lowestLeaf = null;
		HuffmanTree lowestLeaf2 = null;
		int minFreq = 1000000000;
		int minFreq2 = 1000000000;
		Character nextSymbol;

		while(symbols.hasNext()){

			nextSymbol = symbols.next();
			if(!freq.containsKey(nextSymbol)){
				freq.put(nextSymbol, 1);
			}

			else if(freq.containsKey(nextSymbol)){
				freq.put(nextSymbol, freq.get(nextSymbol)+1);
			}

		}

		for(Character charac : freq.keySet()){
			listOfTrees.add(new HuffmanLeaf(charac, freq.get(charac)));
		}

		while(listOfTrees.size() > 1){

			if(twoLowestFreqs.size() == 0){
				for(HuffmanTree currLeaf : listOfTrees){
					if(currLeaf.getFrequency() < minFreq){
						minFreq = currLeaf.getFrequency();
						lowestLeaf = currLeaf;

					}
				}

				minFreq = 1000000000;
				listOfTrees.remove(lowestLeaf);
				twoLowestFreqs.add(lowestLeaf);


			}

			if(twoLowestFreqs.size() == 1){
				for(HuffmanTree currLeaf : listOfTrees){
					if(currLeaf.getFrequency() < minFreq2){
						minFreq2 = currLeaf.getFrequency();
						lowestLeaf2 = currLeaf;
					}
				}

				minFreq2 = 1000000000;
				listOfTrees.remove(lowestLeaf2);
				twoLowestFreqs.add(lowestLeaf2);

			}

			if(twoLowestFreqs.size() == 2){
				tree = new HuffmanInternalNode(twoLowestFreqs.get(0), twoLowestFreqs.get(1));
				listOfTrees.add(tree);
				twoLowestFreqs.clear();

			}

		}

		return listOfTrees.get(0);

	}	

	/*This method returns a string of 0s and 1s that represents the location of the symbol in the tree. It does this 
	 * by looping through the iterator given in the parameter. FindCode is then called to concantenate the code 
	 * together. The current character is then removed from the iterator. In the end, the final concantenation is 
	 * returned.*/
	public static String encode(HuffmanTree tree, Iterator<Character> symbols) {
		Character nextSymbol;
		String concan = "";

		while(symbols.hasNext()){

			nextSymbol = symbols.next();
			concan += tree.findCode(nextSymbol);
			symbols.remove();

		}

		return concan;

	}

	/*This method returns the character that is represented by a given string of 0s and 1s. It does this by looping 
	 * through the iterator given in the parameter. Decode is then called and concantenated to the current string. The 
	 * final String is returned in the end.*/
	public static String decode(HuffmanTree tree, Iterator<Character> bits) {
		String concan = "";

		while(bits.hasNext()){

			concan += tree.decode(bits);

		}

		return concan;

	}
}
