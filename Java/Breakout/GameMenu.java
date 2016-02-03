import java.awt.*;
import java.awt.geom.*;

public class GameMenu {
	public static boolean visible;
	public GameMenu() {
		visible = true;

	}
	
	public void setVisible(boolean b) {
		visible = b;
	}

	
	public void paint(Graphics2D brush) {
		if (visible) {
			brush.setColor(Color.BLACK);
			brush.setFont(new Font("Courier", Font.BOLD, 25));
			brush.drawString("Game Menu", 210, 150);
			brush.setFont(new Font("Courier", Font.BOLD, 18));
			brush.drawString("Blue Blocks: extra life", 150, 200);
			brush.drawString("Red Blocks: extend paddle for 15 seconds", 70, 230);
			brush.drawString("Green Blocks: double score for 15 seconds", 65, 260);
			brush.drawString("Press Spacebar to start!", 150, 400);


		}
	}
}