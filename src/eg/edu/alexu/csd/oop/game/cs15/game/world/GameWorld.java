package eg.edu.alexu.csd.oop.game.cs15.game.world;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.cs15.game.object.Container;
import eg.edu.alexu.csd.oop.game.cs15.game.object.DynamicJarReader;
import eg.edu.alexu.csd.oop.game.cs15.game.object.FacadeLeaderboard;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs15.game.object.Shape;
import eg.edu.alexu.csd.oop.game.cs15.game.object.StopStateLeft;
import eg.edu.alexu.csd.oop.game.cs15.game.object.StopStateRight;
import eg.edu.alexu.csd.oop.game.cs15.Strategy;
import eg.edu.alexu.csd.oop.game.cs15.game.object.AddLeftCommand;
import eg.edu.alexu.csd.oop.game.cs15.game.object.AddRightCommand;
import eg.edu.alexu.csd.oop.game.cs15.game.object.CareTaker;
import eg.edu.alexu.csd.oop.game.cs15.game.object.Clown;
import eg.edu.alexu.csd.oop.game.cs15.game.object.CommandManager;
import eg.edu.alexu.csd.oop.game.cs15.game.object.ConstantBackground;
import eg.edu.alexu.csd.oop.game.cs15.game.object.FlyWeightFactory;
import eg.edu.alexu.csd.oop.game.cs15.game.object.GameObjectContainer;
import eg.edu.alexu.csd.oop.game.cs15.game.object.ImageType;
import eg.edu.alexu.csd.oop.game.cs15.game.object.Iterator;
import eg.edu.alexu.csd.oop.game.cs15.game.object.JLogger;
import eg.edu.alexu.csd.oop.game.cs15.game.object.Observer;
import eg.edu.alexu.csd.oop.game.cs15.game.object.Score;

public class GameWorld extends Observer implements World {

	private static int MAX_TIME; // 1 minute
	private int score = 0;
	private long startTime = System.currentTimeMillis();
	private int width;
	private int height;
	private int right;
	private CommandManager cm;
	private int left;
	private final LinkedList<GameObject> constant = new LinkedList<GameObject>();
	private final LinkedList<GameObject> moving = new LinkedList<GameObject>();
	private LinkedList<GameObject> control = new LinkedList<GameObject>();
	private DynamicJarReader jar = DynamicJarReader.getInstance("lib/test.jar");
	private Constructor<?> co;
	private String paths[] = jar.getImagesName();
	private LinkedList<GameObject> leftobject;
	private LinkedList<GameObject> rightobject;
	private CareTaker careTaker;
	private int lives;
	private Strategy strategy;
	private boolean timeout;
	private String name;
	private FacadeLeaderboard facadeLeaderboard;
	private Logger log = JLogger.getLogInstance();

