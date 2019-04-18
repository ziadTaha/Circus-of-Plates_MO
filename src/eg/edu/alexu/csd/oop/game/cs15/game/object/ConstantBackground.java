package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ConstantBackground implements GameObject {

	private int x, y;
	private boolean visible;
	private static final int MAX_MSTATE = 1;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];

	public ConstantBackground(int width, int height, String path) {
		this.visible = true;

		// create a bunch of buffered images and place into an array, to be displayed
		// sequentially
		try {
			spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
			this.x = 0;
			this.y = 0;
			Image tmp = spriteImages[0].getScaledInstance(width, height, Image.SCALE_SMOOTH);
		    BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = dimg.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
		    spriteImages[0] = dimg;
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		return this.y;
	}

	@Override
	public void setY(int y) {
		this.y = y;

	}

	@Override
	public int getWidth() {
		return this.spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return this.spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return this.spriteImages;
	}

}
