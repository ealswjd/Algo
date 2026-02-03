import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14434
public class Main {
    private static int K;
    private static int[] days; // 날마다 아이들이 놀이기구 타는 횟수
    private static List<List<Integer>> height; // 아이들 키 정보

    public static void main(String[] args) throws IOException {
        sol();
        print();
    }//main

    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int day=1; day<=K; day++) {
            days[day] += days[day-1];
            ans.append(days[day]).append('\n');
        }

        System.out.print(ans);
    }//print

    private static int getDay(int a, int b, int limit) {
        int start = 1;
        int end = K + 1;
        int mid, totalH;

        while(start < end) {
            mid = (start + end) / 2;
            // a랑 b의 mid날째 키 합
            totalH = getHeight(a, mid) + getHeight(b, mid);

            if (totalH >= limit) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }//getDay

    private static int getHeight(int idx, int day) {
        List<Integer> h = height.get(idx);
        int start = 0;
        int end = h.size();
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if (h.get(mid) > day) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }//getHeight

    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 아이들의 수 N, 놀이기구의 수 M, 기간 K, 질문의 개수 Q가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 아이들의 수
        int M = Integer.parseInt(st.nextToken()); // 놀이기구의 수
        K = Integer.parseInt(st.nextToken()); // 기간
        int Q = Integer.parseInt(st.nextToken()); // 질문의 개수

        int[] limits = new int[M+1]; // 놀이기구 키제한
        days = new int[K+1]; // 날마다 아이들이 놀이기구 타는 횟수
        height = new ArrayList<>(N+1); // 아이들 키 정보

        for(int i=0; i<=N; i++) {
            height.add(new ArrayList<>());
        }

        // 놀이기구들의 키제한이 순서대로 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            limits[i] = Integer.parseInt(st.nextToken());
        }

        // 각 날에 키가 자라는 아이의 번호가 총 K개 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int day=1; day<=K; day++) {
            int idx = Integer.parseInt(st.nextToken());
            height.get(idx).add(day);
        }

        while(Q-- >0) {
            st = new StringTokenizer(br.readLine());
            // a랑 b가 놀이기구 m을 타려고 매일 시도함
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // a랑 b가 놀이기구 m을 탈 수 있는 날
            int day = getDay(a, b, limits[m]);
            if (day <= K) {
                days[day]++;
            }
        }

        br.close();
    }//sol

}//class