//package alpha_6;

public class evaluate {
	final static private int EMPTY = 0;
	final static private int BLACK = 1;
	final static private int WHITE = 2;
	final static private int RED = 3;
	final static private int WIDTH = 3;
	final static private int DEPTH = 3;
	static cor move = new cor();
	final static int LEFTRIGHT = 0, TOPDOWN = 1, TOPLBOTR = 2, BOTLTOPR = 3, OFFENSE = 1, DEFENSE = 2;
	static boolean DEBUG = false, PRINTINFO = false;
	final static long[][] scoresArray1 = { // turn < 6
		//{ 13, 24, 44, 80 }, { 6, 97, 176, 320 }, { 1549, 6195, 11264, 20480 }, { 1024000, 4096000, 16384000, 65536000 }, { 1024000, 4096000, 16384000, 65536000 }, 
		{ 13, 24, 44, 80 }, { 6, 97, 176, 320 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 16384000, 65536000 }, { 0, 0, 0, 65536000 }, 
		//{ 3, 6, 11, 20 }, { 97, 387, 704, 1280 }, { 24, 1549, 2816, 5120 }, { 209600, 838400, 3353600, 13414400 }, { 209600, 838400, 3353600, 13414400 }, 
		{ 3, 6, 11, 20 }, { 97, 387, 704, 1280 }, { 24, 1549, 2816, 5120 }, { 0, 0, 3353600, 13414400 }, { 0, 0, 0, 13414400 }, 
		//{ 53, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 167772160, 335544320, 671088640, 1342177000},
		{ 53, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 0, 0, 0, 1342177000},
		//{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 1310720, 2621440, 5242880, 10485760 }, { 1310720, 2621440, 5242880, 10485760 } };
		{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 5242880, 10485760 }, { 0, 0, 0, 10485760 } };
	final static long[][] scoresArray2 = {  // turn > 6
		//{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 1024000, 4096000, 16384000, 65536000 }, { 1024000, 4096000, 16384000, 65536000 }, 
		{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 16384000, 65536000 }, { 0, 0, 0, 65536000 }, //stone 1 - offense
		//{ 3, 6, 11, 20 }, { 6, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 209600, 838400, 3353600, 13414400 }, { 209600, 838400, 3353600, 13414400 }, 
		{ 3, 6, 11, 20 }, { 6, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 0, 0, 3353600, 13414400 }, { 0, 0, 0, 13414400 },  //stone 1 - defense
		//{ 3, 6, 11, 20 }, { 6, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 0, 0, 3353600, 13414400 }, { 0, 0, 3353600, 13414400 }, 
		{ 53, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 0, 0, 0, 1342177000 }, //stone 2 - offense
		//{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 1310720, 2621440, 5242880, 10485760 }, { 1310720, 2621440, 5242880, 10485760 } };
		{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 5242880, 10485760 }, { 0, 0, 0, 10485760 } }; // stone 2 - defense

	final static long[][] offense1Opening = {{ 13, 24, 44, 80 }, { 6, 97, 176, 320 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 16384000, 65536000 }, { 0, 0, 0, 65536000 }};
	final static long[][] offense2Opening = {{ 53, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 0, 0, 0, 1342177000}};
	final static long[][] defense1Opening = {{ 3, 6, 11, 20 }, { 97, 387, 704, 1280 }, { 24, 1549, 2816, 5120 }, { 0, 0, 3353600, 13414400 }, { 0, 0, 0, 13414400 }};
	final static long[][] defense2Opening = {{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 5242880, 10485760 }, { 0, 0, 0, 10485760 }};

	final static long[][] offense1 = {{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 16384000, 65536000 }, { 0, 0, 0, 65536000 }};
	final static long[][] offense2 = {{ 53, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 0, 0, 0, 1342177000 }};
	final static long[][] defense1 = {{ 3, 6, 11, 20 }, { 6, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 0, 0, 3353600, 13414400 }, { 0, 0, 0, 13414400 }};
	final static long[][] defense2 = {{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 5242880, 10485760 }, { 0, 0, 0, 10485760 }};
	public static void 
	aiTurn(int[][] board, int aiTag, int turn, cor[] evaluateResult)
	{
		System.out.println("----aiturn with tag: " + aiTag);
		int oppoTag = opponentTag(aiTag);
		int[][] scoreBoard = new int[19][19];

		initializeScoreBoard(scoreBoard);
		readBoWDir(board, scoreBoard, aiTag, 1, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 1, DEFENSE, turn);
		// find location with highest point
		// enter input
		if (DEBUG) printDebug(scoreBoard);
		aiInput(board, scoreBoard, aiTag, evaluateResult, 0);
		initializeScoreBoard(scoreBoard);

		if(DEBUG) System.out.println("-----------------------------------move2------------------------------------------");
		readBoWDir(board, scoreBoard, aiTag, 2, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 2, DEFENSE, turn);

		if (DEBUG) printDebug(scoreBoard);
		aiInput(board, scoreBoard, aiTag, evaluateResult, 1);
	}

