package alpha_6;

public class evaluate { //
	// board scoreForBlack, scoreForwhite;
	// static int [][]totalScore= new int[19][19];
	static cor move = new cor();
	final static int LEFTRIGHT = 0, TOPDOWN = 1, TOPLBOTR = 2, BOTLTOPR = 3;

	public static void aiTurn(int[][] board, int aiTag) {
		System.out.println("----aiturn with tag: " + aiTag);
		int oppoTag = opponentTag(aiTag);
		int[][] scoreBoard = new int[19][19];
		readBoWDir(board, scoreBoard, aiTag);
		readBoWDir(board, scoreBoard, oppoTag);
		// find location with highest point
		// enter input
	}
	/*
	 * read board left to right, top to bottom, left top to right bottom, left
	 * bottom to right top if there is a stone, hand direction, cor of the beginning
	 * of the row, copied data of the row, usertag to evaGIveScore() all direction
	 * start from (0,0) except left bottom to right top which starts from (18,0)
	 * (0,0) being left top corner; (18,0) being left bottom
	 */

	public static void readBoWDir(int[][] rawData, int[][] scoreBoard, int userTag) {
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
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, LEFTRIGHT);
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
					evaGiveScoreC1(scoreBoard, oneRow, 19, userTag, move, TOPDOWN);
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
					evaGiveScoreC1(scoreBoard, oneRow, 19 - i, userTag, move, TOPLBOTR);
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
					evaGiveScoreC1(scoreBoard, oneRow, k, userTag, move, TOPLBOTR);
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
					evaGiveScoreC1(scoreBoard, oneRow, i + 1, userTag, move, BOTLTOPR);
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
					evaGiveScoreC1(scoreBoard, oneRow, 18 - i, userTag, move, BOTLTOPR);
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
	 * start_index_x,y 시작하는 좌표 direction 방 userTag_confirm 흑인지 백인지 확인 onerawdatalen 는
	 * 넘어오는 array의 길이 (대각선 때문에) one_rawData[] 는 array 값
	 */

	public static void evaGiveScoreC1(int[][] scoreBoard, int one_rawData[], int onerawdatalen, int userTag, cor move,
			int direction) {
		int[] dummycell = new int[19]; // 만약 상대편 돌에 의해 쓰레기 cell 이 나올 때를 대비.
		int first_notusertag = -1; // dummycell을 살펴볼 때 만약 notusertag가 있다면 위치를 기억하기 위한 수
		int gab_notusertag = 0;
		int oppoTag = opponentTag(userTag);

		System.out.println("debug------- for : " + userTag);
		System.out.println("dir: " + direction + " | pos x: " + move.x + " | pos y: " + move.y);
		for (int i = 0; i < onerawdatalen; i++) {
			System.out.printf("%d ", one_rawData[i]);
		}
		System.out.println();
		
		/*
		 * convert dead area to oppotag
		 */
		for (int i = 0; i < onerawdatalen; i++) { // 받아온 데이터를 dummycell 로 옮긴다.
			dummycell[i] = one_rawData[i];
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

		}
		/*
		 * segment usertag
		 */
		int pos = 0, stoStart = -1, frontGap = 0, btwGap = 0, count1 = 0, count2 = 0;
		boolean conti = false, blocked = false;

		System.out.print("dummies:");
		for (int k = 0; k < onerawdatalen; k++) {
			System.out.print(dummycell[k] + " ");
		}
		System.out.println();
		while (pos < onerawdatalen) {
			int temp;
			if ((temp = dummycell[pos]) == oppoTag) {
				if (conti) {
					System.out.println("blocked directly " + frontGap + " " + count1 + " " + btwGap);
					blocked = true;
					giveScore(); // blocked btwgap count1 frontgap

					conti = false;
					blocked = false;
					frontGap = 0;
					count1 = 0;
				}
				frontGap = 0;
			} else if (temp == 0) {
				if (conti) {// 앞에 돌이 잘 나오다가 빈 공간 따라서 뒤에 상태에 따라서 점수를 줘야헌다.
					btwGap++;
					for (int j = 1; j < 4; j++) {
						if (pos + j >= onerawdatalen) {
							System.out.println("end of line");
							break;
						} else if (dummycell[pos + j] == oppoTag) {
							System.out.println("blocked!");
							blocked = true;
							break;
						} else if (dummycell[pos + j] == userTag)
							break;
						btwGap++;
					}
					System.out.println("btwgap==" + btwGap);
					if (btwGap == 4) {
						System.out.println("case isolated " + frontGap + " " + count1);
						giveScore();
						// 여기서도 iso라고 밝히고 점수를 줘야
					} else if (blocked) {
						System.out.println("case blocked " + frontGap + " " + count1 + " " + btwGap);
						giveScore();
						// blocked 밝히고 btwGap이 뒤에 공간이라고 넘기고 계산
					} else if (btwGap < 4) {
						// frontGap count1 gap count2 --> score
						pos += btwGap;
						while (dummycell[pos] == userTag) {// i pointing at the space behind the stone after the loop,
															// may be 0 or oppo or 3
							count2++;
							pos++;
						}
						System.out.println(
								"case connected " + frontGap + " " + count1 + " " + btwGap + " " + count2 + " ");
						giveScore();
						// 여기서 frontgap count1 btwgap count2로 점수를 처리하고 btwgap을 frontgap으로, count2을
						// count1로 돌리면 그게 다음 돌들의 info가 된다.
						// 그리고 그렇게 해서 frontgap에 점수를 넣을려고 하는데 있으면 안넣고 페스하면 된다. 그게 벽으로막혀있는 게 아니기때문에 점수를 짜게
						// 줄 필요가 없다.
					} else
						System.out.println("bug2");
					// check if there is stone within 3 blocks, no then isolated, yes then count
					// them too
					btwGap = 0;
					conti = false;
					blocked = false;
					frontGap = 0;
					count1 = count2;
					count2 = 0;
				}
				frontGap++;
			} else if (temp == userTag) {
				if (stoStart == -1)
					stoStart = pos;
				conti = true;
				count1++;
			}
			pos++;
		} // while loop end

		/*
		 * int[] one_rawScore = new int[19]; // 점수 저장할 array int count_usertag = 0; for
		 * (int i = 0; i < arraylength - 5; i++) { // 6개의 범위 안에 usertag가 얼마나 있는지 확인 for
		 * (int j = i; j < i + 6; j++) { if (dummycell[j] == userTag) count_usertag++; }
		 * // 만약 그 범위에 0, 즉 빈 칸이 있다면 count_usertag 만큼 더해준다. 그게 점수다 for (int j = i; j < i
		 * + 6; j++) { if (dummycell[j] == 0) one_rawScore[j] += count_usertag; }
		 * count_usertag = 0; }
		 */
		// 여기 까지가 점수 매기기

		// 방향 순서 ->, 아래 , 오른쪽 아래, 왼쪽 아래
		// int x = start_index_x;
		// int y = start_index_y;
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
		}

