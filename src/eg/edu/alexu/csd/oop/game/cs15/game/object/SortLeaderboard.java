package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.util.LinkedList;
import java.util.Map;

public class SortLeaderboard {

	private String[] playerData;
	private Map<String, LinkedList<PlayerInfo>> map;

	public SortLeaderboard (String[] playerData, Map<String, LinkedList<PlayerInfo>> map) {
		this.playerData = playerData;
		this.map = map;
	}


	public Map<String, LinkedList<PlayerInfo>> sortedData(){

		int counter = 0;

		

		if (this.playerData[1].equals("EASY")) {
			counter = 0;
			for (int i = 0; i < map.get("EASY").size(); i++) {
					if (map.get("EASY").get(i).getName().equals(this.playerData[0])) {
						counter ++;
						if (counter == 1) {
							map.get("EASY").get(i).setScore(playerData[2]);
						}
					}
			}

			if (counter == 0) {


				map.get("EASY").add(new PlayerInfo(this.playerData[0], this.playerData[2]));
			}

			 int n = map.get("EASY").size();
		        for (int j = 1; j < n; ++j)
		        {
		            int key = Integer.parseInt(map.get("EASY").get(j).getScore());
		            int z = j-1;

		            /* Move elements of arr[0..i-1], that are
		               greater than key, to one position ahead
		               of their current position */
		            while (z>=0 && Integer.parseInt(map.get("EASY").get(z).getScore()) > key)
		            {
		            	String temp1, temp2;
		            	temp1 = map.get("EASY").get(z + 1).getScore();
		            	temp2 = map.get("EASY").get(z + 1).getName();
		            	map.get("EASY").get(z + 1).setScore(map.get("EASY").get(z).getScore());
		            	map.get("EASY").get(z + 1).setName(map.get("EASY").get(z).getName());
		            	map.get("EASY").get(z).setScore(temp1);
		            	map.get("EASY").get(z).setName(temp2);

		                z = z-1;
		            }
		            key = Integer.parseInt(map.get("EASY").get(z+1).getScore());
		           // arr[j+1] = key;
		        }

		       
		        return map;

		} else if (this.playerData[1].equals("MOD")) {

			counter = 0;
			for (int i = 0; i < map.get("MOD").size(); i++) {
					if (map.get("MOD").get(i).getName().equals(this.playerData[0])) {
						counter ++;
						if (counter == 1) {
							map.get("MOD").get(i).setScore(playerData[2]);
						}
					}
			}

			if (counter == 0) {
				map.get("MOD").add(new PlayerInfo(this.playerData[0], this.playerData[2]));
			}
			 int n = map.get("MOD").size();
		        for (int j = 1; j < n; ++j)
		        {
		            int key = Integer.parseInt(map.get("MOD").get(j).getScore());
		            int z = j-1;

		            /* Move elements of arr[0..i-1], that are
		               greater than key, to one position ahead
		               of their current position */
		            while (z>=0 && Integer.parseInt(map.get("MOD").get(z).getScore()) > key)
		            {
		            	String temp1, temp2;
		            	temp1 = map.get("MOD").get(z + 1).getScore();
		            	temp2 = map.get("MOD").get(z + 1).getName();
		            	map.get("MOD").get(z + 1).setScore(map.get("MOD").get(z).getScore());
		            	map.get("MOD").get(z + 1).setName(map.get("MOD").get(z).getName());
		            	map.get("MOD").get(z).setScore(temp1);
		            	map.get("MOD").get(z).setName(temp2);
		                z = z-1;
		            }
		            key = Integer.parseInt(map.get("MOD").get(z+1).getScore());
		           // arr[j+1] = key;
		        }

		        return map;

		} else if (this.playerData[1].equals("HARD")) {

			counter = 0;
			for (int i = 0; i < map.get("HARD").size(); i++) {
					if (map.get("HARD").get(i).getName().equals(this.playerData[0])) {
						counter ++;
						if (counter == 1) {
							map.get("HARD").get(i).setScore(playerData[2]);
						}
					}
			}

			if (counter == 0) {
				map.get("HARD").add(new PlayerInfo(this.playerData[0], this.playerData[2]));
			}
			 int n = map.get("HARD").size();
		        for (int j = 1; j < n; ++j)
		        {
		            int key = Integer.parseInt(map.get("HARD").get(j).getScore());
		            int z = j-1;

		            /* Move elements of arr[0..i-1], that are
		               greater than key, to one position ahead
		               of their current position */
		            while (z>=0 && Integer.parseInt(map.get("HARD").get(z).getScore()) > key)
		            {
		            	String temp1, temp2;
		            	temp1 = map.get("HARD").get(z + 1).getScore();
		            	temp2 = map.get("HARD").get(z + 1).getName();
		            	map.get("HARD").get(z + 1).setScore(map.get("HARD").get(z).getScore());
		            	map.get("HARD").get(z + 1).setName(map.get("HARD").get(z).getName());
		            	map.get("HARD").get(z).setScore(temp1);
		            	map.get("HARD").get(z).setName(temp2);
		                z = z-1;
		            }
		            key = Integer.parseInt(map.get("HARD").get(z+1).getScore());
		           // arr[j+1] = key;
		        }

		        return map;

		} else {
			return null;
		}
	}

}
