package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.cs15.game.world.GameWorld;
import eg.edu.alexu.csd.oop.game.cs15.game.world.ModeFactory;

public class Main {
	private static String Difficulty;
	private static String playerName;

	public static String getPlayerName() {
		return playerName;
	}

	public static void setPlayerName(String playerName) {
		Main.playerName = playerName;
	}

	public static void main(String[] args) {

		ThreadingPool pool = ThreadingPool.getInstance(2);
		SplashThread splash = new SplashThread();
		SoundThread sound = new SoundThread();
		pool.execute(splash);
		try {

			Thread.sleep(1000);
			pool.execute(sound);
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		splash.stop();
		Runnable game = new Runnable() {

			@Override
			public void run() {
				startGame();

			}
		};
		pool.execute(game);
	}

	public static void startGame() {
		JFrame modeChooser = new JFrame("Choose the Level!");
		int x = 280;
		int y = 396;
		modeChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modeChooser.setSize(x, y);
		modeChooser.setLocationRelativeTo(null);
		modeChooser.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		JLabel easyLabel = new JLabel("Easy Mode", JLabel.CENTER);
		easyLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
		easyLabel.setForeground(Color.white);
		easyLabel.setBounds((x / 2) - 100, (int) (y * 0.10), 200, 100);
		modeChooser.add(easyLabel);
		easyLabel.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				Difficulty = "EASY";
				modeChooser.setVisible(false);
				start();
			}
		});
		JLabel mediumLabel = new JLabel("Medium Mode", JLabel.CENTER);
		mediumLabel.setBounds((x / 2) - 100, (int) (y * 0.40), 200, 100);
		mediumLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
		mediumLabel.setForeground(Color.white);
		modeChooser.add(mediumLabel);
		mediumLabel.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				Difficulty = "MODERATE";
				modeChooser.setVisible(false);
				start();
			}
		});
		JLabel hardLabel = new JLabel("Hard Mode", JLabel.CENTER);
		hardLabel.setBounds((x / 2) - 100, (int) (y * 0.7), 200, 100);
		hardLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
		hardLabel.setForeground(Color.white);
		modeChooser.add(hardLabel);
		hardLabel.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				Difficulty = "HARD";
				modeChooser.setVisible(false);
				start();
			}
		});
		try {
			modeChooser.getContentPane().add(new IntroBackGround(x, y, "/intro.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		modeChooser.setResizable(false);
		modeChooser.setVisible(true);
		JFrame inputDialog = new JFrame("Player name");
		inputDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputDialog.setLocationRelativeTo(null);
		inputDialog.setSize(500, 200);
		inputDialog.setLayout(new GridLayout(3, 1));
		JLabel lbl = new JLabel("Please enter your 5 letter Name.", JLabel.CENTER);
		lbl.setFont(new Font("Courier New", Font.ITALIC, 20));
		lbl.setForeground(Color.red);
		inputDialog.add(lbl);
		JTextField txtPlayer = new JTextField();
		txtPlayer.setFont(new Font("Courier New", Font.ITALIC, 20));
		txtPlayer.setForeground(Color.blue);
		txtPlayer.setSize(250, 50);
		inputDialog.add(txtPlayer);
		JPanel btnPanel = new JPanel();
		JButton btn = new JButton("ok");
		btn.setFont(new Font("Courier New", Font.BOLD, 20));
		btn.setSize(100, 50);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtPlayer.getText().isEmpty()) {
					setPlayerName(txtPlayer.getText());
					inputDialog.setVisible(false);
				}
			}
		});
		btnPanel.add(btn);
		inputDialog.add(btnPanel);
		inputDialog.setVisible(true);
		inputDialog.setResizable(false);
	}

	public static void start() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenu mode = new JMenu("Mode");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		JMenuItem easyModeMenuItem = new JMenuItem("Easy");
		JMenuItem moderateModeMenuItem = new JMenuItem("Moderate");
		JMenuItem hardModeMenuItem = new JMenuItem("Hard");
		menu.add(newMenuItem);
		menu.addSeparator();
		mode.add(easyModeMenuItem);
		mode.addSeparator();
		mode.add(moderateModeMenuItem);
		mode.addSeparator();
		mode.add(hardModeMenuItem);
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);
		menuBar.add(mode);
		ModeFactory modeFactory = ModeFactory.getInstance();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (screen.width * 0.80);
		int y = (int) (screen.height * 0.80);
		final GameController gameController = GameEngine.start("The Egyption King",
				new GameWorld(x, y, new Score(), getPlayerName(), modeFactory.getMode(Difficulty)), menuBar,
				Color.BLACK);
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.changeWorld(
						new GameWorld(x, y, new Score(), getPlayerName(), modeFactory.getMode(Difficulty)));
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		});
		easyModeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController
						.changeWorld(new GameWorld(x, y, new Score(), getPlayerName(), modeFactory.getMode("EASY")));
			}
		});
		moderateModeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.changeWorld(
						new GameWorld(x, y, new Score(), getPlayerName(), modeFactory.getMode("MODERATE")));
			}
		});
		hardModeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController
						.changeWorld(new GameWorld(x, y, new Score(), getPlayerName(), modeFactory.getMode("HARD")));
			}
		});
	}
}

class IntroBackGround extends JPanel {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	public IntroBackGround(int width, int height, String fileName) throws IOException {
		backgroundImage = ImageIO.read(getClass().getResourceAsStream(fileName));
		Image tmp = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		backgroundImage = dimg;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}