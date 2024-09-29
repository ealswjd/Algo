import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11085
public class Main {
    static int P; // 지점 개수
    static int BW, CW; // 백준 월드 수도, 큐브 월드 수도
    static List<int[]> list; // 지점 연결 정보
    static int[] parent;

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken()); // 지점 개수
        int W = Integer.parseInt(st.nextToken()); // 길 개수

        st = new StringTokenizer(br.readLine());
        BW = Integer.parseInt(st.nextToken()); // 백준 월드 수도
        CW = Integer.parseInt(st.nextToken()); // 큐브 월드 수도

        init();

        while(W-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            list.add(new int[] {start, end, width});
        }

        int width = getWidth();
        System.out.println(width);
    }//main


    private static int getWidth() {
        int min = 0;
        list.sort(((o1, o2) -> o2[2] - o1[2]));

        for(int[] cur : list) {
            int start = cur[0];
            int end = cur[1];
            int width = cur[2];

            union(start, end);

            if(find(BW) == find(CW)) {
                min = width;
                break;
            }
        }

        return min;
    }//getWidth


    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }//union


    private static int find(int n) {
        if(n == parent[n]) return n;

        return parent[n] = find(parent[n]);
    }//find


    private static void init() {
        parent = new int[P];
        list = new ArrayList<>(); // 지점 연결 정보

        for(int i=0; i<P; i++) {
            parent[i] = i;
        }
    }//init

}//class