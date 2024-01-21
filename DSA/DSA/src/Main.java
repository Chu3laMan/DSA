import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Main {
    static int x = 10;

    public static void main(String[] args) {
//        System.out.println(isPythagorean(new int[] {3, 1, 4, 6, 5}));
//        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
//        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
//        System.out.println(majorityElement(new int[] {2, 2, 3, 4, 5, 2, 9, 2}));
//        System.out.println(longestConsecutiveSequence(new int[] {100, 4, 200, 1, 2, 3}));
//        System.out.println(Arrays.toString(maximumSumSubArray(new int[] {100, 4, 200, 1, 2, 3})));
//        System.out.println(maximumSubArray(nums));
//        System.out.println(maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//        String[] words = {"the","quick","brown","fox","jumps","over","the","lazy","dog"};
//        System.out.println(shortestDistance(words, "fox", "dog"));
//        System.out.println(numGoodPairs(new int[] {1, 1, 1, 1}));
//        System.out.println(Arrays.toString(targetSum(new int[] {1, 2, 3, 4, 6}, 6)));
//        List<Runnable> tasks = new ArrayList<Runnable>();
//        MultiExecutor multiExecutor = new MultiExecutor(tasks);
//        multiExecutor.executeAll();
        int[][] meetings = {{1, 4}, {1, 5}, {5, 6}, {6, 10}, {7, 9}};
        System.out.println("Minimum number of meeting rooms required: " + minMeetingRooms(meetings));
        
        printScore("ABABAA");



    }

    public static void printScore(String input) {
        int scoreA = 0;
        int scoreB = 0;
        for (char c : input.toCharArray()) {
            if (c == 'A') {
                scoreA += 15;
            } else if (c == 'B') {
                scoreB += 15;
            }
            System.out.println("Player A : " + scoreA + " Player B : " + scoreB);
            if (scoreA >= 40 && scoreA > scoreB + 10) {
                System.out.println("Player A wins the game");
                break;
            } else if (scoreB >= 40 && scoreB > scoreA + 10) {
                System.out.println("Player B wins the game");
                break;
            }
        }
    }

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Use a min-heap to track the minimum end time of merged intervals
        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>();

        // Start with the first meeting's end time
        endTimeQueue.add(intervals[0][1]);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            // If the current meeting starts after the earliest meeting ended, merge them
            if (intervals[i][0] >= endTimeQueue.peek()) {
                endTimeQueue.poll();
            }
            // Add the current meeting's end time to the queue
            endTimeQueue.add(intervals[i][1]);
        }

        // The size of the queue is the number of rooms required
        return endTimeQueue.size();
    }

