import java.awt.*;

public class Stats {
	
	protected static int score;
	protected static int lives;
	protected boolean gameOver;

	//initial stats in the beginning
	public Stats() {
		score = 0;
		lives = 3;
		gameOver = false;
	}

	public int getScore() {
		return score;
	}

	public static int getLives() {
		return lives;
	}

	public void addScore() {
		score += 1;
	}

	public static void setLives(int l) {
		lives = l;
	}

	public void death() {
		lives -= 1;
	}
	//checks if the game is over
	public boolean checkIfGameOver() {
		if (lives < 0) {
			gameOver = true;
		}
		return gameOver;
	}

	//three different paint methods depending if gameover, or lost a life, or regular

	public void paint(Graphics2D brush) {
		brush.setColor(Color.BLACK);
		brush.setFont(new Font("Courier", Font.BOLD, 15));
		brush.drawString("Score:" + score,5,90);
		brush.drawString("Lives:" + lives,5,105);
	}

	public void paintV2(Graphics2D brush) {
		brush.setColor(Color.BLACK);
		brush.setFont(new Font("Courier", Font.BOLD, 15));
		brush.drawString("Score:" + score,5,90);
		brush.drawString("Lives:" + lives,5,105);
		brush.setFont(new Font("Courier", Font.BOLD, 30));
		brush.drawString("Press Spacebar to continue", 80, 150);
		brush.drawString("Lives left: " + lives, 175, 200);
	}

	public void paintV3(Graphics2D brush) {
		brush.setColor(Color.BLACK);
		brush.setFont(new Font("Courier", Font.BOLD, 15));
		brush.drawString("Score:" + score,5,90);
		brush.drawString("Lives:" + lives,5,105);
		brush.setFont(new Font("Courier", Font.BOLD, 30));
		brush.drawString("Game Over", 200, 150);
		brush.drawString("Your final score is: " + score, 100, 200);
		brush.drawString("Press Spacebar to restart", 70, 250);
	}

}

