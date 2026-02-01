class Solution {
    public int minimumCost(int[] nums) {
        // The first element is always the cost of the first subarray.
        int sum = nums[0];
        
        // We need to find the two smallest elements in the rest of the array (index 1 to end).
        // Initialize min1 and min2 to the maximum possible integer value.
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            
            // Update the minimums
            if (current < min1) {
                min2 = min1;    // The old min1 becomes the new min2
                min1 = current; // Update min1 to the new smallest
            } else if (current < min2) {
                min2 = current; // Update min2 if it's smaller than current min2
            }
        }
        
        return sum + min1 + min2;
    }
}