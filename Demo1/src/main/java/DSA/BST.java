package DSA;


import domain.User;

public interface BST<T> {
	
	public boolean insert(int id, User user);
	public String find(String searchItem);
	public void inOrder(Node localRoot);
	public int nItems();
	public int size(Node localNode);

}
