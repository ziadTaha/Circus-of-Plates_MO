package eg.edu.alexu.csd.oop.game.cs15.game.object;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class JLogger {
	private static Logger Log = Logger.getLogger(JLogger.class);

	private JLogger() {
		Log = Logger.getLogger(JLogger.class);
		PropertyConfigurator.configure("log4j.properties");
	}

	public static Logger getLogInstance() {
		if (Log == null) {
			new JLogger();
			return JLogger.Log;
		}
		return Log;
	}

}
