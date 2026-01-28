#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

class Solution {
public:
    int minCost(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        
        // Use long long to prevent integer overflow during cost accumulation
        // Initialize with a large value representing infinity
        vector<vector<long long>> dp(m, vector<long long>(n, LLONG_MAX));
        
        // Base case: Starting at (0,0) costs 0
        dp[0][0] = 0;
        
        // Helper function to propagate normal moves (Right and Down)
        // Since moves are restricted to Right/Down, a single top-left to bottom-right pass works.
        auto propagate_walk = [&]() {
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (dp[i][j] == LLONG_MAX) continue;
                    
                    int current_cost = grid[i][j]; // Cost to enter (i, j) is not used for outgoing
                    
                    // Move Down: Cost is value of destination cell (i+1, j)
                    if (i + 1 < m) {
                        dp[i+1][j] = min(dp[i+1][j], dp[i][j] + grid[i+1][j]);
                    }
                    // Move Right: Cost is value of destination cell (i, j+1)
                    if (j + 1 < n) {
                        dp[i][j+1] = min(dp[i][j+1], dp[i][j] + grid[i][j+1]);
                    }
                }
            }
        };

        // 1. Initial pass: Calculate costs with 0 teleports
        propagate_walk();
        
        // Prepare a list of all cell coordinates to sort them by grid value
        vector<pair<int, int>> cells;
        cells.reserve(m * n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cells.push_back({i, j});
            }
        }
        
        // Sort cells by grid value in descending order.
        // This allows efficient processing of the "grid[dest] <= grid[source]" condition.
        sort(cells.begin(), cells.end(), [&](const pair<int, int>& a, const pair<int, int>& b) {
            return grid[a.first][a.second] > grid[b.first][b.second];
        });

        // 2. Iterate k times to allow up to k teleports
        for (int step = 0; step < k; ++step) {
            // Create next state, initially a copy of current best costs
            vector<vector<long long>> next_dp = dp;
            
            long long min_source_cost = LLONG_MAX;
            int idx = 0;
            int total_cells = cells.size();
            
            // Process cells from highest value to lowest
            while (idx < total_cells) {
                int r_curr = cells[idx].first;
                int c_curr = cells[idx].second;
                int val = grid[r_curr][c_curr];
                int start_idx = idx;
                
                // Process a batch of cells with the same grid value
                long long current_batch_min = LLONG_MAX;
                
                // First loop: Find the min cost among all cells with this specific value
                while (idx < total_cells && grid[cells[idx].first][cells[idx].second] == val) {
                    int r = cells[idx].first;
                    int c = cells[idx].second;
                    current_batch_min = min(current_batch_min, dp[r][c]);
                    idx++;
                }
                
                // Update the global minimum source cost.
                // Since we are iterating descending, 'min_source_cost' tracks the min cost 
                // of all cells seen so far (which all have value >= current val).
                min_source_cost = min(min_source_cost, current_batch_min);
                
                // Second loop: Update next_dp for the current batch using the global minimum
                // Teleport cost is 0, so the cost becomes exactly min_source_cost
                for (int i = start_idx; i < idx; ++i) {
                    int r = cells[i].first;
                    int c = cells[i].second;
                    if (min_source_cost != LLONG_MAX) {
                        next_dp[r][c] = min(next_dp[r][c], min_source_cost);
                    }
                }
            }
            
            // Apply the updates to the main DP table
            dp = next_dp;
            
            // Propagate the new teleported costs via normal walking
            propagate_walk();
        }
        
        return (int)dp[m-1][n-1];
    }
};