import junit.framework.TestCase;
import java.util.*;

public class PublicTests extends TestCase {
	public void testHuffmanTreeBuilder() {
		String input = "ABCA";
		Iterator<Character> it = InternalTools.getCharacterIteratorFromString(input);
		HuffmanTree tree = HuffmanTools.buildHuffmanTree(it);
		assertEquals(5, tree.countNodes());
		assertEquals(input.length(), tree.getFrequency());
		assertEquals(1, tree.findDepth(new Character('A')));
		assertEquals(2, tree.findDepth(new Character('B')));
		assertEquals(2, tree.findDepth(new Character('C')));
	}
	
	public void testEncoding() {
		String input = "ABCA";
		Iterator<Character> it = InternalTools.getCharacterIteratorFromString(input);
		HuffmanTree tree = HuffmanTools.buildHuffmanTree(it);
		it = InternalTools.getCharacterIteratorFromString("AAA");
		String code = HuffmanTools.encode(tree, it);
		assertTrue(code.equals("111") || code.equals("000"));
		it = InternalTools.getCharacterIteratorFromString("CCC");
		code = HuffmanTools.encode(tree, it);
		assertTrue(code.equals("010101") || code.equals("000000") ||
				   code.equals("101010") || code.equals("111111"));
	}
	
	public void testDecoding() {
		String input = "ABCA";
		Iterator<Character> it = InternalTools.getCharacterIteratorFromString(input);
		HuffmanTree tree = HuffmanTools.buildHuffmanTree(it);
		it = InternalTools.getCharacterIteratorFromString("0011");
		String message = HuffmanTools.decode(tree, it);
		assertTrue(message.equals("AAC") || message.equals("AAB") ||
				   message.equals("BAA") || message.equals("CAA"));
	}
}
