import java.awt.geom.*;
import java.awt.*;


public class Brick extends ColorShape {
	public int xPos = 0;
	public int yPos = 0;
	public int width = 0;
	public int height = 0;
	protected Rectangle2D.Double shape;

	// constructor
	public Brick(int x, int y, int w, int h) {
		super(new Rectangle2D.Double(x, y, w, h));
		
		//set brick x, y, width, and height
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		setBorderColor(Color.BLACK);
		// update shape
		shape = (Rectangle2D.Double)super.shape;
		shape.setRect(xPos, yPos, width, height);
	}

	public Brick(int x, int y, int w, int h, Color c) {
		super(new Rectangle2D.Double(x, y, w, h));
		
		//set brick x, y, width, and height
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		setBorderColor(Color.BLACK);
		setFillColor(c);
		// update shape
		shape = (Rectangle2D.Double)super.shape;
		shape.setRect(xPos, yPos, width, height);
	}
	public int getX() {
		return xPos;
	}
	public Rectangle2D.Double getShape() {
		return shape;
	}

	public Color getColor() {
		return getFillColor();
	}
			
}