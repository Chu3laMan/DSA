package algoAndDS;

public class BST<Key extends Comparable<Key>, Value> {
	
	private Node root;
	
	public class Node {
		
		private Node left;
		private Node right;
		private Key key;
		private Value val;
		private int N;
		
		
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
		
	}
	
	private int size(Node x) {
		if(x == null)
			return 0;
		else 
			return x.N;
	}
	
	public int size() {
		return size(root);
	}
	
	
	private Value get(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return get(x.left, key);
		else if(cmp > 0) return get(x.right, key);
		else
			return x.val;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Node put(Node x, Key key, Value val) {
		if(x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(x.left, key, val);
		else if(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val); 
	}
	
	private Node findMax(Node x) {
		if(x == null)
			return null;
		return findMax(x.right);
	}
	
	public Node findMax() {
		return findMax(root);
	}
	
	public void findMin() {
		findMin(root);
	}
	
	private Node findMin(Node x) {
		if(x == null)
			return null;
		return findMin(x.left);
	}
	
	private Node deleteMin(Node x) {
		if(x == null)
			return null;
		return x.left = deleteMin(x.left);
	}
	
	//delete() method using the Hibbard way
	public Node delete(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = delete(x.left, key);
		else if(cmp > 0) x.right = delete(x.right, key);
		else {
			if(x.left == null) return x.left;
			if(x.right == null) return x.right;
			Node t = x; //save the node to be deleted in t, in this case is x
			x = findMin(t.right); //set x to point to its successor findMin(t.right)
			x.right = deleteMin(t.right); //set the right link of x to deleteMin(t.right)
			x.left = t.left; //update the link of x to t.left
			
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	private void print(Node x) {
		if(x == null)
			return;
		print(x.left);
		System.out.print(x.key + " ");
		System.out.println(x.val);
		print(x.right);
	}
	
	public void print() {
		print(root);
	}
	
	private int maxDepth(Node x) {
		if(x == null)
			return 0;
		else {
			int leftDepth = maxDepth(x.left);
			int rightDepth = maxDepth(x.right);
			if(leftDepth > rightDepth)
				return leftDepth + 1;
			else
				return rightDepth + 1;
					
		}
	}
	
	public int maxDepth() {
		return maxDepth(root);
	}
	
	//non-recursive of Min()
	public Node min() {
		Node current = root;
		Node last = null;
		while(current != null) {
			last = current;
			current = current.left;
		}
		return last;
	}
	
	//non-recursive of Max()
	public Node max() {
		Node current = root;
		Node first = root;
		while(current != null) {
			first = current;
			current = current.right;
		}
		return first;
	}
	
	
	public int rank(Key key, Node x) {
		if(x == null) 
			return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return rank(key, x.left);
		else if(cmp > 0) return 1 + size(x.left) + size(x.right) + rank(key, x.right);
		else
			return size(x.left);
	}
	
	private Node floor(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		else if(cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if(t != null) return t;
		else 
			return x;
			
	}
	
	public Value floor(Key key) {
		Node x = floor(root, key);
		if(x == null) return null;
		return x.val;
	}
	
	private Node ceiling(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		else if(cmp < 0) return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if(t != null)
			return t;
		else 
			return x;
	}
	
	public Value celing(Key key) {
		Node x = ceiling(root, key);
		if(x == null) return null;
		return x.val;
	}
	
	
	
	
	
}
