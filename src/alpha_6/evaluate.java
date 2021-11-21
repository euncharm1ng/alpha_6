//package alpha_6;

public class evaluate {
	final static private int EMPTY = 0;
	final static private int BLACK = 1;
	final static private int WHITE = 2;
	final static private int RED = 3;
	final static private int width = 3;
	static cor move = new cor();
	final static int LEFTRIGHT = 0, TOPDOWN = 1, TOPLBOTR = 2, BOTLTOPR = 3, OFFENSE = 1, DEFENSE = 2;
	static boolean DEBUG = false, PRINTINFO = false;
	final static int[][] scoresArray1 = { { 13, 24, 44, 80 }, { 6, 97, 176, 320 }, { 1549, 6195, 11264, 20480 },
			{ 1024000, 4096000, 16384000, 65536000 }, { 1024000, 4096000, 16384000, 65536000 }, { 3, 6, 11, 20 },
			{ 97, 387, 704, 1280 }, { 24, 1549, 2816, 5120 }, { 209600, 838400, 3353600, 13414400 },
			{ 209600, 838400, 3353600, 13414400 }, { 53, 97, 176, 320 }, { 97, 387, 704, 1280 },
			{ 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 167772160, 335544320, 671088640, 1342177000 },
			{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 },
			{ 1310720, 2621440, 5242880, 10485760 }, { 1310720, 2621440, 5242880, 10485760 } };
	final static int[][] scoresArray2 = { 
			{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 1024000, 4096000, 16384000, 65536000 }, { 1024000, 4096000, 16384000, 65536000 }, 
			{ 3, 6, 11, 20 },{ 6, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 209600, 838400, 3353600, 13414400 },{ 209600, 838400, 3353600, 13414400 }, 
			{ 53, 97, 176, 320 }, { 97, 387, 704, 1280 },{ 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 167772160, 335544320, 671088640, 1342177000 },
			{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 },{ 1310720, 2621440, 5242880, 10485760 }, { 1310720, 2621440, 5242880, 10485760 } };

	/*
	 * final static int[][] scoresArray = { { 5, 10, 20, 35 }, { 15, 20, 30, 45 }, {
	 * 10, 15, 25, 40 }, { 0, 0, 0, 1000 }, { 0, 0, 0, 1000 }, { 0, 0, 5, 20 }, { 0,
	 * 5, 15, 30 }, { 10, 15, 25, 40 }, { 0, 0, 120, 200, }, { 0, 0, 0, 200 }, { 0,
	 * 0, 80, 200 }, { 5, 10, 20, 35 }, { 0, 5, 15, 30 }, { 10, 15, 25, 40 }, { 0,
	 * 0, 0, 2000 }, { 0, 0, 10, 25 }, { 15, 20, 30, 45 }, { 0, 5, 15, 30 }, { 0, 0,
	 * 0, 1000 }, { 0, 0, 0, 1000 } };
	 * 
	 * final static int[][] scoresArray = { { 13, 24, 44, 80 }, { 24, 1549, 2816,
	 * 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0, 0, 512000 }, { 0, 0, 0, 512000
	 * }, { 3, 6, 11, 20 }, { 6, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 0, 0, 0,
	 * 102400 }, { 0, 0, 0, 102400 }, { 53, 97, 176, 320 }, { 97, 387, 704, 1280 },
	 * { 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 0, 0, 0, 2048000 }, { 13,
	 * 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 }, { 0, 0,
	 * 0, 409600 }, { 0, 0, 0, 409600 } };
	 */

