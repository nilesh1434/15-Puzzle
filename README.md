# 15 Puzzle Game

This project is a Java-based implementation of the classic 15 Puzzle game. It integrates Artificial Intelligence (AI) heuristics to solve the puzzle efficiently. The game challenges players to arrange tiles in sequential order, and the AI provides optimal solutions when needed.  

---

## 🎮 About the Game  
The 15 Puzzle is a sliding puzzle consisting of a 4x4 grid with 15 numbered tiles and one empty space. The objective is to move the tiles into numerical order, using the empty space strategically.  

This version introduces AI heuristics to analyze the board state and find optimal moves, making it a valuable tool for learning AI and puzzle-solving strategies.  

---

## ✨ Features  
- **Interactive Gameplay**: Users can manually play the puzzle.  
- **AI Solver**: Implements AI heuristics to find the shortest solution.  
- **Heuristics Used**:  
  - **Manhattan Distance**: Calculates the total distance of tiles from their target positions.  
  - **Misplaced Tiles**: Counts the number of tiles not in their correct positions.  
- **Visualization**: Displays the AI solving steps (if applicable).  
- **Customizable Start State**: Players can choose a starting arrangement or randomize it.  

---

## 🛠️ Tech Stack  
- **Language**: Java  
- **Algorithms**:  
  - Breadth-First Search (BFS)  
  - A* Search Algorithm (with heuristics)  
- **Libraries**: Standard Java libraries for UI

---

## 🚀 Getting Started  

### 1️⃣ Prerequisites  
- Install [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) version 8 or later.  

### 2️⃣ Clone the Repository  
```bash
git clone https://github.com/yourgithubusername/15-puzzle-game.git
cd 15-puzzle-game
```

### 3️⃣ Compile and Run the Program
- To compile the code:
```bash
javac Main.java
```

- To run the program:
```bash
java Main
```

### 📖 How It Works
Gameplay:

The player can interact with the puzzle, sliding tiles to solve it manually.
A "Solve" button activates the AI, which calculates the optimal solution using selected heuristics.
AI Heuristics:

Manhattan Distance: Prioritizes moves that minimize the total distance of tiles from their correct positions.
Misplaced Tiles: Focuses on reducing the number of incorrectly placed tiles.
Algorithm:

The A* search algorithm ensures the AI finds the most efficient solution.
The heuristics guide the search process to reduce unnecessary computations.

Thank you for exploring the 15 Puzzle Game project! 🎉

Happy Coding!!!
