# 🤿 Depth-First Search (DFS) Traversal

## 📖 1. Overview & Core Intuition

**Depth-First Search (DFS)** is a fundamental graph traversal algorithm that explores as **deeply as possible** along each branch before backtracking.

### 💡 The Real-World Analogy: Exploring a Maze
Imagine entering a labyrinth. You decide on a simple strategy:
1. Whenever you reach a fork, you pick the first path and keep walking until you hit a dead end.
2. Once you hit a dead end, you back up (backtrack) to the previous fork and try the next unvisited path.
3. You leave breadcrumbs (`visited` set) so you never walk down the same path twice.

Unlike BFS (which explores concentric circles level-by-level), DFS plunges straight down to the absolute bottom of a graph tree before looking at sibling nodes.

---

## ⚙️ 2. Core Mechanism: The Call Stack (LIFO)

DFS relies entirely on a **Stack** data structure (Last-In, First-Out / LIFO).
* **Recursive DFS**: Uses the implicit system **Call Stack**. Every time `dfs(neighbor)` is called, the current function pauses and is pushed onto the call stack.
* **Iterative DFS**: Uses an explicit `Stack<Integer>` (or `Deque`) in heap memory to simulate the exact same process without risking call stack overflow.

---

## 🚀 3. Step-by-Step Algorithm Walkthrough (Recursive)

1. **Initialize**: Create a boolean array `visited[]`.
2. **Start**: Call `dfs(source, adj, visited)`.
3. **Execution (`dfs(curr)`)**:
   * Mark `visited[curr] = true`.
   * Process `curr` (e.g., print it, add to path).
   * Iterate through all neighbors `v` of `curr`:
     * If `v` is NOT visited (`!visited[v]`), recursively call `dfs(v, adj, visited)`.

---

## 💻 4. Master Code Templates

### Java Implementation (Recursive & Iterative)
```java
import java.util.*;

public class DFSTraversal {

    /**
     * 1. RECURSIVE DFS Traversal
     */
    public List<Integer> dfsRecursive(int V, List<List<Integer>> adj, int source) {
        List<Integer> dfsOrder = new ArrayList<>();
        boolean[] visited = new boolean[V];
        
        dfsHelper(source, adj, visited, dfsOrder);
        return dfsOrder;
    }

    private void dfsHelper(int curr, List<List<Integer>> adj, boolean[] visited, List<Integer> result) {
        visited[curr] = true; // Mark visited
        result.add(curr);     // Process node
        
        for (int neighbor : adj.get(curr)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, adj, visited, result); // Recurse
            }
        }
    }

    /**
     * 2. ITERATIVE DFS Traversal (Using Explicit Stack)
     */
    public List<Integer> dfsIterative(int V, List<List<Integer>> adj, int source) {
        List<Integer> dfsOrder = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>(); // Modern Stack implementation
        
        stack.push(source);
        
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            
            if (!visited[curr]) {
                visited[curr] = true;
                dfsOrder.add(curr);
                
                // To match recursive DFS order, push neighbors in reverse order
                List<Integer> neighbors = adj.get(curr);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        
        return dfsOrder;
    }
}
```

### Python Implementation
```python
from typing import List

class DFSTraversal:
    def dfs_recursive(self, V: int, adj: List[List[int]], source: int) -> List[int]:
        dfs_order = []
        visited = [False] * V
        
        def dfs_helper(curr: int):
            visited[curr] = True
            dfs_order.append(curr)
            
            for neighbor in adj[curr]:
                if not visited[neighbor]:
                    dfs_helper(neighbor)
                    
        dfs_helper(source)
        return dfs_order
```

---

## ⏱️ 5. Complexity Analysis

* **Time Complexity**: $O(V + E)$
  * Every vertex is marked visited exactly once ($O(V)$).
  * We explore the outgoing edges of each vertex exactly once ($O(E)$ total).
* **Space Complexity**: $O(V)$
  * The `visited` array takes $O(V)$ space.
  * **Recursion Stack**: In the worst-case (a linear chain graph $A \rightarrow B \rightarrow C \rightarrow D$), the call stack reaches a depth of $O(V)$. In a perfectly balanced tree, the stack depth is $O(\log V)$.

---

## 🔍 6. Pattern Recognition: When to Use DFS?

### 🚩 Key Trigger Words in Problem Statements
1. *"Find all possible paths/permutations"*, *"Backtracking"*
2. *"Connected components"*, *"Number of Islands"*, *"Flood Fill"*
3. *"Detect a cycle in a directed/undirected graph"*
4. *"Topological sorting"* (via post-order stack reversal)
5. *"Maze solving"*, *"Can you reach the destination?"*