	public static void
	runMinimax(int[][] board, int currTag, int turn, cor[] evaluateResult)
	{
		int[][] scoreBoard = new int[19][19];
		cor[] topFirstMoves = new cor[WIDTH], topSecondMoves = new cor[WIDTH];
		int[] topFirstScore = new int[WIDTH], topSecondScore = new int[WIDTH];
		boolean bestRecorded = false;
		int oppoTag = opponentTag(currTag); 
		long bestScore = 0, currScore = 0;
		cor bestFirst = new cor(), bestSecond = new cor();
		for(int i = 0; i < WIDTH; i++){ 
			topFirstMoves[i] = new cor(); 
			topSecondMoves[i] = new cor();
		}

		initializeScoreBoard(scoreBoard);
		readBoWDir(board, scoreBoard, currTag, 1, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 1, DEFENSE, turn);
		findTop(scoreBoard, topFirstMoves, topFirstScore);
		printDebug(scoreBoard);
		outerloop:
		for(int i = 0; i < WIDTH; i++){
			placeMove(board, topFirstMoves[i], currTag);
			System.out.println("\tFIRST MOVE: " + topFirstMoves[i]);
			if(isWin(board, topFirstMoves[i])){
				System.out.println("WIN DETECTED MOVE 1" + topFirstMoves[i] + " " + DEPTH);
				int second = 0;
				if(i == 0) second = 1;
				bestFirst.setI(topFirstMoves[i].getI());
				bestFirst.setJ(topFirstMoves[i].getJ());
				bestSecond.setI(topFirstMoves[second].getI());
				bestSecond.setJ(topFirstMoves[second].getJ());
				break;
			}

			initializeScoreBoard(scoreBoard);
			readBoWDir(board, scoreBoard, currTag, 2, OFFENSE, turn);
			readBoWDir(board, scoreBoard, oppoTag, 2, DEFENSE, turn);
			findTop(scoreBoard, topSecondMoves, topSecondScore);
			printDebug(scoreBoard);
			for(int j = 0; j < WIDTH; j++){
				currScore = topFirstScore[i] + topSecondScore[j];
				placeMove(board, topSecondMoves[j], currTag);
				System.out.println("\t\tSECOND MOVE: " + topSecondMoves[j]);
				if(isWin(board, topSecondMoves[j])){
					System.out.println("WIN DETECTED MOVE 2 " + topFirstMoves[i] + topSecondMoves[j] + " " + DEPTH);
					bestFirst.setI(topFirstMoves[i].getI());
					bestFirst.setJ(topFirstMoves[i].getJ());
					bestSecond.setI(topSecondMoves[j].getI());
					bestSecond.setJ(topSecondMoves[j].getJ());
					break outerloop;
				}
				currScore -= minimax(board, oppoTag, turn+1, DEPTH-1);

				printBoard(board);

				placeMove(board, topSecondMoves[j], EMPTY);

				if(!bestRecorded || currScore > bestScore){ 
					bestScore = currScore;
					bestFirst.setI(topFirstMoves[i].getI());
					bestFirst.setJ(topFirstMoves[i].getJ());
					bestSecond.setI(topSecondMoves[j].getI());
					bestSecond.setJ(topSecondMoves[j].getJ());
					bestRecorded = true;
				}
			}
			placeMove(board, topFirstMoves[i], EMPTY);
		}
		placeMove(board, bestFirst, currTag);
		placeMove(board, bestSecond, currTag);
		evaluateResult[0].setI(bestFirst.getI());
		evaluateResult[0].setJ(bestFirst.getJ());
		evaluateResult[1].setI(bestSecond.getI());
		evaluateResult[1].setJ(bestSecond.getJ());
		System.out.println("minimax:: " + bestFirst + " " + bestSecond + " " + bestScore);
	}

	public static long
	minimax(int[][] board, int currTag, int turn, int currDepth)
	{
		if(currDepth <= 0) return 0;

		int[][] scoreBoard = new int[19][19];
		cor[] topFirstMoves = new cor[WIDTH], topSecondMoves = new cor[WIDTH];
		int[] topFirstScore = new int[WIDTH], topSecondScore = new int[WIDTH];
		boolean bestRecorded = false;
		int oppoTag = opponentTag(currTag); 
		long bestScore = 0, currScore = 0;
		cor bestFirst = new cor(), bestSecond = new cor();
		for(int i = 0; i < WIDTH; i++){ 
			topFirstMoves[i] = new cor(); 
			topSecondMoves[i] = new cor();
		}

		initializeScoreBoard(scoreBoard);
		readBoWDir(board, scoreBoard, currTag, 1, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 1, DEFENSE, turn);
		findTop(scoreBoard, topFirstMoves, topFirstScore);
		for(int i = 0; i < WIDTH; i++){
			placeMove(board, topFirstMoves[i], currTag);
			if(isWin(board, topFirstMoves[i])) {
				System.out.println("WIN DETECTED move 1" + topFirstMoves[i] + " " + currDepth);
				placeMove(board, topFirstMoves[i], EMPTY);
				return 10000000000L;
			}
			
			initializeScoreBoard(scoreBoard);
			readBoWDir(board, scoreBoard, currTag, 2, OFFENSE, turn);
			readBoWDir(board, scoreBoard, oppoTag, 2, DEFENSE, turn);
			findTop(scoreBoard, topSecondMoves, topSecondScore);

			for(int j = 0; j < WIDTH; j++){
				currScore = topFirstScore[i] + topSecondScore[j];
				placeMove(board, topSecondMoves[j], currTag);
				if(isWin(board, topSecondMoves[j])){
					System.out.println("WIN DETECTED move2" + topFirstMoves[i] + topSecondMoves[j] + " " + currDepth);
					placeMove(board, topFirstMoves[i], EMPTY);
					placeMove(board, topSecondMoves[j], EMPTY);
					return 10000000000L;
				}

				currScore -= minimax(board, oppoTag, turn+1, currDepth-1);

				if(!bestRecorded || currScore > bestScore){ 
					bestScore = currScore;
					bestRecorded = true;
				}
				placeMove(board, topSecondMoves[j], EMPTY);
			}
			placeMove(board, topFirstMoves[i], EMPTY);
		}
		return bestScore;
	}

