/**
 * This class implements all methods needed by algorithm computerPlay
 * @author Serena Hou 251015235
 */

public class nk_TicTacToe {
	int board_size, inline, max_levels;
	private char[][] gameBoard;
	
	public nk_TicTacToe (int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		gameBoard = new char [board_size][board_size];
		for(int i = 0; i < board_size; i++){
            for(int j = 0; j < board_size; j++){
                gameBoard[i][j]=' ';
            }
        }
	}
	
	public Dictionary createDictionary() {
		return new Dictionary(board_size);
	}
	
	public int repeatedConfig(Dictionary configurations) {
		String converted = "";
		for(int i = 0; i < board_size; i++){
            for(int j = 0; j < board_size; j++){
                converted+=gameBoard[i][j];
            }
        }
        if(configurations.get(converted) != -1){
            return configurations.get(converted);
        }
        return -1;
	}
	
	public void insertConfig(Dictionary configurations, int score) {
		String converted = "";
        for(int i = 0; i < board_size; i++){
            for(int j = 0; j < board_size; j++){
                converted+=gameBoard[i][j];
            }
        }
		configurations.insert(new Record(converted, score));
	}
	
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean wins(char symbol) {
		if (rows(symbol) || columns(symbol) || diagonals(symbol)) {// if there are wins in any direction, return true
			return true;
		}
		return false;
	}
	
    private boolean rows(char symbol){
        for(int i = 0; i < board_size; i++){
            int total=0;
            for(int j = 0; j < board_size; j++){
                if(gameBoard[i][j]!=symbol){ 
                    total=0;
                }
                if(gameBoard[i][j]==symbol){
                    total++;
                }
                if(total>=inline){
                    return true;
                }
            }

        }
        return false;
    }
    
    private boolean columns(char symbol){
        for(int i = 0; i < board_size; i++){
            int total=0;
            for(int j = 0; j < board_size; j++){
                if(gameBoard[j][i]!=symbol){
                    total=0;
                }
                if(gameBoard[j][i]==symbol){
                    total++;
                }
                if(total>=inline){
                    return true;
                }
            }

        }
        return false;
    }

    private boolean diagonals(char symbol){
        for(int x = 0; x < board_size; x++){
            for(int y = 0; y < board_size; y++){
                int total = 0;
                for(int i = x, j = y; i < board_size && j < board_size; i++, j++){
                    if(gameBoard[i][j]!= symbol){
                        total=0;
                    }
                    if(gameBoard[i][j]== symbol){
                        total++;
                    }
                    if(total>=inline){
                        return true;
                    }
                }
            }
        }
        for(int x = 0; x < board_size; x++){
            for(int y = 0; y < board_size; y++){
                int total = 0;
                for(int i = x, j = y; i >= 0 && j < board_size; i--, j++){
                    if(gameBoard[i][j]!= symbol){
                        total=0;
                    }
                    if(gameBoard[i][j]== symbol){
                        total++;
                    }
                    if(total>=inline){
                        return true;
                    }
                }
            }
        }
        return false;
    }
	
	public boolean isDraw() {
		if(!wins('O') && !wins('X')){
            for(int i = 0; i < board_size; i++){
                for(int j = 0; j < board_size; j++){
                    if(gameBoard[i][j] == ' '){// checks for empty space
                        return false;
                    }
                }
            }
        }
		else{
            return false;
        }
        return true;
	}
	
	public int evalBoard() {
        if(wins('X')){
            return 0;
        }
		if(wins('O')){
            return 3;
        }
        if(isDraw()){
            return 2;
        }
        return 1;
	}
}
