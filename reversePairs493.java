import java.util.Arrays;

public class reversePairs493 {

    public int reversePairs(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    private int mergeSortAndCount(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        
        int mid = left + (right - left) / 2;
        int count = 0;

        // 1. Divide and conquer both halves
        count += mergeSortAndCount(nums, left, mid);
        count += mergeSortAndCount(nums, mid + 1, right);
        
        // 2. Count reverse pairs and merge the sorted pieces together
        count += countAndMerge(nums, left, mid, right);
        
        return count;
    }

    private int countAndMerge(int[] nums, int left, int mid, int right) {
        int count = 0;
        int j = mid + 1;

        // --- STEP 1: COUNT REVERSE PAIRS ---
        // We do this before merging so we don't destroy the sorted order required for counting
        for (int i = left; i <= mid; i++) {
            // Use (long) to prevent integer overflow when multiplying by 2
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            // All elements from the current 'j' position back to 'mid + 1' 
            // are valid pairs for nums[i]
            count += (j - (mid + 1));
        }

        // --- STEP 2: CLASSIC MERGE (Your trusted structure!) ---
        int[] temp = new int[right - left + 1];
        int i = left;
        j = mid + 1;
        int k = 0;

        // Your primary comparison loop [cite: 127]
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // Leftover loops placed safely outside to prevent trapping elements [cite: 128, 151]
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // Copy the merged elements back into the original array
        System.arraycopy(temp, 0, nums, left, temp.length);

        return count;
    }

    // Main method to run and verify the code in VS Code
    public static void main(String[] args) {
        reversePairs493 solution = new reversePairs493();

        // Example 1 [cite: 131]
        int[] nums1 = {1, 3, 2, 3, 1};
        int result1 = solution.reversePairs(nums1);
        System.out.println("Test Case 1 Output: " + result1); 
        // Expected Output: 2 [cite: 131]

        // Example 2 [cite: 131]
        int[] nums2 = {2, 4, 3, 5, 1};
        int result2 = solution.reversePairs(nums2);
        System.out.println("Test Case 2 Output: " + result2); 
        // Expected Output: 3 [cite: 131]
    }
}