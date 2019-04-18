package eg.edu.alexu.csd.oop.game.cs15.game.object;

public class SplashThread implements Runnable {

	private ImagePanel splash;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		    splash = new ImagePanel();
			splash.play();
	}
    public void stop()
    {
    	splash.close();
    }

}