		System.out.println("||x\\y\t0 1 2 3 4 5 6 7 8 9 a 1 2 3 4 5 6 7 8");
		for (int i = 0; i < 19; i++) {
			System.out.printf("||%d\t", i);
			for (int j = 0; j < 19; j++) {
				System.out.printf("%d ", scoreBoard[i][j]);
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

	public static void diagnosis(int[] dummycell, int onerawdatalen, int userTag) {
		int i = 0, stoStart = -1, frontGap = 0, btwGap = 0, count1 = 0, count2 = 0;
		boolean conti = false, blocked = false;
		int oppoTag = opponentTag(userTag);
		System.out.print("dummies:");
		for (int k = 0; k < onerawdatalen; k++) {
			System.out.print(dummycell[k] + " ");
		}
		System.out.println();
		while (i < onerawdatalen) {
			int temp;
			if ((temp = dummycell[i]) == oppoTag) {
				if (conti) {
					System.out.println("blocked directly " + frontGap + " " + count1 + " " + btwGap);
					blocked = true;
					giveScore(); // blocked btwgap count1 frontgap

					conti = false;
					blocked = false;
					frontGap = 0;
					count1 = 0;
				}
				frontGap = 0;
			} else if (temp == 0) {
				if (conti) {// 앞에 돌이 잘 나오다가 빈 공간 따라서 뒤에 상태에 따라서 점수를 줘야헌다.
					btwGap++;
					for (int j = 1; j < 4; j++) {
						if (i + j >= onerawdatalen) {
							System.out.println("end of line");
							break;
						} else if (dummycell[i + j] == oppoTag) {
							System.out.println("blocked!");
							blocked = true;
							break;
						} else if (dummycell[i + j] == userTag)
							break;
						btwGap++;
					}
					System.out.println("btwgap==" + btwGap);
					if (btwGap == 4) {
						System.out.println("case isolated " + frontGap + " " + count1);
						giveScore();
						// 여기서도 iso라고 밝히고 점수를 줘야
					} else if (blocked) {
						System.out.println("case blocked " + frontGap + " " + count1 + " " + btwGap);
						giveScore();
						// blocked 밝히고 btwGap이 뒤에 공간이라고 넘기고 계산
					} else if (btwGap < 4) {
						// frontGap count1 gap count2 --> score
						i += btwGap;
						while (dummycell[i] == userTag) {// i pointing at the space behind the stone after the loop,
															// may be 0 or oppo or 3
							count2++;
							i++;
						}
						System.out.println(
								"case connected " + frontGap + " " + count1 + " " + btwGap + " " + count2 + " ");
						giveScore();
						// 여기서 frontgap count1 btwgap count2로 점수를 처리하고 btwgap을 frontgap으로, count2을
						// count1로 돌리면 그게 다음 돌들의 info가 된다.
						// 그리고 그렇게 해서 frontgap에 점수를 넣을려고 하는데 있으면 안넣고 페스하면 된다. 그게 벽으로막혀있는 게 아니기때문에 점수를 짜게
						// 줄 필요가 없다.
					} else
						System.out.println("bug2");
					// check if there is stone within 3 blocks, no then isolated, yes then count
					// them too
					btwGap = 0;
					conti = false;
					blocked = false;
					frontGap = 0;
					count1 = count2;
					count2 = 0;
				}
				frontGap++;
			} else if (temp == userTag) {
				if (stoStart == -1)
					stoStart = i;
				conti = true;
				count1++;
			}
			i++;
		} // while loop end
	}

	public static void giveScore() {

	}
}