package eg.edu.alexu.csd.oop.game.cs15.game.world;

import eg.edu.alexu.csd.oop.game.cs15.Strategy;

public class ModerateMode implements Strategy{

	@Override
	public int getSpeed() {
		return 5;
	}

	@Override
	public int getControlSpeed() {
		return 30;
	}

	@Override
	public String getStrategyName() {
		return "MOD";
	}

	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 40;
	}
}
