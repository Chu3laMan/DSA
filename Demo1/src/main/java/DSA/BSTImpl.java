package DSA;

import domain.User;

public class BSTImpl implements BST<User> {
	
	private Node root;
	private static int nItem = 1;
	
	
	@Override
	public boolean insert(int id, User user) {
		Node newNode = new Node();
		newNode.id = id;
		newNode.user = user;
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
	@Override
	public String find(String searchItem) {
		Node current = root;
		while(!searchItem.equals(current.user.getLastName())) {
			if(current.left != null)
				current = current.left;
			else if(current.right != null)
				current = current.right;
		}
		return current.user.toString();
	}
	@Override
	public void inOrder(Node localNode) {
		if(localNode == null)
			return;
		else {
			inOrder(localNode.left);
			System.out.println(localNode.user);
			inOrder(localNode.right);
		}
	}
	
	
	@Override
	public int nItems() {
		return nItem;
	}
	
	
	@Override
	public int size(Node localNode) {
		if(localNode == null)
			return 0;
		return size(localNode.left) + size(localNode.right) + 1;
	}
	
	public int height() {
		return size(root);
	}
	
	public void print() {
		inOrder(root);
	}

	

}
