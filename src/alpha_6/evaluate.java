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
		// ���⼭���ʹ� �����Է� ���� �ɷ� �Է��ؾ���.
		// int[][] checkscore = new int[19][19]; //board �����ϱ� ���� board.
		int high_i = 0, high_j = 0;
		int high_score = 0;
		/**
		 * board���� checkboard�� �����ϱ�
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
	 * start_index_x,y �����ϴ� ��ǥ direction �� userTag_confirm ������ ������ Ȯ�� onerawdatalen
	 * �� �Ѿ���� array�� ���� (�밢�� ������) one_rawData[] �� array ��
	 */

	public static void evaGiveScoreC1(int[][] scoreBoard, int dummycell[], int onerawdatalen, int userTag, cor move,
			int direction, int runNumber, int offOrDef, int turn) {
		// int[] dummycell = new int[onerawdatalen]; // ���� ����� ���� ���� ������ cell �� ���� ����
		// ���.
		int[] one_rawData = new int[onerawdatalen];
		int first_notusertag = -1, gab_notusertag = 0; // dummycell�� ���캼 �� ���� notusertag�� �ִٸ� ��ġ�� ����ϱ� ���� ��
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
		 * for (int i = 0; i < onerawdatalen; i++) { // �޾ƿ� �����͸� dummycell �� �ű��.
		 * dummycell[i] = one_rawData[i]; }
		 */
		for (int i = 0; i < onerawdatalen; i++) {
			if (dummycell[i] == 3)
				dummycell[i] = oppoTag;
			if (dummycell[i] == oppoTag) { // ���� �� cell �� notusertag ���.
				if (first_notusertag == -1) { // ó�� notusertag�� ���Դٸ�.
					gab_notusertag = i; // gab�� ó�� ���� �� index ��ŭ�̴�.
					first_notusertag = 0; // ���� notusertag�� �� �� ���Ա� ������ 0���� �ٲ��ش�.
				} else {
					gab_notusertag = i - first_notusertag - 1; // �ι�° notusertag�� ���Դٸ� �� index�� ���̿� -1 �̴�.
				}
				if (gab_notusertag < 6) { // ���� ���̰� 5�����̸� ������ ���� ����, ���� ���� ���� �ִٰ� �����Ѵ�.
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

		// ���࿡ �Ʊ� ���� ���̿� ��ֹ��� ���ٸ� ���۰� ���� �����ͼ� 1-1-11�� ���̽��� Ȯ���� �� �ֵ���

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
				if (conti) {// �տ� ���� �� �����ٰ� �� ���� ���� �ڿ� ���¿� ���� ������ ������.
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
						// ���⼭�� iso��� ������ ������ ���
					} else if (blocked) {
						if (DEBUG)
							System.out.println("case blocked " + frontGap + " " + count1 + " " + btwGap);
						giveScore(one_rawData, frontGap, count1, btwGap, count2, backGap, stoStart, blocked,
								backBlocked, runNumber, offOrDef, turn);
						// blocked ������ btwGap�� �ڿ� �����̶�� �ѱ�� ���
					} else if (btwGap < 4) { // count2�� �ִٰ� �����ϴ� case
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
						// ���⼭ frontgap count1 btwgap count2�� ������ ó���ϰ� btwgap�� frontgap����, count2��
						// count1�� ������ �װ� ���� ������ info�� �ȴ�.
						// �׸��� �׷��� �ؼ� frontgap�� ������ �������� �ϴµ� ������ �ȳְ� �佺�ϸ� �ȴ�. �װ� �����θ����ִ� �� �ƴϱ⶧���� ������ ¥��
						// �� �ʿ䰡 ����.
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

//������ 2ĭ ������������ �ݴ��� /4.5 3ĭ ������������ �״�
//�տ� ���� �������� ���鿡�� �������� ���� ���� ���� ���� ������ ���� ����ϸ� �ξ� ����