/**
 * This class associates a configuration with an integer score to make an entry in the dictionary
 * @author Serena Hou 251015235
 */

public class Record {
	private String config;
	private int score;
	
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}

	public String getConfig() {
		return this.config;
	}
	
	public int getScore() {
		return this.score;
	}
}