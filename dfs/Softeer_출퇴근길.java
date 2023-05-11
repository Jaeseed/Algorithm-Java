package dfs;

import java.util.*;
import java.io.*;


public class Softeer_출퇴근길
{   

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 일방통행 길 저장 인접리스트
        Node[] arr = new Node[N+1];
        // 역방향 리스트
        Node[] rArr = new Node[N+1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[s] = new Node(e, arr[s]);
            rArr[e] = new Node(s, rArr[e]);
        }
        
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        boolean[] sVisited = new boolean[N+1];
        boolean[] tVisited = new boolean[N+1];
        boolean[] sRVisited = new boolean[N+1];
        boolean[] tRVisited = new boolean[N+1];
        
        sVisited[T] = true;
        tVisited[S] = true;

        dfs(S, T, arr, sVisited);
        dfs(T, S, arr, tVisited);
        dfs(S, T, rArr, sRVisited);
        dfs(T, S, rArr, tRVisited);


        int answer = -2;

        for (int n = 1; n < N + 1; n++) {
            if (sVisited[n] && tVisited[n] && sRVisited[n] && tRVisited[n]) {
                answer += 1;
            }
        }

        System.out.println(answer);

    }

    static void dfs(int now, int t, Node[] arr, boolean[] visited) {
    	visited[now] = true;
    	Node nNode = arr[now];
    	while (nNode != null) {
    		if (!visited[nNode.road]) {
    			visited[nNode.road] = true;
    			dfs(nNode.road, t, arr, visited);
    		}
    		nNode = nNode.next;
    	}
    }

    static class Node {
        int road;
        Node next;

        Node(int road, Node next) {
            this.road = road;
            this.next = next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}