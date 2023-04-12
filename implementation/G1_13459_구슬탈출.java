package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_13459_구슬탈출 {
	static char[][] arr;
	static int N;
	static int M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bfr.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		// 파란, 빨간 구슬의 r, c값
		int br = 0;
		int bc = 0;
		int rr = 0;
		int rc = 0;
		for (int n = 0; n < N; n++) {
			String tmp = bfr.readLine();
			for (int m = 0; m < M; m++) {
				arr[n][m] = tmp.charAt(m);
				if (arr[n][m] == 'R') {
					rr = n;
					rc = m;
				}
				if (arr[n][m] == 'B') {
					br = n;
					bc = m;
				}
			}
		}
		if (dfs(0,rr,rc,br,bc)) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		
	}
	
	static boolean dfs(int cnt, int rr, int rc, int br, int bc) {
		if (cnt == 10) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			Info rInfo = gravity(rr + dr[i], rc + dc[i], i);
			Info bInfo = gravity(br + dr[i], bc + dc[i], i);
			if (rInfo.r == rr && bInfo.r == br && rInfo.c == rc && bInfo.c == bc) continue;
			if (bInfo.flag) {
				continue;
			}
			if (rInfo.flag) {
				return true;
			}
			if (rInfo.r == bInfo.r && rInfo.c == bInfo.c) {
				if ((rr - br) * dr[i] > 0) {
					bInfo.setRC(bInfo.r-dr[i], bInfo.c-dc[i]);
				}
				else if ((rr - br) * dr[i] < 0){
					rInfo.setRC(rInfo.r-dr[i], rInfo.c-dc[i]);
				}
				else if ((rc - bc) * dc[i] > 0) {
					bInfo.setRC(bInfo.r-dr[i], bInfo.c-dc[i]);
				}
				else if ((rc - bc) * dc[i] < 0) {
					rInfo.setRC(rInfo.r-dr[i], rInfo.c-dc[i]);
				}
			}
			if (dfs(cnt + 1, rInfo.r, rInfo.c, bInfo.r, bInfo.c)) {
				return true;
			}
			
		}
		return false;
	}
	
	static class Info {
		int r;
		int c;
		boolean flag;
		Info (int r, int c, boolean flag) {
			this.r = r;
			this.c = c;
			this.flag = flag;
		}
		public void setRC (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Info gravity(int r, int c, int d) {
		boolean flag = false;
		while (true) {
			if (arr[r][c] == '#') {
				r -= dr[d];
				c -= dc[d];
				break;
			}
			if (arr[r][c] == 'O') {
				flag = true;
				break;
			}
			r += dr[d];
			c += dc[d];
		}
		return new Info(r, c, flag);
	}

}
