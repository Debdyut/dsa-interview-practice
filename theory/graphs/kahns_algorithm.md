# 🕸️ Kahn's Algorithm (BFS Topological Sort)

## 📖 1. Overview & Core Intuition

**Kahn’s Algorithm** is an elegant, iterative algorithm used to find a **Topological Ordering** of a Directed Acyclic Graph (DAG). It is also one of the most powerful techniques for **Cycle Detection** in directed graphs.

### 💡 The Real-World Analogy: Task Scheduling & Prerequisites
Imagine you are completing a university degree or building a complex software project:
1. Some tasks/courses have **no prerequisites** (e.g., Introduction to Programming). You can start these immediately.
2. Other tasks require certain prerequisites to be finished first (e.g., Machine Learning requires Calculus and Linear Algebra).
3. Every time you finish a prerequisite, you unlock subsequent courses. Once all prerequisites for a course are completed, that course becomes available to take.

Kahn's Algorithm simulates this exact process using **Breadth-First Search (BFS)** and a concept called **In-Degree**.

---

## 🧱 Prerequisite Knowledge

Before mastering Kahn's Algorithm, ensure you are comfortable with these core graph fundamentals:
* 🏗️ [adjacency_list.md](adjacency_list.md): Transforming edge lists into efficient lookup structures.
* 🌊 [bfs_traversal.md](bfs_traversal.md): Queue mechanics and level-by-level graph exploration.
* 🔢 [vertex_degrees.md](vertex_degrees.md): Tracking incoming dependencies and outgoing connections.

---

## 🔑 2. Key Concepts

* **Directed Acyclic Graph (DAG)**: A directed graph with no directed cycles. Topological sorting is *only* possible on DAGs.
* **In-Degree**: The number of incoming edges directed *into* a vertex. In our analogy, the in-degree of a course is the number of uncompleted prerequisites it has.
* **Source Node**: A vertex with `in-degree == 0`. These are tasks that have no dependencies and can be executed immediately.

---

## 🚀 3. Step-by-Step Algorithm Walkthrough

### Phase 1: Initialization
1. **Build Adjacency List**: Construct a directed graph representation `adj` from the given edges.
2. **Compute In-Degrees**: Create an array `indegree` of size $V$. For every directed edge $u \rightarrow v$, increment `indegree[v]`.
3. **Initialize Queue**: Scan the `indegree` array. Push all vertices with `indegree[i] == 0` into a BFS Queue `Q`.

### Phase 2: BFS Traversal
While the Queue `Q` is not empty:
1. Pop a vertex `u` from `Q`.
2. Add `u` to the `topologicalOrder` list (or increment a `visitedCount`).
3. For each neighbor `v` in `adj.get(u)`:
   * Decrement `indegree[v]` by 1 (simulating the completion of prerequisite `u`).
   * If `indegree[v]` becomes `0`, push `v` into `Q`.

### Phase 3: Cycle Detection
* Compare the number of visited nodes (`visitedCount`) with the total number of vertices $V$.
* If `visitedCount == V`, the graph is a valid DAG and `topologicalOrder` contains a valid ordering.
* If `visitedCount < V`, **a cycle exists** in the graph. The remaining nodes are trapped in a circular dependency, preventing their in-degrees from ever reaching 0.

---

## 📊 4. Visual Trace Example

Consider 4 courses: `0, 1, 2, 3` with prerequisites: `1 -> 0`, `2 -> 0`, `3 -> 1`, `3 -> 2`.
Graph Edges: $3 \rightarrow 1$, $3 \rightarrow 2$, $1 \rightarrow 0$, $2 \rightarrow 0$.

| Step | Action | Queue (`Q`) | `indegree` Array `[0, 1, 2, 3]` | `topoOrder` List |
| :--- | :--- | :--- | :--- | :--- |
| **0** | Initial State | `[3]` | `[2, 1, 1, 0]` | `[]` |
| **1** | Pop `3`, decrement neighbors `1, 2` | `[1, 2]` | `[2, 0, 0, 0]` | `[3]` |
| **2** | Pop `1`, decrement neighbor `0` | `[2]` | `[1, 0, 0, 0]` | `[3, 1]` |
| **3** | Pop `2`, decrement neighbor `0` | `[0]` | `[0, 0, 0, 0]` | `[3, 1, 2]` |
| **4** | Pop `0`, no neighbors | `[]` | `[0, 0, 0, 0]` | `[3, 1, 2, 0]` |

**Result**: All 4 nodes visited. Valid topological ordering: `[3, 1, 2, 0]`.

---

## 💻 5. Master Code Templates

