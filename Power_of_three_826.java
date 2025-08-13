public class Power_of_three_826 {
    public static boolean isPowerOfThree(int n) {
        if (n <= 0) return false;  // Negative numbers & zero can't be powers of 3
        while (n % 3 == 0) {       // Keep dividing by 3
            n /= 3;
        }
        return n == 1;             // If reduced to 1, it's a power of 3
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27));  // true
        System.out.println(isPowerOfThree(9));   // true
        System.out.println(isPowerOfThree(45));  // false
        System.out.println(isPowerOfThree(1));   // true (3^0)
    }
}