	static void
	placeMove(int[][] board, cor move, int tag)
	{ 
		System.out.println("CHANGING " + board[move.getI()][move.getJ()] + " to " + tag + " at " + move);
		board[move.getI()][move.getJ()] = tag; }

	static boolean
	isWin(int[][] board, cor move)
	{
		int count = 0, homeTag = board[move.getI()][move.getJ()];
		int moveI = move.getI(), moveJ = move.getJ();
		if(homeTag == 0) System.out.println("************SOMETHING VERY WRONG************");
		//check horizontal
		for(int j = moveJ; j < 19; j++){
			if(board[moveI][j] == homeTag) count++;
			else break;
		}
		for(int j = moveJ; j > -1; j--){
			if(board[moveI][j] == homeTag) count++;
			else break;
		}
		if(count > 6) return true;
		else count = 0;

		//check vertical
		for(int i = moveI; i < 19; i++){
			if(board[i][moveJ] == homeTag) count++;
			else break;
		}
		for(int i = moveI; i > -1; i--){
			if(board[i][moveJ] == homeTag) count++;
			else break;
		}
		if(count > 6) return true;
		else count = 0;

		//check right-up diagonal
		for(int i = moveI, j = moveJ; i < 19 && j > -1; i++, j--){
			if(board[i][j] == homeTag) count++;
			else break;
		}
		for(int i = moveI, j = moveJ; i > -1 && j < 19; i--, j++){
			if(board[i][j] == homeTag) count++;
			else break;
		}
		if(count > 6) return true;
		else count = 0;	

		//check left-up diagonal
		for(int i = moveI, j = moveJ; i > -1 && j > -1; i--, j--){
			if(board[i][j] == homeTag) count++;
			else break;
		}
		for(int i = moveI, j = moveJ; i < 19&& j < 19; i++, j++){
			if(board[i][j] == homeTag) count++;
			else break;
		}
		if(count > 6) return true;
		return false;
	}

