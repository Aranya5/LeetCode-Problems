public class long_corridor_2147 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String corridor1 = "SSPPSPS";
        String corridor2 = "PPSPSP";
        String corridor3 = "S";
        String corridor4 = "SPPSSPPSPS"; 

        System.out.println(sol.numberOfWays(corridor1)); // Expected: 3
        System.out.println(sol.numberOfWays(corridor2)); // Expected: 1
        System.out.println(sol.numberOfWays(corridor3)); // Expected: 0
        System.out.println(sol.numberOfWays(corridor4)); // Expected: 0 (Odd number of seats)
    }
}

class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = 1000_000_007;
        long result = 1;
        int prevSeatIndex = 0;
        int numSeats = 0;

        for (int i = 0; i < corridor.length(); i++) {
            char c = corridor.charAt(i);
            if (c == 'S') {
                numSeats++;
                
                // If this is the 3rd, 5th, 7th... seat, multiply result by the gap distance
                // The gap is the distance between the current seat (i) and the previous seat (prevSeatIndex)
                if (numSeats > 2 && numSeats % 2 == 1) {
                    result = (result * (i - prevSeatIndex)) % MOD;
                }
                
                prevSeatIndex = i;
            }
        }

        // Valid only if seats are > 0 and Even
        return numSeats > 0 && numSeats % 2 == 0 ? (int) result : 0;
    }
}