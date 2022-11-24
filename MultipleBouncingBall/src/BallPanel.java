import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class BallPanel extends JPanel implements MouseListener{
	private static final int REFRESH_RATE = 30;
	private Ball ball;
	public List<Ball> balls = new ArrayList<Ball>();
	private BallArea box;
	private int areaWidth, areaHeight;
	
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
				box.set(0, 0, width, height);
			}
		});
		addMouseListener(this);
		startThread();
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while(true) {
					update(box);
					repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					} catch(InterruptedException ex) {}
				}
			}
		};
		
		{
			gameThread.start(); // Jalankan thread
		}
	}

	private int angleInDegree() {
		Random rand = new Random();
		return rand.nextInt(360);
	}
	
	private void update(BallArea box){
		for(Ball ball : balls) {
			ball.collide(box);
			for(Ball ball2 : balls) {
				if(ball == ball2) continue;
				ball.collide(ball2);
			}
		}
	}
	
	private void createBall(int x, int y) {
		Random rand = new Random();
		int radius = rand.nextInt(30, 80);
		int speed = 5;

		// Danger OverLapping	
		ball = new Ball(x, y, radius, speed, angleInDegree());
		balls.add(ball);
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for(Ball ball : balls){
			ball.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		createBall(x, y);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