	public static void 
	printBoard(int[][] board) 
	{
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

	static void
	findTop(int[][] scoreBoard, cor[] topMoves, int[] topScore)
	{
		for(int i = 0; i < WIDTH; i++){
			topMoves[i].setI(-1);
			topMoves[i].setJ(-1);
			topScore[i] = 0;
		}

		for(int i = 0; i < 19; i++){
			for(int j = 0; j < 19; j++){
				for(int k = WIDTH-1; k > -1; k--){
					if(scoreBoard[i][j] > topScore[k]){
						if(k != 0) continue;
						else k--;
					}
					if(k < WIDTH-1){
						int targetScore = scoreBoard[i][j], targetX = i, targetY = j;
						int tempScore, tempX, tempY;
						for(k++; k < WIDTH; k++){
							tempScore = topScore[k];
							tempX = topMoves[k].getI();
							tempY = topMoves[k].getJ();
							topScore[k] = targetScore;
							topMoves[k].i = targetX;
							topMoves[k].j = targetY;
							targetScore = tempScore;
							targetX = tempX;
							targetY = tempY;
						}
						break;
					}
					else { break; }
				}
			}
		}
	}

	static void initializeScoreBoard(int[][] scoreBoard) {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				scoreBoard[i][j] = 0;
			}
		}
	}

	static void
	aiInput(int[][] board, int[][] scoreBoard, int aiTag, cor[] evaluateResult, int index)
	{
		int high_i = 0, high_j = 0;
		int high_score = 0;

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (high_score < scoreBoard[i][j]) {
					high_score = scoreBoard[i][j];
					high_i = i;
					high_j = j;
				}
			}
		}
		evaluateResult[index].setI(high_i);
		evaluateResult[index].setJ(high_j);
		board[high_i][high_j] = aiTag;

		System.out.println("Input aiTag x" + high_i + ", y" + high_j + ".");
		System.out.println();
	}

	/*
	 * read board left to right, top to bottom, left top to right bottom, left
	 * bottom to right top if there is a stone, hand direction, cor of the beginning
	 * of the row, copied data of the row, usertag to evaGIveScore() all direction
	 * start from (0,0) except left bottom to right top which starts from (18,0)
	 * (0,0) being left top corner; (18,0) being left bottom
	 */
	public static void readBoWDir(int[][] rawData, int[][] scoreBoard, int userTag, int runNumber, int offOrDef, int turn) {
		long[][] scoreArr;
		int k = 0;
		int[] oneRow = new int[19];
		if(turn < 6){
            if(runNumber == 1){
                if(offOrDef == OFFENSE) scoreArr = offense1Opening;
                else scoreArr = defense1Opening;
            }
            else{
                if(offOrDef == OFFENSE) scoreArr = offense2Opening;
                else scoreArr = defense2Opening;
            }
        }
        else{
            if(runNumber == 1){
                if(offOrDef == OFFENSE) scoreArr = offense1;
                else scoreArr = defense1;
            }
            else{
                if(offOrDef == OFFENSE) scoreArr = offense2;
                else scoreArr = defense2;
            }
        }

		for (int i = 0; i < 19; i++) { // left right
			for (int j = 0; j < 19; j++) {
				if (rawData[i][j] == userTag) {
					move.i = i; move.j = 0;
					for (j = 0; j < 19; j++) { oneRow[j] = rawData[i][j]; }
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, LEFTRIGHT, runNumber, offOrDef, turn);
					break;
				}
			}
		}

		for (int i = 0; i < 19; i++) { // top down
			for (int j = 0; j < 19; j++) {
				if (rawData[j][i] == userTag) {
					move.i = 0; move.j = i;
					for (j = 0; j < 19; j++) { oneRow[j] = rawData[j][i]; }
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, TOPDOWN, runNumber, offOrDef, turn);
					break;
				}
			}
		}

		for (int i = 0; i < 14; i++) {// top left to right bottom, top left to top right
			k = i;
			for (int j = 0; j < 19 - i; j++) {
				if (rawData[k][j] == userTag) {
					move.i = i; move.j = 0; k = i;
					for (j = 0; j < 19 - i; j++) {
						oneRow[j] = rawData[k][j];
						k++;
					}
					evaGiveScoreC1(scoreBoard, oneRow, 19 - i, userTag, move, TOPLBOTR, runNumber, offOrDef, turn);
					break;
				}
				k++;
			}
		}
		for (int i = 1; i < 14; i++) {// top left to right bottom, left top to left down
			k = 0;
			for (int j = i; j < 19; j++) {
				if (rawData[k][j] == userTag) {
					move.i = 0; move.j = i; k = 0;
					for (j = i; j < 19; j++) {
						oneRow[k] = rawData[k][j];
						k++;
					}
					evaGiveScoreC1(scoreBoard, oneRow, k, userTag, move, TOPLBOTR, runNumber, offOrDef, turn);
					break;
				}
				k++;
			}
		}

		for (int i = 18; i > 4; i--) {// top right to left bottom, top right to top left
			k = i;
			for (int j = 0; j < i; j++) {
				if (rawData[k][j] == userTag) {
					move.i = i; move.j = 0; k = i;
					for (j = 0; j < i; j++) {
						oneRow[j] = rawData[k][j];
						k--;
					}
					evaGiveScoreC1(scoreBoard, oneRow, i + 1, userTag, move, BOTLTOPR, runNumber, offOrDef, turn);
					break;
				}
				k--;
			}
		}
		for (int i = 1; i < 14; i++) {// top right to left bottom, right top to right bottom
			k = 18;
			for (int j = i; j < 18; j++) {
				if (rawData[k][j] == userTag) {
					move.i = 18; move.j = i; k = 18;
					int pos = 0;
					for (j = i; j < 18; j++) {
						oneRow[pos] = rawData[k][j];
						k--;
						pos++;
					}
					evaGiveScoreC1(scoreBoard, oneRow, 19 - i, userTag, move, BOTLTOPR, runNumber, offOrDef, turn);
					break;
				}
				k--;
			}
		}

		return;
	}
	
	public static void 
	evaGiveScoreC1(int[][] scoreBoard, int dummycell[], int onerawdatalen, int userTag, cor move,
			int direction, int runNumber, int offOrDef, int turn) 
	{
		long[] one_rawData = new long[onerawdatalen];
		int first_notusertag = -1, gab_notusertag = 0; // dummycell을 살펴볼 때 만약 notusertag가 있다면 위치를 기억하기 위한 수
		int oppoTag = opponentTag(userTag);
		int pos = 0, stoStart = -1, frontGap = 0, btwGap = 0, count1 = 0, count2 = 0, count2Start = -1, backGap = 0;
		boolean conti = false, blocked = false, backBlocked = false;

		if(PRINTINFO) {
			System.out.println("debug——— for : " + userTag);
			System.out.println("dir: " + direction + " | pos x: " + move.i + " | pos y: " + move.j);
			for (int i = 0; i < onerawdatalen; i++) {
				System.out.printf("%d ", dummycell[i]);
			}
			System.out.println();
		}

		/*
		 * convert dead area to oppotag
		 */
		/*
		 * for (int i = 0; i < onerawdatalen; i++) { // 받아온 데이터를 dummycell 로 옮긴다.
		 * dummycell[i] = one_rawData[i]; }
		 */
		for (int i = 0; i < onerawdatalen; i++) {
			if (dummycell[i] == 3)
				dummycell[i] = oppoTag;
			if (dummycell[i] == oppoTag) { // 만약 각 cell 이 notusertag 라면.
				if (first_notusertag == -1) { // 처음 notusertag가 나왔다면.
					gab_notusertag = i; // gab은 처음 나온 수 index 만큼이다.
					first_notusertag = 0; // 이제 notusertag가 한 번 나왔기 때문에 0으로 바꿔준다.
				} else {
					gab_notusertag = i - first_notusertag - 1; // 두번째 notusertag가 나왔다면 두 index의 차이에 -1 이다.
				}
				if (gab_notusertag < 6) { // 만약 차이가 5이하이면 어차피 죽은 공간, 따라서 검은 돌만 있다고 생각한다.
					for (int j = first_notusertag; j < i; j++) {
						dummycell[j] = oppoTag;
					}
				}
				first_notusertag = i;
			}
			if (i == onerawdatalen - 1) {
				if (i - first_notusertag < 6) {
					for (int j = first_notusertag + 1; j < onerawdatalen; j++) {
						dummycell[j] = oppoTag;
					}
				}
			}
		} // end convert dead

		/*
		 * segment usertag,,
		 * diagnosis--------------------------------------------------------------
		 */
		if(DEBUG){
			System.out.print("dummies:");
			for (int k = 0; k < onerawdatalen; k++) {
				System.out.print(dummycell[k] + " ");
			}
			System.out.println();
		}
		// 만약에 아군 돌들 사이에 장애물이 없다면 시작과 끝은 데려와서 1-1-11의 케이스를 확인할 수 있도록
		
		int[] lastCaseInfoList = new int[10];
		int isConnected = 0;
		/*
		 * 차례대로 pos_1, count1_1, btwgap_1, count2_1, btwgap_2, count2_2
		 * 예를 들어 0 0 0 0 0 0 X 0 X 0 X X 0 0 0 ... 라고 해보자
		 * 그렇다면 pos_1 은 6, 
		 * 아래 while 반복문을 돌리면 debug가 다음과 같이 나온다.
		 * case connected frontGap 6 count1 1 btwGap 1 count2 1 X 0 X ...
		 * case connected frontGap 0 count1 1 btwGap 1 count2 2 .. X 0 X X ..
		 * case blocked
		 * 
		 * 따라서 count1_1 = 1 , bywgap_1 =1 , count2 = 1, btwGap_2 = 1, count2_2 =2 라고 할 수 있다.
		 * 여기서 pos_1을 제외하고 나머지의 값들의 합이 6 이상이고, btwGap들의 합이 2일 때가 X 0 X 0 X X 류의 케이스라고 판단.
		 * 사실 X X 0 X 0 X X 0 X 등등 이렇게 나오면 당연히 막을 수 있다. 엇 아닌가 2 1 2... 이거 생각보다 점수가 작게 될지도.. -> 아님 잘 될 것 같
		 * 
		 * 맨 처음 X 0 X 0 X 같은 경우에는 안됨 그렇지만 그 뒤에 X 0 X 0 X X 같은 경우에는 된다. 그렇기 때문에 이 경우도 생각해주어야 한다.
		 * connected 가 나온다면 바로 isConnected 변수가 1증가한다. 그렇다면 isConnected가 0이 아니라면 lastCaseInfoList[0] = pos, 
		 * lastCaseInfoList[1] = count1_1, lastCaseInfoList[2] = btwgap_1, lastCaseInfoList[3] = count2_1 이 들어간다.
		 * 
		 * 그리고 한 번 더 돌렸는데 connected가 된다면? 그러면 1이 증가하면서 isConnected 가 2가 될 것이다. 그러면 lastCaseInfoList[4] = count1_2,
		 * lastCaseInfoList[5] = btwgap_2, lastCaseInfoList[6] = count2_2 가 들어가도록한다.@
		 *  -> 여기서 isConnected가 2 이하일 때만 입력이 되도록 한다. 만약 2가 다 채워졌고, 합이 6이상이라 isconnected가 1로 내려가지 않으면 connected가 한 번 더
		 *  되면 isConnected가 3이 된다. 그러면 사실 이건 못 막는다. 혹은 앞에서 한 번 막고 그 다음에 다시 막아야한다.
		 * 
		 * 그렇다면 한 번 더 돌렸는데 connected가 아니라면? 그러면 isConnected가 다시 0이 되게한다.따라서 한번 while구문이 돌았을 때
		 *  isConnected가 0이라면 lastCaseInfoList를 모두 초기화 한다.@
		 *  
		 *  그렇다면 X 0 X 0 X 0 X X  는 어떻게 알까?
		 *  위는 connected가 연속 3번 이상이 될 때를 말하는 것이다. 그렇다면 2번 연속이 될 때 한번 걸러준다. lastCaseInfoList[1~3,5,6]을 합했을 때 6이상인가를 확인.
		 *  만약 6 이상이 되지 않는다면? 또 btwgap이 각각 1이 아니라면?(이것 같은 경우에는 X X 0 0 X X 를 뜻하는건데, 그냥도 잘 된다) 그렇다면 그건 딱히 필요없는 정보들이다. 
		 *  따라서 position 값인 lastCaseInfoList[0]에 count1 + btwgap을 하면 다음의 Position으로 설정 가능.
		 *  
		 *  그 뒤 [1] = [4] , [2] = [5], [3] = [6] 으로 바꾼다. 그러면 앞전에 정보는 버리게 되고 다음 것을 받을 준비를 할 수 있게 되는 것이다. 
		 *  이때 중요한 점은 [4],[5],[6] 부분은 0으로 꼭 만들어줘야 한다. 그리고 isConnected는 1로 바꾼다.@

		 *  
		 *  그러면 이 while 이 다 돌고 나왔을 때 판단을 해보자.
		 *  
		 *  만약 isConnected가 2라면 이제 점수를 추가해준다. 
		 *  one_dataRaw[pos+count1_1] 
		 *  one_dataRaw[pos+count1_1+btwgap1 + count2_1] 에 점수를 추가 해준다.
		 *  
		 *  그 점수는 가장 큰 점수를 넣는다. 
		 *  
		 */
		while (pos < onerawdatalen) {
			int temp;
			if ((temp = dummycell[pos]) == oppoTag) {
				if (conti) {
					// (for special case)
					if(isConnected!= 2) { //2개라면 건드리지 말아야함!
						isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
						lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
					}
					
					if(DEBUG) System.out.println("blocked directly " + frontGap + " " + count1 + " " + btwGap);
					blocked = true;
					giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked, backBlocked, runNumber, offOrDef, turn); // blocked
					// btwgap
					// count1 frontgap

					conti = false;
					blocked = false;
					frontGap = 0;
					count1 = 0;
					stoStart = -1;
				}
				frontGap = 0;
			} else if (temp == 0) {
				if (conti) {// 앞에 돌이 잘 나오다가 빈 공간 따라서 뒤에 상태에 따라서 점수를 줘야헌다.
					btwGap++;
					for (int j = 1; j < 4; j++) {
						if (pos + j >= onerawdatalen) {
							//(for special case)
//							if(isConnected!= 2) { //2개라면 건드리지 말아야함!
//								isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
//								lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
//							}
//							
							
							if(DEBUG) System.out.println("end of line");
							blocked = true;
							break;
						} else if (dummycell[pos + j] == oppoTag) {
							//(for special case)
//							if(isConnected!= 2) { //2개라면 건드리지 말아야함!
//								isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
//								lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
//							}
							
							if(DEBUG) System.out.println("blocked!");
							blocked = true;
							break;
						} else if (dummycell[pos + j] == userTag) {
							//(for special case)
//							if(isConnected!= 2) { //2개라면 건드리지 말아야함!
//								isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
//								lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
//							}
							break;
						} else {
							btwGap++;
						}
					}
					if(DEBUG) System.out.println("btwgap==" + btwGap);
					if (btwGap == 4) {
						// (for special case)
						if(isConnected!= 2) { //2개라면 건드리지 말아야함!
							isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
							lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
						}
						if(DEBUG){	
							System.out.println("case isolated " + frontGap + " " + count1);
							System.out.println("case isolated " + stoStart);
						}
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked,
								backBlocked, runNumber, offOrDef, turn);
						// 여기서도 iso라고 밝히고 점수를 줘야
					} else if (blocked) {
						// (for special case)
						if(isConnected!= 2) { //2개라면 건드리지 말아야함!
							isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
							lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
						}
						if(DEBUG){
							System.out.println("case blocked stoStart " + stoStart);
							System.out.println("case blocked " + frontGap + " " + count1 + " " + btwGap);
						}
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked,
								backBlocked, runNumber, offOrDef, turn);
						// blocked 밝히고 btwGap이 뒤에 공간이라고 넘기고 계산
					} else if (btwGap < 4) { // count2가 있다고 생각하는 case
						// frontGap count1 gap count2 --> score
						
						//(for special case)
						isConnected += 1; // 이 때 case가 connected가 발생하는 경우이다. 따라서 isConnected를 1올린다.
						
						pos += btwGap;
						count2Start = pos;
						while (pos < onerawdatalen && dummycell[pos] == userTag) {// i pointing at the space behind the
																					// stone after the loop,
							// may be 0 or oppo or 3
							count2++;
							pos++;
						}
						for (int i = 0; i < 2; i++) {
							if (pos + i >= onerawdatalen) //여기 -1추가함 11.23 화요일 19:38
								break;
							else if (dummycell[pos + i] == 0)
								backGap++;
							else if (dummycell[pos + i] == oppoTag)
								backBlocked = true;
						}

						pos--;
						if(DEBUG){
							System.out.println(
									"case connected " + frontGap + " " + count1 + " " + btwGap + " " + count2 + " ");
							System.out.println("case connected stoStart " + stoStart);
						}
						
						//(for special case)
						//isConnected가 1이라는 소리는 connected가 첫번째로 나왔다는 소리. 따라서 [0]에 stoStart를 넣어줘서 어디서 시작인지 넣어둔다.
						if(isConnected ==1) {  
							lastCaseInfoList[0] = stoStart; 
						}
						//1일 때는 index 1,2,3에 정보를 넣고, 2일 때는 4,5,6에 넣는다.
						//isConnected는 무조건 1이상이므로 마이너스가 나올 수 없다.
						lastCaseInfoList[(isConnected-1)*3+1] = count1;
						lastCaseInfoList[(isConnected-1)*3+2] = btwGap;
						lastCaseInfoList[(isConnected-1)*3+3] = count2;
						
						if(isConnected == 2) {
							int confirm_special_sum =0; //lastCaseInfoList에 있는 값들을 합쳐서 6이상이 되면, 필요한 값이다.
							for(int c_i = 1; c_i <7; c_i++) {
								confirm_special_sum +=lastCaseInfoList[c_i];
							}
							confirm_special_sum -= lastCaseInfoList[3];
							//여기까지 하면 count1_1, btwgap_1, count2_1, btwgap_2, count2_2의 값을 다 더 한 것이라 볼 수 있다.
							
							if(confirm_special_sum > 5 && lastCaseInfoList[2] == 1 && lastCaseInfoList[5] == 1) {
								if(DEBUG) System.out.println("Last case : 잘 들어감!");
							}else {
								/*
								만약 6이상이 아니거나, btwgap이 각각 1이 아니라면 딱히 필요 없는 값들인것이다. 왜냐하면 X 0 0 0 X 0 0 0 0 X 도 합쳤을 때 
								6이상이 되는데 필요한 값이 아니다. 만약 X 0 X 0 0 X X 라면? 물론 충분히 위협적이지만 바로 끝낼 수 있는 값이 아님. 다른 case로 무마 가능.
								따라서 btwgap이 모두 1로 설정한다.
								결론적으로, 첫 번째 connected 정보를 지우고 두번째 connected 정보를 앞으로 바꾼다. 
								X 0 X 0 X 0 X X 를 예로 생각해보면 처음엔 X 0 X 0 X 로 합이 5가 되면서 필요없는 정보가 돼버림. 따라서 두번째 정보를 앞으로 옮겨서 X 0 X 0 X X를 계산.
								*/
//								따라서 position 값인 lastCaseInfoList[0]에 count1 + btwgap을 하면 다음의 Position으로 설정 가능.
								int temp_a, temp_b, temp_c, temp_d=0;
								temp_a = lastCaseInfoList[0] + lastCaseInfoList[1] + lastCaseInfoList[2]; 
								//첫 번째 position + count1_1 + btwgap_1 = 두 번째 position
								
								temp_b = lastCaseInfoList[4]; // count1_2
								temp_c = lastCaseInfoList[5]; //btwgap_2
								temp_d = lastCaseInfoList[6]; // count2_2
								
								lastCaseInfoList = new int[10]; // 이렇게 해도 되는지 모르겠지만 초기화 해준 것이다.
								
								//이제 다시 넣어준다.
								lastCaseInfoList[0] = temp_a;
								lastCaseInfoList[1] = temp_b;
								lastCaseInfoList[2] = temp_c;
								lastCaseInfoList[3] = temp_d;
								if(DEBUG) System.out.println("여기 확인 하는거임!!!!!!!!!!!!!!!" +lastCaseInfoList[0] + lastCaseInfoList[1] + lastCaseInfoList[2] +lastCaseInfoList[3]);
								//이렇게 되면 connected는 하나만 있는 것과 동일하므로 isConnected를 1로 바꿔준다.
								isConnected = 1;
								
							}
						}
						if(DEBUG){
							System.out.print("this is lastCaseInfo:  ");
							for(int i=0; i<lastCaseInfoList.length; i++) {
								System.out.print(lastCaseInfoList[i]+" ");
							}
							System.out.println();
						}
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked, backBlocked, runNumber, offOrDef, turn);
						
						// 여기서 frontgap count1 btwgap count2로 점수를 처리하고 btwgap을 frontgap으로, count2을
						// count1로 돌리면 그게 다음 돌들의 info가 된다.
						// 그리고 그렇게 해서 frontgap에 점수를 넣을려고 하는데 있으면 안넣고 페스하면 된다. 그게 벽으로막혀있는 게 아니기때문에 점수를 짜게
						// 줄 필요가 없다.
					} else
						System.out.println("bug2");
					// check if there is stone within 3 blocks, no then isolated, yes then count
					// them too
					if (count2 == 0)
						conti = false;
					btwGap = 0;
					blocked = false;
					frontGap = 0;
					count1 = count2;
					count2 = 0;
					stoStart = count2Start;
					backBlocked = false;
					backGap = 0;
				} else {
					frontGap++;
				}
			} else if (temp == userTag) {
				if (stoStart == -1)
					stoStart = pos;
				conti = true;
				count1++;
			}
			pos++;
		} // while loop end
		
