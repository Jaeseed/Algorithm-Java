package segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_2042_구간합구하기 {
	static class Node {
		int start;
		int end;
		long value;
		Node (int start, int end, long value) {
			this.start = start;
			this.end = end;
			this.value = value;
		}
	}
	
	static Node[] tree;
	static long arr[];
	static int idx[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 트리를 담을 배열의 길이 구하기
		int bin = 1;
		while (true) {
			if (bin >= N) {
				tree = new Node[bin * 2];
				break;
			}
			bin *= 2;
		}
		
		arr = new long[N+1];
		// 노드들의 idx 저장 배열
		idx = new int[N+1];
		
		for (int n = 1; n <= N; n++) {
			arr[n] = Long.parseLong(br.readLine());
		}
		
		makeTree(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			if (order == 1) {
				long value = Long.parseLong(st.nextToken());
				int nodeIdx = idx[s];
				long diff = value - tree[nodeIdx].value;
				tree[nodeIdx].value = value;
				changeParent(nodeIdx, diff);
			}
			else {
				int e = Integer.parseInt(st.nextToken());
				sb.append(sum(1,s,e) + "\n");
			}
		}
		System.out.println(sb);

	}
	
	static long makeTree(int now, int s, int e) {
		if (s == e) {
			tree[now] = new Node(s, s, arr[s]);
			idx[s] = now;
			return arr[s];
		}
		long value = 0;
		value += makeTree(now * 2, s, (s + e) / 2);
		value += makeTree(now * 2 + 1, (s + e) / 2 + 1, e);
		tree[now] = new Node(s, e, value);
		return value;
	}
	
	static void changeParent(int now, long value) {
		now /= 2;
		while (now > 0) {
			tree[now].value += value;
			now /= 2;
		}
	}
	
	static long sum(int idx, int s, int e) {
		Node now = tree[idx];
		// 현재 노드 범위가 구간 합에 포함 될 때
		if (now.start >= s && now.end <= e) {
			return now.value;
		}
		
		long value = 0;
		int mid = (now.start + now.end) / 2;
		// 현재 노드의 왼쪽 자식 중 구간에 포함 되는 자식 노드가 있을 때
		if (mid >= s && now.start <= e) {
			value += sum(idx * 2, s, e);
		}
		// 현재 노드의 오른쪽 자식 중 구간에 포함 되는 자식 노드가 있을 때
		if (now.end >= s && mid + 1 <= e) {
			value += sum(idx * 2 + 1, s, e);
		}
		return value;
		
	}

}
