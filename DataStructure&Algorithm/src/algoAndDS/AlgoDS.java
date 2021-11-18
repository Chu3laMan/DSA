package algoAndDS;

import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import algoAndDS.BST.Node;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.URL;
import java.security.*;

public class AlgoDS {
	
//	private static final String NON_NORMAL_CHARACTERS_PATTER = "!@#$%^&*()_-+={]}[";
	private static final String NON_NORMAL_CHARACTERS_PATTERN = "!@#$%^&*()_-+={]}[";
	
	

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
		
		int[] arr = {0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 61, 71, 73};
		System.out.println(Arrays.toString(rangeOfDuplicateNum(arr, 45)));

		
			   
		
		

       /*
       boolean [][]vis = new boolean[4][4];
       
       TraverseMatrixDFS matrix = new TraverseMatrixDFS();
       matrix.BFS(grid, vis,0, 0);
       */
       
		
		
		/*
		Class[] classes = getClassesInPackage("algoAndDS");
		for(Class c : classes)
			System.out.println(c.getSimpleName().concat(".class"));
		*/
		
		
	   /*
       int h = data.length;
		
		if(h == 0) {
			return;
		}
		
		int l = data[0].length;
		
		boolean[][] visited = new boolean[h][l];
		
		Queue<String> q = new LinkedList<String>();
		
		q.add(0 + "," + 0);
		
		while(q.size() > 0) {
			
			String x = q.remove();
			int row = Integer.parseInt(x.split(",")[0]);
			int col = Integer.parseInt(x.split(",")[1]);
			if(row < 0 || col < 0 || row >= h || col >= l || visited[row][col])  {
				continue;
			}
			
			if(data[row][col] == 1) {
				visited[row][col] = true;
				q.add((row-1) + "," + col); //up
				q.add((row+1) + "," + col); //down
				q.add(row + "," +(col+1)); //right;
				q.add(row + "," + (col-1)); //left
				
			}else
				System.out.println(q.size());
			break;
			
			
		}
		
		
		*/
		
		
	}
	
	public static int[] rangeOfDuplicateNum(int[] array, int target) {
		int lowerBound = 0;
		int upperBound = array.length - 1;
		
		while(lowerBound <=  upperBound) {
			if(array[lowerBound] == target)
				continue;
			if(array[upperBound] == target)
				return new int[] {lowerBound, upperBound};
			
			lowerBound++;
			upperBound--;
		}
		 return new int[] {-1, -1};
		
	}
	
	
	
	public static int[] removeDuplicateElement(int[] array) {
        if(array == null) {
            return null;

        }
        List<Integer> arrayList = IntStream.of(array).boxed().collect(Collectors.toList());

        return arrayList.stream().mapToInt(Integer::intValue).distinct().toArray();

    }
	
	public static void DFS(int[][] grid) {
		int h = grid.length;
        if (h == 0)
            return;
        int l = grid[0].length;

        boolean[][] visited = new boolean[h][l];

        Stack<String> stack = new Stack<>();

        stack.push(0 + "," + 0);

        System.out.println("Depth-First Traversal: ");
        while (stack.empty() == false) {

            String x = stack.pop();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);

            if(row<0 || col<0 || row>=h || col>=l || visited[row][col])
                continue;

            visited[row][col]=true;
            if(grid[row][col] == 5) {
            	System.out.print(grid[row][col] +  "");
            	stack.push(row + "," + (col-1)); //go left
                stack.push(row + "," + (col+1)); //go right
                stack.push((row-1) + "," + col); //go up
                stack.push((row+1) + "," + col); //go down
            }
        }
		
				
	}
	
	public static String findLongestWord(String sen) {
		String[] split = sen.split(" ");
		int max = 0;
		String r = "";
		for(int j = 0; j < split.length; j++) {
			if(split[j].length() > max) {
				max = split[j].length();
				r = split[j];
			}
		}
		return r;
	}
	
	public static String removeCharacters(String str) {
		return str.replaceAll("[^a-zA-Z0-9]", " ");
	}
	
	public static String runLengthEncoding(String str) {
		// stores output string
        String encoding = "";
 
        // base case
        if (str == null) {
            return encoding;
        }
 
        int count;
 
        for (int i = 0; i < str.length(); i++)
        {
            // count occurrences of character at index `i`
            count = 1;
            while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1))
            {
                count++;
                i++;
            }
 
            // append current character and its count to the result
            encoding += String.valueOf(count) + str.charAt(i);
        }
 
        return encoding;
	}
	
	
	
	public static int forward(List<Integer> list, int target, int k) {
		int upper = list.size() - 1;
		int lower = 0;
		int result = 0;
		if(list.get(upper) == target)
			result = list.get(0 + k);
		else if(list.get(lower) == target)
			result = list.get(0 + k);
		else {
			for(int j = 0; j < list.size(); j++)
				if(list.get(j) == target)
					result = list.get(j + k);
		}
		
		return result;
			
	}
	
	public static int backward(List<Integer> list, int target, int k) {
		int upper = list.size() - 1;
		int lower = 0;
		int result = 0;
		if(list.get(upper) == target)
			result = list.get(list.size() - k);
		else if(list.get(lower) == target)
			result = list.get((list.size() - 1) - k);
		else {
			for(int j = list.size()-1; j > 0; j--)
				if(list.get(j) == target)
					result = list.get(j - k);
		}
		return result;
		
		
	}
	
	public static Class[] getClassesInPackage(String pckgname) {
        File directory = getPackageDirectory(pckgname);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Could not get directory resource for package " + pckgname + ".");
        }
 
        return getClassesInPackage(pckgname, directory);
    }
 
    private static Class[] getClassesInPackage(String pckgname, File directory) {
        List<Class> classes = new ArrayList<Class>();
        for (String filename : directory.list()) {
            if (filename.endsWith(".class")) {
                String classname = buildClassname(pckgname, filename);
                try {
                    classes.add(Class.forName(classname));
                } catch (ClassNotFoundException e) {
                    System.err.println("Error creating class " + classname);
                }
            }
        }
        return classes.toArray(new Class[classes.size()]);
    }
 
    private static String buildClassname(String pckgname, String filename) {
        return pckgname + '.' + filename.replace(".class", "");
    }
 
    private static File getPackageDirectory(String pckgname) {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new IllegalStateException("Can't get class loader.");
        }
 
        URL resource = cld.getResource(pckgname.replace('.', '/'));
        if (resource == null) {
            throw new RuntimeException("Package " + pckgname + " not found on classpath.");
        }
 
        return new File(resource.getFile());
    }
	
	public static int sumOfTwoLists(List<Integer> list) {
		int sum = 0;
		for(int i : list)
			sum += i;
		return sum;
	}

	
	static void superStack(String[] operations) {
        if(operations == null || operations.length == 0) {
            System.out.println("EMPTY");
            return;
        }
        
        LinkedList<Object> list = new LinkedList<Object>();
        for(int i = 0; i < operations.length; i++) {
            String current = operations[i];
            if(current.equals("pop")) {
                list.removeLast();
            }else {
                if(current.startsWith("push")) {
                    list.addFirst(Integer.parseInt(current.split(" ")[1]));
                }else {
                    int e = Integer.parseInt(current.split(" ")[1]);
                    int k = Integer.parseInt(current.split(" ")[2]);
                    ListIterator<Object> iterator = list.listIterator();
                    int j = 1;
                    while(iterator.hasNext()) {
                        if (j > e) {
                            break;
                        }
                        iterator.set(iterator.next());
                    }
                }
            }
        }
        
        if(list.isEmpty()) {
            System.out.println("EMPTY");
            
        }else {
            System.out.println(list.getLast());
        }
        

    }
	static void superStack2(String[] operations) {
        List<Integer> stack = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (String s : operations) {
            if (s.startsWith("push")) {
                int value = Integer.parseInt(s.split(" ")[1]);
                stack.add(value);
            } else if (s.startsWith("pop")) {
                stack.remove(stack.size() - 1);
                //modify the hashmap
                for(Integer i: map.keySet()) {
                    if (stack.size() < i) {
                        map.put(i-1, map.get(i));
                        map.remove(i);
                    }
                }
            } else if (s.startsWith("inc")) {
                String[] splits = s.split(" ");
                int e = Integer.parseInt(splits[1]);
                int value = Integer.parseInt(splits[2]);
                map.put(e, map.getOrDefault(e, 0) + value);
            }

            if (stack.size() == 0) {
                System.out.println("EMPTY");
            } else {
                int value = stack.get(stack.size()-1);
                for(Integer i: map.keySet()) {
                    if (stack.size() <= i) {
                        value+=map.get(i);
                    }
                }
                System.out.println(value);
            }
        }
    }
	
	public static void print(int[][] mat){
	         for(int i=0;i<mat.length;i++){
	             for(int j=0;j<mat.length;j++){
	                 System.out.print(mat[i][j]+ " ");
	             }
	             System.out.println();
	         }
	             
		}
	
	
	
	 
	
	public static void BFS(int[][] data) {
		int h = data.length;
		
		if(h == 0) {
			return;
		}
		
		int l = data[0].length;
		
		boolean[][] visited = new boolean[h][l];
		
		Queue<String> q = new LinkedList<String>();
		
		q.add(0 + "," + 0);
		
		while(q.size() > 0) {
			String x = q.remove();
			int row = Integer.parseInt(x.split(",")[0]);
			int col = Integer.parseInt(x.split(",")[1]);
			
			if(row < 0 || col < 0 || row >= h || col >= l || visited[row][col])  {
				continue;
			}
			
			visited[row][col] = true;
			System.out.print(data[row][col] + " ");
			q.add((row-1) + "," + col); //up
			q.add((row+1) + "," + col); //down
			q.add(row + "," +(col+1)); //right;
			q.add(row + "," + (col-1)); //left;
			
			
		} 
		
	}
	
	public static String matrixPath(int[][] data) {
		
		int[][] result = null;
		
		if(data.length == 0 || data == null) {
			return "NOT IMPOSSIBLE";
		}
		
		result = new int[data.length][data.length];
		data[0][0] = 1;
		for (int i = 0; i < data.length; i++) {
			if (data[i][0] != 0) {
				result[i][0] = 1;
			}
		}
		
		for (int j = 0; j < data.length; j++) {
			if (data[0][j] != 0) {
				result[0][j] = 1;
			}
		}
		
		for (int i = 1; i < data.length; i++) {
			for (int j = 1; j < data.length; j++) {
				if (data[i][j] == 1)
					result[i][j] = result[i - 1][j] + result[i][j - 1];
			}
		}
		
		
		return String.valueOf(result[data.length - 1][data.length - 1]);
		
		
	}
	
	
	public static String MissingDigit(String str) {
		String[] toArr = str.split("");
		int pos = -1;
		if(toArr[1].equals("x")) 
			pos = 0;
		else if(toArr[4].equals("x"))
			pos = 4;
		else if(toArr[8].equals("x"))
			pos = 8;
		double x = 0;
		if(pos == 0) {
			String a, b, c;
			b = toArr[5];
			c = toArr[9];
			a = toArr[0];
			
			if(toArr[3].equals("+"))
				x = (Integer.parseInt(c) - Integer.parseInt(b)) / Integer.parseInt(a);
			if(toArr[3].equals("-"))
				x = (Integer.parseInt(c) + Integer.parseInt(b)) / Integer.parseInt(a);
			if(toArr[3].equals("*"))
				x = Integer.parseInt(c) / (Integer.parseInt(a) * Integer.parseInt(b));
			if(toArr[3].equals("/"))
				x = (Integer.parseInt(c) * Integer.parseInt(b)) / Integer.parseInt(a);
			
		}
		
		if(pos == 8) {
			String a, b, c;
			a = toArr[0];
			b = toArr[4];
			if(toArr[2].equals("+"))
				x = Integer.parseInt(a) + Integer.parseInt(b);
			if(toArr[2].equals("-"))
				x = Integer.parseInt(a) - Integer.parseInt(b);
			if(toArr[2].equals("*"))
				x = Integer.parseInt(a) * Integer.parseInt(b);
			if(toArr[2].equals("/")) {
				if(Integer.parseInt(b) == 0)
					throw new ArithmeticException("Couldn't divide on 0.");
				x = Integer.parseInt(a) / Integer.parseInt(b);
			}
		}
		return String.valueOf(x);
	}
	
	
	
	
	
	public static String hashAndSaltPassword(String password) throws NoSuchAlgorithmException, NoSuchProviderException {
		String salt = getSalt2();
		return hashPassword(password + salt);
		
	}
	
	
	private static String getSalt2() throws NoSuchAlgorithmException, NoSuchProviderException
	    {
	        //Always use a SecureRandom generator
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	        //Create array for salt
	        byte[] salt = new byte[32];
	        //Get a random salt
	        sr.nextBytes(salt);
	        //return salt
	        return Base64.getEncoder().encodeToString(salt);
	   }
    
	
	public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {  
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
	 
	 private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	    {
	        byte[] bytes = new byte[hex.length() / 2];
	        for(int i = 0; i<bytes.length ;i++)
	        {
	            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	        }
	        return bytes;
	    }
	
	
	
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.reset();
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		StringBuilder sb = new StringBuilder(mdArray.length * 2);
		for(byte b : mdArray) {
			int v = b & 0xff;
			if(v < 16)
				sb.append('0');
			sb.append(Integer.toHexString(v));
		}
		
		
		
		return sb.toString();
		
	}
	
	
	public static String hashPasswordVersion2(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return toHex(salt) + toHex(hash);
	}
	
	public static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  + paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
	}

	public static byte[]  getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
		
	}
	
	public static double ElemnsFromSet(Set<Double> ts) {
		
		Double[] array = new Double[ts.size()];
		
		int i = 0;
		for(Double d : ts) 
			array[i++] = d;
		
		if(array.length % 2 == 1)
			return array[array.length / 2];
		else
			return (array[array.length / 2 - 1] + array[array.length / 2]) / 2;
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		Set<Double> ts = new TreeSet<Double>();
		for(int i = 0; i < nums1.length; i++) {
			ts.add((double) nums1[i]);
			for(int j = 0; j < nums2.length; j++) {
				ts.add((double) nums2[j]);
			}
		}
		
		return (ElemnsFromSet(ts));
	}
	
	
	
	public static int lengthOfLongestSubstring(String s) {
		int res = 0;
		int len = s.length();
		for(int i = 0; i < len; i++) {
			for(int j = i; j < len; j++) {
				if(checkRept(s, i, j))
					res = Math.max(res, i -  j + 1);
			}
		}
		return res;
		
	}
	
	public static boolean checkRept(String s, int start, int end) {
		int[] chars = new int[127];
		for(int j = start; j < end; j++) {
			char c = s.charAt(j);
			chars[c]++;
			if(chars[c] > 1)
				return false;
		}
		return true;
	}
	
	public static int[] twoSum(int[] array, int target) {
		for(int i = 0; i < array.length; i++) {
			for(int j = i + 1; j < array.length; j++) {
				if((array[i] + array[j]) == target)
					return new int[] {i, j};
			}
		}
		
		return new int[] {0};
	}
	
	
	
	
	
	public static int[] removeDuplicate(int[] array) {
		
		for(int i = 0; i < array.length-1; i++) {
			for(int j = i + 1; j < array.length-1; j++) {
				if(array[i] != array[j])
					return new int[] {array[i], array[j]};
			}
 		}
				
		return null;
	}
	

	
	
	public static <T extends Comparable<T>> int partition(T[] array, int lo, int hi) {
		T pivot = array[hi];
		
		int i = (lo - 1);
		
		for(int j = lo; j < hi; j++) {
			if(array[j].compareTo(pivot) <= 0) {
				i++;
				T temp = (T) array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		
		T temp = (T) array[i+1];
		array[i+1] = array[hi];
		array[hi] = temp;
		
		
		return (i+1);
	}
	
	public static <T extends Comparable<T>> void quickSort(T[] array, int lo, int hi) {
		if(hi < lo)
			return;
		int pivot = partition(array, lo, hi);
		quickSort(array, lo, pivot - 1);
		quickSort(array, pivot+1, hi);
	}

	

}
