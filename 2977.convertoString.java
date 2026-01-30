import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // Trie Node definition
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int id = -1; // Stores the mapped ID of the string ending here
    }

    private void insert(TrieNode root, String s, int id) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.id = id;
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        // 1. Map all unique strings to integer IDs
        Map<String, Integer> stringToId = new HashMap<>();
        int idCounter = 0;
        
        for (String s : original) {
            if (!stringToId.containsKey(s)) stringToId.put(s, idCounter++);
        }
        for (String s : changed) {
            if (!stringToId.containsKey(s)) stringToId.put(s, idCounter++);
        }
        
        // 2. Build the graph and Trie
        int count = stringToId.size();
        long[][] dist = new long[count][count];
        long INF = Long.MAX_VALUE / 2; // Use a safe infinity to prevent overflow
        
        // Initialize distances
        for (long[] row : dist) Arrays.fill(row, INF);
        for (int i = 0; i < count; i++) dist[i][i] = 0;
        
        TrieNode root = new TrieNode();
        // Insert strings into Trie
        for (Map.Entry<String, Integer> entry : stringToId.entrySet()) {
            insert(root, entry.getKey(), entry.getValue());
        }
        
        // Populate initial costs from input
        for (int i = 0; i < original.length; i++) {
            int u = stringToId.get(original[i]);
            int v = stringToId.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        // 3. Floyd-Warshall to find all-pairs shortest paths
        for (int k = 0; k < count; k++) {
            for (int i = 0; i < count; i++) {
                if (dist[i][k] == INF) continue; // Optimization
                for (int j = 0; j < count; j++) {
                    if (dist[k][j] == INF) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        // 4. Dynamic Programming on source string
        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;
            
            // Option 1: Characters match directly, cost 0
            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }
            
            // Option 2: Try to match substrings using the Trie
            TrieNode p1 = root; // Pointer for source
            TrieNode p2 = root; // Pointer for target
            
            for (int j = i; j < n; j++) {
                int charS = source.charAt(j) - 'a';
                int charT = target.charAt(j) - 'a';
                
                // Advance pointers
                if (p1.children[charS] == null || p2.children[charT] == null) {
                    break; // Substring path doesn't exist in our dictionary
                }
                p1 = p1.children[charS];
                p2 = p2.children[charT];
                
                // If both substrings form valid IDs, check transformation cost
                if (p1.id != -1 && p2.id != -1) {
                    long costVal = dist[p1.id][p2.id];
                    if (costVal < INF) {
                        dp[j + 1] = Math.min(dp[j + 1], dp[i] + costVal);
                    }
                }
            }
        }
        
        return dp[n] == INF ? -1 : dp[n];
    }
}
