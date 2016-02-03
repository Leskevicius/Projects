import java.awt.geom.*;
import java.awt.*;

public class Ball extends ColorShape {
	
	// location and size variables
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private static final int height = 15;
	private static final int width = 15;
	private static final Ellipse2D.Double shape = new Ellipse2D.Double(280, 420, width, height);
	private boolean isVisible;
	// constructor
	public Ball() {
		super(shape);

		// set ball color
		super.setFillColor(Color.RED);
		super.setBorderColor(Color.RED);
		
		// set initial values for x and y position and speed
		xPos = 280;
		yPos = 420;
		xSpeed = 4;
		ySpeed = -4;
		isVisible = true;
	}

	public void move() {
		// move ball
		xPos += xSpeed;
		yPos += ySpeed;
		// detect if ball should bounce off an edge
		if (xPos < 0 || xPos > 585) {
			xSpeed = xSpeed*(-1);
		}
		if (yPos < 0) {
			ySpeed = ySpeed*(-1);
		}
		// update shape to new values
		shape.setFrame(xPos, yPos, width, height);
	}

	public void setXspeed(int newSpeed) {
		xSpeed = newSpeed;
	}

	public void setYspeed(int newSpeed) {
		ySpeed = newSpeed;
	}
	public int getXspeed() {
		return xSpeed;
	}

	public int getYspeed() {
		return ySpeed;
	}

	public int getX() {
		return xPos;
	}

	public void setX(int x) {
		xPos = x;
	}

	public void setY(int y) {
		yPos = y;
	}

	public int getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Ellipse2D.Double getShape() {
		return shape;
	}
	//resets the ball
	public void reset() {
		xPos = 280;
		yPos = 420;
		ySpeed = -4;
		xSpeed = 4;
	}
	//freeze the ball
	public void stop() {
		xSpeed = 0;
		ySpeed = 0;
	}
	//visibility
	public void setVisible(boolean b) {
		isVisible = b;
	}
	@Override
	public void paint(Graphics2D brush) {
		if (isVisible) {
			brush.setColor(borderColor);
			brush.draw(shape);
			brush.setColor(fillColor);
			brush.fill(shape);
		}
	}
}