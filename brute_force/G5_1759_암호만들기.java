package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G5_1759_암호만들기 {
	static List<String> consonant = new ArrayList<>();
	static List<String> vowel = new ArrayList<>();
	static List<String> answer = new ArrayList<>();
	static List<String> word = new ArrayList<>();
	static int L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int c = 0; c < C; c++) {
			String now = st.nextToken();
			if (now.equals("a") || now.equals("e") || now.equals("i") || now.equals("o") || now.equals("u")) {
				consonant.add(now);
			}
			else {
				vowel.add(now);
			}
		}
		Collections.sort(consonant);
		Collections.sort(vowel);
		// 최소한의 모음 개수
		int start = 1;
		int end =  consonant.size();
		if (L - vowel.size() > 1) {
			start = L - vowel.size();
		}
		if (L <= consonant.size()) {
			end = L - 2;
		}
		for (int i = start; i <= end; i++) {
			consTracking(0,i);
		}
		Collections.sort(answer);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < answer.size(); i++) {
			sb.append(answer.get(i));
		}
		System.out.println(sb);
		

	}
	
	static void consTracking(int idx, int remain) {
		if (remain == 0) {
			if (L-word.size() < 2) {
				return;
			}
			vowelTracking(0, L - word.size());
			return;
		}
		for (int i = idx; i < consonant.size()-remain+1; i++) {
			word.add(consonant.get(i));
			consTracking(i+1, remain-1);
			word.remove(word.size()-1);
		}
	}
	
	static void vowelTracking(int idx, int remain) {
		if (remain == 0) {
			String now = String.join("", word);
			char[] tmp = now.toCharArray();
			Arrays.sort(tmp);
			answer.add(new String(tmp) + "\n");
			return;
		}
		for (int i = idx; i < vowel.size()-remain+1; i++) {
			word.add(vowel.get(i));
			vowelTracking(i+1, remain-1);
			word.remove(word.size()-1);
		}
	}

}
