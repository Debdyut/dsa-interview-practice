# 🌊 Breadth-First Search (BFS) Traversal

## 📖 1. Overview & Core Intuition

**Breadth-First Search (BFS)** is a foundational graph traversal algorithm that explores nodes **level by level** (or in concentric circles) moving outward from a starting source vertex.

### 💡 The Real-World Analogy: Dropping a Stone in Water
Imagine dropping a stone into a calm pond. Ripples expand outward in perfect concentric circles. 
* **Level 0**: The exact point of impact (Source Vertex).
* **Level 1**: All water molecules immediately adjacent to the impact.
* **Level 2**: Molecules adjacent to Level 1, and so on.

BFS guarantees that you visit all nodes at distance $k$ from the source before visiting any node at distance $k+1$. This property makes BFS the undisputed algorithm of choice for finding the **Shortest Path** in unweighted graphs.

---

## ⚙️ 2. Core Mechanism: The Queue (FIFO)

BFS relies entirely on a **Queue** data structure (First-In, First-Out).
1. When we visit a node, we add all of its unvisited neighbors to the back of the queue.
2. We pull the next node to process from the front of the queue.
3. To prevent infinite loops in graphs with cycles, we maintain a `visited` boolean array (or `Set`).

---

## 🚀 3. Step-by-Step Algorithm Walkthrough

1. **Initialize**: Create a Queue `Q` and a boolean array `visited[]`.
2. **Start**: Push the starting vertex `source` into `Q` and mark `visited[source] = true`.
3. **Loop**: While `Q` is not empty:
   * Pop the front vertex `u` from `Q`.
   * Process `u` (e.g., print it, add to result list).
   * Iterate through all neighbors `v` of `u`:
     * If `v` is NOT visited (`!visited[v]`):
       * Mark `visited[v] = true`.
       * Push `v` into `Q`.

---

## 💻 4. Master Code Templates

### Java Implementation
```java
import java.util.*;

public class BFSTraversal {

    /**
     * Performs BFS traversal on a graph starting from a source vertex.
     * @param V Number of vertices (0 to V-1)
     * @param adj Adjacency list representation of the graph
     * @param source Starting vertex
     * @return List of vertices in BFS traversal order
     */
    public List<Integer> bfs(int V, List<List<Integer>> adj, int source) {
        List<Integer> bfsOrder = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        // 1. Initialize source
        queue.offer(source);
        visited[source] = true;
        
        // 2. Main BFS Loop
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            bfsOrder.add(curr);
            
            // 3. Explore neighbors
            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true; // Mark visited immediately before pushing
                    queue.offer(neighbor);
                }
            }
        }
        
        return bfsOrder;
    }
}
```

### Python Implementation
```python
from collections import deque
from typing import List

class BFSTraversal:
    def bfs(self, V: int, adj: List[List[int]], source: int) -> List[int]:
        bfs_order = []
        visited = [False] * V
        queue = deque([source])
        
        visited[source] = True
        
        while queue:
            curr = queue.popleft()
            bfs_order.append(curr)
            
            for neighbor in adj[curr]:
                if not visited[neighbor]:
                    visited[neighbor] = True
                    queue.append(neighbor)
                    
        return bfs_order
```

---

## ⏱️ 5. Complexity Analysis

* **Time Complexity**: $O(V + E)$
  * Every vertex is enqueued and dequeued exactly once ($O(V)$).
  * We iterate through the adjacency list of each vertex, examining all edges ($O(E)$ total).
* **Space Complexity**: $O(V)$
  * The `visited` array takes $O(V)$ space.
  * The Queue can hold at most $O(V)$ vertices (in the worst case of a star graph where all nodes are connected to the source).

---

## 🔍 6. Pattern Recognition: When to Use BFS?

### 🚩 Key Trigger Words in Problem Statements
1. *"Shortest path in an unweighted graph"*, *"Minimum number of steps/hops"*
2. *"Level order traversal"*, *"Find all nodes at distance K"*
3. *"Word Ladder"*, *"Minimum genetic mutation"*
4. *"Rotting Oranges"*, *"Matrix multi-source expansion"* (Multi-source BFS)
5. *"Web crawling"*, *"Social network connections (Degrees of separation)"*
