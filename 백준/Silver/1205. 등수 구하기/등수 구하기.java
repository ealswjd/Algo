import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1205
public class Main {
    static int N, P;
    static int[] scores;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 점수 리스트에 있는 점수 개수
        int score = Integer.parseInt(st.nextToken()); // 태수 점수
        P = Integer.parseInt(st.nextToken()); // 랭킹 리스트에 올라 갈 수 있는 점수의 개수

        scores = new int[N];
        if(N>0) st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        int rank = getRank(score);
        System.out.print(rank);
    }//main

    private static int getRank(int score) {
        if(N==0) return 1;
        if(scores[N-1] > score) {
            if(N==P) return -1;
            else return N+1;
        }
        int start = 0;
        int end = N-1;
        int mid;

        while(start<end) {
            mid = (start+end)/2;
            if(scores[mid] > score) start = mid+1;
            else end = mid;
        }

        if(P==N && scores[end] >= score){
            boolean flag = false;
            int tmp = end;
            while(tmp < N) {
                if(scores[tmp++] < score) {
                    flag = true;
                    break;
                }
            }

            if(!flag) return -1;
        }

        return end+1;
    }//getRank

}//class