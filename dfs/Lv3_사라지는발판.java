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
	    boolean[][] visited;
	    int width;
	    int height;
	    int[] dr = {-1,1,0,0};
	    int[] dc = {0,0,-1,1};
	    public int solution(int[][] board, int[] aloc, int[] bloc) {
	        height = board.length;
	        width = board[0].length;
	        visited = new boolean[height][width];
	        
	        game(aloc[0], aloc[1], bloc[0], bloc[1], 0, board);
	        
	        return game(aloc[0], aloc[1], bloc[0], bloc[1], 0, board).getSteps();
	    }
	    
	    public class WinnerInfo {
	        int winner;
	        int steps;
	        
	        WinnerInfo() {
	        }
	        
	        WinnerInfo(int winner, int steps) {
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
	    
	    public WinnerInfo game(int ar, int ac, int br, int bc, int steps, int[][] board) {
	        // 전체 경우에서 이긴 결과와 이겼을 때 가장 길었던 매치를 적용
	        int[][] results = new int[8][2];
	        for (int i = 0; i < 4; i++) {
	            for (int j = 0; j < 4; j++) {
	                board[ar][ac] = board[br][bc] = 1;
	                int nar = ar + dr[i];
	                int nac = ac + dc[i];
	                int nbr = br + dr[j];
	                int nbc = bc + dc[j];
	                // 1.옮길 수가 없어 질 때
	                // b승
	                if (!outCheck(ar, ac, nar,nac,board)) {
	                    winCalcul(results, 1, j, steps);
	                }
	                // a승
	                else if (board[br][bc] == 0 || !outCheck(br, bc, nbr, nbc, board)) {
	                    winCalcul(results, 0, i, steps + 1);
	                }
	                // 같은 공간에 있다가 b가 이동해서 계단이 사라질 때 b승
	                else if (board[nar][nac] == 0) {
	                    winCalcul(results, 1, j, steps + 2);
	                }
	                else {
	                    WinnerInfo info = game(nar, nac, nbr, nbc, steps + 2, board);
	                    if (info.getWinner() == 0) {
	                        winCalcul(results, 0, i, info.getSteps());
	                    }
	                    else if (info.getWinner() == 1) {
	                        winCalcul(results, 1, j, info.getSteps());
	                    }
	                }
	            }
	        }
	        board[ar][ac] = board[br][bc] = 1;
	        int winner = -1;
	        if (steps == 0) {
	        	System.out.println(3);
	        }
	        int retStep = 50;
	        for (int i = 0; i < 8; i++) {
	            if (results[i][0] == 4) {
	                if (i < 4) {
	                    winner = 0;
	                }
	                else {
	                    winner = 1;
	                }
	                if (retStep > results[i][1]) {
	                    retStep = results[i][1];
	                }
	            }
	        }
	        return new WinnerInfo(winner,retStep);
	    }
	    
	    public boolean outCheck(int pr, int pc, int r, int c, int[][] board) {
	        board[pr][pc] = 0;
	        if (r >= height || r < 0 || c >= width || c < 0 || board[r][c] == 0) {
	            return false;
	        }
	        return true;
	    }
	    
	    public void winCalcul(int[][] results, int winner, int dir, int steps) {
	        results[winner * 4 + dir][0] += 1;
	        results[winner * 4 + dir][1] = Math.max(results[winner * 4 + dir][1], steps);
	    }
	}

}
