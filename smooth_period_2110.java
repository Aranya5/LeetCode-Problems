import java.util.*;

class Solution {
    public long getDescentPeriods(int[] prices) {
        long result = 1;   // first day itself is a smooth descent period
        long count = 1;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                count++;
            } else {
                count = 1;
            }
            result += count;
        }

        return result;
    }
}

public class smooth_period_2110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] prices = new int[n];

        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        Solution sol = new Solution();
        System.out.println(sol.getDescentPeriods(prices));

        sc.close();
    }
}