	public static void aiTurn(int[][] board, int aiTag, int turn, cor[] logs, int logNum) {
		System.out.println("----aiturn with tag: " + aiTag);
		int oppoTag = opponentTag(aiTag);
		int[][] scoreBoard = new int[19][19];

		//minimax(board, aiTag, turn, depth);

		initializeScoreBoard(scoreBoard);
		readBoWDir(board, scoreBoard, aiTag, 1, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 1, DEFENSE, turn);

		if (DEBUG) printDebug(scoreBoard);
		aiInput(board, scoreBoard, aiTag, logs, logNum);

		if(DEBUG) System.out.println("-----------------------------------move2------------------------------------------");
		initializeScoreBoard(scoreBoard);
		readBoWDir(board, scoreBoard, aiTag, 2, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 2, DEFENSE, turn);

		if (DEBUG) printDebug(scoreBoard);
		aiInput(board, scoreBoard, aiTag, logs, logNum+1);
	}

	public static int
	minimax(int[][] board, int currTag, int turn, int currDepth, cor[] logs, int logNum)
	{
		if(currDepth <= 0) return 0;
		int[][] scoreBoard = new int[19][19];
		cor[] topFirstMoves = new cor[width], topSecondMoves = new cor[width];
		int[] topFirstScore = new int[width], topSecondScore = new int[width];
		boolean bestRecorded = false;
		int oppoTag = opponentTag(currTag), bestScore = 0, currScore = 0;
		cor bestFirst = new cor(), bestSecond = new cor();
		for(int i = 0; i < width; i++){ 
			topFirstMoves[i] = new cor(); 
			topSecondMoves[i] = new cor();
		}
		//System.out.println("entered minimax:: " + currDepth );

		initializeScoreBoard(scoreBoard);
		readBoWDir(board, scoreBoard, currTag, 1, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 1, DEFENSE, turn);

		findTop(scoreBoard, topFirstMoves, topFirstScore);
		for(int i = 0; i < width; i++){
			//System.out.println("\t first moves: " + currScore);
			board[topFirstMoves[i].getX()][topFirstMoves[i].getY()] = currTag;
			if(currDepth == 3) printBoard(board);
			
			initializeScoreBoard(scoreBoard);
			readBoWDir(board, scoreBoard, currTag, 2, OFFENSE, turn);
			readBoWDir(board, scoreBoard, oppoTag, 2, DEFENSE, turn);

			for(int j = 0; j < width; j++){
				topSecondMoves[j].x = -1;
				topSecondMoves[j].y = -1;
				topSecondScore[j] = 0;
			}
			findTop(scoreBoard, topSecondMoves, topSecondScore);
			for(int j = 0; j < width; j++){
				currScore = topFirstScore[i] + topSecondScore[j];
				board[topSecondMoves[j].getX()][topSecondMoves[j].getY()] = currTag;
				currScore -= minimax(board, oppoTag, turn+1, currDepth-1, logs, logNum);

				if(!bestRecorded || currScore > bestScore){ 
					bestScore = currScore;
					bestFirst.x = topFirstMoves[i].x;
					bestFirst.y = topFirstMoves[i].y;
					bestSecond.x = topSecondMoves[i].x;
					bestSecond.y = topSecondMoves[i].y;
					bestRecorded = true;
				}
				board[topSecondMoves[j].getX()][topSecondMoves[j].getY()] = EMPTY;
			}
			board[topFirstMoves[i].getX()][topFirstMoves[i].getY()] = EMPTY;
		}

		if (currDepth == 3){
			board[bestFirst.getX()][bestFirst.getY()] = currTag;
			board[bestSecond.getX()][bestSecond.getY()] = currTag;
			logs[logNum] = new cor(bestFirst.getX(), bestFirst.getY());
			logs[logNum+1] = new cor(bestSecond.getX(), bestSecond.getY());
			System.out.println("minimax:: " + bestFirst + " " + bestSecond + " " + bestScore);
		}
		return bestScore;
	}

