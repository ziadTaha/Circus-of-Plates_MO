package eg.edu.alexu.csd.oop.game.cs15.game.object;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class FlyWeightFactory {
	
	private static final HashMap<String,ImageType> shapes = new HashMap<String,ImageType>();
	private static Logger log = JLogger.getLogInstance() ;
	
	public static ImageType getShape(String path) {
		ImageType shapeImpl = shapes.get(path);
		log.info("creat ball FlyWeightFactory");
		if (shapeImpl == null) {
			shapeImpl = new ImageType(path);
			shapes.put(path, shapeImpl);
			
		}
		return shapeImpl;
	}
	
	
	

}
