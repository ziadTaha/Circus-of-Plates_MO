package eg.edu.alexu.csd.oop.game.cs15.game.object;

public class FacadeLeaderboard {
	
	private String[] playerInfo;
	private ReadLeaderboard read;
	private SortLeaderboard sort;
	private WriteLeaderboard write;
	private EmptyFile empty;
	private ShowingLeaderBoard show;
	
	
	public FacadeLeaderboard(String [] playerInfo) {
		this.playerInfo = playerInfo;
	}
	
	public void showLeaderboard() {
		
		read = new ReadLeaderboard("Leaderboard.json");
		
		if (read.checkerFile()) {
			sort = new SortLeaderboard(playerInfo, read.getData());
			show = new ShowingLeaderBoard(this.playerInfo, sort.sortedData().get(this.playerInfo[1]));
			show.show();
			write = new WriteLeaderboard(sort.sortedData());
			write.writeData();
		} else {
			empty = new EmptyFile();
			empty.write();
			sort = new SortLeaderboard(playerInfo, read.getData());
			show = new ShowingLeaderBoard(this.playerInfo, sort.sortedData().get(this.playerInfo[1]));
			show.show();
			write = new WriteLeaderboard(sort.sortedData());
			write.writeData();
		}
		
	}

}
