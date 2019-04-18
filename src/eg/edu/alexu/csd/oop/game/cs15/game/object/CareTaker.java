package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.util.ArrayList;
import java.util.List;


public class CareTaker extends Observer {
	private List<Memento> mList = new ArrayList<>();

	public CareTaker(Score scoreC) {
		// TODO Auto-generated constructor stub
		
		this.scoreC = scoreC;
		scoreC.attach(this);
	}

	public void add(Memento memento) {
		mList.add(memento);
	}

	public Memento get(int index) {
		return mList.get(index);

	}

	@Override
	public void updateR() {
		// TODO Auto-generated method stub
		mList.clear();
		Originator originator = new Originator();
		originator.setStateLeft(scoreC.getL().size());
		originator.setStateRight(scoreC.getR().size());
		// UpdatedX=ordinaryX;
		add(originator.saveToMemento());

	}

	@Override
	public void updateL() {
		// TODO Auto-generated method stub
		mList.clear();
		mList.clear();
		Originator originator = new Originator();
		originator.setStateLeft(scoreC.getL().size());
		originator.setStateRight(scoreC.getR().size());
		// UpdatedX=ordinaryX;
		add(originator.saveToMemento());

	}
	
}
