# 🌲 DFS Cycle Detection: Three-Color Marking

## 📖 1. Overview & Core Intuition

**Three-Color Marking** is an elegant, canonical Depth-First Search (DFS) technique used to detect **Cycles** in directed graphs and generate **Topological Orderings**.

### 💡 The Real-World Analogy: Exploring a Labyrinth with Colored Chalk
Imagine exploring a massive, dark maze of one-way tunnels. To prevent getting hopelessly lost or walking in circles, you carry three colors of chalk:

1. ⚪ **White Chalk (`0 = UNVISITED`)**: Tunnels you have never stepped foot in.
2. 🟡 **Yellow Chalk (`1 = VISITING`)**: The active trail you are currently walking down. You mark the entrance of a tunnel yellow as you step into it.
3. 🟢 **Green Chalk (`2 = VISITED`)**: Tunnels you have fully explored to their absolute end and verified to be safe dead-ends (no loops). You mark a tunnel green on your way out.

**The Cycle Trap**: If you are walking down a tunnel and encounter a door marked with 🟡 **Yellow Chalk**, you know with 100% certainty that you have walked in a circle! You have found a loop pointing back to your active trail.

---

## 🧱 Prerequisite Knowledge

Before mastering DFS Three-Color Marking, ensure you are comfortable with these core graph fundamentals:
* 🏗️ [adjacency_list.md](adjacency_list.md): Transforming edge lists into efficient lookup structures.
* 🤿 [dfs_traversal.md](dfs_traversal.md): Call stack mechanics and deep branch exploration.
* 📐 [graph_edge_classifications.md](graph_edge_classifications.md): Understanding Tree, Forward, Cross, and Back edges.

---

## ⚙️ 2. Core Mechanism: The Three States

In code, we represent these three chalk colors using an integer array `int[] state` of size $V$:

```
                  ┌──> [ Neighbor (State 2: VISITED) ]  (Safe: Ignored)
                  │
[ Current Node ] ─┼──> [ Neighbor (State 0: UNVISITED) ] (Recurse: dfs(neighbor))
(State 1: VISITING)│
                  └──> [ Neighbor (State 1: VISITING) ]  🚨 CYCLE DETECTED! (Back-edge)
```

| State | Value | Meaning | Action When Encountered |
| :--- | :--- | :--- | :--- |
| `UNVISITED` | `0` | Node has never been seen. | Call `dfs(node)` to explore it. |
| `VISITING` | `1` | Node is part of the current active DFS recursion stack. | **🚨 Cycle Detected!** Return `true`. |
| `VISITED` | `2` | Node and all its descendants are fully processed (verified acyclic). | Safe node. Skip and continue. |

---

## 🚀 3. Step-by-Step Algorithm Walkthrough

### Phase 1: Outer Loop (Handling Disconnected Components)
1. Initialize an array `state` of size $V$ with all `0`s.
2. Loop through every vertex `i` from `0` to `V-1`.
3. If `state[i] == 0`, call `hasCycle(i, adj, state)`. If it returns `true`, immediately terminate and report a cycle.

### Phase 2: Recursive DFS (`hasCycle`)
1. **Mark Active**: Set `state[curr] = 1` (Yellow / VISITING).
2. **Explore Neighbors**: For each `neighbor` in `adj.get(curr)`:
   * If `state[neighbor] == 1` $\rightarrow$ Found a back-edge! Return `true`.
   * If `state[neighbor] == 0` $\rightarrow$ Recurse `hasCycle(neighbor, adj, state)`. If the recursive call returns `true`, bubble up `true`.
3. **Mark Complete**: Set `state[curr] = 2` (Green / VISITED).
4. Return `false` (No cycles found from this node).

---

## 📊 4. Visual Trace Example

Consider a cyclic graph with 3 nodes: `0 -> 1 -> 2 -> 0`.

