import java.awt.*;
import java.util.Random;

public class Ball {
	private float x, y;
	private float speedX, speedY;
	private float radius;
	private Random rand = new Random();
	private int red = rand.nextInt(255), green = rand.nextInt(255), blue = rand.nextInt(255);
	private Font font = new Font("Times New Roman", Font.BOLD, 40);
	private char c;
	
	// Constructor
	public Ball(float x, float y, float radius, float speed, float angleInDegree, char c) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float)(speed * Math.sin(Math.toRadians(angleInDegree)));
		this.c = c;
	}
	
	// Set Grafik
	public void draw(Graphics g) {
		g.setColor(new Color(red, green, blue));
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
		
		// Drawing char
		g.setColor(Color.black);
		g.setFont(font);
		
		// Char Position
		int cX = (int)(x) - (g.getFontMetrics().stringWidth(String.valueOf(c)) / 2);
		int cY = (int)(y) + (g.getFontMetrics().getHeight() / 4);
		g.drawString(String.valueOf(c), cX, cY);
	}

	public void collide(BallArea box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;
		
		x += speedX;
		y += speedY;

		if(x < ballMinX) {
			speedX *= -1;
			x = ballMinX;
		}
		else if(x > ballMaxX) {
			speedX *= -1;
			x = ballMaxX;
		}
		
		if(y < ballMinY) {
			speedY *= -1;
			y = ballMinY;
		}
		else if(y > ballMaxY) {
			speedY *= -1;
			y = ballMaxY;
		}
	}
	
	public void collide(Ball ball) {
		float tempSpeedX, tempSpeedY;
		
		int distX = (int) (ball.x - x);
		int distY = (int) (ball.y - y);
		
		double distSquared = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		
		if(distSquared <= ball.radius + radius) {
			if(distSquared < ball.radius + radius) {
				double sinT = distY / (distSquared);
				double cosT = distX / (distSquared);
				
				x = ball.x - (float) (cosT * (radius + ball.radius));
				y = ball.y - (float) (sinT * (radius + ball.radius));
			}
			
			tempSpeedX = speedX;
			tempSpeedY = speedY;
			speedX = ball.speedX;
			speedY = ball.speedY;
			ball.speedX = tempSpeedX;
			ball.speedY = tempSpeedY;
			
		}
	}
}
