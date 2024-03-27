import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 비밀 모임 (골드 4)
 * 링크 : https://www.acmicpc.net/problem/13424
 * */
public class Main {
    static final int INF=987654321;
    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 방의 개수
            init();

            int M = Integer.parseInt(st.nextToken()); // 비밀통로의 개수
            while(M-->0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 방 1
                int b = Integer.parseInt(st.nextToken()); // 방 2
                int c = Integer.parseInt(st.nextToken()); // 비밀통로의 길이
                map[a][b] = map[b][a] = c;
            }//while

            int K = Integer.parseInt(br.readLine()); // 모임에 참여하는 친구의 수
            int[] friends = new int[K];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<K; i++) {
                friends[i] = Integer.parseInt(st.nextToken());
            }

            floyd();
            ans.append(getMin(K, friends)).append('\n');
        }//while

        System.out.print(ans);
    }//main

    private static int getMin(int K, int[] friends) {
        int min = INF;
        int num=0, start;
        int[] rooms = new int[N+1];

        // 각 방의 이동 거리 총합 구하기
        for(int i=0; i<K; i++) {
            start = friends[i];
            for(int e=1; e<=N; e++) {
                rooms[e] += map[start][e];
            }
        }//for

        // 이동 거리 총합 최솟값 구하기
        for(int i=1; i<=N; i++) {
            min = Math.min(min, rooms[i]);
        }

        // 이동 거리 총합이 최소인 방 구하기
        for(int i=1; i<=N; i++) {
            if(rooms[i] == min) {
                num = i;
                break;
            }
        }

        return num;
    }//getMin

    private static void floyd() {
        for(int i=1; i<=N; i++) {
            for(int s=1; s<=N; s++) {
                for(int e=1; e<=N; e++) {
                    map[s][e] = Math.min(map[s][e], map[s][i] + map[i][e]);
                }
            }
        }
    }//floyd

    private static void init() {
        map = new int[N+1][N+1];
        for(int i=0; i<=N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i]=0;
        }
    }//init

}//class