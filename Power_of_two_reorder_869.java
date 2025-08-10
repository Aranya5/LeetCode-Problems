

public class Power_of_two_reorder_869 {

    public boolean reorderedPowerOf2(int n) {
        int count = counter(n);

        // Check all powers of two up to 2^29
        for (int i = 0; i < 30; ++i) {
            if (counter(1 << i) == count) {
                return true;
            }
        }
        return false;
    }

    // Encodes digit counts into an integer
    private int counter(int n) {
        int count = 0;
        while (n > 0) {
            int digit = n % 10;
            count += Math.pow(10, digit);
            n /= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        Power_of_two_reorder_869 sol = new Power_of_two_reorder_869();

        int[] testNumbers = {1, 10, 16, 46, 821, 128, 24, 125};
        for (int num : testNumbers) {
            System.out.println(num + " → " + sol.reorderedPowerOf2(num));
        }
    }
}
