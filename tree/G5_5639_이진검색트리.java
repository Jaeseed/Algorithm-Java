package tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_5639_이진검색트리 {
	static int[] tree = new int[10001];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		String input;
		int now;
		int idx = -1;
		FileInputStream fis = new FileInputStream("src/baekjoon/input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		while ((input = br.readLine()) != null) {
			idx += 1;
			now = Integer.parseInt(input);
			tree[idx] = now;
		}
		br.close();
		postOrder(0, idx);
		System.out.println(sb);

	}

	static void postOrder(int s, int e) {
		if (s > e)
			return;
		int idx = s + 1;
		while (idx <= e && tree[idx] < tree[s]) {
			idx += 1;
		}
		postOrder(s + 1, idx - 1);
		postOrder(idx, e);
		sb.append(tree[s] + "\n");
	}

}
