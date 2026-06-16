public class ProcessStringWithSpecialOperations3612 {

    public static String processStr(String s) {

        StringBuilder result = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (Character.isLowerCase(ch)) {
                result.append(ch);
            }
            else if (ch == '*') {
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            }
            else if (ch == '#') {
                result.append(result.toString());
            }
            else if (ch == '%') {
                result.reverse();
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String s1 = "a#b%*";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + processStr(s1));

        String s2 = "z*#";
        System.out.println("\nInput: " + s2);
        System.out.println("Output: " + processStr(s2));
    }
}