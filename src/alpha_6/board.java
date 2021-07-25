package alpha_6;

import java.util.Scanner;

public class board {
	int [][]board;
	int userTag, aiTag;
	int turn, logNum;
	int numOfRed;
	Scanner n;
	boolean winchecker;
	cor[] logs;

	/*
	 * initializes the variables
	 */
	public board() {
		this.board=new int[19][19];
		initializeBoard();
		userTag=2;
		winchecker=false;
		n=new Scanner(System.in);
		logs=new cor[361];
		logNum=0;
	}
	public void setAiTag(int aiTag) {
		this.aiTag=aiTag;
	}
	public void setNumOfRed(int numOfRed) {
		this.numOfRed=numOfRed;
	}
	
	public void increaseTurn() {
		turn++;
	}
	
	//initialize the board and input 0 to all place
	private void initializeBoard(){ 
		System.out.println("initializing...");
		for(int i =0; i< 19; i++) {
			for(int j=0; j<19; j++) {
				this.board[i][j]=0;
			}
		}
	}

	//return false for invalid, return true for valid input, for 2 coordinates
	public boolean isValidInput(cor move1, cor move2) { 
		int x1=move1.getX();
		int x2=move2.getX();
		int y1=move1.getY();
		int y2=move2.getY();
		if(x1<0||y1<0||x2<0||y2<0) return false;
		else if(x1>18||y1>18||x2>18||y2>18) return false;
		else if(this.board[x1][y1]!=0) return false;
		else if(this.board[x2][y2]!=0) return false;
		else return true;
	}
	
	//return false for invalid, return true for valid input, for 1 coordinate
	public boolean isValidInput(cor move1) { 
		int x1=move1.getX();
		int y1=move1.getY();
		if(x1<0||y1<0) return false;
		else if(x1>18||y1>18) return false;
		else if(this.board[x1][y1]!=0) return false;
		else return true;
	}
	
	// return false for not full, true for full board
	public boolean isFull() { 
		for(int i =0; i< 19; i++) {
			for(int j=0; j<19; j++) {
				if(this.board[i][j]==0) return false;
			}
		}
		return true;
	}
	
