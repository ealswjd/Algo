import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14434
public class Main {
    static int N, M, K, Q;
    static int[] limits; // 놀이기구들의 키제한
    static int[] days; // 각 날마다 놀이기구 타는 횟수
    static List<List<Integer>> growList; // 키 성장 정보

    public static void main(String[] args) throws IOException {
        init();
        print();
    }//main


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int d=1; d<=K; d++) {
            days[d] += days[d-1];
            ans.append(days[d]).append('\n');
        }

        System.out.print(ans);
    }//print

    
    private static int getDay(int i, int j, int limit) {
        int start = 1;
        int end = K+1;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            int sum = getHeight(i, mid) + getHeight(j, mid);

            if(sum < limit) start = mid + 1;
            else end = mid;
        }

        return end;
    }//getDay

    
    private static int getHeight(int num, int day) {
        int start = 0;
        int end = growList.get(num).size() - 1;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(growList.get(num).get(mid) <= day) start = mid + 1;
            else end = mid - 1;
        }

        return end + 1;
    }//getHeight

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아이들의 수
        M = Integer.parseInt(st.nextToken()); // 놀이기구의 수
        K = Integer.parseInt(st.nextToken()); // 기간
        Q = Integer.parseInt(st.nextToken()); // 질문의 개수

        limits = new int[M+1]; // 놀이기구들의 키제한
        days = new int[K+1]; // 각 날마다 놀이기구 타는 횟수
        growList = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            growList.add(new ArrayList<>());
        }

        // 놀이기구들의 키제한이 순서대로 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++){
            limits[i] = Integer.parseInt(st.nextToken());
        }

        // 각 날에 키가 자라는 아이의 번호가 총 K개 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=K; i++) {
            int num = Integer.parseInt(st.nextToken());
            growList.get(num).add(i);
        }

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 아이 i와 아이 j가 놀이기구 k를 타려고 매일 시도
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int day = getDay(i, j, limits[k]);

            if(day <= K) days[day]++;
        }

    }//init


}//class