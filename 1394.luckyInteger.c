#include <stdio.h>
int findLucky(int *arr, int arrSize)
{
  int freq[501] = {0};
  for (int i = 0; i < arrSize; i++)
  {
    freq[arr[i]]++;
  }
  int lucky = -1;
  for (int i = 1; i < 501; i++)
  {
    if (i == freq[i])
    {
      lucky = i;
    }
  }
  return lucky;
}

int main()
{
  int arr[] = {2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 6, 6}; // Example input
  int size = sizeof(arr) / sizeof(arr[0]);

  int result = findLucky(arr, size);
  printf("%d\n", result); // Output the lucky integer or -1 if none found

  return 0;
}