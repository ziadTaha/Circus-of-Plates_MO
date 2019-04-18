package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class testleaderboard {

	public static void main(String[] args) {
		WriteLeaderboard w;
		ReadLeaderboard r;
		SortLeaderboard s;

		String[] playerinfo = {"ahmed", "EASY", "2"};

		LinkedList<PlayerInfo> l1 = new LinkedList<>();
		LinkedList<PlayerInfo> l2 = new LinkedList<>();
		LinkedList<PlayerInfo> l3 = new LinkedList<>();

		Map <String, LinkedList<PlayerInfo>> map = new HashMap<String, LinkedList<PlayerInfo>>();

		map.put("EASY", l1);
		map.put("MOD", l2);
		map.put("HARD", l3);

		 new WriteLeaderboard (new SortLeaderboard(playerinfo, new ReadLeaderboard("Leaderboard.json").getData()).sortedData()).writeData();
	}

}
