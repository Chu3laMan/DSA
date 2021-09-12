package algoAndDS;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	
	public Key[] keys;
	public Value[] vals;
	public int nElems;
	
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Comparable[capacity];
	}
	
	public int size() {
		return nElems;
	}
	
	
	public Value get(Key key) {
		int i = rank(key);
		if(i < nElems && key.compareTo(key) == 0) 
			return vals[i];
		return null;
	}
	
	public void put(Key key, Value val) {
		int i = rank(key);
		if(i < nElems && key.compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		for(int j = nElems; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		nElems++;
	}
	
	
	public int rank(Key key) {
		int lo = 0;
		int hi = nElems - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		return lo;
		
	}
	
	
	
	

}