	public static void printBoard(int[][] board) {
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
		for(int i = 0; i < 19; i++){
			for(int j = 0; j < 19; j++){
				for(int k = width-1; k > -1; k--){
					if(scoreBoard[i][j] > topScore[k]){
						if(k != 0) continue;
						else k--;
					}
					if(k < width-1){
						int targetScore = scoreBoard[i][j], targetX = i, targetY = j;
						int tempScore, tempX, tempY;
						for(k++; k < width; k++){
							tempScore = topScore[k];
							tempX = topMoves[k].getX();
							tempY = topMoves[k].getY();
							topScore[k] = targetScore;
							topMoves[k].x = targetX;
							topMoves[k].y = targetY;
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

	static void aiInput(int[][] board, int[][] scoreBoard, int aiTag, cor[] logs, int logNum) {
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
		logs[logNum] = new cor(high_i, high_j);

		board[high_i][high_j] = aiTag;

		if(PRINTINFO) System.out.println("Input aiTag x" + high_i + ", y" + high_j + ".");
		if(PRINTINFO) System.out.println();
	}
	
	/*
	 * read board left to right, top to bottom, left top to right bottom, left
	 * bottom to right top if there is a stone, hand direction, cor of the beginning
	 * of the row, copied data of the row, usertag to evaGIveScore() all direction
	 * start from (0,0) except left bottom to right top which starts from (18,0)
	 * (0,0) being left top corner; (18,0) being left bottom
	 */
	public static void 
	readBoWDir(int[][] rawData, int[][] scoreBoard, int userTag, int runNumber, int offOrDef, int turn) 
	{
		int k = 0;
		int[] oneRow = new int[19];

		for (int i = 0; i < 19; i++) { // left right
			for (int j = 0; j < 19; j++) {
				if (rawData[i][j] == userTag) {
					move.x = i;
					move.y = 0;
					for (j = 0; j < 19; j++) { oneRow[j] = rawData[i][j]; }
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, LEFTRIGHT, runNumber, offOrDef, turn);
					break;
				}
			}
		}

		for (int i = 0; i < 19; i++) { // top down
			for (int j = 0; j < 19; j++) {
				if (rawData[j][i] == userTag) {
					move.x = 0;
					move.y = i;
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
					move.x = i;
					move.y = 0;
					k = i;
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
					move.x = 0;
					move.y = i;
					k = 0;
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
					move.x = i;
					move.y = 0;
					k = i;
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
					move.x = 18;
					move.y = i;
					k = 18;
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
		int[] one_rawData = new int[onerawdatalen];
		int first_notusertag = -1, gab_notusertag = 0; // dummycell을 살펴볼 때 만약 notusertag가 있다면 위치를 기억하기 위한 수
		int oppoTag = opponentTag(userTag);
		int pos = 0, stoStart = -1, frontGap = 0, btwGap = 0, count1 = 0, count2 = 0, count2Start = -1, backGap = 0;
		boolean conti = false, blocked = false, backBlocked = false;

		if(PRINTINFO) {
			System.out.println("debug——— for : " + userTag);
			System.out.println("dir: " + direction + " | pos x: " + move.x + " | pos y: " + move.y);
			for (int i = 0; i < onerawdatalen; i++) {
				System.out.printf("%d ", dummycell[i]);
			}
			System.out.println();
		}

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

		if(PRINTINFO){
			System.out.print("dummies:");
			for (int k = 0; k < onerawdatalen; k++) {
				System.out.print(dummycell[k] + " ");
			}
			System.out.println();
		}
		
		int[] lastCaseInfoList = new int[10];
		int isConnected = 0;
	
		while (pos < onerawdatalen) {
			int temp;
			if ((temp = dummycell[pos]) == oppoTag) {
				if (conti) {
					if(isConnected!= 2) { //2개라면 건드리지 말아야함!
						isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
						lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
					}
					
					if(PRINTINFO) System.out.println("blocked directly " + frontGap + " " + count1 + " " + btwGap);
					blocked = true;
					giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked, backBlocked,
							runNumber, offOrDef, turn); // blocked
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
							
							if(PRINTINFO) System.out.println("end of line");
							blocked = true;
							break;
						} else if (dummycell[pos + j] == oppoTag) {
							
							if(PRINTINFO) System.out.println("blocked!");
							blocked = true;
							break;
						} else if (dummycell[pos + j] == userTag) {
							break;
						} else {
							btwGap++;
						}
					}
					if(PRINTINFO) System.out.println("btwgap==" + btwGap);
					if (btwGap == 4) {
						if(isConnected!= 2) { //2개라면 건드리지 말아야함!
							isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
							lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
						}
						
						if(PRINTINFO) System.out.println("case isolated " + frontGap + " " + count1);
						if(PRINTINFO) System.out.println("case isolated " + stoStart);
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked,
								backBlocked, runNumber, offOrDef, turn);
					} else if (blocked) {
						if(isConnected!= 2) { //2개라면 건드리지 말아야함!
							isConnected = 0; // connected가 아닌 것이 나올 때는 연속된 connected가 아니기에 0으로 리셋.
							lastCaseInfoList = new int[10]; //만약 isConnected가 0이되는 경우라면 array도 초기화를 해줘야한다.
						}
						
						if(PRINTINFO) System.out.println("case blocked stoStart " + stoStart);
						if(PRINTINFO) System.out.println("case blocked " + frontGap + " " + count1 + " " + btwGap);
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked, backBlocked, runNumber, offOrDef, turn);
					} else if (btwGap < 4) { // count2가 있다고 생각하는 case
						isConnected += 1; // 이 때 case가 connected가 발생하는 경우이다. 따라서 isConnected를 1올린다.
						pos += btwGap;
						count2Start = pos;
						while (pos < onerawdatalen && dummycell[pos] == userTag) {// i pointing at the space behind the
							count2++;
							pos++;
						}
						for (int i = 0; i < 2; i++) {
							if (pos + i >= onerawdatalen)
								break;
							else if (dummycell[pos + i] == 0)
								backGap++;
							else if (dummycell[pos + i] == oppoTag)
								backBlocked = true;
						}

						pos--;
						
						if(PRINTINFO) System.out.println( "case connected " + frontGap + " " + count1 + " " + btwGap + " " + count2 + " ");
						if(PRINTINFO) System.out.println("case connected stoStart " + stoStart);
						
						if(isConnected ==1) {  lastCaseInfoList[0] = stoStart; }
						lastCaseInfoList[(isConnected-1)*3+1] = count1;
						lastCaseInfoList[(isConnected-1)*3+2] = btwGap;
						lastCaseInfoList[(isConnected-1)*3+3] = count2;
						
						if(isConnected == 2) {
							int confirm_special_sum =0; //lastCaseInfoList에 있는 값들을 합쳐서 6이상이 되면, 필요한 값이다.
							for(int c_i = 1; c_i <7; c_i++) { confirm_special_sum +=lastCaseInfoList[c_i]; }
							confirm_special_sum -= lastCaseInfoList[3];
							
							if(confirm_special_sum > 5 && lastCaseInfoList[2] == 1 && lastCaseInfoList[5] == 1) {
								if(PRINTINFO) System.out.println("Last case : 잘 들어감!");
							}else {
								int temp_a, temp_b, temp_c, temp_d=0;
								temp_a = lastCaseInfoList[0] + lastCaseInfoList[1] + lastCaseInfoList[2]; 
								temp_b = lastCaseInfoList[4]; // count1_2
								temp_c = lastCaseInfoList[5]; //btwgap_2
								temp_d = lastCaseInfoList[6]; // count2_2
								lastCaseInfoList = new int[10]; // 이렇게 해도 되는지 모르겠지만 초기화 해준 것이다.
								lastCaseInfoList[0] = temp_a;
								lastCaseInfoList[1] = temp_b;
								lastCaseInfoList[2] = temp_c;
								lastCaseInfoList[3] = temp_d;
								if(PRINTINFO) System.out.println("여기 확인 하는거임!!!!!!!!!!!!!!!" +lastCaseInfoList[0] + lastCaseInfoList[1] + lastCaseInfoList[2] +lastCaseInfoList[3]);
								isConnected = 1;
							}
						}
						if(PRINTINFO){
							System.out.print("this is lastCaseInfo:  ");
							for(int i=0; i<lastCaseInfoList.length; i++) {
								System.out.print(lastCaseInfoList[i]+" ");
							}
							System.out.println();
						}
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked, backBlocked, runNumber, offOrDef, turn);
					} else
						System.out.println("bug2");
					if (count2 == 0) conti = false;
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
				if (stoStart == -1) stoStart = pos;
				conti = true;
				count1++;
			}
			pos++;
		} // while loop end

		if(PRINTINFO){
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
			int[][] scoresArray_forSpecial;
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
		if(PRINTINFO){
			System.out.print("this is one_rawdata:  ");
			for(int i=0; i<onerawdatalen; i++) {
				System.out.print(one_rawData[i]+" ");
			}
			System.out.println();
		}

		switch (direction) {
		case LEFTRIGHT: // left to right
			for (int i = 0; i < 19; i++) {
				scoreBoard[move.x][i] += one_rawData[i];// one_rawScore[i];
			}
			break;
		case TOPDOWN: // top down
			for (int i = 0; i < 19; i++) {
				scoreBoard[i][move.y] += one_rawData[i];// one_rawScore[i];
			}
			break;
		case TOPLBOTR: // TOP L TO BOT R
			for (int i = 0; i < onerawdatalen; i++) {
				scoreBoard[move.x][move.y] += one_rawData[i];// one_rawScore[i];
				move.x++;
				move.y++;
			}
			break;
		case BOTLTOPR: // BOT L TO TOP R
			for (int i = 0; i < onerawdatalen; i++) {
				scoreBoard[move.x][move.y] += one_rawData[i];// one_rawScore[i];
				move.x--;
				move.y++;
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
		if (userTag == 1)
			return 2;
		else
			return 1;
	}

	public static void giveScore(int[] one_rawData, int frontGap, int count1, int btwGap, int count2, int backGap,
			int pos, boolean blocked, boolean backBlocked, int runNumber, int offOrDef, int turn) {
		int[][] scoresArray;
		if (turn < 6)
			scoresArray = scoresArray1;
		else
			scoresArray = scoresArray2;
		int scoreArrayAdd = 0, temp = 0;
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
		if (DEBUG)
			System.out.println("--debug--givascore frontgap: " + scoreArrayAdd + " " + count1);
		for (int i = 0; i < frontGap; i++) {
			if(PRINTINFO) System.out.println(scoreArrayAdd +" " + count1 + " " +i);
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
		} else if (count2 == 0) {// case isolated
			int j = 0;
			if (DEBUG)
				System.out.println("--debug--givascore btwgap 1: " + scoreArrayAdd + " " + count1);
			for (int i = btwGap - 1; i >= 0; i--) {
				temp = scoresArray[scoreArrayAdd + count1 - 1][i];
				if (frontGap < 3)
					temp /= 4.5;
				one_rawData[pos + j] = temp;
				j++;
			}
			return;
		} else if (count2 != 0) {// if not isolated
			if (offOrDef == DEFENSE && btwGap <= 2 || runNumber == 1 && btwGap <= 2) { // run1 with gap 2, consider them
																						// as one, for
				// defense no need to differentiate
				// if (!backBlocked||count1+count2>=4) {
				count1 += count2;
				for (int i = 0; i < btwGap; i++) {
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
}

//막힌게 2칸 떨어져있으면 반대편 /4.5 3칸 떨어져있으면 그대
//앞에 들어가는 점수들은 돌들에만 의존하지 말고 뒤의 갭과 앞의 돌들을 같이 고려하면 훨씬 좋을
