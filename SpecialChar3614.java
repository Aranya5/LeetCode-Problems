public class SpecialChar3614 {

    public static char processStr(String s, long k) {

        int n = s.length();
        long[] len = new long[n];

        long currLen = 0;

        // Calculate length after each operation
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                currLen++;
            } 
            else if (ch == '*') {
                if (currLen > 0) {
                    currLen--;
                }
            } 
            else if (ch == '#') {
                currLen *= 2;
            }
            // '%' does not change length

            len[i] = currLen;
        }

        // If k is out of bounds
        if (k >= currLen) {
            return '.';
        }

        // Traverse backwards
        for (int i = n - 1; i >= 0; i--) {

            char ch = s.charAt(i);
            long prevLen = (i == 0) ? 0 : len[i - 1];

            if (ch >= 'a' && ch <= 'z') {

                if (k == prevLen) {
                    return ch;
                }
            }

            else if (ch == '#') {

                long oldLen = prevLen;

                if (oldLen > 0) {
                    k %= oldLen;
                }
            }

            else if (ch == '%') {

                long currentLen = len[i];
                k = currentLen - 1 - k;
            }

            // '*' needs no action during backward traversal
        }

        return '.';
    }

    public static void main(String[] args) {

        String s1 = "a#b%*";
        long k1 = 1;

        System.out.println("Input: " + s1 + ", k = " + k1);
        System.out.println("Output: " + processStr(s1, k1));

        String s2 = "cd%#*#";
        long k2 = 3;

        System.out.println("\nInput: " + s2 + ", k = " + k2);
        System.out.println("Output: " + processStr(s2, k2));

        String s3 = "z*#";
        long k3 = 0;

        System.out.println("\nInput: " + s3 + ", k = " + k3);
        System.out.println("Output: " + processStr(s3, k3));
    }
}
