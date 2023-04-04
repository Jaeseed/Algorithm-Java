package greedy;

public class Lv2_조이스틱 {

	class Solution {
	    public int solution(String name) {
	        int answer = 0;
	        int length = name.length();
	        int jump = 0;
	        int idx = 1;
	        while (idx < length) {
	            if (name.charAt(idx) != 'A') {
	                idx += 1;
	                continue;
	            }
	            int start = idx;
	            int cnt = 1;
	            while (true) {
	                idx += 1;
	                if (idx == length) {
	                    jump = Math.max(cnt, jump);
	                    break;
	                }
	                if (name.charAt(idx) != 'A') {
	                    int left = cnt - start + 1;
	                    int right = cnt - (length - idx);
	                    jump = Math.max(jump, Math.max(left,right));
	                    idx += 1;
	                    break;
	                }
	                cnt +=1 ;
	                
	            }
	            
	        }
	        for (int i = 0; i < length; i++) {
	            char now = name.charAt(i);
	            if (now == 'A') {
	                continue;
	            }
	            answer += Math.min(91 - (int)now, (int)now - 65);
	        }
	        System.out.println(jump);
	        answer -= jump;
	        answer += length - 1;
	        
	        return answer;
	    }
	}

}
