package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class Shape implements GameObject {
	private int x;
	private boolean visible;
	private ImageType ballType;
	protected State s;

	public Shape(int x, int y, ImageType ballType) {
		this.x = x;
		this.visible = true;
		this.ballType = ballType;
		this.s = new MovingState(y);
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public void setX(int x) {
		this.x = x;

	}

	@Override
	public int getY() {
		return s.getY();
	}

	@Override
	public void setY(int y) {
			s.setY(y);;
	}

	@Override
	public int getWidth() {
		return this.ballType.getSpriteImages()[0].getWidth();
	}

	@Override
	public int getHeight() {
		return this.ballType.getSpriteImages()[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return this.ballType.getSpriteImages();
	}

	public abstract void move(GameObject k);

	public abstract  State getSate();

	public abstract void setSate(State s);

	public String getName() {
		return this.ballType.getNameImage();
	}

}
