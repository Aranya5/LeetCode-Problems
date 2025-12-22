#include <iostream>
#include <vector>
#include <string>
#include <algorithm> // for max

using namespace std;

class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        if (strs.empty() || strs[0].empty()) return 0;
        
        int n = strs.size();
        int m = strs[0].length();
        
        // dp[i] stores the max number of kept columns ending at index i
        // that satisfy the sorted condition for all rows.
        vector<int> dp(m, 1);
        int max_kept = 1;
        
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < i; ++j) {
                bool can_extend = true;
                
                // Check if column j can precede column i in ALL rows
                for (int k = 0; k < n; ++k) {
                    if (strs[k][j] > strs[k][i]) {
                        can_extend = false;
                        break;
                    }
                }
                
                if (can_extend) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
            max_kept = max(max_kept, dp[i]);
        }
        
        return m - max_kept;
    }
};

int main() {
    Solution sol;

    // Test Case 1: Example from problem description
    // Rows: "babca", "bbazb"
    // Valid columns to keep could be index 1 ('a','b') and 3 ('c','z').
    // Length 2 kept -> 3 deleted.
    vector<string> strs1 = {"babca", "bbazb"};
    cout << "Test Case 1: " << sol.minDeletionSize(strs1) << " (Expected: 3)" << endl;

    // Test Case 2: Strictly decreasing
    // Can only keep 1 column. 5 total - 1 kept = 4 deleted.
    vector<string> strs2 = {"edcba"};
    cout << "Test Case 2: " << sol.minDeletionSize(strs2) << " (Expected: 4)" << endl;

  
    vector<string> strs3 = {"ghi", "def", "abc"};
    cout << "Test Case 3: " << sol.minDeletionSize(strs3) << " (Expected: 0)" << endl;

    return 0;
}