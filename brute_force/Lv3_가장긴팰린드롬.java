package brute_force;

public class Lv3_가장긴팰린드롬 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public int solution(String s)
    {
        int answer = 0;
        int length = s.length();
        loop:for (int i = 1; i < length + 1; i++) {
            for (int j = 0; j < i; j++) {
                int front = j;
                int rear = length - i + j;
                while (front <= rear) {
                    if (s.charAt(front) != s.charAt(rear)) break;
                    front += 1;
                    rear -= 1;
                }
                if (front > rear) {
                    answer = length - i + 1;
                    break loop;
                }
            }
        }

        return answer;
    }

}
