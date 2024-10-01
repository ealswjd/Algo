import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/10799
public class Main {
    static final char OPEN = '(';
    static final char CLOSE = ')';
    static final char LASER = '*';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] position = br.readLine().toCharArray();

        br.close();

        int cnt = getCnt(position);
        System.out.print(cnt);
    }//main

    private static int getCnt(char[] position) {
        int total = 0;
        int cnt = 0;
        Queue<Character> q = new LinkedList<>();

        for(int i=0; i<position.length; i++) {
            if(position[i] == OPEN) {
                if(position[i+1] == CLOSE) {
                    q.add(LASER);
                    i++;
                }
                else q.add(OPEN);
            }
            else q.add(CLOSE);
        }

        char cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            switch (cur){
                case LASER :
                    if(cnt > 0) total += cnt;
                    break;
                case OPEN:
                    cnt++;
                    break;
                case CLOSE:
                    cnt--;
                    total++;
                    break;
            }

        }

        return total;
    }//getCnt


}//class