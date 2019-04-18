package eg.edu.alexu.csd.oop.game.cs15.game.object;

public class SoundThread implements Runnable{

	@Override
	public void run() {
		BackgroundMusic bm = new BackgroundMusic();
		bm.playSong();

	}

}
