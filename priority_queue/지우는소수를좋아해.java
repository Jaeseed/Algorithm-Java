package priority_queue;

import java.util.*;
import java.io.*;


public class 지우는소수를좋아해
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Node>[] arr = new ArrayList[N+1];
        int[] dist = new int[N+1];
        for (int n = 1; n < N + 1; n++) {
            arr[n] = new ArrayList<>();
        	dist[n] = Integer.MAX_VALUE;
        }
        dist[1] = 0;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            arr[A].add(new Node(B,C));
            arr[B].add(new Node(A,C));
        }

        int targetLevel = 0;
        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        Queue<Node> qu = new PriorityQueue<>();
        for (Node tmp : arr[1]) {
            qu.offer(tmp);
        }

        while (!qu.isEmpty()) {
            Node now = qu.poll();
            if (visited[now.now]) continue;
            visited[now.now] = true;
            if (now.now == N) {
                targetLevel = now.level;
            }
            for (Node next : arr[now.now]) {
            	int level = Math.max(next.level, now.level);
                if (visited[next.now]) continue;
                if (dist[next.now] <= level) continue;
                dist[next.now] = level;
                qu.offer(new Node(next.now, level));
            }

        }
        boolean flag = false;
        while (!flag) {
        	targetLevel += 1;
        	flag = true;
        	for (int i = 2; i <= Math.sqrt(targetLevel); i++) {
        		if (targetLevel % i == 0) {
        			flag = false;
        			break;
        		}
        	}
        }
        System.out.println(targetLevel);

    }

    static class Node implements Comparable<Node> {
        int now;
        int level;
        
        Node (int now, int level) {
            this.now = now;
            this.level = level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        public int compareTo(Node n) {
            return this.level - n.level;
        }
    }
}