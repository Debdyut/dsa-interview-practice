import java.util.*;

/**
 * 🎯 LeetCode 207: Course Schedule
 * Modern Java 25+ Implementation showcasing Records, Local Type Inference (var),
 * Pattern Matching, and Sequenced Collections.
 */
public class CompleteImplementation {

    // =========================================================================
    // 🚀 APPROACH 1: Kahn's Algorithm (BFS / Topological Sort) - Optimal
    // =========================================================================
    public boolean canFinishKahns(int numCourses, int[][] prerequisites) {
        // Using var for local variable type inference (Java 10+)
        var adj = new ArrayList<List<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        var indegree = new int[numCourses];
        
        // Build Adjacency List and In-Degree Array
        for (var prereq : prerequisites) {
            int course = prereq[0];
            int prereqCourse = prereq[1];
            adj.get(prereqCourse).add(course); // prereqCourse -> course
            indegree[course]++;
        }
        
        // Using ArrayDeque as a modern SequencedCollection Queue (Java 21+)
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completedCourses = 0;
        
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            completedCourses++;
            
            for (var neighbor : adj.get(curr)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return completedCourses == numCourses;
    }

    // =========================================================================
    // 🌲 APPROACH 2: DFS Cycle Detection (Three-Color Marking)
    // =========================================================================
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        var adj = new ArrayList<List<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (var prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]);
        }
        
        // State array: 0 = UNVISITED, 1 = VISITING (in current path), 2 = VISITED (acyclic)
        var state = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, adj, state)) {
                    return false; // Cycle detected, cannot finish
                }
            }
        }
        return true;
    }

    private boolean hasCycle(int curr, List<List<Integer>> adj, int[] state) {
        state[curr] = 1; // Mark as VISITING
        
        for (var neighbor : adj.get(curr)) {
            if (state[neighbor] == 1) {
                return true; // Found a back-edge to a VISITING node -> Cycle!
            }
            if (state[neighbor] == 0) {
                if (hasCycle(neighbor, adj, state)) {
                    return true;
                }
            }
        }
        
        state[curr] = 2; // Mark as VISITED (fully processed and verified acyclic)
        return false;
    }

    // =========================================================================
    // 🧪 MODERN JAVA 25+ TEST FRAMEWORK
    // =========================================================================
    
    // Java Record (Java 16+) for clean, immutable test case representation
    record TestCase(int id, String name, int numCourses, int[][] prerequisites, boolean expected) {}

    public static void main(String[] args) {
        var solver = new CompleteImplementation();
        
        var testCases = List.of(
            new TestCase(1, "Basic Valid Schedule", 2, new int[][]{{1, 0}}, true),
            new TestCase(2, "Basic Immediate Cycle", 2, new int[][]{{1, 0}, {0, 1}}, false),
            new TestCase(3, "Multiple Prerequisites (Valid)", 4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}, true),
            new TestCase(4, "Disconnected Components (Valid)", 5, new int[][]{{1, 0}, {3, 2}}, true),
            new TestCase(5, "Complex Cycle (3 Nodes)", 3, new int[][]{{1, 0}, {2, 1}, {0, 2}}, false),
            new TestCase(6, "Single Course No Prerequisites", 1, new int[][]{}, true),
            new TestCase(7, "Large Linear Chain", 5, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}}, true)
        );

        System.out.println("🚀 RUNNING COURSE SCHEDULE TEST SUITE (MODERN JAVA 25+)");
        System.out.println("=".repeat(70));
        
        int passedKahns = 0;
        int passedDFS = 0;

        for (var tc : testCases) {
            System.out.printf("🔹 Test %d: %s (numCourses=%d, edges=%d)%n", 
                              tc.id(), tc.name(), tc.numCourses(), tc.prerequisites().length);
            
            // Benchmark Kahn's Algorithm
            long startKahns = System.nanoTime();
            boolean resKahns = solver.canFinishKahns(tc.numCourses(), tc.prerequisites());
            long timeKahns = (System.nanoTime() - startKahns) / 1000; // microseconds
            
            // Benchmark DFS
            long startDFS = System.nanoTime();
            boolean resDFS = solver.canFinishDFS(tc.numCourses(), tc.prerequisites());
            long timeDFS = (System.nanoTime() - startDFS) / 1000; // microseconds

            boolean kahnsOk = (resKahns == tc.expected());
            boolean dfsOk = (resDFS == tc.expected());

            if (kahnsOk) passedKahns++;
            if (dfsOk) passedDFS++;

            System.out.printf("   [Kahn's BFS]  Result: %-5s | Expected: %-5s | Status: %s (%3d µs)%n",
                              resKahns, tc.expected(), kahnsOk ? "✅ PASS" : "❌ FAIL", timeKahns);
            System.out.printf("   [DFS 3-Color] Result: %-5s | Expected: %-5s | Status: %s (%3d µs)%n",
                              resDFS, tc.expected(), dfsOk ? "✅ PASS" : "❌ FAIL", timeDFS);
            System.out.println("-".repeat(70));
        }

        System.out.printf("🎯 SUMMARY: Kahn's BFS [%d/%d passed] | DFS 3-Color [%d/%d passed]%n",
                          passedKahns, testCases.size(), passedDFS, testCases.size());
        System.out.println("=".repeat(70));
    }
}
