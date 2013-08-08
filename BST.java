package assignment6;

/**
 * NOTE: THIS IS NOT THE RIGHT CLASS HEADER FOR ASSIGNMENT 6! THIS IS A SIMPLIFIED TREE JUST FOR LAB 6
 * 
 * @author Paymon Saebi
 *
 * @param <Type> Patch holder for a generic binary search tree 
 */ 
public class BST<Type> 
{
	public static void main (String[] args)
	 {
		 // Use this main if you need to test out the binary node methods during the lab.
		System.out.println("Done!");
	 }
	
	/*
	 * The very incomplete code for the tree itself
	 * Most of the interesting code in this file is in the private BinaryNode class.
	 * For assignment 8, you will create a much more complete tree class
	 */
	private BinaryNode root;
	
	public BST()
	{
		root = null;
	}
	
	// ****************************
	
	// And many more BST methods...	
	 
	// ****************************
	
	// YOU CAN USE THE PRIVATE CLASS BELOW AS YOUR NODE FOR ASSIGNMENT 6
	/**
	 * Represents a general binary tree node. Each binary node contains
	 * data, a left child, and a right child, and a parent.
	 * 
	 * This would make a good node class for a BinarySearchTree implementation
	 * 
	 */
	private class BinaryNode 
	{
		// Since the outer BST class declares <Type>, we can use it here without redeclaring it for BinaryNode
		Type data;

		BinaryNode left;

		BinaryNode right;

		// Adding a parent reference breaks the definition of a tree,
		// but some people find it makes BST management easier.
		// If you choose to use them, remember to update parent pointers
		// when adding/removing nodes!
		BinaryNode parent;

		/**
		 * Construct a new node with known children
		 * 
		 */
		public BinaryNode(Type _data, BinaryNode _left, BinaryNode _right) 
		{
			data = _data;
			left = _left;
			right = _right;
		}

		/**
		 * Construct a new node with no children
		 * 
		 */
		public BinaryNode(Type _data) 
		{
			this(_data, null, null);
		}

		/**
		 * Construct a new node with a known parent
		 * 
		 */
		public BinaryNode(Type _data, BinaryNode _parent)
		{
			this(_data, null, null);
			parent = _parent;
		}

		/**
		 * Getter method.
		 * 
		 * @return the node data.
		 */
		public Type getData() 
		{
			return data;
		}

		/**
		 * Setter method.
		 * 
		 * @param _data
		 *          - the node data to be set.
		 */
		public void setData(Type _data) 
		{
			data = _data;
		}

		/**
		 * Getter method.
		 * 
		 * @return the left child node.
		 */
		public BinaryNode getLeft() 
		{
			return left;
		}

		/**
		 * Setter method.
		 * 
		 * @param _left
		 *          - the left child node to be set.
		 */
		public void setLeft(BinaryNode _left) 
		{
			left = _left;
		}

		/**
		 * Getter method.
		 * 
		 * @return the right child node.
		 */
		public BinaryNode getRight() 
		{
			return right;
		}

		/**
		 * Setter method.
		 * 
		 * @param _right
		 *          - the right child node to be set.
		 */
		public void setRight(BinaryNode _right) 
		{
			right = _right;
		}

		/**
		 * Getter method.
		 * 
		 * @return the parent of this node.
		 */
		public BinaryNode getParent()
		{
			return parent;
		}

		/**
		 * Setter method.
		 * 
		 * @param _parent
		 *          - the parent node to be set.
		 */
		public void setParent(BinaryNode _parent)
		{
			parent = _parent;
		}

		/**
		 * Number of children
		 * Use this to help figure out which BST deletion case to perform
		 * 
		 * @return The number of children of this node
		 */
		public int numChildren()
		{
			int numChildren = 0;
			
			if(getLeft() != null)
				numChildren++;
			if(getRight() != null)
				numChildren++;
			
			return numChildren;
		}

		/**
		 * @return The leftmost node in the binary tree rooted at this node.
		 */
		public BinaryNode getLeftmostNode() 
		{
			// Base case, done for you
			if(getLeft() == null)
				return this; // returns "this" node

			BinaryNode temp = getLeft();
			
			while (temp.getLeft() != null)
			{
				temp = temp.getLeft();
			}
			
			return temp;
		}

		/**
		 * @return The rightmost node in the binary tree rooted at this node.
		 */
		public BinaryNode getRightmostNode() 
		{
			// Base case, done for you
			if(getRight() == null)
				return this; // returns "this" node

			BinaryNode temp = getRight();
			
			while (temp.getRight() != null)
			{
				temp = temp.getRight();
			}
			
			return temp;
		}		

		/** 
		 * This method applies to binary search trees only (not general binary trees).
		 * 
		 * @return The successor of this node.
		 *  
		 * The successor is a node which can replace this node in a case-3 BST deletion.
		 * It is either the smallest node in the right subtree,
		 * or the largest node in the left subtree.
		 */
		public BinaryNode getSuccessor() 
		{			
			return getRight().getLeftmostNode();
		}
		
		/**
		 * @return The height of the binary tree rooted at this node. The height of a
		 * tree is the length of the longest path to a leaf node. Consider a tree with
		 * a single node to have a height of zero.
		 *  
		 * The height of a tree with more than one node is the greater of its two subtrees'
		 * heights, plus 1
		 */
		public int height() 
		{			
			int heightright = 0;
			int heightleft = 0;
			
			// If the left and right are not null, then count the height of those subtrees
			if (getRight() != null)
			{
				heightright = 1;
				getRight().height();
			}
			if (getLeft() != null)
			{
				heightleft = 1;
				getLeft().height();
			}
			
			// If the right subtree is greater then the left, return the right subtree + 1, otherwise do
			// the same with the left subtree.
			if (getRight().height() > getLeft().height())
				return heightright + getRight().height();
			
			return heightleft + getLeft().height();
		}
	}	
}
