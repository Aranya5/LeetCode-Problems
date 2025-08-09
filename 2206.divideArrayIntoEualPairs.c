#include <stdio.h>
#include <stdbool.h>

bool divideArray(int *nums, int numsSize)
{
  int freq[501] = {0}; // given constraints: 1 <= nums[i] <= 500

  // Count frequency of each number
  for (int i = 0; i < numsSize; i++)
  {
    freq[nums[i]]++;
  }

  // Check if every number appears an even number of times
  for (int i = 0; i < 501; i++)
  {
    if (freq[i] % 2 != 0)
    {
      return false;
    }
  }

  return true;
}

int main()
{
  int nums[] = {3, 2, 3, 2, 7, 2, 7, 2}; // Example
  int size = sizeof(nums) / sizeof(nums[0]);

  if (divideArray(nums, size))
  {
    printf("true\n");
  }
  else
  {
    printf("false\n");
  }

  return 0;
}
