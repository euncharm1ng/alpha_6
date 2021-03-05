package alpha_6;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class BoardGUI {
	JFrame frame = new JFrame("tester");
	JButton[] btn = new JButton[361];
	int turnCounter, loc1, loc2;

	
	private void startingSetting(ImageIcon Nothing, board board) {
		Object[] options = { "black", "white" };
		int n = JOptionPane.showOptionDialog(frame, "Choose the color for the ai (black is first)",
				"starting settings", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		System.out.println("it is: " + n);
		board.setAiTag(n);
		if(n==0)
			turnCounter=2;

		Object[] possibilities = { 0,1,2,3,4,5 };
		int s =  (int) JOptionPane.showInputDialog(frame, "choose the number of red stones",
				"starting settings", JOptionPane.PLAIN_MESSAGE, Nothing, possibilities, "0");
		System.out.println("it is: " + s);
		board.setNumOfRed(s);
	}
	
	
	public void show() {

		

		board board = new board();

		JPanel panel = new JPanel(new GridLayout(19, 19));
		ImageIcon Nothing = resizeIcon(new ImageIcon("icon/Nothing.png"));
		ImageIcon NothingTop = resizeIcon(new ImageIcon("icon/Nothing_Top.png"));
		ImageIcon NothingBot = resizeIcon(new ImageIcon("icon/Nothing_Bot.png"));
		ImageIcon NothingRight = resizeIcon(new ImageIcon("icon/Nothing_Right.png"));
		ImageIcon NothingLeft = resizeIcon(new ImageIcon("icon/Nothing_Left.png"));
		ImageIcon NothingTopLeft = resizeIcon(new ImageIcon("icon/Nothing_TopLeft.png"));
		ImageIcon NothingTopRight = resizeIcon(new ImageIcon("icon/Nothing_TopRight.png"));
		ImageIcon NothingBotLeft = resizeIcon(new ImageIcon("icon/Nothing_BotLeft.png"));
		ImageIcon NothingBotRight = resizeIcon(new ImageIcon("icon/Nothing_BotRight.png"));

		ImageIcon Black = resizeIcon(new ImageIcon("icon/Black.png"));
		ImageIcon BlackTop = resizeIcon(new ImageIcon("icon/Black_Top.png"));
		ImageIcon BlackBot = resizeIcon(new ImageIcon("icon/Black_Bot.png"));
		ImageIcon BlackRight = resizeIcon(new ImageIcon("icon/Black_Right.png"));
		ImageIcon BlackLeft = resizeIcon(new ImageIcon("icon/Black_Left.png"));
		ImageIcon BlackTopLeft = resizeIcon(new ImageIcon("icon/Black_TopLeft.png"));
		ImageIcon BlackTopRight = resizeIcon(new ImageIcon("icon/Black_TopRight.png"));
		ImageIcon BlackBotLeft = resizeIcon(new ImageIcon("icon/Black_BotLeft.png"));
		ImageIcon BlackBotRight = resizeIcon(new ImageIcon("icon/Black_BotRight.png"));

		ImageIcon White = resizeIcon(new ImageIcon("icon/White.png"));
		ImageIcon WhiteTop = resizeIcon(new ImageIcon("icon/White_Top.png"));
		ImageIcon WhiteBot = resizeIcon(new ImageIcon("icon/White_Bot.png"));
		ImageIcon WhiteRight = resizeIcon(new ImageIcon("icon/White_Right.png"));
		ImageIcon WhiteLeft = resizeIcon(new ImageIcon("icon/White_Left.png"));
		ImageIcon WhiteTopLeft = resizeIcon(new ImageIcon("icon/White_TopLeft.png"));
		ImageIcon WhiteTopRight = resizeIcon(new ImageIcon("icon/White_TopRight.png"));
		ImageIcon WhiteBotLeft = resizeIcon(new ImageIcon("icon/White_BotLeft.png"));
		ImageIcon WhiteBotRight = resizeIcon(new ImageIcon("icon/White_BotRight.png"));
		
		startingSetting(Nothing, board);

		for (int i = 0; i < 361; i++) {
			btn[i] = new JButton();
			panel.add(btn[i]);
			if (i == 0)
				btn[i].setIcon(NothingTopLeft);
			else if (i == 18)
				btn[i].setIcon(NothingTopRight);
			else if (i == 342)
				btn[i].setIcon(NothingBotLeft);
			else if (i == 360)
				btn[i].setIcon(NothingBotRight);
			else if (i < 19)
				btn[i].setIcon(NothingTop);
			else if (i % 19 == 0)
				btn[i].setIcon(NothingLeft);
			else if (i % 19 == 18)
				btn[i].setIcon(NothingRight);
			else if (i > 342)
				btn[i].setIcon(NothingBot);
			else
				btn[i].setIcon(Nothing);

			btn[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					turnCounter++;
					int location = findCor(e.getSource());

					if (turnCounter == 1)
						loc1 = location;
					else
						loc2 = location;

					if (location == 0)
						btn[location].setIcon(BlackTopLeft);
					else if (location == 18)
						btn[location].setIcon(BlackTopRight);
					else if (location == 342)
						btn[location].setIcon(BlackBotLeft);
					else if (location == 360)
						btn[location].setIcon(BlackBotRight);
					else if (location < 19)
						btn[location].setIcon(BlackTop);
					else if (location % 19 == 0)
						btn[location].setIcon(BlackLeft);
					else if (location % 19 == 18)
						btn[location].setIcon(BlackRight);
					else if (location > 342)
						btn[location].setIcon(BlackBot);
					else
						btn[location].setIcon(Black);

					if (turnCounter == 2) {
						board.getInput(loc1, loc2);
						board.increaseTurn();
						board.aiTurnFunc();
						cor[] aiStone = board.placeAiStone();
						for (int i = 0; i < 2; i++) {
							int aiLoc = aiStone[i].x * 19 + aiStone[i].y;
							if (aiLoc == 0)
								btn[aiLoc].setIcon(WhiteTopLeft);
							else if (aiLoc == 18)
								btn[aiLoc].setIcon(WhiteTopRight);
							else if (aiLoc == 342)
								btn[aiLoc].setIcon(WhiteBotLeft);
							else if (aiLoc == 360)
								btn[aiLoc].setIcon(WhiteBotRight);
							else if (aiLoc < 19)
								btn[aiLoc].setIcon(WhiteTop);
							else if (aiLoc % 19 == 0)
								btn[aiLoc].setIcon(WhiteLeft);
							else if (aiLoc % 19 == 18)
								btn[aiLoc].setIcon(WhiteRight);
							else if (aiLoc > 342)
								btn[aiLoc].setIcon(WhiteBot);
							else
								btn[aiLoc].setIcon(White);
						}
						turnCounter=0;
					}
				}
			});
		}

		frame.add(panel);
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	} // end of method

	public int findCor(Object c) {
		for (int i = 0; i < 361; i++) {
			if (btn[i] == c) {
				System.out.println("this is button: " + i);
				return i;
			}
		}
		return -1;
	} // end of method

	public ImageIcon resizeIcon(ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(600 / 19, 600 / 19, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	} // end of method

} // end of class
