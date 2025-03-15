import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2668
public class Main {
    private static int N, result; // 칸의 개수, 뽑힌 개수
    private static int[] numbers; // 표의 둘째 줄에 들어가는 정수들
    private static boolean[] checked, visited; // 뽑힌 정수, 방문 체크


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();

        for(int i=1; i<=N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        ans.append(result).append('\n');

        for(int n=1; n<=N; n++) {
            if(checked[n]) ans.append(n).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static void dfs(int start, int cur) {
        if(numbers[cur] == start) {
            checked[cur] = true;
            result++;
        }

        int next = numbers[cur];
        if(!visited[next]) {
            visited[next] = true;
            dfs(start, next);
            visited[next] = false;
        }
    }//dfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // N개의 칸

        numbers = new int[N+1]; // 표의 둘째 줄에 들어가는 정수들
        checked = new boolean[N+1]; // 뽑힌 정수들
        visited = new boolean[N+1]; // 방문 체크

        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }//init


}//class