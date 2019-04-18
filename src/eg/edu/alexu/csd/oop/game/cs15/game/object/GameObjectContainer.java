package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;

public class GameObjectContainer implements Container {
	private LinkedList<GameObject> objects;
	private Logger log = JLogger.getLogInstance();

	public GameObjectContainer(LinkedList<GameObject> objects) {
		this.objects = objects;
	}

	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		
		return new GameObjectIterator();
	}

	private class GameObjectIterator implements Iterator {
		int index;

		@Override
		public boolean hasNext() {
			if (index < objects.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (hasNext()) {
				return objects.get(index++);
			}
			log.warn("noNext");
			return null;
		}

	}
}
