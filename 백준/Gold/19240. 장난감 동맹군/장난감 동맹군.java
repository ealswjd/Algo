import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19240
public class Main {
    private static int N; // N개의 동물 장난감
    private static int[] team; // 각 장난감 팀
    private static List<List<Integer>> list; // 라이벌


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        while(T-- > 0) {
            init(br);
            sol(ans);
        }

        br.close();
        System.out.print(ans);
    }//main


    private static void sol(StringBuilder ans) {
        boolean isPossible = true;

        for(int i=1; i<=N; i++) {
            if(team[i] != 0) continue;
            // 동맹 불가능
            if(isInvalidAlliance(i, 1)) {
                isPossible = false;
                break;
            }
        }

        if(isPossible) ans.append("YES\n");
        else ans.append("NO\n");
    }//sol


    private static boolean isInvalidAlliance(int cur, int num) {
        team[cur] = num;

        for(int next : list.get(cur)) {
            if(team[next] == 0) {
                if(isInvalidAlliance(next, num^3)) return true;
            }
            else if(team[cur] == team[next]){
                return true;
            }
        }

        return false;
    }//isInvalidAlliance

    
    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N개의 동물 장난감
        int M = Integer.parseInt(st.nextToken()); // 라이벌 관계 수

        team = new int[N+1]; // 각 장난감 팀
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.get(x).add(y);
            list.get(y).add(x);
        }

    }//init


}//class