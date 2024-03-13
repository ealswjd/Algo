import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 여러분의 다리가 되어 드리겠습니다! (골드 5)
 * 링크 : https://www.acmicpc.net/problem/17352
 * */
public class Main {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // N개의 섬
        init();

        StringTokenizer st;
        for(int i=2; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // 다리들이 잇는 두 섬의 번호
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b); // 연결
        }//for
        br.close();

        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for(int i=1; i<=N; i++) {
            if(find(i) == i) {
                cnt++;
                ans.append(i).append(' ');
                if(cnt == 2) break;
            }//if
        }//for

        System.out.print(ans);
    }//main

    // 연결
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a <= b) parent[b] = a;
        else parent[a] = b;
    }//union

    // 부모 찾기
    private static int find(int n) {
        if(parent[n] == n) return n;

        return parent[n] = find(parent[n]);
    }///find

    // 초기화
    private static void init() {
        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }//for
    }//init

}//class