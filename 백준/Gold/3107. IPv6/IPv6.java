import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문제 이름(난이도) : IPv6 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/3107
 * */
public class Main {
    static final int N = 8;
    static String[] ipv6;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ipv6 = br.readLine().split(":"); // 각 그룹은 콜론 (:)으로 구분
        br.close();

        StringBuilder ans = new StringBuilder();
        restore(ans); // 원래 IPv6 (32자리의 16진수)로 복원
        
        System.out.print(ans);
    }//main

    private static void restore(StringBuilder ans) {
        int cnt = 1;
        int len = ipv6.length;
        fill(ans, ipv6[0]);

        for(int i=1; i<len; i++) {
            if(ipv6[i].equals("")){
                int Z = N - (len-i) - (i-1);
                while(Z-->0) {
                    ans.append(":");
                    fill(ans, "");
                    cnt++;
                }//while
            }else {
                ans.append(":");
                fill(ans, ipv6[i]);
                cnt++;
            }
        }//for

        if(cnt < N) {
            int Z = N-cnt;
            while(Z-->0) {
                ans.append(":");
                fill(ans,"");
            }//while
        }//if
        
    }//restore

    
    // 앞자리 0으로 채우기
    private static void fill(StringBuilder ans, String s) {
        for(int i=0, max=4-s.length(); i<max; i++) {
            ans.append('0');
        }//for
        ans.append(s);
    }//fill

}//class