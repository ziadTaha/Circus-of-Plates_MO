package eg.edu.alexu.csd.oop.game.cs15.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface State {

	public void move(Shape Myshape, GameObject k);

	public void setY(int y);
	
	public int getY();

}
