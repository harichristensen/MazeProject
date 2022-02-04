Purpose:
I created this code to create a maze, display the maze, solve the maze, and then display the solved maze. While it is
not completely finished, this project highlights my ability to use the model view control format and solve a problem
using recursion.

SS of what the gui looks like when code is run, or when "New Maze" is clicked
!(https://raw.githubusercontent.com/harichristensen/MazeProject/master/images/newmaze.PNG)

SS of when "Solve Maze" is clicked
!(https://raw.githubusercontent.com/harichristensen/MazeProject/master/images/solvedmaze.PNG)

My thoughts while making this project:
I could not figure out why my maze solver was searching every cell in the maze until it reached the end of the maze.
Originally, I thought that I had to get the neighbour at index 0, so I would never go out of bounds:
Line 65 was originally: Cell added = cells.push(cell.getNeighbours().get(0));
I thought to myself if getting the first index searches all the cells then the opposite of the first index (last index)
could give the opposite result. So I changed line 65 to:
Line 65 after: Cell added = cells.push(cell.getNeighbours().get(cell.getNeighbours().size()-1));
Suddenly my best path algorithm worked, and it searched the least amount of cells before reaching the end of the maze.

At the beginning of creating this project I had an option to choose the size of the maze. However, my maze solving
algorithm could not handle larger mazes, so I had to remove the larger options until I have time to multithread the
algorithm.

Main Modules:
control: holds the controller class, controls and connects view and util modules
jar-file: hold the executable jar file for the project
MazeAlgorithm: the main module, starts the controller
model: holds the maze and cell classes
util: holds the maze solver class
view: holds the maze view class



TO DO
To make the algorithm find the best path I will change the solver to choose the neighbour that
is closer to the end cell.

Increase size of maze in maze solver. Right now StackOverflowException occurs on bigger mazes, so I would have to handle
that by using multithreading

Handle exceptions more

Make the xml diagram for the project


