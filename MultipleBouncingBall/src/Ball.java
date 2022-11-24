import java.awt.*;
import java.util.Random;

public class Ball {
	private float x, y;
	private float speedX, speedY;
	private float radius;
	private Random rand = new Random();
	private int red = rand.nextInt(255), green = rand.nextInt(255), blue = rand.nextInt(255);
	
	// Constructor
	public Ball(float x, float y, float radius, float speed, float angleInDegree) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float)(speed * Math.sin(Math.toRadians(angleInDegree)));
	}
	
	// Set Grafik
	public void draw(Graphics g) {
		g.setColor(new Color(red, green, blue));
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));;
	}

	public void collide(BallArea box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;
		
		x += speedX;
		y += speedY;

//		System.out.println("x : " + x);
//		System.out.println("Bola x : " + ball.x);


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
		
		double distSquared = Math.pow(distX, 2) + Math.pow(distY, 2);
		
		if(distSquared <= Math.pow(ball.radius + radius, 2)) {
			if(distSquared < Math.pow(ball.radius + radius, 2)) {
				double sinT = distY / Math.sqrt(distSquared);
				double cosT = distX / Math.sqrt(distSquared);
				
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
