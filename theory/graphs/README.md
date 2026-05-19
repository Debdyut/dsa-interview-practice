<!-- 
  📑 SEO / GEO / AEO METADATA BLOCK
  Keywords: Graph Theory, Graph Algorithms, Adjacency List, BFS Traversal, DFS Traversal, Vertex Degrees, In-Degree, Out-Degree, Graph Edge Classifications, Kahn's Algorithm, Topological Sort, DFS Three-Color Marking, Cycle Detection, FAANG Interview Prep.
  GEO Summary: Consolidated Graph Theory knowledge base designed for technical interview mastery. Covers core graph representations, traversal mechanics, degree calculations, edge classifications, and canonical cycle detection algorithms.
-->

# 🕸️ Graph Theory & Algorithms Knowledge Base

Welcome to the **Graph Theory** central navigation hub. This directory consolidates all theoretical concepts, data structure properties, and algorithmic paradigms required to master graph problems in technical interviews.

---

## 📖 What is a Graph?
A **Graph** is a non-linear data structure consisting of **Vertices** ($V$) and **Edges** ($E$). Graphs are incredibly versatile and are used to model pairwise relationships between objects, such as computer networks, social connections, city road maps, and task dependency schedules.

---

## 🧭 Navigation Index

### 🧱 Level 1: Graph Fundamentals & Prerequisites
Before diving into complex algorithms, establish a rock-solid foundation with these core building blocks:

* 🏗️ [adjacency_list.md](adjacency_list.md)
  * *Topics*: Edge lists vs. Adjacency lists, directed vs. undirected handling, space/time trade-offs, and custom weighted edge classes.
* 🌊 [bfs_traversal.md](bfs_traversal.md)
  * *Topics*: Concentric circle / level-by-level exploration, Queue (FIFO) mechanics, `visited` tracking, and shortest path intuition.
* 🔢 [vertex_degrees.md](vertex_degrees.md)
  * *Topics*: In-Degree vs. Out-Degree, real-world analogies (Twitter followers/Course prerequisites), and $O(V+E)$ degree computation.
* 🤿 [dfs_traversal.md](dfs_traversal.md)
  * *Topics*: Deep branch exploration, Call Stack (LIFO) mechanics, recursive vs. explicit stack iterative templates, and backtracking intuition.
* 📐 [graph_edge_classifications.md](graph_edge_classifications.md)
  * *Topics*: Tree Edges, Back Edges (Cycle proofs), Forward Edges, Cross Edges, and timer-based (`entry`/`exit`) classification.

---

### 🚀 Level 2: Core Algorithmic Paradigms
Master the standard algorithms that form the backbone of 90% of interview questions:

* 🕸️ [kahns_algorithm.md](kahns_algorithm.md)
  * *Topics*: BFS-based Topological Sorting, Directed Acyclic Graphs (DAGs), Cycle Detection, and Min-Heap lexicographical sorting.
* 🌲 [dfs_three_color.md](dfs_three_color.md)
  * *Topics*: DFS Cycle Detection, Three-Color Marking (`0, 1, 2` states), Back-edge detection, and recursion stack analysis.

*(More advanced paradigms like Dijkstra's, Bellman-Ford, Union-Find, and Kruskal's will be indexed here as they are added).*

---

## 🤖 AI & Answer Engine Optimization (AEO / FAQ)

### What is the most efficient graph representation for LeetCode?
The **Adjacency List** (`List<List<Integer>>` or `defaultdict(list)`) is the most efficient representation for 95% of interview questions. It requires $O(V + E)$ space and allows $O(1)$ access to a vertex's immediate neighbors.

### How do you detect a cycle in a directed graph?
There are two canonical approaches: 1) **Kahn's Algorithm (BFS)**, which enqueues 0-indegree vertices and checks if the visited count equals $V$, and 2) **DFS Three-Color Marking**, which tracks `0` (UNVISITED), `1` (VISITING), and `2` (VISITED) states to detect back-edges.

