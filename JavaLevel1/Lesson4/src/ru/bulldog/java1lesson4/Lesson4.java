package ru.bulldog.java1lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {

	private final static Scanner SCANNER = new Scanner(System.in);
	private final static Random RANDOM = new Random();

	private final static char CROSS = 'X';
	private final static char ZERO = 'O';
	private final static char EMPTY = ' ';

	private static char[][] gameMap;
	private static int winCount;
	private static int mapSize;
	private static int diagonals;

	public static void main(String[] args) {
		System.out.println("Welcome to the Tic-Tac-Toe game!");
		mapSize = 0;
		while (mapSize == 0) {
			System.out.print("Enter map size (from 3 to 5): ");
			if (SCANNER.hasNextInt()) {
				int num = SCANNER.nextInt();
				if (num >= 3 && num <= 5) {
					mapSize = num;
				}
			}
			SCANNER.nextLine();
		}
		if (mapSize == 3) {
			winCount = 3;
		} else {
			winCount = 0;
			while (winCount == 0) {
				System.out.printf("Enter line size, needed to win (from 3 to %d): ", mapSize);
				if (SCANNER.hasNextInt()) {
					int num = SCANNER.nextInt();
					if (num >= 3 && num <= mapSize) {
						winCount = num;
					}
				}
				SCANNER.nextLine();
			}
		}
		diagonals = mapSize - winCount;
		gameMap = gameInit();
		startGame();
	}

	private static void startGame() {
		printMap();
		while (true) {
			playerTurn();
			printMap();
			if (checkWin()) {
				System.out.println("Great! You are win!");
				break;
			}
			if (checkDraw()) {
				System.out.println("Sorry. It's a Draw.");
				break;
			}
			aiTurn();
			printMap();
			if (checkWin()) {
				System.out.println("Sorry. Computer is win.");
				break;
			}
			if (checkDraw()) {
				System.out.println("Sorry. It's a Draw.");
				break;
			}
		}
		System.out.println("GAME OVER");
	}

	private static char[][] gameInit() {
		char[][] map = new char[mapSize][mapSize];
		for (char[] row : map) {
			Arrays.fill(row, EMPTY);
		}
		return map;
	}

	private static void printMap() {
		for (int h = 0; h <= mapSize; h++) {
			System.out.print(h + "  ");
		}
		System.out.println();
		for (int h = 0; h < mapSize; h++) {
			System.out.print((h + 1) + " ");
			for (int v = 0; v < mapSize; v++ ) {
				System.out.print("[" + gameMap[v][h] + "]");
			}
			System.out.println();
		}
	}

	private static void playerTurn() {
		int x, y;
		while (true) {
			System.out.print("Enter your move (format: X Y): ");
			if (SCANNER.hasNextInt()) {
				x = SCANNER.nextInt() - 1;
				y = SCANNER.nextInt() - 1;
				if (isTurnValid(x, y)) {
					gameMap[x][y] = CROSS;
					break;
				}
				System.out.println("\nInvalid move, try again!");
			}
			SCANNER.nextLine();
		}
		System.out.println();
	}

	private static void aiTurn() {
		int[] move = checkMoves(CROSS);
		if (move.length == 0) {
			move = checkAiMoves();
		}
		gameMap[move[0]][move[1]] = ZERO;
		System.out.printf("Computer's move is: %d - %d%n", move[0] + 1, move[1] + 1);
		System.out.println();
	}

	private static int[] checkAiMoves() {
		int[] move = checkMoves(ZERO);
		if (move.length == 0) {
			move = new int[]{
				RANDOM.nextInt(mapSize),
				RANDOM.nextInt(mapSize)
			};
			while (!isTurnValid(move[0], move[1])) {
				move[0] = RANDOM.nextInt(mapSize);
				move[1] = RANDOM.nextInt(mapSize);
			}
		}
		return move;
	}

	private static int[] checkMoves(char target) {
		int[] move;
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				char charAt = gameMap[i][j];
				if (charAt == target) {
					move = checkLine(i, j, target);
					if (move.length > 0) {
						return move;
					}
					if (target == ZERO) {
						return getNearestValid(i, j);
					}
				}
			}
		}
		return new int[0];
	}

	private static int[] checkLine(int x, int y, char target) {
		int winLen = winCount - 1;
		int[] move = checkHorizontalAndVerticalLine(x, y, target, winLen);
		if (move.length > 0) return move;
		move = checkMainDiagonalLine(x, y, target, winLen);
		if (move.length > 0) return move;
		return checkSideDiagonalLine(x, y, target, winLen);
	}

	private static int[] checkHorizontalAndVerticalLine(int x, int y, char target, int winLen) {
		int horizontal = 0;
		int vertical = 0;
		int[] move = new int[2];
		for (int i = 0; i < mapSize; i++) {
			if (gameMap[i][y] == target) {
				horizontal++;
				if (horizontal == winLen) {
					if (isTurnValid(i + 1, y)) {
						move[0] = i + 1;
						move[1] = y;
						return move;
					} else if (isTurnValid(i - winLen, y)) {
						move[0] = i - winLen;
						move[1] = y;
						return move;
					}
				}
			} else {
				horizontal = 0;
			}
			if (gameMap[x][i] == target) {
				vertical++;
				if (vertical == winLen) {
					if (isTurnValid(x, i + 1)) {
						move[0] = x;
						move[1] = i + 1;
						return move;
					} else if (isTurnValid(x, i - winLen)) {
						move[0] = x;
						move[1] = i - winLen;
						return move;
					}
				}
			} else {
				vertical = 0;
			}
		}
		return new int[0];
	}

	private static int[] checkMainDiagonalLine(int x, int y, char target, int winLen) {
		int diagonal = 0;
		int[] start = getMainDiagonalStart(x, y);
		int startX = start[0];
		int startY = start[1];
		int[] move = new int[2];
		for (int i = 0; i < mapSize; i++) {
			int moveX = startX + i;
			int moveY = startY + i;
			if (moveX >= mapSize || moveY >= mapSize) break;
			if (gameMap[moveX][moveY] == target) {
				diagonal++;
				if (diagonal == winLen) {
					if (isTurnValid(moveX + 1, moveY + 1)) {
						move[0] = moveX + 1;
						move[1] = moveY + 1;
						return move;
					} else if (isTurnValid(moveX - winLen, moveY - winLen)) {
						move[0] = moveX - winLen;
						move[1] = moveY - winLen;
						return move;
					}
				}
			} else {
				diagonal = 0;
			}
		}
		return new int[0];
	}

	private static int[] checkSideDiagonalLine(int x, int y, char target, int winLen) {
		int diagonal = 0;
		int[] start = getSideDiagonalStart(x, y);
		int startX = start[0];
		int startY = start[1];
		int[] move = new int[2];
		for (int i = 0; i < mapSize; i++) {
			int moveX = startX - i;
			int moveY = startY + i;
			if (moveX < 0 || moveY >= mapSize) break;
			if (gameMap[moveX][moveY] == target) {
				diagonal++;
				if (diagonal == winLen) {
					if (isTurnValid(moveX - 1, moveY + 1)) {
						move[0] = moveX - 1;
						move[1] = moveY + 1;
						return move;
					} else if (isTurnValid(moveX + winLen, moveY - winLen)) {
						move[0] = moveX + winLen;
						move[1] = moveY - winLen;
						return move;
					}
				}
			} else {
				diagonal = 0;
			}
		}
		return new int[0];
	}

	private static int[] getMainDiagonalStart(int x, int y) {
		for (int i = 0; i < mapSize; i++) {
			if (x - (i + 1) < 0 || y - (i + 1) < 0) {
				return new int[] { x - i, y - i };
			}
		}
		return new int[] { x, y };
	}

	private static int[] getSideDiagonalStart(int x, int y) {
		for (int i = 0; i < mapSize; i++) {
			if (x + (i + 1) >= mapSize || y - (i + 1) < 0) {
				return new int[] { x + i, y - i };
			}
		}
		return new int[] { x, y };
	}

	private static int[] getNearestValid(int x, int y) {
		int[] move = new int[2];
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 1; j++) {
				move[0] = x + i;
				move[1] = y + j;
				if (isTurnValid(move[0], move[1])) {
					return move;
				}
				move[0] = x - i;
				move[1] = y + j;
				if (isTurnValid(move[0], move[1])) {
					return move;
				}
				move[0] = x + i;
				move[1] = y - j;
				if (isTurnValid(move[0], move[1])) {
					return move;
				}
				move[0] = x - i;
				move[1] = y - j;
				if (isTurnValid(move[0], move[1])) {
					return move;
				}
			}
		}
		return new int[0];
	}

	private static boolean isTurnValid(int x, int y) {
		if (x < 0 || y < 0 || x >= mapSize || y >= mapSize) return false;
		return gameMap[x][y] == EMPTY;
	}

	private static boolean checkWin() {
		if (checkHorizontalsAndVerticals()) {
			return true;
		}
		if (checkMainDiagonals()) {
			return true;
		}
		return checkSideDiagonals();
	}

	private static boolean checkHorizontalsAndVerticals() {
		for (int i = 0; i < mapSize; i++) {
			int horX = 0, horO = 0;
			int vertX = 0, vertO = 0;
			for (int j = 0; j < mapSize; j++) {
				if (gameMap[i][j] == CROSS) {
					vertO = 0;
					vertX++;
				} else if (gameMap[i][j] == ZERO) {
					vertX = 0;
					vertO++;
				} else {
					vertX = 0;
					vertO = 0;
				}
				if (gameMap[j][i] == CROSS) {
					horO = 0;
					horX++;
				} else if (gameMap[j][i] == ZERO) {
					horX = 0;
					horO++;
				} else {
					horX = 0;
					horO = 0;
				}
				if (horX == winCount || horO == winCount || vertX == winCount || vertO == winCount) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean checkMainDiagonals() {
		int mainX = 0, mainO = 0;
		int[] topX = new int[diagonals];
		int[] topO = new int[diagonals];
		int[] bottX = new int[diagonals];
		int[] bottO = new int[diagonals];
		for (int i = 0; i < mapSize; i++) {
			if (gameMap[i][i] == CROSS) {
				mainO = 0;
				mainX++;
			} else if (gameMap[i][i] == ZERO) {
				mainX = 0;
				mainO++;
			} else {
				mainX = 0;
				mainO = 0;
			}
			if (mainX == winCount || mainO == winCount) {
				return true;
			}
			for (int j = 0; j < diagonals; j++) {
				int idx = i + j + 1;
				if (idx >= mapSize) break;
				if (gameMap[idx][i] == CROSS) {
					topO[j] = 0;
					topX[j]++;
				} else if (gameMap[idx][i] == ZERO) {
					topX[j] = 0;
					topO[j]++;
				} else {
					topX[j] = 0;
					topO[j] = 0;
				}
				if (gameMap[i][idx] == CROSS) {
					bottO[j] = 0;
					bottX[j]++;
				} else if (gameMap[i][idx] == ZERO) {
					bottX[j] = 0;
					bottO[j]++;
				} else {
					bottX[j] = 0;
					bottO[j] = 0;
				}
				if (topX[j] == winCount || topO[j] == winCount || bottX[j] == winCount || bottO[j] == winCount) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean checkSideDiagonals() {
		int sideX = 0, sideO = 0;
		int[] topX = new int[diagonals];
		int[] topO = new int[diagonals];
		int[] bottX = new int[diagonals];
		int[] bottO = new int[diagonals];
		for (int i = 0; i < mapSize; i++) {
			int sideIdx = (mapSize - i) - 1;
			if (gameMap[sideIdx][i] == CROSS) {
				sideO = 0;
				sideX++;
			} else if (gameMap[sideIdx][i] == ZERO) {
				sideX = 0;
				sideO++;
			} else {
				sideX = 0;
				sideO = 0;
			}
			if (sideX == winCount || sideO == winCount) {
				return true;
			}
			for (int j = 0; j < diagonals; j++) {
				int idxTop = (sideIdx - j) - 1;
				if (idxTop >= 0) {
					if (gameMap[idxTop][i] == CROSS) {
						topO[j] = 0;
						topX[j]++;
					} else if (gameMap[idxTop][i] == ZERO) {
						topX[j] = 0;
						topO[j]++;
					} else {
						topX[j] = 0;
						topO[j] = 0;
					}
				}
				int idxBott = (i + j) + 1;
				if (idxBott < mapSize) {
					if (gameMap[sideIdx][idxBott] == CROSS) {
						bottO[j] = 0;
						bottX[j]++;
					} else if (gameMap[sideIdx][idxBott] == ZERO) {
						bottX[j] = 0;
						bottO[j]++;
					} else {
						bottX[j] = 0;
						bottO[j] = 0;
					}
				}
				if (topX[j] == winCount || topO[j] == winCount || bottX[j] == winCount || bottO[j] == winCount) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean checkDraw() {
		for (char[] row : gameMap) {
			for (char cell : row) {
				if (cell == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
}