//		그러면 이 while 이 다 돌고 나왔을 때 판단을 해보자.
//		 *  
//		 *  만약 isConnected가 2라면 이제 점수를 추가해준다. 
//		 *  one_dataRaw[stoStart+count1_1] 
//		 *  one_dataRaw[stoStart+count1_1+btwgap1 + count2_1] 에 점수를 추가 해준다.
//		 *  
//		 *  그 점수는 가장 큰 점수를 넣는다.
		
		//for special case
		// 어디에 무엇이 들어갈지 알았음. 그러면 점수를 넣어보자.
		if(DEBUG){
			System.out.println("!!!!!!!!length!!!!!!" + lastCaseInfoList.length);
			System.out.print("this is lastCaseInfo:  ");
			for(int i=0; i<lastCaseInfoList.length; i++) {
				System.out.print(lastCaseInfoList[i]+" ");
			}
			System.out.println();
		}
		
		int length_lastCase=0;
		for(int pl =1; pl < lastCaseInfoList.length; pl++) {
			if(lastCaseInfoList[pl] != 0)
				length_lastCase +=1;
		}
		if(length_lastCase == 6) {
			if(PRINTINFO) System.out.println("됐다!!!!");
			long[][] scoresArray_forSpecial;
			if(turn<4) 
				scoresArray_forSpecial=scoresArray1;
			else
				scoresArray_forSpecial=scoresArray2;
			int scoreArrayAdd = 0, temp = 0;
			if (runNumber == 1 && offOrDef == OFFENSE)
				scoreArrayAdd = 0;
			else if (runNumber == 1 && offOrDef == DEFENSE)
				scoreArrayAdd = 5;
			else if (runNumber == 2 && offOrDef == OFFENSE)
				scoreArrayAdd = 10;
			else if (runNumber == 2 && offOrDef == DEFENSE)
				scoreArrayAdd = 15;
			one_rawData[lastCaseInfoList[0] + lastCaseInfoList[1]] += scoresArray_forSpecial[scoreArrayAdd+4][3];
			one_rawData[lastCaseInfoList[0] + lastCaseInfoList[1] + lastCaseInfoList[2] + lastCaseInfoList[3]] += scoresArray_forSpecial[scoreArrayAdd+4][3];
			
		}
		if(DEBUG){
			System.out.print("this is one_rawdata:  ");
			for(int i=0; i<onerawdatalen; i++) {
				System.out.print(one_rawData[i]+" ");
			}
			System.out.println();
		}

		switch (direction) {
		case LEFTRIGHT: // left to right
			for (int i = 0; i < 19; i++) {
				scoreBoard[move.i][i] += one_rawData[i];// one_rawScore[i];
			}
			break;

		case TOPDOWN: // top down
			for (int i = 0; i < 19; i++) {
				scoreBoard[i][move.j] += one_rawData[i];// one_rawScore[i];
			}
			break;

		case TOPLBOTR: // TOP L TO BOT R
			for (int i = 0; i < onerawdatalen; i++) {
				scoreBoard[move.i][move.j] += one_rawData[i];// one_rawScore[i];
				move.i++;
				move.j++;
			}
			break;

		case BOTLTOPR: // BOT L TO TOP R
			for (int i = 0; i < onerawdatalen; i++) {
				scoreBoard[move.i][move.j] += one_rawData[i];// one_rawScore[i];
				move.i--;
				move.j++;
			}
			break;

		default:
			break;
		}// end of switch
	}

	public static void printDebug(int[][] scoreBoard) {
		System.out.println(
				"||x\\y\t   0    1    2    3    4    5    6    7    8    9    a    1    2    3    4    5    6    7    8");
		for (int i = 0; i < 19; i++) {
			System.out.printf("||%d\t", i);
			for (int j = 0; j < 19; j++) {
				System.out.printf("%4d ", scoreBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int opponentTag(int userTag) {
		if (userTag == BLACK) return WHITE;
		else return BLACK;
	}

	public static void 
	giveScore(long[] one_rawData, int frontGap, int count1, int btwGap, int count2, int backGap, int pos, boolean blocked, boolean backBlocked, int runNumber, int offOrDef, int turn) 
	{
		long[][] scoresArray;
		if (turn < 6)
			scoresArray = scoresArray1;
		else
			scoresArray = scoresArray2;
		int scoreArrayAdd = 0;
		long temp = 0;
		if (runNumber == 1 && offOrDef == OFFENSE)
			scoreArrayAdd = 0;
		else if (runNumber == 1 && offOrDef == DEFENSE)
			scoreArrayAdd = 5;
		else if (runNumber == 2 && offOrDef == OFFENSE)
			scoreArrayAdd = 10;
		else if (runNumber == 2 && offOrDef == DEFENSE)
			scoreArrayAdd = 15;
		/*
		 * doing front calc
		 */
		if (frontGap > 4) {
			frontGap = 4;
		}
		pos -= frontGap;
		if (DEBUG) System.out.println("--debug--givascore frontgap: " + scoreArrayAdd + " " + count1);

		//for (int i = frontGap -1; i >= 0; i--) {
			//temp = scoresArray[scoreArrayAdd + count1 - 1][3-i];

		for(int i = 0; i < frontGap; i++){
			temp = scoresArray[scoreArrayAdd + count1 - 1][i];
			if (btwGap < 3 && blocked) {
				temp /= 4.5;
			}
			if (one_rawData[pos + i] < temp) {
				one_rawData[pos + i] = temp;// scoresArray[scoreArrayAdd + count1 -1][i];
			}
		}
		pos += frontGap + count1;
		/*
		 * doing back calc, pos at the start of bwtgap
		 */
		if (blocked) {
			int j = 0;
			for (int i = btwGap - 1; i >= 0; i--) {
				temp = scoresArray[scoreArrayAdd + count1 - 1][j];
				if (frontGap < 3)
					temp /= 4.5;
				one_rawData[pos + i] = temp;
				j++;
			}
		} 
		else if (count2 == 0) {// case isolated
			int j = 0;
			if (DEBUG) System.out.println("--debug--givascore btwgap 1: " + scoreArrayAdd + " " + count1);
			for (int i = btwGap - 1; i >= 0; i--) {
				temp = scoresArray[scoreArrayAdd + count1 - 1][i];
				if (frontGap < 3)
					temp /= 4.5;
				one_rawData[pos + j] = temp;
				j++;
			}
			return;
		} 
		else if (count2 != 0) {// if not isolated
			if (offOrDef == DEFENSE && btwGap <= 2 || runNumber == 1 && btwGap <= 2) { // run1 with gap 2, consider them
																						// as one, for
				// defense no need to differentiate
				// if (!backBlocked||count1+count2>=4) {
				count1 += count2;
				if(count1 >5)
						count1 = 5; //여기서 count1 + count2 가 5를 넘어가는 경우가 있음 예를 들어 x x x 0 0 O O O일 때 그래서 그냥 5로 맞춰 11.23. 19:55
				for (int i = 0; i < btwGap; i++) {
					if(DEBUG) System.out.println("scor + co " +scoreArrayAdd+ " " + count1);
					temp = scoresArray[scoreArrayAdd + count1 - 1][3];
					if (frontGap < 3 || backBlocked)
						temp /= 4.5;
					one_rawData[pos + i] = temp;
				}
				// }
				/*
				 * else { int j=0, i=btwGap+count2+backGap; if(i>4) i=3; for (; i >=0; i--) {
				 * temp = scoresArray[scoreArrayAdd + count1 - 1][j]; if (frontGap < 3) temp /=
				 * 4.5; one_rawData[pos + i] = temp; j++; } }
				 */
			} else if (runNumber == 2 && btwGap == 1) { // for run2 only consider 2 pile of stones with 1gap as one
				count1 += count2;
				for (int i = 0; i < btwGap; i++) {
					temp = scoresArray[scoreArrayAdd + count1 - 1][3];
					if (frontGap < 3 || backBlocked)
						temp /= 4.5;
					one_rawData[pos + i] = temp;
				}
			} else {// else just consider them as individuals
				int j = 0;
				for (int i = btwGap - 1; i >= 0; i--) {
					temp = scoresArray[scoreArrayAdd + count1 - 1][i];
					if (frontGap < 3 || backBlocked)
						temp /= 4.5;
					one_rawData[pos + j] = temp;
					j++;
				}
			}
		}
		/*
		 * if (frontGap == 0) { System.out.println("front is blocked directly"); } else
		 * if (frontGap < 4) {
		 * System.out.println("font is blocked indirectly with gap: " + frontGap); } if
		 * (blocked && btwGap == 0) { System.out.println("end is blocked directly"); }
		 * else if (blocked) { System.out.println("end is blocked indirectly with gap: "
		 * + btwGap); }
		 */
	}
	
	public static int chkDummyCell(int[] dummycell,int onerawdatalen, int userTag) { //6개가 연속으로 되는지 확인하는 함수 11.23
		//dummycell을 받아서 계
		int overSix=0;
		for(int i=0; i<onerawdatalen;i++) {
			if(dummycell[i] == userTag) {
				overSix++;
			}else {
				overSix=0;
			}
		}
		return overSix; //이걸 받을 때는 overSix가 6보다 클 때 true라고 하면서 6개가 되는지 확인 할 수 있다.
	}
}

//막힌게 2칸 떨어져있으면 반대편 /4.5 3칸 떨어져있으면 그대
//앞에 들어가는 점수들은 돌들에만 의존하지 말고 뒤의 갭과 앞의 돌들을 같이 고려하면 훨씬 좋을
