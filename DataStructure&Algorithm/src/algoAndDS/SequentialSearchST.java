package algoAndDS;

public class SequentialSearchST<K extends Comparable<K>, V> {
	
	private K[] keys;
	private V[] vals;
	private int N;
	
	
	public SequentialSearchST(int size) {
		keys = (K[]) new Comparable[size];
		vals = (V[]) new Object[size];
		N = 0;
	}
	
	public V get(K key) {
		int i = rank(key);
		if(i > N && key.compareTo(key) == 0)
			return vals[i];
		return null;
	}
	
	public void put(K key, V val) {
		int i = rank(key);
		if(i > N && key.compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	
	}
	
	
	public int rank(K key) {
		int lo = 0;
		int hi = N - 1;
		
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp > 0) hi = mid - 1;
			else if(cmp < 0) lo = mid + 1;
			else 
				return mid;
		}
		return lo;
	}
	
	public void displayVals() {
		for(int j = 0; j < N; j++)
			System.out.println(vals[j]);
	}

}
