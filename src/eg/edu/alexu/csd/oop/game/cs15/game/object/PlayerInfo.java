package eg.edu.alexu.csd.oop.game.cs15.game.object;

public class PlayerInfo {

	private String name;
	private String score;

	public PlayerInfo(String name, String string) {
		this.name = name;
		this.score = string;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getScore() {
		return this.score;
	}

}
