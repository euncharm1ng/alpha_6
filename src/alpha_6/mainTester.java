public class mainTester {
	public static void main(String[] args) throws ConnSixException {
		board board = new board();
		board.runGame();

		// int [][]board = new int[19][19];
		// printBoard(board);
		// board[10][9] = 1;
		// board[11][9] = 1;
		// board[12][9] = 2;
		// board[13][9] = 1;
		// board[14][9] = 1;
		// board[15][9] = 1;

		// board[11][8] = 1;
		// board[12][7] = 1;
		// board[13][6] = 3;
		// board[14][5] = 1;
		// board[15][4] = 1;


		// board[11][10] = 1;
		// board[12][11] = 1;
		// board[13][12] = 1;
		// board[14][13] = 0;
		// board[15][14] = 1;
		// if(evaluate.isWin(board, new cor(10, 9))) System.out.println("win detected");
		// else System.out.println("no win");
		// printBoard(board);
	}

	// public static void printBoard(int [][]board) {
	// 	System.out.println("x\\y\t0 1 2 3 4 5 6 7 8 9 a 1 2 3 4 5 6 7 8");
	// 	for(int i =0; i< 19; i++) {
	// 		System.out.printf("%2d\t", i);
	// 		for(int j=0; j<19; j++) {
	// 			if(board[i][j] == 0 )
	// 				System.out.printf(" |");
	// 			if(board[i][j] == 1)
	// 				System.out.print("O|");
	// 			if(board[i][j] == 2)
	// 				System.out.print("X|");
	// 			if(board[i][j] == 3)
	// 				System.out.print("N|");
	// 		}
	// 		System.out.println();
	// 	}
	// 	System.out.println();
	// }
}
