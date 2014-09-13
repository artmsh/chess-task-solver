chess-task-solver
=================

Solver for chess task

Usage
=====

```sh
javac ChessSolver.java
java ChessSolver [-n] [board-width] [board-height] [list-of-figures]
```
* **-n** output only combinations count, not combinations ifself
* **board-width**, **board-height** indicate a chess board dimensions (non-zero positive integers)
* **list-of-figures** is space-separated list of available chess figures, for example for 1 King, 1 Queen, 1 Bishop, 1 Rook, 1 Knight list would be "K1 Q1 B1 R1 N1"

Output
======
Number of existing combinations (or error message if input is not valid) at first line, next lines contain description of solutions.

Example
=======
```sh
> java ChessSolver 3 3 K2 R1
> 4
> K.K
> ...
> .R.
> K..
> ..R
> K..
> ..K
> R..
> ..K
> .R.
> ...
> K.K
```

Requirements
============
Java 8