### Java Implementation
```java
import java.util.*;

public class KahnsAlgorithm {

    /**
     * Finds a topological ordering of a directed graph.
     * @param V Number of vertices (0 to V-1)
     * @param prerequisites List of directed edges [u, v] representing u -> v
     * @return Topological order if DAG, or empty array if cycle detected.
     */
    public int[] kahnTopologicalSort(int V, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        
        int[] indegree = new int[V];
        
        // 1. Build Adjacency List and In-Degree array
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            indegree[v]++;
        }
        
        // 2. Initialize Queue with all 0-indegree vertices
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        
        int[] topoOrder = new int[V];
        int index = 0;
        
        // 3. BFS Traversal
        while (!q.isEmpty()) {
            int u = q.poll();
            topoOrder[index++] = u;
            
            for (int v : adj.get(u)) {
                indegree[v]--; // u is completed, remove prerequisite for v
                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        
        // 4. Cycle Check
        if (index == V) {
            return topoOrder; // Successfully visited all nodes (DAG)
        }
        
        return new int[0]; // Cycle detected!
    }
}
```

### Python Implementation
```python
from collections import deque
from typing import List

class KahnsAlgorithm:
    def kahn_topological_sort(self, V: int, prerequisites: List[List[int]]) -> List[int]:
        adj = [[] for _ in range(V)]
        indegree = [0] * V
        
        # 1. Build Adjacency List and In-Degree array
        for u, v in prerequisites:
            adj[u].append(v)
            indegree[v] += 1
            
        # 2. Initialize Queue with all 0-indegree vertices
        q = deque([i for i in range(V) if indegree[i] == 0])
        
        topo_order = []
        
        # 3. BFS Traversal
        while q:
            u = q.popleft()
            topo_order.append(u)
            
            for v in adj[u]:
                indegree[v] -= 1
                if indegree[v] == 0:
                    q.append(v)
                    
        # 4. Cycle Check
        if len(topo_order) == V:
            return topo_order # Valid DAG
        return [] # Cycle detected
```

---

## ⏱️ 6. Complexity Analysis

* **Time Complexity**: $O(V + E)$
  * Building the adjacency list and indegree array takes $O(V + E)$.
  * Each vertex is pushed to and popped from the queue exactly once ($O(V)$).
  * For each popped vertex, we iterate through its outgoing edges ($O(E)$ total across the entire graph).
* **Space Complexity**: $O(V + E)$
  * Adjacency list stores $V$ vertices and $E$ edges: $O(V + E)$.
  * `indegree` array and Queue `Q` take $O(V)$ space.

---

## 🔍 7. Pattern Recognition: When to Apply Kahn's?

### 🚩 Key Trigger Words in Problem Statements
If you see any of the following phrases in an interview question, immediately think **Kahn's Algorithm**:
1. *"Prerequisites"*, *"Dependencies"*, *"Task Scheduling"*
2. *"Find the order of execution"*, *"Can all tasks be finished?"*
3. *"Alien language"*, *"Given a dictionary of words, find the character order"*
4. *"Build system"*, *"Package installation order"*, *"Compilation dependencies"*
5. *"Deadlock detection"*, *"Circular dependency check"*

### 🎯 Kahn's Algorithm vs. DFS Topological Sort

| Scenario | Use Kahn's (BFS) | Use DFS | Reason |
| :--- | :--- | :--- | :--- |
| **Cycle Detection** | ✅ Excellent | ✅ Excellent | Both work perfectly. Kahn's is often preferred in interviews because it's iterative. |
| **Lexicographically Smallest Order** | ✅ **Mandatory** | ❌ Difficult | Replacing Kahn's `Queue` with a `PriorityQueue` (Min-Heap) automatically yields the smallest/alphabetical ordering. DFS cannot do this easily. |
| **All Possible Topological Orders** | ❌ Difficult | ✅ **Mandatory** | Backtracking with DFS is required to explore all valid permutations. |
| **Deep/Large Graphs** | ✅ Excellent | ❌ Risky | Kahn's avoids recursion stack overflow ($O(V)$ auxiliary heap space vs call stack space). |

---

## 📚 8. Master Practice List

Here is a curated list of LeetCode problems categorized by how Kahn's algorithm is applied:

### Level 1: Direct Application (Fundamentals)
* 🔗 [LeetCode 207: Course Schedule](https://leetcode.com/problems/course-schedule/) *(Cycle Detection)*
* 🔗 [LeetCode 210: Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) *(Standard Topological Ordering)*

### Level 2: Advanced Variations & Priority Queue
* 🔗 [LeetCode 269: Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) *(Premium / LintCode 892)*
  * *Twist*: You must first compare adjacent words to build the graph edges between characters, then run Kahn's.
* 🔗 [LeetCode 1136: Parallel Courses](https://leetcode.com/problems/parallel-courses/) *(Premium)*
  * *Twist*: Track the level/depth of BFS to find the minimum number of semesters needed to complete all courses.
* 🔗 [LeetCode 2115: Find All Possible Recipes from Given Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/)
  * *Twist*: Initial queue contains the `supplies` (indegree 0). Recipes are nodes whose indegree reaches 0.

### Level 3: Master / Complex Graph Construction
* 🔗 [LeetCode 310: Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees/)
  * *Twist*: An undirected graph variation of Kahn's. Start with `degree == 1` (leaves) and peel them inward until 1 or 2 center nodes remain.
* 🔗 [LeetCode 1203: Sort Items by Groups Respecting Dependencies](https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/)
  * *Twist*: Requires running Kahn's algorithm twice—first at the group level, and then at the individual item level within each group.
