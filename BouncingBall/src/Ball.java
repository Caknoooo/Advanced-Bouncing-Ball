import java.awt.*;
import java.util.Random;

public class Ball {
	private float x, y;
	private float speedX, speedY;
	private float radius;
	protected boolean flag = false;
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
		flag = false;
		
		x += speedX;
		y += speedY;
		
		if(x < ballMinX) {
			speedX *= -1;
			x = ballMinX;
			flag = true;
			//ballArea.checkCollideFunc();
		}
		else if(x > ballMaxX) {
			speedX *= -1;
			x = ballMaxX;
			flag = true;
			//ballArea.checkCollideFunc();
		}
		
		if(y < ballMinY) {
			speedY *= -1;
			y = ballMinY;
			flag = true;
			//ballArea.checkCollideFunc();
		}
		else if(y > ballMaxY) {
			speedY *= -1;
			y = ballMaxY;
			flag = true;
			//ballArea.checkCollideFunc();
		}
	}
}