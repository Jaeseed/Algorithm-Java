package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_2263_트리의순회 {
	static int[] pre;
	static int idx;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 0 행은 in order 1행은 post order
		int[][] orders = new int[2][N];
		for (int i = 0; i < 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				orders[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pre = new int[N];
		
		tree(0, N - 1, 0, N - 1, orders[0], orders[1]);
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			sb.append(pre[n] + " ");
		}
		System.out.println(sb);
		
	}

	static void tree(int is, int ie, int ps, int pe, int[] in, int[] post) {
		if (is > ie || ps > pe) return;
		pre[idx++] = post[pe];
		for (int i = is; i < N; i++) {
			if (in[i] == post[pe]) {
				tree(is, i - 1, ps, ps + i - is - 1, in, post);
				tree(i + 1, ie, ps + i - is, pe - 1, in, post);
			}
		}
	}
}
