import java.awt.*;
import java.awt.geom.*;

public class Win {
	public static boolean visible;
	public Win() {
		visible = false;
		
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}

	
	public void paint(Graphics2D brush) {
		if (visible) {
			brush.setColor(Color.BLACK);
			brush.setFont(new Font("Courier", Font.BOLD, 25));
			brush.drawString("Winning!", 230, 150);
			brush.setFont(new Font("Courier", Font.BOLD, 18));
			brush.drawString("Big Thanks for Sean and TA's.", 150, 200);
			brush.drawString("For making this is as fun as it is.", 110, 230);
			brush.drawString("Your Final Score is: " + Stats.score, 160, 260);
			brush.drawString("Press Spacebar to replay!", 150, 400);


		}
	}
}