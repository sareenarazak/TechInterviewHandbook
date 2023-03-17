import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Week2 {

    public static void main(String[] args) {
        System.out.println(canConstruct("rand", "randssss")); // true
        System.out.println(canConstruct("rand", "asbcndr")); // true

        System.out.println( canConstruct("rand", "r")); // false
        System.out.println(canConstruct("randa", "rands")); // false

        System.out.println(waysToClimbStairsIterative(10)); // 89
        System.out.println(waysToClimbStairsRecurse(10)); // 89

        System.out.println(waysToClimbStairsIterative(3)); // 3
        System.out.println(waysToClimbStairsRecurse(3)); // 3

        System.out.println(longestPalindrome("abccccdd")); // 7
        System.out.println(longestPalindrome("a")); // 1
        System.out.println(longestPalindrome("aA")); // 1
        System.out.println(longestPalindrome("abab")); // 4

    }

    /**
     * Given two strings ransomNote and magazine, return true if ransomNote can be
     * constructed by using the letters from magazine and false otherwise.
     * 
     * Each letter in magazine can only be used once in ransomNote.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: ransomNote = "a", magazine = "b"
     * Output: false
     * Example 2:
     * 
     * Input: ransomNote = "aa", magazine = "ab"
     * Output: false
     * Example 3:
     * 
     * Input: ransomNote = "aa", magazine = "aab"
     * Output: true
     * 
     * 
     * Constraints:
     * 
     * 1 <= ransomNote.length, magazine.length <= 105
     * ransomNote and magazine consist of lowercase English letters.
     */

    private static boolean canConstruct(String randsomNote, String magazine) {
        if (randsomNote == null || randsomNote.length() == 0)
            return true;
        if (magazine == null || magazine.length() < randsomNote.length())
            return false;

        // only lowercase english alphabets
        int[] memo = new int[26];
        for (char mChar : magazine.toCharArray()) {
            memo[mChar - 'a']++;
        }

        for (char rChar : randsomNote.toCharArray()) {
            if (--memo[rChar - 'a'] < 0)
                return false;
        }
        return true;
    }

    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can
     * you climb to the top?
     * 
     * Example 1:
     * 
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     * 
     * Input: n = 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 45
     * 
     */

    private static int waysToClimbStairsIterative(int n) {
        if (n <= 1)
            return n;
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    private static int waysToClimbStairsRecurse(int n) {
        if (n <= 1)
            return n;
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 2;
        return climbMemo(n, memo);
    }

    private static int climbMemo(int i, int[] memo) {
        if (i == 0)
            return 0;
        if (memo[i] != 0)
            return memo[i];
        memo[i] = climbMemo(i - 1, memo) + climbMemo(i - 2, memo);
        return memo[i];
    }

    /**
     * Given a string s which consists of lowercase or uppercase letters, return the
     * length of the longest palindrome that can be built with those letters.
     * 
     * Letters are case sensitive, for example, "Aa" is not considered a palindrome
     * here.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "abccccdd"
     * Output: 7
     * Explanation: One longest palindrome that can be built is "dccaccd", whose
     * length is 7.
     * Example 2:
     * 
     * Input: s = "a"
     * Output: 1
     * Explanation: The longest palindrome that can be built is "a", whose length is
     * 1.
     * 
     */

    // each time if a letter exists incremenet count , we need 2 of each letter,
    // remove the letter after incrementing the count since we found a match
    // if the size of the set is 0 we have perfectly matched couples
    // if the size is not zero, we can add 1 to the the length cos one letter word
    // is a palindrome

    public static int longestPalindrome(String s) {
        Set<Character> letters = new HashSet<>();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (letters.contains(c)) {
                count++;
                letters.remove(c);
            } else {
                letters.add(c);
            }
        }
        if (letters.size() == 0)
            return count * 2;
        return count * 2 + 1;
    }

    /**
     * Given two binary strings a and b, return their sum as a binary string.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: a = "11", b = "1"
     * Output: "100"
     * Example 2:
     * 
     * Input: a = "1010", b = "1011"
     * Output: "10101"
     * 
     * 
     * Constraints:
     * 
     * 1 <= a.length, b.length <= 104
     * a and b consist only of '0' or '1' characters.
     * Each string does not contain leading zeros except for the zero itself.
     * 
     */

    /**
     * Given an integer array nums, return true if any value appears at least twice
     * in the array, and return false if every element is distinct.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,2,3,1]
     * Output: true
     * Example 2:
     * 
     * Input: nums = [1,2,3,4]
     * Output: false
     * Example 3:
     * 
     * Input: nums = [1,1,1,3,3,4,3,2,4,2]
     * Output: true
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * 
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) {
            if (!numSet.add(n))
                return true;
        }
        return false;

    }

    /**
     * Given the head of a singly linked list, reverse the list, and return the
     * reversed list.
     * Tested in leetcode
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    /**
     * Given an array nums of size n, return the majority element.
     * 
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You
     * may assume that the majority element always exists in the array.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [3,2,3]
     * Output: 3
     * Example 2:
     * 
     * Input: nums = [2,2,1,1,1,2,2]
     * Output: 2
     * 
     */
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];

    }

    /**
     * Given the head of a singly linked list, return the middle node of the linked
     * list.
     * 
     * If there are two middle nodes, return the second middle node.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: head = [1,2,3,4,5]
     * Output: [3,4,5]
     * Explanation: The middle node of the list is node 3.
     * Example 2:
     * 
     * 
     * Input: head = [1,2,3,4,5,6]
     * Output: [4,5,6]
     * Explanation: Since the list has two middle nodes with values 3 and 4, we
     * return the second one.
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in the list is in the range [1, 100].
     * 1 <= Node.val <= 100
     * 
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;

    }

    /**
     * Given the root of a binary tree, return its maximum depth.
     * 
     * A binary tree's maximum depth is the number of nodes along the longest path
     * from the root node down to the farthest leaf node.
     * 
     * 
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }
}
