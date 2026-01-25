import java.util.*;

public class Main {

    // The logic from the previous solution
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // 1. Sort meetings by time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));

        // 2. Initialize the set of people who know the secret
        boolean[] hasSecret = new boolean[n];
        hasSecret[0] = true;
        hasSecret[firstPerson] = true;

        int m = meetings.length;
        int i = 0;

        // 3. Process meetings in batches based on time
        while (i < m) {
            int j = i;
            int currentTime = meetings[i][2];

            // Identify the range [i, j] of meetings happening at currentTime
            while (j < m && meetings[j][2] == currentTime) {
                j++;
            }

            // 4. Build the graph for this specific time slice
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> peopleInBatch = new HashSet<>();

            for (int k = i; k < j; k++) {
                int p1 = meetings[k][0];
                int p2 = meetings[k][1];

                graph.computeIfAbsent(p1, x -> new ArrayList<>()).add(p2);
                graph.computeIfAbsent(p2, x -> new ArrayList<>()).add(p1);
                peopleInBatch.add(p1);
                peopleInBatch.add(p2);
            }

            // 5. BFS to spread the secret within this connected component
            Queue<Integer> queue = new LinkedList<>();
            for (int person : peopleInBatch) {
                if (hasSecret[person]) {
                    queue.offer(person);
                }
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                
                if (graph.containsKey(curr)) {
                    for (int neighbor : graph.get(curr)) {
                        if (!hasSecret[neighbor]) {
                            hasSecret[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                    // Optimization: Remove processed node to avoid cycles
                    graph.remove(curr); 
                }
            }

            // Move the pointer to the next time batch
            i = j;
        }

        // 6. Collect results
        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (hasSecret[k]) {
                result.add(k);
            }
        }
        return result;
    }

    // --- Main Method to Run the Code ---
    public static void main(String[] args) {
        Main solver = new Main();

        // Test Case 1
        int n1 = 6;
        int[][] meetings1 = { {1, 2, 5}, {2, 3, 8}, {1, 5, 10} };
        int firstPerson1 = 1;
        System.out.println("Test Case 1 Result: " + solver.findAllPeople(n1, meetings1, firstPerson1));
        // Expected: [0, 1, 2, 3, 5]

        // Test Case 2
        int n2 = 4;
        int[][] meetings2 = { {3, 1, 3}, {1, 2, 2}, {0, 3, 3} };
        int firstPerson2 = 3;
        System.out.println("Test Case 2 Result: " + solver.findAllPeople(n2, meetings2, firstPerson2));
        // Expected: [0, 1, 3]
        
        // Test Case 3
        int n3 = 5;
        int[][] meetings3 = { {3, 4, 2}, {1, 2, 1}, {2, 3, 1} };
        int firstPerson3 = 1;
        System.out.println("Test Case 3 Result: " + solver.findAllPeople(n3, meetings3, firstPerson3));
        // Expected: [1, 2, 3, 4]
    }
}