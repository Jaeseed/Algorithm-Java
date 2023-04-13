package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_17396_백도어 {
	static class Spot implements Comparable<Spot>{
		long t;
		int num;
		Spot (long t, int num) {
			this.t = t;
			this.num = num;
		}
		
		@Override
		public int compareTo(Spot target) {
			return this.t > target.t ? 1 : -1;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		List<Spot>[] route = new ArrayList[N];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if ((s != N - 1 && arr[s] == 1) || (e != N -1 && arr[e] == 1)) continue;
			if (route[s] == null) {
				route[s] = new ArrayList<>();
			}
			if (route[e] == null) {
				route[e] = new ArrayList<>();
			}
			route[s].add(new Spot(t, e));
			route[e].add(new Spot(t, s));
		}
		for (int n = 0; n < N; n++) {
            if (route[n] == null) continue;
			route[n].sort((s1,s2)->Long.compare(s1.t,s2.t));
		}
		
		long answer = -1;
		long[] visited = new long[N];
		for (int n = 0; n < N; n++) {
			visited[n] = Long.MAX_VALUE;
		}
		visited[0] = 0;
		
		Queue<Spot> qu = new PriorityQueue<>();
		if (route[0] == null) {
			System.out.println(-1);
		}
		else {
			for (int i = 0; i < route[0].size(); i++) {
				int nextSpot = route[0].get(i).num;
				visited[nextSpot] = route[0].get(i).t;
				qu.offer(new Spot(route[0].get(i).t, nextSpot));
			}
			while (!qu.isEmpty()) {
				Spot now = qu.poll();
				if (now.num == N-1) {
					answer = now.t;
					break;
				}
				if (visited[now.num] < now.t) continue;
	            if (route[now.num] == null) continue;
				for (int i = 0; i < route[now.num].size(); i++) {
					int nextSpot = route[now.num].get(i).num;
					long nextT = now.t + route[now.num].get(i).t;
					if (visited[nextSpot] <= nextT) {
						continue;
					}
					visited[nextSpot] = nextT;
					qu.offer(new Spot(nextT, nextSpot));
					
				}
				
			}
			
			System.out.println(answer);
		}

		

	}

}
