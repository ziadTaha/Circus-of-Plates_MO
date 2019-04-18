package eg.edu.alexu.csd.oop.game.cs15.game.object;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class BackgroundMusic {
	public void playSong() {
		do {
			try {
				AdvancedPlayer player = new AdvancedPlayer(getClass().getResourceAsStream("/backSong.mp3"));
				player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		} while (true);

	}

}
