import java.awt.*;
import java.awt.geom.*;

public class BrickConfiguration {
	//power ups
	protected static boolean redPowerUp = false;
	protected static boolean greenPowerUp = false;
	protected static boolean bluePowerUp = false;
	
	//location and size variables
	private static final int xStart = 4;
	private static final int yStart = 4;	
	private static final int numCols = 12;
	private static final int numRows = 6;
	private static final int brickHeight = 10;
	private static final int brickWidth = 45;

	// 2D array containing brick objects
	private static Brick[][] bricks = new Brick[numCols][numRows];

	// 2D array telling us whether the brick should be painted (has it been hit?)
	private static boolean[][] paintBricks = new boolean[numCols][numRows];
	// 2d array telling if its a special Brick with power ups
	private static boolean[][] specialBrick = new boolean[numCols][numRows];
	
	// constructor
	public BrickConfiguration() {
		
		// create new bricks and store them in bricks array
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				// initialize paintBricks[i][j]
				paintBricks[i][j] = true;
				// initialize bricks[i][j]
				if (Math.random() > .70) {
					specialBrick[i][j] = true;
					bricks[i][j] = new Brick(i*49+xStart,j*12+yStart,brickWidth,brickHeight,getSpecialColor());
				} else {
					bricks[i][j] = new Brick(i*49+xStart,j*12+yStart,brickWidth,brickHeight);

				}
			}
		}		
	}

	// paint the bricks array to the screen
	public void paint(Graphics2D brush) {
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				// determine if brick should be painted
				if (paintBricks[i][j]) {
					//if so, call paintBrick()
					paintBrick(bricks[i][j],brush);
				}
			}
		}
	}

	// paint an individual brick
	public void paintBrick(Brick brick, Graphics2D brush) {
		// call brick's paint method
		
		if (greenPowerUp) {
			brush.setColor(Color.BLACK);
			brush.setFont(new Font("Courier", Font.BOLD, 15));
			brush.drawString("Double Score!",5,120);
		}
		brick.paint(brush);
		
		
	}

	public Color getSpecialColor() {
		double random = Math.random()*100;
		Color c = new Color(0,0,0);
		if (random <= 40.0) {
			c = Color.GREEN;
		}
		else if (random <= 80.0 && random > 40.0) {
			c = Color.RED;
		}
		else if (random <= 100.0 && random > 80.0) {
			c = Color.BLUE;
		}

		return c;
	}

	public void removeBrick(int row, int col) {
		// update paintBricks array for destroyed brick
		paintBricks[col][row] = false;
		if (specialBrick[col][row]) {
			if (bricks[col][row].getColor() == Color.RED) {

				redPowerUp = true;

			}	else if (bricks[col][row].getColor() == Color.GREEN) {

				greenPowerUp = true;

			}	else if (bricks[col][row].getColor() == Color.BLUE) {

				bluePowerUp = true;
				Stats.setLives(Stats.getLives() + 1);
			}
		}
	}

	public Brick getBrick(int row, int col) {
		return bricks[col][row];
	}

	public boolean exists(int row, int col) {
		return paintBricks[col][row];
	}

	public int getRows() {
		return numRows;
	}

	public int getCols() {
		return numCols;
	}

	public int bricksLeft() {
		int left = 0;
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				if (exists(j,i)) {
					left++;
				}
			}
		}
	return left;
	}

}