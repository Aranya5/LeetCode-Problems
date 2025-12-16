import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<List<Integer>> adj;
    private int[] present;
    private int[] future;
    private int budget;

    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.present = present;
        this.future = future;
        this.budget = budget;
        this.adj = new ArrayList<>();

        // 1. Build the Adjacency List (Tree)
        // IDs are 1-based, we map them to 0-based for list access if needed, 
        // but since hierarchy uses 1-based, we'll keep adj size n+1.
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] relation : hierarchy) {
            int u = relation[0];
            int v = relation[1];
            adj.get(u).add(v);
        }

        // 2. Perform DFS from the CEO (Employee 1)
        // The result is a 2D array: int[2][budget + 1]
        // result[0] = DP array if parent didn't buy
        // result[1] = DP array if parent did buy
        // Since CEO has no parent, we look at result[0] (or result[1] doesn't matter as logic for CEO is root).
        int[][] rootResult = dfs(1);

        // 3. Return max profit for the total budget from the "Parent didn't buy" state 
        // (CEO technically has no parent, so no discount passed down to him).
        return rootResult[0][budget];
    }

    private int[][] dfs(int u) {
        // Initialize DP tables for this node
        // [0][b] -> max profit for subtree u, parent(u) NOT bought
        // [1][b] -> max profit for subtree u, parent(u) BOUGHT
        int[][] dp = new int[2][budget + 1];

        // Intermediate knapsacks to aggregate children results
        // knap0: Combined children DP assuming u is NOT bought (children pay full)
        // knap1: Combined children DP assuming u IS bought (children pay half)
        int[] knap0 = new int[budget + 1];
        int[] knap1 = new int[budget + 1];

        // Merge children branches
        for (int v : adj.get(u)) {
            int[][] childRes = dfs(v);
            
            // Merge child's "Parent Not Bought" (childRes[0]) into knap0
            knap0 = mergeKnapsack(knap0, childRes[0]);
            
            // Merge child's "Parent Bought" (childRes[1]) into knap1
            knap1 = mergeKnapsack(knap1, childRes[1]);
        }

        // Calculate costs and profits for Node u
        int fullCost = present[u - 1];
        int fullProfit = future[u - 1] - fullCost;
        
        int halfCost = present[u - 1] / 2;
        int halfProfit = future[u - 1] - halfCost;

        // Fill current DP tables
        for (int b = 0; b <= budget; b++) {
            // --- State 0: Parent of u did NOT buy ---
            // Option 1: Don't buy u. Children see "u not bought". 
            // We use knap0[b].
            int notBuy = knap0[b];
            
            // Option 2: Buy u (Full Price). Children see "u bought".
            // We use knap1[b - fullCost] + fullProfit.
            int buyFull = -1; // -1 indicates invalid
            if (b >= fullCost) {
                buyFull = knap1[b - fullCost] + fullProfit;
            }
            dp[0][b] = Math.max(notBuy, buyFull);

            // --- State 1: Parent of u DID buy ---
            // Option 1: Don't buy u. Children see "u not bought".
            // We use knap0[b] (Same as above, discount from grandparent stops at u).
            int notBuy2 = knap0[b];

            // Option 2: Buy u (Half Price). Children see "u bought".
            // We use knap1[b - halfCost] + halfProfit.
            int buyHalf = -1;
            if (b >= halfCost) {
                buyHalf = knap1[b - halfCost] + halfProfit;
            }
            dp[1][b] = Math.max(notBuy2, buyHalf);
        }

        return dp;
    }

    // Standard Knapsack Merge: combines two DP arrays into one
    // dpA[w] = max profit with budget w
    // dpB[w] = max profit with budget w
    // result[w] = max(dpA[k] + dpB[w-k])
    private int[] mergeKnapsack(int[] dpA, int[] dpB) {
        int[] newDp = new int[budget + 1];
        // Initialize with -1 or extremely low value if needed, 
        // but since 0 profit (buying nothing) is always possible, 0 is fine.
        
        // We iterate downwards to find max combination
        // This is O(Budget^2)
        for (int b = 0; b <= budget; b++) {
            for (int k = 0; k <= b; k++) {
                newDp[b] = Math.max(newDp[b], dpA[b - k] + dpB[k]);
            }
        }
        return newDp;
    }
}