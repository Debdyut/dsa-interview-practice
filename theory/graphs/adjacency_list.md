# 🏗️ Graph Representation: Adjacency Lists

## 📖 1. Overview: Why Adjacency Lists?

When solving graph problems in technical interviews (like LeetCode), the input is almost always given as an **Edge List**—an array of pairs such as `edges = [[0, 1], [1, 2], [2, 0]]`.

While an edge list is simple, it is **highly inefficient** for graph traversal. If you want to find all neighbors of vertex `0`, you have to scan the entire edge list ($O(E)$ time). 

To achieve optimal $O(1)$ neighbor lookups, we convert the edge list into an **Adjacency List**.

---

## ⚖️ 2. Comparison of Graph Representations

| Representation | Space Complexity | Neighbor Lookup | Edge Query `(u, v)` | Best Used For |
| :--- | :--- | :--- | :--- | :--- |
| **Edge List** `[[u,v]]` | $O(E)$ | $O(E)$ slow | $O(E)$ slow | Initial problem input |
| **Adjacency Matrix** `grid[u][v]` | $O(V^2)$ | $O(V)$ slow | $O(1)$ fast | Dense graphs ($E \approx V^2$) |
| **Adjacency List** `adj[u]` ⭐ | $O(V + E)$ | $O(1)$ fast | $O(\text{degree}(u))$ | **95% of interview problems** (Sparse graphs) |

---

## 🔄 3. Directed vs. Undirected Graphs

The way you populate an adjacency list depends entirely on whether the graph is directed or undirected.

```
Edge: [u, v]

Directed Graph (u -> v):             Undirected Graph (u <-> v):
adj.get(u).add(v);                   adj.get(u).add(v);
                                     adj.get(v).add(u);
```

> [!WARNING]
> **The LeetCode Trap**: Always read the problem description carefully. For example, in *Course Schedule (LeetCode 207)*, the input `[1, 0]` means Course `0` is a prerequisite for Course `1` ($0 \rightarrow 1$). Therefore, you must add `adj.get(0).add(1)`. Reversing the direction can lead to incorrect results in topological sorting.

---

## 💻 4. Master Code Templates

### Java Implementation
```java
import java.util.*;

public class AdjacencyListBuilder {

    /**
     * Builds an adjacency list for a directed graph.
     * @param V Number of vertices (0 to V-1)
     * @param edges 2D array of directed edges [u, v] representing u -> v
     * @return Adjacency list represented as List of Lists
     */
    public List<List<Integer>> buildDirectedGraph(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        
        // 1. Initialize empty lists for all V vertices
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // 2. Populate the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v); // Directed edge u -> v
        }
        
        return adj;
    }

    /**
     * Builds an adjacency list for an undirected graph.
     */
    public List<List<Integer>> buildUndirectedGraph(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v); // Edge u -> v
            adj.get(v).add(u); // Edge v -> u (Undirected)
        }
        
        return adj;
    }
}
```

### Python Implementation
```python
from collections import defaultdict
from typing import List, Dict

class AdjacencyListBuilder:
    def build_directed_graph(self, V: int, edges: List[List[int]]) -> List[List[int]]:
        # Using a list of lists when vertices are 0-indexed from 0 to V-1
        adj = [[] for _ in range(V)]
        
        for u, v in edges:
            adj[u].append(v) # Directed edge u -> v
            
        return adj

    def build_graph_with_dict(self, edges: List[List[int]]) -> Dict[int, List[int]]:
        # Using defaultdict when vertex labels are non-sequential or strings (e.g. Alien Dictionary)
        adj = defaultdict(list)
        
        for u, v in edges:
            adj[u].append(v)
            
        return adj
```

---

## 🏋️ 5. Handling Weighted Edges

If edges have weights (e.g., Dijkstra's Algorithm or Minimum Spanning Trees), store a custom `Pair` or `Edge` object instead of just an integer.

```java
// Java Custom Edge Class
class Edge {
    int to;
    int weight;
    public Edge(int to, int weight) { this.to = to; this.weight = weight; }
}

// Adjacency List Initialization
List<List<Edge>> weightedAdj = new ArrayList<>();
```
```python
# Python Weighted Adjacency List (storing tuples (neighbor, weight))
weighted_adj = defaultdict(list)
weighted_adj[u].append((v, weight))
```
