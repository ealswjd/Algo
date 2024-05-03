import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23254
public class Main {
    static final int A=0, B=1, T=24;
    static int N, M;
    static int[][] subjects;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) * T; // 기말고사는 정확히 24 X N시간 이후에 시작
        M = Integer.parseInt(st.nextToken()); // 기말고사 과목 개수
        subjects = new int[M][2];

        fillSubjects(new StringTokenizer(br.readLine()), A); // 공부를 하지 않아도 받을 수 있는 점수
        fillSubjects(new StringTokenizer(br.readLine()), B); // 한 시간 공부할 때마다 올릴 수 있는 점수

        int maxScore = getMaxScore();
        System.out.print(maxScore);
    }//main

    private static int getMaxScore() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[B] - o1[B]);
        int score = 0; // 총 점수

        for(int[] subject : subjects) {
            score += subject[A];
            pq.offer(new int[] {100 - subject[A], subject[B]}); // {필요한 점수, 시간당 점수}
        }

        int a, b;
        while(!pq.isEmpty() && N > 0) {
            a = pq.peek()[A]; // 필요한 점수
            b = pq.poll()[B]; // 시간당 점수

            if(a == 0) continue;
            if(a < b) pq.add(new int[] {a, a});
            else {
                if(a/b > N) {
                    score += b * N;
                    break;
                }else {
                    score += b * (a/b);
                    pq.add(new int[] {a-(a/b)*b, b});
                    N -= a/b;
                }
            }
        }

        return score;
    }//getMaxScore

    private static void fillSubjects(StringTokenizer st, int idx) {
        for(int i=0; i<M; i++) {
            subjects[i][idx] = Integer.parseInt(st.nextToken());
        }
    }//fillSubjects

}//class