package eg.edu.alexu.csd.oop.game.cs15.game.world;

import eg.edu.alexu.csd.oop.game.cs15.Strategy;

public class ModeFactory {
	
	
	
	public Strategy getMode (String modeTybe) {
		
		if (modeTybe.equalsIgnoreCase("EASY"))
			return new EasyMode();
		
		else if (modeTybe.equalsIgnoreCase("MODERATE"))
			return new ModerateMode();
		
		else if (modeTybe.equalsIgnoreCase("HARD"))
			return new HardMode();
		
		else 
			return null;
	
}
	
	private static ModeFactory instance = new ModeFactory();
	
	private ModeFactory() {}
	
	public static ModeFactory getInstance() {
		return instance;
	}
}
