package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class G1_2213_트리의독립집합 {
	static class Node {
		int value;
		StringBuilder path;
		Node (int value, StringBuilder path) {
			this.value = value;
			this.path = path;
		}
		
	}
	static int N;
	static int[] arr;
	static boolean[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		map = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		while ((st = new StringTokenizer(br.readLine())).hasMoreTokens()) {
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			map[v1][v2] = true;
			map[v2][v1] = true;
		}
		System.out.println(3);
		dp(1);
	}
	
	static Node[] dp(int now) {
		visited[now] = true;
		int useValue = 0;
		int notUseValue = 0;
		StringBuilder useSb = new StringBuilder();
		StringBuilder notUseSb = new StringBuilder();
		for (int n = 2; n <= N; n++) {
			if (!map[now][n] || visited[n]) continue;
			Node[] ret = dp(n);
			useValue += ret[0].value;
			notUseValue += ret[1].value;
			useSb.append(ret[0].path);
			notUseSb.append(ret[1].path);
			
		}
		Node useMe = new Node(notUseValue + arr[now], notUseSb.append(now));
		Node notUseMe;
		if (useValue >= notUseValue) {
			notUseMe = new Node(useValue, useSb);
		}
		else {
			notUseMe = new Node(notUseValue, notUseSb);
		}
		return new Node[] {useMe, notUseMe};
		
	}

}
