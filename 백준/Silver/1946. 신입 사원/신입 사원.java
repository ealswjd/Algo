import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1946
public class Main {
    static int N;
    static PriorityQueue<Score> score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        score = new PriorityQueue<>();
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                score.offer(new Score(first, second));
            }//for

            ans.append(getCnt()).append('\n');
        }//while
        br.close();

        System.out.print(ans);
    }//main

    private static int getCnt() {
        int cnt = 1, max = score.poll().second;
        int second;
        while(!score.isEmpty()) {
            second = score.poll().second;
            if(second > max) continue;
            cnt++;
            max = second;
        }//while

        return cnt;
    }//getCnt

    static class Score implements Comparable<Score> {
        int first;
        int second;
        public Score(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Score o) {
            return this.first - o.first;
        }
    }//Score

}//class