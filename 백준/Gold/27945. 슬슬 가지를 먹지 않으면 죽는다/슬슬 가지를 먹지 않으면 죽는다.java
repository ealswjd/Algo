import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/27945
public class Main {
    static final int FROM = 0, TO = 1, DAY = 2;
    static int N; // 요리 학원의 수
    static int[] parent;
    static List<int[]> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 요리 학원의 수
        int M = Integer.parseInt(st.nextToken()); // 길의 수

        init(M);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken()); // 노점이 여는 날
            
            if(day > N) continue;

            list.add(new int[] {from, to, day});
        }

        br.close();

        int maxDay = getMax();
        System.out.print(maxDay);
    }//main
    

    private static int getMax() {
        list.sort((Comparator.comparingInt(o -> o[DAY])));
        int today = 1;
        int max = 0;
        int cnt = 0;

        for(int[] cur : list) {
            int from = cur[FROM];
            int to = cur[TO];
            int day = cur[DAY];

            if(today < day) break;

            if(union(from, to)) {
                max = day;
                today++;
                if(++cnt == N-1) break;
            }
        }

        return max + 1;
    }//getMax

    
    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;

        if(a < b) parent[b] = a;
        else parent[a] = b;

        return true;
    }//union

    
    private static int find(int n) {
        if(parent[n] == n) return n;

        return parent[n] = find(parent[n]);
    }//find

    
    private static void init(int M) {
        parent = new int[N+1];
        list = new ArrayList<>(M);

        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
    }//init

    
}//class