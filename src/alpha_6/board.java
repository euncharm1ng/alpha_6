package alpha_6;

import java.util.Scanner;

public class board {
	final static private int EMPTY = 0;
	final static private int BLACK = 1;
	final static private int WHITE = 2;
	final static private int RED = 3;
	
	int [][]board;
	int awayTag, aiTag;
	int turn, logNum;
	Scanner n;
	boolean winchecker;
	cor[] logs;
	ConnectSix conSix;

	public board() {
		this.board=new int[19][19];
		initializeBoard();
		winchecker=false;
		n=new Scanner(System.in);
		logs=new cor[361];
		logNum=0;
	}
	
	private void increaseTurn() {
		turn++;
		logNum += 2;
	}
	
	//initialize the board and input 0 to all place
	private void initializeBoard(){ 
		System.out.println("initializing...");
		for(int i =0; i< 19; i++) {
			for(int j=0; j<19; j++) {
				this.board[i][j] = EMPTY;
			}
		}
	}

	//return false for invalid, return true for valid input
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
	
	//return false for invalid, return true for valid input
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
	

	public void enterInput(String msg) {
		String[] redStones = msg.split(":");
		for(String stone : redStones) {
			enterInput(parse(stone), awayTag);
		}
	}
	
	public void enterInput(cor move1, int tag_p) {
		this.board[move1.getX()][move1.getY()]=tag_p;
	}

	/*
	public void getInput() {
		if(isFull()) {
			System.out.println("The board is full");
			return;
		}
		
		int x1=0, x2=0, y1=0, y2=0;
		cor move1, move2;
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
	}
	*/
	
	public void deleteMove(int num) {
		for(int i =0; i<num; i++) {
			logNum--;
			this.board[logs[logNum].getX()][logs[logNum].getY()]=0;
		}
	}
	

	
	public ConnectSix setGame() throws ConnSixException {
		System.out.print("Input the ip address > ");
		String ip =n.nextLine();
		System.out.print("Input the port number > ");
		int port = Integer.parseInt(n.nextLine());
		System.out.print("Input the color > ");
		String color = n.nextLine();
		if (color.toLowerCase().compareTo("black") == 0) {
			aiTag = BLACK; awayTag = WHITE;
		}
		else {
			aiTag = WHITE; awayTag = BLACK;
		}
		
		ConnectSix conSix = new ConnectSix(ip, port, color);
		System.out.println("Red Stone positions are " + conSix.redStones);
		if(conSix.redStones != null) {
			String[] redStones = conSix.redStones.split(":");
			for(String stone : redStones) {
				enterInput(parse(stone), RED);
			}
		}
		return conSix;
	}
	
	public cor parse(String move) {
		System.out.println("in parse " + move);
		int alphabet = move.charAt(0);
		int tenth = move.charAt(1);
		int ones = move.charAt(2);
		System.out.println(alphabet + " " + tenth + " " + ones);
		int y = alphabet - 'A';
		int x = 19 - ((tenth - '0') * 10 + (ones - '0'));
		if (y > 7) y--;
		System.out.println(y + " " + x);
		return new cor(x, y);
	}
	
	public String makeNotation(cor move1, cor move2) {
		System.out.println("make notation " + move1);
		System.out.println("make natation " + move2);
		char alpha1 = (char) (move1.getY() + 'A');
		if (alpha1 >= 'I') alpha1++;
		char alpha2 = (char) (move2.getY() + 'A');
		if (alpha2 >= 'I') alpha2++;
		return String.format("%c%02d:%c%02d",alpha1, 19-move1.getX(), alpha2, 19-move2.getX());
	}
	
 	public void log(cor move1) {
		logs[logNum]=new cor(move1.getX(), move1.getY());
		logNum++;
	}

	public void runGame() throws ConnSixException {
		conSix = setGame();
		n.close();
		printBoard();
		
		if (aiTag == BLACK) {
			String first = conSix.drawAndRead("K10");
			this.board[9][9] = aiTag;
			System.out.println(first);
			enterInput(first);
		} else if (aiTag == WHITE) {
			String first = conSix.drawAndRead("");
			if (first.compareTo("K10") == 0) { enterInput(new cor(9, 9), awayTag); }
			else { System.out.println("not k10?"); }
		}
		
		printBoard();
		do {
			evaluate.aiTurn(this.board, this.aiTag, this.turn, this.logs, this.logNum);
//			logNum += 2;
			enterInput(conSix.drawAndRead(makeNotation(logs[logNum], logs[logNum+1])));

			increaseTurn();
			printBoard();
		}while(true);
		
	}
	
}//end of class

