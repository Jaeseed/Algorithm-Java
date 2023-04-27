package priority_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_1766_문제집 {
	static class Problem{
		List<Integer> sons;
		int belongCnt = 0;
		Problem () {
			this.sons = new ArrayList<>();
		}
		public void addBelongCnt () {
			this.belongCnt += 1;
		}
		public void addSon (int page) {
			this.sons.add(page);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Problem[] problems = new Problem[N+1];
		for (int n = 1; n <= N; n++) {
			problems[n] = new Problem();
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int pageA = Integer.parseInt(st.nextToken());
			int pageB = Integer.parseInt(st.nextToken());
			problems[pageB].addBelongCnt();
			problems[pageA].addSon(pageB);
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> qu = new PriorityQueue<>();
		for (int n = 1; n <= N; n++) {
			if (problems[n].belongCnt == 0) {
				qu.offer(n);
			}
		}
		while (!qu.isEmpty()) {
			int now = qu.poll();
			sb.append(now + " ");
			for (int i = 0; i < problems[now].sons.size(); i++) {
				int son = problems[now].sons.get(i);
				problems[son].belongCnt -= 1;
				if (problems[son].belongCnt == 0) {
					qu.offer(son);
				}
			}
		}
		System.out.println(sb);
//		List<Integer> a = new ArrayList<>();
//		if (a.size() == 0) {
//			System.out.println(1);
//		}
//		if (a.isEmpty()) {
//			System.out.println(2);
//		}
//		if (a != null) {
//			System.out.println(3);
//		}
	}

}
