package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G2_1167_트리의지름 {
	static boolean[] visited;
	static int[] distance;
	static List<Node>[] arr;
	static int V;
	static class Node {
		int num;
		int d;
		Node (int num, int d) {
			this.num = num;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		arr = new ArrayList[V];
		visited = new boolean[V];
		distance = new int[V];
		for (int v = 0; v < V; v++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nowNum = Integer.parseInt(st.nextToken());
			arr[nowNum-1] = new ArrayList<Node>();
			int nextNum = Integer.parseInt(st.nextToken());
			while (nextNum != -1) {
				int nextD = Integer.parseInt(st.nextToken());
				arr[nowNum-1].add(new Node(nextNum-1, nextD));
				nextNum = Integer.parseInt(st.nextToken());
			}
		}
		visited[0] = true;
		dfs(0,0);
 		int targetNum = findMax();
 		visited = new boolean[V];
 		distance = new int[V];
 		visited[targetNum] = true;
 		dfs(targetNum,0);
 		System.out.println(distance[findMax()]);

	}
	static void dfs(int now, int dist) {
		for (int i = 0; i < arr[now].size(); i++) {
			int nextNum = arr[now].get(i).num;
			if (visited[nextNum]) continue;
			int nextD = arr[now].get(i).d;
			if (distance[nextNum] >= dist + nextD) {
				continue;
			}
			visited[nextNum] = true;
			distance[nextNum] = Math.max(distance[i], dist + nextD);
			dfs(nextNum, dist + nextD);
			visited[nextNum] = false;
		}
	}
	
	static int findMax() {
		int maxV = 0;
		int maxN = 0;
 		for (int v = 0; v < V; v++) {
			if (distance[v] > maxV) {
				maxV = distance[v];
				maxN = v;
			}
		}
 		return maxN;
	}
	

}
