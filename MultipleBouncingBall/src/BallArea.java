import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BallArea {
	// Data type
	protected int minX, minY, maxX, maxY;
	private Color colorFilled, colorBorder;
	private Random rand = new Random();
	private int red = rand.nextInt(255), green = rand.nextInt(255), blue = rand.nextInt(255);
	
	// Constructor
	public BallArea(int x, int y, int width, int height) {
		this.minX = x;
		this.minY = y;
		this.maxX = x + width - 1;
		this.maxY = y + height - 1;
//		this.colorFilled = colorFilled;
//		this.colorBorder = colorBorder;
	}
	
	// Setter
	public void set(int x, int y, int width, int height) {
		this.minX = x;
		this.minY = y;
		this.maxX = x + width - 1;
		this.maxY = y + height - 1;
	}
	
	// Drawing graphics
	public void draw(Graphics g) {
		g.setColor(new Color(red, green, blue));
		g.fillRect(minX, minY, maxX - minX, maxY - minY);
		g.setColor(colorBorder);
		g.drawRect(minX, minY, maxX - minX, maxY - minY);
	}
}
