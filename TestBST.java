package assignment6;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class TestBST extends TestCase 
{
	BinarySearchTree<Integer> test;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		test = new BinarySearchTree<Integer>();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}
	
	public void testAdd()
	{
		test.add(1);
		// Test add new elements as well as duplicate elements.
		assertEquals(false, test.add(1));  // duplicate
		assertEquals(true, test.add(5));
		test.add(10);
		test.add(7);
		test.add(8);
		assertEquals(false, test.add(7));
		assertEquals(true, test.add(9));
	}
	
	public void testAddAll()
	{
		ArrayList<Integer> contain = new ArrayList<Integer>();
		
		// populate contain
		for (int i = 0; i < 30; i++)
			contain.add(i);
		
		assertEquals(true, test.addAll(contain));
		assertEquals(false, test.addAll(contain));  // duplicate
		
		// populate contain
		for (int i = 30; i < 40; i++)
			contain.add(i);
		
		assertEquals(true, test.addAll(contain));  // only part of the list was added, so should return true
		
		// test adding an empty collection
		contain.clear();
		assertEquals(false, test.addAll(contain));
	}
	
	public void testClear()
	{
		ArrayList<Integer> contain = new ArrayList<Integer>();
		
		// populate contain
		for (int i = 0; i < 30; i++)
			contain.add(i);
		
		// fill the BST
		test.addAll(contain);
		
		// clear the search tree and make sure the root is null
		test.clear();
		assertEquals(null, test.root);
		assertEquals(false, test.contains(5));  //  false, since it was cleared.
	}
	
	public void testContain()
	{
		ArrayList<Integer> contain = new ArrayList<Integer>();
		
		// populate contain
		for (int i = 0; i < 30; i++)
			contain.add(i);
		
		// fill the BST
		test.addAll(contain);
		
		// contains()
		assertEquals(true, test.contains(4));
		assertEquals(true, test.contains(5));
		assertEquals(true, test.contains(6));
		assertEquals(true, test.contains(7));
		assertEquals(true, test.contains(3));
		assertEquals(false, test.contains(44));
		assertEquals(false, test.contains(64));
		assertEquals(false, test.contains(74));
		
		// containsAll()
		assertEquals(true, test.containsAll(contain));
		contain.clear();
		contain.add(5);
		contain.add(8);
		contain.add(13);
		assertEquals(true, test.containsAll(contain));
		contain.add(55);
		assertEquals(false, test.containsAll(contain));				
	}
	
	public void testFirst()
	{
		ArrayList<Integer> contain = new ArrayList<Integer>();
		
		// populate contain
		for (int i = 0; i < 30; i++)
			contain.add(i);
		
		// fill the BST
		test.addAll(contain);
		assertEquals(0, (int) test.first());
		
		test.add(-4);
		assertEquals(-4, (int) test.first());
		test.add(-3);
		assertEquals(-4, (int) test.first());
		test.add(-100);
		assertEquals(-100, (int) test.first());
		test.add(-99);
		test.add(-87);
		assertEquals(-100, (int) test.first());
	}
	
	public void testIsEmpty()
	{
		assertEquals(true, test.isEmpty());
		
		// fill BST
		for (int i = 0; i < 30; i++)
			test.add(i);
		
		assertEquals(false, test.isEmpty());
		test.clear();
		assertEquals(true, test.isEmpty());		
	}
	
	public void testLast()
	{
		assertEquals(true, test.isEmpty());
		
		// fill BST
		for (int i = 0; i < 30; i++)
			test.add(i);
		
		assertEquals(29, (int) test.last());
		test.add(-5);
		assertEquals(29, (int) test.last());
		test.add(40);
		assertEquals(40, (int) test.last());
		test.add(35);
		assertEquals(40, (int) test.last());
		test.add(38);
		assertEquals(40, (int) test.last());
		test.add(50);
		assertEquals(50, (int) test.last());
	}
	
	public void testRemove()
	{
		// fill BST
		test.add(50);
		test.add(25);
		test.add(75);
		test.add(13);
		test.add(37);
		test.add(60);
		test.add(76);
		test.add(0);
		test.add(52);
		test.add(46);
		test.add(90);
		test.add(740);
		test.add(90);
		test.add(29);
		test.add(51);
		test.add(33);
		test.add(90);
		
		// test remove()
		assertEquals(true, test.remove(50));
		assertEquals(false, test.contains(50));
		assertEquals(true, test.remove(75));
		assertEquals(false, test.contains(75));
		assertEquals(false, test.remove(40));
		assertEquals(false, test.remove(40));
		assertEquals(false, test.remove(1349));
		
		// test removeAll()
		ArrayList<Integer> contain = new ArrayList<Integer>();
//		contain.add(19);
//		contain.add(25);
//		contain.add(13);
//		assertEquals(true, test.removeAll(contain));
//		assertEquals(false, test.removeAll(contain));  // already removed
//		assertEquals(false, test.contains(13));
//		assertEquals(false, test.contains(19));
		
		// fill BST
		test.add(50);
		test.add(25);
		test.add(75);
		test.add(13);
		test.add(37);
		test.add(60);
		test.add(76);
		test.add(0);
		test.add(52);
		test.add(46);
		test.add(90);
		test.add(740);
		test.add(90);
		test.add(29);
		test.add(51);
		test.add(33);
		test.add(90);
		
		contain.add(50); // Add items to contain that are not in BST
		contain.add(76);
		
		assertEquals(true, test.removeAll(contain));
		assertEquals(false, test.removeAll(contain)); // already deleted so false
		
		contain.clear();
		
		assertEquals(false, test.removeAll(contain));
	}
	
	public void testSize()
	{
		// fill BST and test size as we add and remove items
		test.add(50);
		assertEquals(1, test.size());
		test.add(25);
		test.add(75);
		test.add(13);
		test.add(37);
		test.add(60);
		test.add(76);
		test.add(0);
		assertEquals(8, test.size());
		test.add(52);
		test.add(46);
		test.add(90);
		test.add(740);
		assertEquals(12, test.size());
		test.remove(740);
		assertEquals(11, test.size());
		test.add(29);
		test.add(51);
		test.add(33);
		assertEquals(14, test.size());
	}
	
	public void testToArrayList()
	{
		
		for(int i = 1; i <= 11; i++)
			test.add(i);
		
		test.add(-4);
		test.add(-3);
		test.add(-2);
		
		ArrayList<Integer> compare = new ArrayList<Integer>();
		compare.add(-4);
		compare.add(-3);
		compare.add(-2);
		for(int i = 1; i <= 11; i++)
			compare.add(i);
		
		assertEquals(compare, test.toArrayList());
		
		// create a permutated BST and compare it to an ordered ArrayList
		// fill the compared (correct) one
		compare.clear();
		for(int i = 0; i < 100; i++)
			compare.add(i);
		
		// fill testarray
		ArrayList<Integer> testarray = new ArrayList<Integer>();
		for(int i = 0; i < 100; i++)
			testarray.add(i);
		
		// permute testarray
		for(int i = 1; i < 100; i++)
			swap(testarray, i, (int)(Math.random() * 100));
		// add the permuted list to the BST
		test.clear();
		test.addAll(testarray);
		
		assertEquals(compare, test.toArrayList());	
	}
	
	
	
	public static <T extends Comparable<? super T>>  void swap (ArrayList<T> arr,int index1, int index2)
	{
		T temp = arr.get(index1);
		arr.set(index1,arr.get(index2));
		arr.set(index2,temp);
	}
}
