import junit.framework.TestCase;

public class PublicTests extends TestCase {

	public void testSimpleAdd() {
		MyHashSet<String> s = new MyHashSet<String>();
		s.add("hello");
		s.add("apple");
		
		assertEquals(4, s.getCapacity());
		assertEquals(2, s.getSize());
		
		
	}

	public void testReHash() {
		MyHashSet<String> s = new MyHashSet<String>();
		for (int i = 0; i < 1000; i++) {
			s.add("Entry " + i);
		}
		
		assertEquals(2048, s.getCapacity());
		assertEquals(1000, s.getSize());

	}

	public void testNoDuplicates() {
		MyHashSet<String> s = new MyHashSet<String>();
		for (int i = 0; i < 10; i++) {
			s.add("hello");
			s.add("apple");
			s.add("cat");
			s.add("last");
		}
		assertEquals(8, s.getCapacity());
		assertEquals(4, s.getSize());
		for(String a: s){
			System.out.println(a);
		}
	}
}
