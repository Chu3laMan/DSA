package algoAndDS;

public interface HashTable<T> {
	
	public int hashFunc(int key);
	public int hashFunc2(int key);
	public T insert(T item);
	public User find(String item);
	public void displayTable();

}
