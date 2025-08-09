#include <stdio.h>
#include <stdbool.h>

bool isPowerOfTwo(int n) {
    // A power of two has exactly one bit set in binary representation.
    return (n > 0) && ((n & (n - 1)) == 0);
}

int main() {
    int n;
    printf("Enter an integer: ");
    scanf("%d", &n);

    if (isPowerOfTwo(n))
        printf("true\n");
    else
        printf("false\n");

    return 0;
}