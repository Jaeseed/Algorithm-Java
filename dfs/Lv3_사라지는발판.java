package dfs;

public class Lv3_사라지는발판 {

	public static void main(String[] args) {
		Solution dd = new Solution();
		int[][] a = {{1,1,1}, {1,1,1}, {1,1,1}};
		int[] b = {1,0};
		int[] c = {2,2};
		System.out.println(dd.solution(a,b,c));

	}
	static class Solution {
	    int width;
	    int height;
	    int[] dr = {-1,1,0,0};
	    int[] dc = {0,0,-1,1};
	    public int solution(int[][] board, int[] aloc, int[] bloc) {
	        height = board.length;
	        width = board[0].length;
	        int[][] loc = new int[2][2];
	        loc[0] = aloc;
	        loc[1] = bloc;
	        return game(loc, 0, 0, board).getSteps();
	    }
	    
	    public Info game(int[][] loc, int player, int steps, int[][] board) {
	        int r = loc[player][0];
	        int c = loc[player][1];
	        if (board[r][c] == 0) {
	            return new Info((player + 1) % 2, steps);
	        }
	        boolean isWin = false;
	        boolean isBlocked = true;
	        int minStep = 50;
	        int maxStep = 0;
	        for (int i = 0; i < 4; i++) {
	            int nr = r + dr[i];
	            int nc = c + dc[i];
	            if (nr >= height || nr < 0 || nc >= width || nc < 0 || board[nr][nc] == 0) {
	                continue;
	            }
	            isBlocked = false;
	            loc[player][0] = nr;
	            loc[player][1] = nc;
	            board[r][c] = 0;
	            Info now = game(loc, (player + 1) % 2, steps + 1, board);
	            if (now.getWinner() == player) {
	                isWin = true;
	                minStep = Math.min(now.getSteps(), minStep);
	            }
	            else {
	                maxStep = Math.max(now.getSteps(), maxStep);
	            }
	            board[r][c] = 1;
	            loc[player][0] = r;
	            loc[player][1] = c;
	        }
	        if (isBlocked) {
	            return new Info((player + 1) % 2, steps);
	        }
	        if (isWin) {
	            return new Info(player, minStep);
	        }
	        return new Info((player + 1) % 2, maxStep);
	    }
	    
	}

	static class Info {
	    int winner;
	    int steps;

	    Info() {
	    }

	    Info(int winner, int steps) {
	        this.winner = winner;
	        this.steps = steps;
	    }

	    public void setInfo(int winner, int steps) {
	        this.winner = winner;
	        this.steps = steps;
	    }

	    public int getWinner() {
	        return winner;
	    }
	    public int getSteps() {
	        return steps;
	    }
	}

}
