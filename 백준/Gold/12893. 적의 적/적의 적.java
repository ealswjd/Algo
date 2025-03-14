import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12893
public class Main {
    private static int N; // 용재 주위 사람의 수
    private static int[] team; // 우호 관계
    private static List<List<Integer>> list; // 적대관계


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int result = 1;

        for(int i=1; i<=N; i++) {
            if(team[i] != 0) continue;
            if(dfs(i, 1)) {
                result = 0;
                break;
            }
        }

        System.out.print(result);
    }//sol


    private static boolean dfs(int cur, int num) {
        team[cur] = num;

        for(int next : list.get(cur)) {
            if(team[next] == 0) {
                if(dfs(next, num^3)) {
                    return true;
                }
            }
            else if(team[next] == team[cur]) {
                return true;
            }
        }

        return false;
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 용재 주위 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 적대관계의 수

        team = new int[N+1]; //  우호 관계
        list = new ArrayList<>(N+1); // 적대관계

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        br.close();
    }//init


}//class