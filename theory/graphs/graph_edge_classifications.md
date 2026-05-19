# 📐 Graph Edge Classifications in DFS

## 📖 1. Overview: The Four Edge Types

When you perform a Depth-First Search (DFS) on a **Directed Graph**, the traversal produces a **DFS Spanning Tree** (or forest). 

Every directed edge $(u, v)$ in the original graph can be mathematically categorized into exactly one of **four distinct classifications** based on its relationship to this DFS tree.

Understanding these four edge types is the absolute key to mastering advanced graph algorithms, particularly **Cycle Detection** and **Strongly Connected Components (Tarjan's Algorithm)**.

---

## 📊 2. Visualizing the Four Edges

```
               [ A ] (Start DFS)
              /  │  \
  (Tree Edge)/   │   \(Forward Edge)
            ▼    │    ▼
         [ B ]   │   [ D ]
        /   ▲    │     ▲
       ▼    │    │     │
    [ C ] ──┘    │     │(Cross Edge)
 (Back Edge)     │     │
                 ▼     │
               [ E ] ──┘
```

| Edge Type | Definition | State in 3-Color DFS | Implication / Role |
| :--- | :--- | :--- | :--- |
| **1. Tree Edge** | $(u, v)$ where $v$ is visited for the first time during DFS. $v$ becomes a direct child of $u$ in the DFS tree. | `state[v] == 0` (UNVISITED) | Forms the primary backbone of the DFS spanning tree. |
| **2. Back Edge** | $(u, v)$ where $v$ is an ancestor of $u$ in the current active DFS tree. | `state[v] == 1` (VISITING) | **🚨 Proves a Cycle!** A directed graph has a cycle if and only if its DFS tree has a Back-Edge. |
| **3. Forward Edge**| $(u, v)$ where $v$ is a descendant of $u$ in the DFS tree, but not a direct child (a shortcut edge). | `state[v] == 2` (VISITED)<br>*(and `entry[u] < entry[v]`)* | Harmless shortcut. Ignored in cycle detection. |
| **4. Cross Edge** | $(u, v)$ where $v$ is neither an ancestor nor a descendant of $u$. It connects two completely separate, completed branches. | `state[v] == 2` (VISITED)<br>*(and `entry[u] > entry[v]`)* | Harmless lateral link. Ignored in cycle detection. |

---

## 🔍 3. Deep Dive: Why Checking `state == 1` Works

In the **DFS Three-Color Marking** algorithm, we check if `state[neighbor] == 1`. 

Let's look at why this check perfectly isolates **Back-Edges** while safely ignoring Forward and Cross edges:

1. When `state[v] == 1` (VISITING), node $v$ is currently sitting in the active recursion call stack. This guarantees that $v$ is an ancestor of the current node $u$. Therefore, $(u, v)$ is a **Back-Edge** $\rightarrow$ Cycle detected!
2. When `state[v] == 2` (VISITED), node $v$ is fully explored. It could be a **Forward Edge** (shortcut to a completed descendant) or a **Cross Edge** (link to a completed sibling branch). In both cases, no cycle is formed because $v$ has already been verified to have no paths leading back to $u$.

---

## 💻 4. Code Demonstration: Classifying Edges

By maintaining `entry` and `exit` timer arrays alongside `state`, you can classify every edge in code during an interview.

```java
import java.util.*;

public class EdgeClassifier {
    private int timer = 0;

    public void classifyEdges(int V, List<List<Integer>> adj) {
        int[] state = new int[V]; // 0=UNVISITED, 1=VISITING, 2=VISITED
        int[] entry = new int[V];
        int[] exit = new int[V];

        for (int i = 0; i < V; i++) {
            if (state[i] == 0) {
                dfsClassify(i, adj, state, entry, exit);
            }
        }
    }

    private void dfsClassify(int u, List<List<Integer>> adj, int[] state, int[] entry, int[] exit) {
        state[u] = 1; // VISITING
        entry[u] = ++timer;

        for (int v : adj.get(u)) {
            if (state[v] == 0) {
                System.out.printf("Edge (%d -> %d) is a TREE Edge%n", u, v);
                dfsClassify(v, adj, state, entry, exit);
            } else if (state[v] == 1) {
                System.out.printf("Edge (%d -> %d) is a BACK Edge (CYCLE!)%n", u, v);
            } else if (state[v] == 2) {
                if (entry[u] < entry[v]) {
                    System.out.printf("Edge (%d -> %d) is a FORWARD Edge%n", u, v);
                } else {
                    System.out.printf("Edge (%d -> %d) is a CROSS Edge%n", u, v);
                }
            }
        }

        state[u] = 2; // VISITED
        exit[u] = ++timer;
    }
}
```

---

## 🎓 5. Key Takeaways for FAANG Interviews
1. **Cycle Detection**: The entire mathematical basis for directed graph cycle detection is searching for **Back-Edges**.
2. **Undirected Graphs**: In an undirected graph, Forward and Cross edges do not exist! Every edge is either a Tree edge or a Back edge. This is why undirected cycle detection only requires checking `visited[neighbor] && neighbor != parent`.
