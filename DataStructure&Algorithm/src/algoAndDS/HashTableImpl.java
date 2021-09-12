package algoAndDS;

public class HashTableImpl implements HashTable<User> {
	
	private User hashArray[];
	private int arraySize = 10;
	
	public HashTableImpl() {
		hashArray = new User[arraySize];
	}

	public int hashFunc(int key) {
		return key % arraySize;
	}

	public int hashFunc2(int key) {
		return 5 - key % 5;
	}

	
	public User insert(User item) {
		int hashVal = hashFunc(item.hashCode());
		int stepSize = hashFunc(hashVal);
		while(hashArray[hashVal] != null) {
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return hashArray[hashVal] = (User) item;
		
	}

	

	public User find(String item) {
		int hashVal = hashFunc(item.hashCode());
		int stepSize = hashFunc2(hashVal);
		while(hashArray[hashVal] != null) {
			if(hashArray[hashVal].equals(item))
				return hashArray[hashVal];
			hashVal += stepSize;
			hashVal %= arraySize;
		}
		return null;
	}

	public void displayTable() {
		for(int j = 0; j < 10; j++)
			if(hashArray[j] != null)
				System.out.print(hashArray[j] + " ");
		System.out.println();
		
	}
	
	
	
	

}
