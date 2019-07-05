package gomokuGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Window() throws IOException {
		setLayout(new BorderLayout());
		RightPanel rPanel = new RightPanel();
		DrawingPanel d=new DrawingPanel(rPanel);
		
		add(d,BorderLayout.CENTER);
		
		JLabel label = new JLabel("Gomoku");
		label.setBackground(Color.magenta);
		label.setOpaque(true);
		label.setFont(new Font("Sherif",Font.BOLD,55));
		label.setHorizontalAlignment(getWidth());
		add(label,BorderLayout.NORTH);
		//ImageIcon image = new ImageIcon(ImageIO.read(new File("forLeft.jpg")).getScaledInstance(300, 680, Image.SCALE_SMOOTH));
		URL leftImageUrl = getClass().getResource("/images/forLeft.jpg");
		ImageIcon image = new ImageIcon(ImageIO.read(leftImageUrl).getScaledInstance(300, 680, Image.SCALE_SMOOTH));
		
		add(new JLabel(image),BorderLayout.WEST);
		
		add(rPanel,BorderLayout.EAST);
		
		/*setSize(340, 360);*/
		/*setSize(380, 380);*/
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
