package eg.edu.alexu.csd.oop.game.cs15.game.object;

public class Originator {
	private int stateLeft;
	private int stateRight;

	public int getStateLeft() {
		return stateLeft;
	}

	public void setStateLeft(int stateLeft) {
		this.stateLeft = stateLeft;
	}

	public int getStateRight() {
		return stateRight;
	}

	public void setStateRight(int stateRight) {
		this.stateRight = stateRight;
	}

	public Memento saveToMemento() {
		return new Memento(stateLeft, stateRight);
	}

	public void getStateFromMemento(Memento memento) {
		stateLeft = memento.getStateLeft();
		stateRight = memento.getStateRight();
	}



}
