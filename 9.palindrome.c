#include <stdio.h>
#include <stdbool.h>

// Function to check if an integer is a palindrome
bool isPalindrome(int x)
{
  if (x < 0)
    return false; // Negative numbers are not palindromes

  long long rev = 0;
  int original = x;

  while (x > 0)
  {
    int rem = x % 10;
    rev = rev * 10 + rem;
    x /= 10;
  }

  return rev == original;
}

int main()
{
  int test_cases[] = {121, -121, 10, 0, 12321, 9987654321};
  int n = sizeof(test_cases) / sizeof(test_cases[0]);

  for (int i = 0; i < n; i++)
  {
    int x = test_cases[i];
    if (isPalindrome(x))
    {
      printf("%d is a palindrome.\n", x);
    }
    else
    {
      printf("%d is not a palindrome.\n", x);
    }
  }

  return 0;
}
