package eg.edu.alexu.csd.oop.game.cs15.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public class StopStateLeft implements State{

	private int  y; 
	
	 public StopStateLeft(int y) {
		// TODO Auto-generated constructor stub
		 this.y = y;
	}
	@Override
	public void move(Shape Myshape, GameObject k) {
		// TODO Auto-generated method stub
		Myshape.setX( k.getX() + k.getWidth() - Myshape.getWidth() );
	}



	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
	}



	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

}
