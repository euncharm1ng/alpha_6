package alpha_6;



public class evaluate { //
	// board scoreForBlack, scoreForwhite;
	// static int [][]totalScore= new int[19][19];
	static cor move = new cor();
	final static int LEFTRIGHT = 0, TOPDOWN = 1, TOPLBOTR = 2, BOTLTOPR = 3, OFFENSE = 1, DEFENSE = 2;
	static boolean DEBUG = false;
	final static int[][] scoresArray1 = { { 13, 24, 44, 80 }, { 6, 97, 176, 320 }, { 1549, 6195, 11264, 20480 },
			{ 1024000, 4096000, 16384000, 65536000 }, { 1024000, 4096000, 16384000, 65536000 }, { 3, 6, 11, 20 },
			{ 97, 387, 704, 1280 }, { 24, 1549, 2816, 5120 }, { 209600, 838400, 3353600, 13414400 },
			{ 209600, 838400, 3353600, 13414400 }, { 53, 97, 176, 320 }, { 97, 387, 704, 1280 },
			{ 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 167772160, 335544320, 671088640, 1342177000 },
			{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 },
			{ 1310720, 2621440, 5242880, 10485760 }, { 1310720, 2621440, 5242880, 10485760 } };
	final static int[][] scoresArray2 = { { 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 },
			{ 1024000, 4096000, 16384000, 65536000 }, { 1024000, 4096000, 16384000, 65536000 }, { 3, 6, 11, 20 },
			{ 6, 97, 176, 320 }, { 97, 387, 704, 1280 }, { 209600, 838400, 3353600, 13414400 },
			{ 209600, 838400, 3353600, 13414400 }, { 53, 97, 176, 320 }, { 97, 387, 704, 1280 },
			{ 387, 24781, 45056, 81920 }, { 3, 6, 11, 20 }, { 167772160, 335544320, 671088640, 1342177000 },
			{ 13, 24, 44, 80 }, { 24, 1549, 2816, 5120 }, { 1549, 6195, 11264, 20480 },
			{ 1310720, 2621440, 5242880, 10485760 }, { 1310720, 2621440, 5242880, 10485760 } };

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

	
	public static int aiTurn(int[][] board, int aiTag, int turn, cor[] logs, int logNum) {
		System.out.println("----aiturn with tag: " + aiTag);
		int oppoTag = opponentTag(aiTag);
		int[][] scoreBoard = new int[19][19];
		readBoWDir(board, scoreBoard, aiTag, 1, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 1, DEFENSE, turn);
		// find location with highest point
		// enter input
		if (DEBUG)
			printDebug(scoreBoard);
		logNum = aiInput(board, scoreBoard, aiTag, logs, logNum);
		initializeScoreBoard(scoreBoard);

		if (DEBUG)
			System.out.println("-----------------------------------move2------------------------------------------");
		readBoWDir(board, scoreBoard, aiTag, 2, OFFENSE, turn);
		readBoWDir(board, scoreBoard, oppoTag, 2, DEFENSE, turn);
		// find location with highest point
		// enter input
		if (DEBUG)
			printDebug(scoreBoard);
		logNum = aiInput(board, scoreBoard, aiTag, logs, logNum);
		return logNum;
	}

