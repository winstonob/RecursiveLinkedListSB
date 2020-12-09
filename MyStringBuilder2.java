// CS 0445 Fall 2020
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those in MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration (i.e. no loops) is allowed in this implementation. 

// For more details on the general functionality of most of these methods,
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder2 initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
		if (s != null && s.length() > 0)
			makeBuilder(s, 0);
		else // no String so initialize empty MyStringBuilder2
		{
			length = 0;
			firstC = null;
			lastC = null;
		}

	}

	// Method provided by Dr. Ramirez
	// TODO: Add method description
	private void makeBuilder(String s, int pos)
	{
		// Recursive case – we have not finished going through the String
		if (pos < s.length()-1)
		{
		// Note how this is done – we make the recursive call FIRST, then
		// add the node before it. In this way the LAST node we add is
		// the front node, and it enables us to avoid having to make a
		// special test for the front node. However, many of your
		// methods will proceed in the normal front to back way.
			makeBuilder(s, pos+1);
			firstC = new CNode(s.charAt(pos), firstC);
			length++;
		}
		else if (pos == s.length()-1) 	// Special case for last char in String
		{ 								// This is needed since lastC must be
										// set to point to this node
			firstC = new CNode(s.charAt(pos));
			lastC = firstC;
			length = 1;
		}
		else 				// This case should never be reached, due to the way the
							// constructor is set up. However, I included it as a
		{ 					// safeguard (in case some other method calls this one)
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if (s.length > 0) {
			makeBuilder(s, 0);
		}
		else
		{
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

	// Method provided by Dr. Ramirez
	// TODO: Add method description
	private void makeBuilder(char[] s, int pos)
	{
		// Recursive case – we have not finished going through the String
		if (pos < s.length -1)
		{
			// Note how this is done – we make the recursive call FIRST, then
			// add the node before it. In this way the LAST node we add is
			// the front node, and it enables us to avoid having to make a
			// special test for the front node. However, many of your
			// methods will proceed in the normal front to back way.
			makeBuilder(s, pos+1);
			firstC = new CNode(s[pos], firstC);
			length++;
		}
		else if (pos == s.length-1) 	// Special case for last char in String
		{ 								// This is needed since lastC must be
			// set to point to this node
			firstC = new CNode(s[pos]);
			lastC = firstC;
			length = 1;
		}
		else 				// This case should never be reached, due to the way the
		// constructor is set up. However, I included it as a
		{ 					// safeguard (in case some other method calls this one)
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

	// Copy constructor -- make a new MyStringBuilder2 from an old one.  Be sure
	// that you make new nodes for the copy.
	public MyStringBuilder2(MyStringBuilder2 old)
	{
		if(old != null && old.length > 0)
		{
			makeBuilder(old, 0, old.firstC);
		}
		else
		{
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

	// Method provided by Dr. Ramirez
	// TODO: Add method description
	private void makeBuilder(MyStringBuilder2 old, int pos, CNode curNode)
	{
		// Recursive case – we have not finished going through the String
		if (curNode != null)
		{
			// Note how this is done – we make the recursive call FIRST, then
			// add the node before it. In this way the LAST node we add is
			// the front node, and it enables us to avoid having to make a
			// special test for the front node. However, many of your
			// methods will proceed in the normal front to back way.
			makeBuilder(old, pos+1,  curNode.next);
			firstC = new CNode(curNode.data, firstC);
			length++;
		}
		else if (pos == old.length-1) 	// Special case for last char in String
		{ 								// This is needed since lastC must be
			// set to point to this node
			firstC = new CNode(curNode.data);
			lastC = firstC;
			length = 1;
		}
		else 				// This case should never be reached, due to the way the
							// constructor is set up. However, I included it as a
		{ 					// safeguard (in case some other method calls this one)
			length = 0;
			firstC = null;
			lastC = null;
		}
	}
	
	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
		length = 0;
		firstC = null;
		lastC = null;
	}

	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b) {
		if (length == 0) {
			firstC = new CNode(b.firstC.data);
			lastC = firstC;
			length = 1;
			appendRecursively(lastC, b.firstC.next);
		}
		else if(b.length > 0) {
			appendRecursively(lastC, b.firstC);
		}

		return this;
	}

	// TODO: Add method description
	private void appendRecursively(CNode curNode, CNode curNodeB) {
		if(curNodeB != null)
		{
			curNode.next = new CNode(curNodeB.data);
			lastC = curNode.next;
			length++;
			appendRecursively(curNode.next, curNodeB.next);
		}

	}

	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s) {
		if (length == 0) {
			firstC = new CNode(s.charAt(0));
			lastC = firstC;
			length = 1;
			appendRecursively(lastC, 1, s);
		}
		else if (s.length() > 0) {
			appendRecursively(lastC, 0, s);
		}

		return this;
	}

	// TODO: Add method description
	private void appendRecursively(CNode curNode, int pos, String s) {
		if(pos < s.length()) {
			curNode.next = new CNode(s.charAt(pos));
			lastC = curNode.next;
			length++;
			appendRecursively(curNode.next, pos + 1, s);
		}

	}

	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c) {
		if (length == 0) {
			firstC = new CNode(charAt(0));
			lastC = firstC;
			length = 1;
			appendRecursively(lastC, 1, c);

			return this;
		}
		appendRecursively(lastC, 0, c);

		return this;
	}

	private void appendRecursively(CNode curNode, int pos, char[] c) {
		if (pos < c.length) {
			curNode.next = new CNode(c[pos]);
			lastC = curNode.next;
			length++;
			appendRecursively(curNode.next, pos + 1, c);
		}
	}

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c) {
		if (length == 0) {
			firstC = new CNode(c);
			lastC = firstC;
			length = 1;
		}
		else {
			lastC.next = new CNode(c);
			lastC = lastC.next;
			length++;
		}

		return this;
	}


	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index < 0 || index > length - 1) {
			throw new IndexOutOfBoundsException("Provided index is out of bounds.");
		}

		return locateNodeRecursively(firstC, 0, index).data;
	}

	private CNode locateNodeRecursively(CNode n, int pos, int index) {
		if (pos < index) {
			return locateNodeRecursively(n.next, pos + 1, index);
		}
		else {
			return n;
		}
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end) {
		// Edge Case: Invalid Index.
		if(start < 0 || start >= end || start > length - 1) {
			return this;
		}

		int range;

		// Edge Case: End is greater than length.
		if (end > length) {
			lastC = locateNodeRecursively(firstC, 0, start - 1);
			lastC.next = null;
			return this;
		}
		// Edge Case: Start is at the beginning of MyStringBuilder2.
		if (start == 0) {
			firstC = locateNodeRecursively(firstC, 0, end);
		}
		// Standard Case.
		else {
			range = end - start;
			CNode beforeNode = locateNodeRecursively(firstC, 0, start - 1);
			CNode afterNode = locateNodeRecursively(beforeNode, 0, range + 1);
			beforeNode.next = afterNode;
		}
		range = end - start;
		length -= range;

		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index) {
		// Edge Case: Invalid Index.
		if(index < 0 || index > length - 1) {
			return this;
		}
		// Edge Case: Index is at the beginning of MyStringBuilder2.
		if(index == 0) {
			firstC = firstC.next;
			length--;

			return this;
		}
		// Edge Case: Index is at the end of MyStringBuilder 2.
		if(index == length - 1) {
			lastC = locateNodeRecursively(firstC, 0, length - 2);
			lastC.next = null;
		}
		// Standard Case.
		else {
			CNode beforeNode = locateNodeRecursively(firstC, 0, index - 1);
			CNode afterNode = locateNodeRecursively(beforeNode, 0, 2);
			beforeNode.next = afterNode;
		}
		length--;

		return this;
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str) {
		return locateIndexRecursively(firstC, 0, str);
	}

	private int locateIndexRecursively(CNode n, int index, String str) {
		int indexFound = -1;

		// Iterating through entire MyStringBuilder2
		if(n.next != null) {
			// Checks for matching string based
			indexFound = testForMatchRecursively(n, 0, index, str, -1);

			// Determines whether recursion through MyStringBuilder2 should continue or a match was found
			if(indexFound != -1) {
				return indexFound;
			}
			return locateIndexRecursively(n.next, index + 1, str);
		}

		// Returns -1 if the MyStringBuilder2 does not contain any matches
		return indexFound;
	}

	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str) {
		// Edge Case: Offset invalid.
		if(offset < 0 || offset > length) {
			return this;
		}
		// Edge Case: Offset is located at the end of MyStringBuilder2.
		if(offset == length) {
			append(str);
			return this;
		}

		CNode firstCharNode = new CNode(str.charAt(0));
		CNode lastCharNode = constructLListRecursively(firstCharNode, str, 1);

		// Edge Case: Offset is located at the start of MyStringBuilder2.
		if(offset == 0) {
			lastCharNode.next = firstC;
			firstC = firstCharNode;
		}
		// Standard Case.
		else {
			CNode beforeNode = locateNodeRecursively(firstC, 0, offset - 1);
			CNode afterNode = locateNodeRecursively(beforeNode, 0, 1);
			beforeNode.next = firstCharNode;
			lastCharNode.next = afterNode;
		}
		length += str.length();

		return this;
	}

	// Returns last node of constructed list.
	// TODO: Add Method Description
	private CNode constructLListRecursively(CNode n, String str, int pos) {
		if(pos < str.length()) {
			n.next = new CNode(str.charAt(pos));
			return constructLListRecursively(n.next, str, pos + 1);
		}
		return n;
	}

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c) {
		// Edge Case: Invalid Offset.
		if(offset < 0 || offset > length) {
			return this;
		}
		// Edge Case: Offset is located at the end of MyStringBuilder.
		if(offset == length) {
			append(c);
			return this;
		}

		CNode charNode = new CNode(c);

		// Edge Case: Offset is located at the start of MyStringBuilder.
		if(offset == 0) {
			charNode.next = firstC;
			firstC = charNode;
		}
		// Standard Case.
		else {
			CNode beforeNode = locateNodeRecursively(firstC, 0, offset - 1);
			CNode afterNode = locateNodeRecursively(beforeNode, 0, 1);
			beforeNode.next = charNode;
			charNode.next = afterNode;
		}
		length++;

		return this;
	}

	// Insert char array c into the current MyStringBuilder2 starting at index
	// index "offset" and return the current MyStringBuilder2.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c) {
		// Edge Case: Invalid Offset.
		if(offset < 0 || offset > length) {
			return this;
		}
		// Edge Case: Offset is located at the end of MyStringBuilder2.
		if(offset == length) {
			append(c);
			return this;
		}

		CNode firstCharNode = new CNode(c[0]);
		CNode lastCharNode = constructLListRecursively(firstCharNode, c, 1);

		// Edge Case: Offset is located at the start of MyStringBuilder2.
		if(offset == 0) {
			lastCharNode.next = firstC;
			firstC = firstCharNode;
		}
		// Standard Case.
		else {
			CNode beforeNode = locateNodeRecursively(firstC, 0, offset - 1);
			CNode afterNode = locateNodeRecursively(beforeNode, 0, 1);
			beforeNode.next = firstCharNode;
			lastCharNode.next = afterNode;
		}
		length += c.length;

		return this;
	}

	// TODO: Add method description
	private CNode constructLListRecursively(CNode n, char[] c, int pos) {
		if(pos < c.length) {
			n.next = new CNode(c[pos]);
			return constructLListRecursively(n.next, c, pos + 1);
		}
		return n;
	}

	// Return the length of the current MyStringBuilder2
	public int length() {
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str) {
		// Edge Case: Invalid start or end index
		if(start < 0 || start >= end || start > length) {
			return this;
		}

		CNode afterNode;
		CNode firstCharNode = new CNode(str.charAt(0));
		CNode lastCharNode = constructLListRecursively(firstCharNode, str, 1);

		// Edge Case: End index is greater than the size of MyStringBuilder2.
		if(end > length) {
			end = length;
			lastC = lastCharNode;
		}
		int range = end - start;

		// Edge Case: End index is located at the start of MyStringBuilder2.
		if(start == 0) {
			afterNode = locateNodeRecursively(firstC, 0, end);
			lastCharNode.next = afterNode;
			firstC = firstCharNode;
		}
		// Normal Case.
		else {
			CNode beforeNode = locateNodeRecursively(firstC, 0, start - 1);
			afterNode = locateNodeRecursively(beforeNode, 0, range + 1);
			beforeNode.next = firstCharNode;
			lastCharNode.next = afterNode;
		}
		length += str.length() - range;

		return this;
	}

	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse() {
		reverseRecursively(firstC, firstC.next, 0);
		return this;
	}

	// TODO: Add method description
	private void reverseRecursively(CNode beforeNode, CNode curNode, int pos) {

		// Iterating the provided linked list
		if(curNode.next != null) {
			reverseRecursively(beforeNode.next, curNode.next, pos + 1);
		}

		// Redefining the first and last node references
		if(pos == 0) {
			lastC = beforeNode;
			lastC.next = null;
		}
		else if (pos == length - 2) {
			firstC = curNode;
		}

		// Reversing pointer direction
		curNode.next = beforeNode;
	}
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end) {
		// Edge Case: Invalid start or end index.
		if(end <= start || start > length || end > length || start < 0) {
			return "";
		}

		int range = end - start;
		char[] substring = new char[range];
		CNode startNode = locateNodeRecursively(firstC, 0, start);
		toCharArrayRecursively(startNode, 0, range, substring);

		return new String(substring);
	}

	// TODO: Add method description
	private char[] toCharArrayRecursively(CNode n, int pos, int size, char[] c) {
		if(pos < size) {
			c[pos] = n.data;
			return toCharArrayRecursively(n.next, pos + 1, size, c);
		} else
		{
			return c;
		}
	}

	// Return the entire contents of the current MyStringBuilder2 as a String
	public String toString() {
		char [] c = new char[length];
		getString(c, 0, firstC);
		return (new String(c));
	}

	// TODO: Add method descriptiona
	private void getString(char [] c, int pos, CNode curr) {
		if (curr != null) // Not at end of the list
		{
			c[pos] = curr.data; // put next char into array
			getString(c, pos+1, curr.next); // recurse to next node and
		} // next pos in array
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str) {
		return locateLastIndexRecursively(firstC, 0, 0, str);
	}

	// TODO: Add method description
	private int locateLastIndexRecursively(CNode n,  int pos, int index, String str) {
		int returnIndex = -1;
		// Iterating through the entire array
		if(n.next != null) {
			returnIndex = locateLastIndexRecursively(n.next, pos, index + 1, str);
		}

		// Determines what value will be passed back.
		// This value will be the value of "returnIndex" for the next item on the stack
		if(returnIndex != -1) {
			return returnIndex;
		}
		return testForMatchRecursively(n, pos, index, str, -1);
	}

	// TODO: Add method description
	private int testForMatchRecursively(CNode n, int pos, int index, String str, int returnIndex) {
		// Check for a complete.
		if(pos == str.length()) {
			return index;
		}

		// Checking for the individual characters in the string.
		if(n != null) {
			if(pos < str.length() & n.data == str.charAt(pos))
				return testForMatchRecursively(n.next, pos + 1, index, str, returnIndex);
		}
		return returnIndex;
	}
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
	// data and next fields directly.
	private class CNode {
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}

