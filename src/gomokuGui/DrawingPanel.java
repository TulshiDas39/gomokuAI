package gomokuGui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int board[][];
	int humanturnx[]=new int[100];
	int humanturny[]=new int[100];
	
	Color colors[]= {Color.LIGHT_GRAY,Color.BLUE,Color.RED,Color.magenta,Color.ORANGE};
	
	int turn;// turn=1 // turn=2 // turn= 3
	
	Timer t;
	
	GomukuAI gomuku;
	
	boolean gameOver;
	RightPanel rPanel;
	
	public DrawingPanel(RightPanel rPanel) {
		this.rPanel = rPanel;
		setBackground(Color.DARK_GRAY);
		setLayout(new GridLayout(10, 10));	
		/*setSize(400,400);*/
		board=new int[10][10];
		/*setPreferredSize(new Dimension(400, 600));*/
		/*setSize(getWidth(), getHeight());*/
		
		gomuku=new GomukuAI(board);
		
		turn =1;
		
		t=new Timer(1000,this);
		
		
		
		MouseHandler handler = new MouseHandler();
		addMouseListener( handler );
		addMouseMotionListener( handler );
		
		
	}
	
	private class MouseHandler implements MouseListener,MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {
			/*System.out.println(e.getX()+" "+e.getY());*/
			
			int row=e.getY()/62;
			int col=e.getX()/62;
			
			int count=0;
			if(board[row][col]==0) {
				if(turn==1) {
					
					humanturnx[count]=row;
					humanturny[count]=col;
					count++;
					/*System.out.println("joy");*/
					/*board[row][col]=1;*/
					Move move=new Move(row,col);
					gomuku.makeMove(move,1);
					/*System.out.println("maloy");*/
					/*int winTurn=gomuku.checkTerminalState();*/
					/*System.out.println(winTurn);*/
					/*int winTurn=0;*/
					if(gomuku.check(turn)) {
						rPanel.viewWinner("You Win");
							rPanel.addWinGIF();
						turn=3;
						
					}
					
					else{ /*if(winTurn==0)*/
						rPanel.changeTurn(-1, -1,false);
						turn = 2;
						
					}
				}
			}
			
			repaint();
			/*System.out.println(row+" "+col);*/
			t.start();
		}

		/*public void gameOver() {
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					board[i][j]=0;
				}
			}
			repaint();
		}
*/
		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
	
		for(int i=0;i<10*62;i+=62) {
			for(int j=0;j<10*62;j+=62) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(i, j, 60, 60);
				g.setColor(colors[board[j/62][i/62]]);
				g.fillOval(i, j, 60, 60);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(turn==2) {
			rPanel.changeTurn(-1, -1,false);
			Move move = gomuku.setMove(turn,humanturnx,humanturny);
			/*System.out.println("joy");*/
			/*int winTurn=gomuku.checkTerminalState();
			System.out.println("Winturn: "+winTurn);*/
			int winTurn=gomuku.checkTerminalState();
			if(gomuku.check(turn)) {
				rPanel.changeTurn(move.row,move.col,true);
				rPanel.viewWinner("Computer Win");
				rPanel.addLoseGIF();
				turn=3;
				/*repaint();*/
			}
			else if(winTurn == 3){
				rPanel.changeTurn(move.row,move.col,true);
				rPanel.viewWinner("Match Drawn!!!");
				rPanel.addLoseGIF();
			}
			else{ /*if(winTurn==0)s*/
				rPanel.changeTurn(move.row, move.col,false);
				turn = 1;
			}
			repaint();
			
		}
		/*repaint();*/
		 if(turn==3) {
			repaint();
		}
		t.stop();
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

	public void gameOver() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				board[i][j]=0;
			}
		}
		repaint();
		
	}	
}
