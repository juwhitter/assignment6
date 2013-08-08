package assignment6;
/*
 * Julian Whitteron
 * Braeden Bodily
 */
import java.util.ArrayList;
import java.util.TreeSet;

public class TimeBST 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int timesToLoop = 15;
		int itemsToAdd = 900;
		int incrementOfN = 100;
		int numberOfPointsToPlot = 101;

		
		long startTime;
		long midTime;
		long endTime;
		
		// Initiate a new integer BST and treeset
		BinarySearchTree<Integer> bstTest = new BinarySearchTree<Integer>();
		TreeSet<Integer> treeTest = new TreeSet<Integer>();
		// ArrayList so we can randomize the BST
		ArrayList<Integer> randomTest;
		
		// Timing experiment for filling in our Binary Search Tree in sorted order
		System.out.println("Size N of the BST   " + "add()sorted         " + "contains()sorted       " + "add()random     " + "contains()random       " + "add()TreeSet      " + "contains()TreeSet      ");
		
		// Let the system settle for a moment
		try 
		{
			Thread.sleep(5000);
		} catch (InterruptedException e) {e.printStackTrace();}
		// Give it more time to settle and initiate
		for (int i = 0; i < 2000; i++)
			bstTest.add(i);
		bstTest.clear();
		
		// Start with size N and increment each test by incrementOfN until we reach the desired number of tests
		for (int k = itemsToAdd; k <= itemsToAdd + (incrementOfN * (numberOfPointsToPlot - 1)); k = k + incrementOfN)
		{
			// ADD IN SORTED ORDER
			// Clear the BST to start a new test.
			bstTest.clear();
			// Do each test with size N timesToLoop times
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++)
			{
				for (int j = 0; j < k; j++)
				{
					bstTest.add(j);
				}
			}
			midTime = System.nanoTime();
			// Time the for loops
			for (int i = 0; i < timesToLoop; i++)
				for (int j = 0; j < k; j++);
			endTime = System.nanoTime();
			// Average time per add
			long averageTimeAddSorted = ((midTime - startTime - (endTime - midTime)) / timesToLoop) / k;
			
			
			
			// CONTAINS ON BST THAT WAS ADDED IN SORTED ORDER
			// Test contains() method for each integer that is in test after filling it
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++)
			{				
				for (int j = 0; j < k; j++)
				{
					bstTest.contains(j);
				}
			}
			midTime = System.nanoTime();
			// Time the for loops
			for (int i = 0; i < timesToLoop; i++)
				for (int j = 0; j < k; j++)
			endTime = System.nanoTime();
			// Average time per contain
			long averageTimeContainsSorted = ((midTime - startTime - (endTime - midTime)) / timesToLoop) / k;
			
			
			
			// ADD IN RANDOM ORDER
			randomTest = bstTest.toArrayList();  // This random array can be used repeatedly for each of the following tests
			// Randomize the newly created arraylist
			for (int i = 0; i < randomTest.size(); i++)	
				swap(randomTest, i, (int)(Math.random() * randomTest.size()));
			
			// Clear test
			bstTest.clear();
			
			// Test time to add in a random added
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++)
			{				
				for (int j = 0; j < k; j++)
				{
					bstTest.add(randomTest.get(j));  // Add the random arraylist to the test BST
				}
			}
			midTime = System.nanoTime();
			// Time the for loops
			for (int i = 0; i < timesToLoop; i++)
				for (int j = 0; j < k; j++)
			endTime = System.nanoTime();
			// Average time per random add
			long averageTimeAddRandom = ((midTime - startTime - (endTime - midTime)) / timesToLoop) / k;
			
			
			// Test time to contain in a random added
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++)
			{				
				for (int j = 0; j < k; j++)
				{
					bstTest.contains(j);  // Add the random arraylist to the test BST
				}
			}
			midTime = System.nanoTime();
			// Time the for loops
			for (int i = 0; i < timesToLoop; i++)
				for (int j = 0; j < k; j++)
			endTime = System.nanoTime();
			// Average time per random add
			long averageTimeContainsRandom = ((midTime - startTime - (endTime - midTime)) / timesToLoop) / k;
			
			
			
			// TREESET TIMING
			// Test time for adding in a TreeSet
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++)
			{
				for (int j = 0; j < k; j++)
				{
					treeTest.add(randomTest.get(j));  // Add them in random order
				}
			}
			midTime = System.nanoTime();
			// Time the for loops
			for (int i = 0; i < timesToLoop; i++)
				for (int j = 0; j < k; j++);
			endTime = System.nanoTime();
			// Average time per add
			long averageTimeAddTreeSet = ((midTime - startTime - (endTime - midTime)) / timesToLoop) / k;
			
			// Test time for contains in a TreeSet
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++)
			{				
				for (int j = 0; j < k; j++)
				{
					treeTest.contains(j);  
				}
			}
			midTime = System.nanoTime();
			// Time the for loops
			for (int i = 0; i < timesToLoop; i++)
				for (int j = 0; j < k; j++)
			endTime = System.nanoTime();
			// Average time per random add
			long averageTimeContainsTreeSet = ((midTime - startTime - (endTime - midTime)) / timesToLoop) / k;
			
			
			
			System.out.println(k + "                     " + averageTimeAddSorted + "               " + averageTimeContainsSorted + "                 " + averageTimeAddRandom + "              " + averageTimeContainsRandom + "                   " + averageTimeAddTreeSet + "                 " + averageTimeContainsTreeSet);
			
			
			
		}
		
		
	}
	
	/**
	 * Swap two index in an ArrayList
	 * @param arr ArrayList<T>
	 * @param index1 int
	 * @param index2 int
	 */
	public static <T extends Comparable<? super T>>  void swap (ArrayList<T> arr,int index1, int index2)
	{
		T temp = arr.get(index1);
		arr.set(index1,arr.get(index2));
		arr.set(index2,temp);
	}

}
