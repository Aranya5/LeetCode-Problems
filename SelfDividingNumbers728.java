import java.util.ArrayList;
import java.util.List;

public class SelfDividingNumbers728 {

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();

        while (left <= right) {
            if (checkSelfDivide(left)) {
                list.add(left);
            }
            left++;
        }

        return list;
    }

    public static boolean checkSelfDivide(int n) {
        int m = n;

        while (m > 0) {
            int rem = m % 10;

            if (rem == 0 || n % rem != 0) {
                return false;
            }

            m = m / 10;
        }

        return true;
    }

    public static void main(String[] args) {
        int left = 1;
        int right = 22;

        List<Integer> result = selfDividingNumbers(left, right);

        System.out.println(result);
    }
}