import java.util.ArrayList;
import processing.core.PImage;
import processing.core.*;

//THIS ONE HERE
public class Maze extends PApplet {
	PImage[] tiles = new PImage[14];
	PImage[] walls = new PImage[4];
	PImage[] torches = new PImage[4];
	PImage[] ceiling = new PImage[10];
	PApplet p;
	// Array where the cells are stored.
	int[][] maze;
	// Value of the cells.
	int id;
	int pxSize;
	int totalPxWidth;
	int totalPxHeight;
	int randomN;

	int navigate;
	int finalx = 500;
	int finaly = 500;
	boolean top = true;
	boolean bound = false;
	// Array list where the possible movements are stored.
	ArrayList<Integer> Cell = new ArrayList<Integer>();
	// Array list where the already visited cells are stored.
	ArrayList<Integer> Visited = new ArrayList<Integer>();
	// Array list where the cells that are unavailable are added.
	ArrayList<Integer> Walls = new ArrayList<Integer>();

	// Constructor for the maze.
	public Maze(PApplet p, int pxSize) {
		this.p = p;
		this.pxSize = pxSize;
		// Here we divide the size of the screen by the size of the pixels that
		// we want our rectangle to be.
		totalPxWidth = p.width / pxSize;
		totalPxHeight = p.height / pxSize;
		// Then we assign the value to the array list and draw it in the same
		// proportion.
		maze = new int[totalPxWidth][totalPxHeight];

	}

