#include <stdio.h>

int mySqrt(int x) {
    if (x == 0 || x == 1) return x;

    int low = 1, high = x / 2, ans = 0;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        long long sq = (long long)mid * mid; // avoid overflow

        if (sq == x) {
            return mid;
        } else if (sq < x) {
            ans = mid;  // possible answer
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return ans;
}

int main() {
    int x;
    printf("Enter a non-negative integer: ");
    scanf("%d", &x);

    int result = mySqrt(x);
    printf("Square root (floor) of %d is %d\n", x, result);

    return 0;
}