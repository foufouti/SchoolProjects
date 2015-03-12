import java.util.ArrayList;
import java.util.List;

/*This class will search through a given document of Strings. Each document will be provided with a set of terms.
 *While the class searches through the document, it will find and record all of the snippets that contain all of the
 *terms. In the end the program will determine the shortest snippet and return the start position, end position, length, 
 *and the position of an appointed term.*/
public class MinimumSnippet {

	private Iterable<String> document;
	private List<String> terms;
	private ArrayList<Integer> indexPos;
	private int endPos = 0;
	private int startPos = 0;

	
	/*This is the constructor and it handles the main functions of the program. It starts off by defining the parameters 
	 * and setting an exception. The program will then check to see if all of the terms are contained in he document at 
	 * least once. Then, the method sets up variables that will be used throughout the class. Afterwards, a new 
	 * ArrayList is made containing all of the elements in the document. If the terms list is less than or equals to 2, 
	 * the program will then loop through the document and find the position of all the terms and add it to an 
	 * ArrayList.Then the program will loop through the position ArrayList and find the shortest length between 
	 * positions and define the start and end positions. If the size of the terms List is greater than 2, the program 
	 * will begin a while loop the document would then be looped through and once a snippet is found, the program will 
	 * determine if that is the smallest snippet. If the while loop is not done looping, then the looping will continue
	 * from the next term.*/
	public MinimumSnippet(Iterable<String> document, List<String> terms) {
		this.document = document;
		this.terms = terms;

		if (terms.isEmpty()) {
			throw new IllegalArgumentException();
		}

		if (foundAllTerms()) {
			ArrayList<Integer> position = new ArrayList<Integer>();
			ArrayList<String> documentAl = new ArrayList<String>();
			ArrayList<String> tempTerms = new ArrayList<String>();
			boolean doneLooping = false;
			int totalTimesRan = 0;
			int count = -1;
			int diffOfEndStartPos = 0;
			int minVal = 0;

			for (String s : document) {
				documentAl.add(s);
			}

			diffOfEndStartPos = getSizeOfDoc();

			tempTerms = fillTerms(tempTerms);

			if (terms.size() <= 2) {
				for (String s : document) {
					count++;

					if (terms.contains(s)) {
						position.add(count);
					}
				}

				indexPos = position;

				for (int index = 0; index < indexPos.size(); index++) {
					minVal = 0;
					endPos = indexPos.get(0);
					startPos = indexPos.get(0);
					if (index < 1) {
						continue;
					} else {
						minVal = indexPos.get(index) - indexPos.get(index - 1);
						diffOfEndStartPos = indexPos.get(index) - indexPos.get(index-1);

						if (minVal >= diffOfEndStartPos) {
							endPos = indexPos.get(index);
							startPos =indexPos.get(index - 1);
						}
					}
				}
			} else {
				while (!doneLooping) {
					for (int index2 = totalTimesRan; index2 < getSizeOfDoc(); index2++) {

						if (terms.contains(documentAl.get(index2))) {
							position.add(index2);
							tempTerms.remove(documentAl.get(index2));
						}

						if (tempTerms.size() == 0) {
							minVal = position.get(position.size() - 1) - position.get(0);

							if (minVal < diffOfEndStartPos) {
								endPos = position.get(position.size() - 1);
								startPos = position.get(0);
							}

							position.clear();
							tempTerms = fillTerms(tempTerms);

							if (index2 == getSizeOfDoc()) {
								position.clear();
							} else {

								for (int index = index2; index < getSizeOfDoc(); index++) {

									if (tempTerms.contains(documentAl.get(index))) {
										tempTerms.remove(documentAl.get(index2));

										if (tempTerms.size() == 0) {
											position.add(index2);
											tempTerms.remove(documentAl.get(index2));
										} else {
											doneLooping = true;
										}
									}
								}
							}

						}

					}

					totalTimesRan++;
					if (totalTimesRan == getSizeOfDoc()) {
						doneLooping = true;
					}

				}
			}
		}

	}
	
	/*This method loops through the entire document List and determines if all the elements from the terms 
	 * List are contained in the document. It does this by making Strings containing all of the elements in the 
	 * terms and document Lists. The method then loops through the terms String and counts each element that is 
	 * also in the document once all the terms are found the document returns true. Otherwise, the method will return 
	 * false.*/
	public boolean foundAllTerms() {

		String entireDoc = "";
		String allTerms = "";

		for (String s : document) {
			entireDoc += s;
		}

		for (String s : terms) {
			allTerms += s;
		}

		int termsSize = allTerms.length();
		int count = 0;

		for (int index = 0; index < termsSize; index++) {
			String currString = allTerms.substring(index, index + 1);
			if (entireDoc.contains(currString)) {
				count++;

				if (count == termsSize) {
					return true;
				}
			}
		}

		return false;
	}
	
	/*This method returns the starting position of the minimum snippet which is defined in the constructor.*/
	public int getStartingPos() {

		return startPos;

	}
	
	/*This method returns the ending position of the minimum snippet which is defined in the constructor.*/
	public int getEndingPos() {

		return endPos;
	}
	
	/*This method returns the length of the minimum snippet. It does this by looping through the minimum snippet and 
	 * counting each element. The method then returns the total number of elements counted.*/
	public int getLength() {
		int count = 0;
		for (int index = getStartingPos(); index <= getEndingPos(); index++) {
			count++;
		}

		return count;

	}
	
	/*This method takes the index of a certain position in the terms List and whatever element is in that position 
	 * is the element that will be searched for in the documents List. Once the element is found for the last 
	 * time, the position of that element in the document is returned.*/
	public int getPos(int index) {

		ArrayList<Integer> termsPos = new ArrayList<Integer>();
		int position = -1;

		for (String s : document) {
			position++;

			if (terms.get(index).equals(s)) {
				termsPos.add(position);
			}
		}

		return termsPos.get(termsPos.size() - 1);
	}

	/*This method makes a copy of the terms List. It does this by looping through all the terms and adding each element 
	 * to an empty ArrayList. The new ArrayList is returned.*/
	public ArrayList<String> fillTerms(ArrayList<String> arrayToFillUp) {

		for (String s : terms) {
			arrayToFillUp.add(s);
		}

		return arrayToFillUp;
	}

	/*This method returns the length of the document List. It does this by looping through the whole document and 
	 * counts each element. The method will then return the amount of total elements counted.*/
	public int getSizeOfDoc() {

		int counter = 0;

		for (String docTerm : document) {
			counter++;
		}

		return counter;
	}

}