	// Method to draw the maze.
	public void spawn() {
		p.rectMode(PApplet.CENTER);
		// Here we iterate through all the indexed of the array.
		for (int i = 1; i < totalPxWidth-1; i++) {
			for (int j = 1; j < totalPxHeight-1; j++) {
				
				/*
				 * Here we assign a numerical value to each index of the array,
				 * we use the variable top to assure that we are not gonna
				 * assign values more than once.
				 */
				
				if (top == true) {
					maze[i][j] = id;
					id++;
				}
				// Here we draw white rectangles where the Visited cells are.
				if (Visited.contains(maze[i][j]) == true && Walls.contains(maze[i][j]) == false) {
					p.imageMode(CENTER);
					p.image(tiles[1], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4, pxSize / 2 * 1.1f,
							pxSize / 2 * 1.2f);
					p.image(tiles[4], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2 * 1.1f,
							pxSize / 2 * 1.2f);
					p.image(tiles[2], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4, pxSize / 2 * 1.1f,
							pxSize / 2 * 1.2f);
					p.image(tiles[3], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2 * 1.1f,
							pxSize / 2 * 1.2f);
					if (maze[i][j] % 9 == 0) {
						p.image(tiles[0], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2 * 1.1f,
								pxSize / 2 * 1.2f);
					}
					// Sometimes there is a conflict between cells that are
					// visited and cells that are walls, so we make thos white
					// as well.

				} else if (Walls.contains(maze[i][j]) == true && Visited.contains(maze[i][j]) == true) {
					try {
						if (Walls.contains(maze[i][j + 1]) == true) {
							p.stroke(0);
							p.fill(0, 255, 0);
							p.rect(i * pxSize + pxSize / 2, j * pxSize + pxSize / 2, pxSize, pxSize);
						} else if (Walls.contains(maze[i][j + 1]) != true) {
							p.stroke(0);
							p.fill(255, 0, 0);
							p.rect(i * pxSize + pxSize / 2, j * pxSize + pxSize / 2, pxSize, pxSize);
						}
					} catch (ArrayIndexOutOfBoundsException ex) {
					}
				} else if (Walls.contains(maze[i][j]) == true && Visited.contains(maze[i][j]) == false) {

					try {
						if (Walls.contains(maze[i][j + 1]) == true) {
							
							if (Walls.contains(maze[i + 1][j]) != true && Walls.contains(maze[i - 1][j]) != true
									&& Walls.contains(maze[i][j - 1]) != true
									&& Walls.contains(maze[i][j + 1]) == true) {
								p.image(ceiling[1], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[0], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
										pxSize / 2);
								p.image(ceiling[5], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[4], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
										pxSize / 2);
								/*
								 * Cell Cell Wall Cell Wall
								 * 
								 */
							} else if (Walls.contains(maze[i + 1][j]) != true && Walls.contains(maze[i - 1][j]) != true
									&& Walls.contains(maze[i][j + 1]) == true
									&& Walls.contains(maze[i][j - 1]) == true) {
								p.image(ceiling[5], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[4], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
										pxSize / 2);
								p.image(ceiling[5], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[4], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
										pxSize / 2);
								/*
								 * Wall Cell Wall Cell Wall
								 * 
								 */
							} else if (Walls.contains(maze[i + 1][j]) == true && Walls.contains(maze[i - 1][j]) != true
									&& Walls.contains(maze[i][j + 1]) == true
									&& Walls.contains(maze[i][j - 1]) != true) {
								p.image(ceiling[6], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[0], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
										pxSize / 2);
								p.image(ceiling[4], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
										pxSize / 2);
								/*
								 * Cell Cell Wall Wall Wall
								 * 
								 */
							}else if (Walls.contains(maze[i + 1][j]) == true && Walls.contains(maze[i - 1][j]) == true
									&& Walls.contains(maze[i][j + 1]) == true
									&& Walls.contains(maze[i][j - 1]) != true) {
								p.image(ceiling[6], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[6], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
										pxSize / 2);

								/*
								 * Cell Wall Wall Wall Wall
								 * 
								 */
							} else if (Walls.contains(maze[i + 1][j]) == true && Walls.contains(maze[i - 1][j]) != true
									&& Walls.contains(maze[i][j + 1]) == true
									&& Walls.contains(maze[i][j - 1]) == true) {

								p.image(ceiling[4], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
										pxSize / 2);
								p.image(ceiling[5], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[4], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
										pxSize / 2);
								/*
								 * Wall Cell Wall Wall Wall
								 * 
								 */
							} else if (Walls.contains(maze[i + 1][j]) != true && Walls.contains(maze[i - 1][j]) == true
									&& Walls.contains(maze[i][j + 1]) == true
									&& Walls.contains(maze[i][j - 1]) == true) {
								p.image(ceiling[5], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[4], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
										pxSize / 2);
								p.image(ceiling[5], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
										pxSize / 2, pxSize / 2);
								/*
								 * Wall Wall Wall Cell Wall
								 * 
								 */
							} else if (Walls.contains(maze[i + 1][j]) != true && Walls.contains(maze[i - 1][j]) == true
									&& Walls.contains(maze[i][j + 1]) == true
									&& Walls.contains(maze[i][j - 1]) != true) {
								p.image(ceiling[1], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2,
										pxSize / 2, pxSize / 2);
								p.image(ceiling[6], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
										pxSize / 2);
								p.image(ceiling[5], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
										pxSize / 2, pxSize / 2);
								/*
								 * Cell Wall Wall Cell Wall
								 * 
								 */
							} else {
								
							}

						} else if (Visited.contains(maze[i][j + 1]) == true&&Walls.contains(maze[i][j-1])==true) {
							p.image(walls[1], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
									pxSize * 0.50f);
							p.image(walls[3], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
									pxSize * 0.50f);
							p.image(torches[0], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
									pxSize / 2 * 1.1f, pxSize / 2 * 1.1f);
							
						} else if (Visited.contains(maze[i][j + 1]) == true&&Walls.contains(maze[i][j-1])!=true) {
							p.image(ceiling[6], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
									pxSize / 2);
							p.image(ceiling[6], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
									pxSize / 2);
							p.image(walls[1], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
									pxSize * 0.50f);
							p.image(walls[3], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
									pxSize * 0.50f);
							p.image(torches[0], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
									pxSize / 2 * 1.1f, pxSize / 2 * 1.1f);
						}
					}

					catch (ArrayIndexOutOfBoundsException ex) {
					}

				}
				// If a cell is not contained in Visited, it is therefore marked
				// as black.
				else {
					
					p.image(ceiling[1], i * pxSize + 3 * pxSize / 4, j * pxSize + pxSize / 4 + 2,
							pxSize / 2, pxSize / 2);
					p.image(ceiling[0], i * pxSize + pxSize / 4, j * pxSize + pxSize / 4 + 2, pxSize / 2,
							pxSize / 2);
					p.image(ceiling[3], i * pxSize + 3 * pxSize / 4, j * pxSize + 3 * pxSize / 4,
							pxSize / 2, pxSize / 2);
					p.image(ceiling[2], i * pxSize + pxSize / 4, j * pxSize + 3 * pxSize / 4, pxSize / 2,
							pxSize / 2);
				}
				// Here we draw the rectangles with the same proportions are the
				// ones used to make the array.

			}
		}
		// We make top false at the end so the values inside the array are not
		// changed.
		top = false;
	}

