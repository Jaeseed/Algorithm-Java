package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_3085_사탕게임 {
	// 우 하 좌 상
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int N;
	static char[][] arr;
	static int[][][] cntTong;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		cntTong = new int[N][N][2];
		int answer = 0;
		for (int n = 0; n < N; n++) {
			String tmp = br.readLine();
			for (int nn = 0; nn < N; nn++) {
				arr[n][nn] = tmp.charAt(nn);
			}
		}
		
		// 가로 연속 수 세기
		for (int r = 0; r < N; r++) {
			int c = 0;
			while(c < N) {
				int ret = dfs(r, c, 0, 1, arr[r][c]);
				answer = Math.max(answer, ret);
				c += ret;
			}
		}
		
		// 세로 연속 수 세기
		for (int c = 0; c < N; c++) {
			int r = 0;
			while(r < N) {
				int ret = dfs(r, c, 1, 1, arr[r][c]);
				answer = Math.max(answer, ret);
				r += ret;
			}
		}
		
		// 자리 이동 및 검사
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < 2; i++) {
					int tr = r + dr[i];
					int tc = c + dc[i];
					if (tr >= N || tc >= N || arr[r][c] == arr[tr][tc]) continue;
					char tmp = arr[r][c];
					arr[r][c] = arr[tr][tc];
					arr[tr][tc] = tmp;
					answer = Math.max(numCheck(r, c, arr[r][c]), answer);
					answer = Math.max(numCheck(tr,tc, arr[tr][tc]), answer);
					arr[tr][tc] = arr[r][c];
					arr[r][c] = tmp;
				}
			}
		}
		
		
		System.out.println(answer);
		

	}
	
	static int dfs(int r, int c, int idx, int cnt, char color) {
		if (r >= N || c >= N || arr[r][c] != color) {
			return cnt - 1;
		}
		int ret = dfs(r + dr[idx], c + dc[idx], idx, cnt + 1, color);
		cntTong[r][c][idx] = ret;
		return ret;
		
	}
	
	static int numCheck(int r, int c, char color) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			int nnr = r + dr[(i + 2) % 4];
			int nnc = c + dc[(i + 2) % 4];
			if (nr >= N || nr < 0 || nc >= N || nc < 0 || arr[nr][nc] != color) continue;
			if (nnr < N && nnr >= 0 && nnc < N && nnc >= 0 && arr[nnr][nnc] == color) {
				cnt = Math.max(cnt, cntTong[nr][nc][i % 2] + 1 + cntTong[nnr][nnc][i % 2]);
			}
			else {
				cnt = Math.max(cnt, cntTong[nr][nc][i % 2] + 1);
			}
		}
		return cnt;
	}

}
