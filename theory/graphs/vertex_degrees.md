# 🔢 Vertex Degrees: In-Degree & Out-Degree

## 📖 1. Overview: The Concept of Degree

In graph theory, the **Degree** of a vertex is the number of edges connected to it. 

However, when working with **Directed Graphs** (where edges have distinct directions $u \rightarrow v$), the concept of degree splits into two separate and highly important metrics: **In-Degree** and **Out-Degree**.

---

## ⚖️ 2. Definitions & Real-World Analogies

```
         (Incoming Edges)                 (Outgoing Edges)
       A ──┐                           ┌──> X
           ├──> [ Target Node ] ───────┼──> Y
       B ──┘                           └──> Z

       In-Degree = 2                   Out-Degree = 3
```

| Metric | Definition | Real-World Analogy | Role in Kahn's Algorithm |
| :--- | :--- | :--- | :--- |
| **In-Degree** | Number of edges directed *into* a vertex ($u \rightarrow \text{node}$). | • Prerequisites needed for a course.<br>• Followers on Twitter / Instagram.<br>• Libraries your project imports. | Tracks uncompleted dependencies. A node is ready to process only when `indegree == 0`. |
| **Out-Degree** | Number of edges directed *out* of a vertex ($\text{node} \rightarrow v$). | • Subsequent courses unlocked.<br>• People you follow on Twitter.<br>• Projects that depend on your library. | Determines which neighbors need their `indegree` decremented when this node finishes. |

---

## 🔄 3. How to Compute Degrees from an Edge List

When an interview problem provides an edge list `[[u, v]]`, computing the degrees is incredibly straightforward.

For every directed edge `[u, v]` representing $u \rightarrow v$:
* Vertex `u` is the source $\rightarrow$ increment `outdegree[u]`.
* Vertex `v` is the destination $\rightarrow$ increment `indegree[v]`.

> [!TIP]
> In many algorithms (like Kahn's Algorithm), you only need to compute and store `indegree`. `outdegree` is implicitly handled when you iterate through `adj.get(u)`.

---

## 💻 4. Master Code Templates

### Java Implementation
```java
public class DegreeCalculator {

    /**
     * Calculates in-degrees and out-degrees for a directed graph.
     * @param V Number of vertices (0 to V-1)
     * @param edges 2D array of directed edges [u, v] representing u -> v
     */
    public void calculateDegrees(int V, int[][] edges) {
        int[] indegree = new int[V];
        int[] outdegree = new int[V];
        
        for (int[] edge : edges) {
            int u = edge[0]; // Source
            int v = edge[1]; // Destination
            
            outdegree[u]++;
            indegree[v]++;
        }
        
        // Example Output
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": In-Degree = " + indegree[i] + ", Out-Degree = " + outdegree[i]);
        }
    }
}
```

### Python Implementation
```python
from typing import List, Tuple

class DegreeCalculator:
    def calculate_degrees(self, V: int, edges: List[List[int]]) -> Tuple[List[int], List[int]]:
        indegree = [0] * V
        outdegree = [0] * V
        
        for u, v in edges:
            outdegree[u] += 1
            indegree[v] += 1
            
        return indegree, outdegree
```

---

## ⏱️ 5. Complexity Analysis

* **Time Complexity**: $O(V + E)$
  * Initializing the degree arrays takes $O(V)$ time.
  * Iterating through the edge list takes $O(E)$ time.
* **Space Complexity**: $O(V)$
  * `indegree` and `outdegree` arrays each require $O(V)$ space.
