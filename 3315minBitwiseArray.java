import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] minBitwiseArray(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int p : nums) {
            if (p == 2) {
                ans.add(-1);
            } else {
                int bitPos = 0;
                while (((p >> bitPos) & 1) == 1) {
                    bitPos++;
                }

                // The loop stops at the first '0' (at index bitPos).
                // The bit we need to remove is the one immediately before it (bitPos - 1).
                // Example: 7 (0111) -> first 0 is at index 3 -> remove bit at index 2 (val 4).
                ans.add(p - (1 << (bitPos - 1)));
            }
        }

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
}