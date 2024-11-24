package pckg;

public class PlayerScore {
	private int id_Player;
	private int score;
	
	public PlayerScore(int id, int score) {
		this.id_Player = id;
		this.score = score;
	}
	
	public PlayerScore(int id) {
		this.id_Player = id;
		this.score = 0;
	}
	
	
	public int getScore() {
		return this.score;
	}
	
	public int getId() {
		return this.id_Player; 
	}
	
	public String toString() {
		return "Player" + this.id_Player + " , Score : "+this.score+"\n";
	}

}
