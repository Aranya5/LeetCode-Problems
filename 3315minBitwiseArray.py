def find_min_array(nums):
    ans = []
    
    for p in nums:
        if p == 2:
            ans.append(-1)
            continue
        
        # Count the number of trailing ones
        # Example: 11 (1011) -> 2 trailing ones
        temp = p
        trailing_ones = 0
        while (temp & 1):
            trailing_ones += 1
            temp >>= 1
            
        # We flip the highest bit of the trailing ones sequence
        # This corresponds to 2^(trailing_ones - 1)
        subtractor = 1 << (trailing_ones - 1)
        
        ans.append(p - subtractor)
        
    return ans

# Example usage:
# nums = [2, 5, 7, 11]
# print(find_min_array(nums)) 
# Output: [-1, 4, 3, 9]