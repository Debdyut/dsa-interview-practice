# 🎙️ Interview Follow-Ups: Course Schedule

This document explores the most common follow-up questions asked by FAANG interviewers after you successfully solve the base Course Schedule problem.

---

## 🎯 Q1: Return the Actual Course Schedule Order (LeetCode 210)

**Interviewer Question:** *"Instead of just returning `true` or `false`, modify your solution to return the exact order in which courses should be taken. If no valid schedule exists, return an empty array."*

### 💡 The Solution
This is a direct adaptation of **Kahn's Algorithm**. Instead of maintaining a `completedCourses` counter, maintain an `int[] topoOrder` array and an `index` pointer. Every time you poll a node from the queue, store it in `topoOrder[index++]`.

### 💻 Code Modification (Java)
```java
public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
    int[] indegree = new int[numCourses];
    
    for (int[] prereq : prerequisites) {
        adj.get(prereq[1]).add(prereq[0]);
        indegree[prereq[0]]++;
    }
    
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) queue.offer(i);
    }
    
    int[] topoOrder = new int[numCourses];
    int index = 0;
    
    while (!queue.isEmpty()) {
        int curr = queue.poll();
        topoOrder[index++] = curr; // Store the exact ordering
        
        for (int neighbor : adj.get(curr)) {
            indegree[neighbor]--;
            if (indegree[neighbor] == 0) queue.offer(neighbor);
        }
    }
    
    return (index == numCourses) ? topoOrder : new int[0];
}
```

---

## 🔢 Q2: Lexicographically Smallest / Alphabetical Schedule

**Interviewer Question:** *"If multiple courses are available to take at the same time, you must prioritize the course with the smallest ID (or alphabetical title). How would you achieve this?"*

### 💡 The Solution
Replace the standard `Queue` (FIFO) in Kahn's Algorithm with a **`PriorityQueue` (Min-Heap)**. 

When multiple courses have `indegree == 0`, the Min-Heap automatically pulls the course with the smallest ID first.

### ⏱️ Complexity Impact
* **Time Complexity**: Increases from $O(V + E)$ to $O(V \log V + E)$ because inserting and polling from a PriorityQueue takes $O(\log V)$ time per vertex.
* **Space Complexity**: Remains $O(V + E)$.

---

## 🔄 Q3: Return the Exact Circular Dependency (Cycle Path)

**Interviewer Question:** *"If the student cannot finish the courses due to a cycle, print the exact circular dependency (e.g., `Course 1 -> Course 2 -> Course 1`)."*

### 💡 The Solution
Kahn's Algorithm cannot easily reconstruct the cycle path because it simply discards nodes with `indegree > 0`. You must use **DFS 3-Color Marking** combined with a `parent` map/array.

1. Maintain `int[] parent` where `parent[v] = u` when exploring edge $u \rightarrow v$.
2. When DFS encounters a neighbor where `state[neighbor] == 1` (VISITING), a cycle is detected!
3. To reconstruct the cycle, backtrack using the `parent` array from `curr` back to `neighbor`, collecting the nodes along the way.

---

## 🔀 Q4: Find ALL Possible Valid Schedules

**Interviewer Question:** *"Kahn's algorithm only gives us one valid schedule. What if we want to generate EVERY possible valid order in which the student could complete their degree?"*

### 💡 The Solution
To generate all valid permutations, you must use **DFS + Backtracking**.

1. Maintain the `indegree` array and a `boolean[] visited` array.
2. In your recursive `backtrack(path)` function, loop through all vertices $0$ to $V-1$.
3. If a vertex `i` has `indegree[i] == 0` and `!visited[i]`:
   * Mark `visited[i] = true`, add `i` to `path`.
   * Decrement `indegree` for all neighbors of `i`.
   * Recurse: `backtrack(path)`.
   * **Backtrack**: Increment `indegree` for neighbors, remove `i` from `path`, mark `visited[i] = false`.
4. Base case: When `path.size() == numCourses`, add `path` to the global results list.

---

## 👯 Q5: Handle Duplicate Prerequisite Pairs

**Interviewer Question:** *"What if the input `prerequisites` array contains duplicate pairs like `[[1, 0], [1, 0]]`? How does your code behave, and how would you fix it?"*

### 💡 The Solution
* **The Bug**: In standard Kahn's Algorithm, iterating over `[[1, 0], [1, 0]]` would cause `indegree[1]` to be incremented twice (`indegree[1] = 2`). However, Course 0 only appears once in the adjacency list (or twice if not deduplicated). This can cause `indegree[1]` to never reach 0, falsely claiming a cycle exists.
* **The Fix**: Deduplicate the edges before building the graph. Use a `Set<String>` (e.g., storing `"1-0"`) or a `boolean[][]` adjacency matrix (`adjMatrix[u][v] = true`) to ensure each directed edge is processed exactly once.

---

## 🌐 Q6: Distributed / Massive Dependency Graphs (System Design)

**Interviewer Question:** *"Imagine we are building a massive workflow orchestration engine (like Apache Airflow or Uber Cadence). There are 10 million micro-tasks with complex dependencies stored across multiple database shards. How do you detect cycles at scale?"*

### 💡 The Solution
Single-machine BFS/DFS will run out of memory. You must discuss distributed graph processing paradigms:
1. **Distributed Graph Frameworks**: Mention **Pregel** (Google's vertex-centric message passing model) or **Apache Spark GraphX**.
2. **Message Passing Cycle Detection**: Each vertex sends its own ID to its neighbors. In subsequent super-steps, vertices forward the received IDs along outgoing edges. If a vertex ever receives its own ID back, a cycle exists.
3. **Graph Partitioning (Sharding)**: Use algorithms like **Tarjan's Strongly Connected Components (SCC)** to break the massive graph into smaller, independent subgraphs. Subgraphs that do not span across shards can be verified locally on individual worker nodes.

---

## 🔗 Associated Resources
* 💻 [CompleteImplementation.java](../Solutions/CompleteImplementation.java): View the Java 25+ base implementation.
* 🕸️ [Kahn's Algorithm Theory](../../../../theory/graphs/kahns_algorithm.md): Review the underlying BFS topological sort mechanics.
