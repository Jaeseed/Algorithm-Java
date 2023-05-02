package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_2533_사회망서비스 {
	static class Node {
		Node next;
		int num;
		
		Node (int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	
	static Node[] arr;
	static boolean[] visited;
	static int INF = 1000000;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new Node[N];
		visited = new boolean[N];
		for (int n = 0; n < N - 1; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			arr[n1] = new Node(n2, arr[n1]);
			arr[n2] = new Node(n1, arr[n2]);
		}
		visited[0] = true;
		int[] ans = dp(0);
		System.out.println(Math.min(ans[0], ans[1]));
	}
	
	static int[] dp(int num) {
		int useMe = 1;
		int notUseMe = 0;
		Node son = arr[num];
		while (son != null) {
			if (!visited[son.num]) {
				visited[son.num] = true;
				int[] ret = dp(son.num);
				useMe += Math.min(ret[0], ret[1]);
				notUseMe += ret[1];
			}
			son = son.next;
		}
		int[] res = {notUseMe, useMe};
		return res;
	}
}
