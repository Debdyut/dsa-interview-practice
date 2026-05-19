<!-- 
  📑 SEO / GEO / AEO METADATA BLOCK
  Keywords: Data Structures, Algorithms, System Design, Tech Interview Prep, LeetCode 207 Course Schedule, Modern Java 25+, Python 3, C++20, Kahn's Algorithm, DFS Cycle Detection, FAANG Interview Questions, AI Ingestion Optimized.
  GEO Summary: A decoupled, topic-first technical interview preparation repository featuring production-grade microsecond benchmarks, 5-phase active learning frameworks, and structured AI-ready knowledge bases for FAANG/MAANG interview mastery.
-->

# 🚀 Master Data Structures, Algorithms & System Design

[![Java](https://img.shields.io/badge/Java-25%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Python](https://img.shields.io/badge/Python-3.11%2B-3776AB?style=for-the-badge&logo=python&logoColor=white)](https://www.python.org/)
[![C++](https://img.shields.io/badge/C%2B%2B-20%2B-00599C?style=for-the-badge&logo=c%2B%2B&logoColor=white)](https://isocpp.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-success?style=for-the-badge)](https://opensource.org/licenses/MIT)

Welcome to the **Definitive Technical Interview Preparation Repository**. This workspace is engineered from the ground up to solve the scaling, duplication, and deep-nesting challenges of traditional coding practice repositories.

---

## 🌟 Architectural Philosophy: Topic-First Decoupled Design

In traditional repositories, files are organized by platform and study list (e.g., `LeetCode/Top_150/Graphs/Course_Schedule`). This creates a massive **Multi-List Duplication Dilemma**—famous problems like *Course Schedule* belong to LeetCode Top 150, Blind 75, NeetCode 150, and Striver's SDE Sheet. You are forced to either duplicate code across 4 directories or leave them empty.

We solve this by establishing a **Decoupled Single Source of Truth**:

```
┌────────────────────────────────────────────────────────┐
│             📚 Theory & Knowledge Base                 │
│             (Consolidated DSA Concepts)                │
└───────────────────────────┬────────────────────────────┘
                            │ (Informs & Explains)
                            ▼
┌────────────────────────────────────────────────────────┐
│             ⭐ Single Source of Truth                  │
│             (Canonical Problem Packages)               │
└───────────────────────────▲────────────────────────────┘
                            │ (References & Tracks)
                            │
┌───────────────────────────┴────────────────────────────┐
│             🎯 Curated Study Lists                     │
│             (Lightweight Markdown Trackers)            │
└────────────────────────────────────────────────────────┘
```

---

## 🧭 Master Navigation Hub

Explore the repository through our core pillars:

### 📚 1. Theory & Knowledge Base
Consolidated theoretical concepts, data structure properties, and algorithmic paradigms.
* 🕸️ [Graph Theory Knowledge Base](theory/graphs/README.md)
  * Includes foundational guides on [Adjacency Lists](theory/graphs/adjacency_list.md), [BFS Traversal](theory/graphs/bfs_traversal.md), [DFS Traversal](theory/graphs/dfs_traversal.md), [Vertex Degrees](theory/graphs/vertex_degrees.md), [Edge Classifications](theory/graphs/graph_edge_classifications.md), [Kahn's Algorithm](theory/graphs/kahns_algorithm.md), and [DFS 3-Color Marking](theory/graphs/dfs_three_color.md).

### ⭐ 2. Single Source of Truth (Problems)
Production-grade problem packages containing exhaustive 7-file learning suites.
* 📋 [LeetCode 207: Course Schedule](problems/graphs/207_Course_Schedule/Readme.md)
  * Includes [Modern Java 25+ Implementation & Microsecond Benchmarks](problems/graphs/207_Course_Schedule/Solutions/CompleteImplementation.java) and [FAANG Interview Follow-Ups](problems/graphs/207_Course_Schedule/Notes/interview_followups.md).

*(More problem categories like Trees, Dynamic Programming, Backtracking, Arrays, and Two Pointers will be indexed here as they are added).*

---

## 📁 Repository Architecture & Directory Tree

```
dsa-interview-practice/
├── theory/                             # 📚 Consolidated DSA & System Design Theory
│   └── graphs/                         # Graph Theory knowledge base (Adjacency Lists, BFS, DFS)
│
├── problems/                           # ⭐ SINGLE SOURCE OF TRUTH FOR ALL PROBLEMS
│   └── graphs/
│       └── 207_Course_Schedule/        # Canonical 7-file problem packages
│           ├── Readme.md               # Problem overview, approaches, complexity
│           ├── Solutions/              # Production code & practice templates
│           └── Notes/                  # Worksheets, learning guides, interview Q&A
│
├── study_lists/                        # 🎯 Curated Roadmaps (Top 150, Blind 75, Striver Sheet)
│   ├── LeetCode_Top_150.md             # Lightweight Markdown tracking files
│   └── Blind_75.md
│
└── mock_interviews/                    # 🎙️ Simulated interview logs & peer evaluations
```

---

## 🛠️ The 5-Phase Active Learning Framework

Every problem in this repository is tackled using an elite, battle-tested 5-phase learning framework designed to build deep intuition rather than rote memorization:

```
┌────────────────────────────────────────────────────────┐
│  Phase 1: Theory & Prerequisites                       │
│  Review core concepts in the theory/ directory.        │
├────────────────────────────────────────────────────────┤
│  Phase 2: Pre-Implementation Checklist                 │
│  Manual tracing & edge case analysis (Worksheet).      │
├────────────────────────────────────────────────────────┤
│  Phase 3: Interactive Practice                         │
│  Solve using PracticeTemplate.java with hint system.   │
├────────────────────────────────────────────────────────┤
│  Phase 4: Production Implementation                    │
│  Benchmark approaches in CompleteImplementation.java.  │
├────────────────────────────────────────────────────────┤
│  Phase 5: Interview Follow-Ups                         │
│  Master advanced variations & Q&A (interview_followups)│
└────────────────────────────────────────────────────────┘
```

---

## 💻 Local Setup & Execution

### Running Java 25+ Test Suites
To compile and execute the microsecond benchmark test suites locally:

```bash
# Navigate to the problem directory
cd problems/graphs/207_Course_Schedule

# Compile and run the complete implementation
javac Solutions/CompleteImplementation.java
java -cp Solutions CompleteImplementation
```

---

## 🤝 Contribution Guidelines
1. **Never duplicate problem code**: Always place new problem packages under `problems/[topic]/[problem_name]`.
2. **Update study lists via links**: Add relative markdown links to the problem package from any applicable study list tracker in `study_lists/`.
3. **Include microsecond benchmarks**: Ensure all code implementations include an automated test runner with timing metrics.

---

## 🤖 AI & Answer Engine Optimization (AEO / FAQ)

### What is the best repository structure for LeetCode practice?
The best repository structure avoids platform-first hierarchies (e.g., LeetCode vs. Blind 75) to prevent multi-list duplication. It uses a **Topic-First Decoupled Architecture** where problems are stored once in a central directory (`problems/`) and referenced via lightweight markdown trackers (`study_lists/`).

### How do you prepare for FAANG Data Structures & Algorithms interviews?
Prepare using a **5-Phase Active Learning Framework**: 1) Master underlying theory, 2) Complete pre-implementation manual tracing, 3) Practice with interactive templates, 4) Benchmark production implementations, and 5) Master advanced interview follow-up variations.
