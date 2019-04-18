package eg.edu.alexu.csd.oop.game.cs15.game.object;

public class Memento {
	private int stateLeft;
	private int stateRight;


	public Memento(int stateLeft,int stateRight) {
		this.stateLeft = stateLeft;
		this.stateRight = stateRight;

	}

	public int getStateLeft() {
		return stateLeft;

	}

	public int getStateRight() {
		return stateRight;

	}


}
