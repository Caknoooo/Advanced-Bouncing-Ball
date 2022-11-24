import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BallArea {
	// Data type
	protected int minX, minY, maxX, maxY;
	private Color colorFilled, colorBorder;
	protected static final boolean collide = false;
	private Random rand = new Random();
	private int red = rand.nextInt(1, 255);
	private int green = rand.nextInt(1, 255);
	private int blue = rand.nextInt(1, 255);
	private boolean checkCollide = false;
	// Contructor
	public BallArea(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
		this.minX = x;
		this.minY = y;
		this.maxX = x + width - 1;
		this.maxY = y + height - 1;
		this.colorFilled = colorFilled;
		this.colorBorder = colorBorder;
	}
	
	// Setter
	public void set(int x, int y, int width, int height) {
		this.minX = x;
		this.minY = y;
		this.maxX = x + width - 1;
		this.maxY = y + height - 1;
	}
	
	protected void checkCollideFunc() {
		checkCollide = true;
	}
	
	// Drawing graphics
	public void draw(Graphics g) {
		g.setColor(new Color(red, green, blue));
		g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
		g.setColor(colorBorder);
		g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
	}
	
//	public void drawCollide(Graphics g) {
//		g.setColor(new Color(red, green, blue));
//		g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
//		g.setColor(colorBorder);
//		g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
//	}
}