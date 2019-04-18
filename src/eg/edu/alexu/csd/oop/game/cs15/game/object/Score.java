package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Score {

	private List<Observer> observers = new ArrayList<Observer>();
	private int score = 0;
	private int scoreR = 0;
	private int scoreL = 0;


	private LinkedList<GameObject>R;
	private LinkedList<GameObject>L;
	private LinkedList<GameObject>Control;


	public void setL(LinkedList<GameObject>L) {
		this.L=L;
	}
	public void setR(LinkedList<GameObject>R) {
		this.R=R;
	}
	public void setControl(LinkedList<GameObject>R) {
		this.Control=R;
	}
	public LinkedList<GameObject> getR(){
		return R;
	}
	public LinkedList<GameObject> getL(){
		return L;
	}
	public LinkedList<GameObject> getControl(){
		return Control;
	}
	public int getScore() {
		return score;
	}
	public int getScoreR() {
		return scoreR;
	}
	public int getScoreL() {
		return scoreL;
	}

	public void setScoreR() {
		int scoreR;
		int index = R.size()-1;
		scoreR = 0;
		if (index>=2) {

		if(((Shape)R.get(index)).getName().contains("Black") && ((Shape)R.get(index-1)).getName().contains("Black") && ((Shape)R.get(index-2)).getName().contains("Black")) {

			scoreR++;}
		if(((Shape)R.get(index)).getName().contains("Blue") && ((Shape)R.get(index-1)).getName().contains("Blue") && ((Shape)R.get(index-2)).getName().contains("Blue")) {

			scoreR++;
		}

		if(((Shape)R.get(index)).getName().contains("Purple") && ((Shape)R.get(index-1)).getName().contains("Purple") && ((Shape)R.get(index-2)).getName().contains("Purple")) {

			scoreR++;
		}

		}
		if(scoreR == 1) {
			this.scoreR = scoreR;
			score++;
			notifyAllObserversR();
		}
		int scoreL;
		int indexL = L.size()-1;
		scoreL = 0;
		if (indexL>=2) {
			if(((Shape)L.get(indexL)).getName().contains("Black") && ((Shape)L.get(indexL-1)).getName().contains("Black") && ((Shape)L.get(indexL-2)).getName().contains("Black")) {
				scoreL++;}
			if(((Shape)L.get(indexL)).getName().contains("Blue") && ((Shape)L.get(indexL-1)).getName().contains("Blue") && ((Shape)L.get(indexL-2)).getName().contains("Blue")) {
				
				scoreL++;}
			if(((Shape)L.get(indexL)).getName().contains("Purple") && ((Shape)L.get(indexL-1)).getName().contains("Purple") && ((Shape)L.get(indexL-2)).getName().contains("Purple")) {
				
				scoreL++;}
			}
		if(scoreL == 1) {
			this.scoreL = scoreL;
			score++;
			notifyAllObserversL();
		}
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	public void notifyAllObserversR(){
	      for (Observer observer : observers) {
	         observer.updateR();
	      }
	}
	private void notifyAllObserversL() {
		 for (Observer observer : observers) {
	         observer.updateL();
	      }

	}

}
