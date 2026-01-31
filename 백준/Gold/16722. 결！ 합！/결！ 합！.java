import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16722
public class Main {
    private static final int N = 9, TOTAL = 1 << N;
    private static int[] S, C, B; // 도형, 도형색, 배경색
    private static List<Integer> hList;
    private static int H; // 합이 가능한 조합 개수
    private static String[][] commands;
    private static boolean[] isH;

    
    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main
    

    private static void sol() {
        getH(0, 0, 0, new int[3]);
        
        boolean[] checked = new boolean[TOTAL]; // 합 조합 사용 체크
        boolean usedG = false;

        int score = 0, hCnt = 0;
        int a, b, c, h;
        for(String[] cmd : commands) {
            if (cmd[0].equals("H")) { // ‘합’ 외치기
                // H a b c : ‘합’이라고 생각되는 그림의 번호 a, b, c를 외친 입력
                a = Integer.parseInt(cmd[1]) - 1;
                b = Integer.parseInt(cmd[2]) - 1;
                c = Integer.parseInt(cmd[3]) - 1;
                h = (1 << a) | (1 << b) | (1 << c);

                // ‘합’을 이루면서 이전에 외친 적이 없는 그림 조합이라면 +1점
                if (isH[h] && !checked[h]) {
                    score++;
                    hCnt++;
                    checked[h] = true;
                } else {
                    // 아니라면 -1점을 획득
                    score--;
                }
            } else { // ‘결’ 외치기
                // 외치지 않은 ‘합’ 이 없고 ‘결’을 통해 +3점을 얻은 적이 없다면 +3
                if (hCnt == H && !usedG) {
                    score += 3;
                    usedG = true;
                } else {
                    // 아니라면 -1점을 획득
                    score--;
                }
            }
        }

        System.out.print(score);
    }//sol
    

    private static void getH(int cur, int cnt, int comb, int[] pick) {
        if (cnt == 3) {
            if (isAvailable(pick) && !isH[comb]) {
                H++;
                isH[comb] = true;
            }
            return;
        }

        for(int i=cur; i<N; i++) {
            if ((comb & (1 << i)) != 0) continue;
            pick[cnt] = i;
            getH(cur+1, cnt+1, comb | (1 << i), pick);
        }
    }//getH
    

    private static boolean isAvailable(int[] pick) {
        int p1 = pick[0];
        int p2 = pick[1];
        int p3 = pick[2];

        int s = (S[p1] + S[p2] + S[p3]) % 3;
        int c = (C[p1] + C[p2] + C[p3]) % 3;
        int b = (B[p1] + B[p2] + B[p3]) % 3;

        return s == 0 && c == 0 && b == 0;
    }//isAvailable
    

    private static void init() throws IOException {
        isH = new boolean[TOTAL];
        Map<String, Integer> map = new HashMap<>();

        // Si는 {“CIRCLE”, “TRIANGLE”, ”SQUARE”},
        map.put("CIRCLE", 0); map.put("TRIANGLE", 1); map.put("SQUARE", 2);
        // Ci는 {“YELLOW”, “RED”, “BLUE”}
        map.put("YELLOW", 0); map.put("RED", 1); map.put("BLUE", 2);
        // Bi는 {“GRAY”, “WHITE”, “BLACK”}
        map.put("GRAY", 0); map.put("WHITE", 1); map.put("BLACK", 2);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = new int[N]; // 도형
        C = new int[N]; // 도형색
        B = new int[N]; // 배경색

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = map.get(st.nextToken());
            C[i] = map.get(st.nextToken());
            B[i] = map.get(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine()); // 게임 기록(명령)의 수
        commands = new String[M][]; // 플레이어의 명령 기록

        for(int i=0; i<M; i++) {
            commands[i] = br.readLine().split(" ");
        }

        br.close();
    }//init
    

}//class