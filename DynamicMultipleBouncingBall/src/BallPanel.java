import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.JPanel;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class BallPanel extends JPanel implements KeyListener {
	private static final int REFRESH_RATE = 30;
	private Ball ball;
	public List<Ball> balls = new ArrayList<Ball>();
	private BallArea box;
	private int areaWidth, areaHeight;
	private int tempX, tempY;

	public BallPanel(int width, int height) {
		this.areaHeight = height;
		this.areaWidth = width;

		box = new BallArea(0, 0, width, height);

		// Untuk mendapatkan ukuran area latar belakang jika frame di resize
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component) e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, areaWidth, areaHeight);
			}
		});
		addKeyListener(this);
		this.setFocusable(true);
		startThread();
	}

	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					update(box);
					repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					} catch (InterruptedException ex) {
					}
				}
			}
		};

		{
			gameThread.start(); // Jalankan thread
		}
	}

	// Avoid overlapping at start or when random ball
	private static boolean isOverLapping(int x, int y, int ballX, int ballY, int radius) {
		int distX = ballX - x;
		int distY = ballY - y;
		double distSquare = Math.pow(distX, 2) + Math.pow(distY, 2);
		if (distSquare <= Math.pow(radius + radius, 2))
			return true;
		return false;
	}

	private int angleInDegree() {
		Random rand = new Random();
		return rand.nextInt(360);
	}

	private void update(BallArea box) {
		for (Ball ball : balls) {
			ball.collide(box);
			for (Ball ball2 : balls) {
				if (ball == ball2)
					continue;
				ball.collide(ball2);
			}
		}
	}

	private void createBall(char c) {
		Random rand = new Random();
		int radius = 50;
		int speed = 5;
		int x = rand.nextInt(this.areaWidth - radius * 2 - 20) + radius + 10;
		int y = rand.nextInt(this.areaHeight - radius * 2 - 20) + radius + 10;

		if (!balls.isEmpty()) {
			while (isOverLapping(tempX, tempY, x, y, radius)) {
				x = rand.nextInt(this.areaWidth - radius * 2 - 20) + radius + 10;
				y = rand.nextInt(this.areaHeight - radius * 2 - 20) + radius + 10;
			}
		}

		tempX = x;
		tempY = y;
		ball = new Ball(x, y, radius, speed, angleInDegree(), c);
		balls.add(ball);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for (Ball ball : balls) {
			ball.draw(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		boolean isCorrect = Pattern.matches("[A-Z]", String.valueOf(c)) || Pattern.matches("[0-9]", String.valueOf(c))
				|| Pattern.matches("[a-z]", String.valueOf(c));

		if (isCorrect) {
			createBall(c);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
