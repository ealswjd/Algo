import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17219
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 저장된 사이트 주소의 수
        int M = Integer.parseInt(st.nextToken()); // 비밀번호를 찾으려는 사이트 주소의 수

        Map<String, String> pw = new HashMap<>();
        while(N-->0) {
            st = new StringTokenizer(br.readLine());
            pw.put(st.nextToken(), st.nextToken());
        }

        StringBuilder ans = new StringBuilder();
        while(M-->0) {
            ans.append(pw.get(br.readLine())).append('\n');
        }

        System.out.print(ans);
    }//main

}//class