package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteLeaderboard {

	private Map <String , LinkedList<PlayerInfo>> data;

	public WriteLeaderboard(Map <String, LinkedList<PlayerInfo>> data) {
		this.data = data;
	}

	
	public void writeData() {
		String path = "Leaderboard.json";
		File x = new File(path);

		if (x.exists()) {
			x.delete();
			try {
				x.createNewFile();
			} catch (IOException e) {

			}
		} else {
			try {
				x.createNewFile();
			} catch (IOException e) {

			}
		}

		JSONObject obj = new JSONObject();
		JSONArray namesEasy = new JSONArray();
		JSONArray scoreEasy = new JSONArray();
		JSONArray namesMod = new JSONArray();
		JSONArray scoreMod = new JSONArray();
		JSONArray namesHard = new JSONArray();
		JSONArray scoreHard = new JSONArray();

		obj.put("namesEasy", namesEasy);
		obj.put("scoreEasy", scoreEasy);
		obj.put("namesMod", namesMod);
		obj.put("scoreMod", scoreMod);
		obj.put("namesHard", namesHard);
		obj.put("scoreHard", scoreHard);


		try (FileWriter file = new FileWriter(path)) {

			file.write(obj.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.data.get("EASY").size(); i++) {
			namesEasy.add(this.data.get("EASY").get(i).getName());
			scoreEasy.add(this.data.get("EASY").get(i).getScore());
		}

		for (int i = 0; i < this.data.get("MOD").size(); i++) {
			namesMod.add(this.data.get("MOD").get(i).getName());
			scoreMod.add(this.data.get("MOD").get(i).getScore());
		}

		for (int i = 0; i < this.data.get("HARD").size(); i++) {
			namesHard.add(this.data.get("HARD").get(i).getName());
			scoreHard.add(this.data.get("HARD").get(i).getScore());
		}

		obj.put("namesEasy", namesEasy);
		obj.put("scoreEasy", scoreEasy);
		obj.put("namesMod", namesMod);
		obj.put("scoreMod", scoreMod);
		obj.put("namesHard", namesHard);
		obj.put("scoreHard", scoreHard);

		try (FileWriter file = new FileWriter(path)) {

			file.write(obj.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
