public class LargestMagicSquare {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // A magic square must be at least 2x2, so we check from 2 downwards
        for (int size = Math.min(m, n) - 1; size >= 2; size--) {
            for (int i = 0; i + size <= m; i++) {
                for (int j = 0; j + size <= n; j++) {
                    if (isMagicSquare(grid, i, j, size)) {
                        return size;
                    }
                }
            }
        }
        return 1;
    }

    private boolean isMagicSquare(int[][] grid, int row, int col, int size) {
        // For a magic square of size k, we check the inner (k-1)x(k-1) grid
        // The border is excluded
        
        int target = -1;
        
        // Check all rows of the inner square
        for (int i = row + 1; i < row + size; i++) {
            int sum = 0;
            for (int j = col + 1; j < col + size; j++) {
                sum += grid[i][j];
            }
            if (target == -1) {
                target = sum;
            } else if (sum != target) {
                return false;
            }
        }
        
        // Check all columns of the inner square
        for (int j = col + 1; j < col + size; j++) {
            int sum = 0;
            for (int i = row + 1; i < row + size; i++) {
                sum += grid[i][j];
            }
            if (sum != target) {
                return false;
            }
        }
        
        // Check main diagonal of the inner square
        int diag1 = 0;
        for (int i = 0; i < size - 1; i++) {
            diag1 += grid[row + 1 + i][col + 1 + i];
        }
        if (diag1 != target) {
            return false;
        }
        
        // Check anti-diagonal of the inner square
        int diag2 = 0;
        for (int i = 0; i < size - 1; i++) {
            diag2 += grid[row + 1 + i][col + size - 1 - i];
        }
        if (diag2 != target) {
            return false;
        }
        
        return true;
    }
}
