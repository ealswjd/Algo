import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14627
public class Main {
    private static int S; // 파의 개수
    private static int C; // 파닭의 수
    private static int maxLen; // 시장에서 사온 파 최대 길이
    private static long total; // 파 전체 길이
    private static int[] length; // 파의 길이


    public static void main(String[] args) throws IOException {
        init();

        long length = getLength();
        System.out.println(length);
    }//main


    private static long getLength() {
        long len = 0; // 파닭에 넣을 파 최대 길이
        int start = 1;
        int end = maxLen;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                start = mid + 1;
                len = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return total - len * C;
    }//getLength


    private static boolean isPossible(int mid) {
        int count = 0; // 만들 수 있는 파닭 개수

        for(int len : length) {
            count += len / mid;
            if(count >= C) return true;
        }

        return count >= C;
    }//isPossible


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // 파의 개수
        C = Integer.parseInt(st.nextToken()); // 파닭의 수

        length = new int[S];

        for(int i=0; i<S; i++) {
            length[i] = Integer.parseInt(br.readLine());
            maxLen = Math.max(maxLen, length[i]);
            total += length[i];
        }

        br.close();
    }//init


}//class