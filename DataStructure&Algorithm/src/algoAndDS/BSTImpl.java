package algoAndDS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BSTImpl implements BinarySearchTree<Integer> {
	
	private Node root;
    private static int nItem = 1;
	
	

	@SuppressWarnings("rawtypes")
	public void inOrder(Node localRoot) {
		if(localRoot == null)
			return;
		else {
			inOrder(localRoot.left);
			System.out.println(localRoot.id);
			inOrder(localRoot.right);
		}
		
	}

	public void print() {
		inOrder(root);
	}
	
	private int size(Node x) {
		if(x == null)
			return 0;
		return size(x.left) + size(x.right) + 1;
	}
	
	public int size() {
		return size(root);
	}

	public boolean insert(int id) {
		Node newNode = new Node();
		newNode.id = id;
		if(root == null) {
			root = newNode;
			return true;
		}else {
			Node current = root;
			Node parent;
			while(true) {
				parent = current;
				if(id < current.id) {
					current = current.left;
					if(current == null) {
						parent.left = newNode;
						return true;
					}
				}else {
					current = current.right;
					if(current == null) {
						parent.right = newNode;
						return true;
					}
				}
			}
		}
	}
	
	public int Items() {
		return nItem;
	}
	
	private Node findMax(Node x) {
		if(x == null)
			return null;
		return findMax(x.right);
	}
	
	public Node findMax() {
		return findMax(root);
	}
	
	
	

	public String find(String searchItem) {
		// TODO Auto-generated method stub
		return null;
	}

	private int ceiling(Node x, int id) {
		if(x == null)
			return -1;
		if(x.id == id)
			return x.id;
		if(x.id < id)
			return ceiling(x.right, id);
		int ceil = ceiling(x.left, id);
		if(ceil >= id) 
			return ceil;
		else
			return x.id;

		
	}
	
	public int ceiling(int id) {
		int x = ceiling(root, id);
		if(x == -1) return -1;
		return x;
	}
	
	private boolean valide(Node x, int id, boolean min) {
		if(x != null) {
			if(min) {
				if(x.id >= id) {
					return false;
				}
				
				return valide(x.left, id, min) && valide(x.right, id, min);
			}else {
				if(x.id <= id) {
					return false;
				}
				return valide(x.right, id, min) && valide(x.left, id, min);
			}
			
				
		}
		return true;
	}
	
	public boolean valideBST() {
		if(root == null)
			return false;
		return valide(root.left, root.id, true) && valide(root.right, root.id, false);
		
		
	}
	
	
	private void sumAllOfBranches(Node x, int runningSum, List<Integer> sums) {
		if(x == null)
			return;
		int total = runningSum + x.id;
		if(x.left == null && x.right == null)
			sums.add(total);
		sumAllOfBranches(x.left, total, sums);
		sumAllOfBranches(x.right, total, sums);
		
	}
	
	private List<Integer> calculate(Node x) {
		List<Integer> sums = new ArrayList<Integer>();
		if(x == null)
			return null;
		sumAllOfBranches(x, 0, sums);
		return sums;
	}
	
	public List<Integer> finalSum() {
		return calculate(root);
	}
	
	private int floor(Node x, int id) {
		if(x == null)
			return Integer.MAX_VALUE;
		if(x.id > id)
			return floor(x.left, id);
		int floor = floor(x.right, id);
		if(floor <= id)
			return floor;
		else
			return x.id;
	}
	
	public int floor(int id) {
		return floor(root, id);
	}
	
	private List<Integer> inOrderTraverse(Node x, List<Integer> array) {
		if(x == null)
			return null;
		inOrderTraverse(x.left, array);
		array.add(x.id);
		inOrderTraverse(x.right, array);
		return array;
		
	}
	
	public List<Integer> inOrderTraverse(List<Integer> array) {
		return inOrderTraverse(root, array);
	}
	
	
	private int countLeavesNode(Node x) {
		if(x == null)
			return -1;
		else if(x.left == null && x.right == null)
			return 1;
		return countLeavesNode(x.left) + countLeavesNode(x.right);
	}
	
	
	public int countLeavesNode() {
		return countLeavesNode(root);
	}
	
	private int nodeDepths(Node x, int depth) {
		if(x == null)
			return 0;
		else if(x.left == null && x.right == null)
			return depth;
		return nodeDepths(x.left, depth + 1) + nodeDepths(x.right, depth + 1);
	}
	
	public int nodeDepths(int depth) {
		return nodeDepths(root, depth);
	}
	
	
	private void invertBinaryTree(Node x) {
		ArrayDeque<Node> ad = new ArrayDeque<Node>();
		ad.addLast(x);
		while(ad.size() > 0) {
			Node tree = ad.pollLast();
			swap(tree);
			if(x.left != null)
				ad.addLast(tree.left);
			if(x.right != null)
				ad.addLast(tree.right);
		}
	}
	
	public void invert() {
		invertBinaryTree(root);
	}
	
	public void swap(Node x) {
		Node temp = x.left;
		x.left = x.right;
		x.right = temp;
	}
	
	
	private Node findSuccessor(Node tree, Node node) {
		if(tree.left != null)
			return findMin(node);
		Node current = node.parent;
		while(current != null && node == current.right) {
			node = current;
			current = current.parent;
		}
		return current;
	}
	
	public Node findSuccessor(Node node) {
		return findSuccessor(root, node);
	}
	
	private Node findMin(Node x) {
		if(x == null)
			return null;
		return findMin(x.left);
					
	}
	
	public void findMin() {
		findMin(root);
	}
	
	private int binarySearchDiameter(Node x) {
 		int left = 0, right = 0;
 		if(x.left != null)
 			left = size2(x.left);
 		if(x.right != null)
 			right = size2(x.right);
 		
		
		if(left < right)
			return right;
		else
			return left;
	}
	
	
	
	public int binarySearchDiameter() {
		return binarySearchDiameter(root);
	}
	 
	
	
	private int size2(Node x) {
		if(x == null)
			return -1;
		return size(x);
	}
	
	private void InOrderTraverse(Node x, List<Integer> sortedNodeValues) {
		if(x == null) 
			return;
		InOrderTraverse(x.left, sortedNodeValues);
		sortedNodeValues.add(x.id);
		InOrderTraverse(x.right, sortedNodeValues);
	}
	
	public int findKthLargestValueInBst(int k) {
		ArrayList<Integer> sortedNodeValues = new ArrayList<Integer>();
		InOrderTraverse(root, sortedNodeValues);
		return sortedNodeValues.get(sortedNodeValues.size() - 1);
	}
	
	
	public boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		if(arrayOne == null || arrayTwo == null)
			return false;
		if((InOrder(root, arrayOne).equals(InOrder(root, arrayTwo))) && arrayOne.size() == arrayTwo.size())
			return true;
		return false;
	}
	
	public List<Integer> InOrder(Node tree, List<Integer> array) {
		if(tree == null)
			return null;
		InOrder(tree.left, array);
		array.add(tree.id);
		InOrder(tree.right, array);
		Collections.sort(array);
		return array;
  }
	
	public List<Integer> InOrder(List<Integer> array) {
		List<Integer> result = InOrder(root, array);
		return result;
	}
	
	
	private int sumOfTwoMaxPathsInBinaryTree(Node x, int runningSum, List<Integer> sums) {
		if(x == null)
			return -1;
		int total = runningSum + x.id;
		if(x.left == null && x.right == null) {
			sums.add(total);
			return sums.get(0);
		}
		return sumOfTwoMaxPathsInBinaryTree(x.left, total, sums) + sumOfTwoMaxPathsInBinaryTree(x.right, total, sums);
			
	}
	
	public int sumOfTwoPathsBinaryTree() {
		List<Integer> list = new ArrayList<Integer>();
		return sumOfTwoMaxPathsInBinaryTree(root, 0, list);
	}
	
	
	
	
	
	
	
	
}

