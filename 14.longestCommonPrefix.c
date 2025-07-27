#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *longestCommonPrefix(char **strs, int strsSize)
{
  if (strsSize == 0)
    return "";

  // Allocate a buffer large enough to hold the prefix (same size as the first string)
  char *prefix = malloc(strlen(strs[0]) + 1);
  if (!prefix)
    return "";

  strcpy(prefix, strs[0]);

  for (int i = 1; i < strsSize; i++)
  {
    int j = 0;
    while (prefix[j] && strs[i][j] && prefix[j] == strs[i][j])
    {
      j++;
    }
    prefix[j] = '\0'; // Truncate prefix to common part

    // Early exit: if prefix becomes empty
    if (prefix[0] == '\0')
    {
      break;
    }
  }

  return prefix;
}

int main()
{
  char *strs[] = {"flower", "flow", "flight"};
  int size = sizeof(strs) / sizeof(strs[0]);

  char *result = longestCommonPrefix(strs, size);
  printf("Longest Common Prefix: %s\n", result);

  free(result); // Don't forget to free the allocated memory
  return 0;
}
