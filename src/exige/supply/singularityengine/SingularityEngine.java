package exige.supply.singularityengine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.RandomLevel;
import exige.supply.singularityengine.modules.VectorViewer;
import supply.exige.phisix.PhisiX;

/**
 * Singularity Engine Class. Responsible for managing and holding all other
 * engine classes together.
 *
 * @author Ali Shariatmadari
 * @version 1.0
 */

public class SingularityEngine extends Canvas implements Runnable {

	public static final String ENGINE_NAME = "Singularity Engine ";
	public static final double VERSION = 1.011;

	private static final long serialVersionUID = 1L;

	public final static int SCALE = 1;
	public final static int WIDTH = 800;
	public final static int HEIGHT = WIDTH / 16 * 9;
	public boolean fullscreen;

	public static int UP = 30;

	private String title = ENGINE_NAME + " v" + VERSION + " | ";
	private Thread thread;
	private JFrame frame;
	private boolean running;
	private boolean paused = false;

	private Screen screen;
	private Level level;
	private VectorViewer vectorViewer;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create buffered image
																								// based on vortex
																								// height and width
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Access image data from
																							// image raster and store in
																							// engine pixels array

	public SingularityEngine() {
		init(false); // Init SingularityEngine
	}

	public SingularityEngine(boolean fullscreen) {
		init(false); // Init SingularityEngine
	}

	public SingularityEngine(String title, boolean fullscreen) {
		init(fullscreen); // Init SingularityEngine
		this.title += title + " | "; // Append title
		frame.setTitle(this.title); // Set game title
	}

	public SingularityEngine(String title) {
		init(false); // Init SingularityEngine
		this.title += title + " | "; // Append title
		frame.setTitle(this.title); // Set game title
	}

	private void init(boolean fullscreen) {
		screen = new Screen(WIDTH, HEIGHT);
		vectorViewer = new VectorViewer(screen);
		this.fullscreen = fullscreen;

		// DEFAULT VALUES
		setLevel(new RandomLevel(64, 100)); // Set level to Random by default

		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size); // Set window dimensions
		frame = new JFrame(); // Instantiate game frame

		if (fullscreen) { // If the frame is desired to be full screen
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize area
			frame.setUndecorated(true); // Remove top bar
		}

		frame.setResizable(false); // Disable resize
		frame.add(this); // Add game engine to JFrame
		frame.pack(); // Fill entire window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close When the exit button is pressed
		frame.setLocationRelativeTo(null); // Center window
		frame.setVisible(true); // Show window
	}

	@Override
	public void run() { // Thread run
		// Setup timer
		long lastTime = System.nanoTime(); // Retrieve precise system time, pre render loop
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / UP; // Refresh constant - How many nano seconds must pass for one refresh
												// cycle(N / UPS)
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus(); // Put game in focus
		while (running) {
			long now = System.nanoTime(); // Current time
			delta += (now - lastTime) / ns; // DeltaTime divided by refresh const
			lastTime = now; // Reset lastTime
			while (delta >= 1) { // If time elapsed is passed one update cycle or more
				update(); // Update game
				updates++; // Increment update counter
				delta--; // Decrement delta, if delta is still >=, rerun loop for the update() to catch up
			}
			render(); // Render frame
			frames++; // Increment frames per frame render

			if (System.currentTimeMillis() - timer > 1000) { // Once a second has passed
				timer += 1000; // Increment timer by one second
				frame.setTitle(title + frames + " FPS - " + updates + " UPS");
				updates = 0; // Reset updates calculation
				frames = 0; // Reset frames calculation
			}
		}
	}

	// update method run every update cycle to update the game state
	public void update() {
		if (paused)
			return;
		level.update();
		PhisiX.ball.time += 0.1;
	}

	// render method run every render cycle to render game state
	public void render() {
		if (paused)
			return;
		if (getBufferStrategy() == null) { // if buffer strategy is non-existent,
			createBufferStrategy(3); // Create triple buffer
		}

		BufferStrategy bs = getBufferStrategy(); // Retrieve the buffer strategy
		screen.clear(); // Clear screen
		level.render(0, 0, screen); // Render current screen
		vectorViewer.render(); // Render vector properties
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.getPixels()[i]; // Write screen pixels to buffered pixels
		}
		Graphics g = bs.getDrawGraphics(); // Link graphics object to buffer strategy
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Draw image to Graphics object
		PhisiX.drawStuff(g);
		g.dispose(); // Empty rendered graphics from memory
		bs.show(); // Show calculated buffer
	}

	// SingularityEngine thread start
	public synchronized void start() {
		System.out.println("Starting Game Thread!");
		running = true;
		thread = new Thread(this, title);
		thread.start();
	}

	// SingularityEngine thread stop
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frame.dispose(); // Close game window
	}

	/**
	 * Modify the refresh rate of the engine
	 */
	public void setRefreshRate(int ups) {
		UP = ups;
	}

	/**
	 * Should be used by outside classes to pause Sets Game pause displays pause
	 * menu
	 */
	public void pause() {
		if (!paused)
			setPaused(true);
	}

	/**
	 * Sets Game pause displays pause menu
	 *
	 * @param state
	 */
	public void setPaused(boolean state) {
		if (state)
			// PhisiX.showPauseMenu();
			this.paused = state;
	}

	/**
	 * @return pause state
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * Sets Game state
	 *
	 * @param state
	 */
	public void setRunning(boolean state) {
		this.running = state;
	}

	/**
	 * @return game state
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Sets Game title
	 *
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title + " |";
	}

	/**
	 * Sets Game @{@link Level}
	 *
	 * @param level
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * @return game @{@link Level}
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @return game @{@link Screen}
	 */
	public Screen getScreen() {
		return screen;
	}

	/**
	 * @return fullscreen state
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}

}