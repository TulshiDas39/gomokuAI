package gomokuGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	Color colors[]= {Color.LIGHT_GRAY,Color.BLUE,Color.RED,Color.magenta,Color.ORANGE};
	String position[] = {"1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"};
	
	int turn;// turn=1 // turn=2 // turn= 3
	JLabel labelGIF;
	GomukuAI gomuku;
	
	boolean gameOver;
	
	JLabel turn1;
	JLabel status;
	
	public RightPanel() throws MalformedURLException {
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(445, getHeight()));
		setLayout(new GridLayout(4, 1));
		
		turn1 = new JLabel("Your turn");
		turn1.setForeground(Color.WHITE);
		turn1.setFont(new Font("Sherif",Font.PLAIN,20));
		status = new JLabel("");
		status.setFont(new Font("Sherif",Font.BOLD,25));
		status.setHorizontalAlignment(getWidth());
		status.setForeground(Color.PINK);
		status.setFont(new Font("Sherif",Font.PLAIN,20));
		
		add(turn1);
		add(status);
		
	
	}
	
	public void addWinGIF() throws MalformedURLException {
		
		URL url = new URL("file:///C:/Users/Tulshi%20Das/Google%20Drive/Programspace/gomokuGui2/congrats.gif");
        Icon icon = new ImageIcon(url);
        labelGIF = new JLabel(icon);
        /*labelGIF.setVisible(false);*/
        add(labelGIF);
        
	}
	
	/*public void viewCongrats(){
		labelGIF.setVisible(true);
		
	}*/
	
	public void addLoseGIF() throws MalformedURLException{
		URL url = new URL("file:///C:/Users/Tulshi%20Das/Google%20Drive/Programspace/gomokuGui2/game_over.gif");
        Icon icon = new ImageIcon(url);
        labelGIF = new JLabel(icon);
        labelGIF.setPreferredSize(new Dimension(getWidth(), 50));
        add(labelGIF);
	}
	

	public void changeTurn(int row , int col,boolean isWin){
		if(row == -1 && col == -1)turn1.setText("Computer is thinking");
		else if(isWin){
			turn1.setText("Computer maked move in "+position[row]+" row,"+position[col]+" column.");
		}
		else turn1.setText("<html>Computer maked move in "+position[row]+" row,"+position[col]+" column.<br/>Now its your turn</html>");
	}
	
	public void viewWinner(String status){
		this.status.setText(status);
		this.status.setFont(new Font("Sherif",Font.BOLD,25));
		this.status.setForeground(Color.PINK);
	}
	
	
	
}
		
		
		
	
	
	
