public class mainTester {
	public static void main(String[] args) throws ConnSixException {
		//board board = new board();
		//board.runGame();
		int[][] board = new int[19][19];
		board[1][4] = 3;
		board[10][2] = 3;
		board[10][14] = 3;
		board[6][10] = 1;
		board[7][7] = 2;
		board[7][8] = 1;
		board[7][9] = 2;
		board[7][10] = 2;
		board[8][6] = 1;
		board[8][8] = 2;
		board[8][9] = 1;
		board[8][10] = 1;
		board[8][11] = 1;
		board[9][5] = 2;
		board[9][6] = 1;
		board[9][7] = 1;
		board[9][8] = 1;
		board[9][9] = 1;
		board[9][10] = 2;
		board[9][11] = 2;
		board[10][5] = 2;
		board[10][6] = 2;
		board[10][7] = 2;
		board[10][8] = 2;
		board[10][9] = 2;
		board[10][10] = 1;
		printBoard(board);

		cor[] evaluateResult = new cor[2];
		evaluateResult[0] = new cor();
		evaluateResult[1] = new cor();

		evaluate.runMinimax(board, 1, 8, evaluateResult);

		System.out.println(evaluateResult[0] + " " +  evaluateResult[1]);
	}
	public  static void printBoard(int[][] board) {
		System.out.println("x\\y\t0 1 2 3 4 5 6 7 8 9 a 1 2 3 4 5 6 7 8");
		for(int i =0; i< 19; i++) {
			System.out.printf("%2d\t", i);
			for(int j=0; j<19; j++) {
				if(board[i][j] == 0 )
					System.out.printf(" |");
				if(board[i][j] == 1)
					System.out.print("O|");
				if(board[i][j] == 2)
					System.out.print("X|");
				if(board[i][j] == 3)
					System.out.print("N|");
			}
			System.out.println();
		}
		System.out.println();
	}
}