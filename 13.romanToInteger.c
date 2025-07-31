#include <stdio.h>
#include <string.h>

int value(char c) {
    switch(c) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default: return 0;
    }
}

int romanToInt(char *s) {
    int total = 0;
    int prev = 0;
    int len = strlen(s);

    for (int i = len - 1; i >= 0; i--) {
        int curr = value(s[i]);
        if (curr < prev) {
            total -= curr;
        } else {
            total += curr;
        }
        prev = curr;
    }

    return total;
}

int main() {
    char roman[] = "MCMXCIV";  // Example input
    int result = romanToInt(roman);
    printf("Integer value of %s is %d\n", roman, result);
    return 0;
}
