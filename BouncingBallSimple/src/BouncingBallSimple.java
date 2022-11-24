import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BouncingBallSimple extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// Atribut Area
	private static final int AREA_WIDTH = 640;
	private static final int AREA_HEIGHT = 480;
	
	// Atribut Ball
	private static final int BALL_X_INITIAL_POSITION = 50;
	private static final int BALL_Y_INITIAL_POSITION = 20;
	
	// Radius Bola
	private double ballRadius = 50;
	
	// Titik tengah bola di awal
	private double ballX = ballRadius + BALL_X_INITIAL_POSITION;
	private double ballY = ballRadius + BALL_Y_INITIAL_POSITION;
	
	// Kecepatan bola di axis X dan axis Y
	private float ballSpeedX = 4;
	private float ballSpeedY = 3;
	
	// Jumlah refresh layar dalam satu detik misal 30 frame per second
	private static final int REFRESH_RATE = 30;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Gambar area latar belakang dengan warna hitam
		g.setColor(Color.black);
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		
		// Gambar bola dengan warna merah
		g.setColor(Color.red);
		g.fillOval((int)(ballX - ballRadius), (int)(ballY - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));
	}
	
	public BouncingBallSimple() {
		this.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
	}
	
	Thread gameThread = new Thread() {
		public void run() {
			while(true) {
				// Update posisi dengan kecepatan
				ballX += ballSpeedX;
				ballY += ballSpeedY;
				
				// Deteksi tepi kanan-kiri
				if(ballX - ballRadius < 0) {
					ballSpeedX *= -1;
					ballX = ballRadius;
				}
				else if(ballX + ballRadius > AREA_WIDTH) {
					ballSpeedX *= -1;
					ballX = AREA_WIDTH - ballRadius;
				}
				
				// Deteksi tepi atas-bawah
				if(ballY - ballRadius < 0) {
					ballSpeedY *= -1;
					ballY = ballRadius;
				}
				else if(ballY + ballRadius > AREA_HEIGHT) {
					ballSpeedY *= -1;
					ballY = AREA_HEIGHT - ballRadius;
				}
				System.out.println(ballX + " " + ballY);
				// Refresh layar
				// Fungsi repaint akan memanggil paintComponent()
				repaint();
				
				// Beri delay
				try {
					Thread.sleep(1000 / REFRESH_RATE);
				}
				catch(InterruptedException ex) {} 
			}
		}
	};
	
	{
		gameThread.start(); // Jalankan thread
	}
	
	public static void main(String[] args) {
		// Menjalankan UI di event dispatcher thread 
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Membuat frame dan memasangkan JPanel ke JFrame
				JFrame frame = new JFrame("Sumple Bouncing Ball");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BouncingBallSimple());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
