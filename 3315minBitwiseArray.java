class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        vector<int> ans;
        ans.reserve(nums.size()); // Optimization: reserve memory
        

        for (int p : nums) {
            if (p == 2) {
                ans.push_back(-1);
            } else {
                int bitPos = 0;
                while ((p >> bitPos) & 1) {
                    bitPos++;
                }
                
                // The loop stops at the first '0' (at index bitPos).
                // The bit we need to remove is the one immediately before it (bitPos - 1).
                // Example: 7 (0111) -> first 0 is at index 3 -> remove bit at index 2 (val 4).
                ans.push_back(p - (1 << (bitPos - 1)));
            }
        }
        return ans;
    }
}
;