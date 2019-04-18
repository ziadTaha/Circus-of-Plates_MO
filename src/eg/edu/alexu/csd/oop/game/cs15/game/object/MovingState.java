package eg.edu.alexu.csd.oop.game.cs15.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public class MovingState implements State {

	private int y;

	public MovingState(int y) {
		this.y = y;
	}

	@Override
	public void move(Shape MyShape, GameObject k) {
		// TODO Auto-generated method stub
		MyShape.setY(MyShape.getY() +1);
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

}
