import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문제 이름(난이도) : 매직 스타 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/3967
 * */
public class Main {
    static final int N=12, M=26, R=5, C=9;
    static int E;
    static int[] magicStar;
    static List<Integer> emptyList;
    static boolean[] visited;
    static int[] total;
    static int[] or = {0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4};
    static int[] oc = {4, 1, 3, 5, 7, 2, 6, 1, 3, 5, 7, 4};
    static int[][] group = {{0, 2}, {3, 5}, {0, 5}, {2, 5}, {4, 5}, {0, 3}
                            , {2, 4}, {0, 1}, {1, 3}, {1, 4}, {1, 2}, {3, 4}};

    public static void main(String[] args) throws Exception {
        init();
        initMagicStar();
        E = emptyList.size();
        fill(0, 0, 0);
    }//main

    private static void fill(int cur, int cnt, int sum) {
        if(cnt == E) {
            if(isMagicStar()) {
                print();
                System.exit(0);
            }
            return;
        }

        int idx = emptyList.get(cur);
        for(int i=1; i<=N; i++) {
            if(visited[i]) continue;
            
            magicStar[idx] = i;
            visited[i] = true;
            total[group[idx][0]] += i;
            total[group[idx][1]] += i;
            
            fill(cur+1, cnt+1, sum);
            
            visited[i] = false;
            total[group[idx][0]] -= i;
            total[group[idx][1]] -= i;
        }
    }//fill

    private static boolean isMagicStar() {
        for(int i=0; i<6; i++) {
            if(total[i] != M) return false;
        }
        return true;
    }//isMagicStar

    private static void print() {
        StringBuilder ans = new StringBuilder();
        char[][] output = new char[R][C];
        
        for(int i=0; i<R; i++) {
            Arrays.fill(output[i], '.');
        }

        for(int i=0; i<N; i++) {
            output[or[i]][oc[i]] = (char) (magicStar[i] + 'A' - 1);
        }

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                ans.append(output[r][c]);
            }
            ans.append('\n');
        }

        System.out.println(ans);
    }//print

    private static void initMagicStar() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] input = new char[R][C];

        for(int i=0; i<R; i++) {
            input[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<N; i++) {
            if(input[or[i]][oc[i]] == 'x') {
                magicStar[i] = 0;
                emptyList.add(i);
            }
            else {
                magicStar[i] = input[or[i]][oc[i]] - 'A' + 1;
                visited[magicStar[i]] = true;
                total[group[i][0]] += magicStar[i];
                total[group[i][1]] += magicStar[i];
            }
        }

    }//initMagicStar

    private static void init() {
        magicStar = new int[N];
        visited = new boolean[N+1];
        emptyList = new ArrayList<>();
        total = new int[6];
    }//init

}//class