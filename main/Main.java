package main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		if (answer % 4 == 0 && answer % 100 != 0) {
			answer = 1;
		}
		else if (N % 400 == 0) {
			answer = 1;
		}
		System.out.println(answer);
		System.out.println(1804 % 100);

	}

}