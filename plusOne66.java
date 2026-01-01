import java.util.Arrays;

public class plusOne66 {
  public int[] plusOne(int[] digits) {
    int n = digits.length;

    // Move from the last digit to the first
    for (int i = n - 1; i >= 0; i--) {
      // If the digit is less than 9, just increment and return
      if (digits[i] < 9) {
        digits[i]++;
        return digits;
      }
      // If the digit is 9, it becomes 0
      digits[i] = 0;
    }

    // If we reach here, it means the number was all 9s (e.g., 999 -> 1000)
    // Create a new array with one extra slot
    int[] newNumber = new int[n + 1];

    // Set the most significant digit to 1. The rest are 0 by default.
    newNumber[0] = 1;

    return newNumber;
  }

  public static void main(String[] args) {
    plusOne66 solution = new plusOne66();

    // Test Case 1: Standard number
    int[] digits1 = { 1, 2, 3 };
    System.out.println("Input: " + Arrays.toString(digits1));
    System.out.println("Output: " + Arrays.toString(solution.plusOne(digits1)));

    // Test Case 2: Number ending in 9
    int[] digits2 = { 4, 3, 2, 9 };
    System.out.println("\nInput: " + Arrays.toString(digits2));
    System.out.println("Output: " + Arrays.toString(solution.plusOne(digits2)));

    int[] digits3 = { 9, 9, 9 };
    System.out.println("\nInput: " + Arrays.toString(digits3));
    System.out.println("Output: " + Arrays.toString(solution.plusOne(digits3)));
  }
}