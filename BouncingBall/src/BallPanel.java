import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallPanel extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	private static final int REFRESH_RATE = 30;
	private Ball ball;
	private BallArea box;
	private int areaWidth, areaHeight;
	protected List<Ball> balls = new ArrayList<Ball>();
	
	public BallPanel(int width, int height) {
		this.areaHeight = height;
		this.areaWidth = width;
		this.addMouseListener(this);
		
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.WHITE);
	
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
	
	protected void update(BallArea box) {
		for(Ball ball : balls) {
			ball.collide(box);
		}
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for(Ball ball : balls) {
			ball.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int radius = rand.nextInt(50, 100);
				
				
		int speed = 5;
		int angleInDegree = rand.nextInt(360);
				
		ball = new Ball(e.getX(), e.getY(), radius, speed, angleInDegree);
		balls.add(ball);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int radius = rand.nextInt(50, 100);
		
		
		int speed = 5;
		int angleInDegree = rand.nextInt(360);
		
		ball = new Ball(e.getX(), e.getY(), radius, speed, angleInDegree);
		balls.add(ball);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
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