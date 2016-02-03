import java.awt.*;
import java.awt.geom.*;

public class Paddle extends ColorShape{
	
	// location and size variables
	private static int speed;
	private static int xPos;
	private static final int yPos = 461;
	private static int width = 70;
	private static final int height = 10;

	private static final Rectangle2D.Double shape = new Rectangle2D.Double(260,yPos,width,height);

	public Paddle() {
		super(shape);

		// set paddle color
		setFillColor(Color.BLACK);
		setBorderColor(Color.BLACK);

		// set paddle position and speed
		xPos = 260;
		speed = 0;

	}

	public void move() {
		// move paddle
		xPos += speed;
		// stop the paddle from moving at the edges
		if (xPos < 0) {
			xPos = 0;
		}
		if (xPos > 600-width-10) {
			xPos = 600-width-10;
		}
		// update shape
		shape.setRect(xPos, yPos, width, height);
	}

	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		width = w;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return xPos;
	}

	public Rectangle2D.Double getShape() {
		return shape;
	} 

}