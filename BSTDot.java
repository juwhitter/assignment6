package assignment6;

import java.util.ArrayList;

public class BSTDot 
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();
		

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
		test.add(89);
		test.add(42);
		test.add(27);
		test.add(3);
		test.add(14);
		test.add(104);
		
		ArrayList<Integer> contain = new ArrayList<Integer>();
		contain.add(90);
		contain.add(104);
		contain.add(740);
		
		test.removeAll(contain);

		test.writeDot("binarydot.dot");
	}

	
}

