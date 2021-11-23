//package alpha_6;

import java.util.Scanner;

public class board {
	final private int EMPTY = 0;
	final private int BLACK = 1;
	final private int WHITE = 2;
	final private int RED = 3;
	
	int [][]board;
	int awayTag, homeTag, turn;
	Scanner n;
	cor[] evaluateResult;
	ConnectSix conSix;

	public board() {
		this.board=new int[19][19];
		initializeBoard();
		n=new Scanner(System.in);
		evaluateResult=new cor[2];
		evaluateResult[0] = new cor();
		evaluateResult[1] = new cor();
	}
	
	private void increaseTurn() {
		turn++;
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
		String[] stones = msg.split(":");
		for(String stone : stones) {
			enterInput(parse(stone), awayTag);
		}
	}
	
	public void enterInput(cor move1, int tag_p) {
		this.board[move1.getI()][move1.getJ()]=tag_p;
	}
	
	public ConnectSix setGame() throws ConnSixException {
		System.out.print("Input the ip address > ");
		String ip =n.nextLine();
		System.out.print("Input the port number > ");
		int port = Integer.parseInt(n.nextLine());
		System.out.print("Input the color > ");
		String color = n.nextLine();
		if (color.toLowerCase().compareTo("black") == 0) {
			homeTag = BLACK; awayTag = WHITE;
		}
		else {
			homeTag = WHITE; awayTag = BLACK;
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
	
	public String makeNotation() {
		System.out.println("make notation " + evaluateResult[0]);
		System.out.println("make natation " + evaluateResult[1]);
		char alpha1 = (char) (evaluateResult[0].getJ() + 'A');
		if (alpha1 >= 'I') alpha1++;
		char alpha2 = (char) (evaluateResult[1].getJ() + 'A');
		if (alpha2 >= 'I') alpha2++;
		return String.format("%c%02d:%c%02d", alpha1, 19-evaluateResult[0].getI(), alpha2, 19-evaluateResult[1].getI());
	}

	public void runGame() throws ConnSixException {
		conSix = setGame();
		n.close();
		printBoard();
		
		if (homeTag == BLACK) {
			String first = conSix.drawAndRead("K10");
			this.board[9][9] = homeTag;
			System.out.println(first);
			enterInput(first);
		}
		else if (homeTag == WHITE) {
			String first = conSix.drawAndRead("");
			if (first.compareTo("K10") == 0) { enterInput(new cor(9, 9), awayTag); }
			else { System.out.println("not k10?"); }
		}
		
		printBoard();
		do {
			//evaluate.aiTurn(this.board, this.homeTag, this.turn, this.logs, this.logNum);
			evaluate.runMinimax(board, homeTag, turn, this.evaluateResult);
			enterInput(conSix.drawAndRead(makeNotation()));

			increaseTurn();
			printBoard();
		}while(true);
		
	}
}//end of class

