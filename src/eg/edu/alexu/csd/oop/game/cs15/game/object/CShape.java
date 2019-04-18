package eg.edu.alexu.csd.oop.game.cs15.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public class CShape extends Shape{

	public CShape(int x, int y, ImageType ballType) {
		super(x, y, ballType);
	}

	@Override
	public void move(GameObject k) {
		this.s.move(this, k);
	}

	@Override
	public State getSate() {
		return this.s;
	}

	@Override
	public void setSate(State s) {
		this.s = s;
	}

}
