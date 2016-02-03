import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Breakout {
	

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,500);
        frame.setTitle("Breakout");
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);

	}



	private static class GamePanel extends JPanel {
		
		Ball ball;
		Paddle paddle;
		BrickConfiguration bconfig;
		Timer timer;
		//starting menu
		GameMenu gameMenu;
		//won menu
		Win won;
		//power up timers
		Timer red;
		Timer green;
		Timer blue;
		//stats such as lives, score
		Stats stats;
		boolean dead = false;
		//for adding to key listeners
		Continue cont = new Continue();
		Restart rest = new Restart();


		public GamePanel() {
			super();
			setBackground(Color.LIGHT_GRAY);
			// call initializeGameObjects() and initializeTimers()
			initializeTimers();
			initializeGameObjects();
			// add PaddleMover as a keyListener
			addKeyListener(new PaddleMover());
			setFocusable(true);		
		}

		public void initializeGameObjects() {
			// instantiate ball, paddle, and brick configuration
			paddle = new Paddle();
			ball = new Ball();
			bconfig = new BrickConfiguration();
			stats = new Stats();
			gameMenu = new GameMenu();
			won = new Win();

			//start the timers
			timer.start();
		}

		public void initializeTimers() {
			// set up timer to run GameMotion() every 10ms
			timer = new Timer(10, new GameMotion());
			//timers for power ups
			red = new Timer(15000, new RedPowerUp());
			blue = new Timer(15000, new BluePowerUp());
			green = new Timer(15000, new GreenPowerUp());


		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
		
			//if dead, this will give you three seconds to get ready for your next life
			// paint ball, paddle, and brick configuration
			ball.paint(g2);
			paddle.paint(g2);
			bconfig.paint(g2);
			gameMenu.paint(g2);
			won.paint(g2);
			//if dead, paints version two of stats.
			if (dead == true && stats.checkIfGameOver() == true) {
				stats.paintV3(g2);
				
			}
			else if (dead) {
				stats.paintV2(g2);

			}
			else {
				stats.paint(g2);
			}
			

		}
	


		private class GameMotion implements ActionListener {
			public GameMotion() {

			}

			public void actionPerformed(ActionEvent evt) {
				
				//won
				checkForWin();
				//move ball automatically
				ball.move();

				//move paddle according to key press
				paddle.move();

				//check if the ball hits the paddle or a brick
				checkForHit();
				
				//check if dead or gameover;
				checkForDeathAndGameOver();	

				//power ups
				checkForPowerUp();

				//gameMenu
				checkForGameMenu();

				//call repaint
				repaint();
				
			}
		}
		//red power up listener
		private class RedPowerUp implements ActionListener {
			public RedPowerUp() {}
			public void actionPerformed(ActionEvent evt) {
				bconfig.redPowerUp = false;
				red.stop();
				paddle.setWidth(70);

			}
		}
		//green power up listener
		private class GreenPowerUp implements ActionListener {
			public GreenPowerUp() {}
			public void actionPerformed(ActionEvent evt) {
				bconfig.greenPowerUp = false;
				green.stop();

			}
		}
		//blue power up listener
		private class BluePowerUp implements ActionListener {
			public BluePowerUp() {}
			public void actionPerformed(ActionEvent evt) {
				bconfig.greenPowerUp = false;
				green.stop();
			}
		}
		//restart listener
		private class Restart implements KeyListener {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					initializeGameObjects();
					ball.setVisible(true);
					won.setVisible(false);
					ball.reset();
					removeKeyListener(rest);

				}
			}
			public void keyReleased(KeyEvent evt) {

			}
			public void keyTyped(KeyEvent evt) {

			}
		}
		//continue listener
		private class Continue implements KeyListener {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					dead = false;
					ball.setVisible(true);
					gameMenu.setVisible(false);
					ball.reset();
					removeKeyListener(cont);


				}
			}
			public void keyReleased(KeyEvent evt) {

			}
			public void keyTyped(KeyEvent evt) {

			}
		}
		//moves the paddle according to keys
		private class PaddleMover implements KeyListener {
			public void keyPressed(KeyEvent evt) {
				// change paddle speeds for left and right key presses
				int key = evt.getKeyCode();

				if (key == KeyEvent.VK_RIGHT) {
					paddle.setSpeed(5);
				}
				if (key == KeyEvent.VK_LEFT) {
					paddle.setSpeed(-5);
				}
			}
			public void keyReleased(KeyEvent evt) {
				// set paddle speed to 0
				paddle.setSpeed(0);
			}
			public void keyTyped(KeyEvent evt) {}
		}

		//will change boolean dead accordingly
		public void checkForDeathAndGameOver() {
			if (stats.checkIfGameOver() != true) {
				if (ball.getY() > 500) {
					dead = true;
					stats.death();
					ball.reset();
					ball.stop();
					ball.setVisible(false);
					addKeyListener(cont);	
				}
			} else if (stats.checkIfGameOver()) {
				addKeyListener(rest);
			}


		}
		//check if game menu should be visible
		public void checkForGameMenu() {
			if (gameMenu.visible) {
				ball.stop();
				addKeyListener(cont);
				ball.setVisible(false);
			}
		}
		//check if win menu should be visible
		public void checkForWin() {
			if (bconfig.bricksLeft() <= 0) {
				won.visible = true;
				ball.stop();  
				addKeyListener(rest);
				ball.setVisible(false);
			}
		}

		//check for power ups
		public void checkForPowerUp() {
			if (bconfig.redPowerUp) {
				paddle.setWidth(105);
				red.start();
				bconfig.redPowerUp = false;
			}
			if (bconfig.greenPowerUp) {
				green.start();
			}
			if (bconfig.bluePowerUp) {


				blue.start();
			}
		}

		public void checkForHit() {
			
			// change ball speed when ball hits paddle
			if (ball.getShape().intersects(paddle.getShape())) {
				int leftSide = paddle.getX();
				int middleLeft = paddle.getX() + (int)(paddle.getWidth()/3);
				int middleRight = paddle.getX() + (int)(2*paddle.getWidth()/3);
				int rightSide = paddle.getX() + paddle.getWidth();

				if ((ball.getX() >= leftSide) && (ball.getX() < middleLeft)) {
					// change ball speed
					ball.setXspeed(-4);
					ball.setYspeed(-4);
				}
				if ((ball.getX() >= middleLeft) && (ball.getX() <= middleRight)) {
					// change ball speed
					ball.setXspeed(0);
					ball.setYspeed(-4);
				}
				if ((ball.getX() > middleRight) && (ball.getX() <= rightSide)) {
					// change ball speed
					ball.setXspeed(4);
					ball.setYspeed(-4);
				}
			}

			// change ball speed when ball hits brick
			for (int i = 0; i < bconfig.getRows(); i++) {
				for (int j = 0; j < bconfig.getCols(); j++) {
					if (bconfig.exists(i,j)) {
						if (ball.getShape().intersects(bconfig.getBrick(i,j).getShape())) {
							Point ballLeft = new Point((int)ball.getShape().getX(), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballRight = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballTop = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)ball.getShape().getY());
							Point ballBottom = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)(ball.getShape().getY() + ball.getShape().getHeight()));
							if (bconfig.getBrick(i,j).getShape().contains(ballLeft)) {
								// change ball speed
								ball.setXspeed(4);
							}
							else if(bconfig.getBrick(i,j).getShape().contains(ballRight)) {
								// change ball speed
								ball.setXspeed(-4);
							}
							if (bconfig.getBrick(i,j).getShape().contains(ballTop)) {
								// change ball speed
								ball.setYspeed(4);
							}
							else if (bconfig.getBrick(i,j).getShape().contains(ballBottom)) {
								// change ball speed
								ball.setYspeed(-4);
							}
							

							// remove brick
							bconfig.removeBrick(i,j);
							stats.addScore();
							if (bconfig.greenPowerUp) {
								stats.addScore();
							}
							
						}
					}
				}
			}
		}
	}
}