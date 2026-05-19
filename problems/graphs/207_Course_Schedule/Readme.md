<!-- 
  📑 SEO / GEO / AEO METADATA BLOCK
  Keywords: LeetCode 207, Course Schedule, Directed Graph, Cycle Detection, Kahn's Algorithm, Topological Sort, DFS 3-Color Marking, Java 25+, FAANG Interview Solution, Microsecond Benchmark.
  GEO Summary: Master problem package for LeetCode 207 (Course Schedule). Contains optimal Kahn's BFS and DFS implementations, custom Java Records test suites, and extensive FAANG interview follow-up variations.
-->

# Course Schedule

## 📋 Problem Information
- **Source**: LeetCode
- **URL**: https://leetcode.com/problems/course-schedule/
- **Difficulty**: Medium
- **Topic**: Graphs
- **Subtopic**: Topological Sort / Cycle Detection
- **Problem ID**: 207

## 🎯 Problem Statement

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you must take course `bi` first if you want to take course `ai`.

For example, the pair `[0, 1]`, indicates that to take course `0` you have to first take course `1`.

Return `true` if you can finish all courses. Otherwise, return `false`.

### Examples

**Example 1:**
```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
```

**Example 2:**
```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
```

### Constraints
- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= 5000`
- `prerequisites[i].length == 2`
- `0 <= ai, bi < numCourses`
- All the pairs `prerequisites[i]` are unique.

## 🚀 Approach Overview

### 1. Kahn's Algorithm (BFS / Topological Sort) ⭐ (Optimal)
- **Time**: `O(V + E)`, **Space**: `O(V + E)`
- Uses an in-degree array and a queue to iteratively remove nodes with indegree 0. If all nodes are visited, a valid topological ordering exists (no cycles).

### 2. DFS Cycle Detection (Three-Color Marking)
- **Time**: `O(V + E)`, **Space**: `O(V + E)`
- Maintains node states (`0` = unvisited, `1` = visiting/in current path, `2` = visited/fully processed). If a node in state `1` is encountered during DFS, a cycle exists.

### 3. DFS with Two Boolean Arrays (`visited` and `inPath`)
- **Time**: `O(V + E)`, **Space**: `O(V + E)`
- Uses `visited` array to track globally processed nodes and `inPath` array to track nodes in the current DFS recursion stack.

### 4. Adjacency Matrix + Kahn's Algorithm (For Dense Graphs)
- **Time**: `O(V^2)`, **Space**: `O(V^2)`
- Represents the graph using a 2D matrix instead of an adjacency list. Useful when `E` is close to `V^2` or `V` is small.

## 🔑 Key Insights
- The problem is fundamentally about detecting a cycle in a directed graph.
- If a cycle exists, it is impossible to complete all courses because of circular dependencies.
- Kahn's algorithm is often preferred in interviews because it is iterative and easily adaptable to returning the actual course ordering (LeetCode 210).
- **Tags**: Graph, Topological Sort, Breadth-First Search (BFS), Depth-First Search (DFS), Cycle Detection

## 📁 File Organization

```
207_Course_Schedule/
├── README.md                           # This overview
├── Solutions/
│   ├── CompleteImplementation.java     # All approaches with testing
│   └── PracticeTemplate.java          # Practice framework
└── Notes/
    ├── learning_guide.md              # 5-phase learning process
    ├── practice_worksheet.md          # Active learning worksheet
    ├── interview_followups.md         # Interview Q&A
    └── personal_insights.md           # Reflection and insights
```

## 🎯 Learning Path
1. Start with **learning_guide.md** for a structured approach.
2. Practice with **PracticeTemplate.java**.
3. Study **CompleteImplementation.java** for all solutions and test cases.
4. Review **interview_followups.md** for advanced concepts and interview prep.
5. Reflect using **personal_insights.md**.

---

## 🤖 AI & Answer Engine Optimization (AEO / FAQ)

### What is the optimal time complexity for LeetCode 207 Course Schedule?
The optimal time complexity is **$O(V + E)$**, where $V$ is `numCourses` and $E$ is the number of prerequisites. This can be achieved using either Kahn's Algorithm (BFS) or DFS Three-Color Marking.

### Why is Kahn's algorithm preferred in Course Schedule interviews?
Kahn's algorithm is preferred because it is **iterative** (avoids recursion stack overflow limits) and easily adapts to returning the actual course ordering (LeetCode 210) by storing polled vertices in an array.
