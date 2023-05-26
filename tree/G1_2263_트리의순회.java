package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_2263_트리의순회 {
	static int inIdx;
	static int postIdx;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 0 행은 in order 1행은 post order
		int[][] orders = new int[2][N];
		for (int i = 0; i < 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				orders[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N+1];
		inIdx = N - 1;
		postIdx = N - 1;
		visited[orders[1][N-1]] = true;
		Node root = new Node(orders[1][N-1]);
		order(root, orders[0], orders[1]);
		System.out.println(3);
	}
	
	static void order(Node now, int[] inOrder, int[] postOrder) {
		if (inOrder[inIdx] != now.num) {
			postIdx--;
			int rightNum = postOrder[postIdx];
			Node right = new Node(rightNum);
			visited[rightNum] = true;
			now.setRight(right);
			order(right, inOrder, postOrder);
		}
		inIdx--;
		if (visited[inOrder[inIdx]]) return;
		int leftNum = inOrder[postIdx];
		Node left = new Node(leftNum);
		visited[leftNum] = true;
		order(left, inOrder, postOrder);

	}
	
	static class Node {
		int num;
		Node left;
		Node right;
		
		Node (int num) {
			this.num = num;
		}
		
		public void setLeft(Node left) {
			this.left = left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
	}

}
