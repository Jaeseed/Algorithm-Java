package implementation;

import java.util.Stack;

public class Lv3_표편집하기 {
    
    public class Row {
        int[] adj;
        boolean isActive;
        
        Row (int[] adj) {
            this.adj = adj;
            isActive = true;
        }
        
        public void setIsActive() {
            isActive = !isActive;
        }
        
        public void setPrevious(int row) {
            adj[0] = row;
        }
        
        public void setNext(int row) {
            adj[1] = row;
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        // 행들의 관계와 삭제 여부를 저장한 배열
        Row[] rows = new Row[n];
        // 삭제한 배열 저장 LIFO  
        Stack<Integer> deleted = new Stack<>();
        // 현재 행 위치
        int now = k;
        
        for (int i = 0; i < n; i++) {
            int[] adj = {i-1, i+1};
            rows[i] = new Row(adj);
        }
        // 행 벗어나는 구간은 -1로 idx 갱신
        rows[n-1].setNext(-1);
        
        for (String c : cmd) {
            String[] sp = c.split(" ");
            // 이동
            if (sp.length > 1) {
                int cnt = Integer.parseInt(sp[1]);
                int direction = 1;
                if (sp[0].equals("U")) {
                    direction = 0;
                }
                while (cnt > 0) {
                    now = rows[now].adj[direction];
                    cnt -= 1;
                }
            }
            // 삭제
            else if (sp[0].equals("C")) {
                // 삭제 idx 보관
                deleted.push(now);
                // active 상태 false
                rows[now].setIsActive();
                int[] adj = rows[now].adj;
                // 현재가 0행일 때
                if (adj[0] == -1) {
                    rows[adj[1]].setPrevious(-1);
                    now = adj[1];
                }
                // 현재가 마지막 행일 때  
                else if (adj[1] == -1) {
                    rows[adj[0]].setNext(-1);
                    now = adj[0];
                }
                // 앞 뒤 행 다 있을 때
                else {
                    rows[adj[0]].setNext(adj[1]);
                    rows[adj[1]].setPrevious(adj[0]);
                    now = adj[1];
                }
            }
            // 복구
            else {
                int restore = deleted.pop();
                int[] adj = rows[restore].adj;
                rows[restore].setIsActive();
                // 복구 행의 전 행이 있을 때
                if (adj[0] != -1) {
                    rows[adj[0]].setNext(restore);
                }
                // 복구 행의 다음 행이 있을 때
                if (adj[1] != -1) {
                    rows[adj[1]].setPrevious(restore);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (rows[i].isActive) {
                sb.append("O");
            }
            else {
                sb.append("X");
            }
        }
        return sb.toString();
    }
}
