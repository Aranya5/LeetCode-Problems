import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        
        // Edge case: empty matrix
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Define the 4 boundaries
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        
        // Loop until boundaries cross each other
        while (top <= bottom && left <= right) {
            
            // 1. Traverse from left to right along the top boundary
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // Shrink top boundary
            
            // 2. Traverse from top to bottom along the right boundary
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Shrink right boundary
            
            // 3. Traverse from right to left along the bottom boundary
            // (Check if top <= bottom to ensure we haven't already processed this row)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // Shrink bottom boundary
            }
            
            // 4. Traverse from bottom to top along the left boundary
            // (Check if left <= right to ensure we haven't already processed this column)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Shrink left boundary
            }
        }
        
        return result;
    }

    // Main method for testing in VS Code
    public static void main(String[] args) {
        SpiralMatrix54 solution = new SpiralMatrix54();
        
        // Example 1
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Example 1 Output: " + solution.spiralOrder(matrix1)); 
        // Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]
        
        // Example 2
        int[][] matrix2 = {
            {1,  2,  3,  4},
            {5,  6,  7,  8},
            {9, 10, 11, 12}
        };
        System.out.println("Example 2 Output: " + solution.spiralOrder(matrix2)); 
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}