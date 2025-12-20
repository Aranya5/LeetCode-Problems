import java.util.*;

class Solution {
    public int delCol944(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        int rows = strs.length;
        int cols = strs[0].length();
        int deleteCount = 0;

        // Iterate through each column index
        for (int c = 0; c < cols; c++) {
            // Check each row for the current column
            for (int r = 0; r < rows - 1; r++) {
                // If the character in the current row is greater than the next row,
                // the column is not sorted, so we delete it.
                if (strs[r].charAt(c) > strs[r + 1].charAt(c)) {
                    deleteCount++;
                    break; // Move to the next column
                }
            }
        }

        return deleteCount;
    }
}

// Main class to run and test the code
public class delCol944 {
    public static void main(String[] args) {
        Solution solver = new Solution();

        // Example 1
        String[] strs1 = {"abc", "bce", "cae"};
        System.out.println("Result: " + solver.delCol944(strs1)); 
        // Expected: 1

        // Example 2
        String[] strs2 = {"cba", "daf", "ghi"};
        System.out.println("Result: " + solver.delCol944(strs2)); 
        // Expected: 1

        // Example 3
        String[] strs3 = {"a", "b"};
        System.out.println("Result: " + solver.delCol944(strs3)); 
        // Expected: 0
        
        // Example 4
        String[] strs4 = {"zyx", "wvu", "tsr"};
        System.out.println("Result: " + solver.delCol944(strs4)); 
        // Expected: 3
    }
}