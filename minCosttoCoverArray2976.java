import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // 1. Initialize the cost matrix for 26 lowercase letters.
        // We use a safe infinity value (Long.MAX_VALUE / 2) to avoid overflow during addition.
        long[][] minCosts = new long[26][26];
        long INF = Long.MAX_VALUE / 2;
        
        for (long[] row : minCosts) {
            Arrays.fill(row, INF);
        }
        // Converting a character to itself always costs 0
        for (int i = 0; i < 26; i++) {
            minCosts[i][i] = 0;
        }
        
        // 2. Populate the matrix with the direct edges given in the input.
        // If there are multiple ways to convert x -> y, keep the minimum cost.
        int m = original.length;
        for (int i = 0; i < m; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            minCosts[u][v] = Math.min(minCosts[u][v], (long)cost[i]);
        }
        
        // 3. Apply Floyd-Warshall to find All-Pairs Shortest Paths.
        // This accounts for intermediate conversions (e.g., a -> b -> c).
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (minCosts[i][k] < INF && minCosts[k][j] < INF) {
                        minCosts[i][j] = Math.min(minCosts[i][j], minCosts[i][k] + minCosts[k][j]);
                    }
                }
            }
        }
        
        // 4. Calculate the total cost to transform source string to target string.
        long totalCost = 0;
        int n = source.length();
        
        for (int i = 0; i < n; i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';
            
            if (u != v) {
                // If there is no path from source char to target char, conversion is impossible.
                if (minCosts[u][v] == INF) {
                    return -1;
                }
                totalCost += minCosts[u][v];
            }
        }
        
        return totalCost;
    }
}