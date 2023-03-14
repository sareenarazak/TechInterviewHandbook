import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Week1 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9))); // [0,1]
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 5))); // []
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6))); // [1,2]
        System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6))); // [0,1

    }

    /**
        Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
        You may assume that each input would have exactly one solution, and you may not use the same element twice.
        You can return the answer in any order.
     */
    private static int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0) return null;
 
        Map<Integer,Integer> numPairMap = new HashMap<>();
        for(int i = 0 ; i < nums.length; i++) {
            int remainder = target  - nums[i];
            if (numPairMap.containsKey(remainder)) {
                return new int[]{i, numPairMap.get(remainder)};
            }
            numPairMap.put(nums[i], i);
        }

        return new int[]{};
    }

}