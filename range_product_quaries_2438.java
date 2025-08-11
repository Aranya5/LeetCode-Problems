import java.util.*;

public class range_product_quaries_2438 {
    private static final long MOD = 1_000_000_007L;

    // Fast modular exponentiation
    private long modPow(long base, long exp) {
        base %= MOD;
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    public int[] productQueries(int n, int[][] queries) {
        // Step 1: Get exponents of set bits in n
        List<Integer> exponents = new ArrayList<>();
        for (int b = 0; b < 31; b++) {
            if (((n >> b) & 1) == 1) {
                exponents.add(b);
            }
        }

        // Step 2: Prefix sums of exponents
        long[] prefix = new long[exponents.size() + 1];
        for (int i = 0; i < exponents.size(); i++) {
            prefix[i + 1] = prefix[i] + exponents.get(i);
        }

        // Step 3: Answer queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            long exp = prefix[r + 1] - prefix[l];
            ans[i] = (int) modPow(2, exp);
        }
        return ans;
    }

    public static void main(String[] args) {
        range_product_quaries_2438 sol = new range_product_quaries_2438();
        
        // Example input
        int n = 13; // 13 = 1101 in binary -> powers [1, 4, 8]
        int[][] queries = {
            {0, 0}, // product of powers[0] = 1
            {0, 1}, // 1 * 4 = 4
            {1, 2}, // 4 * 8 = 32
            {0, 2}  // 1 * 4 * 8 = 32
        };

        int[] result = sol.productQueries(n, queries);

        System.out.println("Results:");
        for (int val : result) {
            System.out.println(val);
        }
    }
}