	// Here we generate the maze.
	public void generate(int x, int y) {
		// Here we make sure our maze is not gonna go out of bounds.
		if (finalx <= 1) {
			finalx = 1;
		}
		if (finaly <= 1) {
			finaly = 1;
		}
		if (finalx >= maze.length - 1) {
			finalx = maze.length / 2;
		}
		if (finaly >= maze[1].length - 1) {
			finaly = maze[1].length / 2;
		}
		// Here we add all cells around our current cells to the Cell List.
		// CHECK THIS LATER, MIGHT WANT TO CHECK IF THEY ARE CONTAINED IN
		// VISITED AS WELL.
		if (Cell.contains(maze[finalx + 1][finaly]) == false || Cell.contains(maze[finalx][finaly + 1]) == false
				|| Cell.contains(maze[finalx - 1][finaly]) == false
				|| Cell.contains(maze[finalx][finaly - 1]) == false) {
			Cell.add(maze[finalx + 1][finaly]);
			Cell.add(maze[finalx][finaly + 1]);
			Cell.add(maze[finalx - 1][finaly]);
			Cell.add(maze[finalx][finaly - 1]);
		}
		// We then add our current cell to the Visited list, so we won't visit
		// it again
		if (Visited.contains(maze[finalx][finaly]) == false && Walls.contains(maze[finalx][finaly]) == false) {
			Visited.add(maze[finalx][finaly]);
		}
		// Here we choose a random number between 0 and the size of our Cell
		// List, so we can then chose an index.
		navigate = round(random(0, Cell.size() - 1));
		// Check if the cell that we chose randomly is already contained inside
		// the Visited list of the Walls list.
		if (Visited.contains(Cell.get(navigate)) == false && Walls.contains(Cell.get(navigate)) == false) {

			// Here we check the cells our cell to make sure we add walls to the
			// places that are needed.
			if (Visited.contains(maze[finalx - 1][finaly]) == true
					&& Visited.contains(maze[finalx - 1][finaly - 1]) == false
					&& Visited.contains(maze[finalx - 1][finaly + 1]) == false
					|| Visited.contains(maze[finalx][finaly + 1]) == true
							&& Visited.contains(maze[finalx - 1][finaly - 1]) == false
							&& Visited.contains(maze[finalx - 1][finaly + 1]) == false) {
				Walls.add(maze[finalx - 1][finaly - 1]);
				Walls.add(maze[finalx - 1][finaly + 1]);
			}
			if (Visited.contains(maze[finalx + 1][finaly]) == true
					&& Visited.contains(maze[finalx + 1][finaly - 1]) == false
					&& Visited.contains(maze[finalx + 1][finaly + 1]) == false
					|| Visited.contains(maze[finalx][finaly - 1]) == true
							&& Visited.contains(maze[finalx + 1][finaly - 1]) == false
							&& Visited.contains(maze[finalx + 1][finaly + 1]) == false) {
				Walls.add(maze[finalx + 1][finaly + 1]);
				Walls.add(maze[finalx + 1][finaly - 1]);
			}
			if (Visited.contains(maze[finalx][finaly - 1]) == true
					&& Visited.contains(maze[finalx - 1][finaly - 1]) == false
					&& Visited.contains(maze[finalx + 1][finaly - 1]) == false
					|| Visited.contains(maze[finalx - 1][finaly]) == true
							&& Visited.contains(maze[finalx - 1][finaly - 1]) == false
							&& Visited.contains(maze[finalx + 1][finaly - 1]) == false) {
				Walls.add(maze[finalx - 1][finaly - 1]);
				Walls.add(maze[finalx + 1][finaly - 1]);
			}
			if (Visited.contains(maze[finalx][finaly + 1]) == true
					&& Visited.contains(maze[finalx + 1][finaly + 1]) == false
					&& Visited.contains(maze[finalx - 1][finaly - 1]) == false
					|| Visited.contains(maze[finalx + 1][finaly]) == true
							&& Visited.contains(maze[finalx + 1][finaly + 1]) == false
							&& Visited.contains(maze[finalx - 1][finaly - 1]) == false) {
				Walls.add(maze[finalx - 1][finaly + 1]);
				Walls.add(maze[finalx + 1][finaly + 1]);
			}
			// Here we go through the maze array and compare it to Cell list at
			// the index that we chose randomly.
			for (int i = 0; i < maze.length - 1; i++) {
				for (int j = 0; j < maze[i].length - 1; j++) {
					if (Cell.get(navigate) == maze[i][j]) {
						// We then make the index that was chosen randomly our
						// current index.
						finalx = i;
						finaly = j;

					}
				}
			}
			// If not contained inside either, we add it to the Visited list.
			Visited.add(Cell.get(navigate));
			// Here we go through the Cell list and remove the cells that are
			// already inside the Visited list.
			for (int i = 0; i < Cell.size(); i++) {
				if (Visited.contains(Cell.get(i)) == true) {
					Cell.remove(i);
				}
			}
			for (int i = 0; i < Walls.size(); i++) {
				if (Visited.contains(Walls.get(i)) == true) {
					Walls.remove(i);
				}
			}

			// In case Visited list already contains the randomly chosen cell,
			// or Walls already contains the randomly chosen cell.
		} else if (Visited.contains(Cell.get(navigate)) != false || Walls.contains(Cell.get(navigate)) != false) {
			// If it is not contained in the Walls list, we add it to Visited.
			if (Walls.contains(Cell.get(navigate)) == false) {
				Visited.add(Cell.get(navigate));
				Cell.remove(navigate);
				// If it is not contained in the Visited list, we add it to
				// Walls.
			} else {
				Cell.remove(navigate);
			}

		}
	}
}
