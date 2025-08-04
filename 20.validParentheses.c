#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX 1000

bool isMatchingPair(char open, char close) {
    return (open == '(' && close == ')') ||
           (open == '{' && close == '}') ||
           (open == '[' && close == ']');
}

bool isValid(char *s) {
    char stack[MAX];
    int top = -1;

    for (int i = 0; s[i] != '\0'; i++) {
        char ch = s[i];

        // If opening bracket, push to stack
        if (ch == '(' || ch == '{' || ch == '[') {
            stack[++top] = ch;
        }
        // If closing bracket
        else if (ch == ')' || ch == '}' || ch == ']') {
            if (top == -1 || !isMatchingPair(stack[top--], ch)) {
                return false;
            }
        }
    }

    return top == -1; // Stack should be empty if valid
}

int main() {
    char s[MAX];
    printf("Enter a string of brackets: ");
    scanf("%s", s);

    if (isValid(s)) {
        printf("Valid\n");
    } else {
        printf("Invalid\n");
    }

    return 0;
}