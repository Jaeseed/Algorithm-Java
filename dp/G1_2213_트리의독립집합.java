package dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		FileInputStream fis = new FileInputStream("src/input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		map = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		String input = "";
		while ((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			map[v1][v2] = true;
			map[v2][v1] = true;
		}
		Node[] ret = dp(1);
		int answer = 0;
		String[] answerRoute;
		String route = "";
		if (ret[0].value >= ret[1].value) {
			answer = ret[0].value;
			route = ret[0].path.toString();
		}
		else {
			answer = ret[1].value; 
			route = ret[1].path.toString();
		}
		answerRoute = route.split(" ");
		Arrays.sort(answerRoute);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < answerRoute.length; i++) {
			System.out.println(answerRoute[i]);
			sb.append(answerRoute[i] + " ");
		}
		System.out.println(answer);
		System.out.println(sb);
		
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
			if (ret[0].value > ret[1].value) {
				useValue += ret[0].value;
				useSb.append(" " + ret[0].path);
			}
			else {
				useValue += ret[1].value;
				useSb.append(" " + ret[1].path);
			}
			notUseValue += ret[1].value;
			notUseSb.append(" " + ret[1].path);
			
		}
		Node useMe = new Node(notUseValue + arr[now], notUseSb.append(" " + now));
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