| Step | Action | DFS Call Stack | `state` Array `[0, 1, 2]` | Status / Outcome |
| :--- | :--- | :--- | :--- | :--- |
| **0** | Initial State | `[]` | `[0, 0, 0]` | Start outer loop at node `0` |
| **1** | `dfs(0)`, mark `0` VISITING | `[0]` | `[1, 0, 0]` | Explore neighbor `1` (`state == 0`) |
| **2** | `dfs(1)`, mark `1` VISITING | `[0, 1]` | `[1, 1, 0]` | Explore neighbor `2` (`state == 0`) |
| **3** | `dfs(2)`, mark `2` VISITING | `[0, 1, 2]` | `[1, 1, 1]` | Explore neighbor `0` (`state == 1`) |
| **4** | Check neighbor `0` | `[0, 1, 2]` | `[1, 1, 1]` | 🚨 `state[0] == 1` $\rightarrow$ **CYCLE!** |

---

## 💻 5. Master Code Templates

### Java Implementation
```java
import java.util.*;

public class DFSThreeColor {

    /**
     * Checks if a directed graph contains a cycle using 3-color marking.
     * @param V Number of vertices (0 to V-1)
     * @param adj Adjacency list representation
     * @return true if cycle exists, false if graph is a valid DAG.
     */
    public boolean containsCycle(int V, List<List<Integer>> adj) {
        int[] state = new int[V]; // 0 = UNVISITED, 1 = VISITING, 2 = VISITED
        
        // Outer loop to handle disconnected components
        for (int i = 0; i < V; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, adj, state)) {
                    return true; // Cycle found in this component
                }
            }
        }
        return false; // Verified all components are acyclic
    }

    private boolean hasCycle(int curr, List<List<Integer>> adj, int[] state) {
        state[curr] = 1; // 🟡 Mark as VISITING (Active Path)
        
        for (int neighbor : adj.get(curr)) {
            if (state[neighbor] == 1) {
                return true; // 🚨 Back-edge to an active node -> Cycle!
            }
            if (state[neighbor] == 0) {
                if (hasCycle(neighbor, adj, state)) {
                    return true;
                }
            }
        }
        
        state[curr] = 2; // 🟢 Mark as VISITED (Fully explored & verified acyclic)
        return false;
    }
}
```

### Python Implementation
```python
from typing import List

class DFSThreeColor:
    def contains_cycle(self, V: int, adj: List[List[int]]) -> bool:
        # 0 = UNVISITED, 1 = VISITING, 2 = VISITED
        state = [0] * V
        
        def has_cycle(curr: int) -> bool:
            state[curr] = 1 # 🟡 Mark VISITING
            
            for neighbor in adj[curr]:
                if state[neighbor] == 1:
                    return True # 🚨 Back-edge -> Cycle!
                if state[neighbor] == 0:
                    if has_cycle(neighbor):
                        return True
                        
            state[curr] = 2 # 🟢 Mark VISITED
            return False
            
        # Outer loop for disconnected components
        for i in range(V):
            if state[i] == 0:
                if has_cycle(i):
                    return True
        return False
```

---

## ⏱️ 6. Complexity Analysis

* **Time Complexity**: $O(V + E)$
  * Every vertex is visited at most once ($O(V)$). Once marked `2` (VISITED), it is never explored again.
  * We iterate through the adjacency list of each vertex exactly once ($O(E)$ total).
* **Space Complexity**: $O(V + E)$
  * Adjacency list takes $O(V + E)$ space.
  * The `state` array takes $O(V)$ space.
  * The recursion call stack can reach a depth of $O(V)$ in the worst case (a linear chain graph).

---

## 🔍 7. Pattern Recognition: When to Use DFS 3-Color?

### 🚩 Key Trigger Scenarios in Interviews
1. **Reconstructing the Exact Cycle Path**: If the interviewer asks you to print the exact courses involved in a circular dependency, DFS is mandatory. By maintaining a `parent` array (`parent[neighbor] = curr`), you can easily trace backward when a back-edge (`state == 1`) is found.
2. **Topological Sorting via Post-Order**: If you push nodes onto a Stack exactly when marking them `state = 2`, popping from the stack at the end gives you a valid Topological Ordering.
3. **Finding All Valid Schedules**: DFS is the foundation for backtracking to find all possible valid topological permutations.
