import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Week1 {

    private static final Map<Character, Character> CLOSE_OPEN_MAP = Map.of(')', '(', '}', '{', ']', '[');
    private static final int[][] DIRECTIONS = new int[][] { { -1, 0 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static void main(String[] args) {
        // TWO SUM
        System.out.println(Arrays.toString(twoSum(new int[] { 2, 7, 11, 15 }, 9))); // [0,1]
        System.out.println(Arrays.toString(twoSum(new int[] { 2, 7, 11, 15 }, 5))); // []
        System.out.println(Arrays.toString(twoSum(new int[] { 3, 2, 4 }, 6))); // [1,2]
        System.out.println(Arrays.toString(twoSum(new int[] { 3, 3 }, 6))); // [0,1

        // brackets matching
        System.out.println(isValidParanthesis("")); // true;
        System.out.println(isValidParanthesis(null)); // true;
        System.out.println(isValidParanthesis("[]{}()")); // true;
        System.out.println(isValidParanthesis("[{()}]")); // true;
        System.out.println(isValidParanthesis("[{}()]{}")); // true;

        System.out.println(isValidParanthesis("[{()}")); // false;
        System.out.println(isValidParanthesis("}}}]")); // false;
        System.out.println(isValidParanthesis("[{((")); // false;

        // Greedy algo -- > stock
        System.out.println(bestTimeToSell(new int[] { 7, 1, 5, 3, 6, 4 })); // 5
        System.out.println(bestTimeToSell(new int[] { 7, 6, 4, 3, 1 })); // 0

        // is palindrome
        System.out.println(isPalindrome("Malayalam")); // true
        System.out.println(isPalindrome("")); // true
        System.out.println(isPalindrome("M")); // true
        System.out.println(isPalindrome("M :}|")); // true
        System.out.println(isPalindrome("race a car")); // false
        System.out.println(isPalindrome("M :}| a")); // false

        // anagram check
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("anagram", "nagarams")); // false
        System.out.println(isAnagram("a", "acd")); // false

        // binarySearch
        System.out.println(binarySearch(new int[] { -1, 0, 3, 5, 9, 12 }, 9)); // 4
        System.out.println(binarySearch(new int[] { -1, 0, 3, 5, 9, 12 }, 15)); // -1

        System.out.println(
                Arrays.deepToString(floodFill(new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } }, 1, 1, 2))); // [[2,
                                                                                                                 // 2,
                                                                                                                 // 1],
                                                                                                                 // [2,
                                                                                                                 // 2,
                                                                                                                 // 0],
                                                                                                                 // [2,
                                                                                                                 // 0,
                                                                                                                 // 1]]
        System.out.println(
                Arrays.deepToString(floodFill(new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } }, 1, 1, 1))); // no
                                                                                                                 // change

    }

    /**
     * Given an array of integers nums and an integer target, return indices of the
     * two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may
     * not use the same element twice.
     * You can return the answer in any order.
     */
    private static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return null;

        Map<Integer, Integer> numPairMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remainder = target - nums[i];
            if (numPairMap.containsKey(remainder)) {
                return new int[] { i, numPairMap.get(remainder) };
            }
            numPairMap.put(nums[i], i);
        }

        return new int[] {};
    }

    /**
     *
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and
     * ']', determine if the input string is valid.
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     * Example 1:
     * Input: s = "()"
     * Output: true
     * 
     * Example 2:
     * Input: s = "()[]{}"
     * Output: true
     * 
     * Example 3:
     * Input: s = "(]"
     * Output: false
     * 
     * Constraints:
     * 
     * 1 <= s.length <= 104
     * s consists of parentheses only '()[]{}'.
     */

    // String processing using stack, add all the open brackets to stack, once we
    // get a closing bracket, pop and check if it matches the corresponding open
    // if the stack is empty at the end, there are open brackets to be matched -->
    // not valid
    private static boolean isValidParanthesis(String s) {
        if (s == null || s.length() == 0)
            return true;
        // if string length is odd it cannot be valid
        if (s.length() % 2 != 0)
            return false;

        Stack<Character> bracketsStack = new Stack<>();

        for (char bracket : s.toCharArray()) {
            if (bracket == ')' || bracket == '}' || bracket == ']') {
                if (bracketsStack.isEmpty())
                    return false;
                char matchingBracket = CLOSE_OPEN_MAP.get(bracket);
                if (bracketsStack.pop() != matchingBracket)
                    return false;
            } else {
                bracketsStack.push(bracket);
            }
        }
        return bracketsStack.isEmpty();
    }

    /**
     * You are given an array prices where prices[i] is the price of a given stock
     * on the ith day.
     * 
     * You want to maximize your profit by choosing a single day to buy one stock
     * and choosing a different day in the future to sell that stock.
     * 
     * Return the maximum profit you can achieve from this transaction. If you
     * cannot achieve any profit, return 0.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: prices = [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit =
     * 6-1 = 5.
     * Note that buying on day 2 and selling on day 1 is not allowed because you
     * must buy before you sell.
     * Example 2:
     * 
     * Input: prices = [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transactions are done and the max profit = 0.
     * 
     * 
     * Constraints:
     * 
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 104
     */

    private static int bestTimeToSell(int[] prices) {
        // find the min price and keep track of it
        // Each time we check if we sell that day if profit increases

        int minprice = prices[0];
        int maxProfit = 0;

        for (int price : prices) {
            minprice = Math.min(minprice, price);
            maxProfit = Math.max(maxProfit, price - minprice);
        }
        return maxProfit;
    }

    /**
     * A phrase is a palindrome if, after converting all uppercase letters into
     * lowercase letters and removing all non-alphanumeric characters, it reads the
     * same forward and backward. Alphanumeric characters include letters and
     * numbers.
     * 
     * Given a string s, return true if it is a palindrome, or false otherwise.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "A man, a plan, a canal: Panama"
     * Output: true
     * Explanation: "amanaplanacanalpanama" is a palindrome.
     * Example 2:
     * 
     * Input: s = "race a car"
     * Output: false
     * Explanation: "raceacar" is not a palindrome.
     * Example 3:
     * 
     * Input: s = " "
     * Output: true
     * Explanation: s is an empty string "" after removing non-alphanumeric
     * characters.
     * Since an empty string reads the same forward and backward, it is a
     * palindrome.
     * 
     * 
     * Constraints:
     * 
     * 1 <= s.length <= 2 * 105
     * s consists only of printable ASCII characters.
     */

    private static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return true;

        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (!Character.isLetterOrDigit(startChar))
                start++;
            else if (!Character.isLetterOrDigit(endChar))
                end--;
            else {
                if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar))
                    return false;
                start++;
                end--;
            }

        }
        return true;
    }

    /**
     * Given two strings s and t, return true if t is an anagram of s, and false
     * otherwise.
     * 
     * An Anagram is a word or phrase formed by rearranging the letters of a
     * different word or phrase, typically using all the original letters exactly
     * once.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * Example 2:
     * 
     * Input: s = "rat", t = "car"
     * Output: false
     * 
     * 
     * Constraints:
     * 
     * 1 <= s.length, t.length <= 5 * 104
     * s and t consist of lowercase English letters.
     * 
     * 
     * Follow up: What if the inputs contain Unicode characters? How would you adapt
     * your solution to such a case?
     */

    private static boolean isAnagram(String s, String t) {
        // base case if both are null or empty true
        if (s == null && t == null)
            return true;
        if (s.isEmpty() && t.isEmpty())
            return true;
        // if lengths differ false
        if (s.length() != t.length())
            return false;
        // Anagrams have same number of letters at samefrequency;
        // same lenght
        int[] frequency = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
            frequency[t.charAt(i) - 'a']--;
        }

        // After processing if there is a non zero value --> not anagram
        for (int f : frequency) {
            if (f != 0)
                return false;
        }
        return true;

    }

    /**
     * Given an array of integers nums which is sorted in ascending order, and an
     * integer target, write a function to search target in nums. If target exists,
     * then return its index. Otherwise, return -1.
     * 
     * You must write an algorithm with O(log n) runtime complexity.
     * Example 1:
     * 
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     * Example 2:
     * 
     * Input: nums = [-1,0,3,5,9,12], target = 2
     * Output: -1
     * Explanation: 2 does not exist in nums so return -1
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 104
     * -104 < nums[i], target < 104
     * All the integers in nums are unique.
     * nums is sorted in ascending order.
     * 
     */

    // classic binary search imp
    private static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) { // = sign is important for odd length arrays
            int mid = start + (end - start) / 2; // --> avoid overflow for large arrays
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    /**
     * An image is represented by an m x n integer grid image where image[i][j]
     * represents the pixel value of the image.
     * 
     * You are also given three integers sr, sc, and color. You should perform a
     * flood fill on the image starting from the pixel image[sr][sc].
     * 
     * To perform a flood fill, consider the starting pixel, plus any pixels
     * connected 4-directionally to the starting pixel of the same color as the
     * starting pixel, plus any pixels connected 4-directionally to those pixels
     * (also with the same color), and so on. Replace the color of all of the
     * aforementioned pixels with color.
     * 
     * Return the modified image after performing the flood fill.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
     * Output: [[2,2,2],[2,2,0],[2,0,1]]
     * Explanation: From the center of the image with position (sr, sc) = (1, 1)
     * (i.e., the red pixel), all pixels connected by a path of the same color as
     * the starting pixel (i.e., the blue pixels) are colored with the new color.
     * Note the bottom corner is not colored 2, because it is not 4-directionally
     * connected to the starting pixel.
     * Example 2:
     * 
     * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
     * Output: [[0,0,0],[0,0,0]]
     * Explanation: The starting pixel is already colored 0, so no changes are made
     * to the image.
     * 
     * 
     * Constraints:
     * 
     * m == image.length
     * n == image[i].length
     * 1 <= m, n <= 50
     * 0 <= image[i][j], color < 216
     * 0 <= sr < m
     * 0 <= sc < n
     * 
     */

    private static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null || image.length == 0 || image[sr][sc] == color)
            return image;

        int sourceColor = image[sr][sc];
        dfsFloodFill(image, sr, sc, sourceColor, color);
        return image;
    }

    private static void dfsFloodFill(int[][] image, int sr, int sc, int sourceColor, int color) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length)
            return;
        if (image[sr][sc] != sourceColor)
            return;

        image[sr][sc] = color;

        for (int[] direction : DIRECTIONS) {
            int nextRow = sr + direction[0];
            int nextCol = sc + direction[1];
            dfsFloodFill(image, nextRow, nextCol, sourceColor, color);
        }
    }


    /**Given head, the head of a linked list, determine if the linked list has a cycle in it.

    There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
    Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

    Return true if there is a cycle in the linked list. Otherwise, return false.
    */

    private static boolean hasCycleLinkedList(ListNode head) {
        if(head == null || head.next == null) return false;
        if(head == head.next) return true;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) return true;
        }
        return false;
    }


    /**
     * Given a binary tree, determine if it is height-balanced

        Example 1:
        Input: root = [3,9,20,null,null,15,7]
        Output: true

        Example 2:
        Input: root = [1,2,2,3,3,null,null,4,4]
        Output: false

        Example 3:
        Input: root = []
        Output: true
     */
    // Height balanced tree right subtree height and left subtree height differ by 0 or 1 
    private static boolean isHeightBalancedBF(TreeNode root) {
        if(root == null) return true;
        int leftHeight = heightBinaryTree(root.left);
        int rightHeight = heightBinaryTree(root.right);
        if(Math.abs(leftHeight - rightHeight) > 1) return false;
        return isHeightBalancedBF(root.left) && isHeightBalancedBF(root.right);
    }

    private static int heightBinaryTree(TreeNode root) {
        if(root == null) return 0;

        return 1 + Math.max(heightBinaryTree(root.left), heightBinaryTree(root.right));
    }

    private static boolean isHeightBalanced(TreeNode root) {
        if(root == null) return true;
        return heightBalancedHelper(root) != -1;

    }


    private static int heightBalancedHelper(TreeNode root) {
        if(root == null) return 0;

        int leftHeight = heightBalancedHelper(root.left);
        int rightHeight = heightBalancedHelper(root.right);
        
        if(leftHeight == -1) return -1;
        if(rightHeight == -1) return -1;
        if(Math.abs(leftHeight - rightHeight) > 1) return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /** LCA - lowest common ancestor in A BST */
    // TESTEDIN LEET CODE
    // in A BST if the current node is < p and > p that is the LCA node cos the node
    // p or q is on either side of the current node and thus cannot have
    // any other ancesotors. NOTE: a node can be its on ancestor. So if the value is
    // either p or q return that node
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        if (root == p || root == q)
            return root;
        TreeNode ancestor = root;
        int pVal = p.val;
        int qVal = q.val;

        while (ancestor != null) {
            int ancestorVal = ancestor.val;

            if (ancestorVal > pVal && ancestorVal > qVal) {
                ancestor = ancestor.left;
            } else if (ancestorVal < pVal && ancestorVal < qVal) {
                ancestor = ancestor.right;

            } else {
                return ancestor;
            }
        }

        return null;
    }

    private TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        if (root == p || root == q)
            return root;
        int pVal = p.val;
        int qVal = q.val;
        int rootVal = root.val;
        if (rootVal > pVal && rootVal > qVal) {
            return lowestCommonAncestorRecursive(root.left, p, q);
        } else if (rootVal < pVal && rootVal < qVal) {
            return lowestCommonAncestorRecursive(root.right, p, q);
        } else {
            return root;
        }
    }

    /**
     * Given the root of a binary tree, invert the tree, and return its root.
     * 
     * Example 1:
     * 
     * Input: root = [4,2,7,1,3,6,9]
     * Output: [4,7,2,9,6,3,1]
     * Example 2:
     * 
     * 
     * Input: root = [2,1,3]
     * Output: [2,3,1]
     * Example 3:
     * 
     * Input: root = []
     * Output: []
     * 
     */

    // recursion --> Assign the right subtree to left, left subtree to right
    // postorder since we need to operate on the subtrees first --> assigning
    // /inverting before subtrees gives wrong result
    // TESTED IN LEETCODE
    private static TreeNode invertBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode leftSubTree = invertBinaryTree(root.left);
        TreeNode rightSubTree = invertBinaryTree(root.right);

        root.left = rightSubTree;
        root.right = leftSubTree;
        return root;
    }

    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     * 
     * Merge the two lists in a one sorted list. The list should be made by splicing
     * together the nodes of the first two lists.
     * 
     * Return the head of the merged linked list.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: list1 = [1,2,4], list2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     * Example 2:
     * 
     * Input: list1 = [], list2 = []
     * Output: []
     * Example 3:
     * 
     * Input: list1 = [], list2 = [0]
     * Output: [0]
     * 
     * 
     * Constraints:
     * 
     * The number of nodes in both lists is in the range [0, 50].
     * -100 <= Node.val <= 100
     * Both list1 and list2 are sorted in non-decreasing order.
     */

    // TESTED IN LEETCODe
    private ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        // create a dummy head node to keep as ref
        ListNode result = new ListNode(Integer.MAX_VALUE);
        ListNode current = result;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        while (list1 != null) {
            current.next = list1;
            list1 = list1.next;
            current = current.next;
        }

        while (list2 != null) {
            current.next = list2;
            list2 = list2.next;
            current = current.next;
        }
        return result.next;
    }
}