package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] arr = new ArrayList[N+1];
		int[] backCnt = new int[N+1];
		
		for (int n = 1; n <= N; n++) {
			arr[n] = new ArrayList<>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			arr[front].add(back);
			backCnt[back]++;
		}
		
		Queue<Integer> qu = new LinkedList<>();
		for (int n = 1; n <= N; n++) {
			if (backCnt[n] == 0) {
				qu.offer(n);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!qu.isEmpty()) {
			int now = qu.poll();
			sb.append(now + " ");
			for (int nBack : arr[now]) {
				backCnt[nBack]--;
				if (backCnt[nBack] == 0) {
					qu.offer(nBack);
				}
			}
		}
		
		System.out.println(sb);

	}

}
