package assignment6;
/*
 * Julian Whitteron
 * Braeden Bodily
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>
{
	BinaryNode root;
	
	public BinarySearchTree()
	{
		//Sets the root to null as well as its left and right references, which creates an empty tree.
		root = null;
	}
	
	@Override
	public boolean add(Type item) 
	{
		BinaryNode current;
		
		if (root == null)
		{
			root = new BinaryNode(item);
			return true;
		}
		current = root;
		
		// Loop through the binary tree doing comparisons until we add the item or find an object equal to item
		while (true)
		{
			// If the item is less than the node, move to the left
			if ((item.compareTo(current.data) < 0) && (current.left != null))
				current = current.left;
			
			// If it is less than, but left is null, add a new node with the item, return true
			else if ((item.compareTo(current.data) < 0) && (current.left == null))
				{
					current.left = new BinaryNode (item);
					return true;
				}
			
			// If the item is greater than the node, move to the right
			else if ((item.compareTo(current.data) > 0)&& (current.right != null))
				current = current.right;
			
			// If it is greater than, but right is null, add a new node with the item, return true
			else if ((item.compareTo(current.data) > 0)&& (current.right == null))
				{
					current.right = new BinaryNode(item);
					return true;
				}
			
			// Assuming duplicates are not allowed, so if it is already in the tree, return false
			else if (item.compareTo(current.data) == 0) 
				return false;
		}
	}
	@Override
	public boolean addAll(Collection<? extends Type> items) 
	{
		// Boolean whether anything was added or not
		boolean added = false;
		
		for (Type e:items)
		{
			// If even one item was added, set added to true
			if (this.add(e))
				added = true;
		}
			
		return added;
	}
	@Override
	public void clear()
	{
		root = null;
	}
	
	@Override
	public boolean contains(Type item) 
	{
		if (root == null)
			return false;
		
	
		 BinaryNode current = root;
		
		// Loop through the binary tree doing comparisons until we add the item or find an object equal to item
		while (true)
		{
			// If the item is less than the node, move to the left
			if ((item.compareTo(current.data) < 0) && (current.left != null))
				current = current.left;
			
			// If it is less than, but left is null, return false.
			else if ((item.compareTo(current.data) < 0) && (current.left == null))
				return false;
			
			// If the item is greater than the node, move to the right
			else if ((item.compareTo(current.data) > 0)&& (current.right != null))
				current = current.right;
			
			// If it is greater than, but right is null, return false
			else if ((item.compareTo(current.data) > 0)&& (current.right == null))
				return false;
			
			// If they are the same, return true.
			else if (item.compareTo(current.data) == 0) 
				return true;
		}
		
	}
	@Override
	public boolean containsAll(Collection<? extends Type> items) 
	{
		//Iterates through the collection checking until a false value is returned, otherwise it is assumed everything is uniform.
		for (Type e:items)
		{
			// If even one item was added, set added to true
			if (!this.contains(e))
				return false;
		}
		return true;
	}
	
	@Override
	public Type first() throws NoSuchElementException 
	{
		return root.getLeftmostNode().data;
	}
	
	
	@Override
	public boolean isEmpty() 
	{
		return (root == null);
	}
	
	@Override
	public Type last() throws NoSuchElementException 
	{
		
		return root.getRightmostNode().data;
	}
	
	@Override
	public boolean remove(Type item) 
	{
		// If there is no root than the BST is empty: return false
		if (root == null)
			return false;
		
		BinaryNode current = root;
		
		// If root is the item to be deleted is the root, and there are no .left or .right, then clear the BST
		if (item.compareTo(current.data) == 0 && root.left == null && root.right == null)
		{
			this.clear();
			return false;
		}
		
		// Loop through the binary tree doing comparisons until we find the successor and then sets the item to be removed to that and
		//deletes the successor.
		while (true)
		{		
			// If the item is less than the node, move to the left
			if ((item.compareTo(current.data) < 0) && (current.left != null))
			{
				// Check to see if the next item is the one to remove and is a leaf.  If so we will delete the connection from its parent
				if (item.compareTo(current.left.data) == 0 && current.left.left == null && current.left.right == null)
				{
					current.left = null;
					return true;
						
				}

				current = current.left;
			}
			
			// If it is less than, but left is null, return false.
			else if ((item.compareTo(current.data) < 0) && (current.left == null))
				return false;
			
			// If the item is greater than the node, move to the right
			else if ((item.compareTo(current.data) > 0) && (current.right != null))
			{
				// Check to see if the next item is the one to remove and is a leaf.  If so we will delete the connection from its parent
				if (item.compareTo(current.right.data) == 0 && current.right.left == null && current.right.right == null)
				{
					current.right = null;
					return true;					
				}
				
				current = current.right;
			}
			
			// If it is greater than, but right is null, return false
			else if ((item.compareTo(current.data) > 0) && (current.right == null))
				return false;
			
			// If they are the same, creates a node reference to the 'parent' of the successor, checks to see if the successor lies in
			//the left or right side of the tree, and then sets the node to be removed.
			else if (item.compareTo(current.data) == 0) 
			{
				BinaryNode successor = current.getSuccessor();
				current.data = successor.data;
				return true;	
			}
		}
	}
	@Override
	public boolean removeAll(Collection<? extends Type> items) 
	{
		// Boolean whether everything was removed or not
		boolean removed = false;
		
		for (Type e:items)
		{
			// If even one item was added, set added to true
			if (this.remove(e))
				removed = true;
		}
			
		return removed;
	
	}
	@Override
	public int size() 
	{
		// If the root is null, then the size is 0
		if (root == null)
			return 0;
		return 1 + sizeHelper(root);
	}
	/**
	 * Recursive method to assist in finding the size.
	 * @param n
	 * @return amount of nodes in the tree
	 */
	private int sizeHelper(BinaryNode n)
	{
		int left = 0;
		int right = 0;
		if ((n.left == null) && (n.right == null))
			return 0;
		if ((n.left != null))
			left = 1 + sizeHelper(n.left);
		if(n.right != null)
			right = 1 + sizeHelper(n.right);
		
		
		
		return (left+right);
	}
	@Override
	public ArrayList<Type> toArrayList() 
	{
		ArrayList<Type> output = new ArrayList<Type>();
		
		return toArrayHelper(root, output);
	}
	private ArrayList<Type> toArrayHelper(BinaryNode n, ArrayList<Type> list)
	{	
		if (n.left != null)	
			toArrayHelper(n.left, list);
		list.add(n.data);
		if (n.right != null)
			toArrayHelper(n.right, list);

		return list;		
	}
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
			// Checks the left node of the left node of the current node to see if it is null. So we can manipulate the parent node.
			if(getLeft() == null)
				return this; // returns "this" node

			return getLeft().getLeftmostNode();
		}

		/**
		 * @return The rightmost node in the binary tree rooted at this node.
		 */
		public BinaryNode getRightmostNode() 
		{
			// Checks the right node of the right node of the current node to see if it is null. So we can manipulate the parent node.
			if(getRight() == null)
				return this; // returns "this" node

			return getRight().getRightmostNode();
		}		
		
		public BinaryNode getLeftmostParentNode() 
		{			
//			if (this.getLeft() == null)
//			{
//				return null;
//			}
			// Checks the left node of the left node of the current node to see if it is null. So we can manipulate the parent node.
			if(getLeft().getLeft() == null)
				return this; // returns "this" node

//			if (getLeft().getLeftmostNode() == null)
//			{
//				return this;
//			}
			return getLeft().getLeftmostParentNode();
		}

		/**
		 * @return The rightmost node in the binary tree rooted at this node.
		 */
		public BinaryNode getRightmostParentNode() 
		{
//			if (this.getRight() == null)
//			{
//				return null;
//			}
			// Checks the right node of the right node of the current node to see if it is null. So we can manipulate the parent node.
			if(getRight().getRight() == null)
				return this; // returns "this" node

//			if (getRight().getRightmostNode() == null)
//			{
//				return this;
//			}
			return getRight().getRightmostParentNode();
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
			if (this.right != null)
			{
				if (this.right.left == null)
				{
					BinaryNode returnNode = this.right;
					this.right = this.right.right;
					return returnNode;
				}
				
				BinaryNode temp = this.right.getLeftmostParentNode();
				BinaryNode returnNode = temp.left;
				temp.left = returnNode.right;
				return returnNode;
			}
			
			if (this.left != null)
			{
				if (this.left.right == null)
				{
					BinaryNode returnNode = this.left;
					this.left = this.left.left;
					return returnNode;
				}
				
				BinaryNode temp = this.left.getRightmostParentNode();
				BinaryNode returnNode = temp.right;
				temp.right = returnNode.left;
				return returnNode;
			}
			
			else return null;

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
			if (getLeft() == null && getRight() == null)
				return 0;
			
			return 1 + Math.max(getLeft().height(),getRight().height());
		
		}
	}	
	
	public void writeDot(String filename)
	{
		try 
		{
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));
			
			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");
			
			if(root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		}
		catch(Exception e){e.printStackTrace();}
	}

	
	// Recursive method for writing the tree to  a dot file
	private void writeDotRecursive(BinaryNode n, PrintWriter output) throws Exception
	{
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if(n.left != null)
		{
			// write the left subtree
			writeDotRecursive(n.left, output);
			
			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D" );
		}
		if(n.right != null)
		{
			// write the left subtree
			writeDotRecursive(n.right, output);
			
			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D" );
		}		
	}
}