	public String getRandom(String[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public GameWorld(int screenWidth, int screenHeight, Score scoreC, String name, Strategy strategy) {
		this.strategy = strategy;
		this.name = name;
		width = screenWidth;
		height = screenHeight;
		lives = strategy.getLives();
		MAX_TIME = strategy.getTime() * 1000;
		leftobject = new LinkedList<>();
		rightobject = new LinkedList<>();
		try {
			co = jar.getShapeClass().getConstructor(Integer.TYPE, Integer.TYPE, ImageType.class);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethod");
			e.printStackTrace();
		} catch (SecurityException e) {
			log.error("SecurityException");
			e.printStackTrace();
		}
		control.add(new Clown(screenWidth, screenHeight, "/moSalah.png"));
		right = left = height - control.get(0).getHeight();
		for (int i = 0; i < 10; i++) {
			try {
				moving.add((GameObject) co.newInstance((int) (Math.random() * screenWidth),
						-1 * (int) (Math.random() * screenHeight), FlyWeightFactory.getShape(getRandom(paths))));
			} catch (InstantiationException e) {
				log.error("error in creat balls in moving");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				log.error("error in creat balls in moving");
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				log.error("error in creat balls in moving");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				log.error("error in creat balls in moving");
				e.printStackTrace();
			}
		}
		constant.add(new ConstantBackground(screenWidth, screenHeight, "/st.jpg"));
		this.scoreC = scoreC;
		scoreC.attach(this);
		scoreC.setR(rightobject);
		scoreC.setL(leftobject);
		scoreC.setControl(control);
		cm = new CommandManager(this.scoreC);
		careTaker = new CareTaker(this.scoreC);
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return this.constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		if (this.lives == 0) {
			this.moving.clear();
		}
		return this.moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return this.control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean refresh() {
		timeout = System.currentTimeMillis() - startTime > MAX_TIME;
		GameObject c = control.get(0);
		right = left = height - control.get(0).getHeight();
		if (rightobject.size() > 0) {
			right -= rightobject.size() * rightobject.get(0).getHeight();
		}
		if (leftobject.size() > 0) {
			left -= leftobject.size() * leftobject.get(0).getHeight();
		}
		Container mContainer = new GameObjectContainer(moving);
		for (Iterator iter = mContainer.getIterator(); iter.hasNext();) {
			GameObject m = (GameObject) iter.next();
			Shape l = (Shape) m;
			l.move(c);
			if (m.getY() == getHeight()) {
				m.setY(-1 * (int) (Math.random() * getHeight()));
				m.setX((int) (Math.random() * getWidth()));
				log.info("reuse the ball");
			}
			Checkintersection(l, c);
		}
		Container cContainer = new GameObjectContainer(control);
		Iterator iter = cContainer.getIterator();
		for (iter.next(); iter.hasNext();) {
			Shape l = (Shape) iter.next();
			l.move(c);
		}
		if (timeout == true) {
			if (lives > 0) {
				if (score > 0) {
					// trimming left
					log.info("check point");
					int x = leftobject.size();
					int y = rightobject.size();
					for (int i = 0; i < x - careTaker.get(0).getStateLeft(); i++) {
						control.remove(leftobject.peekLast());
						leftobject.removeLast();
					}
					for (int i = 0; i < y - careTaker.get(0).getStateRight(); i++) {
						control.remove(rightobject.peekLast());
						rightobject.removeLast();
					}

				} else {
					leftobject.clear();
					rightobject.clear();
					control.clear();
					control.add(c);
				}
				lives--;
				startTime = System.currentTimeMillis();
				timeout = false;
				if (lives == 0) {
					String[] playerInfo = { this.name, this.strategy.getStrategyName(), String.valueOf(this.score) };
					facadeLeaderboard = new FacadeLeaderboard(playerInfo);
					facadeLeaderboard.showLeaderboard();
				}
			}
		}
		return !timeout && lives > 0;
	}

	private void Checkintersection(Shape m, GameObject c) {
		if ((Math.abs((m.getX() + m.getWidth() / 2) - (c.getX() + c.getWidth() / 2)) <= c.getWidth() / 2)
				&& (Math.abs((m.getX() + m.getWidth() / 2) - (c.getX() + c.getWidth() / 2)) >= c.getWidth() / 2
						- m.getWidth())) {
			if ((c.getX() + c.getWidth() / 2) < (m.getX() + m.getWidth() / 2)) {
				if (Math.abs((m.getY() + m.getHeight() / 2) - right) <= m.getHeight() / 2) {
					log.info("intersection");
					m.setX(c.getX() + c.getWidth() - m.getWidth());
					m.setY(right);
					m.setSate(new StopStateLeft(m.getY()));
					cm.executeRightCommand(new AddRightCommand(rightobject, m, control));
					this.scoreC.setScoreR();
					if (m.getName().contains("ramos")) {
						timeout = true;
					}
					moving.remove(m);
					new FlyWeightFactory();
					try {
						moving.add((GameObject) co.newInstance((int) (Math.random() * width),
								-1 * (int) (Math.random() * height), FlyWeightFactory.getShape(getRandom(paths))));
						log.info("reuse");
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						log.error("error in reuse");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// careTaker.setOrdinaryX(c.getX());
				}

			} else {
				if (Math.abs((m.getY() + m.getHeight() / 2) - left) <= m.getHeight() / 2) {
					log.info("intersection");
					m.setX(c.getX());
					m.setY(left);
					m.setSate(new StopStateRight(m.getY()));
					cm.executeLeftCommand(new AddLeftCommand(leftobject, m, control));
					if (m.getName().contains("ramos")) {
						timeout = true;
					}
					this.scoreC.setScoreR();
					moving.remove(m);
					new FlyWeightFactory();
					try {
						moving.add((GameObject) co.newInstance((int) (Math.random() * width),
								-1 * (int) (Math.random() * height), FlyWeightFactory.getShape(getRandom(paths))));
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// careTaker.setOrdinaryX(c.getX());
				}

			}
		}

	}

	@Override
	public String getStatus() {
		return "Score=" + score + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000) + "   |   Lives=" + lives;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return this.strategy.getSpeed();
	}

	@Override
	public int getControlSpeed() {
		// TODO Auto-generated method stub
		return this.strategy.getControlSpeed();
	}

	@Override
	public void updateR() {

		this.score++;
	}

	@Override
	public void updateL() {
		this.score++;
	}

	public int getLives() {
		return this.lives;
	}

}
