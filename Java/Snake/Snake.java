import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Snake {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setTitle("Snake");
		frame.setResizable(false);
		frame.add(new GamePanel());
		frame.setVisible(true);
	}

	private static class GamePanel extends JPanel {


		public GamePanel() {
			super();
			setBackground(Color.LIGHT_GRAY);

			setFocusable(true);
		}
	}
	/*
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;


	}
	*/




}