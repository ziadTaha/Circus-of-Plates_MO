package eg.edu.alexu.csd.oop.game.cs15.game.object;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadLeaderboard {

	private String path;

	public ReadLeaderboard(String path) {
		this.path = path;
	}
	
	public boolean checkerFile() {
		File file = new File(this.path);
		
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public Map<String, LinkedList<PlayerInfo>> getData(){

		LinkedList<PlayerInfo> easy = new LinkedList<>();
		LinkedList<PlayerInfo> mod = new LinkedList<>();
		LinkedList<PlayerInfo> hard = new LinkedList<>();
		Map<String, LinkedList<PlayerInfo>> data = new HashMap<>();
		JSONParser parser = new JSONParser();

		Object obj = null;
		try {
			obj = parser.parse(new FileReader(path));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray col1 = (JSONArray) jsonObject.get("namesEasy");
		JSONArray col2 = (JSONArray) jsonObject.get("scoreEasy");
		JSONArray col3 = (JSONArray) jsonObject.get("namesMod");
		JSONArray col4 = (JSONArray) jsonObject.get("scoreMod");
		JSONArray col5 = (JSONArray) jsonObject.get("namesHard");
		JSONArray col6 = (JSONArray) jsonObject.get("scoreHard");


		Iterator<String> iterator1 = col1.iterator();
		Iterator<String> iterator2 = col2.iterator();
		Iterator<String> iterator3 = col3.iterator();
		Iterator<String> iterator4 = col4.iterator();
		Iterator<String> iterator5 = col5.iterator();
		Iterator<String> iterator6 = col6.iterator();

		while (iterator1.hasNext() && iterator2.hasNext()) {
			easy.add(new PlayerInfo(iterator1.next(), iterator2.next()));
		}

		while (iterator3.hasNext() && iterator4.hasNext()) {
			mod.add(new PlayerInfo(iterator3.next(), iterator4.next()));
		}

		while (iterator5.hasNext() && iterator6.hasNext()) {
			hard.add(new PlayerInfo(iterator5.next(), iterator6.next()));
		}

		data.put("EASY", easy);
		data.put("MOD", mod);
		data.put("HARD", hard);

		return data;

	}

}
