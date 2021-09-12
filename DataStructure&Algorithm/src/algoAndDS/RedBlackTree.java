package algoAndDS;

public class RedBlackTree<K extends Comparable<K>, V> {
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private static final int COUNT = 10;
	
	class Node {
		public K key;
		public V val;
		Node left, right;
		int N;
		boolean color;
		
		public Node(K key, V val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x) {
		if(x == null)
			return false;
		return x.color = RED;
	}
	
	public int height() {
		return height(root);
	}
	
	
	
	private int height(Node x) {
		if(x == null)
			return 0;
		return Math.max(height(x.left), height(x.right)) + 1;
	}
	
	public Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	public Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	public V get(K key) {
		return get(root, key);
	}
	
	
	private V get(Node x, K key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return get(x.left, key);
		else if(cmp > 0) return get(x.right, key);
		else 
			return x.val;
	}
	
	private int size(Node x) {
		if(x == null)
			return 0;
		return x.N;
	}
	
	public void flipColors(Node h) {
		h.color = RED;
		h.right.color = BLACK;
		h.left.color = BLACK;
	}
	
	private Node put(Node h, K key, V val) {
		if(h == null)
			return new Node(key, val, 1, BLACK);
		int cmp = key.compareTo(h.key);
		if(cmp < 0) h.left = put(h.left, key, val);
		else if(cmp > 0) h.right = put(h.right, key, val);
		else 
			h.val = val;
		if(isRed(h.right) && !isRed(h.left)) 
			h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) 
			h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) 
			flipColors(h);
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	public void put(K key, V val) {
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	public void print() {
		inOrder(root);
	}
	
	private void inOrder(Node x) {
		if(x == null)
			return;
		inOrder(x.left);
		System.out.print(x.key + " " + x.color);
		System.out.println(" " + x.val);
		inOrder(x.right);
	}
	
	public V getRoot() {
		return root.val;
	}
	
	private void printLeft(Node x) {
		if(x == null)
			return;
		printLeft(x.left);
		System.out.print(x.val);
	}
	
	public void Left() {
		printLeft(root);
	}
	
	private void printRight(Node x) {
		if(x == null)
			return;
		printRight(x.right);
		System.out.print(x.val);
	}
	
	public void Right() {
		printRight(root);
	}
	
	private int leftHeight(Node x) {
		if(x == null)
			return 0;
		return leftHeight(x.left) + 1;
	}
	
	public int leftHeight() {
		return leftHeight(root);
	}
	
	private int rightHeight(Node x) {
		if(x == null)
			return 0;
		return rightHeight(x.right) + 1;
	}
	
	public int rightHeight() {
		return rightHeight(root);
	}
	
	public int totalHeight() {
		return leftHeight(root) + rightHeight(root);
	}

}
