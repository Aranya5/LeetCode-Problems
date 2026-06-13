import java.util.Arrays;

public class NextPermutation31 {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        
        // Step 1: Find the Pivot (First Drop)
        // Move left as long as the elements are in descending order[cite: 20].
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        // If we found a valid pivot (the array isn't entirely descending)
        if (i >= 0) {
            int j = nums.length - 1;
            
            // Step 2: Find the Successor
            // Find the smallest element from the right that is greater than the pivot[cite: 27].
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            
            // Step 3: Swap Pivot and Successor [cite: 30]
            swap(nums, i, j);
        }
        
        // Step 4: Reverse the Suffix
        // If i >= 0, this reverses the descending suffix to ascending.
        // If i == -1 (edge case), this reverses the entire array[cite: 5, 52].
        reverse(nums, i + 1, nums.length - 1);
    }
    
    // Helper method to swap two elements in the array [cite: 49]
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // Helper method to reverse a portion of the array [cite: 50]
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    // Main method to test the logic directly in VS Code
    public static void main(String[] args) {
        NextPermutation31 solution = new NextPermutation31();
        
        // Example 1
        int[] nums1 = {1, 2, 3};
        solution.nextPermutation(nums1);
        System.out.println("Next permutation of [1, 2, 3]: " + Arrays.toString(nums1)); 
        // Expected: [1, 3, 2]
        
        // Example 2
        int[] nums2 = {3, 2, 1};
        solution.nextPermutation(nums2);
        System.out.println("Next permutation of [3, 2, 1]: " + Arrays.toString(nums2)); 
        // Expected: [1, 2, 3] (Edge case looping back)
        
        // Example 3
        int[] nums3 = {1, 1, 5};
        solution.nextPermutation(nums3);
        System.out.println("Next permutation of [1, 1, 5]: " + Arrays.toString(nums3)); 
        // Expected: [1, 5, 1]
    }
}