	static void initializeScoreBoard(int[][] scoreBoard) {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				scoreBoard[i][j] = 0;
			}
		}
	}

	static int aiInput(int[][] board, int[][] scoreBoard, int aiTag, cor[] logs, int logNum) {
		// 여기서부터는 점수입력 받은 걸로 입력해야함.
		// int[][] checkscore = new int[19][19]; //board 복사하기 위한 board.
		int high_i = 0, high_j = 0;
		int high_score = 0;
		/**
		 * board에서 checkboard로 복사하기
		 */
		/*
		 * for(int i = 0; i< 19; i++) { for(int j = 0; j< 19; j++) { checkscore[i][j] =
		 * scoreBoard[i][j]; } }
		 */

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
		logNum++;

		board[high_i][high_j] = aiTag;

		System.out.println("Input aiTag x" + high_i + ", y" + high_j + ".");
		System.out.println();
		return logNum;
	}
	/*
	 * read board left to right, top to bottom, left top to right bottom, left
	 * bottom to right top if there is a stone, hand direction, cor of the beginning
	 * of the row, copied data of the row, usertag to evaGIveScore() all direction
	 * start from (0,0) except left bottom to right top which starts from (18,0)
	 * (0,0) being left top corner; (18,0) being left bottom
	 */

	public static void readBoWDir(int[][] rawData, int[][] scoreBoard, int userTag, int runNumber, int offOrDef,
			int turn) {
		int k = 0;
		int[] oneRow = new int[19];

		for (int i = 0; i < 19; i++) { // left right
			for (int j = 0; j < 19; j++) {
				// if(rawData[j][i]!=0&&rawData[j][i]!=3) {
				if (rawData[i][j] == userTag) {
					move.x = i;
					move.y = 0;
					for (j = 0; j < 19; j++) {
						oneRow[j] = rawData[i][j];
					}
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, LEFTRIGHT, runNumber, offOrDef, turn);
					break;
				}
			}
		}

		for (int i = 0; i < 19; i++) { // top down
			for (int j = 0; j < 19; j++) {
				// if(rawData[i][j]!=0&&rawData[i][j]!=3) {
				if (rawData[j][i] == userTag) {
					move.x = 0;
					move.y = i;
					for (j = 0; j < 19; j++) {
						oneRow[j] = rawData[j][i];
					}
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, TOPDOWN, runNumber, offOrDef, turn);
					break;
				}
			}
		}

		for (int i = 0; i < 14; i++) {// top left to right bottom, top left to top right
			k = i;
			for (int j = 0; j < 19 - i; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
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
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
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
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
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
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
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
					evaGiveScoreC1(scoreBoard, oneRow, 18 - i, userTag, move, BOTLTOPR, runNumber, offOrDef, turn);
					break;
				}
				k--;
			}
		}

		return;
	}
	/*
	 * evaluate and give score to new score board according to input C 1is for first
	 * move, C2 is for second move, the score for both cases are different.
	 * 
	 */
	/*
	 * start_index_x,y 시작하는 좌표 direction 방 userTag_confirm 흑인지 백인지 확인 onerawdatalen
	 * 는 넘어오는 array의 길이 (대각선 때문에) one_rawData[] 는 array 값
	 */

	public static void evaGiveScoreC1(int[][] scoreBoard, int dummycell[], int onerawdatalen, int userTag, cor move,
			int direction, int runNumber, int offOrDef, int turn) {
		// int[] dummycell = new int[onerawdatalen]; // 만약 상대편 돌에 의해 쓰레기 cell 이 나올 때를
		// 대비.
		int[] one_rawData = new int[onerawdatalen];
		int first_notusertag = -1, gab_notusertag = 0; // dummycell을 살펴볼 때 만약 notusertag가 있다면 위치를 기억하기 위한 수
		int oppoTag = opponentTag(userTag);
		int pos = 0, stoStart = -1, frontGap = 0, btwGap = 0, count1 = 0, count2 = 0, count2Start = -1, backGap = 0;
		boolean conti = false, blocked = false, backBlocked = false;

		if (DEBUG) {
			System.out.println("debug------- for : " + userTag);
			System.out.println("dir: " + direction + " | pos x: " + move.x + " | pos y: " + move.y);
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

		// 만약에 아군 돌들 사이에 장애물이 없다면 시작과 끝은 데려와서 1-1-11의 케이스를 확인할 수 있도록

		while (pos < onerawdatalen) {
			int temp;
			if ((temp = dummycell[pos]) == oppoTag) {
				if (conti) {
					if (DEBUG)
						System.out.println("blocked directly " + frontGap + " " + count1 + " " + btwGap);
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
							if (DEBUG)
								System.out.println("end of line");
							blocked = true;
							break;
						} else if (dummycell[pos + j] == oppoTag) {
							if (DEBUG)
								System.out.println("blocked!");
							blocked = true;
							break;
						} else if (dummycell[pos + j] == userTag) {
							break;
						} else {
							btwGap++;
						}
					}
					if (DEBUG)
						System.out.println("btwgap==" + btwGap);
					if (btwGap == 4) {
						if (DEBUG)
							System.out.println("case isolated " + frontGap + " " + count1);
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked,
								backBlocked, runNumber, offOrDef, turn);
						// 여기서도 iso라고 밝히고 점수를 줘야
					} else if (blocked) {
						if (DEBUG)
							System.out.println("case blocked " + frontGap + " " + count1 + " " + btwGap);
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked,
								backBlocked, runNumber, offOrDef, turn);
						// blocked 밝히고 btwGap이 뒤에 공간이라고 넘기고 계산
					} else if (btwGap < 4) { // count2가 있다고 생각하는 case
						// frontGap count1 gap count2 --> score
						pos += btwGap;
						count2Start = pos;
						while (pos < onerawdatalen && dummycell[pos] == userTag) {// i pointing at the space behind the
																					// stone after the loop,
							// may be 0 or oppo or 3
							count2++;
							pos++;
						}
						for (int i = 0; i < 2; i++) {
							if (pos + i > onerawdatalen)
								break;
							else if (dummycell[pos + i] == 0)
								backGap++;
							else if (dummycell[pos + i] == oppoTag)
								backBlocked = true;
						}

						pos--;
						if (DEBUG)
							System.out.println(
									"case connected " + frontGap + " " + count1 + " " + btwGap + " " + count2 + " ");
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked,
								backBlocked, runNumber, offOrDef, turn);
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
		if (DEBUG) {
			System.out.print("this is one_rawdata:  ");
			for (int i = 0; i < onerawdatalen; i++) {
				System.out.print(one_rawData[i] + " ");
			}
			System.out.println();
		}
		switch (direction)

		{
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