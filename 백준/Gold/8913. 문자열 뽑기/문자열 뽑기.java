import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/8913
public class Main {
    private static boolean isPossible; // 빈 문자로 바꿀 수 있는지
    private static Set<String> visited; // 상태 확인
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int result;
        while(T-- > 0) {
            String str = br.readLine();
            isPossible = false;
            visited = new HashSet<>();

            sol(str);

            // 문자열을 빈 문자열로 바꿀 수 있으면 1을, 없으면 0을 출력
            result = isPossible ? 1 : 0;
            ans.append(result).append('\n');
        }

        System.out.print(ans);
    }//main
    

    private static void sol(String str) {
        // 이미 성공했거나 현재 문자열 상태를 이미 확인함.
        if (isPossible || visited.contains(str)) {
            return;
        }
        // 빈 문자열이 됐으면 성공
        if (str.isEmpty()) {
            isPossible = true;
            return;
        }

        // 현재 문자열 확인 처리
        visited.add(str);

        int len = str.length();
        int s = 0;
        while(!isPossible && s < len) {
            int e = s;
            while(e < len && str.charAt(s) == str.charAt(e)) {
                e++;
            }

            int cnt = e - s;
            if (cnt > 1) {
                String next = str.substring(0, s) + str.substring(e);
                sol(next);
            }

            s = e;
        }
    }//sol


}//class