import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2866
public class Main {
    static int R, C;
    static char[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];

        for(int i=0; i<R; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        br.close();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    private static int getCnt() {
        int start = 0;
        int end = R-1;
        int mid;

        while(start <= end) {
            mid = (start+end) / 2;
            if(isDuplication(mid)) end = mid-1;
            else start = mid+1;
        }

        return start;
    }//getCnt

    private static boolean isDuplication(int mid) {
        Set<String> set = new HashSet<>();

        for(int c=0; c<C; c++) {
            StringBuilder str = new StringBuilder();

            for(int r=mid+1; r<R; r++) {
                str.append(arr[r][c]);
            }

            if(set.contains(String.valueOf(str))) return true;
            set.add(String.valueOf(str));
        }

        return false;
    }//isDuplication
    
}//class