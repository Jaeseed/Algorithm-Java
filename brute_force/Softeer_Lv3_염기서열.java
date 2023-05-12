package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Softeer_Lv3_염기서열 {
    static int N;
    static int M;
    static int answer = 20;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 좋은 염기서열
        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        recursion(arr, 0, 1);


        System.out.println(answer);
    }

    static void recursion(String[] arr, int now, int jStart) {
        for (int i = now; i < arr.length; i++) {
            int cnt = i + 1;
            if (cnt >= answer) return;
            String standard = arr[i];
            if (i != now) {
            	jStart = i+ 1;
            }
            for (int j = jStart; j < arr.length; j++) {
                String newLine = stCompare(standard, arr[j]);
                if (newLine.length() == M) {
                    String[] newArr = new String[arr.length - 1];
                    int idx = 0;
                    for (int k = 0; k < arr.length; k++) {
                        if (k == j) continue;
                        if (k == i) {
                            newArr[idx] = newLine;
                            idx += 1;
                            continue;
                        }
                        newArr[idx] = arr[k];
                        idx += 1;
                    }
                    recursion(newArr, i, j);
                }
            }
        }
        answer = Math.min(answer, arr.length);
        return;
    }

    static String stCompare(String now, String target) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < M; k++) {
            if (now.charAt(k) == '.') {
                sb.append(target.charAt(k));
            }
            else if (target.charAt(k) == '.'){
                sb.append(now.charAt(k));
            }
            else if (now.charAt(k) == target.charAt(k)) {
                sb.append(now.charAt(k));
            }
            else break;
        }
        String newLine = sb.toString();
        return newLine;
    }
}