//    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
//        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
//        list.sort(Map.Entry.comparingByValue());
//
//        Map<K, V> result = new LinkedHashMap<>();
//        for (Map.Entry<K, V> entry : list) {
//            result.put(entry.getKey(), entry.getValue());
//        }
//
//        return result;
//    }

    static void nonRepeatedWordV2(String str) {
        HashSet<String> hash = new HashSet<>();
        Stream.of(str.split(" ")).filter(n -> !hash.contains(n)).map(String::valueOf).forEach(System.out::println);
    }
    static private Person change(Object o) {
        Person p2 = (Person) o;
        p2.age = 25;
        return p2;
    }
    static class Person {
        Person(String s, int i) {
            ++pid;
            name = s;
            age = i;
        }
        int pid;
        int age;
        String name;
    }
    public static void badMethod() {
        throw new RuntimeException();
    }


    static double rolling_hash(String s) {
        final int p = 31;   // base
        final double m = 1e9 + 9; // large prime number
        double hash_value = 0;
        double hash_pow = 1;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hash_value = ((hash_value + (c - 'a' + 1) * hash_pow) % m);
            hash_pow = (hash_pow * p) % m;
        }
        return hash_value;
    }

    public int solution(String[] A) {
        int maxLength = 0;
        List<String> list = Arrays.stream(A).filter(s -> isDistrict(s))
                .collect(Collectors.toList());
        Map<String, Integer> map = new HashMap<>();
        maxLength = dfs(list, "", 0, maxLength, map);
        return maxLength;
    }

    static boolean isDistrict(String s) {
        char[] chars = s.toCharArray();
        if(chars != null) Arrays.sort(chars);
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == chars[i + 1]) return false;
        }
        return true;
    }

    public int dfs(List<String> a, String p, int i, int maxLength, Map<String, Integer> map) {
        if(map.get(p) != null) return map.get(p);
        boolean isPath = isDistrict(p);

        if(isPath && !a.contains(p)) {
            maxLength = Math.max(p.length(), maxLength);
        }

        if(i == a.size() && !isPath) {
            map.put(p, maxLength);
            return maxLength;
        }

        for(int j = i; j < a.size(); j++) {
            maxLength = dfs(a, p + a.get(i), j + 1, maxLength, map);
        }

        map.put(p, maxLength);
        return maxLength;

    }

    public static int longestStrChain(String[] words) {

        if(words == null || words.length == 0) return 0;
        int res = 0;
        Arrays.sort(words, (a,b)-> a.length()-b.length());  // Sort the words based on their lengths
        HashMap<String, Integer> map = new HashMap();       //Stores each word and length of its max chain.

        for(String w : words) {                             //From shortest word to longest word
            map.put(w, 1);                                  //Each word is atleast 1 chain long
            for(int i=0; i<w.length(); i++) {               //Form next word removing 1 char each time for each w
                StringBuilder sb = new StringBuilder(w);
                String next = sb.deleteCharAt(i).toString();
                if(map.containsKey(next) && map.get(next)+1 > map.get(w))
                    map.put(w, map.get(next)+1);            //If the new chain is longer, update the map
            }
            res = Math.max(res, map.get(w));                //Store max length of all chains
        }
        return res;
    }

    public static long wonderfulSubstrings(String word) {
        long ans = 0;
        int prefix = 0;              // Binary prefix
        int[] count = new int[1024]; // Binary prefix count
        count[0] = 1;                // Empty string ""

        for (final char c : word.toCharArray()) {
            prefix ^= 1 << c - 'a';
            ans += count[prefix];        // All chars occur even times
            for (int i = 0; i < 10; ++i) // ('a' + i) occurs odd times
                ans += count[prefix ^ 1 << i];
            ++count[prefix];
        }

        return ans;
    }


    static boolean isPythagorean(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                for(int k = j + 1; k < nums.length; k++) {
                    int x = nums[i] * nums[i];
                    int y = nums[j] * nums[j];
                    int z = nums[k] * nums[k];
                    if(y + z == x || x + z == y || x + y == z)
                        return true;
                }
            }
        }

        return false;
    }

    static int maxLargestProduct(int[] nums) {
        int res = nums[0];
        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            for(int j = i + 1; j < nums.length; j++) {
                res = Math.max(res, n);
                n *= nums[j];
            }
            res = Math.max(res, n);
        }
        return res;
    }

    //SET MATRIX ZEROS
    static void setZeros(int[][] nums) {

        int colLen = nums.length;
        int rowLen = nums[0].length;

        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        for(int j = 0; j < colLen; j++) {
            if(nums[j][0] == 0) {
                firstColumnZero = true;
                break;
            }
        }

        for(int i = 0; i < rowLen; i++) {
            if(nums[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        for(int i = 0; i < colLen; i++) {
            for(int j = 0; j < rowLen; j++) {
                if(nums[i][j] == 0) {
                    nums[i][0] = 0;
                    nums[0][j] = 0;
                }
            }
        }

        for(int i = 0; i < colLen; i++) {
            for(int j = 0; j < rowLen; j++) {
                if(nums[i][0] == 0 || nums[0][j] == 0) {
                    nums[i][j] = 0;
                }
            }
        }

        if(firstColumnZero)
            for(int i = 0; i < colLen; i++)
                nums[i][0] = 0;

        if(firstRowZero)
            for(int j = 0; j < rowLen; j++)
                nums[0][j] = 0;
    }

    //SEARCH INSERT POSITION
    static int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target) return lo = mid + 1;
            else
                return hi = mid - 1;
        }
        return 0;
    }

    static int majorityElement(int nums[]) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);

                if(map.get(nums[i]) > 1)
                    return nums[i];
            }else {
                map.put(nums[i], 1);
            }
        }
        return -1;
    }

    //LONGEST CONSECUTIVE SEQUENCE
    static int longestConsecutiveSequence(int[] nums) {
        int currentSequence = 1;
        int longestSequence = 1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != nums[i - 1]) {
                if(nums[i] == nums[i - 1] + 1)
                    currentSequence++;
            }else{
                longestSequence = Math.max(longestSequence, currentSequence);
                currentSequence = 1;
            }
        }
        return Math.max(longestSequence, currentSequence);
    }


    //SUFFIX ARRAY IMPLEMENTATION
    static String longestDupString(String s) {
        int N = s.length();
        String[] suffixes = new String[N];
        for(int i = 0; i < N; i++)
            suffixes[i] = s.substring(i, N);
        Arrays.sort(suffixes);
        String lrs = "";
        for(int i = 0; i < N; i++) {
            String x = lcp(suffixes[i], suffixes[i + 1]);
            if(x.length() > lrs.length()) lrs = x;
        }

        return lrs;
    }

    static String lcp(String t1, String t2) {
        int N = Math.min(t1.length(), t2.length());
        for(int i = 0; i < N; i++) {
            if(t1.charAt(i) != t2.charAt(i))
                return t1.substring(0, i);
        }

        return t1.substring(0, N);
    }

    //FIRST MISSING POSITIVE
    static int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int curr = nums[0];
            while(curr - 1 >= 0 && curr - 1 < nums.length && nums[curr - 1] != curr) {
                int temp = nums[curr - 1];
                nums[curr - 1] = curr;
                curr = temp;
            }
        }

        for(int i = 0; i < nums.length; i++)
            if(nums[i] != i + 1) return i + 1;

        return nums.length + 1;
    }

    //RUNNING SUM of 1d ARRAY
    static int[] runningSum(int[] nums) {
        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        for(int i = 0; i < nums.length; i++)
            temp[i] = temp[i - 1] + temp[i];

        return temp;
    }

    //MAXIMUM SUM IN ARRAY
    static int[] maximumSumSubArray(int[] nums) {
        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            temp[i] = nums[i] + temp[i - 1];
        }
        return temp;
    }

    //MAXIMUM SUBARRAY SUM
    static int maximumSubArray(int nums[]) {
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0 ; i <= nums.length - 1; i++) {
            int currSum = 0;
            for(int j = 0; j <= nums.length - 1; j++) {
                currSum += nums[j];
                if(currSum > maxSum) maxSum = currSum;
            }
        }

        return maxSum;

    }

    //SHUFFLE THE ARRAY
    static ArrayList<Integer> shuffleArray(ArrayList<Integer> nums) {
        ArrayList<Integer> res = new ArrayList<>();

        while(res.size() != 0) {
            int num = new Random().nextInt(nums.size());
            res.add(num);
            nums.remove(new Integer(num));
        }

        return res;
    }

    //3 SUM CLOSEST SUM
    public static int threeSumClosestSum(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < sz && diff != 0; i++) {
            int lo = i + 1;
            int hi = sz - 1;
            while(lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if(Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }if(sum < target)
                    ++lo;
                else
                    --hi;
            }

        }
        return target - diff;
    }

    //TWO SUM
    static void twoSum(int[] nums, int i,List<List<Integer>> res) {
        int lo = i + 1;
        int hi = nums.length - 1;
        while(lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if(sum < 0) {
                ++lo;
            }else if(sum > 0) {
                --hi;
            }else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while(lo < hi && nums[lo] == nums[lo - 1])
                    lo++;
            }
        }
    }

    static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int diff = -1;
        for(int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            if(map.containsValue(diff)) {
                return new int[]{diff, nums[i]};
            }else
                map.put(i, nums[i]);
        }
        return new int[] {0};
    }

    static List<List<Integer>> threeSum(int nums[]) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if(i == 0 || nums[i - 1] != nums[i])
                twoSum(nums, i, res);
        }
        return res;
    }

    //LEASR COMMON MULTIPLE
    static int gcd(int a, int b) {
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }

    static int findLCM(int[] nums) {
        int ans = nums[0];
        for(int i = 0; i < nums.length; i++) {
            ans = ( (ans * nums[i]) / gcd(nums[i], ans) );
        }

        return ans;
    }


    static boolean cons(int[] nums) {

        int currentSec = 1;

        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i - 1]) {
                if(nums[i] == nums[i - 1] + 1) {
                    currentSec++;
                }
            }

        }
        return (currentSec == nums.length);
    }

    static boolean searchMatrix(int[][] nums, int target) {
        int row = nums[0].length - 1;
        int col = nums.length - 1;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i <= row; i++) {
            for(int j = 0; j <= col; j++) {
                if(map.containsValue(target))
                    return true;
                else
                    map.put(i, nums[i][j]);
            }
        }

        return false;
    }

    static int numDistintcs(String S, String T) {
        int[][] table = new int[S.length() + 1][T.length() + 1];
        for(int i = 1; i < S.length(); i++) {
            for(int j = 1; j < T.length(); j++) {
                if(S.charAt(i - 1) == T.charAt(j - 1)) {
                    table[i][j] += table[i - 1][j] + table[i - 1][j - 1];
                }else{
                    table[i][j] += table[i - 1][j];
                }
            }
        }
        return table[S.length()][T.length()];

    }

    static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int currSum = 0;
            for(int j = i + 1; j < nums.length; j++) {
                currSum += nums[j];
                if(currSum > maxSum) maxSum = currSum;
            }
        }
        return maxSum;
    }


    static int maxProduct(int[] nums) {
        int res = 0;

        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            for(int j = i + 1; j < nums.length; j++) {
                res = Math.max(res, n);
                n *= nums[j];
            }
            res = Math.max(res, n);
        }
        return res;
    }

    static int removeDup(int[] nums) {
        int i = 0;
        for(int j = 0; j < nums.length; j++) {
            if(nums[i] != nums[j])
                i++;
        }
        return i + 1;
    }

    static int removeDuplicates(int[] nums) {
        if(nums.length == 0 || nums == null)
            return 0;
        int i = 0;
        for(int j = 0; j < nums.length; j++) {
            if(nums[i] != nums[j])
                i++;
            nums[i] = nums[j];
        }
        return i + 1;
    }

    static int lengthOfLongestString(String s) {
        int n = s.length();
        int res = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(checkRepition(s, i, j)) {
                    res = Math.max(res, j - 1 + 1);
                }
            }
        }
        return res;
    }

    static boolean checkRepition(String s, int start, int end) {
        int[] chars = new int[128];
        for(int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if(chars[c] > 1) return false;
        }
        return true;
    }





    static void swap(char[] cArr, int i, int j) {
        char ch = cArr[i];
        cArr[i] = cArr[j];
        cArr[j] = ch;
    }


    public int majorityElements(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
            if(map.get(nums[i]) > 1)
                return nums[i];
            else {
                map.put(nums[i], 1);
            }

        }
        return -1;
    }

    static int peakElementAndReturnItsIdx(int[] nums) {
        int n = nums.length;
        int idx = 0;
        int max = 0;
        for(int i = 2; i <= n - 2; i++) {
            int prev = nums[i - 2];
            int curr = nums[i];
            int next = nums[i + 1];

            while(curr > prev && curr > next && curr > max) {
                idx = i;
                max = curr;
            }

            if(nums[nums.length - 1] > max)
                return nums.length - 1;
        }
        return idx;
    }


    static void reverse(int[] arr, int a, int b) {
        int s = a, e = b;

        while(s < e) {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }
    static void rotate(int[] arr, int d) {
        int n = arr.length - 1;
        reverse(arr, 0, d);
        reverse(arr, d, n);
        reverse(arr, 0, n);
    }

    //Longest Palindrome
    static String longestPalindrome(String s) {
        int maxLength = 0;
        int length = s.length();
        String longestPalindrome = null;

        for(int i = 0; i < length; i++) {
            for(int j = i + 1; j < length; j++) {
                int len = j - 1;
                String curr = s.substring(i, j + 1);
                if(isPalid(curr)) {
                    longestPalindrome = curr;
                    maxLength = len;
                }
            }
        }
        return longestPalindrome;
    }


    static boolean isPalid(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }

    //WORK BREAK
    static boolean WordBreakingHelper(String s, Set<String> dict, int start) {
        if(s.length() == start)
            return true;

        for(String a: dict) {
            int len = a.length();
            int end = start+len;

            if(end > s.length()) continue;

            if(s.substring(start, start+len).equals(a))
                if(WordBreakingHelper(s, dict, start+len))
                    return true;
        }
        return false;
    }

    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < values.length; i++) {
            if(values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }

    //Anagram
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i),  0) + 1);
            freqMap.put(t.charAt(i), freqMap.getOrDefault(t.charAt(i),  0) + 1);
        }

        for(char ch : freqMap.keySet()) {
            if(freqMap.get(ch) != 0) return false;
        }
        return true;
    }

    static int shortestDistance(String[] words, String word1, String word2) {
        if(words.length == 0 || words == null) return -1;

        int posit = -1;
        int posit2 = -1;
        int dist = words.length;

        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            if(word.equals(word2)) {
                posit = i;
            }else if(word.equals(word2)) {
                posit2 = i;
            }

            if(posit != -1 || posit2 != -1) {
                dist = Math.min(dist, Math.abs(posit - posit2));
            }

        }
        return dist;

    }

    static int numGoodPairs(int[] nums) {
        int pairs = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) pairs++;
            }
        }
        return pairs;
    }

    //TARGET SUM
    static int[] targetSum(int[] nums, int target) {
        int upper = nums.length - 1;
        int lower = 0;
        while(lower < upper) {
            int sum = nums[lower] + nums[upper];
            if(sum == target) return new int[] {lower, upper};
            else if(sum < target) lower++;
            else upper--;
        }
        return new int[] {0};
    }



}