	/*
	 * prints the board, o for black, X for white
	 */
	public void printBoard() {
		System.out.println("x\\y\t0 1 2 3 4 5 6 7 8 9 a 1 2 3 4 5 6 7 8");
		for(int i =0; i< 19; i++) {
			System.out.printf("%2d\t", i);
			for(int j=0; j<19; j++) {
				if(this.board[i][j] == 0 )
					System.out.printf(" |");
				if(this.board[i][j] == 1)
					System.out.print("O|");
				if(this.board[i][j] == 2)
					System.out.print("X|");
				if(this.board[i][j] == 3)
					System.out.print("N|");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/* 
	 * enter the 2 coordinates to the board
	 */
	public void enterInput(cor move1, cor move2) {
		this.board[move1.getX()][move1.getY()]=this.userTag;
		this.board[move2.getX()][move2.getY()]=this.userTag;
		winchecker = checkWinCondition();
		toggleUserTag();		
	}
	
	/*
	 * enter one coordinate to the board.
	 */
	public void enterInput(cor move1, int inputUserTag) {
		this.board[move1.getX()][move1.getY()]=inputUserTag;
	}
	
	//userTag only switches between 1 and 2
	public void toggleUserTag() { 
		if(this.userTag==1) this.userTag=2;
		else if(this.userTag==2) this.userTag=1;
	}
	
	/* 
	 * receive coordinate from user or the ai
	 */
	public void getInput(int loc1, int loc2) {
		if(isFull()) {
			System.out.println("The board is full");
			return;
		}
		
		
		int x1=0, x2=0, y1=0, y2=0;
		cor move1, move2;
		
		move1=new cor(loc1/19, loc1%19);
		move2=new cor(loc2/19, loc2%19);
		enterInput(move1, move2);
		log(move1);
		log(move2);

		/*
		boolean checker=false;
		char menu;
		
		do {
			System.out.print("press z to cancel previous move, i to input: ");	
			menu=this.n.next().charAt(0);
			this.n.nextLine();
			if(menu=='i'||menu=='I') {
				do { //checker for invalid input
					if(checker) System.out.println("wrong input! plz retry!");
					System.out.println("input syntax: x1 y1 x2 y2!! 0~18:");
					x1=this.n.nextInt();
					y1=this.n.nextInt();
					x2=this.n.nextInt();
					y2=this.n.nextInt();
					this.n.nextLine();
					move1=new cor(x1, y1);
					move2=new cor(x2, y2);
				}while(checker=(!isValidInput(move1, move2)));	
				enterInput(move1, move2);
				log(move1);
				log(move2);
				System.out.println(logs[logNum-1].getX() + " "+ logs[logNum-1].getY());
				break;
			}else if(menu=='z'||menu=='Z') {
				deleteMove(4);
				printBoard();
			}
			else
				System.out.print("wrong input plz retry: ");
		}while(true);
		*/
	}
	
	/*
	 * undo the move
	 */
	public void deleteMove(int num) {
		for(int i =0; i<num; i++) {
			logNum--;
			//cor tempMove=logs[logNum];
			this.board[logs[logNum].getX()][logs[logNum].getY()]=0;
			
		}
	}
	
	//return true for no winner, false for a winner
	public boolean checkWinCondition() {
		int k = 0;
		int count=0; // 만약 count가 6이 되면 육목이 완성됐다고 판정하고 return true;
		int[][] checkboard = new int[19][19]; //board 복사하기 위한 board.
		
		/**
		 * board에서 checkboard로 복사하
		 */
		for(int i = 0; i< 19; i++) {
			for(int j = 0; j< 19; j++) {
				checkboard[i][j] = board[i][j];
			}
		}
		
		// left right
		Loop_lr:
		for (int i = 0; i < 19; i++) { 
			for (int j = 0; j < 19; j++) {
				if (checkboard[i][j] == userTag) {
					count++;
				}
				else {
					count=0;
				}
				if(count == 6)
					break Loop_lr;
			}
			count =0;
		}
		if(count == 6)
			return true;
		
		// top down
		Loop_td:
		for (int i = 0; i < 19; i++) { 
			for (int j = 0; j < 19; j++) {
				if (checkboard[i][j] == userTag) {
					count++;
				}
				else {
					count=0;
				}
				if(count == 6)
					break Loop_td;
			}
			count = 0;
		}
		if(count == 6)
			return true;
		
		// top left to right bottom, left top to left down
		Loop_tlrb_1:
		for (int i = 0; i < 14; i++) {
			k=i;
			for (int j = 0; j < 19 - i; j++) {
				if(checkboard[k][j] == userTag) {
					count++;
				}
				else {
					count=0;
				}
				if(count == 6)
					break Loop_tlrb_1;
				k++;
			}
			count = 0;
		}
		if(count == 6)
			return true;
		
		// top left to right bottom, top left to top right
		Loop_tlrb_2:
		for (int i = 1; i < 14; i++) {
			k = 0;
			for (int j = i; j < 19; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
				if (checkboard[k][j] == userTag) {
					count++;
				}
				else {
					count=0;
				}
				if(count == 6)
					break Loop_tlrb_2;
				k++;
			}
			count = 0;
		}
		if(count == 6)
			return true;
		
		// top right to left bottom, top right to top left
		Loop_trlb_1:
		for (int i = 18; i > 4; i--) {
			k = i;
			for (int j = 0; j < i; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
				if (checkboard[k][j] == userTag) {
					count++;
				}
				else {
					count=0;
				}
				if(count == 6)
					break Loop_trlb_1;
				k--;
			}
			count = 0;
		}
		if(count == 6)
			return true;
		
		// top right to left bottom, right top to right bottom
		Loop_trlb_2:
		for (int i = 1; i < 14; i++) {
			k = 18;
			for (int j = i; j < 18; j++) {
				// if(rawData[k][j]!=0&&rawData[k][j]!=3) {
				if (checkboard[k][j] == userTag) {
					count++;
				}
				else {
					count=0;
				}
				if(count == 6)
					break Loop_trlb_2;
				k--;
			}
			count = 0;
		}
		if(count == 6)
			return true;
		
		return false;
	}
	
	/*
	 * the first function called when the game starts
	 * sets user tag and ai tag, 
	 * receives the number of red stones or the obstacles
	 */
	public void setGame() {
		
		
		do {
			System.out.print("plz input the color of ai's turn(1 for black, 2for white): ");
			aiTag=this.n.nextInt();
			this.n.nextLine();
		}while(aiTag!=1&&aiTag!=2);
			
			
		boolean invalidInput;
		do {
			cor center=new cor(9, 9);
			enterInput(center, 1);
			log(center);
			System.out.print("plz input the number of the red stone: ");
			numOfRed=this.n.nextInt();
			this.n.nextLine();
			if(numOfRed==0) return;
			
			System.out.print("plz input the cordinates (syntax: x1 y1 x2 y2...): ");
			invalidInput=false;
			cor []moves=new cor[numOfRed];
			int x=0, y=0;
			for(int i=0; i<numOfRed; i++) {
				x=this.n.nextInt();
				y=this.n.nextInt();
				moves[i]=new cor(x, y);
				if(!isValidInput(moves[i])) {
					System.out.println("error!");
					initializeBoard();
					invalidInput=true;
					break;
				}
				enterInput(moves[i], 3);
			}
			this.n.nextLine();
		}while(invalidInput);
	}
	
	/*
	 * memorizes the moves, to undo the moves
	 */
	public void log(cor move1) {
		logs[logNum]=new cor(move1.getX(), move1.getY());
		logNum++;
	}
	public void aiTurnFunc() {
		evaluate.aiTurn(this.board, this.aiTag,this.turn, this.logs, this.logNum);
		printBoard();
		toggleUserTag();
		increaseTurn();
		logNum+=2;
	}
	
	public cor[] placeAiStone() {
		cor[] cor4Return=new cor[2];
		cor4Return[0]=logs[logNum-1];
		cor4Return[1]=logs[logNum-2];
		return cor4Return;
	}

	/*
	 * the starting function
	 */
	public void runGame() {
		
		setGame();
		printBoard();
		do {
			 if(userTag==aiTag) {
				aiTurnFunc();
			}
			//printBoard();
			if(winchecker)  //////// 이거!!!
				break;
			//getInput();
			increaseTurn();
			printBoard();
		}while(!winchecker);
		System.out.println(userTag + " Defeat.....!");
		n.close();
	}
	
}//end of class

